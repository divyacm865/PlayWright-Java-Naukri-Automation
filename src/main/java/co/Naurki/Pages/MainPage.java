package co.Naurki.Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.io.IOException;

import co.Naukri.BasePage.BasePage;
import co.Naukri.Report.ExtentReport;

public class MainPage extends BasePage {

	private final Locator username;
	private final Locator password;
	private final Locator loginButton;
	private final Locator loginBtn;
	private final Locator profileName;

	public MainPage(Page page) {
		super(page);
		loginBtn = page.locator("#login_Layer");
		username = page.getByLabel("Email ID / Username");
		password = page.getByLabel("Password");
		loginButton = page.locator("//button[@type='submit']");
		profileName = page.getByTitle("Divya C M");

	}

	public void login(String userName, String passWord) throws IOException {
		click(loginBtn);
		type(username, userName);
		type(password, passWord);
		ExtentReport.captureScreenshot(page, "Login to Naurki ");
		click(loginButton);
	}

	public void confirmProfile(String profile) throws IOException {
		waitForElement(profileName);
		assertThat(profileName).hasText(profile);
		System.out.println(page.title());
		ExtentReport.captureScreenshot(page, "Profile is");
	}

}
