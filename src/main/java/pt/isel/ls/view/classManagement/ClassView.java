package pt.isel.ls.view.classManagement;

import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Classs;

import java.io.IOException;
import java.io.Writer;

public class ClassView implements Writable {

    private final Classs classs;

    public ClassView(Classs classs) {
        this.classs = classs;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        w.write("Course Acronym: " + classs.getAcronym() + "\n" +
                "Class Identifier: " +classs.getIdentifier() + "\n" +
                "Year Semester: " +classs.getYearSemester() + "\n" +
                "Semester: " +classs.getSemester() + "\n" +
                "\n");

    }
}
