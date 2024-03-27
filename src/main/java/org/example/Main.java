package org.example;

import com.opencsv.CSVWriter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        // Initialize WebDriver and WebDriverWait
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Navigate to Twitter homepage
        driver.get("https://twitter.com");
        driver.manage().window().maximize();

        // Array of usernames to search for
        String[] names = {"Virat", "Bill", "Elon", "max verstappen", "sundar", "justin trudeau", "IRCC", "Rihanna", "Donald", "taylor"};

        // Initialize CSV writer to write data to CSV file
        FileWriter outputFile = new FileWriter("twitter_data.csv");
        CSVWriter writer = new CSVWriter(outputFile);

        // Write header to CSV file
        String[] header = {"username", "Followers", "Posts"};
        writer.writeNext(header);

        // Wait for Sign-in button to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-testid='loginButton']"))).isDisplayed();

        // Click Sign-in
        WebElement signInField = driver.findElement(By.xpath("//a[@data-testid='loginButton']"));
        signInField.click();

        // Type Email
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@autocomplete='username']")));
        driver.findElement((By.xpath("//input[@autocomplete='username']"))).sendKeys("pongping629@gmail.com");

        // Click Next
        driver.findElement(By.xpath("//div[@class='css-175oi2r r-sdzlij r-1phboty r-rs99b7 r-lrvibr r-ywje51 r-usiww2 r-13qz1uu r-2yi16 r-1qi8awa r-ymttw5 r-1loqt21 r-o7ynqc r-6416eg r-1ny4l3l']")).click();
        Thread.sleep(2000);

        // Handling login based on displayed fields
        if (driver.findElement(By.xpath("//input[@data-testid='ocfEnterTextTextInput']")).isDisplayed()) {
            // Entering username
            driver.findElement(By.cssSelector("input[data-testid='ocfEnterTextTextInput']")).click();
            driver.findElement(By.cssSelector("input[data-testid='ocfEnterTextTextInput']")).sendKeys("pongping629");
            // Clicking Next
            driver.findElement(By.xpath("//div[@data-testid='ocfEnterTextNextButton']")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
            // Entering password
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.click();
            passwordField.sendKeys("qwerty$12");
            // Clicking login button
            driver.findElement(By.xpath("//div[@data-testid='LoginForm_Login_Button']")).click();
        } else if (driver.findElement(By.xpath("//input[@autocomplete='current-password']")).isDisplayed()) {
            // Entering password directly if password field is displayed
            wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.click();
            passwordField.sendKeys("qwerty$12");
            // Clicking login button
            driver.findElement(By.xpath("//div[@data-testid='LoginForm_Login_Button']")).click();
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-testid='AppTabBar_Explore_Link']")));
        // Clicking on Explore tab
        driver.findElement(By.xpath("//a[@data-testid='AppTabBar_Explore_Link']")).click();

        String post="";
        String username="";
        String followers="";

        // Iterate through each username in the array
        for (String i : names)
        {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@data-testid='SearchBox_Search_Input']")));
            driver.findElement(By.xpath("//input[@data-testid='SearchBox_Search_Input']")).click();
            driver.findElement(By.xpath("//input[@data-testid='SearchBox_Search_Input']")).sendKeys(Keys.chord(Keys.COMMAND, "a"));
            driver.findElement(By.xpath("//input[@data-testid='SearchBox_Search_Input']")).sendKeys(" ");
            driver.findElement(By.xpath("//input[@data-testid='SearchBox_Search_Input']")).sendKeys(i);
            driver.findElement(By.xpath("//input[@data-testid='SearchBox_Search_Input']")).sendKeys(Keys.ENTER);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='People']")));
            driver.findElement(By.xpath("//span[text()='People']")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@data-testid='UserCell'])[1]")));

            // Click on the first user
            // Fetch username and followers count
            Thread.sleep(2000);
            driver.findElement(By.xpath("(//div[@data-testid='UserCell'])[1]")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='css-175oi2r r-1awozwy r-18u37iz r-1wbh5a2'])[2]")));
            Thread.sleep(2000);
            System.out.println("fetching username.....");
            username = driver.findElement(By.xpath("(//div[@class='css-175oi2r r-1awozwy r-18u37iz r-1wbh5a2'])[2]")).getText();
            System.out.println(username);
            Thread.sleep(2000);
            System.out.println("fetching followers count.....");
            followers = driver.findElement(By.xpath("(//a[@class='css-1rynq56 r-bcqeeo r-qvutc0 r-37j5jr r-a023e6 r-rjixqe r-16dba41 r-1loqt21'])[3]")).getText();
            System.out.println(followers);

            // Fetch posts
            Thread.sleep(2000);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-testid='tweetText']")));
            Thread.sleep(2000);
            List<WebElement> postCount = driver.findElements(By.xpath("//div[@data-testid='tweetText']"));
            System.out.println("fetching fetching posts.....");

            // Write data to CSV
            for (int j = 1; j <= ((postCount.size()) - 1); j++){
                post = driver.findElements(By.xpath("//div[@data-testid='tweetText']")).get(j).getText();
                System.out.println(post);
                String[] twitterData = {username, followers, post};
                Thread.sleep(4000);
            writer.writeNext(twitterData);
                Thread.sleep(2000);
            }
            // Navigate back to search results
            driver.navigate().back();
        }
        // Close WebDriver and print completion message
        driver.close();
        System.out.println("Scrap complete");
    }
}