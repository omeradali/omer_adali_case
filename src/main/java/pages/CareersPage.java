package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CareersPage extends BasePage {

    private final By weAreHiringLink = By.cssSelector("a[href='/careers/'][data-text=\"We're hiring\"]");
    private final By seeAllTeamsButton = By.cssSelector("a.inso-btn.see-more");
    private final By qualityAssuranceDepartment = By.cssSelector("div[data-department='Quality Assurance']");
    private final By qaOpenPositionsButton = By.xpath("//div[@data-department='Quality Assurance']//a[contains(text(), 'See open positions') or contains(@href, 'jobs.lever.co')]");

    public CareersPage(WebDriver driver) {
        super(driver);
    }

    public void scrollToFooterAndClickWeAreHiring() {
        scrollToBottom();
        scrollToElement(weAreHiringLink);
        clickElement(weAreHiringLink);
    }

    public void clickSeeAllTeams() {
        scrollToElement(seeAllTeamsButton);
        clickElementWithJS(seeAllTeamsButton);
    }

    public boolean isQualityAssuranceDepartmentDisplayed() {
        return isElementDisplayed(qualityAssuranceDepartment);
    }

    public void clickQAOpenPositions() {
        scrollToElement(qaOpenPositionsButton);
        String href = findElement(qaOpenPositionsButton).getAttribute("href");
        
        if (href != null && !href.isEmpty()) {
            driver.get(href);
        } else {
            clickElementWithJS(qaOpenPositionsButton);
        }
    }

    public boolean verifyOnCareersPage() {
        return getCurrentUrl().contains("/careers");
    }
}
