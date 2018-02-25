package com.fs.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fs.entity.BlTopic;
import com.fs.mapper.BlTopicMapper;
import com.fs.server.BaseService;

/***
 * 博客主题-逻辑处理信息
 * @ClassName blTopicService
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年2月13日
 */
@Service
public class BlTopicService extends BaseService{

    @Autowired
    private BlTopicMapper blTopicMapper;

    /**
     * 保存数据
     * @param blTopic 保存实体对象
     * @return 1:保存成功 0:保存失败
     */
    public int save(BlTopic blTopic) {
    	blTopic.preInsert();
        return blTopicMapper.insert(blTopic);
    }

    /**
     * 删除数据
     * @param id 主健
     * @return 1:删除成功 0:删除失败
     */
    public int delete(String ids) {
        return blTopicMapper.deleteBatchIds(ids.split(","));
    }

    /**
     * 更新数据
     * @param blTopic 更新实体对象
     * @return 1:更新成功 0:更新失败
     */
    public int update(BlTopic blTopic) {
    	blTopic.preUpdate();
        return blTopicMapper.update(blTopic);
    }

    /**
     * 查询数据-主健查询
     * @param id 主健
     * @return 一条实体对象
     */
    public BlTopic queryByKey(String id) {
        return blTopicMapper.queryByKey(id);
    }

    /**
     * 查询数据-多条件查询
     * @return 实体对象集合
     */
    public List<BlTopic> queryByMap(Map<String, Object> columnMap) {
        return blTopicMapper.queryByMap(columnMap);
    }

    /**
     * 查询所有数据
     * @return 实体对象集合
     */
    public List<BlTopic> queryAllList() {
        return blTopicMapper.queryAllList();
    }
}