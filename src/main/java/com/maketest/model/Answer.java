<<<<<<< HEAD
package com.maketest.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jovana Micic on 24-Oct-16.
 */

@Entity
@Table(name="answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="answer_id", unique = true, nullable = false)
    private Integer id;

    @Column (name="answer_text", unique = false, nullable = false)
    private String answerText;

    @ManyToOne
    @JoinColumn(name="question_answers", referencedColumnName = "question_id", nullable = false)
    private Question questionAnswers;

    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY, mappedBy = "userAnswer")
    private Set<UserAnswer> userAnswers = new HashSet<UserAnswer>();

    public Answer(){};

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Question getQuestionAnswers() {
        return questionAnswers;
    }

    public void setQuestionAnswers(Question questionAnswers) {
        this.questionAnswers = questionAnswers;
    }

    public Set<UserAnswer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(Set<UserAnswer> userAnswers) {
        this.userAnswers = userAnswers;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", answerText='" + answerText + '\'' +
                ", questionAnswers=" + questionAnswers +
                ", userAnswers=" + userAnswers +
                '}';
    }
}
=======
package com.maketest.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jovana Micic on 24-Oct-16.
 */

@Entity
@Table(name="answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="answer_id", unique = true, nullable = false)
    private Integer id;

    @Column (name="answer_text", unique = false, nullable = false)
    private String answerText;

    @ManyToOne
    @JoinColumn(name="question_answers", referencedColumnName = "question_id", nullable = false)
    private Question questionAnswers;

    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY, mappedBy = "userAnswer")
    private Set<UserAnswer> userAnswers = new HashSet<UserAnswer>();

    public Answer(){};

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Question getQuestionAnswers() {
        return questionAnswers;
    }

    public void setQuestionAnswers(Question questionAnswers) {
        this.questionAnswers = questionAnswers;
    }

    public Set<UserAnswer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(Set<UserAnswer> userAnswers) {
        this.userAnswers = userAnswers;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", answerText='" + answerText;
    }
}
>>>>>>> 3e8b4f6b46df55a25ab9a29a2fa68ddb13b105a5
