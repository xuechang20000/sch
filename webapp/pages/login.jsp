<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>亚太教育</title>
	<script type="text/javascript" src="<c:url value="/resources/miniui/boot.js"/>"></script>
<style type="text/css">
.mymenu .mini-menu-float{float:right;}
.mini-splitter #leftTree .mini-outlookbar-groupTitle{font-size:10pt;height:28px;TEXT-ALIGN:left;}
.mini-splitter #p1 .mini-panel-title,.mini-outlookbar-groupTitle{float:none;TEXT-ALIGN: center;}
#layout1{width:1024px;height:540px;margin:110px auto;padding:0px;}
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
     <div id="north" title="north" region="north"  width='1024' height='34' showSplitIcon="false" splitSize=1 allowResize="false" showCollapseButton="false" showHeader="false" >
	  <ul id="nav">
		</ul>
     </div>
	 <div title="south" region="south" showSplit="false" showHeader="false" height="30" >
	        <div style="line-height:28px;text-align:center;cursor:default">Copyright © 亚太教育</div>
	 </div>

     <div title="center" region="center" style="border:0;background-color: #fff;" bodyStyle="overflow:hidden;"    showHeader="false" showCollapseButton="false">
		    <!-- ---------------- -->
		    <div showCollapseButton="false" style="border:1;">
		        <div class="mini-tabs"  id="tabs1" activeIndex="0" style="width:100%;height:450px;"  plain='false'>
				    <div title="首页" name="init" >
				         <iframe id="mainframe" src="<%=request.getContextPath()%>/pages/login_zg.jsp" frameborder="0"  style="width:100%;height:100%;" border="0"></iframe>
				    </div>
				</div>
		    </div> 
		    <!-- ---------------- -->       
     </div>
</div>

<script type="text/javascript">
mini.parse();

</script>
</body>
</html>
