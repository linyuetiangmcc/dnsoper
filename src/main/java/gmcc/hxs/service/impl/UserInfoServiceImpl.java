package gmcc.hxs.service.impl;

import gmcc.hxs.dataobject.Userinfo;
import gmcc.hxs.respository.UserInfoRepository;
import gmcc.hxs.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoRepository repository;

    @Override
    public Userinfo findUserInfoByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public Userinfo findUserInfoByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Userinfo updateUserInfo(Userinfo userinfo) {
        return repository.save(userinfo);
    }
}
