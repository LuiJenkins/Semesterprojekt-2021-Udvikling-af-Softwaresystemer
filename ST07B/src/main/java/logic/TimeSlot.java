package logic;
/*
Class that holds the timeslots where the programs are shown.
This is not yet implemented.
*/

public class TimeSlot {
    private int from;
    private int to;
    private String channel;
    private int programId;

    public TimeSlot(int from, int to, String channel, int programId) {
        this.from = from;
        this.to = to;
        this.channel = channel;
        this.programId = programId;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }
}