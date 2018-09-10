package pt.isel.ls.domain;

public class Attends implements IDomain{

    private String identifier;
    private String acronym;
    private String yearSemester;
    private String semester;
    private int StudentID;

    public Attends(String identifier, String acronym, String yearSemester, String semester, int studentID) {
        this.identifier = identifier;
        this.acronym = acronym;
        this.yearSemester = yearSemester;
        this.semester = semester;
        StudentID = studentID;
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

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int studentID) {
        StudentID = studentID;
    }

    @Override
    public void print() {
        System.out.println("Identifier = "+getIdentifier());
        System.out.println("Acronym Teacher = "+getAcronym());
        System.out.println("Year Semester = "+getYearSemester());
        System.out.println("Semester = "+getSemester());
        System.out.println("ID Teacher = "+getStudentID());
        System.out.println();
    }
}
