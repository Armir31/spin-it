package al.spinit.spinit.dto;

import al.spinit.spinit.entity.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private Role role;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("created_at")
    private Long createdAt;
    @JsonProperty("updated_at")
    private Long updatedAt;
}
