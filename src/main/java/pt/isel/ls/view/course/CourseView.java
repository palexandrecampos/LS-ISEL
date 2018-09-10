package pt.isel.ls.view.course;

import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Course;

import java.io.IOException;
import java.io.Writer;

public class CourseView implements Writable {

    private final Course course;

    public CourseView(Course course) {
        this.course = course;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        w.write("Acronym Course: " + course.getAcronym() + "\n" +
                "Course Name: " +course.getNameCourse() + "\n" +
                "Teacher ID: " +course.getTeacherID() + "\n" +
                "\n");
    }
}