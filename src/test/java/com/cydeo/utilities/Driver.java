package com.cydeo.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Driver {
    //create a private constructor to remove access to this object

    private Driver(){}
    /*
    we make the WebDriver private, because we want to close access from outside the class
     */
    private static WebDriver driver; //default value=null


    /*
    Create reusable utility method which will return the same driver instance once we call it
     */
    public static WebDriver getDriver(){

        if(driver==null){
            /*
            We will need our browserType from configuration.properties file
             */
            String browserType=ConfigurationReader.getProperty( "browser" );

            /*
            Depending on the browserType returned from the configuration.properties
            switch statement will determine the "case", and open the matching browser.
             */
            switch (browserType){
                case "chrome":
                   // WebDriverManager.chromedriver().setup();
                    driver=new ChromeDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait( Duration.ofSeconds( 10 ) );
                    break;
                case "firefox":
                   // WebDriverManager.firefoxdriver().setup();
                    driver=new FirefoxDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait( Duration.ofSeconds( 10 ) );

            }
        }

        return driver;
    }


    public static void closeDriver(){
        if (driver!=null){
            /*
            This line will terminate the currently existing driver completely. It will not exist going forward.
             */
            driver.quit();
            /*
            We assign the value back to "null" so that my "singleton" can create a newer one if needed.
             */
            driver = null;
        }
    }
}
