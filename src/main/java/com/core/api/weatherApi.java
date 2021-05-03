package com.core.api;

import com.tools.restassured.api;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class weatherApi implements api {
    public final String baseURI;
    private static final String apiKey = "753fe6de06e26989a92748da91fcfb75";
    public Map<String, String> params = new HashMap<String,String>();
    private RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

    public weatherApi(){
        this.baseURI="http://api.openweathermap.org/data/2.5/weather";
        params.put("q","");
        params.put("units","metric");
        params.put("appid",apiKey);
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.setBaseUri(baseURI);
    }

    public weatherApi setParam(String param, String value) {
        params.replace(param, value);
        return this;
    }

    @Override
    public Response executeRequest() {
        requestSpecBuilder.addQueryParams(params);
        Response response = given().spec(requestSpecBuilder.build()).log().uri().get();
        System.out.println("\r\nResponse :");
        response.then().log().all();
        return response;
    }
}
