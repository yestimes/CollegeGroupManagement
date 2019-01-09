package utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Latin2utf8 {

    public static String transform(String src){
        return new String(src.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }
}
