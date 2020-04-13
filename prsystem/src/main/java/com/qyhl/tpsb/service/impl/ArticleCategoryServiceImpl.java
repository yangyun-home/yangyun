package com.qyhl.tpsb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qyhl.tpsb.commonutils.AjaxResult;
import com.qyhl.tpsb.commonutils.Constant;
import com.qyhl.tpsb.dto.article.FindArticleDTO;
import com.qyhl.tpsb.entity.ArticleCategory;
import com.qyhl.tpsb.entity.SysUserEntity;
import com.qyhl.tpsb.mapper.ArticleCategoryMapper;
import com.qyhl.tpsb.service.ArticleCategoryService;
@Service
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategory> implements ArticleCategoryService {

	@Override
	public List<ArticleCategory> getList() {
		EntityWrapper<ArticleCategory> wrapper = new EntityWrapper<>();
		wrapper.eq("status", Constant.ONE);
		return this.selectList(wrapper);
	}

	@Override
	public Page<ArticleCategory> getCategoryPage(FindArticleDTO findArticleDTO) {
		EntityWrapper<ArticleCategory> wrapper = new EntityWrapper<>();
		wrapper.like("category_name", findArticleDTO.getSearch()).or()
	           .like("category_description",findArticleDTO.getSearch()).andNew()
		       .eq("status", Constant.ONE)
		       .orderBy("create_time",false);
		Page<ArticleCategory> selectPage = this.selectPage(new Page<ArticleCategory>(findArticleDTO.getPage(),findArticleDTO.getLimit()), wrapper);
	    
		return selectPage;
	}


	@Override
	public AjaxResult updateCategory(ArticleCategory articleCategory,
			SysUserEntity user) {
		AjaxResult result = new AjaxResult();
		ArticleCategory category = this.selectOne(new EntityWrapper<ArticleCategory>()
				.eq("category_id", articleCategory.getCategoryId())
				.eq("status",Constant.ONE));
		if (StringUtils.isEmpty(category)) {
			result.setSuccess(false);
			result.setMsg("不存在"+articleCategory.getCategoryName()+"该分类");
		}else {
			if (articleCategory.getCategoryName().equals(category.getCategoryName())) {
				result.setSuccess(false);
				result.setMsg("分类名称已存在，请重新输入");
			}else{
				articleCategory.setUpdateId(user.getUserid());
				articleCategory.setUpdatePerson(user.getUsername());
				articleCategory.setUpdateTime(new Date());
				try {
					this.updateById(articleCategory);
					result.setSuccess(true);
					result.setMsg(Constant.SUCCESS);
				} catch (Exception e) {
					result.setSuccess(false);
					result.setMsg(Constant.FAIL);
				}
			}
		}
		return result;
	}

	
	@Override
	public AjaxResult deleteCategoryById(String id, SysUserEntity user) {
		AjaxResult result = new AjaxResult();
		ArticleCategory category = this.selectOne(new EntityWrapper<ArticleCategory>()
				.eq("category_id",id)
				.eq("status",Constant.ONE));
		if (StringUtils.isEmpty(category)) {
			result.setSuccess(false);
			result.setMsg("不存在该分类");
		}else {
			try {
				category.setStatus(Constant.ZERO);
				category.setUpdateId(user.getUserid());
				category.setUpdatePerson(user.getUsername());
				category.setUpdateTime(new Date());
				this.updateById(category);
				result.setSuccess(true);
				result.setMsg(Constant.SUCCESS);
			} catch (Exception e) {
				result.setSuccess(false);
				result.setMsg(Constant.FAIL);
			}
		}
		return result;
	}

	@Override
	public AjaxResult addCategory(ArticleCategory articleCategory,
			SysUserEntity user) {
		AjaxResult result = new AjaxResult();
		ArticleCategory category = this.selectOne(new EntityWrapper<ArticleCategory>()
				.eq("category_name", articleCategory.getCategoryName())
				.eq("status", Constant.ONE));
		if (!StringUtils.isEmpty(category)) {
			result.setSuccess(false);
			result.setMsg("该分类名称已存在，请重新输入");
		}else {
			try {
				articleCategory.setCreateId(user.getUserid());
				articleCategory.setStatus(Constant.ONE);
				articleCategory.setCreatePerson(user.getUsername());
				articleCategory.setCreateTime(new Date());
				this.insert(articleCategory);
				result.setSuccess(true);
				result.setMsg(Constant.SUCCESS);
			} catch (Exception e) {
				result.setSuccess(false);
				result.setMsg(e.toString());
			}
			
		}
		return result;
	}

	@Override
	public ArticleCategory getOneById(Long id) {
		// TODO Auto-generated method stub
		EntityWrapper<ArticleCategory> wrapper = new EntityWrapper<>();
		wrapper.eq("status", Constant.ONE)
		       .eq("category_id", id);
		return this.selectOne(wrapper);
	}

	
}
