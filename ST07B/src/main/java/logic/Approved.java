package logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Approved<setStatus> {
    public int status;
    public int programID;
    public String approvedBy;
    public Date approvedDate;


    public static void createApproved(int status, String approvedBy) {
         status = 0;
         approvedBy = "NULL";
    }

    public int getStatus(){
        return status;
    }

    public void setStatus(int newStatus){
    status = newStatus;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public Date getApprovedDate(){
        return approvedDate;
    }
}
