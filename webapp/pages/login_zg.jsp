<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>用户登录</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/login.css"/>
	<script type="text/javascript" src="<c:url value="/resources/javascript/md5.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/miniui/jquery.js"/>"></script>
	<style type="text/css">
    .login_show ul li .li_btn{ height: 35px; line-height: 35px; width: 300px; background: url(<%=request.getContextPath()%>/resources/images/login/loginbtn.png) left top no-repeat; font-family: 微软雅黑; font-size: 14px; text-align: center; color: #fff; cursor: pointer; border: none; }
    .login_show ul li .li_one{ width: 39px; height: 30px; border-top: 1px solid #bdbdbd; border-bottom: 1px solid #bdbdbd;  float: left; display: inline; background: url(<%=request.getContextPath()%>/resources/images/login/icon.png) -8px -76px no-repeat; }
    .login_show ul li .li_two{ width: 39px; height: 30px; border-top: 1px solid #bdbdbd; border-bottom: 1px solid #bdbdbd; float: left; display: inline; background: url(<%=request.getContextPath()%>/resources/images/login/icon.png) -47px -76px no-repeat; }
    .login_show ul li .li_three{ width: 39px; height: 30px;border-top: 1px solid #bdbdbd; border-bottom: 1px solid #bdbdbd;  float: left; display: inline; background: url(<%=request.getContextPath()%>/resources/images/login/icon.png) -86px -76px no-repeat; }
    #in_ymyl{text-decoration:underline;color:#D50003;font-weight: bold;}
	</style>	
	<script type="text/javascript">
	
	function validate(form){
		if(document.getElementById("loginname").value == ""||document.getElementById("loginname").value == "请输入用户名"){
			alert("请输入用户名");
			document.getElementById("loginname").focus();
			return false;
		}
		if(document.getElementById("password").value == ""){
			alert("请输入密码!");
			document.getElementById("password").focus();
			return false;
		}
		document.getElementById("password").value=MD5(document.getElementById("password").value);
		return true;
	}
	function onFocusLoginname(){
		if(document.getElementById("loginname").value == "请输入用户名"){
			document.getElementById("loginname").value = "";
		}
	}
	function onBlurLoginname(){
		if(document.getElementById("loginname").value == ""){
			document.getElementById("loginname").value = "请输入用户名";
		}
	}
	
	function onFocusCheckCode(){
		if(document.getElementById("checkCode").value == "请输入验证码"){
			document.getElementById("checkCode").value = "";
		}
	}
	function onBlurCheckCode(){
		if(document.getElementById("checkCode").value == ""){
			document.getElementById("checkCode").value = "请输入验证码";
		}
	}
	/**
	 * 刷新验证码
	 * @param imgObj 验证码Img元素
	 */ 
	function refreshCode(imgObj) { 
	    if (!imgObj) { 
	        imgObj = document.getElementById("validationCode"); 
	    } 
	 
	    var index = imgObj.src.indexOf("?"); 
	    if(index != -1) { 
	        var url = imgObj.src.substring(0,index + 1); 
	        imgObj.src = url + Math.random(); 
	    } else { 
	        imgObj.src = imgObj.src + "?" + Math.random(); 
	    } 
	}
	/**
           验证码反馈
	**/
	function onValidateCode(){
		var checkCode=document.getElementById("checkCode").value.replace(/(^\s*)|(\s*$)/g, '');
		if(checkCode.length==4){
			var url='<%=request.getContextPath()%>/validationCode/equalsCode.action';
			$.post(url, { checkCode: checkCode},
					   function(data){
					     var png_right='<%=request.getContextPath()%>/resources/images/login/right.png';
					     var png_wrong='<%=request.getContextPath()%>/resources/images/login/wrong.png';
					     if(data=='success') $("#codePng").attr("src",png_right).show();
					     if(data=='false') $("#codePng").attr("src",png_wrong).show();
					   });
		}else{
			$("#codePng").hide();
		}
	}
	</script>
</head>


<body class="login">
<div class="login">
<div class="login_title">
    <p class="p1">用户登陆</p>
    <!-- <p class="p_fanhui"><a href="<%=request.getContextPath()%>"><img src="<%=request.getContextPath()%>/resources/images/login/fanhui.png" border="0"/></a></p> -->
  </div>
  <div class="login_show">
 <form name="loginForm" action="<%=request.getContextPath()%>/login.action" method="post" onsubmit="return validate(this);">
    <ul>
      <li>
        <div class="li_one"></div>
        <div class="li_box">
          <input type="text" id="loginname" name="loginname" onblur="onBlurLoginname();" onfocus="onFocusLoginname();" value="请输入用户名" />
        </div>
      </li>
      <li>
        <div class="li_two"></div>
        <div class="li_box">
          <input type="password" name="password" id="password" />
         <input type="hidden"  name="pid" id="pid" value="1001" />
        </div>
      </li>
      <li>
        <div class="li_three"></div>
        <div class="li_box_two">
        <input id="checkCode" type="text" name="checkCode" onblur="onBlurCheckCode();" maxlength="4" onkeyup="onValidateCode();" onfocus="onFocusCheckCode();" value="请输入验证码" />
       
        </div>
        <div style="width:25px; margin-left:5px; float:left;"> <img id="codePng" /></div>
        <div class="li_yanzheng"><img alt="点击切换" id="validationCode" src="<%=request.getContextPath()%>/validationCode/captchaToken.action" onclick="refreshCode();" /></div>
      </li>
      <li>
        <input type="submit"  class="li_btn" value="登录" />
      </li>
    </ul>
</form>
      <div class="login_zhaohui"><font color="red"> <b> ${message}</b></font></div>
  </div>

</body>
<script>
$("#codePng").hide();
</script>
</html>
