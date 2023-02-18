package com.cydeo.utilities;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class BrowserUtils {
    //This method will accept int seconds and execute Thread.sleep method

    public static void sleep(int second){
        second*=1000;
        try{
            Thread.sleep( second );
        }catch (InterruptedException e){

        }

    }
    public static void switchWindowAndVerify(String expectedInUrl, String expectedInTitle){

        //Return and store all window handles in a Set.
        Set<String> allWindowHandles=Driver.getDriver().getWindowHandles();

        for (String each:allWindowHandles){
            Driver.getDriver().switchTo().window( each );
            System.out.println( "Current URL = " + Driver.getDriver().getCurrentUrl() );

            if(Driver.getDriver().getCurrentUrl().contains( expectedInUrl )){
                break;
            }

        }

        //Assert : Verify Title contains "Etsy"
        String actualTitle=Driver.getDriver().getTitle();

        Assert.assertTrue( actualTitle.contains( expectedInTitle ));

    }


    public static void verifyTitle(String expectedTitle){
        Assert.assertEquals(Driver.getDriver().getTitle(), expectedTitle);
    }

    public static void verifyTitleContains( String expectedTitle){

        Assert.assertTrue( Driver.getDriver().getTitle().contains(expectedTitle) );

    }


    public static void waitForInvisibilityOfGivenElement(WebElement target){
        WebDriverWait wait=new WebDriverWait( Driver.getDriver(), Duration.ofSeconds( 10 ) );
        //use the 'wait' object with the proper syntax to create explicit wait conditions
        wait.until( ExpectedConditions.invisibilityOf(target) );
    }


    public static void waitForTitleContains(String title){
        WebDriverWait wait=new WebDriverWait( Driver.getDriver(), Duration.ofSeconds( 10 ) );
        //use the 'wait' object with the proper syntax to create explicit wait conditions
        wait.until( ExpectedConditions.titleContains(title) );
    }

}
