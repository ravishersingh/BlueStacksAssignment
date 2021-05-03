package com.core.web;

import com.ExceptionHandler.WebDriverNotSetException;
import com.params;
import com.tools.selenium.Drivers;
import com.tools.selenium.Finder;
import com.tools.selenium.ObjectActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class weather {

    private final String baseURL="https://www.weather.com/";

    public void open() throws WebDriverNotSetException {
        Drivers.initDriver("chrome");
        Drivers.GetDriver().get(baseURL);
    }

    public void searchWeather(String searchItem) throws WebDriverNotSetException {
        WebDriverWait wait = new WebDriverWait(Drivers.GetDriver(), params.pageLoadTimeoutSeconds);
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("LocationSearch_input")));
        searchBox.click();
        Finder.findElement(By.id("LocationSearch_input")).sendKeys(searchItem);
        Finder.findElement(By.xpath("//button[starts-with(text(), '"+searchItem+",')]")).click();
    }

    public String readTemperature() throws WebDriverNotSetException {
        return Finder.findElement(By.xpath("(//*[@data-testid='TemperatureValue'])[1]")).getText().replace("°", "");
    }
}
