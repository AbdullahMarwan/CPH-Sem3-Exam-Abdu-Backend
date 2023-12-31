package dtos;

import entities.Role;
import entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class UserDTO {
    private String user_name;
    private String user_pass;
    private List<String> roles = new ArrayList<>();

    public UserDTO(User user) {
        this.user_name = user.getUserName();
        this.user_pass = user.getUserPass();
        for (Role role : user.getRoleList()) {
            this.roles.add(role.getRoleName());
        }
    }
}