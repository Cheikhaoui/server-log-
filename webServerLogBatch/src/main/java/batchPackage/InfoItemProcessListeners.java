package batchPackage;

import logLine.LogLine;
import logLine.LogLineFiltred;

import javax.batch.api.chunk.listener.ItemProcessListener;
import javax.batch.api.listener.JobListener;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Level;
import java.util.logging.Logger;

@Dependent
@Named("infoItemProcessListeners")
public class  InfoItemProcessListeners implements ItemProcessListener {


    private static final Logger logger = Logger.getLogger("InfoItemProcessListener");

    @Override
    public void beforeProcess(Object o) throws Exception {
        logger.log(Level.INFO,"before process MyItem {0}",(LogLine)o);
    }

    @Override
    public void afterProcess(Object o, Object o1) throws Exception {
        logger.log(Level.INFO,String.format("after process MyItem {0} , MyItem2 {1}",o,o1));
    }

    @Override
    public void onProcessError(Object o, Exception e) throws Exception {
        logger.log(Level.INFO,"myItem on error MyItem {0}",(LogLine)o);
    }
}
