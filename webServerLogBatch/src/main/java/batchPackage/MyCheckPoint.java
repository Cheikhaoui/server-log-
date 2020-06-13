package batchPackage;

import java.io.Serializable;

public class MyCheckPoint implements Serializable {

    private Long lineNum = 0l;
    public void increase(){this.lineNum ++ ;}
    public long getLineNum(){return lineNum;}

}
