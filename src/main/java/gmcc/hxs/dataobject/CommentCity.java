package gmcc.hxs.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "commentcity")
public class CommentCity {
    @Id
    @GeneratedValue
    private Integer id;
    private String comment;
    private String city;
}
