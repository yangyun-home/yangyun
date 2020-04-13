package com.qyhl.tpsb.service.impl;

import java.util.Date;

import org.apache.ibatis.executor.ReuseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qyhl.tpsb.commonutils.AjaxResult;
import com.qyhl.tpsb.commonutils.Constant;
import com.qyhl.tpsb.dto.collect.FindCollectDTO;
import com.qyhl.tpsb.entity.Article;
import com.qyhl.tpsb.entity.ArticleCollection;
import com.qyhl.tpsb.entity.SysUserEntity;
import com.qyhl.tpsb.mapper.CollectionMapper;
import com.qyhl.tpsb.service.ArticleService;
import com.qyhl.tpsb.service.CollectionService;
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, ArticleCollection> implements CollectionService {
   @Autowired
   private ArticleService articleService; 
	
	@Override
	public AjaxResult collectionById(Long id,SysUserEntity user) {
		AjaxResult result = new AjaxResult();
		Article article = articleService.getArticleDetails(id);
		ArticleCollection ac = new ArticleCollection();
		ac.setArticleId(id);
		ac.setArticleName(article.getArticleTitle());
		ac.setCollectionUid(user.getUserid());
		ac.setCreateId(user.getUserid());
		ac.setCreatePerson(user.getUsername());
		ac.setCreateTime(new Date());
		ac.setStatus(Constant.ONE);
		
		ArticleCollection selectOne = this.selectOne(new EntityWrapper<ArticleCollection>()
				.eq("status", Constant.ONE).eq("collection_uid", user.getUserid()).eq("article_id", id));
		if (!StringUtils.isEmpty(selectOne)) {
			result.setSuccess(true);
			result.setState(0);
			result.setMsg("你已经收藏过了，请勿重复收藏");
		}else {
			try {
				this.insert(ac);
				result.setSuccess(true);
				result.setState(1);
				result.setMsg("收藏成功");
			} catch (Exception e) {
				result.setSuccess(false);
				result.setMsg("收藏失败");
				System.out.printf("文章收藏异常",e);
			}
		}
		return result;
	}
	
	@Override
	public AjaxResult cancleCollectById(Long id, SysUserEntity user) {
		AjaxResult result = new AjaxResult();
		EntityWrapper<ArticleCollection> wrapper = new EntityWrapper<>();
		wrapper.eq("status", Constant.ONE)
		.eq("collection_uid", user.getUserid())
		.eq("article_id", id);
		ArticleCollection collection = this.selectOne(wrapper);
		if (StringUtils.isEmpty(collection)) {
			result.setSuccess(false);
		    result.setMsg("不存在该文章");
		}else {
			try {
				this.deleteById(collection);
				result.setSuccess(true);
				result.setMsg("取消收藏成功");
			} catch (Exception e) {
				// TODO: handle exception
				result.setSuccess(false);
				result.setMsg("网络异常，请稍后再试");
			}
		}
		return result;
	}

	@Override
	public Page<ArticleCollection> getCollectionPage(FindCollectDTO findCollectDTO, Long userid) {
		EntityWrapper<ArticleCollection> wrapper = new EntityWrapper<>();
		wrapper.eq("status", Constant.ONE)
		       .eq("collection_uid", userid)
		       .orderBy("create_time",false);
		Page<ArticleCollection> selectPage = this.selectPage(new Page<ArticleCollection>(findCollectDTO.getPage(),findCollectDTO.getLimit()), wrapper);
		
		return selectPage;
	
	}

	

}
