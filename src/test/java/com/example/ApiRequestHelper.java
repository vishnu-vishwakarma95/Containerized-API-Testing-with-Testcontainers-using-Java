package com.example;

import  io.restassured.RestAssured;
import  io.restassured.response.Response;

public class ApiRequestHelper {

    public static Response sendGetRequest(String url) {
        return RestAssured.given().when().get(url);
    }

    public static Response sendPostRequest(String url, Object body) {
        return RestAssured.given()
                .contentType("application/json")
                .body(body)
                .when()
                .post(url);
    }

    public static Response sendPutRequest(String url, Object body) {
        return RestAssured.given()
                .contentType("application/json")
                .body(body)
                .when()
                .put(url);
    }

    public static Response sendDeleteRequest(String url) {
        return RestAssured.given().when().delete(url);
    }
}
