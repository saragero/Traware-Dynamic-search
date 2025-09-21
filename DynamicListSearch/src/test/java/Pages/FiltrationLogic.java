package Pages;

import Initialization.DriverInit;
import io.cucumber.java.AfterAll;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FiltrationLogic extends DriverInit {
//    String filePath = "config/testData.properties";
//    Properties pro = new Properties();
//    FileInputStream fileInputStream = new FileInputStream(System.getProperty(filePath));

    By signIn = By.xpath("//*[@class=\"signin leo-quicklogin\"]");
    By dateFromList= By.xpath("//*[@class=\"datepicker ap_date_from hasDatepicker\"]");
//            By.xpath("//*[@placeholder=\"Date from\"]");
    By dateFromIcon = By.xpath("(//*[@class=\"fa fa-calendar\"])[1]");
    By dateToList= By.xpath("//*[@class='datepicker ap_date_to hasDatepicker']");
    By dateToIcon = By.xpath("(//*[@class=\"fa fa-calendar\"])[2]");
    By datePickerMonth = By.xpath("//span[@class=\"ui-datepicker-month\"]");
    By datePickerRow = By.xpath("//tbody/tr");
    By datePickerDay = By.xpath("//tbody/tr/td");
    By adultsPlacholder = By.xpath("//*[@placeholder=\"Adult\"]");
    By adultsValues = By.xpath("//*[@class=\"number-people adult\"]");
    By childplaceholder = By.xpath("//*[@placeholder=\"Child\"]");
    By childvalues = By.xpath("//*[@class=\"number-people child\"]");
    By searchBtn = By.xpath("//*[@name=\"search_product\"]");
    By searchBookingCard = By.id("ap-booking-search");
    //    public FiltrationLogic() throws FileNotFoundException {
//    }

    public void iOpenTheNIMBIAWebsite() throws InterruptedException, IOException {
//        pro.load(fileInputStream);
//        System.out.println(pro.getProperty("https://demo1.leotheme.com/ap_booking_demo/en/home-2.html"));
        driver.navigate().to("https://demo1.leotheme.com/ap_booking_demo/en/home-2.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println(driver.findElement(signIn).getText());
        Assert.assertEquals("Sign in",driver.findElement(signIn).getText());
    }

    public void validateDefaultValuesForDates(){
        validateDateFromDefaultValue();
        validateDateToDefaultValue();
    }

    public void validateSelectDateFrom(){
        Assert.assertTrue(driver.findElement(dateFromIcon).isDisplayed());
        driver.findElement(dateFromList).click();
        selectDateFrom();
        Assert.assertTrue(driver.findElement(dateFromList).getAttribute("value").contains("22"));

    }

    public void validateSelectDateTo() {
        Assert.assertTrue(driver.findElement(dateToIcon).isDisplayed());
        driver.findElement(dateToList).click();
        selectDateTo();
        Assert.assertTrue(driver.findElement(dateToList).getAttribute("value").contains("24"));
    }

    public void iSelectAdultsOption(){
        String adValue = "2";
        //Validate default value for adults filter
        Assert.assertEquals(adValue, driver.findElement(adultsPlacholder).getAttribute("value"));
        driver.findElement(adultsPlacholder).click();
        List<WebElement> adultValues = driver.findElements(adultsValues);
        for (int i=1; i<adultValues.size();i++){
            if (adultValues.get(i).getText().equals("3")){
                adultValues.get(i).click();
                adValue = adultValues.get(i).getText();
            }
        }
        Assert.assertEquals(adValue ,driver.findElement(adultsPlacholder).getText());

    }
    public void iSelectChildernsOption(){
        String childValue = "0";
        //Validate default value for adults filter
        Assert.assertEquals(childValue, driver.findElement(childplaceholder).getAttribute("value"));
        driver.findElement(childplaceholder).click();
        List<WebElement> childernValues = driver.findElements(childvalues);
        for (int i=1; i<childernValues.size();i++){
            if (childernValues.get(i).getText().equals("3")){
                childernValues.get(i).click();
                childValue = childernValues.get(i).getText();
            }
        }
        Assert.assertEquals(childValue ,driver.findElement(adultsPlacholder).getText());
    }

    public void iClickOnSearchBTNValidateTheSearchResults(){
        driver.findElement(searchBtn).click();
//        Assert.assertFalse(driver.findElement(searchBookingCard).isDisplayed());
        quitDriver();
    }

    public void validateDateToDefaultValue(){
        LocalDate tomorrow = LocalDate.now().plusDays(1); // Gets current date
        System.out.println("Tomorrow's date: " + tomorrow);
        // Format it if needed
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
        String formattedDate = tomorrow.format(formatter);
        System.out.println("Formatted date: " + formattedDate);
        Assert.assertEquals(formattedDate,driver.findElement(dateToList).getAttribute("value"));
    }

    public void validateDateFromDefaultValue(){
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd");
        String dateFrom = today.format(formatter);
        System.out.println("Today's date" + dateFrom);
        System.out.println(driver.findElement(dateFromList).getAttribute("value"));
        Assert.assertEquals(driver.findElement(dateFromList).getAttribute("value"),dateFrom);
    }

    public void selectDateFrom() {
        List<WebElement> dateRows = driver.findElements(datePickerRow);
        List<WebElement> dateCol = driver.findElements(datePickerDay);
        for (int colIndex =1;colIndex<dateCol.size();colIndex++){
            for (int i=1; i< dateRows.size();i++){
                System.out.println(findElementElementByDynamicLocatorAndIndex(i,colIndex).getText());
                if (findElementElementByDynamicLocatorAndIndex(i,colIndex).getText().equals("22")){
                    findElementElementByDynamicLocatorAndIndex(i,colIndex).click();
                    return;
                }
            }
        }
    }

    public void selectDateTo() {
        List<WebElement> dateRows = driver.findElements(datePickerRow);
        List<WebElement> dateCol = driver.findElements(datePickerDay);
        for (int colIndex= 1; colIndex<dateCol.size();colIndex++){
            for (int i=1; i< dateRows.size();i++){
                System.out.println(findElementElementByDynamicLocatorAndIndex(i,colIndex).getText());
                if (findElementElementByDynamicLocatorAndIndex(i,colIndex).getText().equals("24")){
                    findElementElementByDynamicLocatorAndIndex(i,colIndex).click();
                    return;
                }
            }
        }
    }

    public WebElement findElementElementByDynamicLocatorAndIndex(int index, int colIndex) {
        return driver.findElement(By.xpath("//tbody/tr[" + (index) + "]/td[" + (colIndex) + "]"));
    }

}
