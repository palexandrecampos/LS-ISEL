package pt.isel.ls.domain;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by palex on 21/03/2017.
 */
public class Teacher extends Person implements IDomain {

    private int teacherID;
    private String nameTeacher;
    private String emailTeacher;
    private List<Classs> classes;
    private List<Course> courses;

    public Teacher(int teacherID, String nameTeacher, String emailTeacher) {
        super(teacherID, nameTeacher, emailTeacher);
        this.teacherID = teacherID;
        this.nameTeacher = nameTeacher;
        this.emailTeacher = emailTeacher;
        classes = new LinkedList<>();
        courses = new LinkedList<>();
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getNameTeacher() {
        return nameTeacher;
    }

    public void setNameTeacher(String nameTeacher) {
        this.nameTeacher = nameTeacher;
    }

    public String getEmailTeacher() {
        return emailTeacher;
    }

    public void setEmailTeacher(String emailTeacher) {
        this.emailTeacher = emailTeacher;
    }

    public List<Classs> getClasses() {
        return classes;
    }

    public List<Course> getCourses(){
        return courses;
    }

    public void print() {
        System.out.println("ID Teacher = "+getTeacherID());
        System.out.println("Name Teacher = "+getNameTeacher());
        System.out.println("Email Teacher = "+getEmailTeacher());
        System.out.println();
    }
}
