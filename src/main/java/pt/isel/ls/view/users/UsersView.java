package pt.isel.ls.view.users;

import pt.isel.ls.common.Writable;
import pt.isel.ls.domain.Person;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class UsersView implements Writable {
    private final List<Person> persons;

    public UsersView(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public void writeTo(Writer w) throws IOException {
        StringBuilder builder = new StringBuilder();
        persons.forEach(person -> builder.append(
                "User: " + person.getUserID() + "\n" +
                        "Name User: " + person.getNameUser() + "\n" +
                        "Email User: " + person.getEmailUser() + "\n" +
                        "\n"));
        w.write(builder.toString());
    }
}
