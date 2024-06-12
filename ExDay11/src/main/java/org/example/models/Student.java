package org.example.models;
public class Student extends Person implements ComparableById{
    public static String studentname ;
    private int studentId;
    private String course;
    


    public Student(String name, int age, String address, int studentId, String course) {
        super(name, age, address);
        this.studentId = studentId;
        this.course = course;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getCourse() {
        return course;
    }

    public void setStudentId(int studentId) {
        try {


            if (studentId <= 0) {
                throw new IllegalArgumentException("Student ID must be a positive integer");
            }
            this.studentId = studentId;
        }catch (IllegalArgumentException e) {
            System.out.println("Error setting Student ID: " + e.getMessage());
        }
    }

    public static String getStudentname() {
        return studentname;
    }

    public static void setStudentname(String studentname) {
        Student.studentname = studentname;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String getDetails() {
        return "Student Details: \n" +
                "Name: " + getName() + "\n" +
                "Age: " + getAge() + "\n" +
                "Address: " + getAddress() + "\n" +
                "Student ID: " + getStudentId() + "\n" +
                "Course: " + getCourse();
    }
    @Override
    public boolean compareById(int id) {
        return this.studentId == id;
    }

}
