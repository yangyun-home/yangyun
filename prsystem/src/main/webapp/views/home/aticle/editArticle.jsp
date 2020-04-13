<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>编辑文章</title>
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
   padding-top: 15px !important;
 }
  .layui-layer-lan .layui-layer-content{
     padding: 15px;
  }
  
  .layui-layedit{
	  overflow-x:auto;
	}
	
	img{
	     width:60%;
	     height:60%;	    
	}
	
	#LAY_layedit_1 > img{
	     width:60%;
	     height:60%;	
	}
	
	.layui-elem-quote.title {
		padding: 10px 15px;
		margin-bottom: 0;
	}
	
	.sysNotice {
		width: 75%;
	}
	.code{
		color: #c7254e;
		background-color: #f9f2f4;
		border-radius: 2px;
		padding: 4px 2px 0;
	}
</style>
</head>

<body>
 <!--头部信息 -->
<%@ include file="../../home/common/header.jsp" %>
 <div class="layui-container article_content" >
    <div class="layui-row">
        <div class="layui-col-xs12 layui-col-sm12 layui-col-md12">
	         <form class="layui-form" action="">
			  <div class="layui-form-item">
			    <label class="layui-form-label">文章名称</label>
			    <div class="layui-input-block">
			      <input type="text" name="articleTitle" maxlength="30" id="article_Title" required lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
			    </div>
			  </div>
			  
			  <div class="layui-form-item">
			    <label class="layui-form-label">所属分类</label>
			    <div class="layui-input-block">
			      <select name="categoryId" id="category_Id" lay-search lay-verify="required">
			        
			      </select>
			    </div>
			  </div>
			  
			   <div class="layui-form-item">
			       <div class="layui-input-block">
			          <textarea name="articleContent" id="releaseArticleArea" class="layui-textarea" placeholder="详细描述" style="display: none;"></textarea>
			       </div>
			   </div>
			  <div class="layui-form-item">
			    <div class="layui-input-block">
			      <button class="layui-btn" id="releaseArticleForm">立即发布</button>
			      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
			      <button type="button" id="privewBtn" class="layui-btn layui-btn-primary">预览</button>
			    </div>
			  </div>
		  </form>
	    </div>
    </div>
 </div>
<!--底部信息 -->
<%@ include file="../../home/common/footer.jsp" %>
<script src="${APP_PATH}/js/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/layui-v2.4.5/layui/layui.js"></script>
<script src="${APP_PATH}/layer/layer.js"></script>
<script type="text/javascript">
layui.use(['element','layedit','form','jquery','laypage'], function(){
	var articles = {};
	var element = layui.element,
	  layedit = layui.layedit,
	  form = layui.form,
	  jquery = layui.jquery,
	  laypage =layui.laypage;
	  //getArticleInfo();
	
	//上传图片
	 layedit.set({
		 uploadImage:{
			 url:'${APP_PATH}/up_files/webUploadImg',
			 type:'post',
			 success: function (data) {
				 // console.log(data);
	             return false;
	        }
		 }
	 });
	
	//建立编辑器
var editIndex = layedit.build('releaseArticleArea',{
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
	
//建立编辑器之后在赋值
 getArticleInfo();
	
$("#privewBtn").click(function(){
	 layer.open({
            type:1,
            skin:'layui-layer-lan',
            area:['500px', '350px']
            ,title: '预览'
            ,content: layedit.getContent(editIndex)
	 });
 });
 
 
//提交文本
$("#releaseArticleForm").click(function(){
	 var content = layedit.getContent(editIndex);
	 var article = $.trim($("#article_Title").val());
	 var cid = $("#category_Id").val();
	 if(article =="" || article ==null){
		 layer.msg("文章名称不能为空", {time:2000, icon:5, shift:6}, function(){	});
		 return false;
	 }
	 if(article.length<1 || article.length>30){
		 layer.msg("文章名称长度在1-30个字符之间", {time:2000, icon:5, shift:6}, function(){});
		 return false;
	 }
	 if(content == "" ||content ==null){
		 layer.msg("文章内容不能为空", {time:2000, icon:5, shift:6}, function(){});
		 return false;
	 }
	 
	 
	 //发起后台请求
	 var loadingIndex = null;
        var requestParam= {};
        articles.articleTitle= article;
        articles.categoryId = cid;
        articles.articleContent = content;
        console.log(articles,55555)
   	    $.ajax({
	   		type : "POST",
	   		async:false,
	   		url  : "${APP_PATH}/article/updates",
	   		data : JSON.stringify(articles),
	   		dataType : "json",
	   		contentType:"application/json;UTF-8",
	   		beforeSend : function() {
	   			loadingIndex = layer.msg('处理中', {icon: 16,shade:0.3,});
	   		},
	   		success : function(result) {
	   			layer.close(loadingIndex);
	   			if ( result.state==1) {
	   				 layer.msg(result.msg, {time:2000, icon:6, shift:6,}, function(){});
	   				window.location.href="${APP_PATH}/index.html/toMyArticleUI";
	   			}else if( result.state == 0){
	                  layer.msg(result.msg, {time:2000, icon:5, shift:6,}, function(){});
	  
	   			}else if( result.state== 2){
	                      layer.msg(result.msg, {time:2000, icon:5, shift:6,}, function(){});  
		    		}else if( result.state== 4){
	                      layer.msg(result.msg, {time:2000, icon:5, shift:6,}, function(){});  
		    		}
	   		}
   	}); 
	 return false;
}); 
 
function getArticleInfo(){
	 var ar_id = "${id}";  
	   $.ajax({
	   type:"GET",
	   url:"${APP_PATH}/article/getArticleInfo",
	   data:{id:ar_id},
	   async:false,
	   success:function(result){
		    console.log(ar_id,editIndex,result,55)
		    articles = result.data;
		    $("#article_Title").val(result.data.articleTitle);
		    
		    layedit.setContent(editIndex,result.data.articleContent);

	        getCategory(result.data.categoryId);
	      },
	   error:function(result){
		  layer.msg('处理异常，请联系管理员',{time:2000,icon:5});
	   }
	   });
	   form.render(); 
} 
 
function getCategory(categoryId){
	console.log(categoryId,3333)
	$.ajax({
	   type:"GET",
	   url:"${APP_PATH}/category/getAllCategory",
	   async:false,
	   success:function(result){
		   var optionstring = ""; 
		   var data = result.data;
           for (var j = 0; j < data.length; j++) {  
                 optionstring += "<option value="+data[j].categoryId+">"+data[j].categoryName+"</option>";  
             }  
             $("#category_Id").html(optionstring); 
			 form.render('select'); 
	      },
	   error:function(result){
		  layer.msg('处理异常，请联系管理员',{time:2000,icon:5});
	   }
	});
}

 
form.render();
	
})


    
  
</script>
</body>
</html>