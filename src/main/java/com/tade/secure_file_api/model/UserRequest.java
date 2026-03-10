package com.tade.secure_file_api.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/*
* Model for user request data, including email and password
*/
@Data
@Getter
@Setter
public class UserRequest {
    private String email;
    private String password;
}
