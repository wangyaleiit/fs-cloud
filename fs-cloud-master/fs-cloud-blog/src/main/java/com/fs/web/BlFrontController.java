package com.fs.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fs.entity.BlArticle;
import com.fs.entity.BlTopic;
import com.fs.entity.BootStrapTable;
import com.fs.entity.Datagrid;
import com.fs.service.BlArticleService;
import com.fs.service.BlTopicService;
import com.fs.utils.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/front")
public class BlFrontController {

    @Autowired
    private BlTopicService blTopicService;
    
    @Autowired
    private BlArticleService blArticleService;
    
    @GetMapping("/queryTopic")
    public R queryTopic() {
        List<BlTopic> list = blTopicService.queryAllList();  
        return R.ok("result",list);
    }
    
    @GetMapping("/queryArticle")
    public R queryArticle(BootStrapTable bootStrapTable,@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(value = "flag", defaultValue = "1", required = false)String flag,
			@RequestParam(value = "topic", defaultValue = "", required = false) String topic) {
        PageHelper.startPage(pageNumber, pageSize);
        Map<String,Object> queryMap = bootStrapTable.getParamsMap();
        queryMap.put("flag", flag);
        queryMap.put("topic", topic);
        List<BlArticle> list = blArticleService.queryByMap(queryMap);
        PageInfo<BlArticle> pageInfo = new PageInfo<BlArticle>(list);
        Datagrid datagrid = new Datagrid(pageInfo.getTotal(),pageInfo.getList());
        return R.ok("result",datagrid);
    }
    
    /**
     * 时间轴
     * @param bootStrapTable
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping("/queryTimeLine")
    public R queryTimeLine(BootStrapTable bootStrapTable,@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Map<String,String>> timeLine = blArticleService.queryTimeLine();
        PageInfo<Map<String,String>> pageInfo = new PageInfo<Map<String,String>>(timeLine);
        Datagrid datagrid = new Datagrid(pageInfo.getTotal(),pageInfo.getList());
        return R.ok("result",datagrid);
    }
    
    
    @GetMapping("/queryByKey/{id}")
    public R queryByKey(BootStrapTable bootStrapTable,@PathVariable String id) {
    	BlArticle blArticle = blArticleService.queryByKey(id);
    	BlArticle preArticle = blArticleService.queryPerInfo(blArticle.getArtIndex());
    	BlArticle nextArticle = blArticleService.queryNextInfo(blArticle.getArtIndex()); 	
    	Map<String,BlArticle> dataMap = new HashMap<String,BlArticle>();
    	dataMap.put("art", blArticle);
    	dataMap.put("preArt", preArticle);
    	dataMap.put("nextArt", nextArticle);
        return R.ok("result",dataMap);
    }
}