package pt.isel.ls.view.student;

import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Student;

import java.io.IOException;
import java.io.Writer;

public class StudentView implements Writable {

    private final Student student;

    public StudentView(Student student) {
        this.student = student;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
       w.write("Student ID: " + student.getStudentID() + "\n" +
                "Name Student: " + student.getNameStudent() + "\n" +
                "Email Student: " + student.getEmailStudent() + "\n" +
                "Acronym Programme: " + student.getAcronymProgramme() + "\n" +
                "\n");
    }
}
