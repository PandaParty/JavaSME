package sme;

import java.util.HashMap;

public interface SMEIO {
    void output(String s, Level runLevel, HashMap<SMEIO, Pair<String, IO>> map);
    String input (Level runLevel, HashMap<SMEIO, Pair<String, IO>> map);
}
