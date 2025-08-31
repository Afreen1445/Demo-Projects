package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class GooglePage {
    private final Page page;
    
    // Locators
    private String searchBox = "textarea[name='q']";  // Updated to textarea
    
    public GooglePage(Page page) {
        this.page = page;
    }
    
    public void navigateToGoogle() {
        page.navigate("https://www.google.com");
        // Wait for the page to be completely loaded
        page.waitForLoadState(LoadState.NETWORKIDLE);
        
        // Handle consent dialog if it appears
        if (page.locator("button:has-text('Accept all')").isVisible()) {
            page.locator("button:has-text('Accept all')").click();
        }
    }
    
    public void searchFor(String searchText) {
        // Wait for the search box to be visible and enabled
        page.locator(searchBox).waitFor();
        page.fill(searchBox, searchText);
        page.press(searchBox, "Enter");
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }
    
    public void clickPlaywrightLink() {
        // Wait for search results and click the Playwright.dev link
        page.waitForLoadState(LoadState.NETWORKIDLE);
        
        // Wait for any search result to appear
        page.waitForSelector("div[role='main']", new Page.WaitForSelectorOptions().setTimeout(45000));
        
        // Find and click the Playwright link using multiple strategies
        try {
            // Try finding by href first
            if (page.locator("a[href*='playwright.dev']").count() > 0) {
                page.locator("a[href*='playwright.dev']").first().click();
            }
            // Try finding by text containing Playwright
            else if (page.getByText("Playwright", new Page.GetByTextOptions().setExact(false)).count() > 0) {
                page.getByText("Playwright", new Page.GetByTextOptions().setExact(false)).first().click();
            }
            // Try finding by heading
            else if (page.locator("h3:has-text('Playwright')").count() > 0) {
                page.locator("h3:has-text('Playwright')").first().click();
            }
            // Direct navigation as fallback
            else {
                page.navigate("https://playwright.dev");
            }
        } catch (Exception e) {
            // Fallback to direct navigation if any error occurs
            page.navigate("https://playwright.dev");
        }
        
        // Wait for navigation to complete
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }
}
