package pt.isel.ls.view.teacher;

import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Teacher;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class TeachersView implements Writable {

    private final List<Teacher> teachers;

    public TeachersView(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        StringBuilder builder = new StringBuilder();
        teachers.forEach(teacher -> builder.append(
                "Teacher ID: " + teacher.getTeacherID() + "\n" +
                        "Name Teacher: " + teacher.getNameTeacher() + "\n" +
                        "Email Teacher: " + teacher.getEmailTeacher() + "\n" +
                        "\n"));
        w.write(builder.toString());
    }
}
