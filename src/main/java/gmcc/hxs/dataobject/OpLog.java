package gmcc.hxs.dataobject;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "oplog")
public class OpLog {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "opid")
    private String opId;
    @Column(name = "userid")
    private String userId;
    private String operation;
    @Column(name = "records")
    private String records;
    private Date createTime;
    private Date updateTime;
    private String recordType;
    private String comment;
}
