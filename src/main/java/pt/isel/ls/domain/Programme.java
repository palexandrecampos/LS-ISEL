package pt.isel.ls.domain;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by palex on 21/03/2017.
 */
public class Programme implements IDomain {

    private String acronymProgramme;
    private String name;
    private int numberSemester;
    private List<Course> courses;

    public Programme(String acronymProgramme, String name, int numberSemester) {
        this.acronymProgramme = acronymProgramme;
        this.name = name;
        this.numberSemester = numberSemester;
        courses = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronymProgramme;
    }

    public void setAcronym(String acronym) {
        this.acronymProgramme = acronym;
    }

    public int getNumberSemester() {
        return numberSemester;
    }

    public void setNumberSemester(int numberSemester) {
        this.numberSemester = numberSemester;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void print() {
        System.out.println("Programme = "+getAcronym());
        System.out.println("Programme Name = "+getName());
        System.out.println("Number of Semesters = "+getNumberSemester());
        System.out.println();

    }
}
