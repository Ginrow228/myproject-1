package lesson9;

public class Employee {

    private String name;
    private String surname;
    private String position;
    private String email;
    private String numberPhone;
    private double wage;
    private int age;

    public Employee(String name, String surname, String position, String email, String numberPhone, double wage, int age) {
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.email = email;
        this.numberPhone = numberPhone;
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

    public String getNumberPhone() {
        return numberPhone;
    }

    public double getWage(){ return wage; }

    public int getAge() {
        return age;
    }

    public void printInformation(){
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Position: " + position);
        System.out.println("Email: " + email);
        System.out.println("Number phone: " + numberPhone);
        System.out.println("wage: " + wage);
        System.out.println("Age: " + age);
        System.out.println();
    }
}
