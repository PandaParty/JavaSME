package sme;

import java.util.List;
import java.util.Scanner;

public class SMEStandardIO implements SMEIO {
    private Level level;

    public SMEStandardIO(Level _level){
        level = _level;
    }

    public void output(String s, Level runLevel, List<TraceItem> trace){
        trace.add(new TraceItem(this, s, IO.OUTPUT));
        if(level == runLevel){
            System.out.println(s);
        }
    }

    public String input(Level runLevel, List<TraceItem> trace){
        if(runLevel == level){
            Scanner scan = new Scanner(System.in);
            String inputString = scan.nextLine();
            trace.add(new TraceItem(this, inputString, IO.INPUT));
            return inputString;
        }
        else if(runLevel.higherThan(level)){
            for(TraceItem t : trace){
                if(t.getChannel().equals(this) && t.getIoType() == IO.INPUT){
                    String res = t.getValue();
                    trace.remove(t);
                    return res;
                }
            }
            return "";
        }
        return "";
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof SMEStandardIO)) return super.equals(o);

        SMEStandardIO so = (SMEStandardIO)o;
        return level == so.level;
    }

    @Override
    public int hashCode(){
        return level.hashCode();
    }
}
