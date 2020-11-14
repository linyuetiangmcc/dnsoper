package gmcc.hxs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/sgw")
//@CrossOrigin
public class SgwController {
    @GetMapping("/check")
    public String check(){
        return "sgw/check";
    }

    @GetMapping("/status")
    @ResponseBody
    public String status(@RequestParam (name = "sgw") String sgw ){
        System.out.println(sgw);
        return new String(sgw+ "success");
    }
}
