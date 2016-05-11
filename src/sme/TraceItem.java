package sme;

public class TraceItem {
    private SMEIO channel;
    private String value;
    private IO ioType;

    public SMEIO getChannel(){return channel;}
    public String getValue(){return value;}
    public IO getIoType(){return ioType;}

    public TraceItem(SMEIO _channel, String _value, IO _ioType){
        channel = _channel;
        value = _value;
        ioType = _ioType;
    }
}