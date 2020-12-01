package gmcc.hxs.service;

import gmcc.hxs.dataobject.Userinfo;

public interface UserInfoService {

    Userinfo findUserInfoByUserId(String userId);

    Userinfo findUserInfoByUsername(String username);

    Userinfo updateUserInfo(Userinfo userinfo);
}
