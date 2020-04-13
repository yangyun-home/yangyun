package com.qyhl.tpsb.service;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.IService;
import com.qyhl.tpsb.commonutils.AjaxResult;
import com.qyhl.tpsb.entity.ArticleComment;
import com.qyhl.tpsb.entity.SysUserEntity;

public interface ArticleCommentService extends IService<ArticleComment>{
    
	AjaxResult save(ArticleComment articleComment, SysUserEntity user);

	AjaxResult getList(Long id);

	AjaxResult reply(ArticleComment articleComment, SysUserEntity user);

}
