package gmcc.hxs.VO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaptrRecord {
    private String _ref;
    private boolean disable;
    private String name;
    private String replacement;
    private String services;
    private String view;
}
