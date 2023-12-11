package dz4;

public class Dz4 {
    public static void main(String[] args) {
        EmployeeDirectory employees = new EmployeeDirectory();
        employees.addNewEmployee("Иван", "89001111111", 8);
        employees.addNewEmployee("Алла", "89002222222", 8);
        employees.addNewEmployee("Мария", "89003333333", 12);
        employees.addNewEmployee("Павел", "89004444444", 20);
        employees.addNewEmployee("Денис", "89005555555", 14);
        employees.addNewEmployee("Иван", "89006666666", 25);

        System.out.println("\nНайдём сотрудников со стажем 8 лет");
        employees.findEmployeeBasedOnExperience(8).forEach(System.out::println);

        System.out.println("\nВыведем номера телефонов по имени Иван");
        System.out.println(employees.getPhoneByName("Иван"));

        System.out.println("\nНайдём сотрудника с табельным номером 3");
        System.out.println(employees.findEmployeeByPersonnelNumber(3));
    }
}
