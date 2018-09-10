package pt.isel.ls.domain;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by palex on 21/03/2017.
 */
public class Classs implements IDomain {

    private String identifier;
    private String acronym;
    private String yearSemester;
    private String semester;
    private List<Teacher> teachers;
    private List<Student> students;


    public Classs(String identifier, String acronym, String yearSemester, String semester) {
        this.identifier = identifier;
        this.acronym = acronym;
        this.yearSemester = yearSemester;
        this.semester = semester;
        teachers = new LinkedList<>();
        students = new LinkedList<>();
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getYearSemester() {
        return yearSemester;
    }

    public void setYearSemester(String yearSemester) {
        this.yearSemester = yearSemester;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void print() {
        System.out.println("Identifier = " + getIdentifier());
        System.out.println("Acronym = " + getAcronym());
        System.out.println("YearSemester = " + getYearSemester());
        System.out.println("Semester = " + getSemester());
        System.out.println();

    }
}
