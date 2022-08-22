package cn.hy.security.controller.resource;

import cn.hy.security.aspect.SysLog;
import cn.hy.security.common.AjaxResult;
import cn.hy.security.resource.util.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/resource/zh")
@Slf4j
public class WhZhController {
    @Value("${redirect.gongjiangtu.url}")
    private String gjtUrl ;
    @Value("${redirect.secondOA.url}")
    private String secondOAUrl;


    /**
     * 八大类实时监控接口
     * @param id 项目id
     * @return
     */
    @SysLog()
    @RequestMapping(value = "/pushMsgLists/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public AjaxResult pushMsgLists(@PathVariable String id) {
        String url = gjtUrl+"/dataV/pushMsgLists/"+id;
        String result = HttpUtil.getRequest(url,null);
        JSONArray resultArray = JSONArray.parseArray(result);
        return AjaxResult.success(resultArray);
    }

    /**
     * 现场问题统计
     * @param id
     * @return
     */
    @SysLog()
    @RequestMapping(value = "/getProblems/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public AjaxResult getProblems(@PathVariable String id) {
        String url = gjtUrl+"/dataV/getProblems/"+id;
        String result = HttpUtil.getRequest(url,null);
        JSONObject resultObject = JSONObject.parseObject(result);
        return AjaxResult.success(resultObject);
    }

    /**
     * 特种设备塔吊统计
     * @param id
     * @return
     */
    @SysLog()
    @RequestMapping(value = "/towerCrane/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public AjaxResult towerCrane(@PathVariable String id) {
        String url = secondOAUrl+"/hyoa/towerCrane/dataV/getByProjectId";
        HashMap<String,Object> requestMap = new HashMap<>();
        requestMap.put("id",id);
        String result = HttpUtil.getRequest(url,requestMap);
        JSONObject resultObject = JSONObject.parseObject(result);
        return AjaxResult.success(resultObject);
    }

    /**
     * 特种设备施工电梯统计
     * @param id
     * @return
     */
    @SysLog()
    @RequestMapping(value = "/lift/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public AjaxResult lift(@PathVariable String id) {
        String url = secondOAUrl+"/hyoa/lift/dataV/getByProjectId";
        HashMap<String,Object> requestMap = new HashMap<>();
        requestMap.put("id",id);
        String result = HttpUtil.getRequest(url,requestMap);
        JSONObject resultObject = JSONObject.parseObject(result);
        return AjaxResult.success(resultObject);
    }


    /**
     * 特种人员统计
     * @param id
     * @return
     */
    @SysLog()
    @RequestMapping(value = "/special/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public AjaxResult special(@PathVariable String id) {
        String url = secondOAUrl+"/hyoa/special/dataV/getByProjectId";
        HashMap<String,Object> requestMap = new HashMap<>();
        requestMap.put("id",id);
        String result = HttpUtil.getRequest(url,requestMap);
        JSONObject resultObject = JSONObject.parseObject(result);
        return AjaxResult.success(resultObject);
    }
}
