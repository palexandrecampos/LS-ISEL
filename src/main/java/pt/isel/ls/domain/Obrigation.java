package pt.isel.ls.domain;

/**
 * Created by palex on 21/03/2017.
 */
public class Obrigation implements IDomain {

    private String acronymProgramme;
    private String acronym;
    private boolean mandatory;
    private int id;

    public Obrigation(String acronymProgramme, String acronym, boolean mandatory, int id) {
        this.acronymProgramme = acronymProgramme;
        this.acronym = acronym;
        this.mandatory = mandatory;
        this.id = id;
    }

    public String getAcronymProgramme() {
        return acronymProgramme;
    }

    public void setAcronymProgramme(String acronymProgramme) {
        this.acronymProgramme = acronymProgramme;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void print() {
        System.out.println("Programme = "+getAcronymProgramme());
        System.out.println("Couse = "+getAcronym());
        System.out.println("Mandatory  = "+isMandatory());
        System.out.println("ID = "+getId());
        System.out.println();

    }
}
