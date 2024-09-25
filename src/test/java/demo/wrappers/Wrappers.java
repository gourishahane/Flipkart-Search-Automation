package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    public static void enterText(ChromeDriver driver,WebElement element,String text){
        try{
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
            Thread.sleep(2000);
            element.click();
            element.clear();
            element.sendKeys(text);
            
            String textEntered=element.getAttribute("value");
            if(textEntered.equals(text)){
                System.out.println("Text entered successfully: "+textEntered);
            }else{
                System.out.println("Text entry failed: "+textEntered);
            }
            element.sendKeys(Keys.ENTER);
            System.out.println("Enter key pressed.");

        }catch(Exception e){
            System.out.println("Exception occurred while entering text: "+e.getMessage());
            e.getStackTrace();
        }
    }

    public static void clickOnElement(ChromeDriver driver,WebElement element){
        try{
            element.click();
            System.out.println("User clicked the element");
            
           }catch(Exception e){
            System.out.println("Exception occurred while clicking element");
        }
    }

    public static void sortByPopularity(ChromeDriver driver) throws InterruptedException{
        List<WebElement> washingMachineRatingElements=driver.findElements(By.xpath("//div[@class='tUxRFH']//span[@class='Y1HWO0']"));
        int machineCount=washingMachineRatingElements.size();
        int ratingCount=0;
        for(int i=0; i<machineCount; i++){
            
        try{
        washingMachineRatingElements=driver.findElements(By.xpath("//div[@class='tUxRFH']//span[@class='Y1HWO0']"));
        
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",washingMachineRatingElements.get(i));
        String ratingText=washingMachineRatingElements.get(i).getText();
            float rating=Float.parseFloat(ratingText);
           
            if (rating<=4.0) {
                ratingCount++;
               
            }
        }catch (StaleElementReferenceException e) {
                System.out.println("Stale element found. Retrying...");
                i--;  
            }
        }
        System.out.println("Count of items with rating less than or equal to 4 stars is: "+ratingCount);
    }
    public static void printTitleWithDiscount(ChromeDriver driver) throws InterruptedException{
        List<WebElement> iPhoneDiscountElements=driver.findElements(By.xpath("//div[@class='tUxRFH']//div[@class='col col-5-12 BfVC2z']//span"));
        List<WebElement> iPhoneTitleElements=driver.findElements(By.xpath("//div[@class='tUxRFH']//div[@class='KzDlHZ']"));
        
        int elementsWithDiscount=iPhoneDiscountElements.size();

            for(int i=0; i<elementsWithDiscount; i++){
                try{
                    iPhoneDiscountElements=driver.findElements(By.xpath("//div[@class='tUxRFH']//div[@class='col col-5-12 BfVC2z']//span"));
                    iPhoneTitleElements=driver.findElements(By.xpath("//div[@class='tUxRFH']//div[@class='KzDlHZ']"));

                    String discountText = iPhoneDiscountElements.get(i).getText();
                    String titleText = iPhoneTitleElements.get(i).getText();
                    
                    if (discountText.contains("%")) {
                    
                    int discountValue = Integer.parseInt(discountText.replaceAll("[^0-9]", ""));
                    
                    if (discountValue > 17) {
                        System.out.println("Title: " + titleText);
                        System.out.println("Discount: " + discountText);
                    }
                }


                }catch(StaleElementReferenceException e) {
                System.out.println("Stale element found. Retrying...");
                i--;  
            }
        }
    }
    

    public static void printProductDetails(ChromeDriver driver) {
        // Wait to ensure the elements are fully loaded
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        try {
            // Wait for the first rating element to be visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='slAVV4']//div[@class='XQDdHH']")));
        } catch (TimeoutException e) {
            System.out.println("Elements not loaded in time.");
            return;
        }
        
        // Locate coffee mug elements
        List<WebElement> ratingElements = driver.findElements(By.xpath("//div[@class='slAVV4']//div[@class='XQDdHH']"));
        List<WebElement> titleElements = driver.findElements(By.xpath("//div[@class='slAVV4']//a[@class='wjcEIp']"));
        List<WebElement> imageElements = driver.findElements(By.xpath("//div[@class='slAVV4']//img[@class='DByuf4']"));
        
        // Logging element counts for debugging
        System.out.println("Found " + ratingElements.size() + " rating elements.");
        System.out.println("Found " + titleElements.size() + " title elements.");
        System.out.println("Found " + imageElements.size() + " image elements.");
    
        // If no elements were found, log and return
        if (ratingElements.isEmpty() || titleElements.isEmpty() || imageElements.isEmpty()) {
            System.out.println("Some elements were not found. Check your XPaths.");
            return;
        }
    
        // List to store coffee mug details
        List<String[]> coffeeMugs = new ArrayList<>();
    
        // Loop through elements and collect details with retry mechanism
        for (int i = 0; i < ratingElements.size(); i++) {
            try {
                // Retry logic in case of stale elements
                WebElement ratingElement = ratingElements.get(i);
                WebElement titleElement = titleElements.get(i);
                WebElement imageElement = imageElements.get(i);
    
                float rating = Float.parseFloat(ratingElement.getText());
    
                if (rating >= 4.0) {
                    String title = titleElement.getText();
                    String imageUrl = imageElement.getAttribute("src");  // Use 'src' for image URL
                    coffeeMugs.add(new String[]{title, imageUrl, String.valueOf(rating)});
                }
            } catch (StaleElementReferenceException e) {
                // Retry if stale element is encountered
                System.out.println("Stale element encountered at index " + i + ". Retrying...");
                ratingElements = driver.findElements(By.xpath("//div[@class='slAVV4']//div[@class='XQDdHH']"));
                titleElements = driver.findElements(By.xpath("//div[@class='slAVV4']//a[@class='wjcEIp']"));
                imageElements = driver.findElements(By.xpath("//div[@class='slAVV4']//img[@class='DByuf4']"));
                i--; // Retry current index
            } catch (Exception e) {
                System.out.println("Error occurred: " + e.getMessage());
            }
        }
    
        // Sort by rating in descending order using Comparator.comparing
        coffeeMugs.sort(Comparator.comparing(mug -> -Float.parseFloat(mug[2])));
    
        // Log sorted result
        System.out.println("Sorted coffee mugs by rating...");
    
        // Print top 5 items
        for (int i = 0; i < Math.min(5, coffeeMugs.size()); i++) {
            String[] mug = coffeeMugs.get(i);
            System.out.println("Title: " + mug[0]);
            System.out.println("Image URL: " + mug[1]);
            System.out.println("Rating: " + mug[2]);
            System.out.println("----------------------------");
        }
    
        // In case no mugs with 4+ stars were found
        if (coffeeMugs.isEmpty()) {
            System.out.println("No coffee mugs with a rating of 4.0 or higher were found.");
        }
    }
    
    
    
}

