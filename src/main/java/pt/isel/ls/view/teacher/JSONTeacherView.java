package pt.isel.ls.view.teacher;

import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Teacher;

import java.io.IOException;
import java.io.Writer;

public class JSONTeacherView implements Writable {

    private final Teacher teacher;

    public JSONTeacherView(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        w.write("\"Teacher\":{\n" +
                "\t\"teacherID\":" + teacher.getTeacherID() + "\",\n" +
                "\t\"nameTeacher\":\"" + teacher.getNameTeacher() + "\",\n" +
                "\t\"emailTeacher\":\"" + teacher.getEmailTeacher() + "\"\n" +
                "}\n");
    }
}
