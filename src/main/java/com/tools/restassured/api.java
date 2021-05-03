package com.tools.restassured;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public interface api {
    public String baseURI = null;
    public Map<String, String> params = new HashMap<String,String>();
    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

    public Response executeRequest();
}
