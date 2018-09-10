package pt.isel.ls.view.programme;

import pt.isel.ls.ResolveURL;
import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Course;
import pt.isel.ls.domain.Programme;
import pt.isel.ls.html.HtmlElem;
import pt.isel.ls.html.HtmlPage;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import static pt.isel.ls.html.Html.*;
import static pt.isel.ls.html.Html.h3;
import static pt.isel.ls.html.Html.text;
import static pt.isel.ls.html.HtmlElem.td;
import static pt.isel.ls.html.HtmlElem.th;
import static pt.isel.ls.html.HtmlElem.tr;

public class HTMLProgrammeView implements Writable {

    private Programme programme;

    public HTMLProgrammeView(Programme programme) {
        this.programme = programme;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        HtmlPage page = new HtmlPage("Academic Semesters",
                h2(text("Acronym Programme: " + programme.getAcronym()),
                        h3(text("Programme Name: " + programme.getName())),
                        h3(text("Number Semester: " + programme.getNumberSemester())),
                        table(
                                new HtmlElem("thead",
                                        tr(
                                                th(text("Course"))
                                        )), ul(getList(programme.getCourses()))

                        ),
                        h3(a(ResolveURL.getOrPostProgramme(), "Programmes")),
                        h4(text("Add Course to Programme"),
                        form("POST", ResolveURL.postCourseToProgramme(programme.getAcronym()),
                                text("Acronym"),
                                br(),
                                textInput("acr"),
                                br(),
                                text("Mandatory"),
                                br(),
                                textInput("mandatory"),
                                br(),
                                text("Semester ID"),
                                br(),
                                textInput("semesters"),
                                br(),

                                submitInput("Submit")),
                        h3(a(ResolveURL.getHome(), "Home")))));

        page.writeTo(w);
    }

    private Writable getList(List<Course> courses) {
        HtmlElem div = new HtmlElem("tbody");
        courses.forEach(
                course -> div.withContent(
                        tr(
                                td(a(ResolveURL.getOrPostCourse(course.getAcronym()), "" + course.getAcronym()))
                        ))
        );
        return div;
    }
}