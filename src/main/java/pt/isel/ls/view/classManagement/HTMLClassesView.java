package pt.isel.ls.view.classManagement;

import pt.isel.ls.ResolveURL;
import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Classs;
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

public class HTMLClassesView implements Writable {

    private final List<Classs> classes;

    public HTMLClassesView(List<Classs> classes) {
        this.classes = classes;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        HtmlElem elem = new HtmlElem("table",
                h1(text("Classes"))
                        .withAttr("class", "text-center")
                        .withAttr("style", "margin-top:20px"),
                table(
                        new HtmlElem("thead",
                                tr(
                                        th(text("Course Acronym")),
                                        th(text("Class Identifier")),
                                        th(text("Year Semester")),
                                        th(text("Semester"))
                                )
                        ),
                        ul(getList(classes))
                ).withAttr("border", "5"),
                h4(text("Add new Class to Course"),
                        form("POST", ResolveURL.getOrPostClassOfCourse(classes.get(0).getAcronym()),
                                text("Semester"),
                                br(),
                                textInput("sem"),
                                br(),
                                text("Identifier"),
                                br(),
                                textInput("num"),
                                br(),
                                submitInput("Submit")),
                        h3(a(ResolveURL.getHome(), "Home")))
        ).withAttr("class", "table table-striped");
        HtmlPage page = new HtmlPage("Academic Semesters", elem);
        page.writeTo(w);
    }

    private Writable getList(List<Classs> classes) {
        HtmlElem div = new HtmlElem("tbody");
        classes.forEach(
                classs -> div.withContent(
                        tr(
                                td(a("/courses/" + classs.getAcronym(), classs.getAcronym())),
                                td(a(ResolveURL.getOrPostSpecificClass(classs.getAcronym(), classs.getYearSemester(),
                                        classs.getSemester(), classs.getIdentifier()), classs.getIdentifier())),
                                td(text(classs.getYearSemester()),
                                        td(text(classs.getSemester()))
                                ))
                ));
        return div;
    }
}
