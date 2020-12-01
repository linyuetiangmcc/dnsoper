package gmcc.hxs.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "userinfo")
public class Userinfo {
    @Id
    private String userId;

    private String username;

    private String password;

    private String email;

    private String tel;

}
