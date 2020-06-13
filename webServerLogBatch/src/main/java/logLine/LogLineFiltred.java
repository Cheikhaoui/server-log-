package logLine;

public class LogLineFiltred extends LogLine{

    public LogLineFiltred(String ipAdd, String url) {
        super(ipAdd, url);
    }

    @Override
    public String toString() {
        return ipAdd +", "+url;
    }
}
