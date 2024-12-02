## Automated Flipkart Product Search and Analysis

## Project Title
Automating Flipkart Product Search and Analysis Using Selenium

## Description
This project automates product search and analysis workflows on Flipkart using Selenium WebDriver. The automation process handles various functionalities, such as searching for products, applying filters, sorting results, and extracting key data for further analysis.

## Key Features
- Navigation: Automatically navigates to Flipkart's homepage and handles the login prompt (if required).
- Product Search: Dynamically searches for specified products, such as "Washing Machine," "iPhone," or "Coffee Mug."
- Sorting and Filtering: Applies filters (e.g., star ratings) and sorting options (e.g., by popularity or reviews).
- Data Extraction: Extracts and processes key data, such as:
- The count of products with specific star ratings.
- Titles and discounts of products with significant price reductions.
- Titles and image URLs of top-rated products with the highest reviews.
- Dynamic Analysis: Uses extracted data to validate search results and present meaningful insights.

## Installation
1. Clone the Repository:
git clone https://github.com/gourishahane/Flipkart-Automation.git

2. Set Up the Environment:
- Install Java 11 or higher.
- Download and configure ChromeDriver compatible with your browser version.
- Install Gradle or Maven for dependency management.
- Add the required dependencies for Selenium WebDriver and TestNG in the build configuration file (build.gradle or pom.xml).

3. Navigate to the Project Directory:
cd ME_QA_XFLIPKART_MODULE_L1

4. Build the Project:
gradle build

5. Set Up Browser Drivers: Ensure the required browser driver (e.g., ChromeDriver) is installed and available in the system path.
   
6. Execute Tests:
gradle test

## Usage
1. Run Test Cases: Execute specific TestNG test classes to automate different workflows:
- TestCase01: Searches for "Washing Machine," applies filters, and counts products with ratings ≤ 4 stars.
- TestCase02: Searches for "iPhone" and extracts titles and discounts of products with >17% discounts.
- TestCase03: Searches for "Coffee Mug," filters by 4+ star ratings, and extracts titles and image URLs of top-reviewed products.

2. Key Functions:
- Wrappers: Reusable methods for searching products, applying filters, scrolling pages, and extracting data.
- Dynamic Sorting and Filtering: Automates the application of sorting and filtering criteria based on inputs.
- Validation: Ensures extracted data meets the specified criteria and logs results.
  
3. Output:
Detailed console logs for each test case, including extracted product titles, ratings, discounts, and other metadata.
Option to save data into files (e.g., CSV, JSON) for offline analysis.

## Dependencies
Selenium WebDriver for browser automation.
TestNG for test case execution and assertions.
Gradle as the build and dependency management tool.

## Contact Information
Name: Gouri Shahane
Email: gouri.shahane@example.com
GitHub: github.com/gourishahane
LinkedIn: linkedin.com/in/gourishahane
