package com.fs.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fs.entity.BlArticle;
import com.fs.mapper.BlArticleMapper;
import com.fs.server.BaseService;
import com.fs.utils.StringUtils;

/***
 * 博客文章-逻辑处理信息
 * @ClassName blArticleService
 * @Description TODO
 * @author wang.yalei@fujisoft-china.com
 * @date 2018年2月13日
 */
@Service
public class BlArticleService extends BaseService{

    @Autowired
    private BlArticleMapper blArticleMapper;

    /**
     * 保存数据
     * @param blArticle 保存实体对象
     * @return 1:保存成功 0:保存失败
     */
    public String saveOrUpdate(BlArticle blArticle) {
        if (blArticle.getSummary() == null || "".equals(blArticle.getSummary())) {
            String stripHtml = stripHtml(blArticle.getHtmlcontent());
            blArticle.setSummary(stripHtml.substring(0, stripHtml.length() > 150 ? 150 : stripHtml.length()));
        }
        String[] dynamicTags = blArticle.getDynamicTags();
        blArticle.setTag(StringUtils.join(dynamicTags,","));
        if ("-1".equals(blArticle.getId())) {
            blArticle.preInsert();
            blArticleMapper.insert(blArticle);
        } else {
        	blArticle.preUpdate();
             blArticleMapper.update(blArticle);
        }
        return blArticle.getId();
    }

    /**
     * 删除数据
     * @param id 主健
     * @return 1:删除成功 0:删除失败
     */
    public int delete(String ids) {
        return blArticleMapper.deleteBatchIds(ids.split(","));
    }

    /**
     * 更新数据
     * @param blArticle 更新实体对象
     * @return 1:更新成功 0:更新失败
     */
    public int update(BlArticle blArticle) {
    	blArticle.preUpdate();
        return blArticleMapper.update(blArticle);
    }

    /**
     * 查询数据-主健查询
     * @param id 主健
     * @return 一条实体对象
     */
    public BlArticle queryByKey(String id) {
        return blArticleMapper.queryByKey(id);
    }

    /**
     * 查询数据-多条件查询
     * @return 实体对象集合
     */
    public List<BlArticle> queryByMap(Map<String, Object> columnMap) {
        return blArticleMapper.queryByMap(columnMap);
    }
    
    /**
     * 时间轴
     * @return
     */
    public List<Map<String,String>> queryTimeLine() {
        return blArticleMapper.queryTimeLine();
    }

    /**
     * 查询所有数据
     * @return 实体对象集合
     */
    public List<BlArticle> queryAllList() {
        return blArticleMapper.queryAllList();
    }
    
	public int toDust(String ids) {
		return blArticleMapper.toDust(ids.split(","));
	}
	
	public BlArticle queryPerInfo(int artIndex) {
		return blArticleMapper.queryPerInfo(artIndex);
	}
	
	public BlArticle queryNextInfo(int artIndex) {
		return blArticleMapper.queryNextInfo(artIndex);
	}
	
    public String stripHtml(String content) {
        content = content.replaceAll("<p .*?>", "");
        content = content.replaceAll("<br\\s*/?>", "");
        content = content.replaceAll("\\<.*?>", "");
        return content;
    }
}