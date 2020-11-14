package gmcc.hxs.respository;

import gmcc.hxs.dataobject.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<Userinfo,String>{
    Userinfo findByUserId(String userId);
    Userinfo findByUsername(String username);
}
