package com.qyhl.tpsb.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qyhl.tpsb.commonutils.AjaxResult;
import com.qyhl.tpsb.commonutils.Constant;
import com.qyhl.tpsb.dto.article.FindArticleDTO;
import com.qyhl.tpsb.entity.Article;
import com.qyhl.tpsb.entity.ArticleCategory;
import com.qyhl.tpsb.entity.SysUserEntity;
import com.qyhl.tpsb.mapper.ArticleMapper;
import com.qyhl.tpsb.service.ArticleCategoryService;
import com.qyhl.tpsb.service.ArticleService;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService{
	
	@Autowired
	private ArticleCategoryService articleCategoryService;
	
	@Override
	public AjaxResult updates(Article article, SysUserEntity user) {
		AjaxResult result = new AjaxResult();
		if (StringUtils.isEmpty(article)) {
			result.setState(0);
			result.setMsg("参数不能为空");
		}else {
			try {
				this.updateById(article);
				result.setState(1);
				result.setMsg("修改成功");
			} catch (Exception e) {
				result.setState(2);
				result.setMsg("网络错误，请稍后再试");
			}
		}
		return result;
	}
	
	@Override
	public AjaxResult add(Article article,SysUserEntity user) {
		 AjaxResult result = new AjaxResult();
		 Article selectOne = this.selectOne(new EntityWrapper<Article>().eq("article_title",article.getArticleTitle()).eq("status", Constant.ONE));
		if (!StringUtils.isEmpty(selectOne)) {
			// 标题已存在
			result.setState(0);
			result.setMsg("文章标题已存在");
		}else {
			article.setCreateId(user.getUserid());
			article.setUserId(user.getUserid());
			article.setCreatePerson(user.getUsername());
			article.setCreateTime(new Date());
			article.setStatus(Constant.ONE);
			try {
				this.insert(article);
				result.setState(1);
				result.setMsg("添加成功");
			} catch (Exception e) {
				result.setState(2);
				result.setMsg("添加异常");
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public Page<Article> getPage(FindArticleDTO findArticleDTO) {
		EntityWrapper<Article> wrapper = new EntityWrapper<>();
		wrapper.eq("status", Constant.ONE)
		       .orderBy("create_time",false);
		Page<Article> selectPage = this.selectPage(new Page<Article>(findArticleDTO.getPage(),findArticleDTO.getLimit()), wrapper);
	    
		return selectPage;
	}


	@Override
	public Article getArticleDetails(Long id) {
		EntityWrapper<Article> wrapper = new EntityWrapper<>();
		wrapper.eq("article_id", id)
		        .eq("status", Constant.ONE);
		Article selectOne = this.selectOne(wrapper);
		if (StringUtils.isEmpty(selectOne.getArticleViews())) {
			selectOne.setArticleViews(BigDecimal.ONE);
		}else {
			BigDecimal num = BigDecimal.ONE;
			BigDecimal add = selectOne.getArticleViews().add(num);
			selectOne.setArticleViews(add);
		}
	    
	    this.updateById(selectOne);
		return selectOne;
	}

	/* 
	 * 分页获取文章
	 */
	@Override
	public Page<Article> getArticleList(FindArticleDTO findArticleDTO) {
		EntityWrapper<Article> wrapper = new EntityWrapper<>();
		wrapper.like("article_title", findArticleDTO.getSearch()).or()
		       .like("create_person",findArticleDTO.getSearch()).or()
		       .like("article_views", findArticleDTO.getSearch()).andNew()
		       .eq("status", Constant.ONE)
		       .orderBy("create_time",false);
		try {
			Page<Article> selectPage = this.selectPage(new Page<Article>(findArticleDTO.getPage(),findArticleDTO.getLimit()),wrapper);
			return selectPage;
		} catch (Exception e) {
			throw new RuntimeException("查询失败");
		}
		
	}

	/* 
	 * 根据ID删除文章
	 */
	@Override
	public AjaxResult removeById(String id, SysUserEntity user) {
		AjaxResult result = new AjaxResult();
		EntityWrapper<Article> wrapper = new EntityWrapper<Article>();
		wrapper.eq("status", Constant.ONE)
		       .eq("article_id", id);
		Article selectOne = this.selectOne(wrapper);
		if (StringUtils.isEmpty(selectOne)) {
			result.setSuccess(false);
			result.setMsg("文章不存在");
		}else {
			try {
				selectOne.setUpdateId(user.getUserid());
				selectOne.setUpdatePerson(user.getUsername());
				selectOne.setUpdateTime(new Date());
				selectOne.setStatus(Constant.ZERO);
				this.updateById(selectOne);
				result.setSuccess(true);
				result.setMsg("删除文章成功");
			} catch (Exception e) {
				result.setSuccess(false);
				result.setMsg("系统运行异常，请稍后");
			}
		}
		return result;
	}

	@Override
	public Integer batchRemoveTitle(List<Article> entityList, SysUserEntity user) {
		Integer state;
		   for (Article article : entityList) {
			   article.setStatus(Constant.ZERO);
			   article.setUpdateId(user.getUserid());
			   article.setUpdatePerson(user.getUsername());
			   article.setUpdateTime(new Date());
		     }
		   try {
			   this.updateBatchById(entityList);
			   state = 1;
			} catch (Exception e) {
				state = 0;
				e.printStackTrace();
			}
		    
			return state;
	}

	/* (non-Javadoc)
	 * @see com.qyhl.tpsb.service.ArticleService#getHotArticle()
	 * 获取10条热门文章
	 */
	@Override
	public AjaxResult getHotArticle() {
		AjaxResult result = new AjaxResult();
		EntityWrapper<Article> wrapper = new EntityWrapper<>();
		wrapper.gt("article_views", Constant.COUNT)
		       .eq("status",Constant.ONE)
		       .orderBy("create_time", false);
		try {
			List<Article> list = this.selectList(wrapper);
			result.setSuccess(true);
			result.setCode(200);
			result.setMsg(Constant.SUCCESS);
			result.setData(list);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(Constant.FAIL);
			result.setCode(100);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.qyhl.tpsb.service.ArticleService#getPageByUserId(com.qyhl.tpsb.dto.article.FindArticleDTO)
	 */
	@Override
	public Page<Article> getPageByUserId(FindArticleDTO findArticleDTO,Long userid) {
		EntityWrapper<Article> wrapper = new EntityWrapper<>();
		wrapper.eq("status", Constant.ONE)
		       .eq("user_id", userid)
		       .orderBy("create_time",false);
		Page<Article> selectPage = this.selectPage(new Page<Article>(findArticleDTO.getPage(),findArticleDTO.getLimit()), wrapper);
		return selectPage;
	}

	@Override
	public Article getArticleInfo(Long id) {
		// TODO Auto-generated method stub
		EntityWrapper<Article> wrapper = new EntityWrapper<>();
		wrapper.eq("article_id", id)
		        .eq("status", Constant.ONE);
		return this.selectOne(wrapper);
	}

	

}
