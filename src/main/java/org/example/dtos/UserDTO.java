package org.example.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.User;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private String username;
    private String password;
    private String email;
    private Set<String> roles;

    public UserDTO(String username, String password,Set<String> roles, String email){
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.email = email;
    }

    public UserDTO(String username, Set<String> roles, String email) {
        this.username = username;
        this.roles = roles;
        this.email = email;
    }

    public UserDTO(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles = user.getRolesAsString();
        this.email = user.getEmail();
    }



}
