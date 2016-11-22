package com.maketest.model;

import com.maketest.repository.TestRepository;

/**
 * Created by Jovana Micic on 22-Nov-16.
 */
public class TestRequest {
    private int id;
    private String testName;
    private String description;
    private String category;

    public TestRequest(){}

    public TestRequest(int id, String testName, String description, String category) {
        this.id = id;
        this.testName = testName;
        this.description = description;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
