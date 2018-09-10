package pt.isel.ls.view.programme;

import pt.isel.ls.ResolveURL;
import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Programme;
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

public class HTMLProgrammesView implements Writable {

    private final List<Programme> programmes;

    public HTMLProgrammesView(List<Programme> programmes) {
        this.programmes = programmes;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        HtmlElem elem = new HtmlElem("table",
                h1(text("Programmes"))
                        .withAttr("class", "text-center")
                        .withAttr("style", "margin-top:20px"),
                table(
                        new HtmlElem("thead",
                                tr(
                                        th(text("Acronym Programme")),
                                        th(text("Name Programme")),
                                        th(text("Number Semesters"))
                                )
                        ),
                        getList(programmes)
                ).withAttr("border", "5"),
                h4(text("Add new Programme"),
                form("POST", ResolveURL.getOrPostProgramme(),
                        text("Acronym Programme"),
                        br(),
                        textInput("pid"),
                        br(),
                        text("Programme Name"),
                        br(),
                        textInput("name"),
                        br(),
                        text("Number Semesters"),
                        br(),
                        textInput("length"),
                        br(),
                        submitInput("Submit"))),
                //h3(a(ResolveURL.getOrPostSpecificProgramme(pid.toString()), "Submit"))),
                h3(a(ResolveURL.getHome(), "Home"))
        ).withAttr("class", "table table-striped");
        HtmlPage page = new HtmlPage("Academic Semesters", elem);
        page.writeTo(w);
    }

    private Writable getList(List<Programme> programmes) {
        HtmlElem div = new HtmlElem("tbody");
        programmes.forEach(
                programme -> div.withContent(
                        tr(
                                td(a(ResolveURL.getOrPostSpecificProgramme(programme.getAcronym()), "" + programme.getAcronym())),
                                td(text(programme.getName())),
                                td(text("" + programme.getNumberSemester())
                                ))
                ));
        return div;
    }
}
