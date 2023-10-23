/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author toufa
 */
public class Inscription {
    private int id;
    private String useremail;
    private String competition;
    private String evaluation;   

    public Inscription(int id, String useremail, String competition, String evaluation) {
        this.id = id;
        this.useremail = useremail;
        this.competition = competition;
        this.evaluation = evaluation;
    }

    public Inscription(String useremail, String competition, String evaluation) {
        this.useremail = useremail;
        this.competition = competition;
        this.evaluation = evaluation;
    }

    public Inscription() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }
    
}
