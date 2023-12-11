package dz4;

public class Employee {
    private static int personnelNumberCounter = 0;
    private final int PERSONNEL_NUMBER;
    private String name;
    private String phone;
    private int experience;

    public Employee(String name, String phone, int experience) {
        this.PERSONNEL_NUMBER = ++personnelNumberCounter;
        this.name = name;
        this.phone = phone;
        this.experience = experience;
    }

    public int getPersonnelNumber() {
        return PERSONNEL_NUMBER;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return String.format("Табельный номер: %d, Имя: %s, Телефон: %s, Стаж: %d",
                PERSONNEL_NUMBER, name, phone, experience);
    }
}