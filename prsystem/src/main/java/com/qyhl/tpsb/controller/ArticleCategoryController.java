package com.qyhl.tpsb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qyhl.tpsb.commonutils.AjaxResult;
import com.qyhl.tpsb.entity.ArticleCategory;
import com.qyhl.tpsb.service.ArticleCategoryService;

@Controller
@RequestMapping(value="/category")
public class ArticleCategoryController {
	@Autowired
	private ArticleCategoryService articleCategoryService;
     
	@GetMapping("/getAllCategory")
	@ResponseBody
	public AjaxResult getAllCategory() {
		AjaxResult result = new AjaxResult();
		List<ArticleCategory> list = articleCategoryService.getList();
		result.setSuccess(true);
		result.setMsg("操作成功");
		result.setData(list);
		return result;
	}
}
