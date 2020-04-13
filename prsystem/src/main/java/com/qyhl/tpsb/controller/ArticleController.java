package com.qyhl.tpsb.controller;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.plugins.Page;
import com.qyhl.tpsb.commonutils.AjaxResult;
import com.qyhl.tpsb.commonutils.LayuiAPiResult;
import com.qyhl.tpsb.commonutils.word.IdentTextUtils;
import com.qyhl.tpsb.dto.article.FindArticleDTO;
import com.qyhl.tpsb.entity.Article;
import com.qyhl.tpsb.entity.ArticleCategory;
import com.qyhl.tpsb.entity.SysUserEntity;
import com.qyhl.tpsb.service.ArticleCategoryService;
import com.qyhl.tpsb.service.ArticleService;
import com.qyhl.tpsb.service.SysUserService;

@Controller
@RequestMapping(value="/article")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private ArticleCategoryService articleCategoryService;
	
	@RequestMapping("/toUpdateArticleUI")
	@ResponseBody
	public ModelAndView toUpdateArticleUI(HttpServletRequest request){
		String id = request.getParameter("id");
		Long id2 = Long.valueOf(id);
		ModelAndView view = new ModelAndView();
		view.setViewName("/home/aticle/editArticle");
		view.addObject("id", id);
		return view;
	}
	
	/**修改分类*/
	@PostMapping(value="/updateCategory")
	@ResponseBody
	@ApiOperation(value = "修改分类",httpMethod = "POST",response=ArticleController.class)
	public AjaxResult updateCategory(@RequestBody ArticleCategory articleCategory,HttpSession session){
		SysUserEntity user = (SysUserEntity) session.getAttribute("loginUser");
		AjaxResult result=articleCategoryService.updateCategory(articleCategory,user);
		return result;
	}
	
	/**新增分类*/
	@PostMapping(value="/addCategory")
	@ResponseBody
	@ApiOperation(value = "新增分类",httpMethod = "POST",response=ArticleController.class)
	public AjaxResult addCategory(@RequestBody ArticleCategory articleCategory,HttpSession session){
		SysUserEntity user = (SysUserEntity) session.getAttribute("loginUser");
		AjaxResult result=articleCategoryService.addCategory(articleCategory,user);
		return result;
	}
	
	/**删除分类*/
	@PostMapping(value="/deleteCategoryById")
	@ResponseBody
	@ApiOperation(value = "根据ID删除文章",httpMethod = "POST", response = ArticleController.class)
	public Object deleteCategoryById(HttpServletRequest request,HttpSession session){
    String id = request.getParameter("id");
		SysUserEntity user = (SysUserEntity) session.getAttribute("loginUser");
		  AjaxResult result = new AjaxResult();
			if (!StringUtils.isEmpty(id)) {
				result = articleCategoryService.deleteCategoryById(id,user);
			}else {
				result.setSuccess(false);
				result.setMsg("参数ID不能为空");
			}
		return result;
	}
	
	/**查询文章分类*/
	@GetMapping(value="/getCategoryPage")
	@ResponseBody
	@ApiOperation(value = "分页获取文章分类",httpMethod = "GET", response = ArticleController.class)
	public LayuiAPiResult getCategoryPage(@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="limit",defaultValue="10")Integer limit,
			@RequestParam(value="send_name",defaultValue="")String send_name){
		LayuiAPiResult result = new LayuiAPiResult();
		
		FindArticleDTO findArticleDTO = new FindArticleDTO();
		findArticleDTO.setPage(page);
		findArticleDTO.setLimit(limit);
		findArticleDTO.setSearch(send_name);
		
		Page<ArticleCategory> pageVo =articleCategoryService.getCategoryPage(findArticleDTO);
		if (!StringUtils.isEmpty(pageVo.getRecords())) {
	    	result.setCode(0);
			result.setMsg("请求成功");
			result.setCount(Integer.parseInt(String.valueOf(pageVo.getTotal())));
			result.setData(pageVo.getRecords());
			result.setPage(page);
			result.setLimit(limit);
		}else {
			result.setCode(1);
			result.setMsg("请求失败");
		}
		return result;
	}

		
	/**
	 * 分页获取文章
	 * */
	@PostMapping(value="getPage")
	@ResponseBody
	@ApiOperation(value = " 分页获取文章",httpMethod = "POST", response = ArticleController.class)
	public AjaxResult getPage(@RequestBody FindArticleDTO findArticleDTO){
		AjaxResult result=new AjaxResult();
		Page<Article> page = articleService.getPage(findArticleDTO);
		if (!StringUtils.isEmpty(page.getRecords())) {
		   result.setData(page);	
		}
		return result;
	}
	
	/**根据用户id分页获取文章*/
	@PostMapping(value="/getPageByUserId")
	@ResponseBody
	@ApiOperation(value = "根据用户id分页获取文章",httpMethod = "POST", response = ArticleController.class)
	public AjaxResult getPageByUserId(@RequestBody FindArticleDTO findArticleDTO,HttpSession session){
		SysUserEntity user = (SysUserEntity) session.getAttribute("webLoginUser");
		
		AjaxResult result=new AjaxResult();
		Page<Article> page = articleService.getPageByUserId(findArticleDTO,user.getUserid());
		if (!StringUtils.isEmpty(page.getRecords())) {
		   result.setData(page);	
		}
		return result;
	}
	
	
	/**
	 * 获取10条热门文章      
	 * @version 1.0.0
	 * time:2020-2-22下午2:56:05
	 */
	@GetMapping("/getHotArticle")
	@ResponseBody
	@ApiOperation(value = "获取热门文章   ",httpMethod = "get", response = ArticleController.class)
	public AjaxResult getHotArticle(){
		AjaxResult result = articleService.getHotArticle();
		return result;
	}
	
	
	@RequestMapping("/details")
	@ResponseBody
	public ModelAndView detailsById(HttpServletRequest request){
		String id = request.getParameter("id");
		Long id2 = Long.valueOf(id);
		Article article =articleService.getArticleDetails(id2);
		SysUserEntity entity = sysUserService.findUserById(article.getUserId());
		ModelAndView view = new ModelAndView();
		view.setViewName("/home/aticle/details");
		view.addObject("articleInfo", article);
		view.addObject("user", entity);
		view.addObject("id", id);
		return view;
	}
	
	@GetMapping("/getInfoById")
	@ResponseBody
	public AjaxResult getInfoById(HttpServletRequest request){
		AjaxResult result = new AjaxResult();
		String id = request.getParameter("id");
		Long id2 = Long.valueOf(id);
		Article article =articleService.getArticleDetails(id2);
		result.setSuccess(true);
		result.setData(article);
		return result;
	}
	
	@GetMapping("/getArticleInfo")
	@ResponseBody
	public AjaxResult getArticleInfo(HttpServletRequest request){
		AjaxResult result = new AjaxResult();
		String id = request.getParameter("id");
		Long id2 = Long.valueOf(id);
		Article article =articleService.getArticleInfo(id2);
		result.setSuccess(true);
		result.setData(article);
		return result;
	}
	
	
	/**
	 * 新增文章
	 * Anthor:Qiu
	 * method：add       
	 * @version 1.0.0
	 * time:2019-11-4下午9:31:44
	 */
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult add(@RequestBody Article article,HttpSession session){
		SysUserEntity user = (SysUserEntity) session.getAttribute("webLoginUser");
		if (StringUtils.isEmpty(user)) {
			AjaxResult result2 = new AjaxResult();
			result2.setState(4);
			result2.setMsg("你未登录系统，请登录后在操作");
			return result2;
		}else {
			AjaxResult result = articleService.add(article,user);
			return result;
		}
	}
	
	
	@PostMapping("/updates")
	@ResponseBody
	public AjaxResult updates(@RequestBody Article article,HttpSession session){
		AjaxResult result = new AjaxResult();
		SysUserEntity user = (SysUserEntity) session.getAttribute("webLoginUser");
		if (StringUtils.isEmpty(user)) {
			result.setState(4);
			result.setMsg("你未登录系统，请登录后在操作");
		}else {
			result = articleService.updates(article,user);
		}
		return result;
	}
	
	/**
	 * 文本识别
	 * Anthor:Qiu
	 * method：add       
	 * @version 1.0.0
	 * time:2019-11-4下午9:32:06
	 */
	@GetMapping("/identText")
	@ResponseBody
	public AjaxResult identText(HttpServletRequest request){
         String text = request.getParameter("text");
         String text2 = IdentTextUtils.identText(text);
		AjaxResult result = new AjaxResult();
		result.setMsg(text2);
		return result;
	}
	
	/**
	 * 分页获取文章
	 */
	@GetMapping(value="/getArticleList")
	@ResponseBody
	@ApiOperation(value = "分页获取文章",httpMethod = "GET", response = ArticleController.class)
	public LayuiAPiResult getArticleList(@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="limit",defaultValue="10")Integer limit,
			@RequestParam(value="send_name",defaultValue="")String send_name){
		LayuiAPiResult result = new LayuiAPiResult();
		
		FindArticleDTO findArticleDTO = new FindArticleDTO();
		findArticleDTO.setPage(page);
		findArticleDTO.setLimit(limit);
		findArticleDTO.setSearch(send_name);
		
		Page<Article> pageVo =articleService.getArticleList(findArticleDTO);
		if (!StringUtils.isEmpty(pageVo.getRecords())) {
	    	result.setCode(0);
			result.setMsg("请求成功");
			result.setCount(Integer.parseInt(String.valueOf(pageVo.getTotal())));
			result.setData(pageVo.getRecords());
			result.setPage(page);
			result.setLimit(limit);
		}else {
			result.setCode(1);
			result.setMsg("请求失败");
		}
		return result;
	}
	
	/*
	 * 根据ID删除文章
	 * */
	@PostMapping(value="/deleteById")
	@ResponseBody
	@ApiOperation(value = "根据ID删除文章",httpMethod = "POST", response = ArticleController.class)
	public Object deleteById(HttpServletRequest request,HttpSession session){
    String id = request.getParameter("id");
		SysUserEntity user = (SysUserEntity) session.getAttribute("loginUser");
		if (StringUtils.isEmpty(user)) {
			//用户删除文章
			user = (SysUserEntity) session.getAttribute("webLoginUser");
		} 
		  AjaxResult result = new AjaxResult();
			if (!StringUtils.isEmpty(id)) {
				result = articleService.removeById(id,user);
			}else {
				result.setSuccess(false);
				result.setMsg("参数ID不能为空");
			}
		return result;
	}
	
	/**
	 * 批量删除文章
	 */
	@PostMapping("/batchRemoveTitle")
	@ResponseBody
	@ApiOperation(value = "批量删除文章",httpMethod = "POST", response = ArticleController.class)
	public Object batchRemoveTitle(@RequestBody List<Article> entityList,HttpSession session){
		SysUserEntity user = (SysUserEntity) session.getAttribute("loginUser");
		AjaxResult result=new AjaxResult();
		  if (StringUtils.isEmpty(entityList)) {
			  result.setMsg("参数不能为空");
			  result.setSuccess(false);
		  }
		  Integer state = articleService.batchRemoveTitle(entityList,user);
		  if (state == 1) {
			  result.setSuccess(true);
			  result.setMsg("操作成功");
		  }else if (state ==0) {
			  result.setSuccess(false);
			  result.setMsg("操作失败");
		  }
		return result;
	}
	
	

}
