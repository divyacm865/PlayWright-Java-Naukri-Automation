package co.Naurki.Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.io.IOException;
import java.nio.file.Paths;

import com.microsoft.playwright.FileChooser;

import co.Naukri.BasePage.BasePage;
import co.Naukri.Report.ExtentReport;

public class ProfilePage extends BasePage {

	private final Locator profile;
	private final Locator updateResume;
	private final Locator deleteButton;
	private final Locator delete;
	private final Locator mobile;
	private final Locator uploadResume;
	private final Locator openProfileMenuBtn;
	private final Locator logOutBtn;

	public ProfilePage(Page page) {
		super(page);
		profile = page.locator("//a[text()='View']");
		updateResume = page.getByText("Update resume");
		deleteButton = page.getByText("deleteOneTheme");
		delete = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete"));
		mobile = page.locator("//span[@name='Mobile']");
		uploadResume = page.getByText("Upload resume");
		openProfileMenuBtn = page.getByLabel("Open profile menu");
		logOutBtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Logout"));
	}

	public void goToProfile(String mobileNum) {
		waitForElement(profile);
		click(profile);
		waitForElement(mobile);
		assertThat(mobile).hasText(mobileNum);
	}

	public void removeOldResume() {
		click(deleteButton);
	}

	public void verifyDeletPopUp() throws IOException {
		System.out.println(page.getByText("Are you sure you want to delete the resume?").textContent());
		ExtentReport.captureScreenshot(page, "Deleting old resume");
		assertThat(page.getByText("Are you sure you want to delete the resume?"))
				.hasText("Are you sure you want to delete the resume?");
	}

	public void confirmDelete() {
		click(delete.last());
	}

	public void uploadResume(String filePath) throws IOException {
		FileChooser fileChooser = page.waitForFileChooser(() -> page.getByText("Upload Resume").click());
		fileChooser.setFiles(Paths.get(filePath));
		waitForElement(delete);
		ExtentReport.captureScreenshot(page, "Uploaded Resume");
	}

	public void logOutFromProfile() throws IOException {
		click(openProfileMenuBtn);
		click(logOutBtn);
		System.out.println(page.title());
		ExtentReport.captureScreenshotOFFullPage(page, "fullPage");

	}

}
