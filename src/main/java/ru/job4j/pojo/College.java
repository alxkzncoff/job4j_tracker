package ru.job4j.pojo;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("Kuznetsov Aleksandr Sergeevich");
        student.setGroup("I3");
        student.setDate("01.09.2008");
        System.out.println("Student: " + student.getName() + System.lineSeparator()
                           + "Group: " + student.getGroup() + System.lineSeparator()
                           + "Date of admission: " + student.getDate());
    }
}
