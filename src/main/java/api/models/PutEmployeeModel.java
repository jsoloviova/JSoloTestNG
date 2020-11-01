package api.models;

public class PutEmployeeModel {

    Integer salary;
    Integer age;

    public PutEmployeeModel(Integer salary, Integer age) {
        this.salary = salary;
        this.age = age;
    }
}
