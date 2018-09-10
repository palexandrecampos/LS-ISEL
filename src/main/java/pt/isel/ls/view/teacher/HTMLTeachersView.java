package pt.isel.ls.view.teacher;

import pt.isel.ls.ResolveURL;
import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Teacher;
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

public class HTMLTeachersView implements Writable {

    private final List<Teacher> teachers;

    public HTMLTeachersView(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        HtmlElem elem = new HtmlElem("table",
                h1(text("Teachers"))
                        .withAttr("class", "text-center")
                        .withAttr("style", "margin-top:20px"),
                table(
                        new HtmlElem("thead",
                                tr(
                                        th(text("Teacher ID")),
                                        th(text("Name Teacher")),
                                        th(text("Email Teacher"))
                                )
                        ),
                        getList(teachers)
                ).withAttr("border", "5"),
                h4(text("Add new Teacher"),
                        form("POST", ResolveURL.getTeachers(),
                                text("Teacher Number"),
                                br(),
                                textInput("num"),
                                br(),
                                text("Name Teacher"),
                                br(),
                                textInput("name"),
                                br(),
                                text("Teacher Email"),
                                br(),
                                textInput("email"),
                                br(),
                                submitInput("Submit"))),
                h3(a(ResolveURL.getHome(), "Home"))
        ).withAttr("class", "table table-striped");
        HtmlPage page = new HtmlPage("Academic Semesters", elem);
        page.writeTo(w);
    }

    private Writable getList(List<Teacher> teachers) {
        HtmlElem div = new HtmlElem("tbody");
        teachers.forEach(
                teacher -> div.withContent(
                        tr(
                                td(a(ResolveURL.getOrPostTeacher(teacher.getTeacherID()), "" + teacher.getTeacherID())),
                                td(text(teacher.getNameTeacher())),
                                td(text(teacher.getEmailTeacher())
                                ))
                ));
        return div;
    }
}
