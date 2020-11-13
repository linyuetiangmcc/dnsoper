package gmcc.hxs.dataobject;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Userinfo {
    @Id
    private String userId;

    private String username;

    private String password;

}
