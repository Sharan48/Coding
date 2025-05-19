package com.example.demo.seleniumtest.endtoend;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

    private static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports extentManager() {

        ExtentSparkReporter report = new ExtentSparkReporter("target/SparkReport/ExtentReport.html");
        report.config().setDocumentTitle("Api Testing");
        extent = new ExtentReports();
        extent.attachReporter(report);
        extent.setSystemInfo("Report_Name", "Api Testing");
        return extent;

    }

    public static ExtentTest extentTest(String name) {
        test = extentManager().createTest(name);
        return test;

    }

    public static void extentFlush() {
        extent.flush();
    }

}
