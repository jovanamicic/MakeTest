package com.maketest.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jovana Micic on 24-Oct-16.
 */

@Entity
@Table(name="result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="result_id", unique = true, nullable = false)
    private Integer id;

    @Column (name="result_date", unique = false, nullable = false)
    private Date resultDate;

    @Column (name = "percentage", unique = false, nullable = true)
    private Integer percentage;

    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY, mappedBy = "userAnswerResult")
    private Set<UserAnswer> userAnswerResult = new HashSet<UserAnswer>();

    @ManyToOne
    @JoinColumn(name="test_result", referencedColumnName = "test_id", nullable = true)
    private Test testResult;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getResultDate() {
        return resultDate;
    }

    public void setResultDate(Date resultDate) {
        this.resultDate = resultDate;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public Set<UserAnswer> getUserAnswerResult() {
        return userAnswerResult;
    }

    public void setUserAnswerResult(Set<UserAnswer> userAnswerResult) {
        this.userAnswerResult = userAnswerResult;
    }

    public Test getTestResult() {
        return testResult;
    }

    public void setTestResult(Test testResult) {
        this.testResult = testResult;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", resultDate=" + resultDate +
                ", percentage=" + percentage +
                ", userAnswerResult=" + userAnswerResult +
                ", testResult=" + testResult +
                '}';
    }
}
