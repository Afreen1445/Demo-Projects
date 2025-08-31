package tests;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.util.Arrays;

public class TestBase {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeClass
    public void setUp() {
        playwright = Playwright.create();
        
        // Configure browser launch options
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
            .setHeadless(false)
            .setArgs(Arrays.asList("--start-maximized"))
            .setTimeout(60000); // 60 seconds timeout
        
        browser = playwright.chromium().launch(launchOptions);
        
        // Configure browser context
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
            .setViewportSize(null) // Use maximized window size
            .setAcceptDownloads(true)
            .setHasTouch(true);
            
        context = browser.newContext(contextOptions);
        
        // Configure page timeouts
        page = context.newPage();
        page.setDefaultTimeout(45000); // 45 seconds timeout for all operations
    }

    @AfterClass
    public void tearDown() {
        if (page != null) {
            page.close();
        }
        if (context != null) {
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
