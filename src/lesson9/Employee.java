package lesson9;

public class Employee {

    private String name;
    private String surname;
    private String position;
    private String email;
    private String phoneNumber;
    private double wage;
    private int age;

    public Employee(String name, String surname, String position, String email, String phoneNumber, double wage, int age) {
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.wage = wage;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPosition() {
        return position;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getWage() {
        return wage;
    }

    public int getAge() {
        return age;
    }

    public void printInformation(){
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Position: " + position);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Wage: " + wage);
        System.out.println("Age: " + age);

    }
}