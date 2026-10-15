package co.Naukri.Report;

import java.io.IOException;
import java.util.Base64;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.microsoft.playwright.Page;

public class ExtentReport {
	private static ExtentReports extent;

	private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	public static synchronized void initReport() {
		if (extent == null) {
			ExtentSparkReporter spark = new ExtentSparkReporter("Reports/ExtentReport.html");
			extent = new ExtentReports();
			spark.config().thumbnailForBase64(true);
			extent.attachReporter(spark);

			extent.setSystemInfo("Project", "Naukri Automation");
			extent.setSystemInfo("Tool", "Playwright Java");
		}
	}

	public static void createTest(String testName) {
		test.set(extent.createTest(testName));
	}

	public static ExtentTest getTest() {
		return test.get();
	}

	public static void flushReport() {
		if (extent != null) {
			extent.flush();
		}
	}

	public static void removeTest() {
		test.remove();
	}

	public static void captureScreenshot(Page page, String stepName) throws IOException {
		byte[] screenshotBytes = page.screenshot(new Page.ScreenshotOptions());
		String base64 = java.util.Base64.getEncoder().encodeToString(screenshotBytes);
		getTest().info(stepName, com.aventstack.extentreports.MediaEntityBuilder
				.createScreenCaptureFromBase64String(base64).build());
	}
	
	public static void captureScreenshotOFFullPage(Page page, String stepName) throws IOException {
		byte[] screenshotBytes = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
		String base64 = java.util.Base64.getEncoder().encodeToString(screenshotBytes);
		getTest().info(stepName, com.aventstack.extentreports.MediaEntityBuilder
				.createScreenCaptureFromBase64String(base64).build());
	}
}
