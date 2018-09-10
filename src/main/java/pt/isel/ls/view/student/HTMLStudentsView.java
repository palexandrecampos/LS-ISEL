package pt.isel.ls.view.student;

import pt.isel.ls.ResolveURL;
import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Student;
import pt.isel.ls.html.HtmlElem;
import pt.isel.ls.html.HtmlPage;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import static pt.isel.ls.html.Html.*;
import static pt.isel.ls.html.HtmlElem.td;
import static pt.isel.ls.html.HtmlElem.th;
import static pt.isel.ls.html.HtmlElem.tr;

public class HTMLStudentsView implements Writable {

    private final List<Student> students;

    public HTMLStudentsView(List<Student> students) {
        this.students = students;

    }

    @Override
    public void writeTo(Writer w) throws IOException {
        HtmlElem elem = new HtmlElem("table",
                h1(text("Students"))
                        .withAttr("class", "text-center")
                        .withAttr("style", "margin-top:20px"),
                table(
                        new HtmlElem("thead",
                                tr(
                                        th(text("Student ID")),
                                        th(text("Name Student")),
                                        th(text("Email Student")),
                                        th(text("Programme"))
                                )
                        ),
                        ul(getList(students))
                ).withAttr("border", "5"),
                h4(text("Add new Student"),
                form("POST", ResolveURL.getStudents(),
                        text("Student Number"),
                        br(),
                        textInput("num"),
                        br(),
                        text("Name Student"),
                        br(),
                        textInput("name"),
                        br(),
                        text("Email Student"),
                        br(),
                        textInput("email"),
                        br(),
                        text("Programme Acronym"),
                        br(),
                        textInput("pid"),
                        br(),
                        submitInput("Submit"))),
                h3(a(ResolveURL.getHome(), "Home"))
        ).withAttr("class", "table table-striped");
        HtmlPage page = new HtmlPage("Academic Semesters", elem);
        page.writeTo(w);
    }

    private Writable getList(List<Student> students) {
        HtmlElem div = new HtmlElem("tbody");
        students.forEach(
                student -> div.withContent(
                        tr(
                                td(a(ResolveURL.getOrPostStudent(student.getStudentID()), "" + student.getStudentID())),
                                td(text(student.getNameStudent())),
                                td(text(student.getEmailStudent()),
                                        td(text(student.getAcronymProgramme()))
                                ))
                ));
        return div;
    }
}