package utils;

public class ArgsCheck {
    public static boolean isStringArgsCorrect(String src){
        if(src == null || "".equals(src)){
            return false;
        }
        else {
            return true;
        }
    }
}
