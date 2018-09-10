package pt.isel.ls.view.programme;

import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Programme;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class ProgrammesView implements Writable {

    private final List<Programme> programmes;

    public ProgrammesView(List<Programme> programmes) {
        this.programmes = programmes;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        StringBuilder builder = new StringBuilder();
        programmes.forEach(programme -> builder.append(
                "Acronym Programme: " + programme.getAcronym() + "\n" +
                        "Programme Name: " + programme.getName() + "\n" +
                        "Number Semesters: " +programme.getNumberSemester() + "\n" +
                        "\n"));
        w.write(builder.toString());
    }
}
