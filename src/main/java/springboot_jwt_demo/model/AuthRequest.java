// model/AuthRequest.java
package springboot_jwt_demo.model;
import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
