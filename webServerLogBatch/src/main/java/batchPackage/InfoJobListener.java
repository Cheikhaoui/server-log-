package batchPackage;

import javax.batch.api.listener.JobListener;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Level;
import java.util.logging.Logger;

@Dependent
@Named("infoJobListener")
public class InfoJobListener implements JobListener {


    private static final Logger logger = Logger.getLogger("InfoJobListener");;

    @Override
    public void beforeJob() throws Exception {
        logger.log(Level.INFO,"the job starting");
    }

    @Override
    public void afterJob() throws Exception {
        logger.log(Level.INFO,"the job is finished");
    }
}
