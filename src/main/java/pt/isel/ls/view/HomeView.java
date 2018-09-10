package pt.isel.ls.view;

import pt.isel.ls.common.Writable;
import pt.isel.ls.html.Html;
import pt.isel.ls.html.HtmlElem;
import pt.isel.ls.html.HtmlPage;
import pt.isel.ls.html.HtmlText;

import java.io.IOException;
import java.io.Writer;

import static pt.isel.ls.html.Html.*;

public class HomeView implements Writable{

    @Override
    public void writeTo(Writer w) throws IOException {
        HtmlPage page = new HtmlPage("Academic Semesters",
                h1(text("Academic Semesters")),
                h2(text("Choose one of the links to see the contents of each entity: ")),
                new HtmlElem("p",  a("/courses","Courses")),
                new HtmlElem("p",  a("/programmes","Programmes")),
                new HtmlElem("p",  a("/students","Students")),
                new HtmlElem("p",  a("/users","Users")),
                new HtmlElem("p",  a("/teachers","Teachers")));
        page.writeTo(w);
    }
}
