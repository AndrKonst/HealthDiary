package HealthDiary.DataBase.models;

import jakarta.persistence.*;

@Entity
@Table (name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    private String id;

    @Column(name = "is_oper")
    private int isOper;

    public User(){
    }

    public User(String id, int isOper) {
        this.id = id;
        this.isOper = isOper;
    }

    //Getters/Setters
    public String getId() {
        return id;
    }

    public int getIsOper() {
        return isOper;
    }

    public void setIsOper(int isOper) {
        this.isOper = isOper;
    }

    @Override
    public String toString(){
        String res;

        if(this.isOper == 1){
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
