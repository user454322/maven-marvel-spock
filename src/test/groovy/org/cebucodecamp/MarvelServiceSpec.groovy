package org.cebucodecamp

import spock.lang.Specification

class MarvelServiceSpec extends Specification {

    def service = new MarvelService();

    def searchCharactersWhoseNameStartsWith() {
        given: "A string as parameter to search character whose names start with it"
        def character = service.searchCharactersWhoseNameStartsWith("A.I.M").get(0);

        expect: "A.I.M. the terrorist organization bent on destroying the world is found."
        character == "A.I.M.";
    }

}
