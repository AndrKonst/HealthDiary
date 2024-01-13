package HealthDiary.DataBase.dao;

import HealthDiary.DataBase.models.DbDiary;
import HealthDiary.DataBase.models.DbUser;
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

    public DbDiary findById(int diaryId) {

        DbDiary diary = this.getSession().get(DbDiary.class, diaryId);
        if (diary == null){
            throw new NoDataFound("No diary with id \"" + diaryId + "\"");
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

    public void createDiary(DbUser user) {

        NativeQuery query = this.getSession().getNamedNativeQuery("create_diary");
        query.setParameter("diary_name", this.diary.getName());
        query.setParameter("user_id", user.getId());

        this.diary.setId((int) query.getSingleResult());
    }
}
