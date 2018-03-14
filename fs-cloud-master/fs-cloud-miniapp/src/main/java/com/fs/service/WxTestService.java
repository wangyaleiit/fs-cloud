package com.fs.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fs.entity.WxTest;
import com.fs.mapper.WxTestMapper;
import com.fs.server.BaseService;

/***
 * ��ˮ����-�߼�������Ϣ
 * @ClassName wxTestService
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018��1��29��
 */
@Service
public class WxTestService extends BaseService{

    @Autowired
    private WxTestMapper wxTestMapper;

    /**
     * ��������
     * @param wxTest ����ʵ�����
     * @return 1:����ɹ� 0:����ʧ��
     */
    public int save(WxTest wxTest) {
        wxTest.setId(get32UUID());
        return wxTestMapper.insert(wxTest);
    }

    /**
     * ɾ������
     * @param id ����
     * @return 1:ɾ���ɹ� 0:ɾ��ʧ��
     */
    public int deleteByKey(String id) {
        return wxTestMapper.deleteByKey(id);
    }

    /**
     * ��������
     * @param wxTest ����ʵ�����
     * @return 1:���³ɹ� 0:����ʧ��
     */
    public int update(WxTest wxTest) {
        return wxTestMapper.update(wxTest);
    }

    /**
     * ��ѯ����-������ѯ
     * @param id ����
     * @return һ��ʵ�����
     */
    public WxTest queryByKey(String id) {
        return wxTestMapper.queryByKey(id);
    }

    /**
     * ��ѯ����-��������ѯ
     * @return ʵ����󼯺�
     */
    public List<WxTest> queryByMap(Map<String, Object> columnMap) {
        return wxTestMapper.queryByMap(columnMap);
    }

    /**
     * ��ѯ��������
     * @return ʵ����󼯺�
     */
    public List<WxTest> queryAllList() {
        return wxTestMapper.queryAllList();
    }
}
