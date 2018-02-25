package com.fs.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fs.entity.BlUser;
import com.fs.mapper.BlUserMapper;
import com.fs.server.BaseService;

/***
 * 博客用户管理-逻辑处理信息
 * @ClassName blUserService
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年2月21日
 */
@Service
public class BlUserService extends BaseService{

    @Autowired
    private BlUserMapper blUserMapper;

    /**
     * 保存数据
     * @param blUser 保存实体对象
     * @return 1:保存成功 0:保存失败
     */
    public int save(BlUser blUser) {
        blUser.setId(get32UUID());
        return blUserMapper.insert(blUser);
    }

    /**
     * 删除数据
     * @param id 主健
     * @return 1:删除成功 0:删除失败
     */
    public int delete(String ids) {
        return blUserMapper.deleteBatchIds(ids.split(","));
    }

    /**
     * 更新数据
     * @param blUser 更新实体对象
     * @return 1:更新成功 0:更新失败
     */
    public int update(BlUser blUser) {
        return blUserMapper.update(blUser);
    }

    /**
     * 查询数据-主健查询
     * @param id 主健
     * @return 一条实体对象
     */
    public BlUser queryByKey(String id) {
        return blUserMapper.queryByKey(id);
    }

    /**
     * 查询数据-多条件查询
     * @return 实体对象集合
     */
    public List<BlUser> queryByMap(Map<String, Object> columnMap) {
        return blUserMapper.queryByMap(columnMap);
    }

    /**
     * 查询所有数据
     * @return 实体对象集合
     */
    public List<BlUser> queryAllList() {
        return blUserMapper.queryAllList();
    }

    /**
     * 根据用户名
     * @return 实体对象
     */
	public BlUser loadUserByUsername(String userIdOrName) {
		return blUserMapper.loadUserByUsername(userIdOrName);
	}
}