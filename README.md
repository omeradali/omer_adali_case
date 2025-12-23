# Insider Test Automation Project

This project contains automated test cases for the Insider website using Java, Selenium WebDriver, and TestNG framework with Page Object Model (POM) design pattern.

## Prerequisites

- Java JDK 11 or higher
- Maven 3.6 or higher
- Chrome browser

## Project Structure

```
InsiderTestAutomation/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── pages/
│   │           ├── BasePage.java
│   │           └── HomePage.java
│   └── test/
│       └── java/
│           ├── base/
│           │   └── BaseTest.java
│           └── tests/
│               └── HomePageTest.java
├── pom.xml
├── testng.xml
└── README.md
```

## Technologies Used

- **Java 11**: Programming language
- **Selenium WebDriver 4.15.0**: Browser automation
- **TestNG 7.8.0**: Testing framework
- **WebDriverManager 5.6.2**: Automatic driver management
- **Maven**: Build and dependency management

## Setup Instructions

1. Clone or download the project
2. Navigate to the project directory
3. Install dependencies:
   ```bash
   mvn clean install
   ```

## Running Tests

### Run all tests:
```bash
mvn test
```

### Run specific test class:
```bash
mvn test -Dtest=HomePageTest
```

### Run with TestNG XML:
```bash
mvn test -DsuiteXmlFile=testng.xml
```

## Test Cases

### HomePageTest
- **verifyHomePageIsOpenedAndMainBlocksAreLoaded**: Verifies that the Insider home page opens correctly and all main page elements are loaded including logo, navigation menu, main content, and footer.

## Page Object Model (POM)

The project follows the Page Object Model design pattern:

- **BasePage**: Contains common methods and WebDriver utilities used across all pages
- **HomePage**: Contains locators and methods specific to the home page
- **BaseTest**: Manages WebDriver setup and teardown for all test classes

## Features

- Clean and maintainable code structure
- Optimized CSS and XPath selectors
- Explicit waits for element visibility
- Comprehensive assertions
- Automatic ChromeDriver management
- Configurable test execution with TestNG
