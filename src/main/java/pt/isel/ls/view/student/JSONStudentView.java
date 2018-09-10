package pt.isel.ls.view.student;

import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Student;

import java.io.IOException;
import java.io.Writer;

public class JSONStudentView implements Writable {
    private final Student student;

    public JSONStudentView(Student student) {
        this.student = student;
    }


    @Override
    public void writeTo(Writer w) throws IOException {
        w.write("\"Student\":{\n" +
                "\t\"studentID\":" + student.getStudentID() + ",\n" +
                "\t\"nameStudent\":\"" + student.getNameStudent() + "\",\n" +
                "\t\"emailStudent\":\"" + student.getEmailStudent() + "\",\n" +
                "\t\"acronymProgramme\":\"" + student.getAcronymProgramme() + "\"\n" +
                "}\n");
    }
}
