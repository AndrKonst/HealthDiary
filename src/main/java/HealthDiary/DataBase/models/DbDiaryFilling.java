package HealthDiary.DataBase.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "diary_filling")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "df_findLastDf4User",
                query = "select *\n" +
                        "  from healthdiary.diary_filling\n" +
                        " where user_id = :user_id\n" +
                        "   and dt = (select max(dt) from healthdiary.diary_filling where user_id = :user_id)",
                resultClass = DbDiaryFilling.class),
        @NamedNativeQuery(
                name="create_filling",
                query="SELECT healthdiary.create_filling(:user_id, :diary_creation_fl)",
                resultClass= Long.class)}
)
public class DbDiaryFilling {
    @Id
    @Column(name = "id")
    private long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private DbUser user;
    @Column(name = "dt")
    private Date dt;
    @Column(name = "creation_fl")
    private Integer creationFl;

    public DbDiaryFilling(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DbUser getUser() {
        return user;
    }

    public void setUser(DbUser user) {
        this.user = user;
    }

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public Integer getCreationFl() {
        return creationFl;
    }

    public void setCreationFl(Integer creationFl) {
        this.creationFl = creationFl;
    }

    @Override
    public String toString() {
        return "dbDiaryFilling{" +
                "id=" + id +
                ", user=" + user +
                ", dt=" + dt +
                ", creationFl=" + creationFl +
                '}';
    }
}
