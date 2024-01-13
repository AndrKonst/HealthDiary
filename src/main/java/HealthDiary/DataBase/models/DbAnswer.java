package HealthDiary.DataBase.models;

import jakarta.persistence.*;

@Entity
@Table(name = "answers")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "add_answer",
                query = "SELECT healthdiary.add_answer(:answer_text, :question_id, :pos)",
                resultClass = Integer.class),
        @NamedNativeQuery(
                name = "last_answer_pos",
                query = "SELECT coalesce(max(pos), 0)\n" +
                        "FROM question_answers\n" +
                        "WHERE question_id = :question_id",
                resultClass = Integer.class)
})
public class DbAnswer {

    @Id
    @Column(name = "answer_id")
    private int answerId;

    @Column(name = "name")
    private String text;

    public DbAnswer(){};

    public DbAnswer(String questAnswText){
        this.text = questAnswText;
    };

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "DbAnswer{" +
                "answerId=" + answerId +
                ", text='" + text + '\'' +
                '}';
    }
}
