package pt.isel.ls.domain;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by palex on 21/03/2017.
 */
public class Course implements IDomain {

    private String nameCourse;
    private String acronym;
    private int teacherID;
    private List<Classs> classes;
    private Programme programme;

    public Course(String nameCourse, String acronym, int teacherID) {
        this.nameCourse = nameCourse;
        this.acronym = acronym;
        this.teacherID = teacherID;
        classes = new LinkedList<>();
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setEmailTeacher(int teacherID) {
        this.teacherID = teacherID;
    }

    public List<Classs> getClasses() {
        return classes;
    }

    public void setProgramme(Programme programme){
        this.programme = programme;
    }

    public Programme getProgramme(){
        return programme;
    }
    public void print() {
        System.out.println("Course = " + getNameCourse());
        System.out.println("Acronym = " + getAcronym());
        System.out.println("Teacher ID = " + getTeacherID());
        System.out.println();

    }
}
