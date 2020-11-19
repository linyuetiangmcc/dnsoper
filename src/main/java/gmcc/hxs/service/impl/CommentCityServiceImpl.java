package gmcc.hxs.service.impl;

import gmcc.hxs.dataobject.CommentCity;
import gmcc.hxs.respository.CommentCityRepository;
import gmcc.hxs.service.CommentCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CommentCityServiceImpl implements CommentCityService {
    @Autowired
    CommentCityRepository repository;

    @Override
    public ArrayList<CommentCity> findCommentCitybyCity(String city) {
        return repository.findAllByCity(city);
    }
}
