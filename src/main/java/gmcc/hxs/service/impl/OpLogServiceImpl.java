package gmcc.hxs.service.impl;

import gmcc.hxs.dataobject.OpLog;
import gmcc.hxs.respository.OpLogRepository;
import gmcc.hxs.service.OpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
@Service
public class OpLogServiceImpl implements OpLogService {

    @Autowired
    private OpLogRepository repository;

    @Override
    public OpLog findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public ArrayList<OpLog> findByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public ArrayList<OpLog> findOpLogsByCreateTimeAfter(Date createTime) {
        return repository.findOpLogsByCreateTimeAfter(createTime);
    }

    @Override
    public ArrayList<OpLog> findOpLogsByCreateTimeAfterAndUserId(Date createTime, String userId) {
        return repository.findOpLogsByCreateTimeAfterAndUserId(createTime,userId);
    }

    @Override
    public OpLog create(OpLog opLog) {
        return repository.save(opLog);
    }
}
