import sme.*;

import java.io.File;
import java.util.HashMap;

public class Main {
    public static void main(String[] args){
        HashMap<SMEIO, Pair<String, IO>> map = new HashMap<>();
        example4(Level.LOW, map);
        example4(Level.HIGH, map);
    }

    private static void example1(Level level, HashMap<SMEIO, Pair<String, IO>> map){
        System.out.println("Running at level: " + level.toString());
        SMEFile lowFile = new SMEFile(new File("lowFile.txt"), Level.LOW);
        SMEFile highFile = new SMEFile(new File("highFile.txt"), Level.HIGH);
        lowFile.output("Text to put in file", level, map);
        String lowContents = lowFile.input(level, map);
        System.out.println(lowContents);
        highFile.output(lowContents, level, map);
    }

    private static void example2(Level level, HashMap<SMEIO, Pair<String, IO>> map){
        System.out.println("Running at level: " + level.toString());
        SMEFile lowFile = new SMEFile(new File("lowFile.txt"), Level.LOW);
        SMEFile highFile = new SMEFile(new File("highFile.txt"), Level.HIGH);
        highFile.output("Text to put in file", level, map);
        String highContents = highFile.input(level, map);
        System.out.println(highContents);
        lowFile.output(highContents, level, map);
    }

    private static void example3(Level level, HashMap<SMEIO, Pair<String, IO>> map){
        System.out.println("Running at level: " + level.toString());
        SMEStandardIO lowLevelStandardIO = new SMEStandardIO(Level.LOW);
        SMEFile highFile = new SMEFile(new File("highFile.txt"), Level.HIGH);
        lowLevelStandardIO.output("Please input your what to put in file", level, map);
        String input = lowLevelStandardIO.input(level, map);
        highFile.output(input, level, map);
    }

    private static void example4(Level level, HashMap<SMEIO, Pair<String, IO>> map){
        System.out.println("Running at level: " + level.toString());
        SMEStandardIO highLevelStandardIO = new SMEStandardIO(Level.HIGH);
        SMEFile lowFile = new SMEFile(new File("lowFile.txt"), Level.LOW);
        highLevelStandardIO.output("Please input your password", level, map);
        String password = highLevelStandardIO.input(level, map);
        if(password == "master"){
            lowFile.output("illegal flow was executed", level, map);
        }
    }
}
