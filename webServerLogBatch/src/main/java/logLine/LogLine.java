package logLine;

import java.io.Serializable;

public class LogLine implements Serializable {
    private  String dateTime;
    protected   String ipAdd;
    private  String browser;
    protected   String url;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getIpAdd() {
        return ipAdd;
    }

    public void setIpAdd(String ipAdd) {
        this.ipAdd = ipAdd;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LogLine(String dateTime, String ipAdd, String browser, String url) {
        this.dateTime = dateTime;
        this.ipAdd = ipAdd;
        this.browser = browser;
        this.url = url;
    }

    public LogLine(String ipAdd, String url) {
        this.ipAdd = ipAdd;
        this.url = url;
    }

    public LogLine(String logLine){
        String[] donnee = logLine.split(", ");
        this.dateTime = donnee[0];
        this.ipAdd = donnee[1];
        this.browser = donnee[2];
        this.url = donnee[3];
    }

    @Override
    public String toString() {
        return "LogLine{" +
                "dateTime='" + dateTime + '\'' +
                ", ipAdd='" + ipAdd + '\'' +
                ", browser='" + browser + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
