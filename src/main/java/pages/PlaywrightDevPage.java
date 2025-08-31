package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import static com.microsoft.playwright.options.WaitForSelectorState.VISIBLE;

public class PlaywrightDevPage {
    private final Page page;
    
    // Locators
    private String docsTab = "nav[aria-label='Main'] >> text=Docs";
    private String testConfigLink = "nav[aria-label='Docs sidebar'] >> text=Test configuration";
    private String searchButton = "button[type='button']:has-text('Search')";
    private String searchInput = "#docsearch-input";
    
    public PlaywrightDevPage(Page page) {
        this.page = page;
    }
    
    public void clickDocsTab() {
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.locator(docsTab).click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }
    
    public void navigateToTestConfiguration() {
        page.locator(testConfigLink).click();
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }
    
    public void searchInDocs(String searchText) {
        // Wait for the search button to be visible and click it
        page.waitForSelector(searchButton, new Page.WaitForSelectorOptions().setState(VISIBLE));
        page.locator(searchButton).first().click();
        
        // Wait for the search modal to appear and be ready
        page.waitForSelector("div[class*='DocSearch-Modal']", new Page.WaitForSelectorOptions().setState(VISIBLE));
        
        // Wait for search input to be visible and type the search text
        page.waitForSelector(searchInput, new Page.WaitForSelectorOptions().setState(VISIBLE));
        page.fill(searchInput, searchText);
        
        // Wait a moment for search results to appear
        page.waitForTimeout(2000);
    }
}
