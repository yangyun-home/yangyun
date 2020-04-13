<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>文章详情页</title>
<%
 pageContext.setAttribute("APP_PATH",request.getContextPath());
%>

<link rel="stylesheet" href="${APP_PATH}/layui-v2.4.5/layui/css/layui.css">
<link rel="stylesheet" href="${APP_PATH}/css/global/global.css">
<link rel="stylesheet" href="${APP_PATH}/css/admin/center.css">
<link rel="stylesheet" href="${APP_PATH}/layui-v2.4.5/layui/css/modules/layui-icon-extend/iconfont.css">

<style type="text/css">
.article_content{
   background: #fff;
 }
.layui-layer-lan .layui-layer-content{
     padding: 15px;
  }
.comment_wrapper{
  border-top:1px solid #eeeeee;
  padding:12px;
}  
.title_box{
  text-align:center;
  font-weight:bold;
}
pre{
  width:65vw;
}
.comment_list{
  padding-top:10px;
}

.content_user{
  display:flex;
  padding-top: 10px;
}

.comment_list_li>div:nth-child(2){
   padding:10px;
}

.comment_list_li:first-child{
   border-top:0;
}

.comment_list_li{
    border-top:1px solid #eeeeee;
}

.comment_list_li>div:last-child{
   padding-bottom:10px;
       cursor: pointer;
}

.content_user img{
    width: 45px;
    height: 45px;
    border-radius: 5px;
}
.user_info{
   padding-left: 10px;
}
.user_info >p:last-child{
  padding-top:3px;
}
.rely_ren{
  padding-right:5px;
  color:#01AAED;
}
.layui-textarea{
  marign:10px;
}
.fb_box{
  text-align:center;
}

.layui-layedit{
    width: 57vw;
}
.bottom_box{
  padding-top:12px;
  display: flex;
  align-items: flex-start;
  flex-direction: column;
}
.bottom_box button{
   margin-top:12px;
}
.verify_code{
  display:flex;
  flex-wrap: wrap;
}
.verify_code input{
  width:100px;
}
</style>
</head>

<body>
 <!--头部信息 -->
<%@ include file="../../home/common/header.jsp" %>
 <div class="layui-container">
    <div class="layui-row">
        <div class="layui-col-xs12 layui-col-sm12 layui-col-md12">
	          <div class="layui-row" style="margin:10px;">
			    <div class="layui-col-md12">
			        <h2 style="display:inline;margin-right:20px;"> ${articleInfo.articleTitle}</h2>
			        
		            <span title="发布用户"><span class="iconfont layui-icon-extendyonghu"></span>：${user.username}</span>
			        <span title="评论" style="margin-left:15px;">评论：254</span>
			        <span title="浏览量" style="margin-left:15px;">浏览量：12</span>
			        <span title="收藏" style="margin-left:15px;color:#ff700a;cursor: pointer;" onclick="conllection('${articleInfo.articleId}')"><span class="iconfont layui-icon-extendshoucang"></span>收藏</span>             
			    </div>
			    
			  </div>
			  
			   <div class="layui-row article_content" style="border:1px solid #bebcbc;border-radius:5px;">
				    <div class="layui-col-md12" id="content_box" style="margin:10px;">
				                
				    </div>
				    <div class="layui-col-md12 comment_wrapper" >
				         <h3 class="title_box">评论区</h3>
				         <ul class="comment_list" id="comment_list_box">
				            <li class="comment_list_li">
				               <div class="content_user">
				                    <img alt="" src="//t.cn/RCzsdCq">
				                    <div class="user_info">
				                        <p>张三</p>
			                            <p>2020-3-25 18:48:56</p>
				                    </div>
			                  </div>
			                  <div>
			                     <span class="rely_ren">@ 张三</span>
			                     <div>评论内容111</div>
		                       </div>
				              <div class="rely"><i class="layui-icon layui-icon-reply-fill"></i> 回复</div>
				            </li> 
				            <li class="comment_list_li">
				               <div class="content_user">
			                    <img alt="" src="//t.cn/RCzsdCq">
			                    <div class="user_info">
			                        <p>张三</p>
		                            <p>2020-3-25 18:48:56</p>
			                    </div>
			                  </div>
			                  <div><span class="rely_ren">@ 张三</span>评论内容1</div>
				                
				              <div class="rely"><i class="layui-icon layui-icon-reply-fill"></i>回复</div>
				            </li> 
				         </ul>           
				    </div>
			  
			         <div class="layui-col-md12 fb_box" style="margin:12px;">
			            <a name="comment_area"></a>
		                <textarea name="articleContent" id="commentArticleArea" class="layui-textarea" placeholder="详细描述" style="display: none;"></textarea>
		                <div class="bottom_box">
		                  <div class="verify_code">
		                      <img id="captcha_img" alt="点击更换" title="点击更换" style="cursor: pointer;"
					            onclick="changeVerifyCode(this)" src="${APP_PATH}/Kaptcha"/>
					          <input type="text" maxlength="4" name="verifyCode" lay-verify="verifyCode" class="layui-input" id="j_captcha" placeholder="验证码">
		                  </div>
				          <button class="layui-btn" id="submitArticleForm">立即发表</button>
				        </div>
			
		                 
				    </div>
			  </div>
	    </div>
	    
    </div>
    
 </div>
<!--底部信息 -->
<%@ include file="../../home/common/footer.jsp" %>
<script src="${APP_PATH}/js/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/layui-v2.4.5/layui/layui.js"></script>
<script src="${APP_PATH}/layer/layer.js"></script>
<script type="text/javascript">
var ar_id = "${id}";
var commentUserId = '';

layui.use(['element','layedit','form','jquery','laypage'], function(){
	var element = layui.element,
	  layedit = layui.layedit,
	  form = layui.form
	  
	  getArticleDetails();
	  
		//建立编辑器
	  var editIndex = layedit.build('commentArticleArea',{
	  	height:230,
	  	//重新定义工具
	  	tool: [
	  		  'strong' //加粗
	  		  ,'italic' //斜体
	  		  ,'underline' //下划线
	  		  ,'del' //删除线
	  		  ,'|' //分割线
	  		  ,'left' //左对齐
	  		  ,'center' //居中对齐
	  		  ,'right' //右对齐
	  		  ,'link' //超链接
	  		  ,'unlink' //清除链接
	  		  ,'face' //表情
	  		  ,'image' //插入图片
	  		  ,'code' //插入代码-----官方没有写上，但是代码中有
	  		  /* ,'preview' */
	  		]
	   }); 
	
	//提交文本
	$("#submitArticleForm").click(function(){
		 var content = layedit.getContent(editIndex);
		 if(content == "" ||content ==null){
			 layer.msg("文章内容不能为空", {time:2000, icon:5, shift:6}, function(){});
			 return false;
		 }
		 if(content.length>1000){
			 layer.msg("发表内容超出范围，字数在1000以内", {time:2000, icon:5, shift:6}, function(){});
			 return false;
		 }
		 var code = $.trim($("#j_captcha").val());
		 if(code==''){
			 layer.msg("验证码不能为空", {time:2000, icon:5, shift:6}, function(){});
			 return false;
		 }
		 //console.log(content.length)
		 var loadingIndex = null;
		 var comment= {};
		    comment.articleId = ar_id;
	        comment.commentUserId = commentUserId;
	        comment.userName = '';
	        comment.commentContent = content;
	        comment.verifyCode = code;
	        
	        console.log(comment,1456)
	        $.ajax({
		   		type : "POST",
		   		async:false,
		   		url  : commentUserId==''?"${APP_PATH}/comment/add":"${APP_PATH}/comment/reply",
		   		data : JSON.stringify(comment),
		   		dataType : "json",
		   		contentType:"application/json;UTF-8",
		   		beforeSend : function() {
		   			loadingIndex = layer.msg('处理中', {icon: 16,shade:0.3,});
		   		},
		   		success : function(result) {
		   			layer.close(loadingIndex);
		   			if ( result.success) {
		   				 layer.msg(result.msg, {time:2000, icon:6, shift:6,}, function(){});
		   				 getCommentList();
		   				commentUserId = '';
		   				$("#j_captcha").val('')
		   				layedit.setContent(editIndex,'');
		   				// window.location.href="${APP_PATH}/index.html/toMyArticleUI";
		   			}else{
		                  layer.msg(result.msg, {time:2000, icon:5, shift:6,}, function(){});  
			    	}
		   		}
	        })      
	})
	
//methods

	
	  
})

/********************************/
//获取评论列表
	function getCommentList(){
		$.ajax({
		   type:"GET",
		   url:"${APP_PATH}/comment/getList/"+ar_id,
		   async:false,
		   success:function(result){
			   //$("#content_box").html(result.data.articleContent)
			   var data = result.data;
			   var content = '';
			   $.each(data,function(index,item){
	    			  content +="<li class='comment_list_li'><div class='content_user'><img alt='' src='"
	    			  +item.img+"'><div class='user_info'><p>"
	    			  +item.commentUserName+"</p><p>"
	    			  +changeTime(item.createTime)+"</p></div></div><div><span class='rely_ren'>"
	    			  +item.userName+"</span><div>"
	    			  +item.commentContent+"</div></div><a href='#comment_area' class='rely' onclick='reply_com(\""+item.commentUserId+"\")'><i class='layui-icon layui-icon-reply-fill'></i> 回复</a></li>";
	    		  });
			   
			   $("#comment_list_box").html(content);
			  // console.log(ar_id,result,55)
	       },
		   error:function(result){
			  layer.msg('处理异常，请联系管理员',{time:2000,icon:5});
		   }
		   });
	}

	//回复
	function reply_com(value){
		console.log('@',value)
		commentUserId = value;
	}

	//获取文章详情
	function getArticleDetails(){
		console.log(44444)
		$.ajax({
			   type:"GET",
			   url:"${APP_PATH}/article/getInfoById",
			   data:{id:ar_id},
			   async:false,
			   success:function(result){
				   $("#content_box").html(result.data.articleContent)
				   getCommentList();
				   //console.log(ar_id,result,55)
		       },
			   error:function(result){
				  layer.msg('处理异常，请联系管理员',{time:2000,icon:5});
			   }
			});
	}
	
	function conllection(id){
		var user = '<%=session.getAttribute("webLoginUser")%>'
			//console.log(11,user,typeof user,id)
		if(user=='null'){
			 
			 layer.msg('请登录后在操作',{time:2000,icon:5});
			 return false;
		}else{
			layer.confirm('确定要收藏本篇文章吗? 是否继续',{icon: 3, title:'提示'}, function(index){
				layer.close(index);
				userCollection(id);
		    }) 
		}
	}
	//收藏
	function userCollection(id){
		var flag = ''
		$.ajax({
			   type:"get",
			   url:"${APP_PATH}/user_collection/collectionById/"+id,
			   async:false,
			   beforeSend : function() {
				   flag = layer.load();
			   },
			   success:function(result){
				   layer.close(flag)
				   if(result.success){
					   if(result.state==0){
						   layer.msg(result.msg,{time:2000,icon:6});
					   }
					   if(result.state==1){
						   layer.msg(result.msg,{time:2000,icon:6}); 
					   }
				   }else{
					   layer.msg(result.msg,{time:2000,icon:5}); 
				   }
	           },
			   error:function(result){
				   layer.close(flag)
				  layer.msg('处理异常，请稍后再试',{time:2000,icon:5});
			   }
		   });
	}
	    
//时间转化
function changeTime(timeStamp){
	    if(timeStamp > 0){
	        var date = new Date();  
	        date.setTime(timeStamp);  
	        var y = date.getFullYear();      
	        var m = date.getMonth() + 1;      
	        m = m < 10 ? ('0' + m) : m;      
	        var d = date.getDate();      
	        d = d < 10 ? ('0' + d) : d;      
	        var h = date.getHours();    
	        h = h < 10 ? ('0' + h) : h;    
	        var minute = date.getMinutes();    
	        var second = date.getSeconds();    
	        minute = minute < 10 ? ('0' + minute) : minute;      
	        second = second < 10 ? ('0' + second) : second;     
	        return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;       
	    }else{
	        return "";
	    }  
}

//切换验证码
function changeVerifyCode(img){
	console.log(1111,img);
	img.src="${APP_PATH}/Kaptcha?" + Math.floor(Math.random()*100);
}

</script>
</body>
</html>