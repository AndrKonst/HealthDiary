package HealthDiary.DataBase.dao;

import HealthDiary.DataBase.models.DbDiary;
import HealthDiary.exceptions.NoDataFound;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.StoredProcedureQuery;
import org.hibernate.query.NativeQuery;

public class DiaryDao extends BaseDao{

    private DbDiary diary;

    public DiaryDao() {
        super();
    }

    public DiaryDao(DbDiary diary) {
        super();
        this.diary = diary;
    }

    public DbDiary findById(Long diary_id) {

        DbDiary diary = this.getSession().get(DbDiary.class, diary_id);
        if (diary == null){
            throw new NoDataFound("No diary with id \"" + diary_id + "\"");
        }

        return diary;
    }

    public DbDiary findByName(String diary_name) {

        NativeQuery query = this.getSession().getNamedNativeQuery("Diary_findByName");
        query.setParameter("diary_name", diary_name);

        DbDiary diary = (DbDiary) query.getSingleResult();
        if (diary == null){
            throw new NoDataFound("No diary with name \"" + diary_name + "\"");
        }

        return diary;
    }

    public void createDiary() {

        NativeQuery query = this.getSession().getNamedNativeQuery("create_diary");
        query.setParameter("diary_name", this.diary.getName());

        this.diary.setId((Long) query.getSingleResult());
    }
}
