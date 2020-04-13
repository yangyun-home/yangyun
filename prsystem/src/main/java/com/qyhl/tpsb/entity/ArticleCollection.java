package com.qyhl.tpsb.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qyhl.tpsb.commonutils.JsonLongSerializer;

@TableName("tb_user_collection")
public class ArticleCollection extends BaseInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@TableId
	@JsonSerialize(using = JsonLongSerializer.class )
	private Long collectionId;
	@JsonSerialize(using = JsonLongSerializer.class )
	private Long collectionUid;
	@JsonSerialize(using = JsonLongSerializer.class )
	private Long articleId;
	private String articleName;
	public Long getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(Long collectionId) {
		this.collectionId = collectionId;
	}
	public Long getCollectionUid() {
		return collectionUid;
	}
	public void setCollectionUid(Long collectionUid) {
		this.collectionUid = collectionUid;
	}
	public Long getArticleId() {
		return articleId;
	}
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	
	
	
	

}
