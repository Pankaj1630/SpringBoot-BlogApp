package com.example.BlogApplication.Payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer id;
    @NotEmpty
    @Size(min = 4,message ="User name must be min 4 character")
    private String name;
    @Email(message = "Your email is invalid")
    private String email;
    @NotEmpty
    @Size(min = 4,max = 10, message = "Password must be in min 4 char and max 10 char")
    private String password;
    @NotEmpty
    private String about;
}
