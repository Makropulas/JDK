package dz4;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDirectory {
    private final List<Employee> employees = new ArrayList<>();

    public void addNewEmployee(String name, String phone, int experience) {
        employees.add(new Employee(name, phone, experience));
    }

    public List<Employee> findEmployeeBasedOnExperience(int experience) {
        return employees.stream()
                .filter(employee -> employee.getExperience() == experience)
                .toList();
    }

    public String getPhoneByName(String name) {
        StringBuilder phonesByName = new StringBuilder();
        for (Employee employee : employees) {
            if (employee.getName().equals(name))
                phonesByName.append(String.format("%s (%s, Табельный номер: %d)\n",
                        employee.getPhone(), name, employee.getPersonnelNumber()));
        }
        return phonesByName.toString();
    }

    public Employee findEmployeeByPersonnelNumber(int personnelNumber) {
        for (Employee employee : employees) {
            if (employee.getPersonnelNumber() == personnelNumber) return employee;
        }
        return null;
    }
}
