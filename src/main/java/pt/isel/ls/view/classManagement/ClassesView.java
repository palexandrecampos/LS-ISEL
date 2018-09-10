package pt.isel.ls.view.classManagement;

import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Classs;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class ClassesView implements Writable {

    private final List<Classs> classes;

    public ClassesView(List<Classs> classes) {
        this.classes = classes;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        StringBuilder builder = new StringBuilder();
        classes.forEach(classs -> builder.append(
                "Course Acronym: " + classs.getAcronym() + "\n" +
                        "Class Identifier: " +classs.getIdentifier() + "\n" +
                        "Year Semester: " +classs.getYearSemester() + "\n" +
                        "Semester: " +classs.getSemester() + "\n" +
                        "\n"));
        w.write(builder.toString());
    }
}
