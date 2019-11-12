package com.example.user.javacoretraining.classes;

public class ClassesBlockTime {
    private int hours;
    private int minutes;
    private int seconds;

    public ClassesBlockTime(int hours, int minutes, int seconds) {
        if (hours > 24) {
            throw new IllegalArgumentException();
        } else {
            this.hours = hours;
        }
        if (minutes > 60) {
            throw new IllegalArgumentException();
        } else {
            this.minutes = minutes;
        }
        if (seconds > 60) {
            throw new IllegalArgumentException();
        } else {
            this.seconds = seconds;
        }
    }

    public void setHours(int hours) {
        if (hours > 24) {
            throw new IllegalArgumentException();
        } else {
            this.hours = hours;
        }
    }

    public void setMinutes(int minutes) {
        if (minutes > 60) {
            throw new IllegalArgumentException();
        } else {
            this.minutes = minutes;
        }
    }

    public void setSeconds(int seconds) {
        if (seconds > 60) {
            throw new IllegalArgumentException();
        } else {
            this.seconds = seconds;
        }
    }

    public void changeHoursBy(int hours) {
        this.hours += hours;
        while (this.hours > 24) {
            this.hours -= 24;
        }
    }

    public void changeMinutesBy(int minutes) {
        int hours = 0;
        this.minutes = minutes;
        while (this.minutes > 60) {
            this.minutes -= 60;
            hours++;
        }
        changeHoursBy(hours);
    }

    public void changeSecondsBy(int seconds) {
        int minutes = 0;
        this.seconds = seconds;
        while (this.seconds > 60) {
            this.seconds -= 60;
            minutes++;
        }
        changeMinutesBy(minutes);
    }
}
