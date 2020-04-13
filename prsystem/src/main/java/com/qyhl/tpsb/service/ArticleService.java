package com.qyhl.tpsb.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.qyhl.tpsb.commonutils.AjaxResult;
import com.qyhl.tpsb.dto.article.FindArticleDTO;
import com.qyhl.tpsb.entity.Article;
import com.qyhl.tpsb.entity.SysUserEntity;

public interface ArticleService extends IService<Article>{

	/** 
	 * 新增文章
	 * Anthor:Qiu
	 * method：add       
	 * @version 1.0.0
	 * time:2019-10-31下午10:06:16  
	 */
	AjaxResult add(Article article,SysUserEntity user);

	/** 
	 * Anthor:Qiu
	 * method：getPage       
	 * @version 1.0.0
	 * time:2019-11-11下午7:40:05  
	 */
	Page<Article> getPage(FindArticleDTO findArticleDTO);
	
	Page<Article> getPageByUserId(FindArticleDTO findArticleDTO,Long userid);

	/** 
	 * Anthor:Qiu
	 * method：getArticleDetails       
	 * @version 1.0.0
	 * time:2019-11-12下午7:16:02  
	 * @return 
	 */
	
	Article getArticleDetails(Long id);
	
	Article getArticleInfo(Long id);

	/** 
	 * 分页获取文章
	 * method：getArticleList       
	 * @version 1.0.0
	 * time:2019-12-3下午10:33:41  
	 */
	Page<Article> getArticleList(FindArticleDTO findArticleDTO);

	/** 
	 * 根据ID删除文章
	 * method：removeById       
	 * @version 1.0.0
	 * time:2019-12-4下午9:49:10  
	 */
	AjaxResult removeById(String id, SysUserEntity user);

	/** 
	 * 根据ids批量删除文章
	 * Anthor:Qiu
	 * method：batchRemoveTitle       
	 * @version 1.0.0
	 * time:2019-12-28下午9:52:08  
	 */
	Integer batchRemoveTitle(List<Article> entityList, SysUserEntity user);

	/** 
	 *获取10条热门文章
	 * method：getHotArticle       
	 * @version 1.0.0
	 * time:2020-2-22下午2:59:46  
	 */
	AjaxResult getHotArticle();

	AjaxResult updates(Article article, SysUserEntity user);

	

}
