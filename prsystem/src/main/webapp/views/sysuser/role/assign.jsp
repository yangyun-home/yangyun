<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  <%
    pageContext.setAttribute("APP_PATH",request.getContextPath());
  %>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
     
	<link rel="stylesheet" href="${APP_PATH}/bootstrap-3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH}/ztree/zTreeStyle.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
  </head>

  <body>

    <div class="container">
      <div class="row">
 
        <div class="col-sm-9 col-md-10">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 角色数据列表</h3>
				
			  </div>
			  <div class="panel-body">
			      <button class="btn btn-success" onclick="doAssign()"><i class="glyphicon glyphicon-user"></i>立即分配权限</button>
                  <ul id="permissionTree" class="ztree"></ul>
			  </div>
			</div>
			 <h3><a href="${APP_PATH}/sys_user/toRoleMana">返回角色列表</a></h3>
        </div>
       
      </div>
      
    </div>

    <script src="${APP_PATH}/js/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap-3.3.7/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH}/layer/layer.js"></script>
	<script src="${APP_PATH}/ztree/jquery.ztree.all-3.5.min.js"></script>
        <script type="text/javascript">
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
			    
			    var setting = {
		    		check : {
		    		    enable : true 
		    		},
		    		async: {
		    			enable: true,
		    			url:"${APP_PATH}/permission/loadAssignTreeData?roleid=${roleid}",
		    			autoParam:["id", "name=n", "level=lv"]
		    		},
					view: {
						selectedMulti: false,
						addDiyDom: function(treeId, treeNode){
							var icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")
							if ( treeNode.icon ) {
								icoObj.removeClass("button ico_docu ico_open").addClass(treeNode.icon).css("background","");
							}
                            
						}
					}
			    };
			   
			    $.fn.zTree.init($("#permissionTree"), setting);
            });
            function doAssign() {
            	var loadingIndex = null;
            	//获取当前节点
            	var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
            	//获取已选中的节点
            	var nodes = treeObj.getCheckedNodes(true);
            	if ( nodes.length == 0 ) {
                    layer.msg("请选择需要分配的许可信息", {time:2000, icon:5, shift:6}, function(){
                    	
                    });
            	} else {
            		var d = "roleid=${roleid}";
            		$.each(nodes, function(i, node){
            			d += "&permissionids="+node.id;
            		});
            		$.ajax({
            			type : "POST",
            			url  : "${APP_PATH}/role/doAssignPermission",
            			data : d,
            			beforeSend : function(){
                			loadingIndex = layer.msg('处理中', {icon: 16});
                		},
            			success : function (result) {
            				layer.close(loadingIndex);
            				if ( result.success ) {
                                layer.msg("分配许可信息成功", {time:2000, icon:6}, function(){
                                	
                                });
            				} else {
                                layer.msg("分配许可信息失败", {time:2000, icon:5, shift:6}, function(){
                                	
                                });
            				}
            			}
            		});
            	}
            }
        </script>
  </body>
</html>
