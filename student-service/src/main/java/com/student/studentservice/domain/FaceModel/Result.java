package com.student.studentservice.domain.FaceModel;

import java.io.Serializable;
import java.util.List;

public class Result implements Serializable {
    private float score;
    private List<Token> face_list;

    public List<Token> getFace_list() {
        return face_list;
    }

    public void setFace_list(List<Token> face_list) {
        this.face_list = face_list;
    }

    @Override
    public String toString() {
        return "Result{" +
                "score=" + score +
                ", face_list=" + face_list +
                '}';
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }


}
