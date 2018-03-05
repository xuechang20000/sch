<%@ page contentType="text/html; charset=utf-8"%>
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
.test_three{ line-height:25px; font-size:14px; color:#A30002; }

.am-btn{ width:120px; height:35px; line-height:25px; cursor:pointer; margin-left:18%; text-align:center; border:none; background:#0e90d2; color:#fff; font-size:14px; }
.am-btn-two{ width:120px; height:35px; line-height:25px; cursor:pointer; margin-left:2%; text-align:center; border:none; background:#999; color:#fff; font-size:14px; }
.am-btn-three{ width:35%; height:32px; line-height:25px; cursor:pointer; margin-left:2%; text-align:center; border:none; background:#0e90d2; color:#fff; font-size:14px; }
</style>
<script type="text/javascript" src="<c:url value="/resources/miniui/boot.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/javascript/frame.js"/>"></script>
<script type="text/javascript">
	
	function submits(){
		var password1 = document.getElementById("password").value;
		var password2 = document.getElementById("password1").value;
		var userid=document.getElementById("userid").value;
		if(password1!=password2){
		
			alert("两次输入密码必须一致");
			return false;
			}else if(password1.length<6){
				alert("密码长度最低为6位");
				return false;
				}else{
					var url = '<%=request.getContextPath()%>/public/userAccount/updatePasswordPublic.action';
					Web.util.request(url,"post",{userid:userid,password:password1},function(data,textstatus){
							alert("提交成功！ ");
							$("#dosubmit").attr("disabled","disabled");
						});
				}
		}
</script>
</head>

<body>
<c:if test="${empty errorMsg }">
<div class="box">
   <div class="box_title">密码修改</div>
   <div class="test_one">
          说明:1、为保证信息安全,密码不能为个人编号。
<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、密码长度需超过6位。
   </div>
<form name="processForm" style="margin:0" method="post" >
<c:if test="${sessionScope['com.wb.session.userid']==null}">
<input type="hidden" id="userid" value="${userid }" name="userid" ></input>
</c:if>
<c:if test="${sessionScope['com.wb.session.userid']!=null}">
<input type="hidden" id="userid" value="${sessionScope['com.wb.session.userid']}" name="userid" ></input>
</c:if>
   <div class="box_box">
     <ul>
     
         <li>
           <div class="box_test">新密码</div>
           <div class="box_right"><input  id="password1" type="password" name="newPassword" maxlength="30" class="input"/></div>
         </li>
         <li>
           <div class="box_test">&nbsp;</div>
           <div class="box_right test_three">(新密码，只能包含英文[a-zA-Z]、数字[0-9]等字符)</div>
         </li>
        <li>
           <div class="box_test">确认新密码</div>
           <div class="box_right"><input  id="password" type="password" name="passwordResetVO.password" maxlength="30" value="" class="input"/></div>
         </li>
         <li>
           <div class="box_test">&nbsp;</div>
           <div class="box_right test_three">(确认新密码，确保和新密码一致)</div>
         </li>
         <li>
            <button type="button" id="dosubmit" onclick="submits();" class="am-btn">提交</button>
            <button type="button" onclick="javascript:window.location.href='<%=request.getContextPath()%>/logout.action'" class="am-btn">返回登陆界面</button>
         </li>
          <li>
           <div class="box_test"></div>
           <div class="box_right"><font  color="red">${errorMsg }</font></div>
         </li>
     </ul>
    </div>
    </form>
</div>
</c:if>
<c:if test="${not empty errorMsg }">
<div class="box_right"><font color="red">${errorMsg }</font></div>
</c:if>

</body>
</html>