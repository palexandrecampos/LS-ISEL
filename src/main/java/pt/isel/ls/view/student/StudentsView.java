package pt.isel.ls.view.student;

import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Student;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class StudentsView implements Writable{

    private final List<Student> students;

    public StudentsView(List<Student> students) {
        this.students = students;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        StringBuilder builder = new StringBuilder();
        students.forEach(student -> builder.append(
                "Student ID: " + student.getStudentID() + "\n" +
                        "Name Student: " + student.getNameStudent() + "\n" +
                        "Email Student: " + student.getEmailStudent() + "\n" +
                        "Acronym Programme: " + student.getAcronymProgramme() + "\n" +
                        "\n"));
        w.write(builder.toString());
    }
}
