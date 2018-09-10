package pt.isel.ls.view.classManagement;

import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Classs;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class JSONClassesView implements Writable {

    private final List<Classs> classes;

    public JSONClassesView(List<Classs> classes) {
        this.classes = classes;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("\"Classes\":[\n");
        classes.forEach(
                classs -> builder.append("\t{\n" +
                        "\t\t\"identifier\":\"" + classs.getIdentifier() + "\",\n" +
                        "\t\t\"acronym\":\"" + classs.getAcronym() + "\",\n" +
                        "\t\t\"yearSemester\":\"" + classs.getYearSemester() + "\",\n" +
                        "\t\t\"semester\":\"" + classs.getSemester() + "\"\n" +
                        "\t},\n")
        );
        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.append("]");
        w.write(builder.toString());
    }
}
