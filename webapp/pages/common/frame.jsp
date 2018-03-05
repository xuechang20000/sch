
	<link rel="stylesheet" id="default__" type="text/css" href="<%=request.getContextPath()%>/resources/miniui/themes/default/skin.css"/>
	<link rel="stylesheet" id="blue__" type="text/css" href="<%=request.getContextPath()%>/resources/miniui/themes/blue/skin.css" DISABLED/>
	<link rel="stylesheet" id="olive2003__" type="text/css" href="<%=request.getContextPath()%>/resources/miniui/themes/olive2003/skin.css" DISABLED/>
	<link rel="stylesheet" id="blue2003__" type="text/css" href="<%=request.getContextPath()%>/resources/miniui/themes/blue2003/skin.css" DISABLED/>
	<link rel="stylesheet" id="blue2010__" type="text/css" href="<%=request.getContextPath()%>/resources/miniui/themes/blue2010/skin.css" DISABLED/>
	<link rel="stylesheet" id="bootstrap__" type="text/css" href="<%=request.getContextPath()%>/resources/miniui/themes/bootstrap/skin.css" DISABLED/>
	<link rel="stylesheet" id="gray__" type="text/css" href="<%=request.getContextPath()%>/resources/miniui/themes/gray__/skin.css" DISABLED/>
	<link rel="stylesheet" id="metro__" type="text/css" href="<%=request.getContextPath()%>/resources/miniui/themes/metro__/skin.css" DISABLED/>
	<link rel="stylesheet" id="metro-green__" type="text/css" href="<%=request.getContextPath()%>/resources/miniui/themes/metro-green/skin.css" DISABLED/>
	<link rel="stylesheet" id="metro-orange__" type="text/css" href="<%=request.getContextPath()%>/resources/miniui/themes/metro-orange/skin.css" DISABLED/>
	<link rel="stylesheet" id="jqueryui-uilightness__" type="text/css" href="<%=request.getContextPath()%>/resources/miniui/themes/jqueryui-uilightness/skin.css" DISABLED/>
	<link rel="stylesheet" id="jqueryui-humanity__" type="text/css" href="<%=request.getContextPath()%>/resources/miniui/themes/jqueryui-humanity/skin.css" DISABLED/>
	<link rel="stylesheet" id="jqueryui-excitebike__" type="text/css" href="<%=request.getContextPath()%>/resources/miniui/themes/jqueryui-excitebike/skin.css" DISABLED/>
	<link rel="stylesheet" id="jqueryui-cupertino__" type="text/css" href="<%=request.getContextPath()%>/resources/miniui/themes/jqueryui-cupertino/skin.css" DISABLED/>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/frame.js"></script>


<script type="text/javascript">
	
	var __miniuiSkin__;
	var __miniuiSkins__ = new Array('default__','blue__','blue2003__','blue2010__','bootstrap__','gray__','metro__',
						'olive2003__','metro-green__','metro-orange__','jqueryui-uilightness__','jqueryui-humanity__',
						'jqueryui-excitebike__','jqueryui-cupertino__');
	if(document.cookie.length>0){
   		__miniuiSkin__ = getCookie__('miniuiSkin');
   		if(__miniuiSkin__){
   			for(var i=0;i<__miniuiSkins__.length;i++)
   				document.getElementById(__miniuiSkins__[i]).disabled = true;
   			document.getElementById(__miniuiSkin__+'__').disabled = false;
   			alert(document.getElementById(__miniuiSkin__+'__'));
   		}
    }
	
	function getCookie__(c_name) {
	   if(document.cookie.length>0){
		   c_start = document.cookie.indexOf(c_name + "=");
		   if (c_start != -1) {
		   c_start = c_start + c_name.length + 1;
		   c_end = document.cookie.indexOf(";", c_start);
		   if (c_end == -1) c_end = document.cookie.length;
		   	return unescape(document.cookie.substring(c_start, c_end));
		   }
		}
		return "no";
	 }
</script>
