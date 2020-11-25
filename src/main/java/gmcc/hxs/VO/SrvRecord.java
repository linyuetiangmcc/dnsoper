package gmcc.hxs.VO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SrvRecord {
    private String _ref;
    private boolean disable;
    private String name;
    private int priority;
    private String shared_record_group;
    private String target;
}
