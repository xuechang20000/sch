<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>后台管理</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/css.css"/>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/miniui/boot.js "></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/frame.js "></script>
<%
	String username=(String)session.getAttribute("com.wb.session.name");
	if(username==null)
		response.sendRedirect("login.jsp");
%>
<script type="text/javascript">
if ( parent.location != document.location )
{
   parent.location = "<%=request.getContextPath()%>/pages/admin/index.jsp";
}  
</script>
<style>
body{background-color:#fff;}
#center .mini-layout-region-body {margin:0;padding:0}
#center .mini-layout-region-body  .mini-panel-border{border:0;}
.mini-layout-region-title{color:#0032a4}
.tcdl1{  line-height:36px;}
.tcdl1 a{font-size:12px; color:#fff;}
</style>
</head>
<body>

<div id="layout1" class="mini-layout" style="width:100%;height:650px;"  borderStyle="border:solid 1px #aaa;">
    <div title="north" region="north" height="80" showSplitIcon="true" showHeader="false" >
        <div class="head">
		  <div class="logo"><img src="<%=request.getContextPath()%>/resources/images/head.png"></div>
		  <div class="top-right">
		    <div class="top-welcome">您好，${sessionScope["com.wb.session.name"] }</div>
		    <!-- <div class="xgmm"><a href="#">修改密码</a></div> -->
		    <div class="tcdl1">
		    	<a href="javascript:changePW()">修改密码</a>&nbsp;&nbsp;
		    <a href='<%=request.getContextPath()%>/logout.action'>退出登录</a></div>
		  </div>
		</div>
    </div>
    <div title="后台管理" showProxyText="true" region="west" width="220" expanded="true" showSplitIcon="true">
	    <ul id="menu2" class="mini-menu">
    		<li onclick="loadMainPanel('group')">用户组织维护</li>
    		<li onclick="loadMainPanel('school')">学校专业级联维护</li>
    		<li onclick="loadMainPanel('emp')">员工信息查询</li>
    		<li onclick="loadMainPanel('dict')">数据字典维护</li>
		</ul>
		    <ul id="tree1" class="mini-tree" url="<%=request.getContextPath()%>/admin/queryTree.action" style="width:200px;padding:5px;" 
		        showTreeIcon="true" textField="text" idField="id" parentField="pid" resultAsTree="false" contextMenu="#contextMenu"
		        onendedit="onendedit" ondrop="ondrop" onnodedblclick="nodedblclick" allowLeafDropIn="true" allowDrag="true" allowDrop="true"
		        >
		    </ul>
    </div>
    <div title="center" region="center" >
       <div class="mini-panel" id="main_panel" bodyStyle="border:0;overflow:hidden" style="width:1080px;height:600px;"   showHeader="false" url="<%=request.getContextPath()%>/pages/welcome.jsp">
		
		</div>
    </div>
</div>

<ul id="contextMenu" class="mini-contextmenu"  onbeforeopen="onBeforeOpen">        
    <li>
        <span iconCls="icon-add">新增节点</span>
        <ul>
            <li onclick="onAddBefore">插入节点(前)</li>                
            <li onclick="onAddAfter">插入节点(后)</li>    
            <li onclick="onAddNode">插入子节点</li>                 
        </ul>
    </li>
     <li class="separator"></li>
    <li name="edit" iconCls="icon-edit" onclick="onEditNode">编辑节点</li>
    <li name="remove" iconCls="icon-remove" onclick="onRemoveNode">删除节点</li>        
</ul>

 <script type="text/javascript">
        mini.parse();

        var layout = mini.get("layout1");
		var curNode;
		var parentNode;
        function loadTree(){
        	var tree = mini.get("tree1");
        	
        	tree.loadData('<%=request.getContextPath()%>/admin/queryTree.action');
        	
			if(curNode!=null){
        	parentNode=tree.getParentNode (curNode);
        	tree.expandPath(parentNode);
			}
        }
        function onAddBefore(e) {
            var tree = mini.get("tree1");
            var node = tree.getSelectedNode();
			
            var newNode = {};
            tree.addNode(newNode, "before", node);
        }
        function onAddAfter(e) {
            var tree = mini.get("tree1");
            var node = tree.getSelectedNode();

            var newNode = {};
            tree.addNode(newNode, "after", node);
        }
        function onAddNode(e) {
            var tree = mini.get("tree1");
            var node = tree.getSelectedNode();

            var newNode = {};
            tree.addNode(newNode, "add", node);
        }
        function onEditNode(e) {
            var tree = mini.get("tree1");
            var node = tree.getSelectedNode();
            
            tree.beginEdit(node);
        }
        function onRemoveNode(e) {

            var tree = mini.get("tree1");
            var node = tree.getSelectedNode();

            if (node) {
                if (confirm("确定删除选中节点?")) {
                	
                	var json = mini.encode(node);
                	Web.util.request('<%=request.getContextPath()%>/admin/deleteTreeNode.action','post',{json:json},function(){
                		tree.removeNode(node);
                	});
                    
                   
                }
            }
        }
        function ondrop(e) {
           var dropNode=e.dragNode;
           var json = mini.encode(dropNode);
           Web.util.request('<%=request.getContextPath()%>/admin/updateTreeNode.action','post',{json:json},function(){});
        }
        function onendedit(e) {
        	 var tree = mini.get("tree1");
             var node = tree.getSelectedNode();
             var json = mini.encode(node);
             curNode=node;
         	 Web.util.request('<%=request.getContextPath()%>/admin/addTreeNode.action','post',{json:json},function(data){
         		node.id=data.id; 
         	 });
         	 
        }
       function onBeforeOpen(e) {
    	}
       function nodedblclick(e){
    	   var node =e.node;
    	   var id=node.id;
    	   if(node.text==null||node.text==''){
    		   mini.alert('请编辑节点名称！');
    		   return;
    	   }
    	   var panel = mini.get("main_panel");
    	   panel.load('<%=request.getContextPath()%>/pages/admin/main.jsp?id='+id);
       }
       function loadMainPanel(menu){
    	   var panel = mini.get("main_panel");
    	   if("group"==menu){
        	   panel.load('<%=request.getContextPath()%>/pages/admin/group.jsp');
    	   }
    	   if("school"==menu){
        	   panel.load('<%=request.getContextPath()%>/pages/admin/school.jsp');
    	   }
    	   if("emp"==menu){
        	   panel.load('<%=request.getContextPath()%>/pages/admin/empSearch.jsp');
    	   }
    	   if("dict"==menu){
        	   panel.load('<%=request.getContextPath()%>/pages/admin/dict.jsp');
    	   }
       }
      function  changePW(){
    	  var panel = mini.get("main_panel");
    	  panel.load('<%=request.getContextPath()%>/pages/updatePassword.jsp');
          }
    </script>
</body>
<style>
</style>
</html>
