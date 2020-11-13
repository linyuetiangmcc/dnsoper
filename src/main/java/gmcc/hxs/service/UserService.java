package gmcc.hxs.service;

import gmcc.hxs.dataobject.Userinfo;

public interface UserService {

    Userinfo findUserInfoByUserId(String userId);

    Userinfo findUserInfoByUsername(String username);
}
