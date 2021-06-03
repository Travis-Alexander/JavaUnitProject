package com.company;

import java.io.Serializable;

public class Time implements Serializable {
    private int hours = 0;
    private int minutes = 0;
    private int seconds = 0;

    public Time() {
    }

    public Time(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;


    }

    public static Time parseTime(String input) {
        String[] times = input.split(":", 0);

        int hour = 0;
        int minute = 0;
        int second = 0;

        switch (times.length) {
            case 1 -> {
                hour = Integer.parseInt(times[0]);
                minute = 0;
                second = 0;
            }
            case 2 -> {
                hour = Integer.parseInt(times[0]);
                minute = Integer.parseInt(times[1]);
                second = 0;
            }
            case 3 -> {
                hour = Integer.parseInt(times[0]);
                minute = Integer.parseInt(times[1]);
                second = Integer.parseInt(times[2]);
            }
        }
        return new Time(hour, minute, second);
    }

    public String getFormattedTime() {
        String displayHour = Integer.toString(hours);
        String displayMinute = Integer.toString(minutes);
        String displaySecond = Integer.toString(seconds);
        if (hours < 10) {
            displayHour = "0" + displayHour;
        }
        if (minutes< 10) {
            displayMinute = "0" + displayMinute;
        }

        if (seconds < 10){
            displaySecond = "0" + displaySecond;
        }

        return displayHour + ":" + displayMinute + ":" + displaySecond;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }


}
