package pt.isel.ls.view.course;

import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Course;

import java.io.IOException;
import java.io.Writer;

public class JSONCourseView implements Writable {

    private final Course course;

    public JSONCourseView(Course course) {
        this.course = course;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        w.write("\"Course\":{\n" +
                "\t\"nameCourse\":\"" + course.getNameCourse() + "\",\n" +
                "\t\"acronym\":\"" + course.getAcronym() + "\",\n" +
                "\t\"teacherID\":" + course.getTeacherID() + "\n" +
                "}\n");
    }
}
