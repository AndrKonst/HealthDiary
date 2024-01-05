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
                        "   and dt <= (select max(dt) from healthdiary.diary_filling where user_id = :user_id and creation_fl = :creation_fl)\n" +
                        "   and creation_fl = :creation_fl",
                resultClass = DbDiary.class),
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
    private Boolean creationFl;

    public DbDiaryFilling(){
    }

    public DbDiaryFilling(DbUser user, Boolean creationFl) {
        this.user = user;
        this.creationFl = creationFl;
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

    public Boolean getCreationFl() {
        return creationFl;
    }

    public void setCreationFl(Boolean creationFl) {
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
