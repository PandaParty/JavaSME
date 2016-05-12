package sme;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class SMEFile implements SMEIO{
    private Level level;
    private File file;

    public SMEFile(File _file, Level _level){
        file = _file;
        level = _level;
    }

    public void output(String input, Level runLevel, List<TraceItem> trace){
        trace.add(new TraceItem(this, input, IO.OUTPUT));
        if(runLevel == level && !input.equals("")){
            try {
                Files.write(file.toPath(), input.getBytes());
            }catch(IOException ioe){
                System.err.println(ioe.getMessage());
            }
        }
    }

    //Needs to support possibly reading from lower level to higher.
    public String input(Level runLevel, List<TraceItem> trace){
        if(runLevel == level) {
            try{
                String result = Files.readAllLines(file.toPath()).get(0);
                trace.add(new TraceItem(this, result, IO.INPUT));
                return result;
            }catch(IOException ioe) {
                System.err.println(ioe.getMessage());
            }
        }
        else if(runLevel.higherThan(level)) {
            for (TraceItem t : trace) {
                if (t.getChannel().equals(this) && t.getIoType() == IO.INPUT) {
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
        if(!(o instanceof SMEFile)) return super.equals(o);
        SMEFile f = (SMEFile)o;

        return (f.file.equals(file) && f.level == level);
    }

    @Override
    public int hashCode(){
        return 2*file.hashCode() + 3*level.hashCode();
    }
}
