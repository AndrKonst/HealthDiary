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
    private int state;

    public DbUser(){
    }

    public DbUser(long id, int isAdmin, int state) {
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

    public int getState() {
        return state;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString(){
        String res;

        if(this.isAdmin == 1){
            res = "User " +
                  this.id +
                  " (operator)," +
                  " cur_state = " +
                  this.state;
        }else{
            res = "User " +
                    this.id +
                    " (not operator)," +
                    " cur_state = " +
                    this.state;
        }

        return res;
    }
}
