package com.fs.web;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.fs.entity.BootStrapTable;
import com.fs.entity.Datagrid;
import com.fs.entity.BlTopic;
import com.fs.service.BlTopicService;
import com.fs.utils.R;

/***
 * 博客主题-控制器信息
 * @ClassName blTopicController
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年2月13日
 */
@Api(tags={"博客主题控制器"})
@RestController
@RequestMapping("/topic")
public class BlTopicController {

    @Autowired
    private BlTopicService blTopicService;

    /**
     * 保存数据
     * @param blTopic 保存实体对象
     * @return 1:保存成功 0:保存失败
     */
    @ApiOperation("保存数据 ")
    @PostMapping("/save")
    public R save(BlTopic blTopic) {
        if(blTopicService.save(blTopic) > 0) {
             return R.ok();
        }
        return R.error();
    }

    /**
     * 删除数据
     * @param id 主健
     * @return 1:删除成功 0:删除失败
     */
    @ApiOperation("根据主Key删除数据 ")
    @DeleteMapping("/del/{id}")
    public R delete(@PathVariable String id) {
        if(blTopicService.delete(id) > 0) {
             return R.ok();
        }
        return R.error();
    }

    /**
     * 更新数据
     * @param blTopic 更新实体对象
     * @return 1:更新成功 0:更新失败
     */
    @ApiOperation("更新数据 ")
    @PutMapping("/update")
    public R update(BlTopic blTopic) {
        if(blTopicService.update(blTopic) > 0) {
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
        return R.ok("result",blTopicService.queryByKey(id));
    }

    /**
     * 查询数据
     * @param columnMap 查询条件
     * @return 实体对象
     */
    @ApiOperation("多条件查询数据 ")
    @GetMapping("/queryByMap")
    public R queryByMap(BootStrapTable bootStrapTable,@RequestBody Map<String, Object> columnMap) {
        PageHelper.startPage(bootStrapTable.getPageNumber(), bootStrapTable.getPageSize());
        List<BlTopic> list = blTopicService.queryByMap(columnMap);  
        PageInfo<BlTopic> pageInfo = new PageInfo<BlTopic>(list);
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
        List<BlTopic> list = blTopicService.queryAllList();  
        PageInfo<BlTopic> pageInfo = new PageInfo<BlTopic>(list);
        Datagrid datagrid = new Datagrid(pageInfo.getTotal(),pageInfo.getList());
        return R.ok("result",datagrid);
    }
}