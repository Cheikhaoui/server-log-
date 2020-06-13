package batchPackage;

import logLine.LogLine;

import javax.batch.api.chunk.ItemReader;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;

@Dependent
@Named("logLineReader")
public class LogLineReader implements ItemReader {
    private BufferedReader bufferedReader;
    private MyCheckPoint myCheckPoint;
    @Inject
    private JobContext jobContext;
    @Override
    public void open(Serializable serializable) throws Exception {
        if(myCheckPoint == null){
            myCheckPoint = new MyCheckPoint();
        }else {
            myCheckPoint = (MyCheckPoint) serializable;
        }
        String fileName = jobContext.getProperties().getProperty("log_file_name");
        ClassLoader classLoader  = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        for (long i = 0 ; i < myCheckPoint.getLineNum();i++){
            bufferedReader.readLine();
        }
    }

    @Override
    public void close() throws Exception {
        bufferedReader.close();
    }

    @Override
    public Object readItem() throws Exception {
        String entry = bufferedReader.readLine();
        if(entry != null){
            myCheckPoint.increase();
            return new LogLine(entry);
        };
        return null;
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return myCheckPoint;
    }
}
