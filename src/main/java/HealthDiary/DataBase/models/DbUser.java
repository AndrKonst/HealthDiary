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

    public DbUser(){
    }

    public DbUser(long id, int isAdmin) {
        this.id = id;
        this.isAdmin = isAdmin;
    }

    //Getters/Setters
    public Long getId() {
        return id;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString(){
        String res;

        if(this.isAdmin == 1){
            res = "User " +
                  this.id +
                  " (operator)";
        }else{
            res = "User " +
                    this.id +
                    " (not operator)";
        }

        return res;
    }
}
