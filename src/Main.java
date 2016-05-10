import sme.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        List<Triple<SMEIO, String, IO>> trace = new ArrayList<>();
        example3(Level.LOW, trace);
        example3(Level.HIGH, trace);

        for(Triple<SMEIO, String, IO> t : trace){
            System.out.println("Channel: " + t.a.toString() + " "+ t.c.toString() + ":" + t.b.toString());
            for(Triple<SMEIO, String, IO> other : trace){
                if(t != other)
                {
                    if(t.a.equals(other.a) && t.c.equals(other.c) && t.b.equals(other.b)){
                        System.out.println("Duplicate found");
                    }
                }
            }
        }
    }

    private static void example1(Level level, List<Triple<SMEIO, String, IO>> trace){
        System.out.println("Running at level: " + level.toString());
        SMEFile lowFile = new SMEFile(new File("lowFile.txt"), Level.LOW);
        SMEFile highFile = new SMEFile(new File("highFile.txt"), Level.HIGH);
        lowFile.output("Text to put in file", level, trace);
        String lowContents = lowFile.input(level, trace);
        System.out.println(lowContents);
        highFile.output(lowContents, level, trace);
    }

    private static void example2(Level level, List<Triple<SMEIO, String, IO>> trace){
        System.out.println("Running at level: " + level.toString());
        SMEFile lowFile = new SMEFile(new File("lowFile.txt"), Level.LOW);
        SMEFile highFile = new SMEFile(new File("highFile.txt"), Level.HIGH);
        highFile.output("Text to put in file", level, trace);
        String highContents = highFile.input(level, trace);
        System.out.println(highContents);
        lowFile.output(highContents, level, trace);
    }

    private static void example3(Level level, List<Triple<SMEIO, String, IO>> trace){
        System.out.println("Running at level: " + level.toString());
        SMEStandardIO lowLevelStandardIO = new SMEStandardIO(Level.LOW);
        SMEFile highFile = new SMEFile(new File("highFile.txt"), Level.HIGH);
        lowLevelStandardIO.output("Please input what to put in file", level, trace);
        String input = lowLevelStandardIO.input(level, trace);
        highFile.output(input, level, trace);
    }

    private static void example4(Level level, List<Triple<SMEIO, String, IO>> trace){
        System.out.println("Running at level: " + level.toString());
        SMEStandardIO highLevelStandardIO = new SMEStandardIO(Level.HIGH);
        SMEFile lowFile = new SMEFile(new File("lowFile.txt"), Level.LOW);
        highLevelStandardIO.output("Please input your password", level, trace);
        String password = highLevelStandardIO.input(level, trace);
        if(password == "master"){
            lowFile.output("illegal flow was executed", level, trace);
        }
    }
}
