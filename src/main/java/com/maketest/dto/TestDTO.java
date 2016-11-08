package com.maketest.dto;

import com.maketest.model.Test;

/**
 * Created by Jovana Micic on 08-Nov-16.
 */
public class TestDTO {
    private int id;
    private String testName;
    private String description;
    private String category;

    public TestDTO(Test t){
        this(t.getId(),t.getTestName(), t.getDescription(), t.getCategory());
    }

    public TestDTO(int id, String testName, String description, String category) {
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
