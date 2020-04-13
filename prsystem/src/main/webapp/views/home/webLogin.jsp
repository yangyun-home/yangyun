<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<style type="text/css">
  body{
    display:flex;
    justify-content: center;
  }
  .layui-form{
    margin-top: 20px;
    padding:20px;
    background-color:#f9f5f5;
    border-radius:8px;
  }
</style>
<title>用户登录</title>
<%
 pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<link rel="stylesheet" href="${APP_PATH}/layui-v2.4.5/layui/css/layui.css">
</head>
<body>
<form class="layui-form" action="">
  <div class="layui-form-item">
    <label class="layui-form-label">手机号</label>
    <div class="layui-input-inline">
      <input type="text" name="phone" maxlength="11" required lay-verify="phone" placeholder="请输入手机号" autocomplete="off" class="layui-input" style="width:192px;">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">密码</label>
    <div class="layui-input-inline">
      <input type="password" name="password" maxlength="30" required lay-verify="password" placeholder="请输入密码" autocomplete="off" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item">
        <label class="layui-form-label">验证码</label>
     <div class="layui-input-inline">
      <input type="text" maxlength="4" name="verifyCode" lay-verify="verifyCode" class="layui-input" id="j_captcha" placeholder="验证码">
     </div>
     <div class="layui-form-mid layui-word-aux" style="padding:0 !important;">
          <img id="captcha_img" alt="点击更换" title="点击更换" style="cursor: pointer;"
            onclick="changeVerifyCode(this)" src="${APP_PATH}/Kaptcha"/>
     </div>
    
  </div>
  
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" type="button" lay-submit lay-filter="userLoginForm">立即登录</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
      <a class="layui-btn success" href="${APP_PATH}/index.html/toIndexUI">返回</a>
    </div>
  </div>
</form>     

<script src="${APP_PATH}/js/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/layui-v2.4.5/layui/layui.js"></script>
<script>
//Demo
layui.use('form', function(){
  var form = layui.form,
      layer = layui.layer;
//表单校验
  form.verify({
	  phone:function(value,item){
		  if(value==""){return '手机号不能为空';}
		  if(!new RegExp("^(13[0-9]|14[5-9]|15[0-3,5-9]|16[2,5,6,7]|17[0-8]|18[0-9]|19[0-3,5-9])\\d{8}$").test(value)){
	         return '请输入正确的手机号';
          }
	  },
	  password:function(value,item){
		  if(value==""){
				return '密码不能为空';
			}
		  if(!new RegExp("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{5,20}$").test(value)){
			  return '密码至少包含 数字和英文，长度5-20';
		  }
	  },
	  verifyCode:function(value,item){
		  if(value==""){
				return '验证码不能为空';
			}
	  }
	}); 
  
  //监听提交
  form.on('submit(userLoginForm)', function(data){
    console.log(JSON.stringify(data.field));
 // 1、用户发起登录请求
	var loadingIndex = null;
	$.ajax({
		type : "POST",
		url  : "${APP_PATH}/sysUser/webUserLogin",
		data : JSON.stringify(data.field),
		dataType : "json",
		contentType:"application/json;UTF-8",
		beforeSend : function() {
			loadingIndex = layer.msg('处理中', {icon: 16});
		},
		success : function(result) {
			//console.log(result)
			layer.close(loadingIndex);
			if ( result.state == 1) {
               layer.msg(result.msg, {time:1000, icon:6}, function(){
            	   window.localStorage.setItem('userInfo',JSON.stringify(result.data))
        		   window.location.href = "${APP_PATH}/index.html/toIndexUI";
               });
			} else if(result.state == 0){
               layer.msg(result.msg, {time:2000, icon:5, shift:6}, function(){
               	
               });
			} else if(result.state == 2){
                   layer.msg(result.msg, {time:2000, icon:5, shift:6}, function(){
                       	
                   });
    		} else if(result.state == 3){
                   layer.msg(result.msg, {time:2000, icon:5, shift:6}, function(){
                       	
                   });
    		} else if(result.state == 4){
                   layer.msg(result.msg, {time:2000, icon:5, shift:6}, function(){
                       	
                   });
   			}else if(result.state == 5){
                layer.msg(result.msg, {time:2000, icon:5, shift:6}, function(){
                   	
                });
 			}else if(result.state == 6){
                layer.msg(result.msg, {time:2000, icon:5, shift:6}, function(){
                   	
                });
 			}   
		}
	});
   return false;
  });
});

//切换验证码
function changeVerifyCode(img){
	console.log(1111,img);
	img.src="${APP_PATH}/Kaptcha?" + Math.floor(Math.random()*100);
}
</script>
</body>
</html>