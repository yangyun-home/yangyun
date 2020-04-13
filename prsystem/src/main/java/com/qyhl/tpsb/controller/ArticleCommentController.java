package com.qyhl.tpsb.controller;

import javax.servlet.http.HttpServletRequest;
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

import com.qyhl.tpsb.commonutils.AjaxResult;
import com.qyhl.tpsb.entity.ArticleComment;
import com.qyhl.tpsb.entity.SysUserEntity;
import com.qyhl.tpsb.service.ArticleCommentService;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value="/comment")
public class ArticleCommentController {
	
	@Autowired
	private ArticleCommentService articleCommentService;
	
	@PostMapping("/add")
	@ResponseBody
	@ApiOperation(value = "发表评论",httpMethod = "POST", response = ArticleCommentController.class)
	public AjaxResult add(@RequestBody ArticleComment articleComment,HttpServletRequest request ,HttpSession session) {
		//获取服务端验证码，用于比较用户传来的验证码
		String verifyCodeByServer = (String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		AjaxResult result = new AjaxResult();
		SysUserEntity user = (SysUserEntity) session.getAttribute("webLoginUser");
		if (StringUtils.isEmpty(articleComment.getVerifyCode())) {
			result.setMsg("验证码不能为空");
			result.setSuccess(false);
		} else {
            if (!articleComment.getVerifyCode().equals(verifyCodeByServer)) {
            	result.setMsg("验证码不正确，请重新输入");
    			result.setSuccess(false);
			} else {
				if (StringUtils.isEmpty(user)) {
					result.setMsg("您未登录系统，请登录胡在操作");
					result.setSuccess(false);
				} else {
					if (StringUtils.isEmpty(articleComment)) {
						result.setMsg("请求参数不能为空");
						result.setSuccess(false);
					}else {
						result = articleCommentService.save(articleComment,user);
					}
				}
			}
		}
		
		return result;
	}
	
	@PostMapping("/reply")
	@ResponseBody
	@ApiOperation(value = "发表回复评论",httpMethod = "POST", response = ArticleCommentController.class)
	public AjaxResult reply(@RequestBody ArticleComment articleComment,HttpServletRequest request,HttpSession session) {
		//获取服务端验证码，用于比较用户传来的验证码
		String verifyCodeByServer = (String)request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		AjaxResult result = new AjaxResult();
		SysUserEntity user = (SysUserEntity) session.getAttribute("webLoginUser");
		if (StringUtils.isEmpty(articleComment.getVerifyCode())) {
			result.setMsg("验证码不能为空");
			result.setSuccess(false);
		} else {
            if (!articleComment.getVerifyCode().equals(verifyCodeByServer)) {
            	result.setMsg("验证码不正确，请重新输入");
    			result.setSuccess(false);
			} else {
				if (StringUtils.isEmpty(user)) {
					result.setMsg("您未登录系统，请登录胡在操作");
					result.setSuccess(false);
				} else {
					if (StringUtils.isEmpty(articleComment)) {
						result.setMsg("请求参数不能为空");
						result.setSuccess(false);
					}else {
						result = articleCommentService.reply(articleComment,user);
					}
				}
			}
		}
		return result;
	}
	
	@GetMapping("/getList/{id}")
	@ResponseBody
	@ApiOperation(value = "获取评论列表",httpMethod = "GET", response = ArticleCommentController.class)
	public AjaxResult getList(@PathVariable("id") Long id) {
		AjaxResult result = new AjaxResult();
		result = articleCommentService.getList(id);
		return result;
	}

}
