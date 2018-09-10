package pt.isel.ls.commands;

import pt.isel.ls.common.Writable;

import java.io.*;

public class Response {


    private Writer writer;
    private LocationViews locationViews;

    public Response(Writer writer) {
        this.writer = writer;
        locationViews = new LocationViews();
    }

    public void write(String toWrite) throws IOException {
        writer.write(toWrite);
        writer.flush();
    }

    public static Response createResponse(String file) throws IOException {
        return new Response(file == null ? new PrintWriter(System.out) : new FileWriter(new File(file)));
    }

    public static Response createResponse(PrintWriter writer) {
        return new Response(writer);
    }

    public Writer getWriter() {
        return writer;
    }

    public void write(Writable writable) throws IOException {
        writable.writeTo(writer);
        writer.flush();
    }

    public LocationViews getLocationViews() {
        return locationViews;
    }

    public void setLocationViews(LocationViews locationViews) {
        this.locationViews = locationViews;
    }
}
