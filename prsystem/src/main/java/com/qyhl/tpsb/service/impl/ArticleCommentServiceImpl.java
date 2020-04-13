package com.qyhl.tpsb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qyhl.tpsb.commonutils.AjaxResult;
import com.qyhl.tpsb.commonutils.Constant;
import com.qyhl.tpsb.entity.ArticleComment;
import com.qyhl.tpsb.entity.SysUserEntity;
import com.qyhl.tpsb.mapper.ArticleCommentMapper;
import com.qyhl.tpsb.service.ArticleCommentService;
import com.qyhl.tpsb.service.SysUserService;
@Service
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements ArticleCommentService {

	@Autowired
	private SysUserService sysUserService;
	
	@Override
	public AjaxResult save(ArticleComment comment, SysUserEntity user) {
		AjaxResult result = new AjaxResult();
		// TODO Save user comment
		comment.setStatus(Constant.ONE);
		comment.setCreateId(user.getUserid());
		comment.setCreatePerson(user.getUsername());
		comment.setCreateTime(new Date());
		comment.setCommentUserId(user.getUserid());
		
		try {
			this.insert(comment);
			result.setMsg("发表评论成功");
			result.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			result.setMsg("发表评论失败");
			result.setSuccess(false);
			System.out.println(e);
		}
		return result;
	}
	
	@Override
	public AjaxResult reply(ArticleComment comment, SysUserEntity user) {
		AjaxResult result = new AjaxResult();
		// TODO Save user comment
		comment.setStatus(Constant.ONE);
		comment.setCreateId(user.getUserid());
		comment.setCreatePerson(user.getUsername());
		comment.setCreateTime(new Date());
        if (!StringUtils.isEmpty(comment.getCommentUserId())) {
			SysUserEntity userEntity = sysUserService.findUserById(comment.getCommentUserId());
			if (!StringUtils.isEmpty(userEntity)) {
				comment.setUserName("@ "+userEntity.getUsername());
			}
		}
		comment.setCommentUserId(user.getUserid());
		
		try {
			this.insert(comment);
			result.setMsg("回复评论成功");
			result.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			result.setMsg("回复评论失败");
			result.setSuccess(false);
			System.out.println(e);
		}
		return result;
	}

	@Override
	public AjaxResult getList(Long id) {
		AjaxResult result = new AjaxResult();
		// TODO getList
		EntityWrapper<ArticleComment> wrapper = new EntityWrapper<>();
		wrapper.eq("status", Constant.ONE)
		       .eq("article_id", id);
		try {
			List<ArticleComment> list = this.selectList(wrapper);
			if (!StringUtils.isEmpty(list)) {
				for(ArticleComment comment : list) {
					if (!StringUtils.isEmpty(comment.getCommentUserId())) {
						SysUserEntity user = sysUserService.findUserById(comment.getCommentUserId());
					    if (!StringUtils.isEmpty(user)) {
					    	comment.setCommentUserName(user.getUsername());
						    comment.setimg(user.getUserPhoto());	
						}
					}
				}
			}
			result.setSuccess(true);
			result.setMsg("操作成功");
			result.setData(list);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("操作失败");
		}
		return result;
	}

	

}
