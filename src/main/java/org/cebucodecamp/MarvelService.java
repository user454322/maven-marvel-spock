package org.cebucodecamp;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.currentTimeMillis;
import static java.util.Collections.emptyList;

public class MarvelService {

    private static final String API_PUBLIC_KEY = "";
    private static final String API_PRIVATE_KEY = "";


    public List<String> searchCharactersWhoseNameStartsWith(final String characterNameStart) {
        final List<String> retVal = new ArrayList();

        if (characterNameStart == null) {
            return emptyList();
        }

        try {
            final String ts = ts();
            final String hash = hash(ts);
            final HttpResponse<JsonNode> response =
                    Unirest.get("http://gateway.marvel.com:80/v1/public/characters")
                            .queryString("ts", ts)
                            .queryString("hash", hash)
                            .queryString("nameStartsWith", characterNameStart)
                            .queryString("apikey", API_PUBLIC_KEY)
                            .asJson();
            final JSONObject  data = (JSONObject) response.getBody().getObject().get("data");
            final JSONArray results = (JSONArray) data.get("results");
            for(int i = 0; i < results.length(); i++) {
                JSONObject result = (JSONObject) results.get(i);
                final String name = (String) result.get("name");
                retVal.add(name);
            }

        } catch (final UnirestException restExc) {
            throw new MarvelException(restExc);
        }

        return retVal;
    }


    private String ts() {
        return Long.toString(currentTimeMillis());
    }

    private String hash(final String ts) {
        String retVal = "";
        try {
            final String message = ts + API_PRIVATE_KEY + API_PUBLIC_KEY;
            final MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(StandardCharsets.UTF_8.encode(message));
            retVal = String.format("%032x", new BigInteger(1, md5.digest()));

        }catch (final NoSuchAlgorithmException ignoredExc) {
        }

        return retVal;
    }
}
