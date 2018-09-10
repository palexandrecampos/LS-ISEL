package pt.isel.ls.view.course;

import pt.isel.ls.ResolveURL;
import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Course;
import pt.isel.ls.html.HtmlPage;

import java.io.IOException;
import java.io.Writer;

import static pt.isel.ls.html.Html.*;

public class HTMLCourseView implements Writable {

    private final Course course;

    public HTMLCourseView(Course course) {
        this.course = course;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        Writable programmesExist;

        programmesExist = course.getProgramme() == null ? text("There isn't any Programme associated to this Course") :
                a("/programmes/" + course.getProgramme().getAcronym(), "Programme");

        if (course.getClasses().size() == 0) {
            HtmlPage page = new HtmlPage("Academic Semesters",
                    h2(text("Course Acronym: " + course.getAcronym()),
                            h3(text("Course Name: " + course.getNameCourse())),
                            h3(text("Teacher ID: "), a(ResolveURL.getOrPostTeacher(course.getTeacherID()), "" + course.getTeacherID())),
                            h3(text("There aren't Classes associated to this Course")),
                            h4(text("Add new Class to Course")),
                            form("POST", ResolveURL.getOrPostClassOfCourse(course.getAcronym()),
                                    text("Semester"),
                                    br(),
                                    textInput("sem"),
                                    br(),
                                    text("Identifier"),
                                    br(),
                                    textInput("num"),
                                    br(),
                                    submitInput("Submit")),
                            h3(a("/courses", "Courses")),
                            h3(programmesExist)),
                    h3(a(ResolveURL.getHome(), "Home"))
            );

            page.writeTo(w);
        } else {
            HtmlPage page = new HtmlPage("Academic Semesters",
                    h2(text("Course Acronym: " + course.getAcronym()),
                            h3(text("Course Name: " + course.getNameCourse())),
                            h3(text("Teacher ID: "), a(ResolveURL.getOrPostTeacher(course.getTeacherID()), "" + course.getTeacherID())),
                            h3((a(ResolveURL.getOrPostClassOfCourse(course.getAcronym()), "Classes Of " + course.getAcronym()))),
                            h3(a("/courses", "Courses")),
                            h3(programmesExist)),
                    h3(a(ResolveURL.getHome(), "Home"))
            );

            page.writeTo(w);
        }
    }
}
