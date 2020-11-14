package gmcc.hxs.controller;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
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
    private String[] citys = {"广州","深圳","佛山","中山","珠海","清远","韶关","汕头","河源","汕尾","揭阳","潮州",
            "东莞","惠州","江门","阳江","肇庆","云浮","湛江","茂名","梅州"};

    @RequestMapping("/citys")
    @ResponseBody
    public ArrayList<String> getAllCity(){
        ArrayList<String> result = new ArrayList<>();
        Collections.addAll(result, citys);
        return result;
    }

    @RequestMapping("/sgw")
    @ResponseBody
    public ArrayList<String> getSgwByCity(@RequestParam(name = "city") String city){
        ArrayList<String> result = new ArrayList<>();
        if(city.equals("广州")){
            result.add("gzsaegw1");
            result.add("gzsaegw2");
        }
        return result;
    }

}
