package sme;

import java.util.List;

public interface SMEIO {
    void output(String s, Level runLevel, List<TraceItem> map);
    String input (Level runLevel, List<TraceItem> map);
}
