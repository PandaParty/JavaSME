package sme;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class JavaSME {

    public static void execute(BiConsumer<Level, List<Triple<SMEIO, String, IO>>> f){
        List<Triple<SMEIO, String, IO>> trace = new ArrayList<>();
        f.accept(Level.LOW, trace);
        f.accept(Level.HIGH, trace);
        try{
            checkFlow(trace);
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static void checkFlow(List<Triple<SMEIO, String, IO>> trace) throws Exception{
        for(int i = 0; i < trace.size(); i++){
            if(trace.get(i).c != IO.OUTPUT){
                trace.remove(i);
                i--;
            }
        }
        for(Triple<SMEIO, String, IO> t : trace){
            boolean foundMatch = false;
            for (Triple<SMEIO, String, IO> other : trace) {
                if (t != other) {
                    if (t.a.equals(other.a) && t.b.equals(other.b)) {
                        foundMatch = true;
                    }
                }
            }
            if(!foundMatch){
                throw new Exception("Illegal information flow in program");
            }
        }
    }
}
