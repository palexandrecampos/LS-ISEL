package pt.isel.ls.commands;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import pt.isel.ls.Request;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Rafae_000 on 04/04/2017.
 */
public class Option implements ICommand {

    @Override
    public void execute(SQLServerDataSource dataSource, Request request, Response response) throws Exception {
        Map<String, String> map = new HashMap<>();

        Scanner scanner = new Scanner(new FileReader(new File("src/main/resources/commandList.txt")));

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (!line.startsWith("#")) {
                String[] args = line.split(";");
                map.put(args[0].concat(" " + args[1]), args[2]);
            }
        }

        for (String s : map.keySet()) {
            System.out.println(s);
            System.out.println("    " + map.get(s));
        }


    }
}
