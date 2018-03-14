package com.fs.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fs.entity.MiniEmployee;
import com.fs.service.MiniEmployeeService;
import com.fs.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/***
 * 钟点工发布任务
 * @ClassName employeeController
 * @Description TODO
 * @author ma.li@fujisoft-china.com
 * @date 2018年1月19日
 */
@Api(tags={"钟点工发布任务"})
@RestController
@RequestMapping("/miniApp/employee")
public class EmployeeController {

    
    @Autowired
    private MiniEmployeeService employeeService;
    
    
    /***
     * 保存
     * @param employee
     * @return
     */
	@ApiOperation("保存")
	@PostMapping("/release")
	public R release(@RequestBody MiniEmployee employee){
	    int count = employeeService.save(employee);
		if(count > 0){return R.ok("id",employee.getId());}
		return R.error();
	}
	
	/**
	 * 查询list
	 * @return
	 */
	@GetMapping("/queryList")
	public R query(){
		List<MiniEmployee> employeeList = employeeService.findAllList();
		return R.ok("result",employeeList);
	}
	
	/**
	 * 查询所有发布的任务
	 * @param openId
	 * @return
	 */
	@GetMapping("/info/{id}")
	public R findById(@PathVariable String id){
		MiniEmployee employee = employeeService.findById(id);
		return R.ok("result",employee);
	}
	
	/**
	 * 查询所有发布的任务
	 * @param openId
	 * @return
	 */
	@GetMapping("/pubList/{openId}")
	public R publish(@PathVariable String openId){
		List<MiniEmployee> pubList = employeeService.findListByKey(openId);
		return R.ok("result",pubList);
	}
	
	/**
	 * 更新
	 * @param employee
	 * @return
	 */
	@PutMapping("/update")
	public R update(@RequestBody MiniEmployee employee){
		employee.setIsShow("1");
		int count = employeeService.update(employee);
		if(count > 0){
			return R.ok();
		}
		return R.error();
	}
	
	
	/**
	 * 删除数据
	 * @param id
	 * @return
	 */
	@DeleteMapping("/del/{id}")
	public R del(@PathVariable String id){
		int count = employeeService.delete(id);
		if(count > 0){
			return R.ok();
		}
		return R.error();
	}
}
