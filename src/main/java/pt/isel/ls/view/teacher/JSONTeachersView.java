package pt.isel.ls.view.teacher;

import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Teacher;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class JSONTeachersView implements Writable {

    private final List<Teacher> teachers;

    public JSONTeachersView(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("\"Teachers\":[\n");
        teachers.forEach(
                teacher -> builder.append("\t{\n" +
                        "\t\t\"teacherID\":\"" + teacher.getTeacherID() + "\",\n" +
                        "\t\t\"nameTeacher\":\"" + teacher.getNameTeacher() + "\",\n" +
                        "\t\t\"emailTeacher\":\"" + teacher.getEmailTeacher() + "\"\n" +
                        "\t},\n ")
        );
        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.append("]");
        w.write(builder.toString());
    }
}
