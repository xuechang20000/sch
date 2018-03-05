<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>亚太教育</title>
	<script type="text/javascript" src="<c:url value="/resources/miniui/boot.js"/>"></script>
<style type="text/css">
body html{padding:0px;}
.mymenu .mini-menu-float{float:right;}
.mini-splitter #leftTree .mini-outlookbar-groupTitle{font-size:10pt;height:28px;TEXT-ALIGN:left;}
.mini-splitter #p1 .mini-panel-title,.mini-outlookbar-groupTitle{float:none;TEXT-ALIGN: center;}
#layout1{width:100%;height:540px;margin:110px auto;margin-left:5px;padding:0px;}
#north{background:#00A2CA;}
#layout1 ul#nav{float:right;margin:0;}
#layout1 ul#nav li{display:inline-block;float:right;padding:0}
#layout1 ul#nav li a{display:inline-block; padding:0 10px; text-decoration:none; line-height:33px; color:#FFF; font-family:"\5FAE\8F6F\96C5\9ED1"; font-size:14px}
#layout1 ul#nav li a:hover{background:#0095BB}
#layout1 #userspan{padding-left:15px;float:left;line-height:33px;color:#FFF;}
#layout1 #userspan a{font-size:12px;color:#FFF;}
#leftTree .mini-outlookbar-border{height:444px;}
</style>
<script type="text/javascript">
if ( parent.location != document.location )
{
   parent.location = "<%=request.getContextPath()%>/pages/index.jsp";
}  
</script>
</head>
<body leftmargin="0px" topmargin="0px" style="background:no-repeat url(<c:url value="/resources/images/workspace/top_bg.png"/>) top center;">

<div id="layout1" class="mini-layout"  borderStyle="border:solid 0px #aaa;">
     <div id="north" title="north" region="north"  width=100% height='34' showSplitIcon="false" splitSize=1 allowResize="false" showCollapseButton="false" showHeader="false" >
      <c:if test="${sessionScope['com.wb.session.loginname']!=null}">
      <span id="userspan">帐号：${sessionScope["com.wb.session.loginname"] } &nbsp;&nbsp;姓名：${sessionScope["com.wb.session.name"] }&nbsp;&nbsp;
      	<a href="javascript:changePW()">修改密码</a>&nbsp;&nbsp;
      	<a href="<%=request.getContextPath()%>/logout.action">退出</a>
      </span>
      </c:if>
	  <ul id="nav">
		</ul>
     </div>
	 <div title="south" region="south" showSplit="false" showHeader="false" height="30" >
	        <div style="line-height:28px;text-align:center;cursor:default">Copyright © 亚太教育</div>
	 </div>

     <div title="center" region="center" style="border:0;background-color: #fff;" bodyStyle="overflow:hidden;"    showHeader="false" showCollapseButton="false">
      	<div id="centerSplitter" class="mini-splitter" style="width:100%;height:100%;"  borderStyle="border:1;">
      		<!-- ---------------- -->
		    <div size="14%" showCollapseButton="true" style="border:1;">
		    	<div id="p1" class="mini-panel" title="header"  style="width:auto;border:0;" bodyStyle="display:none">
		    	</div>
			         <div id="leftTree" class="mini-outlookmenu" style="height:444px;"  onitemclick="onItemSelect"
	            	idField="id" parentField="pid" textField="text" borderStyle="border:1px;">
	        		 </div>
        	</div>
		    <!-- ---------------- -->
		    <div showCollapseButton="false" style="border:1;">
		        <div class="mini-tabs"  id="tabs1" activeIndex="0" style="width:100%;height:100%;" plain='false'>
				    <div title="首页" name="init" >
				         <iframe id="mainframe" src="<%=request.getContextPath()%>/workspace/loadWelcomPage.action" frameborder="0"  style="width:100%;height:100%;" border="0"></iframe>
				    </div>
				</div>
		    </div> 
		    <!-- ---------------- -->       
		</div>
     </div>
</div>

<script type="text/javascript">
mini.parse();

function onItemSelect(e) {
    var item = e.item;
    src = '<%=request.getContextPath()%>'+item.url;
    nodeid = "tab$" + item.id;
    text =item.text;

    var tabs = mini.get("tabs1");
    var tab = tabs.getTab(nodeid);
    if(!tab){
    	tab = {};
        tab.title=text;
    	tab.showCloseButton = true;
        tab.name = nodeid;
        tab.url=src;
        tabs.addTab(tab);
        }
    tabs.activeTab(tab);
}
function changePW(){
	 src = '<%=request.getContextPath()%>/pages/updatePassword.jsp';
	    nodeid = "tab$chpw";
	    text ='修改密码';

	    var tabs = mini.get("tabs1");
	    var tab = tabs.getTab(nodeid);
	    if(!tab){
	    	tab = {};
	        tab.title=text;
	    	tab.showCloseButton = true;
	        tab.name = nodeid;
	        tab.url=src;
	        tabs.addTab(tab);
	        }
	    tabs.activeTab(tab);
}
function displayLeft(pid,parentMenuTitle){
	//展开左侧导航
	 var layout = mini.get("layout1");
	 layout.updateRegion("west", { visible: true });
	 layout.updateRegion("west", { expanded: true });
	//展开左侧面板
	mini.get("centerSplitter").expandPane(1);
	//替换左侧标题
	$(".mini-splitter #p1 .mini-panel-title").html(parentMenuTitle);
	//加载左侧导航
	mini.get("leftTree").load("<%=request.getContextPath()%>/workspace/loadLeftResources.action?pid="+pid);
}
$.each($(".mini-menubar .mini-menuitem"),function(i){
	$(this).bind("click",function(){
		     //导航栏ID
			var pid=$(this).attr("id");
			 //导航栏标题
			var parentMenuTitle=$(this).find(".mini-menuitem-text").html();
			displayLeft(pid,parentMenuTitle);			
		});
});

function onLoadPage(){
	var url="<%=request.getContextPath()%>/workspace/loadTopParentResources.action";
	$.getJSON(url, function(json){
		  var i=0;
		  var lis='';
		  for(i=0;i<json.length;i++){
			  if(i==0){
				  displayLeft(json[i].id,json[i].text);
				  }
			     lis=lis+'<li><a href=\'javascript:displayLeft('+json[i].id+',\"'+json[i].text+'\");\'>'+json[i].text+'</a></li>';
			  }
		  $("#nav").html(lis);
	});
}
onLoadPage();
</script>
</body>
</html>
