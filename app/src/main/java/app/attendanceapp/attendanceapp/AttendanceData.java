package app.attendanceapp.attendanceapp;

public class AttendanceData {
    String rollno;
    String name;
    String status;

    public AttendanceData(String rollno, String name, String status) {
        this.rollno = rollno;
        this.name = name;
        this.status = status;
    }

    public AttendanceData(String rollno, String name) {
        this.rollno = rollno;
        this.name = name;
        this.status = "present";
    }

}
