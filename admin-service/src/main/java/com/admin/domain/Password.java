package com.admin.domain;

import lombok.Data;

@Data
public class Password {

    private String password;
    private String newPassword;
    private String newPasswordTwo;
    private Long stu_numb;
    private String email;
    private String nick_photo;
    private String checkPass;
    private Long teaNumb;
    private Long adminNumb;

}
