package com.example.demo.seleniumtest.endtoend;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class DynamicApiTest {

    @Test
    public void dynamicResponse() {

        RestAssured.baseURI = "https://92do4.wiremockapi.cloud/";

        Response repnse = RestAssured.given().and().when().get("things/4");
        repnse.then().body(JsonSchemaValidator
                .matchesJsonSchemaInClasspath("schemas/dynamicbody.json"));

        repnse.then().log().all();

        Object val = repnse.jsonPath().get("value");

        if (val instanceof String) {
            System.out.println(val);
        } else if (val instanceof Integer) {
            System.out.println(val);
        } else if (val instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) val;
            System.out.println(map);
            System.out.println(map.get("amount"));
        } else if (val instanceof List) {
            List<?> list = (List<?>) val;
            list.forEach(item -> System.out.println(item));

        }

        // Map<String, Object> user = repnse.jsonPath().get("$");

        // if (user != null && user.containsKey("value")) {
        // Map<String, Object> map = (Map<String, Object>) user.get("value");
        // System.out.println(map.get("currency"));
        // }

        Long time = repnse.getTime();
        System.out.println(time);

    }

    @Test
    public void apiPaginationValidate() {
        RestAssured.baseURI = "https://reqres.in/api";
        RestAssured.baseURI = "https://92do4.wiremockapi.cloud/";
        int page = 1;
        boolean flag = true;
        Set<Integer> set = new HashSet<>();

        // Response user = RestAssured.given().queryParam("page", page).get("/users");
        // user.then().log().all();

        while (flag) {
            Response user = RestAssured.given().queryParam("page", page).get("things");
            user.then().log().all();

            List<Map<String, ?>> data = user.body().jsonPath().get("data");

            for (Map<String, ?> map : data) {
                int id = (int) map.get("id");
                Assert.assertNotNull(id, "id is null");
                System.out.println(id);

                // Checking ids
                Assert.assertTrue(set.add(id), "user id not found" + id);

                // checking email, firstname and lastname
                Assert.assertNotNull(map.get("email"), "Email is null for user id" + id);
                Assert.assertNotNull(map.get("first_name"), "first_name is null for user id " + id);
                Assert.assertNotNull(map.get("last_name"), "last_name is null for user id " + id);
            }

            int total = user.body().jsonPath().getInt("total_pages");
            flag = page < total;
            page++;

        }

    }

    @Test
    public void testApiRateLimit() {

        int maxRetries = 5;
        int retryCount = 0;
        int waiTime = 1000; // in milliseconds(start with 1 seconds)

        RestAssured.baseURI = "https://92do4.wiremockapi.cloud/";

        while (retryCount < maxRetries) {

            Response rep = RestAssured.given().and().when().get("things");

            int code = rep.getStatusCode();

            if (code == 200) {
                System.out.println("getting correct response");
                break;
            } else if (code == 429) {
                try {
                    Thread.sleep(waiTime);
                } catch (InterruptedException e) {
                    System.out.println("tomany response");
                    break;
                }
                waiTime *= 2;
                retryCount++;

            } else {
                System.out.println("failed with status code " + code);
                break;
            }

        }

        if (retryCount == maxRetries) {
            System.out.println("you have tries max times");
        }

    }

    @Test
    public void ConcurrentLoadTest() throws InterruptedException {

        int userCount = 1000;
        ExecutorService exe = Executors.newFixedThreadPool(100);
        CountDownLatch latch = new CountDownLatch(userCount);

        for (int i = 0; i < userCount; i++) {

            exe.submit(() -> {
                try {
                    RestAssured.baseURI = "https://reqres.in/api";

                    Response re = RestAssured.given().and().when().get("/user");
                    System.out.println(Thread.currentThread().getName() + " " + re.getStatusCode());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        exe.shutdown();

    }

    // 1 way to handle timeout
    @Test
    public void timeOutHandle() {

        // 1. Configure HTTP timeouts globally
        RestAssured.config = RestAssuredConfig.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.socket.timeout", 5000)
                        .setParam("http.connection.timeout", 5000)
                        .setParam("http.connection.request.timeout", 4000));

        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RestAssured.baseURI = "https://reqres.in/api";

        Response rep = RestAssured.given().and().when().get("/user");

        if (rep.getStatusCode() == 200) {
            System.out.println("Response received successfully");
        } else {
            System.out.println("Failed to receive response, status code: " + rep.getStatusCode());
        }

    }

}

// 2 way to handle timeout
class RetryFilter implements io.restassured.filter.Filter {

    private final int maxRetries;
    private final long waitTime;

    public RetryFilter(int maxRetries, long waitTime) {
        this.maxRetries = maxRetries;
        this.waitTime = waitTime;
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
            FilterContext ctx) {
        int attempt = 0;
        Response response = null;
        Exception lastException = null;

        while (attempt < maxRetries) {

            try {
                response = ctx.next(requestSpec, responseSpec);
                if (response.getStatusCode() < 500) {
                    return response; // Return the successful response
                }

            } catch (Exception e) {
                lastException = e; // Store the last exception
            }
            attempt++;
            try {
                Thread.sleep(waitTime * attempt); // exponential backoff
            } catch (Exception e) {
                Thread.currentThread().interrupt(); // Restore the interrupted status
                break; // Exit the loop if interrupted
            }
        }

        throw new RuntimeException("All retry attempts failed", lastException);

    }

}
