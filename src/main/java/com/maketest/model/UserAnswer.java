package com.maketest.model;

import javax.persistence.*;

/**
 * Created by Jovana Micic on 24-Oct-16.
 */

@Entity
@Table(name="UserAnswer")
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="user_answer_id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="user_answer_result", referencedColumnName = "result_id", nullable = false)
    private Result userAnswerResult;

    @ManyToOne
    @JoinColumn(name="user_answer", referencedColumnName = "answer_id", nullable = false)
    private Answer userAnswer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Result getUserAnswerResult() {
        return userAnswerResult;
    }

    public void setUserAnswerResult(Result userAnswerResult) {
        this.userAnswerResult = userAnswerResult;
    }

    public Answer getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(Answer userAnswer) {
        this.userAnswer = userAnswer;
    }

    @Override
    public String toString() {
        return "UserAnswer{" +
                "id=" + id +
                ", userAnswerResult=" + userAnswerResult +
                ", userAnswer=" + userAnswer +
                '}';
    }
}
