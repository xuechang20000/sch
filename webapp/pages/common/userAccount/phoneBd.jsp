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
		$.ajax({
			   type: "POST",
			   async: true,
			   url: "${pageContext.request.contextPath}/public/userAccount/phoneBind.action",
			   data: "code="+$("#code").val()+"&phone="+$("#phone").val()+"&aac002="+$("#aac002").val()+"&aac001="+$("#aac001").val(),
			   success: function(data){
						var obj=eval('('+data+')');
						if(obj.error){
							alert(obj.error);
							return false;
						}
							window.location.href="${pageContext.request.contextPath}/public/userAccount/sendSmsFind.action?phone="+$("#phone").val()+"&aac001="+$("#aac001").val();
			   }
			});
	}
	var t;
	var i=59;
	function sendMb(){
		var aac002=document.getElementById('aac002').value;
		var aac001=document.getElementById('aac001').value;
		var phone=document.getElementById('phone').value;
		if(!aac002){
			alert('请输入身份证号！');
			return;
		}
		if(!phone){
			alert('请输入手机号码！');
			return;
		}
		if($.trim(phone).length!=11){
			alert('手机号码格式不正确！');
			return;
		}
		$("#send").attr("disabled",true);
		$.ajax({
		   type: "POST",
		   async: true,
		   url: "${pageContext.request.contextPath}/public/userAccount/sendSmsBind.action",
		   data: "phone="+$("#phone").val()+"&aac002="+$("#aac002").val()+"&aac001="+$("#aac001").val(),
		   success: function(data){
					var obj=eval('('+data+')');
					if(obj.error){
						if(t){
							clearTimeout(t);
							$("#send").attr("disabled",false);
							$("#send").val("发送验证码");
						}
						alert(obj.error);
						return false;
					}
					if(obj.success){
						$("#msg").html("验证码已发送至手机，输入验证码以绑定手机");
					}
		   }
		});
		t=setTimeout(
		function(){
			if(i<=0){
				$("#send").attr("disabled",false);
				$("#send").val("发送验证码");
				return;
			}
			$("#send").val(i);
			i--;
			t=setTimeout(arguments.callee,1000);
		},1000);
	}
	function back(){
		window.history.back();
	}
</script>
</head>

<body>
<div class="box">
   <div class="box_title">手机号码绑定</div>
  <form  name="processForm" style="margin:0" method="post" action="${pageContext.request.contextPath}/public/userAccount/phoneBind.action" >
  <input name="aac002" id="aac002"  type="hidden" class="login_input1" size="20" maxlength="18" value="${ac01dto.aac002}"/>
  <input name="aac001" id="aac001"  type="hidden" class="login_input1" size="20" maxlength="18" value="${ac01dto.aac001}"/>    
  <input name="aac003" id="aac003"  type="hidden" class="login_input1" size="20" maxlength="18" value="${ac01dto.aac003}"/> 
  <input name="flag" id="flag"  type="hidden" class="login_input1" size="20" maxlength="18" value="${flag}"/>
   <div class="box_box">
     <ul>
         <li>
           <div class="box_test">手机号码</div>
           <div class="box_right"><input name="phone" id="phone" maxlength="18" type="text" class="input"/></div>
         </li>
         <li>
           <div class="box_test"  name="code">验证码</div>
           <div class="box_right"><input type="text" name="code" id="code" class="input" style="width:60%; float:left; display:inline;"/>
           <button type="button" onclick="sendMb()" id="send" class="am-btn-three">发送验证码</button></div>
         </li>
        
         <li>
            <button type="button" onclick="query()" id="button_query" class="am-btn">提交</button>
            <button type="button" onclick="reset()" class="am-btn-two">重置</button>
         </li>
     </ul>
    </div>
    </form>
</div>


</body>
</html>