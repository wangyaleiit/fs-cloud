package com.fs.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fs.entity.BlRole;
import com.fs.entity.BlUser;
import com.fs.entity.BootStrapTable;
import com.fs.entity.Datagrid;
import com.fs.service.BlUserService;
import com.fs.utils.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/***
 * 博客用户管理-控制器信息
 * @ClassName blUserController
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年2月21日
 */
@Api(tags={"博客用户管理控制器"})
@RestController
@RequestMapping("/user")
public class BlUserController {

    @Autowired
    private BlUserService blUserService;

    /**
     * 保存数据
     * @param blUser 保存实体对象
     * @return 1:保存成功 0:保存失败
     */
    @ApiOperation("保存数据 ")
    @PostMapping("/save")
    public R save(BlUser blUser) {
        if(blUserService.save(blUser) > 0) {
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
        if(blUserService.delete(id) > 0) {
             return R.ok();
        }
        return R.error();
    }

    /**
     * 更新数据
     * @param blUser 更新实体对象
     * @return 1:更新成功 0:更新失败
     */
    @ApiOperation("更新数据 ")
    @PutMapping("/update")
    public R update(BlUser blUser) {
        if(blUserService.update(blUser) > 0) {
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
        return R.ok("result",blUserService.queryByKey(id));
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
        List<BlUser> list = blUserService.queryByMap(columnMap);  
        PageInfo<BlUser> pageInfo = new PageInfo<BlUser>(list);
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
        List<BlUser> list = blUserService.queryAllList();  
        PageInfo<BlUser> pageInfo = new PageInfo<BlUser>(list);
        Datagrid datagrid = new Datagrid(pageInfo.getTotal(),pageInfo.getList());
        return R.ok("result",datagrid);
    }
    
    // TODO 从缓存里取
    @GetMapping("/info")
    public BlUser getUserInfo(){
    	BlUser user =  blUserService.loadUserByUsername("admin");
    	List<BlRole> list = new ArrayList<BlRole>();
    	BlRole role = new BlRole();
    	role.setName("admin");
    	list.add(role);
    	user.setRoles(list);
    	return user;
    }
}