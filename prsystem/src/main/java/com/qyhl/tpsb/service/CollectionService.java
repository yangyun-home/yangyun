package com.qyhl.tpsb.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.qyhl.tpsb.commonutils.AjaxResult;
import com.qyhl.tpsb.dto.collect.FindCollectDTO;
import com.qyhl.tpsb.entity.ArticleCollection;
import com.qyhl.tpsb.entity.SysUserEntity;

public interface CollectionService extends IService<ArticleCollection>{

	AjaxResult collectionById(Long id,SysUserEntity user);

	Page<ArticleCollection> getCollectionPage(FindCollectDTO findCollectDTO, Long userid);

	AjaxResult cancleCollectById(Long id, SysUserEntity user);

}
