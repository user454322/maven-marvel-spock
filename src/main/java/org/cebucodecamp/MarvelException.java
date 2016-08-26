package org.cebucodecamp;

public class MarvelException extends RuntimeException {

    private static final long serialVersionUID = 49832749239423L;

    public MarvelException(final Throwable throwable){
        super(throwable);
    }
}
