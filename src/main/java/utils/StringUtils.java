package utils;

public class StringUtils {

    public static int string2Integer(String src){
        if (src == null || "".equals(src)){
            return Integer.MIN_VALUE;
        }else {
            return Integer.parseInt(src);
        }
    }
}
