package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     @Test
     public void testCase01(){
        try{
            String flipkartUrl="https://www.flipkart.com/";
        if(!driver.getCurrentUrl().equals(flipkartUrl)){
            driver.get(flipkartUrl);
            System.out.println("User is navigated to flipkart");
        }

        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
        WebElement search=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@title='Search for Products, Brands and More']")));
        Wrappers.enterText(driver, search, "Washing Machine");
        

        wait.until(ExpectedConditions.urlContains("Washing"));
        System.out.println("User is navigated to washing machine results page");

        WebElement sortByPopularity=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Popularity']")));
        Wrappers.clickOnElement(driver, sortByPopularity);

        Wrappers.sortByPopularity(driver);
    
    }catch(Exception e){
        System.out.println("Exception occurred in testCase01 "+e.getMessage());
        e.getStackTrace();
    }
}

     @Test
     public  void testCase02(){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        try{
            WebElement search=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@title='Search for products, brands and more']")));
            Wrappers.enterText(driver, search, "iPhone");
           
    
            wait.until(ExpectedConditions.urlContains("iPhone"));
            System.out.println("User is navigated to iPhone results page");

            Wrappers.printTitleWithDiscount(driver);
        
        }catch(Exception e){
            System.out.println("Exception occurred in testCase02 "+e.getMessage());
            e.getStackTrace();
        }

     }

     @Test
     public void testCase03(){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        try{
            WebElement search=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@title='Search for products, brands and more']")));
            Wrappers.enterText(driver, search, "Coffee Mug");
           
    
            wait.until(ExpectedConditions.urlContains("Coffee"));
            System.out.println("User is navigated to Coffee Mug results page");

            Wrappers.printProductDetails(driver);
        
        }catch(Exception e){
            System.out.println("Exception occurred in testCase03 "+e.getMessage());
            e.getStackTrace();
        }

     }
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}