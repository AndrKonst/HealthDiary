package HealthDiary.DataBase.models;

import jakarta.persistence.*;

@Entity
@Table (name = "users")
public class DbUser {

    @Id
    @Column(name = "user_id")
    private long id;

    @Column(name = "is_admin")
    private int isAdmin;

    @Column(name = "state")
    private Integer state;

    @Column(name = "step")
    private Integer step;

    public DbUser(){
    }

    public DbUser(long id, int isAdmin, Integer state) {
        this.id = id;
        this.isAdmin = isAdmin;
        this.state = state;
    }

    //Getters/Setters
    public Long getId() {
        return id;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public Integer getState() {
        return state;
    }

    public Integer getStep() {
        return step;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    public void setStep(Integer step) {
        this.step = step;
    }

    @Override
    public String toString(){
        String res;

        if(this.isAdmin == 1){
            res = "User " +
                  this.id +
                  " (operator)," +
                  " cur_state = " +
                  this.state +
                  ", cur_step = " +
                  this.step;
        }else{
            res = "User " +
                    this.id +
                    " (not operator)," +
                    " cur_state = " +
                    this.state +
                    ", cur_step = " +
                    this.step;
        }

        return res;
    }
}
