package batchPackage;

import javax.batch.api.Batchlet;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

@Dependent
@Named("mobileBachelet")
public class MobileBachelet implements Batchlet {

    @Inject
    private JobContext jobContext;
    BufferedReader bufferedReader;
    int pageVisit = 0;
    int allVisit = 0;


    @Override
    public String process() throws Exception {
        String fileOutput = jobContext.getProperties().getProperty("filtered_file_name");
        String buyPage = jobContext.getProperties().getProperty("buy_page");
        bufferedReader = new BufferedReader(new FileReader(fileOutput));
        String line = bufferedReader.readLine();
        while (line != null){
            if(line.split(", ")[1].equals(buyPage)){
                pageVisit ++;
            }
            allVisit ++;
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        String fileName = jobContext.getProperties().getProperty("out_file_name");
        /*
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName,false));
        double percent = (pageVisit/allVisit)*100;
        bufferedWriter.write(String.format("%d, %d, %02f",pageVisit,allVisit,percent));
        *
         */
        /* Write the result */
        // we have to use try () because try with resources will close automaticaly our whriter after finish
        try (BufferedWriter bwriter =
                     new BufferedWriter(new FileWriter(fileName, false))) {
            double percent = 100.0 * ((1.0 * pageVisit) /(1.0*allVisit));
            bwriter.write(String.format("%d, %d, %.02f", pageVisit,
                    allVisit, percent));
        }
        return "COMPLETED";
    }

    @Override
    public void stop() throws Exception {
        bufferedReader.close();
    }
}
