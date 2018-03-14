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
import com.fs.entity.WxTest;
import com.fs.service.WxTestService;
import com.fs.utils.R;

/***
 * 流水记账-控制器信息
 * @ClassName wxTestController
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年1月30日
 */
@Api(tags={"流水记账控制器"})
@RestController
@RequestMapping("/miniApp/test")
public class WxTestController {

    @Autowired
    private WxTestService wxTestService;

    /**
     * 保存数据
     * @param wxTest 保存实体对象
     * @return 1:保存成功 0:保存失败
     */
    @ApiOperation(" 保存数据 ")
    @PostMapping("/save")
    public R save(WxTest wxTest) {
        if(wxTestService.save(wxTest) > 0) {
             return R.ok();
        }
        return R.error();
    }

    /**
     * 删除数据
     * @param id 主健
     * @return 1:删除成功 0:删除失败
     */
    @ApiOperation(" 根据主Key删除数据 ")
    @DeleteMapping("/del/{id}")
    public R deleteByKey(@PathVariable String id) {
        if(wxTestService.deleteByKey(id) > 0) {
             return R.ok();
        }
        return R.error();
    }

    /**
     * 更新数据
     * @param wxTest 更新实体对象
     * @return 1:更新成功 0:更新失败
     */
    @ApiOperation(" 更新数据 ")
    @PutMapping("/update")
    public R update(WxTest wxTest) {
        if(wxTestService.update(wxTest) > 0) {
             return R.ok();
        }
        return R.error();
    }

    /**
     * 查询数据
     * @param id 主健
     * @return 1条实体对象
     */
    @ApiOperation(" 根据主Key查询数据 ")
    @GetMapping("/queryByKey/{id}")
    public R queryByKey(BootStrapTable bootStrapTable,@PathVariable String id) {
        return R.ok("result",wxTestService.queryByKey(id));
    }

    /**
     * 查询数据
     * @param columnMap 查询条件
     * @return 实体对象
     */
    @ApiOperation(" 多条件查询数据 ")
    @GetMapping("/queryByMap")
    public R queryByMap(BootStrapTable bootStrapTable,@RequestBody Map<String, Object> columnMap) {
        PageHelper.startPage(bootStrapTable.getPageNumber(), bootStrapTable.getPageSize());
        List<WxTest> list = wxTestService.queryByMap(columnMap);  
        PageInfo<WxTest> pageInfo = new PageInfo<WxTest>(list);
        Datagrid datagrid = new Datagrid(pageInfo.getTotal(),pageInfo.getList());
        return R.ok("result",datagrid);
    }

    /**
     * 查询所有
     * @return 实体对象
     */
    @ApiOperation(" 查询所有数据 ")
    @GetMapping("/queryAllList")
    public R queryAllList(BootStrapTable bootStrapTable) {
        PageHelper.startPage(bootStrapTable.getPageNumber(), bootStrapTable.getPageSize());
        List<WxTest> list = wxTestService.queryAllList();  
        PageInfo<WxTest> pageInfo = new PageInfo<WxTest>(list);
        Datagrid datagrid = new Datagrid(pageInfo.getTotal(),pageInfo.getList());
        return R.ok("result",datagrid);
    }
}