package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class TestSuit {

    protected static WebDriver driver; //import selenium web-driver dependency by clicking on 'RED' bulb or (Manualy = In 'POM' file)

    public static void main(String args[]) {

        System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver.exe");
        //Open Chrome
        driver = new ChromeDriver();  // import chrome web-Driver dependency (In 'POM' file)

        //Duration to be wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  //if this line 'RED' ,Click on bulb and select first option

        //Maximize the screen
        driver.manage().window().maximize();

        //Open Web page
        driver.get("https://demo.nopcommerce.com/");

        // click on register button
        //driver.findElement(By.className("ico-register")).click();
        Clickonelement(By.className("ico-register"));

        //Enter First name
        //driver.findElement(By.xpath("//input[@name='FirstName']")).sendKeys("Rajesh");
        Sendvalue(By.xpath("//input[@name='FirstName']"), "Rajesh");

        //Enter Last name
        //driver.findElement(By.id("LastName")).sendKeys("Patel");
        Sendvalue(By.id("LastName"), "Patel");

        //Enter Email and Printout Time
        //driver.findElement(By.id("Email")).sendKeys("abc098@gmail.com");
        Sendvalue(By.id("Email"), "abc" + rendomdate() + "@gmail.com");
        System.out.println(rendomdate());

        //Enter password
        // driver.findElement(By.id("Password")).sendKeys("asd@123");
        Sendvalue(By.id("Password"), "asd@123");

        //Enter Confirmed Password
        //driver.findElement(By.id("ConfirmPassword")).sendKeys("asd@123");
        Sendvalue(By.id("ConfirmPassword"), "asd@123");

        //Click on Register button
        driver.findElement(By.id("register-button")).click();

        //Result verification
        String actual = gettext(By.className("result"));
        String expected = "Your registration completed";

        if (actual.equals(expected)){
            System.out.println("Your suit is pass");
        }else
            System.out.println("your suit is fail");

        //Wait for button clickable
        //Clickonelement(By.xpath("//a[@class='button-1 register-continue-button']"));
        driverWaitsUntil(By.xpath("//a[@class='button-1 register-continue-button']"),5);

        driver.quit();
    }
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>              All Utility Methods          >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //Click on Element
    public static void Clickonelement(By by) {
        driver.findElement(by).click();
    }

    //Send Text Values
    public static void Sendvalue(By by, String value) {
        driver.findElement(by).sendKeys(value);
    }

    //Time stamp
    public static String rendomdate(){
        Date dat = new Date();
        SimpleDateFormat formate = new SimpleDateFormat("ddMMyyyyHHmmss");
        return formate.format(dat);
    }

    //Wait for click-able
    public static void driverWaitsUntil(By by, int time){
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    //wait for url
    public static void driverwaiturl(String url, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.urlToBe(url));
    }

    //Get text
    public static String gettext(By by){
       return driver.findElement(by).getText();
    }

    }