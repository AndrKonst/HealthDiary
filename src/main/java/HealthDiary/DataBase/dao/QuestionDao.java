package HealthDiary.DataBase.dao;

import HealthDiary.DataBase.models.DbDiary;
import HealthDiary.DataBase.models.DbQuestion;
import HealthDiary.DataBase.models.DbUser;
import HealthDiary.exceptions.NoDataFound;
import org.hibernate.query.NativeQuery;

public class QuestionDao extends BaseDao{

    private DbQuestion question;

    public QuestionDao() {
        super();
    }

    public QuestionDao(DbQuestion question) {
        super();
        this.question = question;
    }

    public DbQuestion findById(int questionId) {

        DbQuestion question = this.getSession().get(DbQuestion.class, questionId);
        if (question == null){
            throw new NoDataFound("No question with id \"" + questionId + "\"");
        }

        return question;
    }

    public void addQuestion(int diaryId, int pos) {

        NativeQuery query = this.getSession().getNamedNativeQuery("add_question");
        query.setParameter("question_text", this.question.getText());
        query.setParameter("diary_id", diaryId);
        query.setParameter("pos", pos);

        this.question.setQuestionId((Integer) query.getSingleResult());
    }
}
