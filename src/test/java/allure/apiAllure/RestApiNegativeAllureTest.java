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
public class RestApiNegativeAllureTest {

    @BeforeClass
    public void start() {
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
    }

    @Test
    public void getAllEmployeesNegativeTest() {
        given()
                .get("/employees")
                .then()
                .log().all()
                .statusCode(200)
                .assertThat()
                .body("status", equalTo("success"))
                .body("data.id", hasItems("50"));
        //.body("data.id", instanceOf(String.class));
    }

    @Test
    public void getAllEmployeeByIdNegativeTest() {
        EmployeeAllure expectedEmployee = new EmployeeAllure(
                "Tiger Nixon",
                320800,
                61,
                ""
        );
        EmployeeResponseAllure expectedResponse = new EmployeeResponseAllure("success", expectedEmployee, "Successfully! Record has been fetched.");
        EmployeeResponseAllure employeeResponse = given()
                .get("/employee/11")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponseAllure.class);
        assertEquals(employeeResponse, expectedResponse);
    }

    @Test
    public void postEmployeeNegativeTest() {
        PostEmployeeModelAllure postEmployeeModel = new PostEmployeeModelAllure(15, "qweqwe", "123123", "21");
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
    public void putAllEmployeeByIdNegativeTest() {
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
                .put("/update/50")
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
    public void deleteEmployeeNegativeTest() {
        EmployeeResponseAllure expectedResponse = new EmployeeResponseAllure("success", "successfully! deleted Records");
        EmployeeResponseAllure employeeResponse = given()
                .delete("/delete/2")
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
                .body("data.employee_name", hasItems("Garrett Winters"));
    }
}
