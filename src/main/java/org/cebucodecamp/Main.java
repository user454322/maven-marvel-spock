package org.cebucodecamp;

import java.util.List;

import static java.lang.System.err;
import static java.lang.System.exit;
import static java.lang.System.out;


public class Main {


    public static void main (final String[] args) throws Exception  {

        if (args.length > 0) {
            final MarvelService service = new MarvelService();
            final String arg = args[0];

            switch (arg) {
                case "search":
                    final String searchParam = args[1];
                    List<String> charactersNames = service.searchCharactersWhoseNameStartsWith
                        (searchParam);
                    out.println(charactersNames);
                    break;

                case "list-characters":

                default:
                    err.println("Unknown command");
                    exit(1);
            }
        }

    }

}
