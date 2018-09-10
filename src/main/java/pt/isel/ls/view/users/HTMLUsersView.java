package pt.isel.ls.view.users;

import pt.isel.ls.ResolveURL;
import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Person;
import pt.isel.ls.domain.Student;
import pt.isel.ls.domain.Teacher;
import pt.isel.ls.html.HtmlElem;
import pt.isel.ls.html.HtmlPage;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import static pt.isel.ls.html.Html.*;
import static pt.isel.ls.html.HtmlElem.*;

public class HTMLUsersView implements Writable {

    private final List<Person> persons;

    public HTMLUsersView(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        HtmlElem elem = new HtmlElem("ls",
                h1(text("Users"))
                        .withAttr("class", "text-center")
                        .withAttr("style", "margin-top:20px"),
                table(
                        new HtmlElem("thead",
                                tr(
                                        th(text("ID")),
                                        th(text("Name")),
                                        th(text("Email"))
                                )
                        ), getList(persons)
                ).withAttr("border", "5"),
                        h3(a(ResolveURL.getHome(), "Home"))
                ).withAttr("class", "table table-striped");
        HtmlPage page = new HtmlPage("Academic Semesters", elem);
        page.writeTo(w);
    }

    private Writable getList(List<Person> persons) {
        HtmlElem div = new HtmlElem("tbody");
        persons.forEach(
                person -> {
                    if (person instanceof Teacher) {
                        div.withContent(
                                tr(
                                        td(a(ResolveURL.getOrPostTeacher(((Teacher) person).getTeacherID()), "" + ((Teacher) person).getTeacherID())),
                                        td(text(((Teacher) person).getNameTeacher())),
                                        td(text(((Teacher) person).getEmailTeacher()))
                                )
                        );
                    } else {
                        div.withContent(
                                tr(
                                        td(a(ResolveURL.getOrPostStudent(((Student) person).getStudentID()), "" + ((Student) person).getStudentID())),
                                        td(text(((Student) person).getNameStudent())),
                                        td(text(((Student) person).getEmailStudent()))

                                ));
                    }
                });

        return div;
    }
}