package co.Naukri.UploadResumes;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import co.Naukri.BaseTest.BaseTest;
import co.Naukri.DataProvider.TestDataProvider;
import co.Naukri.DataProvider.YAMLConfigData;
import co.Naukri.DataProvider.YAMLConfigData.UserConfig;
import co.Naukri.DataReader.YamlReader;
import co.Naurki.Pages.MainPage;
import co.Naurki.Pages.ProfilePage;
import co.Naukri.Listener.ExtentTestNGListener;


@Listeners(ExtentTestNGListener.class)
public class UploadResume extends BaseTest {

	@Test(dataProvider="loginData",dataProviderClass = TestDataProvider.class)
	public void TestingJobApply(String ProfileName, String Mobile,String ResumePath, String read1, String read2) throws IOException {
		Page page = initialiseBroswer(TestDataProvider.getEnv("browser"), TestDataProvider.getEnv("url"), TestDataProvider.getEnv("headless"));
		MainPage mainPage = new MainPage(page);
		ProfilePage profilePage = new ProfilePage(page);
		@SuppressWarnings("rawtypes")
		YAMLConfigData config = YamlReader.loadConfig();
		YAMLConfigData.UserConfig user = (UserConfig) config.getUsers().get(0); 
	
		try {
			mainPage.login( user.getUsername(), user.getPassword());
			mainPage.confirmProfile(ProfileName);
			profilePage.goToProfile(Mobile);
			profilePage.removeOldResume();
			profilePage.verifyDeletPopUp();
			profilePage.confirmDelete();
			profilePage.uploadResume(ResumePath);
			System.out.println("Resume upload completed");
		//	page.pause(); 
			profilePage.logOutFromProfile();
			System.out.println("Logged out");
			System.out.println(read1);
			System.out.println(read2);
		//	page.pause(); 
		} finally {
		}

	}

}
