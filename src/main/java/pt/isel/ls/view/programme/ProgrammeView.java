package pt.isel.ls.view.programme;


import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Programme;

import java.io.IOException;
import java.io.Writer;

public class ProgrammeView implements Writable {

    private final Programme programme;

    public ProgrammeView(Programme programme) {
        this.programme = programme;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        w.write("Programme: " + programme.getAcronym() + "\n" +
                "Programme Name: " + programme.getName() + "\n" +
                "Number of Semesters: " + programme.getNumberSemester() + "\n" +
                "\n");
    }
}
