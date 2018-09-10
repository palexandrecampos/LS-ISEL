package pt.isel.ls.domain;

/**
 * Created by palex on 21/03/2017.
 */
public class AcademicSemester implements IDomain{

    private String yearSemester;
    private String semester;

    public AcademicSemester(String yearSemester, String semester) {
        this.yearSemester = yearSemester;
        this.semester = semester;
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

    @Override
    public void print() {
        System.out.println("Year Semester"+getYearSemester());
        System.out.println("Semester"+getSemester());
        System.out.println();

    }
}
