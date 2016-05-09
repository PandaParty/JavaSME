package sme;

import java.util.HashMap;

public interface SMEIO {
    void output(String s, Level runLevel);
    String input (Level runLevel, HashMap<SMEIO, String> map);
}
