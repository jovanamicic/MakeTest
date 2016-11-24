package com.maketest.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jovana Micic on 24-Oct-16.
 */
@Entity
@Table(name="test")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="test_id", unique = true, nullable = false)
    private Integer id;

    @Column (name="test_name", unique = false, nullable = false)
    private String testName;

    @Column (name="description", unique = false, nullable = true)
    private String description;

    @Column (name = "category", unique = false, nullable = false)
    private String category;

    @Column (name = "creating_date", unique = false, nullable = false)
    private Date creatingDate;

    @ManyToOne
    @JoinColumn(name="user", referencedColumnName = "user_id", nullable = false)
    private User userTests;

    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY, mappedBy = "testQuestions")
    private Set<Question> questions = new HashSet<Question>();

    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY, mappedBy = "testResult")
    private Set<Result> results = new HashSet<Result>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreatingDate() {
        return creatingDate;
    }

    public void setCreatingDate(Date creatingDate) {
        this.creatingDate = creatingDate;
    }

    public User getUserTests() {
        return userTests;
    }

    public void setUserTests(User userTests) {
        this.userTests = userTests;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Set<Result> getResults() {
        return results;
    }

    public void setResults(Set<Result> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", testName='" + testName + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", creatingDate=" + creatingDate +
                ", userTests=" + userTests +
                ", questions=" + questions +
                ", results=" + results +
                '}';
    }
}
