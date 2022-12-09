package com.example.englishquestion.model;

import java.util.List;

public class Question {
    private String questIndex;

    private String questTitle;

    private String answer1;

    private String answer2;

    private String answer3;

    private String answer4;

    private int correctAnswer;

    private String atCatID;

    private int questImg;

    public Question() {
    }

    public Question(String questIndex, String questTitle, String answer1, String answer2, String answer3, String answer4, int correctAnswer, String atCatID, int questImg) {
        this.questIndex = questIndex;
        this.questTitle = questTitle;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
        this.atCatID = atCatID;
        this.questImg = questImg;
    }

    public String getQuestIndex() {
        return questIndex;
    }

    public void setQuestIndex(String questIndex) {
        this.questIndex = questIndex;
    }

    public String getQuestTitle() {
        return questTitle;
    }

    public void setQuestTitle(String questTitle) {
        this.questTitle = questTitle;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getAtCatID() {
        return atCatID;
    }

    public void setAtCatID(String catID) {
        this.atCatID = atCatID;
    }

    public int getQuestImg() {
        return questImg;
    }

    public void setQuestImg(int questImg) {
        this.questImg = questImg;
    }
}
