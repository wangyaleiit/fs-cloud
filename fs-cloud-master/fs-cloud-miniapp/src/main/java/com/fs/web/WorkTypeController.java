package com.fs.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fs.entity.MiniWorkType;
import com.fs.service.MiniWorkTypeService;
import com.fs.utils.R;

/**
 * 工种管理	
 * @ClassName WechatAuthController
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2017年11月20日
 */
@RestController
@RequestMapping("/miniApp/workType")
public class WorkTypeController {

	@Autowired
	private MiniWorkTypeService workTypeService;
	
	@PostMapping("/save")
	public R save(MiniWorkType workType){
		int count = workTypeService.save(workType);
		if(count > 0){
			return R.ok();
		}
		return R.error();
	}
	
	@DeleteMapping("/del/{id}")
	public R del(@PathVariable String id){
		int count = workTypeService.del(id);
		if(count > 0){
			return R.ok();
		}
		return R.error();
	}
	
	@PutMapping("/update")
	public R update(MiniWorkType workType){
		int count = workTypeService.update(workType);
		if(count > 0){
			return R.ok();
		}
		return R.error();
	}
	
	@GetMapping("/query")
	public R query(){
		List<MiniWorkType> workTypeList = workTypeService.query();
		return R.ok("result",workTypeList);
	}
}
