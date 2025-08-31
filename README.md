# Playwright Test Automation Framework

This project demonstrates a test automation framework using:
- Playwright for Java
- TestNG
- Maven
- Allure Reports
- Page Object Model (POM)

## Project Structure
```
├── src
│   ├── main/java/pages        # Page Objects
│   │   ├── GooglePage.java    # Google Search page actions
│   │   └── PlaywrightDevPage.java # Playwright docs page actions
│   └── test/java/tests        # Test Classes
│       ├── TestBase.java      # Base test configuration
│       └── GoogleSearchTest.java # Test scenarios
├── pom.xml                    # Maven dependencies and plugins
└── README.md
```

## Prerequisites
- Java JDK 11 or higher
- Maven 3.6 or higher
- Allure Command Line Tool (for reports)

## Setup
1. Clone the repository
2. Install dependencies:
```bash
mvn clean install
```
3. Install Playwright browsers:
```bash
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
```

## Running Tests
To run all tests:
```bash
mvn clean test
```

## Test Scenarios
1. Google Search and Navigation Test:
   - Opens Google in Chrome (headed mode)
   - Searches for "playwright"
   - Clicks on the Playwright.dev link
   - Navigates to "Docs" tab
   - Goes to "Test Configuration" section

2. Documentation Search Test:
   - Continues from the previous test
   - Uses the search functionality in Playwright docs
   - Searches for "fixture"
   - Verifies search results

## Viewing Test Reports
To generate and view Allure reports:
```bash
mvn allure:serve
```

## Framework Features
- Page Object Model design pattern
- Allure reporting with test steps and screenshots
- TestNG for test execution and assertions
- Maven for dependency management and build
- Configurable test timeouts and retry mechanisms
- Headed/Headless mode support
- Screenshot and video capture on test failure

## Configuration
The framework supports various configurations through TestBase.java:
- Browser selection (Chrome/Firefox/Safari)
- Headed/Headless mode
- Viewport size
- Default timeouts
- Screenshot and video capture settings

## Contributing
1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request
