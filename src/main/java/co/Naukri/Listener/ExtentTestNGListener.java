package co.Naukri.Listener;

import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import co.Naukri.Report.ExtentReport;


public class ExtentTestNGListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        ExtentReport.initReport();
    }

    @Override
    public void onTestStart(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        if (result.getParameters().length > 0) {
            testName += " - "
                    + Arrays.toString(result.getParameters());
        }

        ExtentReport.createTest(testName);

        ExtentReport.getTest()
                .log(Status.INFO, "Test execution started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    	ExtentReport.getTest()
                .log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	ExtentReport.getTest()
                .log(Status.FAIL, "Test failed");

        if (result.getThrowable() != null) {
        	ExtentReport.getTest()
                    .fail(result.getThrowable());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    	ExtentReport.getTest()
                .log(Status.SKIP, "Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
    	ExtentReport.flushReport();
    	ExtentReport.removeTest();
    }
}


