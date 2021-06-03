package com.company;

import java.io.Serializable;

public class StreamEntry implements Serializable {


    //How much did they earn
     private String streamDay = "";
     private Time streamTime = Time.parseTime("00:00:00");
     private Double earnings = 0.0;

    public StreamEntry(){
    }

     public StreamEntry(String dayOfStream, String timeOfStream, Double streamEarnings){
        streamDay = dayOfStream;
        streamTime = Time.parseTime(timeOfStream);
        earnings = streamEarnings;
    }
    public String getStreamDay() {
        return streamDay;
    }

    public void setStreamDay(String streamDay) {
        this.streamDay = streamDay;
    }

    public Time getStreamTime() {
        return streamTime;
    }

    public void setStreamTime(Time streamTime) {
        this.streamTime = streamTime;
    }

    public Double getEarnings() {
        return earnings;
    }

    public void setEarnings(Double earnings) {
        this.earnings = earnings;
    }

    //How Much Do they Need to take out?




    public String toString(){
        return "Stream info: \nDay Streamed: " + streamDay + "\n Time of Stream: " + streamTime + "\nAmount earned from stream: " + earnings;
    }
}
