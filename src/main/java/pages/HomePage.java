package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    
    private static final String HOME_PAGE_URL = "https://insiderone.com/";
    
    private final By logoElement = By.cssSelector("header img, .site-logo img, a[href*='insiderone'] img");
    private final By navigationMenu = By.cssSelector("header, nav, .navigation, .menu");
    private final By mainContent = By.cssSelector("main, #main, .main-content, article");
    private final By footerSection = By.cssSelector("footer, .footer, .site-footer");
    private final By pageBody = By.cssSelector("body");
    private final By acceptAllCookiesButton = By.cssSelector("button#accept-all-cookies, button[id*='accept'], a#wt-cli-accept-all-btn");
    
    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    public void navigateToHomePage() {
        driver.get(HOME_PAGE_URL);
    }
    
    public boolean isLogoDisplayed() {
        return isElementDisplayed(logoElement);
    }
    
    public boolean isNavigationMenuDisplayed() {
        return isElementDisplayed(navigationMenu);
    }
    
    public boolean isMainContentDisplayed() {
        return isElementDisplayed(mainContent);
    }
    
    public boolean isFooterDisplayed() {
        return isElementDisplayed(footerSection);
    }
    
    public boolean isPageBodyDisplayed() {
        return isElementDisplayed(pageBody);
    }
    
    public boolean verifyHomePageUrl() {
        return getCurrentUrl().contains("insiderone.com");
    }
    
    public boolean verifyPageTitle() {
        String title = getPageTitle();
        return title != null && !title.isEmpty();
    }

    public void acceptCookies() {
        try {
            if (isElementDisplayed(acceptAllCookiesButton)) {
                clickElement(acceptAllCookiesButton);
            }
        } catch (Exception e) {
            System.out.println("Cookie banner not found or already accepted");
        }
    }
}
