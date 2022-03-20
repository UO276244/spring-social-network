package com.uniovi.sdipractica134.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
public class Log implements Comparable<Log>{

    @Id
    @GeneratedValue
    private Long id;
    private String logType;
    private Timestamp time;
    private String description;

    public Log(){}


    public Log(String logType, Timestamp time, String description) {
        this.logType = logType;
        this.time=time;
        this.description = description;
    }


    public String toString(){
        return "Log " + this.logType + " - At time: " + this.time + " - Description: " + this.description;
    }


    public String getLogType() {
        return logType;
    }

    public LocalDateTime stampToDate(){
        return this.time.toLocalDateTime();
    }

    public Timestamp getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    @Override
    public int compareTo(Log o) {
        return time.compareTo(o.time);
    }
}

