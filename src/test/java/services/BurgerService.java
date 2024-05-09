package services;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.*;
import static io.restassured.RestAssured.given;

public class BurgerService {
    
    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site";

    private static final String CREATE_USER_URL = "/api/auth/register";

    private static final String DELETE_USER_URL = "api/auth/user";
    
    @Step("Create user via POST /api/auth/register")
    public String createUser(User user) {
        Response createResponse = given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(CREATE_USER_URL);
                
        
        return createResponse.then().extract().jsonPath().getString("accessToken");
    }

    @Step("Delete user via DELETE api/auth/user")
    public Response deleteUser(String token) {
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .when()
                .delete(DELETE_USER_URL);
    }

}