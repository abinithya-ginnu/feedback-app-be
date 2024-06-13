package com.ict.feedbackappbe.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

enum OU {
    Academic,
    Corporate,
    Retail,
    Government
}

enum TrainingType {
    LTT,
    MDT,
    Microskill
}

enum Status {
    Upcoming,
    Ongoing,
    Completed,
    Cancelled,
    Hold
}

@Entity
@Table(name = "courses")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonProperty("course_name")
    private String courseName;
    private String description;
    @JsonProperty("organizational_unit")
    private OU organizationUnit;
    @JsonProperty("training_type")
    private TrainingType trainingType;
    @JsonProperty("start_date")
    private LocalDateTime startDate;
    @JsonProperty("end_date")
    private LocalDateTime endDate;
    private int trainer;
    private Status status;
    @JsonProperty("feedback_score")
    private int feedbackScore;
    @JsonProperty("created_date")
    private LocalDateTime createdDate;
    @JsonProperty("modified_date")
    private LocalDateTime modifiedDate;

    public CourseEntity() {
    }

    public CourseEntity(int id, String courseName, String description, OU organizationUnit, TrainingType trainingType, LocalDateTime startDate, LocalDateTime endDate, int trainer, Status status, int feedbackScore, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
        this.organizationUnit = organizationUnit;
        this.trainingType = trainingType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.trainer = trainer;
        this.status = status;
        this.feedbackScore = feedbackScore;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OU getOrganizationUnit() {
        return organizationUnit;
    }

    public void setOrganizationUnit(OU organizationUnit) {
        this.organizationUnit = organizationUnit;
    }

    public TrainingType getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(TrainingType trainingType) {
        this.trainingType = trainingType;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public int getTrainer() {
        return trainer;
    }

    public void setTrainer(int trainer) {
        this.trainer = trainer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getFeedbackScore() {
        return feedbackScore;
    }

    public void setFeedbackScore(int feedbackScore) {
        this.feedbackScore = feedbackScore;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
