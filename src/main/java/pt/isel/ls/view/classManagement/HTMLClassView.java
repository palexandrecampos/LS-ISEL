package pt.isel.ls.view.classManagement;

import pt.isel.ls.ResolveURL;
import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Classs;
import pt.isel.ls.domain.Student;
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

public class HTMLClassView implements Writable {

    private final Classs classs;

    public HTMLClassView(Classs classs) {
        this.classs = classs;
    }


    @Override
    public void writeTo(Writer w) throws IOException {
        HtmlPage page = new HtmlPage("Academic Semesters",
                h2(text("Course Acronym: " + classs.getAcronym())),
                h3(text("Class Identifier: " + classs.getIdentifier())),
                h3(text("Year Semester: " + classs.getYearSemester())),
                h3(text("Semester: " + classs.getSemester())),
                table(
                        new HtmlElem("thead",
                                tr(
                                        th(text("Student ID")),
                                        th(text(""))
                                )
                        ),
                        generateTBodyStudents(classs.getStudents())
                ),
                table(
                        new HtmlElem("thead",
                                tr(
                                        th(text("Teacher ID")),
                                        th(text(""))
                                )
                        ),
                        generateTBodyTeachers(classs.getTeachers())
                ),
                h3(a(ResolveURL.getOrPostClassOfCourse(classs.getAcronym()), "Classes Of " + classs.getAcronym())),
                h4(text("Add new Teacher to Class"),
                        form("POST", ResolveURL.postTeacherToClass(classs.getAcronym(), classs.getYearSemester(),
                                classs.getSemester(), classs.getIdentifier()),
                                text("Teacher Number"),
                                br(),
                                textInput("numDoc"),
                                br(),
                                submitInput("Sumbit"))),
                h4(text("Add new Student to Class"),
                        form("POST", ResolveURL.postStudentToClass(classs.getAcronym(), classs.getYearSemester(),
                                classs.getSemester(), classs.getIdentifier()),
                                text("Student Number"),
                                br(),
                                textInput("numStu"),
                                br(),
                                submitInput("Submit"))),
                h3(a(ResolveURL.getHome(), "Home"))
        );
        page.writeTo(w);
    }

    private Writable generateTBodyStudents(List<Student> students) {
        HtmlElem elem = new HtmlElem("tbody");
        students.forEach(
                student -> elem.withContent(
                        tr(
                                td(a(ResolveURL.getOrPostStudent(student.getStudentID()), "" + student.getStudentID())
                                )
                        )
                ));
        return elem;
    }

    private Writable generateTBodyTeachers(List<Teacher> teachers) {
        HtmlElem elem = new HtmlElem("tbody");
        teachers.forEach(
                teacher -> elem.withContent(
                        tr(
                                td(a(ResolveURL.getOrPostTeacher(teacher.getTeacherID()), "" + teacher.getTeacherID())
                                )
                        )
                ));
        return elem;
    }
}
