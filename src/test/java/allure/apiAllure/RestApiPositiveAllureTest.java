package allure.apiAllure;

import allure.LogListener;
import allure.apiAllure.models.*;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.testng.AssertJUnit.assertEquals;

@Listeners(LogListener.class)
public class RestApiPositiveAllureTest {

    @BeforeClass
    public void start() {
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
    }

    @Test
    public void getAllEmployeesTest() {
        given()
                .get("/employees")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("success"))
                .body("data.id", hasItems("1", "2", "3"));
                //.body("data.id", instanceOf(String.class));
    }

    @Test
    public void getAllEmployeeByIdTest() {
        EmployeeAllure expectedEmployee = new EmployeeAllure(
                "Tiger Nixon",
                320800,
                61,
                ""
        );
        EmployeeResponseAllure expectedResponse = new EmployeeResponseAllure("success", expectedEmployee, "Successfully! Record has been fetched.");
        EmployeeResponseAllure employeeResponse = given()
                .get("/employee/1")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponseAllure.class);
        assertEquals(employeeResponse, expectedResponse);
    }

    @Test
    public void postEmployeeTest() {
        PostEmployeeModelAllure postEmployeeModel = new PostEmployeeModelAllure("qweqwe", "123123", "21");
        EmployeeResponseAllure expectedResponse = new EmployeeResponseAllure("success", new EmployeeAllure(), "Successfully! Record has been added.");
        EmployeeResponseAllure employeeResponse = given()
                .with()
                .body(postEmployeeModel)
                .post("/create")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponseAllure.class);
        assertEquals(employeeResponse, expectedResponse);
    }

    @Test
    public void putAllEmployeeByIdTest() {
        PutEmployeeModelAllure putEmployeeModel = new PutEmployeeModelAllure(5000, 88);
        EmployeeAllure expectedEmployee = new EmployeeAllure(
                "Jenette Caldwell",
                5000,
                88,
                ""
        );
        EmployeeResponseAllure expectedResponse = new EmployeeResponseAllure("success", new EmployeeAllure(), "Successfully! Record has been updated.");
        EmployeeResponseAllure employeeResponse = given()
                .with()
                .body(putEmployeeModel)
                .put("/update/21")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponseAllure.class);
        assertEquals(employeeResponse, expectedResponse);

        given()
                .get("/employees")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body("id", Matchers.is(21))
                .body("salary", Matchers.is(5000))
                .body("age", Matchers.is(88));

        // OR:
        EmployeeResponseAllure expectedResponseGetEmployee = new EmployeeResponseAllure("success", expectedEmployee, "Successfully! Record has been fetched.");
        EmployeeResponseAllure employeeResponseGetEmployee = given()
                .get("/employee/21")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponseAllure.class);
        assertEquals(employeeResponseGetEmployee, expectedResponseGetEmployee);
    }

    @Test
    public void deleteEmployeeTest() {
        EmployeeResponseAllure expectedResponse = new EmployeeResponseAllure("success", "successfully! deleted Records");
        EmployeeResponseAllure employeeResponse = given()
                .delete("/delete/2")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponseAllure.class);
        assertEquals(employeeResponse, expectedResponse);
    }

}
