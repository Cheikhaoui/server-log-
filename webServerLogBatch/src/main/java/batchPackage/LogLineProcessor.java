package batchPackage;

import logLine.LogLine;
import logLine.LogLineFiltred;

import javax.batch.api.chunk.ItemProcessor;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Dependent
@Named("logLineProcessor")
public class LogLineProcessor implements ItemProcessor {

    private int nbrowser = 0;
    @Inject
    private JobContext jobContext;
    List<String> myBrowsers;

    public LogLineProcessor() {
    }

    @Override
    public Object processItem(Object o) throws Exception {
        if (nbrowser == 0) {
            nbrowser = Integer.parseInt(jobContext.getProperties().getProperty("num_browsers"));
            myBrowsers = new ArrayList<>(nbrowser);
            for (int i = 1; i <= nbrowser; i++) {
                myBrowsers.add(jobContext.getProperties()
                        .getProperty("browser_"+i));
            }
        }
        LogLine logLine = (LogLine) o;
        List<LogLineFiltred> logLinesFiltered = myBrowsers.stream().filter(b -> b.equalsIgnoreCase(logLine.getBrowser()))
                .map(b -> new LogLineFiltred(logLine.getIpAdd(), logLine.getUrl())).collect(Collectors.toList());
        if (logLinesFiltered.size() > 0) {
            return logLinesFiltered.get(0);
        }
        return null;
    }
}
