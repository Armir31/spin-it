package al.spinit.spinit.dto;

import lombok.Data;

@Data
public class JwtResponse {
    private String token;
    private String username;
    private String role;
}
