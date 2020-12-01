package gmcc.hxs.respository;

import gmcc.hxs.dataobject.CommentCity;
import gmcc.hxs.dataobject.OpLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Date;

public interface OpLogRepository extends JpaRepository<OpLog,Integer> {
    OpLog findById(Integer id);
    ArrayList<OpLog> findByUserId(String userId);
    ArrayList<OpLog> findOpLogsByCreateTimeAfter(Date createTime);
    ArrayList<OpLog> findOpLogsByCreateTimeAfterAndUserId(Date createTime,String userId);
}
