package com.qyhl.tpsb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.qyhl.tpsb.commonutils.AjaxResult;
import com.qyhl.tpsb.dto.article.FindArticleDTO;
import com.qyhl.tpsb.dto.collect.FindCollectDTO;
import com.qyhl.tpsb.entity.Article;
import com.qyhl.tpsb.entity.ArticleCollection;
import com.qyhl.tpsb.entity.SysUserEntity;
import com.qyhl.tpsb.service.CollectionService;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/user_collection")
public class CollectionController {
	@Autowired
	private CollectionService collectionService;
   
	@GetMapping("/collectionById/{id}")
	@ResponseBody
	@ApiOperation(value = "用户收藏文章",httpMethod = "GET", response = CollectionController.class)
	public AjaxResult collectionById(@PathVariable("id") Long id,HttpSession session) {
		System.out.println(id);
		SysUserEntity user = (SysUserEntity) session.getAttribute("webLoginUser");
		AjaxResult result=collectionService.collectionById(id,user);
		return result;
	}
	
	@GetMapping("/cancleCollectById/{id}")
	@ResponseBody
	@ApiOperation(value = "用户取消收藏文章",httpMethod = "GET", response = CollectionController.class)
	public AjaxResult cancleCollectById(@PathVariable("id") Long id,HttpSession session) {
		System.out.println(id);
		SysUserEntity user = (SysUserEntity) session.getAttribute("webLoginUser");
		AjaxResult result=collectionService.cancleCollectById(id,user);
		return result;
	}
	
	@PostMapping(value="/getCollectionByUserId")
	@ResponseBody
	@ApiOperation(value = "获取用户收藏",httpMethod = "POST", response = CollectionController.class)
	public AjaxResult getCollectionPage(@RequestBody FindCollectDTO findCollectDTO,HttpSession session){
		SysUserEntity user = (SysUserEntity) session.getAttribute("webLoginUser");
		
		AjaxResult result=new AjaxResult();
		Page<ArticleCollection> page = collectionService.getCollectionPage(findCollectDTO,user.getUserid());
		if (!StringUtils.isEmpty(page.getRecords())) {
		   result.setData(page);	
		}
		return result;
	}
}
