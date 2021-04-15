package logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import static java.rmi.server.LogStream.log;

public class Approved<setStatus> {
    public static void main(String[] args) {
        int status;
        int programID;
        int approvedBy;
        Date approvedDate;
        //behøves vistnok ik??
    }


    public static void createApproved(int status, int programID, int approvedBy) {
        //return int
        throw new UnsupportedOperationException();

    }

    public int getStatus(){
        //return int
        throw new UnsupportedOperationException();
    }

    public void setStatus(){
        // return this.status
        throw new UnsupportedOperationException();
    }

    public int getApprovedBy() {
        //return int
        throw new UnsupportedOperationException();
    }

    public Date getApprovedDate(){
        // return Date
        throw new UnsupportedOperationException();
    }
}
