<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>修改密码</title>
	<script type="text/javascript" src="<c:url value="/resources/miniui/boot.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/javascript/frame.js"/>"></script>
	 <style type="text/css">
	body{background-color:#fff;}
    fieldset{border:solid 1px #aaa; padding:10px;}    
    .form-label{float:right;}    
    </style>
</head>
<body onload="loadForm();">
    <fieldset id="fd2">
        <legend><span>密码修改</span></legend>
        <div class="fieldset-body">
        <div id="form1">
          <table class="form-table" border="0" cellpadding="1"  cellspacing="4">
           <colgroup align="right" width="25%"></colgroup>
           <colgroup align="left" width="25%"></colgroup>
                <tr>
                    <td class="form-label">原密码:</td>
                    <td>
                       <input id="oldpassword" name="oldpassword" class="mini-password"  required="true" /> 
                    </td>
                </tr>
                 <tr>
                    <td class="form-label">新密码:</td>
                    <td>
                       <input id="password1" name="password1" class="mini-password" vtype="minLength:6" required="true"  /> 
                    </td>
                </tr>
                 <tr>
                    <td class="form-label">确认新密码:</td>
                    <td>
                       <input id="password" name="password" class="mini-password" vtype="minLength:6" required="true" /> 
                    </td>
                </tr>
                <tr>
                <td></td>
	            <td>
	                <a class="mini-button" id="dosubmit" onclick="submitForm()" >提交</a>
	            </td>
        		</tr>
            </table>
            </div>
        </div>
    </fieldset>
</body>
<script type="text/javascript">
mini.parse();
function submitForm() {
	 var form = new mini.Form("#form1");
     form.validate();
     if (form.isValid() == false) return;
     if(mini.get("password1").getValue()!= mini.get("password").getValue()){
    	 mini.alert("新密码与确认密码必须一致，请重新输入！");
		return false;
     }
	//提交表单数据
	var url = '<%=request.getContextPath()%>/work/userAccount/updatePassword.action';
	Web.util.formSubmit("form1",url,"post",function(data,textstatus){
			mini.alert("提交成功！ ");
			mini.get("dosubmit").disable();
		});
   }
</script>
</html>
