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
.box_box ul li .box_right .select{width:100%; height:30px; overflow:hidden; line-height:30px; overflow:hidden; border:1px solid #ddd; font-size:14px; color:#666; }

</style>
<script type="text/javascript" src="<c:url value="/resources/miniui/jquery.js"/>"></script>
<script type="text/javascript">
function save(){
	var userid = document.getElementById("userid").value;
	var question1 = document.getElementById("question1").value;
	var answer1 = document.getElementById("answer1").value;
	var question2 = document.getElementById("question2").value;
	var answer2 = document.getElementById("answer2").value;
	if(question1 == "" || answer1 == ""){
		alert("请完整填写第一个密保问题！");
		return false;
	}
	if(question2 == "" || answer2 == ""){
		alert("请完整填写第二个密保问题！");
		return false;
	}
	if(question1==question2){
		alert("请不要选择相同的密保问题！");
		return false;
		}
	$.ajax({
		   type: "POST",
		   contentType: "application/x-www-form-urlencoded; charset=utf-8",
		   url: "${pageContext.request.contextPath}/public/userAccount/validatePassport.action",
		   data: {userid:userid,question1:question1,answer1:answer1,question2:question2,answer2:answer2},
		   success: function(data){
			   var obj=eval('('+data+')');
			     if(obj.error!=null&&obj.error!=""){
					alert(obj.error);
					return false;
					}else{
					 window.location.href="${pageContext.request.contextPath}/public/userAccount/index_passwordReset.action?userid="+obj.userid;
					}
		   }
		});

}
	function back(){
		window.history.back();
	}
</script>
</head>

<body>
<div class="box">
   <div class="box_title">密保问题设置</div>
   <div class="test_one">
         注：1、请设置密保问题，密保问题不要随意泄露。
        <br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、密保问题作为找回密码的依据，请妥善保存。
         
        
   </div>
   <form  name="processForm" style="margin:0" method="post" action="${pageContext.request.contextPath}/systemPress/mbsave.action" >
   <input type="hidden" id="userid" value="${userid }" name="userid" >
   <div class="box_box">
     <ul>
     
         <li>
           <div class="box_test">密保问题1</div>
           <div class="box_right">
            <select id="question1" class="select"  name="question1"  <c:if test="${uaasUserPassport.question1!=null }"> disabled="disabled" </c:if>  >
					                    <option value="">--请选择--</option>
					                    <option value="01"  <c:if test="${uaasUserPassport.question1=='01' }"> selected="selected" </c:if> >你母亲的姓名</option>
					                    <option value="02"  <c:if test="${uaasUserPassport.question1=='02' }"> selected="selected" </c:if> >你父亲的姓名</option>
					                    <option value="03"  <c:if test="${uaasUserPassport.question1=='03' }"> selected="selected" </c:if> >你对象的姓名</option>
					                    <option value="04"  <c:if test="${uaasUserPassport.question1=='04' }"> selected="selected" </c:if> >你小学老师的姓名</option>
					                    <option value="05"  <c:if test="${uaasUserPassport.question1=='05' }"> selected="selected" </c:if> >你中学老师的姓名</option>
					                    <option value="06"  <c:if test="${uaasUserPassport.question1=='06' }"> selected="selected" </c:if> >你大学老师的姓名</option>
					                    <option value="07"  <c:if test="${uaasUserPassport.question1=='07' }"> selected="selected" </c:if> >你朋友的姓名</option>
				                      </select>
           </div>
         </li>
         <li>
           <div class="box_test">&nbsp;</div>
           <div class="box_right"><input type="text" name="answer1" id="answer1" maxlength="50" class="input" /></div>
         </li>
         <li>
           <div class="box_test">密保问题2</div>
           <div class="box_right">
            <select id="question2" class="select"   name="question2" <c:if test="${uaasUserPassport.question1!=null }"> disabled="disabled" </c:if> >
					                    <option value="">--请选择--</option>
					                    <option value="01"  <c:if test="${uaasUserPassport.question2=='01' }"> selected="selected" </c:if> >你母亲的姓名</option>
					                    <option value="02"  <c:if test="${uaasUserPassport.question2=='02' }"> selected="selected" </c:if> >你父亲的姓名</option>
					                    <option value="03"  <c:if test="${uaasUserPassport.question2=='03' }"> selected="selected" </c:if> >你对象的姓名</option>
					                    <option value="04"  <c:if test="${uaasUserPassport.question2=='04' }"> selected="selected" </c:if> >你小学老师的姓名</option>
					                    <option value="05"  <c:if test="${uaasUserPassport.question2=='05' }"> selected="selected" </c:if> >你中学老师的姓名</option>
					                    <option value="06"  <c:if test="${uaasUserPassport.question2=='06' }"> selected="selected" </c:if> >你大学老师的姓名</option>
					                    <option value="07"  <c:if test="${uaasUserPassport.question2=='07' }"> selected="selected" </c:if> >你朋友的姓名</option>
				                      </select>
           </div>
         </li>
          <li>
           <div class="box_test">&nbsp;</div>
           <div class="box_right"><input type="text" name="answer2" id="answer2" maxlength="50" class="input" /></div>
         </li>
         <li>
            <button type="button" id="dosubmit" onclick="save()" class="am-btn">保存</button>
            <button type="button" onclick="reset()" class="am-btn-two">重置</button>
         </li>
     </ul>
    </div>
    </form>
</div>


</body>
</html>