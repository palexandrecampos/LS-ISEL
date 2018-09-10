package pt.isel.ls.view.users;

import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Person;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class JSONUsersView implements Writable {
    private final List<Person> persons;

    public JSONUsersView(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("\"Users\":[\n");
        persons.forEach(
                person -> builder.append("\t{\n" +
                        "\t\t\"userID\":\"" + person.getUserID() + "\",\n" +
                        "\t\t\"nameUser\":\"" + person.getNameUser() + "\",\n" +
                        "\t\t\"emailUser\":\"" + person.getEmailUser() + "\"\n" +
                        "\t},\n ")
        );
        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.append("]");
        w.write(builder.toString());
    }
}
