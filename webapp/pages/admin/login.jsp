<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<title>系统界面主页</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/css.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/md5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/miniui/jquery.js"></script>
<script type="text/javascript">
	
	function validate(form){
		if($("#loginname").val() == ""||$("#loginname").val() == "请输入用户名"){
			alert("请输入用户名");
			$("#loginname").focus();
			return false;
		}
		if($("#password").val() == ""){
			alert("请输入密码!");
			$("#password").focus();
			return false;
		}
		$("#password").val(MD5($("#password").val()));
		return true;
	}
	function onFocusLoginname(){
		if($("#loginname").val() == "请输入用户名"){
			$("#loginname").val("");
		}
	}
	function onBlurLoginname(){
		if($("#loginname").val() == ""){
			$("#loginname").val("请输入用户名") ;
		}
	}
	
	</script>
</head>

<body class="login-bj">

<div class="warp login_top"><img src="<%=request.getContextPath()%>/resources/images/login-logo.png"/></div>
<div class="login">
 <form name="loginForm" action="<%=request.getContextPath()%>/login.do" method="post" onsubmit="return validate(this);">
   <div class="warp">
      <div class="login_box">
         <div class="login_title">用户登录</div>
         <div class="login_show">
            <ul>
              <li><input name="loginname" id="loginname" onblur="onBlurLoginname();" onfocus="onFocusLoginname();" type="text" class="login-dl" value="请输入用户名"></li>
              <li><input name="password" id="password"  type="password" class="login-mm" value=""></li>
             
              <li><input name=""  type="submit" class="login-btn" value=""></li>
            </ul>
         </div>
         <br/>
         <br/>
          <div><font color="red"> <b> ${message}</b></font></div>
      </div>
   </div>
   </form>
</div>
<div class="login_foot">
Copyright @ 2017 亚泰教育
</div>
</body>
</html>
