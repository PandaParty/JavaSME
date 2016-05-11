import sme.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        List<Triple<SMEIO, String, IO>> trace = new ArrayList<>();
        example4(Level.LOW, trace);
        example4(Level.HIGH, trace);

        try{
            JavaSME.checkFlow(trace);
        }catch(Exception e){
            System.err.println(e.getMessage());
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
        if(password.equals("master")){
            lowFile.output("illegal flow was executed", level, trace);
        }
    }
}
