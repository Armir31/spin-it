package al.spinit.spinit.dto;

import al.spinit.spinit.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String username;
    private Role role;
}
