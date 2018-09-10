package pt.isel.ls.view.course;

import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Course;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CoursesView implements Writable {

    private final List<Course> courses;

    public CoursesView(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        StringBuilder builder = new StringBuilder();
        courses.forEach(course -> builder.append(
                "Acronym Course: " + course.getAcronym() + "\n" +
                        "Course Name: " +course.getNameCourse() + "\n" +
                        "Teacher ID: " +course.getTeacherID() + "\n" +
                        "\n"));
        w.write(builder.toString());
    }
}
