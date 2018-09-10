package pt.isel.ls.view;

import pt.isel.ls.ResolveURL;
import pt.isel.ls.common.Writable;
import pt.isel.ls.html.Html;
import pt.isel.ls.html.HtmlElem;
import pt.isel.ls.html.HtmlPage;

import java.io.IOException;
import java.io.Writer;

import static pt.isel.ls.html.Html.*;

public class ErrorViewHtml implements Writable {

    private int status;
    private String errorTextException;

    public ErrorViewHtml(int status, String errorTextException) {
        this.status = status;
        this.errorTextException = errorTextException;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        HtmlPage page = new HtmlPage("Academic Semesters",
                new HtmlElem("div")
                        .withContent(h1(text("Error " + status)))
                .withContent(h2(text(errorTextException)))
                .withContent(h3(a(ResolveURL.getHome(), "Home")))
                .withAttr("style", "text-align:center")
        );
        page.writeTo(w);
    }
}
