package myBeans;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.*;
import java.util.Properties;

@SessionScoped
@Named
public class MyBean implements Serializable {
    private long exID;
    private JobOperator jobOperator;

    public String inputLog() throws Exception{
        String input = "";
        String fileName = "log1.txt";
        ClassLoader classLoader  = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = bufferedReader.readLine();
        while (line != null){
            input += line + "\n";
            line  = bufferedReader.readLine();
        }
        return input;
    }

    public String startJob(){
        jobOperator = BatchRuntime.getJobOperator();
        Properties properties = new Properties();
        properties.setProperty("log_file_name","log1.txt");
        exID = jobOperator.start("webServerLog",properties);
        return "jobStarted";
    }

    public String getStatus(){
        return jobOperator.getJobExecution(exID).getBatchStatus().toString();
    }

    public boolean isCompleted(){
        return getStatus().equalsIgnoreCase("COMPLETED");
    }

    public String showResult() throws Exception{
        if(isCompleted()) {
            BufferedReader breader;
            breader = new BufferedReader(new FileReader("result.txt"));
            String[] results = breader.readLine().split(", ");
            return "number of all visit with our favorit browser "+results[0]+"\n" +
                    " number of all visit to buy page with our favorite browser" + results[1] + "\n"+
                    " result "+results[2];
        }
        return "";
    }
}
