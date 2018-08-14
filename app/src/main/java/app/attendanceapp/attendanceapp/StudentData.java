package app.attendanceapp.attendanceapp;

public class StudentData {
    String name;
    String rollno;
    String status;
    boolean toBeDeleted;

    public StudentData(String name, String rollno, String status) {
        this.name = name;
        this.rollno = rollno;
        this.status = status;
        toBeDeleted = false;
    }

    public StudentData(String name, String rollno) {
        this.name = name;
        this.rollno = rollno;
        toBeDeleted = false;

    }
}
