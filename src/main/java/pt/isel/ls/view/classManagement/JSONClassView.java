package pt.isel.ls.view.classManagement;

import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Classs;

import java.io.IOException;
import java.io.Writer;

public class JSONClassView implements Writable {

    private final Classs classs;

    public JSONClassView(Classs classs) {
        this.classs = classs;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        w.write("\"Class\":{\n" +
                "\t\"identifier\":\"" + classs.getIdentifier() + "\",\n" +
                "\t\"acronym\":\"" + classs.getAcronym() + "\",\n" +
                "\t\"yearSemester\":\"" + classs.getYearSemester() + "\",\n" +
                "\t\"semester\":\"" + classs.getSemester() + "\"\n" +
                "}\n");
    }
}
