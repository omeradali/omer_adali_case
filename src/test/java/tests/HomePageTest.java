package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CareersPage;
import pages.HomePage;
import pages.QualityAssurancePage;

import java.util.Set;

public class HomePageTest extends BaseTest {

    @Test(priority = 1, description = "Verify Insider home page and complete careers journey to QA positions")
    public void verifyInsiderHomePageAndCareersTest() {
        HomePage homePage = new HomePage(driver);
        CareersPage careersPage = new CareersPage(driver);
        QualityAssurancePage qaPage = new QualityAssurancePage(driver);

        homePage.navigateToHomePage();

        homePage.acceptCookies();

        Assert.assertTrue(homePage.verifyHomePageUrl(),
            "Home page URL should contain 'insiderone.com'");

        Assert.assertTrue(homePage.verifyPageTitle(),
            "Page title should not be empty");

        Assert.assertTrue(homePage.isNavigationMenuDisplayed(),
            "Navigation menu should be displayed on the home page");

        Assert.assertTrue(homePage.isMainContentDisplayed(),
            "Main content section should be displayed on the home page");

        Assert.assertTrue(homePage.isFooterDisplayed(),
            "Footer section should be displayed on the home page");

        Assert.assertTrue(homePage.isPageBodyDisplayed(),
            "Page body should be displayed on the home page");

        String mainWindow = driver.getWindowHandle();

        careersPage.scrollToFooterAndClickWeAreHiring();

        Assert.assertTrue(careersPage.verifyOnCareersPage(),
            "Should navigate to Careers page");

        careersPage.clickSeeAllTeams();

        Assert.assertTrue(careersPage.isQualityAssuranceDepartmentDisplayed(),
            "Quality Assurance department should be displayed");

        careersPage.clickQAOpenPositions();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        qaPage.waitForPageToLoad();

        String currentUrl = qaPage.getPageUrl();
        System.out.println("QA Jobs Page URL: " + currentUrl);

        Assert.assertTrue(currentUrl.contains("jobs.lever.co") || currentUrl.contains("Quality"),
            "Should navigate to Quality Assurance jobs page. Current URL: " + currentUrl);

        qaPage.waitForPageToLoad();

        Assert.assertTrue(qaPage.isLocationDropdownDisplayed(),
            "Location dropdown should be displayed on the jobs page");

        qaPage.clickLocationDropdown();

        qaPage.selectIstanbulTurkiye();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String urlAfterLocation = qaPage.getPageUrl();
        System.out.println("URL after location selection: " + urlAfterLocation);
        
        Assert.assertTrue(urlAfterLocation.contains("jobs.lever.co/insiderone"),
            "Should be on jobs.lever.co/insiderone page. Current URL: " + urlAfterLocation);
        
        Assert.assertTrue(urlAfterLocation.contains("team=Quality%20Assurance"),
            "URL should contain team=Quality%20Assurance. Current URL: " + urlAfterLocation);
        
        Assert.assertTrue(urlAfterLocation.contains("location=Istanbul%2C%20Turkiye"),
            "URL should contain location=Istanbul%2C%20Turkiye. Current URL: " + urlAfterLocation);

        Assert.assertTrue(qaPage.verifyTeamCategoryIsQualityAssurance(),
            "Team category should be 'Quality Assurance'");

        Assert.assertTrue(qaPage.verifyPostingName("Software Quality Assurance Engineer (Remote)"),
            "Posting name should be 'Software Quality Assurance Engineer (Remote)'");

        Assert.assertTrue(qaPage.verifyJobLocation("Istanbul, Turkiye"),
            "Job location should be 'Istanbul, Turkiye'");

        qaPage.clickApplyButton();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String jobDetailUrl = qaPage.getPageUrl();
        System.out.println("Job detail page URL: " + jobDetailUrl);

        Assert.assertTrue(jobDetailUrl.contains("jobs.lever.co/insiderone/774658ce-0d6e-4b07-a69b-4629fa11d6f3"),
            "Should navigate to job detail page. Current URL: " + jobDetailUrl);

        qaPage.clickApplyForThisJobButton();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String applyPageUrl = qaPage.getPageUrl();
        System.out.println("Apply page URL: " + applyPageUrl);

        Assert.assertTrue(applyPageUrl.contains("jobs.lever.co/insiderone/774658ce-0d6e-4b07-a69b-4629fa11d6f3/apply"),
            "Should navigate to job application page. Current URL: " + applyPageUrl);

        Assert.assertTrue(qaPage.isSubmitApplicationButtonDisplayed(),
            "Submit application button should be displayed on the application page");
    }
}
