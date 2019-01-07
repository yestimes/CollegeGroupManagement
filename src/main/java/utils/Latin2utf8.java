package org.john.utils;

import java.io.UnsupportedEncodingException;

public class Latin2utf8 {

    public static String transform(String src){
        String dest = null;
        try {
            dest = new String(src.getBytes("iso-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return dest;
    }
}
