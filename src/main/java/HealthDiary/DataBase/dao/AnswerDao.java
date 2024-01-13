package HealthDiary.DataBase.dao;

import HealthDiary.DataBase.models.DbAnswer;
import HealthDiary.DataBase.models.DbQuestion;
import HealthDiary.exceptions.NoDataFound;
import org.hibernate.query.NativeQuery;

public class AnswerDao extends BaseDao{
    private DbAnswer answer;

    public AnswerDao(){
        super();
    }
    public AnswerDao(String answText){
        super();

        this.answer = new DbAnswer(answText);
    }

    public DbAnswer getAnswer() {
        return answer;
    }

    public void findById(int answerId) {

        answer = this.getSession().get(DbAnswer.class, answerId);
        if (answer == null){
            throw new NoDataFound("No answer with id \"" + answerId + "\"");
        }
    }

    public void addAnswer(int questionId, int pos){
        NativeQuery query = this.getSession().getNamedNativeQuery("add_answer");
        query.setParameter("answer_text", answer.getText());
        query.setParameter("question_id", questionId);
        query.setParameter("pos", pos);

        answer.setAnswerId((Integer) query.getSingleResult());
    }

    public int getLastAnswerPos(int questionId){
        NativeQuery query = this.getSession().getNamedNativeQuery("last_answer_pos");
        query.setParameter("question_id", questionId);

        return (int) query.getSingleResult();
    }
}
