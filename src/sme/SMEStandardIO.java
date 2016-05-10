package sme;

import java.util.HashMap;
import java.util.Scanner;

public class SMEStandardIO implements SMEIO {
    private Level level;

    public SMEStandardIO(Level _level){
        level = _level;
    }

    public void output(String s, Level runLevel){
        if(level == runLevel){
            System.out.println(s);
        }
    }

    public String input(Level runLevel, HashMap<SMEIO, Pair<String, IO>> map){
        if(runLevel == level){
            Scanner scan = new Scanner(System.in);
            String inputString = scan.nextLine();
            map.put(this, new Pair(inputString, IO.INPUT));
            return inputString;
        }
        else if(runLevel.higherThan(level)){
            if(map.containsKey(this)){
                return map.get(this).a;
            }
            else{
                return "Shit's on fire, yo";
            }
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
