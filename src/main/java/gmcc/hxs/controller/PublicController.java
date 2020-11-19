package gmcc.hxs.controller;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import gmcc.hxs.dataobject.CommentCity;
import gmcc.hxs.service.CommentCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;

@Controller
@RequestMapping("/public")
//@CrossOrigin
public class PublicController {
    private String[] citys = {"广州","深圳","佛山","汕头", "东莞","惠州","江门","湛江","郑州",
            "阳江","肇庆","云浮","茂名","梅州","中山","珠海","清远","韶关","河源","汕尾","揭阳","潮州"};

    @Autowired
    private CommentCityService commentCityService;

    @RequestMapping("/citys")
    @ResponseBody
    public ArrayList<String> getAllCity(){
        ArrayList<String> result = new ArrayList<>();
        Collections.addAll(result, citys);
        return result;
    }

    @RequestMapping("/sgw")
    @ResponseBody
    public ArrayList<CommentCity> getSgwByCity(@RequestParam(name = "city") String city){
        ArrayList<CommentCity> result = commentCityService.findCommentCitybyCity(city);
        return result;
    }

}
