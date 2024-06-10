package org.example.models;
public class Person extends PersonRecord {
    private String name;
    private int age;
    private String address;

    public Person(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setAge(int age) {
        try {
            if (age < 0 || age > 150) {
                throw new IllegalArgumentException("Age must be between 0 and 150");
            }
            this.age = age;
        } catch (IllegalArgumentException e) {
            System.out.println("Error setting age: " + e.getMessage());
        }
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String getDetails() {
        return "Person Details: \n" +
                "Name: " + getName() + "\n" +
                "Age: " + getAge() + "\n" +
                "Address: " + getAddress();
    }
}
