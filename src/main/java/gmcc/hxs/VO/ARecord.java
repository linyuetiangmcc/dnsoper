package gmcc.hxs.VO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ARecord {
    private String _ref;
    private boolean disable;
    private String name;
    private String ipv4addr;
    private String shared_record_group;
}
