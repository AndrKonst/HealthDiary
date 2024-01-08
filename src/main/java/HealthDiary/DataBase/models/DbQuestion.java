package HealthDiary.DataBase.models;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
@NamedNativeQueries({
        @NamedNativeQuery(
                name="add_question",
                query="SELECT healthdiary.add_question(:question_text, :diary_id, :pos)",
                resultClass= Integer.class)
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
