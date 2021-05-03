package test;

import com.ExceptionHandler.WebDriverNotSetException;
import com.params;
import com.util.WeatherHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class weatherTest {
    private static ArrayList<String> citiesToSearch = new ArrayList<String>();

    @BeforeSuite
    public void prePrint() {
        print("Web Execution only valid for 'Mac OS' operating system.\r\nCurrent execution environment operating system found as : " + params.osName);
        print("Supported Chrome Browser version - 90.0.4430.93.");
    }

    @BeforeMethod
    protected void setUp() {
        citiesToSearch.add("Noida");
        System.out.println("Input Data:" + citiesToSearch);
    }

    @Test
    public void testSearchResults() throws WebDriverNotSetException {
        for (String searchValue : citiesToSearch) {
            String expectedTemp = WeatherHelper.getTemperature_api(searchValue); // Get_Expected_Data (API)
            String actualTemp = WeatherHelper.getTemperature_ui(searchValue); // Get_Actual_Data (UI)
            Assert.assertEquals(actualTemp, expectedTemp); //Validations
        }
    }

    @Test
    public void testSearchResultsByVariance() throws WebDriverNotSetException {
        int varianceTolerance = 3;
        for (String searchValue : citiesToSearch) {
            String expectedTemp = WeatherHelper.getTemperature_api(searchValue); // Get_Expected_Data (API)
            String actualTemp = WeatherHelper.getTemperature_ui(searchValue); // Get_Actual_Data (UI)

            Assert.assertTrue(Math.abs(Float.parseFloat(actualTemp) - Float.parseFloat(expectedTemp)) <= varianceTolerance); // Validate temperature diff
        }
    }

    public static void print(String txt) {
        System.out.println(txt);
    }

}
