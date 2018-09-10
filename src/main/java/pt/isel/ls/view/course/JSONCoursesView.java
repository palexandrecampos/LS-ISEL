package pt.isel.ls.view.course;

import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Course;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class JSONCoursesView implements Writable {

    private final List<Course> courses;

    public JSONCoursesView(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("\"Courses\":[\n");
        courses.forEach(
                course -> builder.append("\t{\n" +
                        "\t\t\"nameCourse\":\"" + course.getNameCourse() + "\",\n" +
                        "\t\t\"acronym\":\"" + course.getAcronym() + "\",\n" +
                        "\t\t\"teacherID\":" + course.getTeacherID() + "\n" +
                        "\t},\n")
        );
        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.append("]");
        w.write(builder.toString());
    }
}
