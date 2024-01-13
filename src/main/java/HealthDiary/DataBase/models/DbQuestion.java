package HealthDiary.DataBase.models;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
@NamedNativeQueries({
        @NamedNativeQuery(
                name="add_question",
                query="SELECT healthdiary.add_question(:question_text, :diary_id, :pos)",
                resultClass= Integer.class),
        @NamedNativeQuery(
                name="last_quest_pos",
                query="select coalesce(max(pos), 0)\n" +
                      "  from healthdiary.diary_question\n" +
                      " where diary_id = :diary_id\n" +
                      "   and start_dt <= now()\n" +
                      "   and (end_dt is null or end_dt >= now())",
                resultClass= Integer.class),
        @NamedNativeQuery(
                name = "Question_findByPos",
                query = "select q.*\n" +
                        "  from healthdiary.diary_question dq\n" +
                        "  join healthdiary.questions q on dq.question_id = q.question_id\n" +
                        " where dq.diary_id = :diary_id\n" +
                        "   and dq.pos = :pos\n" +
                        "   and dq.start_dt <= now()\n" +
                        "   and (dq.end_dt is null or dq.end_dt > now())",
                resultClass = DbQuestion.class)
})
public class DbQuestion {
    @Id
    @Column(name = "question_id")
    private int questionId;
    @Column(name = "text")
    private String text;
    @Column(name = "repeatable")
    private boolean repeatable;

    public DbQuestion(){};

    public DbQuestion(String text){
        this.text = text;
        this.repeatable = false;
    };

    public int getQuestionId() {
        return questionId;
    }

    public String getText() {
        return text;
    }

    public boolean getRepeatable() {
        return repeatable;
    }

    public void setRepeatable(boolean repeatable) {
        this.repeatable = repeatable;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "DbQuestion{" +
                "questionId=" + questionId +
                ", text='" + text + '\'' +
                ", repeatable=" + repeatable +
                '}';
    }
}
