package com.ortega.tshombo.models;

import java.util.ArrayList;

public class DataModel {

    private String room;
    private String subject;
    private String hoursMask;
    private String start;
    private String end;
    private String classes;

    public DataModel(String room, String subject, String hoursMask, String start, String end, String classes) {
        this.room = room;
        this.subject = subject;
        this.hoursMask = hoursMask;
        this.start = start;
        this.end = end;
        this.classes = classes;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHoursMask() {
        return hoursMask;
    }

    public void setHoursMask(String hoursMask) {
        this.hoursMask = hoursMask;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }
}
