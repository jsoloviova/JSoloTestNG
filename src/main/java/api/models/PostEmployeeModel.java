package api.models;

public class PostEmployeeModel {

    Integer id;
    String name;
    String salary;
    String age;

    public PostEmployeeModel(String name, String salary, String age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public PostEmployeeModel(Integer id, String name, String salary, String age) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
    }
}
