package com.fs.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fs.entity.WxTest;
import com.fs.mapper.WxTestMapper;
import com.fs.server.BaseService;

/***
 * 流水记账-逻辑处理信息
 * @ClassName wxTestService
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年1月29日
 */
@Service
public class WxTestService extends BaseService{

    @Autowired
    private WxTestMapper wxTestMapper;

    /**
     * 保存数据
     * @param wxTest 保存实体对象
     * @return 1:保存成功 0:保存失败
     */
    public int save(WxTest wxTest) {
        wxTest.setId(get32UUID());
        return wxTestMapper.insert(wxTest);
    }

    /**
     * 删除数据
     * @param id 主健
     * @return 1:删除成功 0:删除失败
     */
    public int deleteByKey(String id) {
        return wxTestMapper.deleteByKey(id);
    }

    /**
     * 更新数据
     * @param wxTest 更新实体对象
     * @return 1:更新成功 0:更新失败
     */
    public int update(WxTest wxTest) {
        return wxTestMapper.update(wxTest);
    }

    /**
     * 查询数据-主健查询
     * @param id 主健
     * @return 一条实体对象
     */
    public WxTest queryByKey(String id) {
        return wxTestMapper.queryByKey(id);
    }

    /**
     * 查询数据-多条件查询
     * @return 实体对象集合
     */
    public List<WxTest> queryByMap(Map<String, Object> columnMap) {
        return wxTestMapper.queryByMap(columnMap);
    }

    /**
     * 查询所有数据
     * @return 实体对象集合
     */
    public List<WxTest> queryAllList() {
        return wxTestMapper.queryAllList();
    }
}
