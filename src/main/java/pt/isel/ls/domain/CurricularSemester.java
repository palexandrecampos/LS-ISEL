package pt.isel.ls.domain;

/**
 * Created by palex on 21/03/2017.
 */
public class CurricularSemester implements IDomain {

    private int id;

    public CurricularSemester(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void print() {
        System.out.println("ID Semester = "+getId());
        System.out.println();
        ;
    }
}
