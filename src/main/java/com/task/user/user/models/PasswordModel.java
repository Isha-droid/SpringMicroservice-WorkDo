package com.task.user.user.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class PasswordModel {

    private String email;
    private String oldPassword;
    private String newPassword;
}