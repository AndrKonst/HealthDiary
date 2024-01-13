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

    public DbQuestion getQuestion() {
        return question;
    }

    public void findById(int questionId) {

        question = this.getSession().get(DbQuestion.class, questionId);
        if (question == null){
            throw new NoDataFound("No question with id \"" + questionId + "\"");
        }
    }

    public void findByPos(int diaryId, int pos) {
        NativeQuery query = this.getSession().getNamedNativeQuery("Question_findByPos");
        query.setParameter("diary_id", diaryId);
        query.setParameter("pos", pos);

        question = (DbQuestion) query.getSingleResult();
        if (question == null){
            throw new NoDataFound("No question with pos \"" + pos + "\" for diary \"" + diaryId + "\"");
        }
    }

    public void addQuestion(int diaryId, int pos) {

        NativeQuery query = this.getSession().getNamedNativeQuery("add_question");
        query.setParameter("question_text", this.question.getText());
        query.setParameter("diary_id", diaryId);
        query.setParameter("pos", pos);

        this.question.setQuestionId((Integer) query.getSingleResult());
    }

    public int getLastQuestPos(int diaryId){
        NativeQuery query = this.getSession().getNamedNativeQuery("last_quest_pos");
        query.setParameter("diary_id", diaryId);

        return (int) query.getSingleResult();
    }
}
