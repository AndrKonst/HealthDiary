package HealthDiary.DataBase.dao;

import HealthDiary.DataBase.models.DbDiary;
import HealthDiary.DataBase.models.DbUser;
import HealthDiary.exceptions.NoDataFound;
import jakarta.persistence.StoredProcedureQuery;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.query.NativeQuery;
import org.jetbrains.annotations.NotNull;

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

    public void closeDiary() {

        StoredProcedureQuery query = this.getSession().createNamedStoredProcedureQuery("close_diary");
        query.setParameter("p_id", this.diary.getId());

        query.execute();
    }

    public List<DbDiary> getDiaryList() {
        List<Object[]> resultList;
        List<DbDiary> diaryList = new ArrayList<>();

        NativeQuery query = this.getSession().createNativeQuery(
                "SELECT *\n" +
                     "FROM diaries\n" +
                    "WHERE start_dt <= now()\n" +
                      "AND (end_dt IS NULL OR end_dt > now())");
        resultList = query.getResultList();

        for (Object[] row : resultList) {diaryList.add(castToDbDiary(row));}

        return diaryList;
    }

    @NotNull
    private DbDiary castToDbDiary(Object[] diaryLine){
        DbDiary diary = new DbDiary();

        diary.setId((int) diaryLine[0]);
        diary.setName((String) diaryLine[1]);
        diary.setStartDt(Date.from((Instant) diaryLine[2]));

        if (diaryLine[3] != null){
            diary.setEndDt(Date.from((Instant) diaryLine[3]));
        }

        return diary;
    }
}
