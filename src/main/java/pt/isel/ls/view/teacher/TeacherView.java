package pt.isel.ls.view.teacher;

import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Teacher;

import java.io.IOException;
import java.io.Writer;

public class TeacherView implements Writable {

    private final Teacher teacher;

    public TeacherView(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        w.write("Teacher: " + teacher.getTeacherID() + "\n" +
                "Name Teacher: " + teacher.getNameTeacher() + "\n" +
                "Email Teacher: " + teacher.getEmailTeacher() + "\n" +
                "\n");
    }
}
