package batchPackage;

import logLine.LogLineFiltred;

import javax.batch.api.chunk.ItemWriter;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.List;

@Dependent
@Named("logFilteredLineWriter")
public class LogFilteredLineWriter implements ItemWriter {
    @Inject
    private JobContext jobContext;
    private BufferedWriter bufferedWriter;
    @Override
    public void writeItems(List<Object> list) throws Exception {
        for (Object logLineFiltred: list) {
            LogLineFiltred logLineFiltred1 = (LogLineFiltred) logLineFiltred;
            bufferedWriter.write(logLineFiltred1.toString());
            bufferedWriter.newLine();
        }
    }

    @Override
    public void close() throws Exception {
    bufferedWriter.close();
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return  new MyCheckPoint();
    }

    @Override
    public void open(Serializable serializable) throws Exception {
    String fileName = jobContext.getProperties().getProperty("filtered_file_name");
    if(serializable != null){
        bufferedWriter = new BufferedWriter(new FileWriter(fileName,true));
    }else {
        bufferedWriter = new BufferedWriter(new FileWriter(fileName,false));
    }
    }
}
