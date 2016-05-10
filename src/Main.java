import sme.Level;
import sme.SMEFile;
import sme.SMEIO;
import sme.SMEStandardIO;

import java.io.File;
import java.util.HashMap;

public class Main {
    public static void main(String[] args){
        HashMap<SMEIO, String> map = new HashMap<>();
        example4(Level.LOW, map);
        example4(Level.HIGH, map);
    }

    private static void example1(Level level, HashMap<SMEIO, String> map){
        System.out.println("Running at level: " + level.toString());
        SMEFile lowFile = new SMEFile(new File("lowFile.txt"), Level.LOW);
        SMEFile highFile = new SMEFile(new File("highFile.txt"), Level.HIGH);
        lowFile.output("Text to put in file", level);
        String lowContents = lowFile.input(level, map);
        System.out.println(lowContents);
        highFile.output(lowContents, level);
    }

    private static void example2(Level level, HashMap<SMEIO, String> map){
        System.out.println("Running at level: " + level.toString());
        SMEFile lowFile = new SMEFile(new File("lowFile.txt"), Level.LOW);
        SMEFile highFile = new SMEFile(new File("highFile.txt"), Level.HIGH);
        highFile.output("Text to put in file", level);
        String highContents = highFile.input(level, map);
        System.out.println(highContents);
        lowFile.output(highContents, level);
    }

    private static void example3(Level level, HashMap<SMEIO, String> map){
        System.out.println("Running at level: " + level.toString());
        SMEStandardIO lowLevelStandardIO = new SMEStandardIO(Level.LOW);
        SMEFile highFile = new SMEFile(new File("highFile.txt"), Level.HIGH);
        lowLevelStandardIO.output("Please input your what to put in file", level);
        String input = lowLevelStandardIO.input(level, map);
        highFile.output(input, level);
    }

    private static void example4(Level level, HashMap<SMEIO, String> map){
        System.out.println("Running at level: " + level.toString());
        SMEStandardIO highLevelStandardIO = new SMEStandardIO(Level.HIGH);
        SMEFile lowFile = new SMEFile(new File("lowFile.txt"), Level.LOW);
        highLevelStandardIO.output("Please input your password", level);
        String password = highLevelStandardIO.input(level, map);
        if(password == "master"){
            lowFile.output("illegal flow was executed", level);
        }
    }
}
