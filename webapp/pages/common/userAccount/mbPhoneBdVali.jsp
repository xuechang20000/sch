<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>




<html>
<head> <title>手机绑定</title> 
<link
	href='<html:rewrite forward="style_qdyth_core"/>' rel="stylesheet"
	type="text/css" /> 

<SCRIPT type="text/javascript" src='<html:rewrite forward="coreJS"/>'></SCRIPT>
<SCRIPT type="text/javascript" src='<html:rewrite forward="tabJS"/>'></SCRIPT>
<script type="text/javascript" src='<html:rewrite forward="typesJS"/>'></script>
<script type="text/javascript" src='<html:rewrite forward="guidelineValidatorJS"/>'></script>
<SCRIPT type="text/javascript" src='<html:rewrite forward="composevalidatorJS"/>'></SCRIPT>

<script type="text/javascript">
	function query(){
		var code=document.getElementById('code').value;
		if(!code){
			alert('请输入验证码！');
			return false;
		}
		document.forms[0].action="${pageContext.request.contextPath}/findPassword/mbPhoneBdVali.action";
		document.forms[0].submit();
	}
	function sendMb(){
		document.getElementById('button_query').disable=true;
		var aac002=document.getElementById('aac002').value;
		var password=document.getElementById('password').value;
		var phone=document.getElementById('phone').value;
		if(!aac002){
			alert('请输入身份证号！');
			return;
		}
		if(!password){
			alert('请输入密码！');
			return;
		}
		if(!phone){
			alert('请输入手机号码！');
			return;
		}
		document.forms[0].submit();
	}
	function back(){
		window.history.back();
	}
	/*function init(){
		var flag='${flag}';
		if(flag=='success'){
			document.getElementById("idcardRow").style.display="none";
			document.getElementById("passwordRow").style.display="none";
			document.getElementById("phoneRow").style.display="none";
			document.getElementById("codeRow").style.display="block";
			document.getElementById("button_query").onclick="query()";
			document.getElementById("send").style.display="block";
			document.getElementById("aac002").value='${aac002}';
			document.getElementById("password").value='${password}';
			document.getElementById("phone").value='${phone}';
		}
	}*/
</script>
</head>

<body>
		<br><br><br><br>
	    <table width="731px" cellpadding="0" cellspacing="0" class="table_box" align="center">
	    		<tr>
	    			<td colspan="3">
	    				<table width="731px" cellpadding="0" cellspacing="0" class="title_box">
				    		<tr>
				    			<td class="title_left" width="6"></td>
				    			<td class="title_bg" width="719"><div class="news_content_icon">手机绑定</div></td>
				    			<td class="title_right"  width="6"></td>
				    		</tr>	
	    				</table>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="left_bg" width="3"></td>
	    			<td width="725" height="300" class="content" valign="top">
						<table width="85%" border="0" align="center" cellpadding="3">
							<tr>
								<td width="20%"></td>
								<td height="20" align="left" valign="middle">
									<logic:messagesPresent><img src='<c:url value="/resource/images/szyth/login_icon1.gif"/>' width="14" height="14" align="absmiddle" > <span class="text3"><html:errors/></span></logic:messagesPresent>										
								</td>
							</tr>
						</table>	
						
						<form  name="processForm" style="margin:0" method="post" action="${pageContext.request.contextPath}/findPassword/mbPhoneBd.action" >
						<input name="aac002" id="aac002"  type="hidden" class="login_input1" size="20" maxlength="18" value="${aac002}"/>    			         
						<input name="password" id="password"  type="hidden" class="login_input1" size="20" maxlength="18" value="${password}" />    			         
						<input name="phone" id="phone"  type="hidden" class="login_input1" size="20" maxlength="18" value="${phone}"/>
							<div id="msg"></div>
							<table width="85%" border="0" align="center" cellpadding="3" cellspacing="0" class="form_style">
								   
								<tr id="codeRow">
									<td><font color="red">*</font>验证码：</td>
									<td><input type="text" id="code" name="code" class="login_input1"/></td>
								</tr>
							</table>	
							<table width="85%" border="0" align="center" cellpadding="3">
								<tr>
									<td align="right"><input type="button" value="提交" onclick="query()" id="button_query" class="menu_bj_1"/></td>
									<td align="left"><input type="button" value="重 置" onclick="reset()" class="menu_bj_1"/></td>
									<td><input type="button" value="重新发送" onclick="sendMb()" class="menu_bj_1" id="send"/></td>  
								</tr>	
							</table>	
	    				</form>
	    			</td>
	    			<td class="right_bg" width="3"></td>
	    		</tr>
	    		<tr>
	    			<td class="bottom_left"></td>
	    			<td class="bottom_bg"></td>
	    			<td class="bottom_right"></td>
	    		</tr>
	    	</table>	
	</body>
</html>
