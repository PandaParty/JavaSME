package sme;

import java.util.List;
import java.util.Scanner;

public class SMEStandardIO implements SMEIO {
    private Level level;

    public SMEStandardIO(Level _level){
        level = _level;
    }

    public void output(String s, Level runLevel, List<Triple<SMEIO, String, IO>> trace){
        trace.add(new Triple<>(this, s, IO.OUTPUT));
        if(level == runLevel){
            System.out.println(s);
        }
    }

    public String input(Level runLevel, List<Triple<SMEIO, String, IO>> trace){
        if(runLevel == level){
            Scanner scan = new Scanner(System.in);
            String inputString = scan.nextLine();
            trace.add(new Triple<>(this, inputString, IO.INPUT));
            return inputString;
        }
        else if(runLevel.higherThan(level)){
            for(Triple<SMEIO, String, IO> t : trace){
                if(t.a.equals(this) && t.c == IO.INPUT){
                    String res = t.b;
                    trace.remove(t);
                    return res;
                }
            }
            return "Shit's on fire, yo";
        }
        return "Can not read input of higher level";
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
