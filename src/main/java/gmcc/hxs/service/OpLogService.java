package gmcc.hxs.service;

import gmcc.hxs.dataobject.OpLog;

import java.util.ArrayList;
import java.util.Date;

public interface OpLogService {
    OpLog findById(Integer id);
    ArrayList<OpLog> findByUserId(String userId);
    ArrayList<OpLog> findOpLogsByCreateTimeAfter(Date createTime);
    OpLog create(OpLog opLog);
}
