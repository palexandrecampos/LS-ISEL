package pt.isel.ls.view.student;

import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Student;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class JSONStudentsView implements Writable {

    private final List<Student> students;

    public JSONStudentsView(List<Student> students) {
        this.students = students;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("\"Students\":[\n");
        students.forEach(
                student -> builder.append("\t{\n" +
                        "\t\t\"studentID\":" + student.getStudentID() + ",\n" +
                        "\t\t\"nameStudent\":\"" + student.getNameStudent() + "\",\n" +
                        "\t\t\"emailStudent\":\"" + student.getEmailStudent() + "\",\n" +
                        "\t\t\"acronymProgramme\":\"" + student.getAcronymProgramme() + "\"\n" +
                        "\t},\n")
        );
        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.append("]");
        w.write(builder.toString());
    }
}
