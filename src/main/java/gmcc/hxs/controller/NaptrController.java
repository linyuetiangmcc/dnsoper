package gmcc.hxs.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import gmcc.hxs.VO.NaptrRecord;
import gmcc.hxs.VO.RecordOp;
import gmcc.hxs.VO.ResultNaptr;
import gmcc.hxs.VO.ResultOp;
import gmcc.hxs.constant.CurrentUserConstants;
import gmcc.hxs.dataobject.OpLog;
import gmcc.hxs.dataobject.Userinfo;
import gmcc.hxs.service.OpLogService;
import gmcc.hxs.utils.HttpClientUtil;
import gmcc.hxs.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/naptr")
@Slf4j
public class NaptrController {
    @Autowired
    private OpLogService opLogService;

    @Value(value = "${gmcc.hxs.dnsip}")
    private String ip;

    @GetMapping("/check")
    public String check(){
        return "naptr/check";
    }

    @GetMapping("/geli")
    public String geli(){
        return "naptr/geli";
    }

    @GetMapping("/huige")
    public String huige(){
        return "naptr/huige";
    }

    @PostMapping("/gelihuige")
    @ResponseBody
    public ArrayList<RecordOp> gelihuige(@RequestBody ArrayList<NaptrRecord> geliBody, @RequestParam String disable, HttpServletRequest request, @RequestParam String sgw){
        Map<String, String> httpHeadMap = getHttpHeader();
        String result = "";
        ArrayList<RecordOp> recordOpArrayList = new ArrayList<>();
        //int count = 0;
        for (NaptrRecord naptrRecord:geliBody
             ) {
            String url = "https://" + ip + "/wapi/v2.6.1/" + naptrRecord.get_ref() + "?_return_as_object=1&disable="+ disable+ "&_return_fields=name,disable,comment";
            result = HttpClientUtil.doPut(url, httpHeadMap, "utf-8");
            //count++;
            ResultOp resultOp = JSONObject.parseObject(result, ResultOp.class);
            //System.out.println(result);
            if(disable.equals("true")) {
                if (resultOp != null && resultOp.getResult().isDisable())
                    recordOpArrayList.add(resultOp.getResult());
            }
            else{
                if (resultOp != null && !resultOp.getResult().isDisable())
                    recordOpArrayList.add(resultOp.getResult());
            }
        }
        //操作记录入库
        String str= JSON.toJSON(recordOpArrayList).toString();
        OpLog opLog = new OpLog();
        opLog.setCreateTime(new Date());
        opLog.setRecords(str);
        String userid = ((Userinfo)request.getAttribute(CurrentUserConstants.CURRENT_USER)).getUserId();
        opLog.setUserId(userid);
        if(disable.equals("true"))
            opLog.setOperation("disable");
        else
            opLog.setOperation("enable");
        opLog.setOpId(KeyUtil.genUniqueKey());
        opLog.setRecordType("NAPTR");
        opLog.setComment(sgw);

        OpLog opresult = opLogService.create(opLog);
        if(opresult != null){
            log.info("用户" + userid + "将" + sgw + "的Naptr记录设置为" + disable +"操作写日志入库成功！！");
        }

        /*拆装LOG record记录代码如下
        String str= JSON.toJSON(naptrRecordOpArrayList).toString();
        System.out.println(str);
        ArrayList<NaptrRecordOp> naptrRecordOpArrayList2 = new ArrayList<>();
        naptrRecordOpArrayList2 = (ArrayList<NaptrRecordOp>) JSONObject.parseArray(str,NaptrRecordOp.class);
        System.out.println(naptrRecordOpArrayList2.size());*/
        return recordOpArrayList;
    }
    @GetMapping("/status")
    @ResponseBody
    public ArrayList<NaptrRecord> status(@RequestParam(name = "sgw") String sgw ){
        Map<String, String> httpHeadMap = getHttpHeader();
        String url = "https://"+ ip +"/wapi/v2.6.1/record:naptr?" +
                "_paging=1&_return_as_object=1&_return_fields=name,replacement,services,disable,view&_max_results=100&comment=" + sgw;
        String result = HttpClientUtil.doGet(url,httpHeadMap,"utf-8");
        ResultNaptr resultNaptr = JSONObject.parseObject(result, ResultNaptr.class);
        ArrayList<NaptrRecord> returnResult = resultNaptr.getResult();
        Collections.sort(returnResult, new Comparator<NaptrRecord>() {
            public int compare(NaptrRecord arg0, NaptrRecord arg1) {
                return arg0.getName().compareTo(arg1.getName());
            }
        });
        return resultNaptr.getResult();
    }

    public Map<String, String> getHttpHeader(){
        Map<String, String> httpHeadMap = new HashMap<String, String>();
        httpHeadMap.put("Authorization","Basic bGlueXVldGlhbjpHbWNjQDEyMzQ=");
        httpHeadMap.put("Content-Type", "application/json");
        return httpHeadMap;
    }
}
