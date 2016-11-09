package com.maketest.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/*
 * Created by Jovana Micic on 24-Oct-16.
 */
@Entity
@Table(name="question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="question_id", unique = true, nullable = false)
    private Integer id;

    @Column (name="question_text", unique = false, nullable = false)
    private String questionText;

    @Column (name = "correct_answer", unique = false, nullable = false)
    private String correctAnswer;

    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY, mappedBy = "questionAnswers")
    private Set<Answer> answers = new HashSet<Answer>();

    @ManyToOne
    @JoinColumn(name="test_questions", referencedColumnName = "test_id", nullable = true)
    private Test testQuestions;

    public Question(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public Test getTestQuestions() {
        return testQuestions;
    }

    public void setTestQuestions(Test testQuestions) {
        this.testQuestions = testQuestions;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionText='" + questionText + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", answers=" + answers +
                ", testQuestions=" + testQuestions +
                '}';
    }
}
