package pt.isel.ls.view.student;

import pt.isel.ls.ResolveURL;
import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Classs;
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

public class HTMLStudentView implements Writable {

    private final Student student;

    public HTMLStudentView(Student student) {
        this.student = student;
    }

    public void writeTo(Writer w) throws IOException {
        HtmlElem elem = new HtmlElem("table",
                h2(text("Student ID: " + student.getStudentID())),
                h3(text("Name Student: " + student.getNameStudent())),
                h3(text("Email Student: " + student.getEmailStudent())),
                h3(text("Acronym Programme: "), a(ResolveURL.getOrPostSpecificProgramme(student.getAcronymProgramme()), student.getAcronymProgramme())),
                table(
                        new HtmlElem("thead",
                                tr(
                                        th(text("Course ")),
                                        th(text("Class ID")),
                                        th(text("Curricular Year")),
                                        th(text("Curricular Semester"))
                                )
                        ), getList(student.getClasses())
                ),
                        h3(a(ResolveURL.getHome(), "Home")),
                        h3(a("/students", "Students")),
                        h3(a("/users","Users"))
        ).withAttr("class", "table table-striped");
        HtmlPage page = new HtmlPage("Academic Semesters", elem);
        page.writeTo(w);
    }

    private Writable getList(List<Classs> classes) {
        HtmlElem div = new HtmlElem("tbody");
        classes.forEach(
                classs -> div.withContent(
                        tr(
                                td(text(classs.getAcronym())),
                                td(a(ResolveURL.getOrPostSpecificClass(classs.getAcronym(), classs.getYearSemester(), classs.getSemester(),
                                        classs.getIdentifier()), "" + classs.getIdentifier())),
                                td(text(classs.getYearSemester())),
                                td(text(classs.getSemester()))
                        ))
        );
        return div;
    }
}
