<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<title>找回密码</title>
<style type="text/css">
body, div, ul, ol, li, dl, dt, dd, h1, h2, h3, h4, h5, h6, p, span, em, strong, img, form, fieldset, input, textarea, button, pre, table, tr, th, td, blockquote, code, label, cite, i { padding: 0; margin: 0 }
:focus { outline: 0 }
h1, h2, h3, h4, h5, h6 { font-size: 100% }
fieldset, img { border: 0 }
ul, ol, li { list-style-type: none }
table { border-collapse: collapse; border-spacing: 0 }
caption, th, tr, td { text-align: left }
u, del, ins { text-decoration: none }
label { cursor: pointer }
.clear { clear: both; font-size: 0px; line-height: 0px; height: 0px; overflow: hidden }
.clearfix { display: block; zoom: 1 }
.clearfix:after { content: "."; display: block; height: 0; clear: both; visibility: hidden }
.mt20 { margin-top: 20px; }
.box{ overflow:hidden; width:100%; overflow:hidden; margin-left:auto; padding-bottom:15px; margin-right:auto; border:1px solid #9acbf4;}
.box_title{ height:35px; line-height:35px; background:url(http://221.215.38.136/grcx/resource/images/wsbs/icon/news_content_icon.gif) 10px 10px  no-repeat #f4f2ff; padding-left:30px; font-size:14px; font-weight:700px; color:#01859e;}
.box_box{ margin-top:15px; overflow:hidden; max-width:600px;}
.box_box ul li{ overflow:hidden; margin-top:15px;}
.box_box ul li .box_test{ width:15%; float:left; display:inline; padding-right:1%; line-height:30px; font-size:14px; text-align:right;}
.box_box ul li .box_right{width:80%; float:left; display:inline; padding-left:2%;  text-align:left;}
.box_box ul li .box_right .input{ width:98%; height:30px; overflow:hidden; line-height:30px; padding-left:2%; overflow:hidden; border:1px solid #ddd; font-size:14px; color:#666;}
.test_one{ line-height:25px; font-size:14px; color:#A30002; padding-left:3%; margin-top:10px; margin-bottom:10px;}
.am-btn{ width:120px; height:35px; line-height:25px; cursor:pointer; margin-left:18%; text-align:center; border:none; background:#0e90d2; color:#fff; font-size:14px; }
.am-btn-two{ width:120px; height:35px; line-height:25px; cursor:pointer; margin-left:2%; text-align:center; border:none; background:#999; color:#fff; font-size:14px; }
.am-btn-three{ width:35%; height:32px; line-height:25px; cursor:pointer; margin-left:2%; text-align:center; border:none; background:#0e90d2; color:#fff; font-size:14px; }
</style>
<script type="text/javascript" src="<c:url value="/resources/miniui/jquery.js"/>"></script>


<script type="text/javascript">
	function query(){
		var aac002=document.getElementById('aac002').value;
		var code=document.getElementById('code').value;
		if(!code){
			alert('请输入验证码！');
			return false;
		}
		$.ajax({
			   type: "POST",
			   async: true,
			   url: "${pageContext.request.contextPath}/public/userAccount/mbPhoneBdVali.action",
			   data: "code="+$("#code").val()+"&phone="+$("#phone").val()+"&aac002="+$("#aac002").val()+"&aac001="+$("#aac001").val(),
			   success: function(data){
						if(data!=''){
							alert(data);
							return false;
						}
				window.location.href="${pageContext.request.contextPath}/public/userAccount/index_passwordReset.action?phone="+$("#phone").val()+"&aac001="+$("#aac001").val();
			   }
			});
	}
	function resend(){
		var aac002=document.getElementById('aac002').value;
		if(!aac002){
			alert('传入的身份证号为空！');
			return;
		}
		document.getElementById('btn_resend').disabled='true';
		window.location.href="${pageContext.request.contextPath}/public/userAccount/sendSmsFind.action?phone="+$("#phone").val()+"&aac001="+$("#aac001").val();
	}
	function back(){
		window.history.back();
	}
</script>
</head>

<body>
<div class="box">
   <div class="box_title">手机找回密码</div>
   <div class="test_one">
   <c:if test="${not empty errorMsg }">
   	${errorMsg }
   </c:if>
     <c:if test="${empty errorMsg }">
          验证码已发送至您的手机${phoneNumber }，请在五分钟内输入验证码并重置密码
    </c:if>
   </div>
   
	<form  name="processForm" style="margin:0" method="post" action="${pageContext.request.contextPath}/systemPress/mbPhoneFind.action" >
		<input type="hidden" id="aac002" value="${aac002}" name="aac002" >
		<input type="hidden" id="aac001" value="${aac001}" name="aac001" >
		<input type="hidden" id="phone" value="${phone}" name="phone" >
   <div class="box_box">
     <ul>
     
         <li>
           <div class="box_test">验证码</div>
           <div class="box_right"><input type="text" name="code" id="code" maxlength="18" class="input"  style="width:60%; float:left; display:inline;"/>
           <button type="button" onclick="resend()" id="btn_resend" class="am-btn-three">重新发送</button></div>
         </li>
        
         <li>
            <button type="button" onclick="query()" class="am-btn">提交</button>
         </li>
     </ul>
    </div>
    </form>
</div>


</body>
</html>