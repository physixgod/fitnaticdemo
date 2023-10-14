/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author toufa
 */
public class Competition {
    private int id;
    private String name;
    private Date startDate;
    private Date endDate;
    private String location;
    private String description;
    private String sportType;
    private String competitionCategory;
    private double entryFee;
    private int maxParticipants;
    private String status;
   
    private String prizes; 
    public Competition(int id, String name, Date startDate, Date endDate, String location, String description,
                             String sportType, String competitionCategory, double entryFee, int maxParticipants,
                             String status,String prizes) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.description = description;
        this.sportType = sportType;
        this.competitionCategory = competitionCategory;
        this.entryFee = entryFee;
        this.maxParticipants = maxParticipants;
        this.status = status;
        
        this.prizes = prizes;
        
    }
    public Competition( String name, Date startDate, Date endDate, String location, String description,
                             String sportType, String competitionCategory, double entryFee, int maxParticipants,
                             String status, String prizes) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.description = description;
        this.sportType = sportType;
        this.competitionCategory = competitionCategory;
        this.entryFee = entryFee;
        this.maxParticipants = maxParticipants;
        this.status = status;
        
        this.prizes = prizes;
        
    }
    public Competition(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sportType) {
        this.sportType = sportType;
    }

    public String getCompetitionCategory() {
        return competitionCategory;
    }

    public void setCompetitionCategory(String competitionCategory) {
        this.competitionCategory = competitionCategory;
    }

    public double getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(double entryFee) {
        this.entryFee = entryFee;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrizes() {
        return prizes;
    }

    public void setPrizes(String prizes) {
        this.prizes = prizes;
    }

}
