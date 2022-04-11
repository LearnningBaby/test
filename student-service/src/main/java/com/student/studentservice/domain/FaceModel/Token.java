package com.student.studentservice.domain.FaceModel;

import java.io.Serializable;

public class Token implements Serializable {
    private String face_token;

    public String getFace_token() {
        return face_token;
    }

    public void setFace_token(String face_token) {
        this.face_token = face_token;
    }

    @Override
    public String toString() {
        return "Token{" +
                "face_token='" + face_token + '\'' +
                '}';
    }
}
