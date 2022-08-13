package com.govtech.viswa.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.Optional;

/**
 * @author Sarav on 10 Aug 2022
 * @project govtech
 * @package com.govtech.viswa.util
 * @class Common
 */
@Log4j2
public class Common implements Serializable {

    private static volatile Common instance = null;

    private Common () { }

    private static Common getInstance () {
        if ( instance == null ) {
            synchronized ( Common.class ) {
                if ( instance == null ) {
                    instance = new Common();
                }
            }
        }
        return instance;
    }

    private Common readResolve () {
        return instance;
    }

    public static Gson gson = new GsonBuilder().excludeFieldsWithModifiers( Modifier.STATIC, Modifier.TRANSIENT, Modifier.VOLATILE ).create();

    static final ClassLoader classLoader;

    static {
        classLoader = Common.class.getClassLoader();
    }

    public static String readJson ( String fileName )  {

        String jsonString = null;
        try {
            jsonString = new String( classLoader.getResourceAsStream( fileName ).readAllBytes() );
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info( "reading " + jsonString );
        return jsonString;
    }


    public static String left ( String str, int len ) {
        if ( str == null ) {
            return null;
        }
        if ( len < 0 ) {
            return "";
        }
        if ( str.length() <= len ) {
            return str;
        }
        return str.substring( 0, len );
    }

    public static <T> T isNullOrDefault(T input, T data) {
        return Optional.ofNullable(input).orElse(data);
    }

    public static <T> T isNull(T input) {
        if (input != null) {
            return input;
        }
        else {
            input = (T) "";
            return input;
        }
    }

}
