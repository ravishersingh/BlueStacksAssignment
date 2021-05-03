package com.util;

import com.ExceptionHandler.WebDriverNotSetException;
import com.core.api.weatherApi;
import com.core.web.weather;
import com.tools.selenium.Drivers;
import io.restassured.response.Response;

public class WeatherHelper {

    public static String getTemperature_api(String cityName){
        weatherApi api = new weatherApi();
        api.setParam("q", cityName);
        Response response = api.executeRequest();
        return response.jsonPath().getMap("main").get("temp").toString();
    }

    public static String getTemperature_ui(String cityName) throws WebDriverNotSetException {
        try {
            weather w = new weather();
            w.open();
            w.searchWeather(cityName);
            return w.readTemperature();
        } finally {
            Drivers.quit();
        }
    }

}
