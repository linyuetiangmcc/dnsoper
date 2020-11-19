package gmcc.hxs.service;


import gmcc.hxs.dataobject.CommentCity;

import java.util.ArrayList;

public interface CommentCityService {
    ArrayList<CommentCity> findCommentCitybyCity(String city);
}
