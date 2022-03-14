package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CssSelectorDemo {
   
@Test(enabled = false)
public void loginMethod() throws InterruptedException{
    ChromeOptions chromeOptions = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        //Thread.sleep(3000);
        WebElement menu = driver.findElement(By.cssSelector("#react-burger-menu-btn"));
       Actions action=new Actions(driver);
       action.moveToElement(menu).click().build().perform();
       Thread.sleep(3000);
       String logout=driver.findElement(By.cssSelector("#logout_sidebar_link")).getText();
       System.out.println("captured "+logout);
       driver.findElement(By.cssSelector("#logout_sidebar_link")).click();

       Assert.assertEquals(logout, "LOGOUT");
      

        driver.close();
}



@Test
public void maxPriceTag() throws InterruptedException{
  ChromeOptions chromeOptions = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);

        //
        List<WebElement> priceTags = driver.findElements(By.cssSelector(".inventory_item_price"));
        System.out.println("size of price WebElement "+priceTags.size());
        String maxprice=String.valueOf(getmaxPrice(priceTags));
        WebElement maxWB = getMaxPriceWebElement(priceTags, maxprice);
        String getMaxPriceWebElement=maxWB.getText();
        System.out.println("getMaxPriceWebElement: "+getMaxPriceWebElement);

        String xpath="//div[normalize-space()='"+getMaxPriceWebElement+"']/following-sibling::button";
        System.out.println("xpath is "+xpath);
        //div[normalize-space()='$49.99']/following-sibling::button[text()='Add to cart']
        driver.findElement(By.xpath(xpath)).click(); 
        Thread.sleep(2000);
        System.out.println("remove button shows "+ driver.findElement(By.xpath(xpath)).getText());
        String noItemsInBasket=driver.findElement(By.xpath("//a[@class='shopping_cart_link']/child::span")).getText();
        System.out.println("basket is "+noItemsInBasket);

        driver.close();


}

public float getmaxPrice(List<WebElement> priceTags){
  List<Float> Dprice=new ArrayList<Float>();
  for ( WebElement price : priceTags) {
    float newPrice = Float.parseFloat(price.getText().replace("$", ""));
   Dprice.add(newPrice);
    
  }
  return Collections.max(Dprice);

}


public WebElement getMaxPriceWebElement(List<WebElement> priceTags,String price){
  WebElement maxWebElement=null;
  for (WebElement webElement : priceTags) {
    if(webElement.getText().contains(price)){
      maxWebElement=webElement;

    }   
  }
  return maxWebElement;
    
}

}
