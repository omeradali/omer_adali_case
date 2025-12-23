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
            System.out.println("Team Dropdown Text: " + dropdownText);
            
            if (dropdownText != null && !dropdownText.isEmpty()) {
                boolean result = dropdownText.toUpperCase().contains("QUALITY ASSURANCE");
                System.out.println("Contains 'Quality Assurance' (case-insensitive): " + result);
                return result;
            }
            
            // Alternatif: aria-label'ı kontrol et
            WebElement element = findElement(teamDropdown);
            String ariaLabel = element.getAttribute("aria-label");
            System.out.println("Team Dropdown aria-label: " + ariaLabel);
            
            if (ariaLabel != null) {
                boolean result = ariaLabel.toUpperCase().contains("QUALITY ASSURANCE");
                System.out.println("aria-label contains 'Quality Assurance' (case-insensitive): " + result);
                return result;
            }
            
            return false;
        } catch (Exception e) {
            System.out.println("Error verifying Quality Assurance filter: " + e.getMessage());
            e.printStackTrace();
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
        System.out.println("Team Dropdown after click: " + dropdownText);
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
        System.out.println("Team Category Title: " + teamCategory);
        boolean isQA = teamCategory.equalsIgnoreCase("Quality Assurance");
        System.out.println("Is Quality Assurance: " + isQA);
        return isQA;
    }

    public boolean verifyPostingName(String expectedName) {
        String actualName = getPostingName();
        System.out.println("Posting Name: " + actualName);
        boolean matches = actualName.equals(expectedName);
        System.out.println("Posting name matches: " + matches);
        return matches;
    }

    public boolean verifyJobLocation(String expectedLocation) {
        String actualLocation = getJobLocation();
        System.out.println("Job Location: " + actualLocation);
        boolean matches = actualLocation.equalsIgnoreCase(expectedLocation);
        System.out.println("Location matches: " + matches);
        return matches;
    }

    public void clickJobListing() {
        scrollToElement(postingTitleLink);
        clickElement(postingTitleLink);
        System.out.println("Job listing clicked");
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
        System.out.println("Apply button clicked");
    }

    public void clickApplyForThisJobButton() {
        scrollToElement(applyForThisJobButton);
        clickElement(applyForThisJobButton);
        System.out.println("Apply for this job button clicked");
    }

    public boolean isSubmitApplicationButtonDisplayed() {
        try {
            WebElement element = findElement(submitApplicationButton);
            boolean isDisplayed = element.isDisplayed();
            System.out.println("Submit application button is displayed: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            System.out.println("Submit application button not found: " + e.getMessage());
            return false;
        }
    }
}
