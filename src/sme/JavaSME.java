package sme;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class JavaSME {

    public static void execute(BiConsumer<Level, List<TraceItem>> f){
        List<TraceItem> trace = new ArrayList<>();
        System.out.println("Running at level: LOW");
        f.accept(Level.LOW, trace);
        System.out.println("Running at level: HIGH");
        f.accept(Level.HIGH, trace);
        try{
            checkFlow(trace);
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static void checkFlow(List<TraceItem> trace) throws Exception{
        for(int i = 0; i < trace.size(); i++){
            if(trace.get(i).getIoType() != IO.OUTPUT){
                trace.remove(i);
                i--;
            }
        }
        for(TraceItem t : trace){
            boolean foundMatch = false;
            for (TraceItem other : trace) {
                if (t != other) {
                    if (t.getChannel().equals(other.getChannel()) && t.getValue().equals(other.getValue())) {
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
