package lesson9;

public class Main {
    public static void main(String[] args){
        Employee[] employes = {
                new Employee("Bobby", "Broke", "Admin", "bobby.broke@email.com", "823482384", 1000.00, 20),
                new Employee("Jessy", "Stone", "Cleaning Woman", "jessy322@email.com", "85623423", 800.00, 44),
                new Employee("Kevin", "Heart", "Accountant", "heart.keavin@email.com", "854743231", 1200.00, 28),
                new Employee("Jerry", "Ban", "Watchman", "jerrybanny@email.com", "825623223", 850.00, 50),
                new Employee("James", "Long", "Admin", "jameees@email.com", "852356434", 1000.00, 32)
        };

        for (int i = 0; i < employes.length; i++) {
            if(employes[i].getAge() > 40){
                employes[i].printInformation();
            }
        }
    }
}
