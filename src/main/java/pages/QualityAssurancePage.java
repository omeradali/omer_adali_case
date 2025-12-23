package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QualityAssurancePage extends BasePage {

    private final By jobListings = By.cssSelector(".posting, .job-listing, [class*='position']");
    private final By pageHeader = By.cssSelector("h1, h2, .page-title");
    private final By locationDropdown = By.xpath("//div[@role='button' and @aria-label='Filter by Location: All']");
    private final By istanbulTurkiyeOption = By.xpath("//a[contains(@href, 'location=Istanbul%2C%20Turkiye')]");
    private final By teamDropdown = By.xpath("//div[@role='button' and contains(@aria-label, 'Filter by Team')]");
    private final By teamCategoryTitle = By.cssSelector(".posting-category-title.large-category-label");
    private final By postingName = By.cssSelector("h5[data-qa='posting-name']");
    private final By postingTitleLink = By.cssSelector("a.posting-title");
    private final By jobLocation = By.cssSelector("span.sort-by-location.posting-category.small-category-label.location");
    private final By applyButton = By.xpath("//a[contains(text(),'Apply')]");
    private final By applyForThisJobButton = By.xpath("(//a[normalize-space()='Apply for this job' and contains(@href,'jobs.lever.co')])[1]");
    private final By submitApplicationButton = By.xpath("//button[@data-qa='btn-submit' and normalize-space()='Submit application']");

    public QualityAssurancePage(WebDriver driver) {
        super(driver);
    }

    public boolean verifyOnQAJobsPage() {
        String url = getCurrentUrl();
        return url.contains("jobs.lever.co") && url.contains("Quality%20Assurance");
    }

    public boolean isJobListingsDisplayed() {
        return isElementDisplayed(jobListings);
    }

    public String getPageUrl() {
        return getCurrentUrl();
    }

    public void waitForPageToLoad() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public boolean isLocationDropdownDisplayed() {
        return isElementDisplayed(locationDropdown);
    }

    public void clickLocationDropdown() {
        clickElement(locationDropdown);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void selectIstanbulTurkiye() {
        scrollToElement(istanbulTurkiyeOption);
        clickElement(istanbulTurkiyeOption);
    }

    public boolean isTeamDropdownDisplayed() {
        return isElementDisplayed(teamDropdown);
    }

    public void clickTeamDropdown() {
        clickElement(teamDropdown);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String getTeamDropdownText() {
        WebElement element = findElement(teamDropdown);
        return element.getText();
    }

    public boolean verifyQualityAssuranceFilterSelected() {
        try {
            String dropdownText = getTeamDropdownText();
            
            if (dropdownText != null && !dropdownText.isEmpty()) {
                return dropdownText.toUpperCase().contains("QUALITY ASSURANCE");
            }
            
            WebElement element = findElement(teamDropdown);
            String ariaLabel = element.getAttribute("aria-label");
            
            if (ariaLabel != null) {
                return ariaLabel.toUpperCase().contains("QUALITY ASSURANCE");
            }
            
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyQualityAssuranceInTeamDropdown() {
        clickTeamDropdown();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        String dropdownText = getTeamDropdownText();
        return dropdownText.contains("Quality Assurance");
    }

    public String getTeamCategoryTitle() {
        WebElement element = findElement(teamCategoryTitle);
        return element.getText();
    }

    public String getPostingName() {
        WebElement element = findElement(postingName);
        return element.getText();
    }

    public String getJobLocation() {
        WebElement element = findElement(jobLocation);
        return element.getText();
    }

    public boolean verifyTeamCategoryIsQualityAssurance() {
        String teamCategory = getTeamCategoryTitle();
        return teamCategory.equalsIgnoreCase("Quality Assurance");
    }

    public boolean verifyPostingName(String expectedName) {
        String actualName = getPostingName();
        return actualName.equals(expectedName);
    }

    public boolean verifyJobLocation(String expectedLocation) {
        String actualLocation = getJobLocation();
        return actualLocation.equalsIgnoreCase(expectedLocation);
    }

    public void clickJobListing() {
        scrollToElement(postingTitleLink);
        clickElement(postingTitleLink);
    }

    public void clickApplyButton() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        WebElement element = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(applyButton));
        scrollToElement(applyButton);
        element.click();
    }

    public void clickApplyForThisJobButton() {
        scrollToElement(applyForThisJobButton);
        clickElement(applyForThisJobButton);
    }

    public boolean isSubmitApplicationButtonDisplayed() {
        try {
            WebElement element = findElement(submitApplicationButton);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
