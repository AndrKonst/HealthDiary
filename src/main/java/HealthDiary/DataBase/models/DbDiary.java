package HealthDiary.DataBase.models;

import jakarta.persistence.*;

import java.util.Date;

import static jakarta.persistence.ParameterMode.IN;

@Entity
@Table (name = "diaries")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Diary_findByName",
                query = "select *\n" +
                        "  from healthdiary.diaries\n" +
                        " where name = :diary_name\n" +
                        "   and start_dt <= now()\n" +
                        "   and (end_dt is null or end_dt > now())",
                resultClass = DbDiary.class),
        @NamedNativeQuery(
            name="create_diary",
            query="SELECT healthdiary.create_diary(:diary_name, :user_id)",
            resultClass= Integer.class)}
)
public class DbDiary {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "start_dt")
    private Date startDt;

    @Column(name = "end_dt")
    private Date endDt;

    public DbDiary(){}

    public DbDiary(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDt(Date startDt) {
        this.startDt = startDt;
    }

    @Override
    public String toString() {
        return "DbDiary{" +
                "id = " + id +
                ", name = " + name +
                '}';
    }
}
