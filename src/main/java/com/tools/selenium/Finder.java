package com.tools.selenium;

import com.ExceptionHandler.WebDriverNotSetException;
import com.params;
import org.openqa.selenium.*;

import java.util.List;

public class Finder {
    public static boolean debug;

    public static WebElement findElement(By args) throws WebDriverNotSetException {
        long startTime = System.currentTimeMillis(); // Start_Timer
        WebElement tempObject = null;

        while (((System.currentTimeMillis() - startTime) / 1000) < params.maxFinderTimeOut) {
            try {
                tempObject = Drivers.GetDriver().findElement(args);
                break;
            } catch (NoSuchElementException e) {
                continue;
            } catch (StaleElementReferenceException e) {
                continue;
            }
        }
        if(debug) {
            long elapsedTime = (System.currentTimeMillis() - startTime) / 1000; // Stop_Timer
            System.out.println("Time taken to find Object:" + elapsedTime + "seconds");
        }
        if (tempObject == null)
            throw new NoSuchElementException(
                    "[TimeOut-" + params.maxFinderTimeOut + "] Object Not Found using: " + args);
        else
            return tempObject;

    }

    public static WebDriver findWebDriver() throws WebDriverNotSetException {
        return Drivers.GetDriver();
    }

    public static List<WebElement> findElements(By args) throws WebDriverNotSetException {
        return Drivers.GetDriver().findElements(args);
    }

}
