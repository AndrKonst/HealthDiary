package HealthDiary.DataBase.dao;

import HealthDiary.DataBase.models.DbDiary;
import HealthDiary.DataBase.models.DbDiaryFilling;
import HealthDiary.DataBase.models.DbUser;
import HealthDiary.exceptions.NoDataFound;
import org.hibernate.query.NativeQuery;

public class DiaryFillingDao extends BaseDao {
    private DbDiaryFilling df;

    public DiaryFillingDao() {
        super();
    }
    public DiaryFillingDao(DbUser user, Boolean diaryCreationFl) {
        super();
        this.df = new DbDiaryFilling(user, diaryCreationFl);
    }

    public DiaryFillingDao(DbDiaryFilling df) {
        super();
        this.df = df;
    }

    public DbDiaryFilling getDf() {
        return df;
    }

    public void findById(Long df_id) {

        DbDiaryFilling df = this.getSession().get(DbDiaryFilling.class, df_id);
        if (df == null){
            throw new NoDataFound("No diary_filling with id \"" + df_id + "\"");
        }

        this.df = df;
    }

    public void findLastDf4User() {

        Long user_id = this.df.getUser().getId();

        NativeQuery query = this.getSession().getNamedNativeQuery("df_findLastDf4User");
        query.setParameter("user_id", user_id);
        query.setParameter("creation_fl", this.df.getCreationFl());

        DbDiaryFilling df = (DbDiaryFilling) query.getSingleResult();
        if (df == null){
            throw new NoDataFound("No df for user \"" + user_id + "\"");
        }

        this.df = df;
    }
    public void setFilling() {

        NativeQuery query = this.getSession().getNamedNativeQuery("create_filling");
        query.setParameter("user_id", this.df.getUser().getId());
        query.setParameter("diary_creation_fl", this.df.getCreationFl());
        Long id = (Long) query.getSingleResult();

        // После добавления получим данные о записи из базы
        findById(id);
    }
}
