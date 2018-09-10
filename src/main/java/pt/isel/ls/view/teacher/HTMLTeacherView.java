package pt.isel.ls.view.teacher;

import pt.isel.ls.ResolveURL;
import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Classs;
import pt.isel.ls.domain.Course;
import pt.isel.ls.domain.Teacher;
import pt.isel.ls.html.HtmlElem;
import pt.isel.ls.html.HtmlPage;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import static pt.isel.ls.html.Html.*;
import static pt.isel.ls.html.HtmlElem.td;
import static pt.isel.ls.html.HtmlElem.th;
import static pt.isel.ls.html.HtmlElem.tr;

public class HTMLTeacherView implements Writable {

    private final Teacher teacher;

    public HTMLTeacherView(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        HtmlElem elem = new HtmlElem("Academic Semesters",
                h2(text("Teacher ID: " + teacher.getTeacherID())),
                h3(text("Name Teacher: " + teacher.getNameTeacher())),
                h3(text("Email Teacher: " + teacher.getEmailTeacher())),
                        table(
                                new HtmlElem("thead",
                                        tr(
                                                th(text("Classes"))
                                        )), ul(getClassesList(teacher.getClasses()))

                        ),
                        table(
                              new HtmlElem("thread",
                                      tr(
                                              th(text("Courses")))),
                                                ul(getCoursesList(teacher.getCourses())
                                      )
                        ),
                h3(a(ResolveURL.getHome(), "Home")),
                        h3(a("/teachers","Teachers")),
                        h3(a("/users","Users"))
        ).withAttr("class", "table table-striped");

        HtmlPage page = new HtmlPage("Academic Semesters", elem);
        page.writeTo(w);
    }

    private Writable getClassesList(List<Classs> classes) {
        HtmlElem div = new HtmlElem("tbody");
        classes.forEach(
                classs -> div.withContent(
                        tr(
                                td(text(classs.getAcronym())),
                                td(a(ResolveURL.getOrPostSpecificClass(classs.getAcronym(),classs.getYearSemester(),classs.getSemester(),classs.getIdentifier()),classs.getIdentifier())))
                        ));
        return div;
    }

    private Writable getCoursesList(List<Course> courses) {
        HtmlElem div = new HtmlElem("tbody");
        courses.forEach(
                course -> div.withContent(
                        tr(
                                td(a(ResolveURL.getCourseOfTeacher(course.getAcronym()),""+course.getAcronym()))
                        )
                )
        );
        return div;
    }
}

