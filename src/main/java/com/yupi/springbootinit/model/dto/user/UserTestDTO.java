package com.yupi.springbootinit.model.dto.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserTestDTO implements Serializable {
    private String firstName;

    private String lastName;

    private static final long serialVersionUID = 1L;
}
