package pt.isel.ls.domain;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by palex on 21/03/2017.
 */
public class Teach implements IDomain {

    private String identifier;
    private String acronym;
    private String yearSemester;
    private String semester;
    private int teacherID;
    private List<Teach> teaches;

    public Teach(String identifier, String acronym, String yearSemester, String semester, int teacherID) {
        this.identifier = identifier;
        this.acronym = acronym;
        this.yearSemester = yearSemester;
        this.semester = semester;
        this.teacherID = teacherID;
        teaches = new LinkedList<>();
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

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public List<Teach> getTeaches() {
        return teaches;
    }

    public void print() {
        System.out.println("Identifier = "+getIdentifier());
        System.out.println("Acronym Teacher = "+getAcronym());
        System.out.println("Year Semester = "+getYearSemester());
        System.out.println("Semester = "+getSemester());
        System.out.println("ID Teacher = "+getTeacherID());
        System.out.println();

    }
}
