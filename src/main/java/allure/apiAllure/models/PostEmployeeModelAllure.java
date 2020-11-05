package allure.apiAllure.models;

public class PostEmployeeModelAllure {

    Integer id;
    String name;
    String salary;
    String age;

    public PostEmployeeModelAllure(String name, String salary, String age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public PostEmployeeModelAllure(Integer id, String name, String salary, String age) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.age = age;
    }
}
