package api;

import api.models.Employee;
import api.models.EmployeeResponse;
import api.models.PostEmployeeModel;
import api.models.PutEmployeeModel;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.testng.AssertJUnit.assertEquals;

public class RestApiNegativeTest {

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
        Employee expectedEmployee = new Employee(
                "Tiger Nixon",
                320800,
                61,
                ""
        );
        EmployeeResponse expectedResponse = new EmployeeResponse("success", expectedEmployee, "Successfully! Record has been fetched.");
        EmployeeResponse employeeResponse = given()
                .get("/employee/11")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponse.class);
        assertEquals(employeeResponse, expectedResponse);
    }

    @Test
    public void postEmployeeNegativeTest() {
        PostEmployeeModel postEmployeeModel = new PostEmployeeModel(15, "qweqwe", "123123", "21");
        EmployeeResponse expectedResponse = new EmployeeResponse("success", new Employee(), "Successfully! Record has been added.");
        EmployeeResponse employeeResponse = given()
                .with()
                .body(postEmployeeModel)
                .post("/create")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponse.class);
        assertEquals(employeeResponse, expectedResponse);
    }

    @Test
    public void putAllEmployeeByIdNegativeTest() {
        PutEmployeeModel putEmployeeModel = new PutEmployeeModel(5000, 88);
        Employee expectedEmployee = new Employee(
                "Jenette Caldwell",
                5000,
                88,
                ""
        );
        EmployeeResponse expectedResponse = new EmployeeResponse("success", new Employee(), "Successfully! Record has been updated.");
        EmployeeResponse employeeResponse = given()
                .with()
                .body(putEmployeeModel)
                .put("/update/50")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponse.class);
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
        EmployeeResponse expectedResponseGetEmployee = new EmployeeResponse("success", expectedEmployee, "Successfully! Record has been fetched.");
        EmployeeResponse employeeResponseGetEmployee = given()
                .get("/employee/21")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponse.class);
        assertEquals(employeeResponseGetEmployee, expectedResponseGetEmployee);
    }

    @Test
    public void deleteEmployeeNegativeTest() {
        EmployeeResponse expectedResponse = new EmployeeResponse("success", "successfully! deleted Records");
        EmployeeResponse employeeResponse = given()
                .delete("/delete/2")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(EmployeeResponse.class);
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
