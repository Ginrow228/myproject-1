package lesson7;

public class Employee {
    private String info;
    private String jobTitle;
    private String email;
    private String numberPhone;
    private int age;

    public Employee(String info, String jobTitle, String email, String numberPhone, int age) {
        this.info = info;
        this.jobTitle = jobTitle;
        this.email = email;
        this.numberPhone = numberPhone;
        this.age = age;
    }

    public String getInfo() {
        return info;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getEmail() {
        return email;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public int getAge() {
        return age;
    }

}
