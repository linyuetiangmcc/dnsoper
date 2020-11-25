package gmcc.hxs.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import gmcc.hxs.VO.*;
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
@RequestMapping("/srv")
@Slf4j
public class SrvController {
    @Autowired
    private OpLogService opLogService;

    @Value(value = "${gmcc.hxs.dnsip}")
    private String ip;

    @GetMapping("/check")
    public String check(){
        return "srv/check";
    }

    @GetMapping("/geli")
    public String geli(){
        return "srv/geli";
    }

    @GetMapping("/huige")
    public String huige(){
        return "srv/huige";
    }

    @PostMapping("/gelihuige")
    @ResponseBody
    public ArrayList<RecordOp> gelihuige(@RequestBody ArrayList<SrvRecord> geliBody, @RequestParam String disable, HttpServletRequest request, @RequestParam String sgw){
        Map<String, String> httpHeadMap = getHttpHeader();
        String result = "";
        ArrayList<RecordOp> recordOpArrayList = new ArrayList<>();
        //int count = 0;
        for (SrvRecord srvRecord:geliBody
             ) {
            String url = "https://" + ip + "/wapi/v2.6.1/" + srvRecord.get_ref() + "?_return_as_object=1&disable="+ disable+ "&_return_fields=name,disable,comment";
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
        opLog.setRecordType("SRV");
        opLog.setComment(sgw);

        OpLog opresult = opLogService.create(opLog);
        if(opresult != null){
            log.info("用户" + userid + "将" + sgw + "的Srv记录设置为" + disable +"操作写日志入库成功！！");
        }
        return recordOpArrayList;
    }
    @GetMapping("/status")
    @ResponseBody
    public ArrayList<SrvRecord> status(@RequestParam(name = "sgw") String sgw ){
        Map<String, String> httpHeadMap = getHttpHeader();
        String url = "https://"+ ip +"/wapi/v2.6.1/sharedrecord:srv?" +
                "_paging=1&_return_as_object=1&_return_fields=name,target,disable,priority,shared_record_group&_max_results=100&comment=" + sgw;
        String result = HttpClientUtil.doGet(url,httpHeadMap,"utf-8");
        ResultSrv resultSrv = JSONObject.parseObject(result, ResultSrv.class);
        ArrayList<SrvRecord> returnResult = resultSrv.getResult();
        Collections.sort(returnResult, new Comparator<SrvRecord>() {
            public int compare(SrvRecord arg0, SrvRecord arg1) {
                return arg0.getName().compareTo(arg1.getName());
            }
        });
        return resultSrv.getResult();
    }

    public Map<String, String> getHttpHeader(){
        Map<String, String> httpHeadMap = new HashMap<String, String>();
        httpHeadMap.put("Authorization","Basic bGlueXVldGlhbjpHbWNjQDEyMzQ=");
        httpHeadMap.put("Content-Type", "application/json");
        return httpHeadMap;
    }
}
