package sme;

import java.util.List;

public interface SMEIO {
    void output(String s, Level runLevel, List<Triple<SMEIO, String, IO>> map);
    String input (Level runLevel, List<Triple<SMEIO, String, IO>> map);
}
