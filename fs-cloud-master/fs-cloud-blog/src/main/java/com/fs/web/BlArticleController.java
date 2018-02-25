package com.fs.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fs.entity.BlArticle;
import com.fs.entity.BootStrapTable;
import com.fs.entity.Datagrid;
import com.fs.service.BlArticleService;
import com.fs.utils.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/***
 * 博客文章-控制器信息
 * @ClassName blArticleController
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年2月13日
 */
@Api(tags={"博客文章控制器"})
@RestController
@RequestMapping("/article")
public class BlArticleController {

    @Autowired
    private BlArticleService blArticleService;

    /**
     * 保存数据
     * @param blArticle 保存实体对象
     * @return 1:保存成功 0:保存失败
     */
    @ApiOperation("保存更新博客数据 ")
    @PostMapping("/save")
    public R save(BlArticle blArticle) {
        return R.ok(blArticleService.saveOrUpdate(blArticle));
    }
    
    /**
     * 删除数据
     * @param id 主健
     * @return 1:删除成功 0:删除失败
     */
    @ApiOperation("根据主Key删除数据 ")
    @DeleteMapping("/del/{id}")
    public R delete(@PathVariable String id) {
        if(blArticleService.delete(id) > 0) {
             return R.ok();
        }
        return R.error();
    }

    /**
     * 更新数据
     * @param blArticle 更新实体对象
     * @return 1:更新成功 0:更新失败
     */
    @ApiOperation("更新数据 ")
    @PutMapping("/update")
    public R update(BlArticle blArticle) {
        if(blArticleService.update(blArticle) > 0) {
             return R.ok();
        }
        return R.error();
    }
    
    
    /**
     * 更新数据
     * @param blArticle 更新实体对象
     * @return 1:更新成功 0:更新失败
     */
    @ApiOperation("更新数据 ")
    @PutMapping("/toDust/{id}")
    public R update(@PathVariable String id) {
        if(blArticleService.toDust(id) > 0) {
             return R.ok();
        }
        return R.error();
    }

    /**
     * 查询数据
     * @param id 主健
     * @return 1条实体对象
     */
    @ApiOperation("根据主Key查询数据 ")
    @GetMapping("/queryByKey/{id}")
    public R queryByKey(BootStrapTable bootStrapTable,@PathVariable String id) {
        return R.ok("result",blArticleService.queryByKey(id));
    }
    
    /**
     * 查询数据
     * @param columnMap 查询条件
     * @return 实体对象
     */
    @ApiOperation("多条件查询数据 ") 	
    @GetMapping("/queryByMap")
    public R queryByMap(BootStrapTable bootStrapTable,@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(value = "flag", defaultValue = "1", required = false) String flag) {
        PageHelper.startPage(pageNumber, pageSize);
        Map<String,Object> queryMap = bootStrapTable.getParamsMap();
        queryMap.put("flag", flag);
        List<BlArticle> list = blArticleService.queryByMap(queryMap);
        PageInfo<BlArticle> pageInfo = new PageInfo<BlArticle>(list);
        Datagrid datagrid = new Datagrid(pageInfo.getTotal(),pageInfo.getList());
        return R.ok("result",datagrid);
    }

    /**
     * 查询所有
     * @return 实体对象
     */
    @ApiOperation("查询所有数据 ")
    @GetMapping("/queryAllList")
    public R queryAllList(BootStrapTable bootStrapTable) {
        PageHelper.startPage(bootStrapTable.getPageNumber(), bootStrapTable.getPageSize());
        List<BlArticle> list = blArticleService.queryAllList();  
        PageInfo<BlArticle> pageInfo = new PageInfo<BlArticle>(list);
        Datagrid datagrid = new Datagrid(pageInfo.getTotal(),pageInfo.getList());
        return R.ok("result",datagrid);
    }
}