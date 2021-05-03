package com.tools.selenium;

import com.ExceptionHandler.InvalidArgumentsException;
import com.ExceptionHandler.WebDriverNotSetException;
import com.params;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Drivers {
    private static WebDriver Driver = null; // WebDriver
    private static JavascriptExecutor JSExe = null;
    private static Actions ActionBuilder = null;
    @SuppressWarnings("unused")
    private DesiredCapabilities D_Caps = null; // Caps for Driver

    Drivers(WebDriver driver) {
        SetDriver(driver);
    }

    public static boolean SetDriver(WebDriver driver) {
        if (driver == null)
            throw new InvalidArgumentsException("Cannot set 'NULL' value on WebDriver.");
        try {
            Driver = driver;
            JSExe = (JavascriptExecutor) Driver;
            ActionBuilder = new Actions(Driver);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static WebDriver GetDriver() throws WebDriverNotSetException {
        if (Driver == null)
            throw new WebDriverNotSetException("WebDriver Tool not set ! Please set or initialize WebDriver");
        return Driver;
    }

    public static JavascriptExecutor GetJavascriptExecutor() throws WebDriverNotSetException {
        if (JSExe == null)
            JSExe = (JavascriptExecutor) GetDriver();
        return JSExe;
    }

    private static boolean PostDriverInitActions(WebDriver driver) {
        try {
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().pageLoadTimeout(params.pageLoadTimeout, TimeUnit.MILLISECONDS);
            driver.manage().timeouts().implicitlyWait(params.driverImplicitWait, TimeUnit.MILLISECONDS);
            driver.manage().window().maximize();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    public static WebDriver initDriver(Browser br) {
        return initDriver(br.getBrowserName());
    }

    public static WebDriver initDriver(String BrowserName) {
        BrowserName = BrowserName.toLowerCase();
        if (BrowserName.contains("ie") || (BrowserName.contains("internet") && BrowserName.contains("iexplore"))) {
            System.setProperty("webdriver.ie.driver", "src/main/resources/driver/IEDriverServer");
            Driver = new InternetExplorerDriver();
        } else if (BrowserName.contains("firefox")) {
            Driver = new FirefoxDriver();
        } else if (BrowserName.contains("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver");
            Driver = new ChromeDriver();
        }
        PostDriverInitActions(Driver);
        return Driver;
    }

    public enum Browser{
        CHROME("chrome"),IE("ie"),SAFARI("safari");
        Browser(String brName) { this.brName = brName; }
        private String brName;
        String getBrowserName(){
            return brName;
        }
    }

    public static void quit() throws WebDriverNotSetException {
        GetDriver().quit();
    }

}
