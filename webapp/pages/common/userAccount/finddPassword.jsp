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
.am-btn{ width:120px; height:35px; line-height:25px; cursor:pointer; margin-left:18%; text-align:center; border:none; background:#0e90d2; color:#fff; font-size:14px; }
.am-btn-two{ width:120px; height:35px; line-height:25px; cursor:pointer; margin-left:2%; text-align:center; border:none; background:#999; color:#fff; font-size:14px; }
</style>

<script type="text/javascript">
if ( parent.location != document.location )
{
   parent.location = "<%=request.getContextPath()%>/pages/common/userAccount/finddPassword.jsp";
} 
//查询
	function query() {
		var aac001 = document.getElementById("aac001").value;
		var aac002 = document.getElementById("aac002").value;
		//var email = document.getElementById("email").value;
		if(aac001 == ""){
			document.getElementById("aac001").focus();
			alert("人员编号不能为空，请查看！");
			return false;
		}
		if(aac002 == ""){
			document.getElementById("aac002").focus();
			alert("身份证不能为空，请查看！");
			return false;
		}

		if(aac001 != "" && aac002 != ""){
			if(validateIdCard(aac002)!= false){
				document.forms[0].submit();
		}
	}
}
	//邮箱
	 function checkemail(){
        var temp = document.getElementById("email");
        var myreg = /(\S)+[@]{1}(\S)+[.]{1}(\w)+/;
        if(temp.value!=""){
            if(!myreg.test(temp.value)){
                alert("请输入有效的email!");
                temp.value="";
                document.getElementById("email").focus();
                return false;
            }
         }
      }
     //身份证
     /**
	  *** 转化身份证，将15位或者18位的身份证转换为正确的18为身份证
	  **/
    idCardTo18 =function(idcard) {
		var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
		var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
		var sum = 0;
		 
		if(idcard.length != 15 &&idcard.length != 18) {
			return idcard;
		}
		if (idcard.length == 15) {
			idcard = idcard.substr(0, 6) + '19'+ idcard.substr(6, idcard.length - 6);
		} else {
			idcard = idcard.substr(0, 17);
		}
		for (var i = 0; i < idcard.length; i++) {
			sum += idcard.substr(i, 1) * arrInt[i];
		}
		idcard += arrCh[sum % 11];
		return idcard;
	}

	/**
	**  校验身份证是否合法
	**/	
    validateIdCard = function(value) {
		if(typeof value =='undefined' || !value || value ==''){
			 alert("身份证号码为空!");
			 document.getElementById("aac002").focus();
             return false;
		}	
		if (value.length != 15 && value.length != 18) {
			 alert("身份证号码长度应该为15位或者18位!");
			 document.getElementById("aac002").focus();
             return false;
		}
		var patten1 = "^\\d{15}$";
		var patten2 = "^\\d{17}[0123456789Xx]$";
		if (!new RegExp(patten1, "g").test(value)&& !new RegExp(patten2, "g").test(value)) {
			alert("身份证号码只能包含数字和X");
			document.getElementById("aac002").focus();
            return false;
		}
		var id18 = idCardTo18(value);
		var csrqstr = id18.substr(6, 8);
		var year = parseInt(csrqstr.substr(0, 4), 10);
		var month = parseInt(csrqstr.substr(4, 2), 10) - 1;
		var date = parseInt(csrqstr.substr(6, 2), 10);
		var csrq = new Date(year, month, date);
		if (year != csrq.getFullYear() || month != csrq.getMonth()|| date != csrq.getDate()) {
			alert("身份证中的出生日期['+csrqstr +']非法!");
			document.getElementById("aac002").focus();
            return false;
		}
		//if(value !=id18){
		//	alert("身份证不正确，正确的身份证号码为:["+id18+"]");
		//	document.getElementById("aac002").focus();
         //   return false;
		//}	
}

	//重置
	function reset() {
		document.forms[0].reset();
	}
</script>
</head>

<body>
<div class="box">
   <div class="box_title">找回密码</div>
   <div class="test_one">
            注：1、以下输入框的”编号“为老卡的个人编号（磁条）或新卡的社会保障卡号（芯片）。
       <!--  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、如果已设置密保问题，可输入编号和身份证号点“查询”找回。
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、若未设置密保问题可通过手机找回密码。 -->
   </div>
   <form  action="${pageContext.request.contextPath}/public/userAccount/findPassword.action" style="margin:0" method="post">
   <div class="box_box">
     <ul>
         <li>
           <div class="box_test">编号</div>
           <div class="box_right"><input type="text" name="aac001"  id="aac001" class="input" /></div>
         </li>
         <li>
           <div class="box_test">身份证号</div>
           <div class="box_right"><input type="text" name="aac002"  id="aac002" class="input" /></div>
         </li>
          <li>
           <div class="box_test"></div>
           <div class="box_right"><font color="red">${message }</font></div>
         </li>
         <li>
            <button type="button" onclick="query()" class="am-btn">查询</button>
            <button type="button" onclick="reset()" class="am-btn-two">重置</button>
         </li>
     </ul>
    </div>
    </form>
</div>


</body>
</html>