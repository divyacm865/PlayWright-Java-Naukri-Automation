package co.Naukri.BaseTest;

import java.util.Arrays;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BaseTest {

	private static Playwright pw;
	private static Browser browser;
	private static BrowserType browserType;
	private static BrowserContext context;
	private static Page page;

	public Page initialiseBroswer(String browserEngine, String URL, String headLess) {
		pw = Playwright.create();
		if (browserEngine.equalsIgnoreCase("chrome")) {
			browserType = pw.chromium();
		} else if (browserEngine.equalsIgnoreCase("firefox")) {
			browserType = pw.firefox();
		} else if (browserEngine.equalsIgnoreCase("safari")) {
			browserType = pw.webkit();
		} else {
			throw new IllegalArgumentException("Unsupported browser: " + browserEngine);
		}
		if (headLess.equalsIgnoreCase("YES")) {
			browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false)
					.setArgs(java.util.Arrays.asList("--start-maximized")));
		} else {
			browser = browserType.launch(new BrowserType.LaunchOptions().setArgs(Arrays.asList("--start--maximized")));
		}
		context = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
		page = context.newPage();
		page.setDefaultTimeout(45000); 
		page.navigate(URL.trim());
		return page;
	}
}
