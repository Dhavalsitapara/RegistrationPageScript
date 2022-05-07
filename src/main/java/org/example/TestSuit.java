package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TestSuit {       //Start from here to write the code

    protected static WebDriver driver; //import selenium web-driver dependency by clicking on 'RED' bulb or (Manualy = In 'POM' file)

    public static void main(String args[]){

        System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver.exe");
        //Open Chrome
        driver= new ChromeDriver();  // import chrome web-Driver dependency (In 'POM' file)

        //Duration to be wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  //if this line 'RED' ,Click on bulb and select first option

        //Maximize the screen
        driver.manage().window().maximize();

        //Open Web page
        driver.get("https://demo.nopcommerce.com/");
        // click on register button
        driver.findElement(By.className("ico-register")).click();

        //Enter First name
        driver.findElement(By.xpath("//input[@name='FirstName']")).sendKeys("Automation");

        //Enter Last name
        driver.findElement(By.id("LastName")).sendKeys("Last name Test");

        //Enter Email ID
        driver.findElement(By.id("Email")).sendKeys("abc098@gmail.com");

        //Enter password
        driver.findElement(By.id("Password")).sendKeys("asd@123");

        //Enter Confirmed Password
        driver.findElement(By.id("ConfirmPassword")).sendKeys("asd@123");

        //Click on Register button
        driver.findElement(By.id("register-button")).click();
    }
}
