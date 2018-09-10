package pt.isel.ls.view.course;

import pt.isel.ls.ResolveURL;
import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Course;
import pt.isel.ls.html.HtmlElem;
import pt.isel.ls.html.HtmlPage;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import static pt.isel.ls.html.Html.*;
import static pt.isel.ls.html.Html.a;
import static pt.isel.ls.html.Html.text;
import static pt.isel.ls.html.HtmlElem.td;
import static pt.isel.ls.html.HtmlElem.th;
import static pt.isel.ls.html.HtmlElem.tr;

public class HTMLCoursesView implements Writable {

    private final List<Course> courses;

    public HTMLCoursesView(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        HtmlElem elem = new HtmlElem("table",
                h1(text("Courses"))
                        .withAttr("class", "text-center")
                        .withAttr("style", "margin-top:20px"),
                table(
                        new HtmlElem("thead",
                                tr(
                                        th(text("Acronym Course")),
                                        th(text("Name Course")),
                                        th(text("Teacher ID"))
                                )
                        ), (getList(courses))
                ).withAttr("border", "5"),
                h4(text("Add new Course"),
                form("POST", "/courses",
                        text("Name Course"),
                        br(),
                        textInput("name"),
                        br(),
                        text("Acronym"),
                        br(),
                        textInput("acr"),
                        br(),
                        text("Teacher ID"),
                        br(),
                        textInput("teacher"),
                        br(),
                        submitInput("Submit"))),
                //h3(a(ResolveURL.getOrPostCourse()), "Submit"),
                h3(a(ResolveURL.getHome(), "Home"))
        ).withAttr("class", "table table-striped");
        HtmlPage page = new HtmlPage("Academic Semesters", elem);
        page.writeTo(w);
    }

    private Writable getList(List<Course> courses) {
        HtmlElem div = new HtmlElem("tbody");
        courses.forEach(
                course -> div.withContent(
                        tr(
                                td(a(ResolveURL.getOrPostCourse(course.getAcronym()), "" + course.getAcronym())),
                                td(text(course.getNameCourse())),
                                td(text("" + course.getTeacherID())
                                ))
                ));
        return div;
    }
}
