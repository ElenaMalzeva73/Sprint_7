package courier;

import io.restassured.response.Response;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import static courier.CourierCreds.credsFrom;
import static courier.CourierGenerator.*;
import static org.junit.Assert.*;
import org.apache.http.HttpStatus;

public class CreatingCourierTest extends  BaseTest{
    @Test
    @DisplayName("Создание курьера")
    public void createCourierResponse201Created() {
        CreatingCourier creatingCourier = new CreatingCourier();
       Courier courier = randomCourier();
        Response response = creatingCourier.create(courier);
        assertEquals("ok",HttpStatus.SC_CREATED,response.statusCode());
        Response responseLogin = creatingCourier.courierLogin(credsFrom(courier));
        assertEquals("id",HttpStatus.SC_OK,responseLogin.statusCode());
    }

    @Test
    @DisplayName("Создание курьера без логина")
    public void createCourierResponse400BadRequestNoLogin() {
        CreatingCourier creatingCourier = new CreatingCourier();
        Courier courier = randomCourierNoLogin();
        Response response = creatingCourier.create(courier);
        assertEquals(HttpStatus.SC_BAD_REQUEST,response.statusCode());
    }
    @Test
    @DisplayName("Создание курьера без пароля")
    public void createCourierResponse400BadRequestNoPassword() {
        CreatingCourier creatingCourier = new CreatingCourier();
        Courier courier = randomCourierNoPassword();
        Response response = creatingCourier.create(courier);
        assertEquals(HttpStatus.SC_BAD_REQUEST, response.statusCode());
    }
    @Test
    @DisplayName("Создание курьера без логина и пароля")
    public void createCourierResponse400BadRequestNoPasswordNoLogin() {
        CreatingCourier creatingCourier = new CreatingCourier();
        Courier courier = randomCourierNoPasswordNoLogin();
        Response response = creatingCourier.create(courier);
        assertEquals(HttpStatus.SC_BAD_REQUEST, response.statusCode());
    }
    //Баг ожидаемый результат код 409, фактический код 201
    @Test
    @DisplayName("Создание курьера с ранее зарегистрированным логина")
    public void createCourierResponse409ConflictRecurringLogin() {
        CreatingCourier creatingCourier = new CreatingCourier();
        Courier courier = randomCourier();
        Response response = creatingCourier.create(courier);
        creatingCourier.create(courier);
        assertEquals(HttpStatus.SC_CONFLICT,response.statusCode());
    }
}