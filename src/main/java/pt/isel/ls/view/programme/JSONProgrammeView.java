package pt.isel.ls.view.programme;

import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Programme;

import java.io.IOException;
import java.io.Writer;

public class JSONProgrammeView implements Writable {

    private final Programme programme;

    public JSONProgrammeView(Programme programme) {
        this.programme = programme;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        w.write("\"Programme\":{\n" +
                "\t\"acronymProgramme\":\"" + programme.getAcronym() + "\",\n" +
                "\t\"name\":\"" + programme.getName() + "\",\n" +
                "\t\"numberSemester\":" + programme.getNumberSemester() + "\n" +
                "}\n");
    }
}
