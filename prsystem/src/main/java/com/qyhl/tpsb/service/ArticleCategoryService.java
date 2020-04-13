package com.qyhl.tpsb.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.qyhl.tpsb.commonutils.AjaxResult;
import com.qyhl.tpsb.dto.article.FindArticleDTO;
import com.qyhl.tpsb.entity.ArticleCategory;
import com.qyhl.tpsb.entity.SysUserEntity;

public interface ArticleCategoryService extends IService<ArticleCategory>{
	
	List<ArticleCategory> getList();
	
	Page<ArticleCategory> getCategoryPage(FindArticleDTO findArticleDTO);

	AjaxResult updateCategory(ArticleCategory articleCategory, SysUserEntity user);

	AjaxResult deleteCategoryById(String id, SysUserEntity user);
	
	AjaxResult addCategory(ArticleCategory articleCategory, SysUserEntity user);
	
	ArticleCategory getOneById(Long id);

}
