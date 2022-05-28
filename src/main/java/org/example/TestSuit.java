package org.example;
//-------------------------------------------------------------------
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
//--------------------------------------------------------------------------------
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Currency;
import java.util.Date;
import java.util.regex.Pattern;

public class TestSuit {

    protected static WebDriver driver; //import selenium web-driver dependency by clicking on 'RED' bulb or (Manualy = In 'POM' file)


    @BeforeMethod
        public void startOfBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver.exe");
        //Open Chrome
        driver = new ChromeDriver();  // import chrome web-Driver dependency (In 'POM' file)

        //Duration to be wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  //if this line 'RED' ,Click on bulb and select first option

        //Maximize the screen
        driver.manage().window().maximize();

        //Open Web page
        driver.get("https://demo.nopcommerce.com/");
    }

    @AfterMethod
    public void close()
    {
        driver.quit();
    }


    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@   Test Case    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    @Test (priority = 0)
    public void userShouldAbleToChangeCurrency(){
       //Verification at binning for default Currency
        char ActualDefualt = gettext(By.xpath("//div[@data-productid=\"1\"]//child::span[contains(text(),\"$\")]")).charAt(0);
        char ExpectedDefault= '$';
        Assert.assertEquals(ActualDefualt,ExpectedDefault,"Default $ is not selected");

        //Select Euro Currency from drop-down
        Select currency = new Select(driver.findElement(By.id("customerCurrency")));
        currency.selectByIndex(1);

        //Verification for Euro currency change
        char Expected='€';
        char ActualPrice = gettext(By.xpath("//div[@data-productid=\"1\"]//child::span[contains(text(),\"€\")]")).charAt(0);
        Assert.assertEquals(ActualPrice,Expected, "Currency is WRONG");
    }

    @Test (priority = 2)
        public void userShouldBeAbleToRegisterSuccsesfully() {

        // click on register button
        driver.findElement(By.className("ico-register")).click();
        //driverClickOnElement(By.className("ico-register"));

        //Select Male Radio button----------------------------------------------------------------Radio button
        driver.findElement(By.id("gender-male")).click();

        //Enter First name
        //driver.findElement(By.xpath("//input[@name='FirstName']")).sendKeys("Rajesh");
        driverSendValue(By.xpath("//input[@name='FirstName']"), "Rajesh");

        //Enter Last name
        //driver.findElement(By.id("LastName")).sendKeys("Patel");
        driverSendValue(By.id("LastName"), "Patel");

        //Birth Day-----------------------------------------------------------------------------Birthdate & All
        Select selectDay = new Select(driver.findElement(By.name("DateOfBirthDay")));
        selectDay.selectByIndex(9);
        //Month
        Select selectMonth = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        selectMonth.selectByValue("12");
        //Year
        Select selectYear = new Select(driver.findElement(By.name("DateOfBirthYear")));
        selectYear.selectByVisibleText("1996");

        //Enter Email and Printout Time
        //driver.findElement(By.id("Email")).sendKeys("abc098@gmail.com");
        driverSendValue(By.id("Email"), "abc" + rendomdate() + "@gmail.com");
        System.out.println(rendomdate());

        //Enter password
        // driver.findElement(By.id("Password")).sendKeys("asd@123");
        driverSendValue(By.id("Password"), "asd@123");

        //Enter Confirmed Password
        //driver.findElement(By.id("ConfirmPassword")).sendKeys("asd@123");
        driverSendValue(By.id("ConfirmPassword"), "asd@123");

        //Click on Register button
        driver.findElement(By.id("register-button")).click();


            //Result verification for Registration
            String actual = gettext(By.className("result"));
            String expected = "Your registration completed";
            Assert.assertEquals(actual, expected, "Registration is not success full");
        }

        @Test (priority = 1)
        public void userShouldBeAbleToAddProductInTheCart(){

        //Click on Computer Link
            driverClickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Computers')]"));

        //Click on Desktop category
            driverClickOnElement(By.xpath("//li[@class='inactive']//a[contains(text(),\"Desktops\")]"));

        //Click on Add to Cart
            driverClickOnElement(By.xpath("//div[@data-productid='1']//child::button[contains(text(),\"Add to cart\")]"));

        //Select Processor
            Select select1 = new Select(driver.findElement(By.id("product_attribute_1")));
            select1.selectByIndex(1);

        //Select RAM
            Select select2 = new Select(driver.findElement(By.id("product_attribute_2")));
            select2.selectByIndex(1);

        //Click on HDD (320 gb)
            driverClickOnElement(By.xpath("//label[contains(text(),\"320 GB\")]"));

        //Click on OS (Vista Premium [+$60.00])
            driverClickOnElement(By.id("product_attribute_4_9"));

        //Select Software
            driverClickOnElement(By.id("product_attribute_5_11"));
            driverClickOnElement(By.id("product_attribute_5_12"));

        //Click on Add to cart button
            driverClickOnElement(By.xpath("//div[@class=\"add-to-cart\"]//child::button"));

        //Click on Shopping Cart
            driverClickOnElement(By.xpath("//span[@class=\"cart-label\"]"));

        //assert point
            //Page Checking
            String title = titleOfPage();
            String expectedTitle="nopCommerce demo store. Shopping Cart";
            Assert.assertEquals(title,expectedTitle,"Page Title is not Matching");

            //Product Verification
           String actual = gettext(By.xpath("//a[@href=\"/build-your-own-computer\" and @class=\"product-name\"]")) ;
           String expected = "Build your own computer";
           Assert.assertEquals(actual,expected,"Product name is NOT matching");
        }
    @Test
    public void userShoulBeAbleEmailAFriendAfterRegistration(){

        userShouldBeAbleToRegisterSuccsesfully(); //Register Method

        //Click on Continue button
        driverClickOnElement(By.xpath("//a[contains(text(),\"Continue\")]"));

        //Click on Product
        driverClickOnElement(By.xpath("//a[contains(text(),\"own computer\")]"));

        //Click on Email a Friend
        driverClickOnElement(By.xpath("//button[contains(text(),\"Email a friend\")]"));

        //Enter Friend's Email address
        driverSendValue(By.id("FriendEmail"),"asd"+rendomdate()+"@gmail.com");

        //Enter personal notes
        driverSendValue(By.id("PersonalMessage"),"This item is good to buy");

        //Click on Send button
        driverClickOnElement(By.xpath("//button[contains(text(),\"Send email\")]"));

        //Result Verification
        String expected = "Your message has been sent.";
        String actual = gettext(By.className("result"));

        Assert.assertEquals(actual,expected,"Sending Email to Friend was FAILED");
    }

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>              All Utility Methods          >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //Click on Element
    public static void driverClickOnElement(By by)
    {
        driver.findElement(by).click();
    }

    //Send Text Values
    public static void driverSendValue(By by, String value)
    {
        driver.findElement(by).sendKeys(value);
    }

    //Time stamp
    public static String rendomdate()
    {
        Date dat = new Date();
        SimpleDateFormat formate = new SimpleDateFormat("ddMMyyyyHHmmss");
        return formate.format(dat);
    }

    //Wait for element to be click-able
    public static void WaitUntilClickableByWebElement(By by, int time)
    {
        WebElement element=driver.findElement(by);
        element.findElement(by).click();

        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeClickable(element));

    }

    //Wait for element to be click-able
    public static void driverWaitsUntil(By by, int time)
    {
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    //wait for url
    public static void driverWaitUrl(String url, int time)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.urlToBe(url));
    }

    //Get text
    public static String gettext(By by)
    {
       return driver.findElement(by).getText();
    }

    public static String titleOfPage(){
       return driver.getTitle();
    }

    //Wait until Presence of Element (All)
    public static void driverPresenceofElement(By by, int time)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    //wait until presence of Element Located (One only)
    public static void driverPresenceOfElement(By by,int time)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    //URL Fraction or Contains
    public static void  driverURLContains(String URLContains,int time)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.urlContains(URLContains));
    }

    //wait until Element title contains
    public static void DriverElementTitleContains (String title,int time)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.titleContains(title));
    }

    //Wait until Invisibility of WebElement
    public static void driverInvisibilityOfElement(WebElement element,int time)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    //Wait until alert is Present >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Quation<<<<<<<<<<<<<<<<<<<<<<<<<
    public static void driverAlertsPresent (int time)
    {
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    //Wait until Attribute ToBe  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Quation<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    public static void driverAttributeToBe(By by, String attribute,String value,int time)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.attributeToBe(by,attribute,value));
    }

    //Wait until Attribute not Empty (WebElement & Attribute)
    public static void driverAttributeNotEmpty(WebElement webelement,String attribute, int time)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.attributeToBeNotEmpty(webelement,attribute));
    }

    //Wait until Element to be Selected (Locator)
    public static void driverElementToBeSelected(By by,int time)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeSelected(by));
    }

    //Wait until TextToBe
    public static void driverTextToBe(By by,String value,int time)
    {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.textToBe(by,value));
    }

    //Wait until Text tobe Match<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Quation>>>>>>>>>>>>>>>>>>>
    public static void driverTextTobeMatch(By by, Pattern pattern , int time)
    {
        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.textMatches(by,pattern));
    }

    //DOM Attribute tobe <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Quation>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public static void driverDomAttributesToBe(WebElement element,String attribute, String value,int time)
    {
        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.domAttributeToBe(element,attribute,value));
    }

}