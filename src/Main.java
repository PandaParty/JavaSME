import sme.Level;
import sme.SMEFile;

import java.io.File;
import java.util.HashMap;

public class Main {
    public static void main(String[] args){
        HashMap<SMEFile, String> map = new HashMap<>();
        example2(Level.LOW, map);
        example2(Level.HIGH, map);
    }

    private static void example1(Level level, HashMap<SMEFile, String> map){
        System.out.println("Running at level: " + level.toString());
        SMEFile lowFile = new SMEFile(new File("lowFile.txt"), Level.LOW);
        SMEFile highFile = new SMEFile(new File("highFile.txt"), Level.HIGH);
        lowFile.write("Text to put in file", level);
        String lowContents = lowFile.read(level, map);
        System.out.println(lowContents);
        highFile.write(lowContents, level);
    }

    private static void example2(Level level, HashMap<SMEFile, String> map){
        System.out.println("Running at level: " + level.toString());
        SMEFile lowFile = new SMEFile(new File("lowFile.txt"), Level.LOW);
        SMEFile highFile = new SMEFile(new File("highFile.txt"), Level.HIGH);
        highFile.write("Text to put in file", level);
        String highContents = highFile.read(level, map);
        System.out.println(highContents);
        lowFile.write(highContents, level);
    }
}
