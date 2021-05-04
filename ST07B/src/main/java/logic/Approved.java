package logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Approved<setStatus> {
    public int status;
    public int programID;
    public Date approvedDate;

    public Approved(int programID,int status,Date approvedDate) {
        this.status = status;
        this.programID = programID;
        this.approvedDate = approvedDate;
    }
    public static void createApproved(int status) {
         status = 0;
    }

    public int getStatus(){
        return status;
    }

    public void setStatus(int newStatus){
    status = newStatus;
    }


    public Date getApprovedDate(){
        return approvedDate;
    }
    public void approve() {
        status = 2;
        approvedDate = new Date();
    }
    public void deny() {
        status = 0;
    }
}
