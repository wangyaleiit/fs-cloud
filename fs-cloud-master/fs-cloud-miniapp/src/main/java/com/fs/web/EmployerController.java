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
import com.fs.entity.MiniEmployer;
import com.fs.service.MiniEmployerService;
import com.fs.service.MiniUserService;
import com.fs.utils.R;

/***
 * 雇主发布任务
 * @ClassName EmployerController
 * @Description TODO
 * @author ma.li@fujisoft-china.com
 * @date 2018年1月19日
 */
@RestController
@RequestMapping("/miniApp/employer")
public class EmployerController {

    
    @Autowired
    private MiniEmployerService employerService;
    
    @Autowired
    private MiniUserService userService;
    
    
    /***
     * 保存
     * @param employer
     * @return
     */
	@PostMapping("/release")
	public R release(@RequestBody MiniEmployer employer){
	    int count = employerService.save(employer);
		if(count > 0){return R.ok("id",employer.getId());}
		return R.error();
	}
	
	/**
	 * 查询list
	 * @return
	 */
	@GetMapping("/queryList")
	public R query(){
		List<MiniEmployer> employerList = employerService.findAllList();
		return R.ok("result",employerList);
	}
	
	/**
	 * 查询所有发布的任务
	 * @param openId
	 * @return
	 */
	@GetMapping("/info/{id}")
	public R findById(@PathVariable String id){
		MiniEmployer employer = employerService.findById(id);
		return R.ok("result",employer);
	}
	
	/**
	 * 查询所有发布的任务
	 * @param openId
	 * @return
	 */
	@GetMapping("/pubList/{openId}")
	public R publish(@PathVariable String openId){
		List<MiniEmployer> pubList = employerService.findListByKey(openId);
		return R.ok("result",pubList);
	}
	
	/**
	 * 更新
	 * @param employer
	 * @return
	 */
	@PutMapping("/update")
	public R update(MiniEmployer employer){
		int count = employerService.update(employer);
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
		int count = employerService.delete(id);
		if(count > 0){
			return R.ok();
		}
		return R.error();
	}
	
	/***
	 * 预约
	 * @param openId
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/subscribe/{openId}")
	public R subscribe(@PathVariable String openId){
		if(userService.isSubscribeRole(openId) > 0){
			
		} else {
			return R.error("无权预约，请先认证\r\n（我的-钟点认证）");
		}
		return R.ok();
	}
}
