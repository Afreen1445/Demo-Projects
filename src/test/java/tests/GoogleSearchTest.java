package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import pages.GooglePage;
import pages.PlaywrightDevPage;

public class GoogleSearchTest extends TestBase {

    @Test(priority = 1)
    @Description("Test Google Search and navigate to Playwright documentation")
    @Severity(SeverityLevel.NORMAL)
    public void testGoogleSearch() {
        // Initialize page objects
        GooglePage googlePage = new GooglePage(page);
        PlaywrightDevPage playwrightPage = new PlaywrightDevPage(page);

        // Search for Playwright
        googlePage.navigateToGoogle();
        googlePage.searchFor("playwright");
        
        // Navigate to Playwright.dev
        googlePage.clickPlaywrightLink();
        
        // Navigate to documentation and test configuration
        playwrightPage.clickDocsTab();
        playwrightPage.navigateToTestConfiguration();
        
        // Add a small wait to see the final page
        page.waitForTimeout(3000);
    }
    
    @Test(priority = 2, dependsOnMethods = "testGoogleSearch")
    @Description("Search for 'fixture' in Playwright documentation")
    @Severity(SeverityLevel.NORMAL)
    public void testSearchInDocs() {
        // Since we're already on the Playwright docs page from the previous test,
        // just initialize PlaywrightDevPage and perform the search
        PlaywrightDevPage playwrightPage = new PlaywrightDevPage(page);
        
        // Now search for fixture in the documentation
        playwrightPage.searchInDocs("fixture");
        
        // Add a small wait to see the search results
        page.waitForTimeout(3000);
    }
}
