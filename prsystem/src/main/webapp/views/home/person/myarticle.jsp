<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>我发布的文章</title>
<%
 pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<link rel="stylesheet" href="${APP_PATH}/layui-v2.4.5/layui/css/layui.css">
<link rel="stylesheet" href="${APP_PATH}/css/global/global.css">
<link rel="stylesheet" href="${APP_PATH}/css/admin/center.css">
</head>
<body>
 <!--头部信息 -->
<%@ include file="../../home/common/header.jsp" %>
 <div class="layui-container fly-marginTop fly-user-main">
  <ul class="layui-nav layui-nav-tree layui-inline" lay-filter="user">
     <li class="layui-nav-item">
      <a href="${APP_PATH}/views/home/person/person_center.jsp">
        <i class="layui-icon">&#xe620;</i>
                        基本设置
      </a>
    </li>
    <li class="layui-nav-item layui-this">
      <a href="${APP_PATH}/index.html/toMyArticleUI">
        <i class="layui-icon">&#xe609;</i>
                     文章管理
      </a>
    </li>
    <!-- <li class="layui-nav-item">
      <a href="index.html">
        <i class="layui-icon">&#xe612;</i>
                    用户中心
      </a>
    </li> -->
    <li class="layui-nav-item">
      <a href="${APP_PATH}/index.html/toMyMessageUI">
        <i class="layui-icon">&#xe611;</i>
                        我的消息
      </a>
    </li>
  </ul>

  <div class="site-tree-mobile layui-hide">
    <i class="layui-icon">&#xe602;</i>
  </div>
  <div class="site-mobile-shade"></div>
  
  <div class="site-tree-mobile layui-hide">
    <i class="layui-icon">&#xe602;</i>
  </div>
  <div class="site-mobile-shade"></div>
  
  
  <div class="fly-panel fly-panel-user" pad20>
    <!--
    <div class="fly-msg" style="margin-top: 15px;">
      您的邮箱尚未验证，这比较影响您的帐号安全，<a href="activate.html">立即去激活？</a>
    </div>
    -->
    <div class="layui-tab layui-tab-brief" lay-filter="user">
     <!--  <button type="button" class="layui-btn">立即发布文章<i class="layui-icon layui-icon-add-1"></i> </button> -->
      <ul class="layui-tab-title" id="LAY_mine">
        <li data-type="mine-jie" lay-id="index" class="layui-this">我的文章<span class="layui-badge" id="my_article">99+</span></li>
        <li data-type="collection" data-url="/collection/find/" lay-id="collection">收藏文章<span class="layui-badge" id="my_collect">66</span></li>
        <li data-type="mine-article" lay-id="article">发布文章</li>
        <li data-type="mine-article" lay-id="article">测试</li>
      </ul>
      <div class="layui-tab-content" style="padding: 20px 0;">
        <div class="layui-tab-item layui-show">
          <ul class="mine-view jie-row" id="my_article_list">
            <li>
              <a class="jie-title" href="../jie/detail.html" target="_blank">基于 layui 的极简社区页面模版</a>
              <i>2017/3/14 上午8:30:00</i>
              <a class="mine-edit" href="/jie/edit/8116">编辑</a>
              <em>661阅/10答</em>
            </li>
            <li>
              <a class="jie-title" href="../jie/detail.html" target="_blank">基于 layui 的极简社区页面模版</a>
              <i>2017/3/14 上午8:30:00</i>
              <a class="mine-edit" href="/jie/edit/8116">编辑</a>
              <em>661阅/10答</em>
            </li>
            <li>
              <a class="jie-title" href="../jie/detail.html" target="_blank">基于 layui 的极简社区页面模版</a>
              <i>2017/3/14 上午8:30:00</i>
              <a class="mine-edit" href="/jie/edit/8116">编辑</a>
              <em>661阅/10答</em>
            </li>
          </ul>
          <div id="LAY_page">
	          <!-- 分页插件 -->
			  <div id="my_article_page" style="margin:15px;"></div>
		</div>
        </div>
        
        <div class="layui-tab-item">
          <ul class="mine-view jie-row" id="my_collection_list">
            <li>
              <a class="jie-title" href="../jie/detail.html" target="_blank">基于 layui 的极简社区页面模版</a>
              <i>收藏于23小时前</i>  
            </li>
          </ul>
          <div id="LAY_page">
              <!-- 分页插件 -->
			  <div id="my_collection_page" style="margin:15px;"></div>
          </div>
        </div>
        
        <!-- 发布文章页面 -->
        <div class="layui-tab-item">
           <form class="layui-form" action="">
			  <div class="layui-form-item">
			    <label class="layui-form-label">文章名称</label>
			    <div class="layui-input-block">
			      <input type="text" name="articleTitle" maxlength="30" id="articleTitle" required lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
			    </div>
			  </div>
			  
			  <div class="layui-form-item">
			    <label class="layui-form-label">所属分类</label>
			    <div class="layui-input-block">
			      <select name="categoryId" id="categoryId" lay-search lay-verify="required">
			       <c:forEach items="${articleCategory}" var="category">
			          <option value="${category.categoryId}">${category.categoryName}</option>
			       </c:forEach>
			        
			       <!--  <option value="0">北京</option>
			        <option value="1">上海</option>
			        <option value="2">广州</option>
			        <option value="3">深圳</option>
			        <option value="4">杭州</option> -->
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
        
        <div class="layui-tab-item">
          <ul class="mine-view jie-row">
            <li>
              <a class="jie-title" href="../jie/detail.html" target="_blank">测试测试</a>
              <i>收藏于23测试888</i>  </li>
          </ul>
          <div id="LAY_page">
             <!-- 分页插件 -->
			  <div id="my_article_page" style="margin:15px;"></div>
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
var counts =0;
var con = "";

var page = 1;

//collection
var colCount = 5;

$(function(){
	var currentPage =1;
	var size =10;
	var param={
		page:currentPage,
		limit:size
	};
	myArticleData(param);
	myCollectionData(param);
});

//初始化我的文章列表
function myArticleData(param){
  $.ajax({
    	 url:"${APP_PATH}/article/getPageByUserId",
    	 data:JSON.stringify(param),
    	 type:"POST",
    	 dataType : "json",
 		 contentType:"application/json;UTF-8",
    	 success:function(res){
    		 counts = res.data.total;
    		 $("#my_article").text(counts)
    		 buildDataPage(res.data);
    	 }
      });
  } 
  
function buildDataPage(data){
	var content = "";
	//console.log(666222,data)
	$.each(data.records,function(index,item){
		  content +="<li><a class='jie-title' href=''../jie/detail.html' target='_blank'>"
		  +item.articleTitle+"</a><i>"+changeTime(item.createTime)+
		  "</i><a class='mine-edit' href='#' onclick='item_edit(\""
				  +item.articleId+"\")'>编辑</a><a class='mine-edit' href='#' onclick='item_detail(\""
				  +item.articleId+"\")'>查看</a><a class='mine-edit' style='background-color:#FF5722' href='#' onclick='item_del(\""
				  +item.articleId+"\")'>删除</a><em>"
		  +item.articleViews+"阅/"+item.articleCommentCount+"答</em></li>";
	  });
	  
	 $("#my_article_list").html(content);
}

// 编辑
function item_edit(id){
	console.log('edit',id)
	window.location.href="${APP_PATH}/article/toUpdateArticleUI?id="+id;
}
//查看详情
function item_detail(id){
	window.location.href="${APP_PATH}/article/details?id="+id;
}
// 删除
function item_del(a_id){
	if(a_id){
		console.log('del',a_id)
		layer.confirm('确定要删除文章吗? 是否继续',{icon: 3, title:'提示'}, function (index) {
            layer.close(index);
            //向服务端发送删除指令
            $.ajax({
 			   type:"post",
     		   url:"${APP_PATH}/article/deleteById",
     		   data:{id:a_id},
     		   async:false,
     		   dataType:"json",
     		   beforeSend : function() {
     			 var ii = layer.load();
     			//此处用setTimeout演示ajax的回调
	    		    setTimeout(function(){
	    		      layer.close(ii);
	    		    }, 2000);
	    		   },
     		   success:function(result){
     		     if(result.success){
     		    	layer.msg(result.msg,{time:2000,icon:6});
     		    	var param={
     		    			page:page,
     		    			limit:10
     		    		};
     		    	myArticleData(param);
     		     }else{
     		    	layer.msg(result.msg,{time:2000,icon:5});
     		     }	  
                },
     		   error:function(result){
     			  layer.msg('处理异常，请稍后再试',{time:2000,icon:5});
     		   }
 		   });
        });
	}
}
  
// 初始化我的收藏
function myCollectionData(data){
	$.ajax({
   	 url:"${APP_PATH}/user_collection/getCollectionByUserId",
   	 data:JSON.stringify(data),
   	 type:"POST",
   	 dataType : "json",
	 contentType:"application/json;UTF-8",
   	 success:function(res){
   		colCount = res.data.total;
   	    $("#my_collect").text(colCount)
   		renderMyCollect(res.data) 
   	 }
     });
}

function renderMyCollect(data){
	var content = "";
	//console.log(7777,data)
	$.each(data.records,function(index,item){
		  content +="<li><a class='jie-title' href='#' onclick='goDetail(\""+item.articleId+"\")'>"+item.articleName+"</a><a class='mine-edit' href='#' onclick='cancle_collect(\""+item.articleId+"\")'>取消收藏</a></li>";
	  });
	  
	 $("#my_collection_list").html(content);
}

//取消收藏
function cancle_collect(id){
	console.log('cancle_collect',id)
	var user = '<%=session.getAttribute("webLoginUser")%>'
		console.log(11,user,typeof user,id)
	if(user=='null'){
		 layer.msg('请登录后在操作',{time:2000,icon:5});
		 return false;
	}else{
		layer.confirm('确定要取消本篇文章的收藏吗? 是否继续',{icon: 3, title:'提示'}, function(index){
			layer.close(index);
			user_canclesCollect(id);
	    }) 
	}
}

function user_canclesCollect(id){
var flag = ''
	$.ajax({
	   type:"get",
	   url:"${APP_PATH}/user_collection/cancleCollectById/"+id,
	   async:false,
	   beforeSend : function() {
		   flag = layer.load();
	   },
	   success:function(result){
		   layer.close(flag)
		   if(result.success){
			   var param={
						page:page,
						limit:10
					};
					myCollectionData(param);
			   layer.msg(result.msg,{time:2000,icon:6});
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

//获取文章详情
function goDetail(id){
	console.log(id,77)
	window.location.href="${APP_PATH}/article/details?id="+id;
}

function toMessage(){
 window.location.href="${APP_PATH}/index.html/toMyMessageUI";
}
   
	//注意：导航 依赖 element 模块，否则无法进行功能性操作
	layui.use(['element','layedit','form','jquery','laypage'], function(){
	var element = layui.element,
	  layedit = layui.layedit,
	  form = layui.form,
	  jquery = layui.jquery,
	  laypage =layui.laypage;
	
	//我的文章分页器
	  laypage.render({
	    elem: 'my_article_page' //注意，这里的 test1 是 ID，不用加 # 号
	    ,count:counts //数据总数，从服务端得到
	    ,jump: function(obj, first){
	        //obj包含了当前分页的所有参数，比如：
	        var param={
	        		page:obj.curr,
	        		limit:obj.limit
	        	};
	        if(!first){
	          //do something
	          console.log("当前页"+obj.curr);
	          console.log("条数"+obj.limit);
	          page = obj.curr;
	          myArticleData(param);
	          //搜索方法可以放这里
	          
	        }
	      }
	  }); 
	
	//我的收藏分页器
	laypage.render({
	    elem: 'my_collection_page' //注意，这里的 test1 是 ID，不用加 # 号
	    ,count:colCount //数据总数，从服务端得到
	    ,jump: function(obj, first){
	        //obj包含了当前分页的所有参数，比如：
	        var param={
	        		page:obj.curr,
	        		limit:obj.limit
	        	};
	        if(!first){
	          //do something
	          console.log("当前页"+obj.curr);
	          console.log("条数"+obj.limit); 
	          myCollectionData(param);
	          //搜索方法可以放这里
	          
	        }
	      }
	  }); 
	 
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

	 $("#privewBtn").click(function(){
		 layer.open({
             type:1,
             skin:'layui-layer-lan',
             area:['500px', '350px']
             ,title: '预览'
             ,content: layedit.getContent(editIndex)
		 });
	 });
	 
	//审核文本内容是否合格，
	$("#articleTitle").blur(function(){
		var title = $.trim($("#articleTitle").val());
		if(title.length !==0){
		  $.ajax({
    		type : "GET",
    		async:false,
    		url  : "${APP_PATH}/article/identText",
    		data : {text:title},
    		success : function(result) {
    			var res = $.parseJSON(result.msg);
    			if(res.result.reject && res.result.reject.length>0){
    				if(res.result.reject[0].label==1){
    					layer.msg("标题含有[暴恐违禁]内容，请重新输入", {time:2000, icon:5, shift:6}, function(){});
    					return false;
    				}else if(res.result.reject[0].label == 2){
    					layer.msg("标题含有[文本色情]内容，请重新输入", {time:2000, icon:5, shift:6}, function(){});
    					return false;
    				}else if(res.result.reject[0].label == 3){
    					layer.msg("标题含有[政治敏感]内容，请重新输入", {time:2000, icon:5, shift:6}, function(){});
    					return false;
    				}else if(res.result.reject[0].label == 4){
    					layer.msg("标题含有[恶意推广]内容，请重新输入", {time:2000, icon:5, shift:6}, function(){});
    					return false;
    				}else if(res.result.reject[0].label == 5){
    					layer.msg("标题含有[低俗辱骂]内容，请重新输入", {time:2000, icon:5, shift:6}, function(){});
    					return false;
    				}else if(res.result.reject[0].label == 6){
    					layer.msg("标题含有无营养的内容，请重新输入", {time:2000, icon:5, shift:6}, function(){});
    					return false;
    				}
    			}
    		}
    	}); 
		}
	});
	
	 
     // 提交文本
	 $("#releaseArticleForm").click(function(){
		 var content = layedit.getContent(editIndex);
		 var article = $.trim($("#articleTitle").val());
		 var cid = $("#categoryId").val();
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
             requestParam.articleTitle= article;
             requestParam.categoryId = cid;
             requestParam.articleContent = content;
	    	$.ajax({
	    		type : "POST",
	    		async:false,
	    		url  : "${APP_PATH}/article/add",
	    		data : JSON.stringify(requestParam),
	    		dataType : "json",
	    		contentType:"application/json;UTF-8",
	    		beforeSend : function() {
	    			loadingIndex = layer.msg('处理中', {icon: 16,shade:0.3,});
	    		},
	    		success : function(result) {
	    			layer.close(loadingIndex);
	    			if ( result.state==1) {
	    				 layer.msg(result.msg, {time:2000, icon:6, shift:6,}, function(){
	    					 location.reload();	
	                       });
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
	 
	});

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
	
	
</script>
</body>
</html>
<style>
.layui-layer-lan .layui-layer-content{
     padding: 15px;
  }
	.sysNotice .layui-table {
		margin-top: 0;
		border-left: 5px solid #e2e2e2;
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