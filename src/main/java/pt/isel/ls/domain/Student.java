package pt.isel.ls.domain;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by palex on 21/03/2017.
 */
public class Student extends Person implements IDomain {

    private int studentID;
    private String nameStudent;
    private String emailStudent;
    private String acronymProgramme;
    private List<Classs> classes;

    public Student(int studentID, String nameStudent, String emailStudent, String acronymProgramme) {
        super(studentID, nameStudent, emailStudent);
        this.studentID = studentID;
        this.nameStudent = nameStudent;
        this.emailStudent = emailStudent;
        this.acronymProgramme = acronymProgramme;
        classes = new LinkedList<>();
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getEmailStudent() {
        return emailStudent;
    }

    public void setEmailStudent(String emailStudent) {
        this.emailStudent = emailStudent;
    }

    public String getAcronymProgramme() {
        return acronymProgramme;
    }

    public void setAcronymProgramme(String acronymProgramme) {
        this.acronymProgramme = acronymProgramme;
    }

    public List<Classs> getClasses() {
        return classes;
    }

    public void print() {
        System.out.println("ID Student = "+getStudentID());
        System.out.println("Name Student = "+getNameStudent());
        System.out.println("Email Student = "+getEmailStudent());
        System.out.println("Programme = "+getAcronymProgramme());
        System.out.println();
    }
}
