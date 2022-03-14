package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoMavenTest {

@Test(enabled=false)
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.yatra.com/");

        Thread.sleep(3000);

        driver.close();
    }


    @Test
    public void Test1(){
        System.out.println("Test TestNG class");
        Assert.assertEquals("Test","Test");
    }

    @Test
    public void Test2(){
        System.out.println("Test TestNG class");
        Assert.assertEquals("Test2","Test2");
    }

    /*@Test
    public void Test3(){
        throw new SkipException("This is not ready to Test");
    }*/
}
