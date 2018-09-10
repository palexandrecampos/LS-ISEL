package pt.isel.ls.view.programme;

import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Programme;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class JSONProgrammesView implements Writable {

    private final List<Programme> programmes;

    public JSONProgrammesView(List<Programme> programmes) {
        this.programmes = programmes;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("\"Programmes\":[\n");
        programmes.forEach(
                programme -> builder.append("\t{\n" +
                        "\t\t\"acronymProgramme\":\"" + programme.getAcronym() + "\",\n" +
                        "\t\t\"name\":\"" + programme.getName() + "\",\n" +
                        "\t\t\"numberSemester\":" + programme.getNumberSemester() + "\n" +
                        "\t},\n")
        );
        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.append("]");
        w.write(builder.toString());
    }
}
