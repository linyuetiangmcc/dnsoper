package gmcc.hxs.service.impl;

import gmcc.hxs.dataobject.Userinfo;
import gmcc.hxs.respository.UserInfoRepository;
import gmcc.hxs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
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
}
