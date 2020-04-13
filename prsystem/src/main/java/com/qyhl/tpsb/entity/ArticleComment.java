package com.qyhl.tpsb.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qyhl.tpsb.commonutils.JsonLongSerializer;

/**
 * 文章评论
 * method：ArticleComment  
 * author：Qiu
 * time：2019-10-18 
 * @version 1.0.0
 */
@TableName("tb_article_comments")
public class ArticleComment extends BaseInfo implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * `comment_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `comment_user_id` bigint(32) DEFAULT NULL COMMENT '发表用户ID',
  `article_id` bigint(32) DEFAULT NULL COMMENT '评论博文ID',
  `comment_like_count` bigint(32) DEFAULT NULL COMMENT '点赞数',
  `comment_content` text COMMENT '评论内容',
  `reply_user_id` bigint(32) DEFAULT NULL,
  `reply_content` text,
  `parent_comment_id` bigint(20) DEFAULT NULL COMMENT '父评论ID',
	 */
	@TableId
	@JsonSerialize(using = JsonLongSerializer.class )
	private Long commentId;
	@JsonSerialize(using = JsonLongSerializer.class )
	private Long commentUserId;
	private String commentContent;
	@JsonSerialize(using = JsonLongSerializer.class )
	private Long articleId;
	@JsonSerialize(using = JsonLongSerializer.class )
    private Long replyUserId;
    private String replyContent;
	private Long commentLikeCount;
	@JsonSerialize(using = JsonLongSerializer.class )
	private Long parentCommentId;
	
	@TableField(exist=false)
	private String commentUserName;
	
	@TableField(exist=false)
	private String img;
	
	private String userName;
	@TableField(exist=false)
	private String verifyCode;

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Long getCommentUserId() {
		return commentUserId;
	}

	public void setCommentUserId(Long commentUserId) {
		this.commentUserId = commentUserId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Long getReplyUserId() {
		return replyUserId;
	}

	public void setReplyUserId(Long replyUserId) {
		this.replyUserId = replyUserId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Long getCommentLikeCount() {
		return commentLikeCount;
	}

	public void setCommentLikeCount(Long commentLikeCount) {
		this.commentLikeCount = commentLikeCount;
	}

	public Long getParentCommentId() {
		return parentCommentId;
	}

	public void setParentCommentId(Long parentCommentId) {
		this.parentCommentId = parentCommentId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCommentUserName() {
		return commentUserName;
	}

	public void setCommentUserName(String commentUserName) {
		this.commentUserName = commentUserName;
	}

	public String getimg() {
		return img;
	}

	public void setimg(String img) {
		this.img = img;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
	
	
}
