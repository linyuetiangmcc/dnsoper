package gmcc.hxs.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import gmcc.hxs.VO.NaptrRecord;
import gmcc.hxs.VO.NaptrRecordOp;
import gmcc.hxs.VO.ResultNaptr;
import gmcc.hxs.VO.ResultOp;
import gmcc.hxs.dataobject.OpLog;
import gmcc.hxs.service.OpLogService;
import gmcc.hxs.utils.HttpClientUtil;
import gmcc.hxs.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/sgw")
//@CrossOrigin
public class SgwController {
    @Autowired
    private OpLogService opLogService;

    @Value(value = "${gmcc.hxs.dnsip}")
    private String ip;

    @GetMapping("/check")
    public String check(){
        return "sgw/check";
    }

    @PostMapping("/gelihuige")
    @ResponseBody
    public ArrayList<NaptrRecordOp> gelihuige(@RequestBody ArrayList<NaptrRecord> geliBody, @RequestParam String disable){
        Map<String, String> httpHeadMap = getHttpHeader();
        String result = "";
        ArrayList<NaptrRecordOp> naptrRecordOpArrayList = new ArrayList<>();
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
                    naptrRecordOpArrayList.add(resultOp.getResult());
            }
            else{
                if (resultOp != null && !resultOp.getResult().isDisable())
                    naptrRecordOpArrayList.add(resultOp.getResult());
            }
        }
        //操作记录入库
        String str= JSON.toJSON(naptrRecordOpArrayList).toString();
        OpLog opLog = new OpLog();
        opLog.setCreateTime(new Date());
        opLog.setNeList(str);
        opLog.setUserId("1");
        if(disable.equals("true"))
            opLog.setOperation("disable");
        else
            opLog.setOperation("enable");
        opLog.setOpId(KeyUtil.genUniqueKey());

        OpLog resutl = opLogService.create(opLog);
        System.out.println(resutl);


        /*String str= JSON.toJSON(naptrRecordOpArrayList).toString();
        System.out.println(str);
        ArrayList<NaptrRecordOp> naptrRecordOpArrayList2 = new ArrayList<>();
        naptrRecordOpArrayList2 = (ArrayList<NaptrRecordOp>) JSONObject.parseArray(str,NaptrRecordOp.class);
        System.out.println(naptrRecordOpArrayList2.size());*/
        return naptrRecordOpArrayList;
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
