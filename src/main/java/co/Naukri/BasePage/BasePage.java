package co.Naukri.BasePage;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Locator.WaitForOptions;
import com.microsoft.playwright.Page;

public class BasePage {

	final protected Page page;

	public BasePage(Page page) {
		this.page = page;
	}

	public static void click(Locator locator) {
		waitForElement(locator);
		locator.hover();
		locator.click();
	}

	public static void type(Locator ele, String text) {
		waitForElement(ele);
		ele.hover();
		ele.click();
		ele.fill(text);
	}

	public static String textValue(Locator ele) {
		waitForElement(ele);
		return ele.textContent();
	}

	public static void waitForElement(Locator ele) {
		ele.waitFor(new WaitForOptions().setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE));
	}
}
