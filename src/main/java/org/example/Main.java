package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://twitter.com");
        driver.manage().window().maximize();
        String[] names={"Virat","modi","Elon"};

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-testid='loginButton']"))).isDisplayed();
        // Click Sign-in
        WebElement signInField  = driver.findElement(By.xpath("//a[@data-testid='loginButton']"));
        signInField.click();

        // Type Email
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@autocomplete='username']")));
        driver.findElement((By.xpath("//input[@autocomplete='username']"))).sendKeys("pongping629@gmail.com");

        // Click Next
        driver.findElement(By.xpath("//div[@class='css-175oi2r r-sdzlij r-1phboty r-rs99b7 r-lrvibr r-ywje51 r-usiww2 r-13qz1uu r-2yi16 r-1qi8awa r-ymttw5 r-1loqt21 r-o7ynqc r-6416eg r-1ny4l3l']")).click();
        Thread.sleep(2000);
        if(driver.findElement(By.xpath("//input[@data-testid='ocfEnterTextTextInput']")).isDisplayed()) {
            driver.findElement(By.cssSelector("input[data-testid='ocfEnterTextTextInput']")).click();
            driver.findElement(By.cssSelector("input[data-testid='ocfEnterTextTextInput']")).sendKeys("pongping629");
            driver.findElement(By.xpath("//div[@data-testid='ocfEnterTextNextButton']")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.click();
            passwordField.sendKeys("qwerty$12");
            driver.findElement(By.xpath("//div[@data-testid='LoginForm_Login_Button']")).click();
        }

        else
        {
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.click();
            passwordField.sendKeys("qwerty$12");
            driver.findElement(By.xpath("//div[@data-testid='LoginForm_Login_Button']")).click();
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-testid='AppTabBar_Explore_Link']")));
        driver.findElement(By.xpath("//a[@data-testid='AppTabBar_Explore_Link']")).click();
        for(String i:names)
        {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@data-testid='SearchBox_Search_Input']")));
            driver.findElement(By.xpath("//input[@data-testid='SearchBox_Search_Input']")).click();
            driver.findElement(By.xpath("//input[@data-testid='SearchBox_Search_Input']")).sendKeys(Keys.chord(Keys.COMMAND,"a"));
            //driver.findElement(By.xpath("//input[@data-testid='SearchBox_Search_Input']")).sendKeys(Keys.CLEAR);
            //driver.findElement(By.xpath("//input[@data-testid='SearchBox_Search_Input']")).clear();
            driver.findElement(By.xpath("//input[@data-testid='SearchBox_Search_Input']")).sendKeys(" ");
            driver.findElement(By.xpath("//input[@data-testid='SearchBox_Search_Input']")).sendKeys(i);
            driver.findElement(By.xpath("//input[@data-testid='SearchBox_Search_Input']")).sendKeys(Keys.ENTER);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='People']")));
            driver.findElement(By.xpath("//span[text()='People']")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@data-testid='UserCell'])[1]")));
            Thread.sleep(2000);
            driver.findElement(By.xpath("(//div[@data-testid='UserCell'])[1]")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='css-175oi2r r-1awozwy r-18u37iz r-1wbh5a2'])[2]")));
            Thread.sleep(2000);
            String username = driver.findElement(By.xpath("(//div[@class='css-175oi2r r-1awozwy r-18u37iz r-1wbh5a2'])[2]")).getText();
            System.out.println(username);
            Thread.sleep(2000);
            String followers_count = driver.findElement(By.xpath("(//a[@class='css-1rynq56 r-bcqeeo r-qvutc0 r-37j5jr r-a023e6 r-rjixqe r-16dba41 r-1loqt21'])[3]")).getText();
            System.out.println(followers_count);
            Thread.sleep(2000);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-testid='tweetText']")));
            Thread.sleep(2000);
            String post = driver.findElement(By.xpath("//div[@data-testid='tweetText']")).getText();
            System.out.println(post);
            Thread.sleep(2000);
            driver.navigate().back();
        }
driver.close();

    }
}