<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>密保手机修改</title>
	<script type="text/javascript" src="<c:url value="/resources/miniui/boot.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/javascript/frame.js"/>"></script>
	 <style type="text/css">
	body{background-color:#fff;}
    fieldset{border:solid 1px #aaa; padding:10px;}        
    </style>
</head>
<body>
    <fieldset id="fd2">
        <legend><span>密保手机修改</span></legend>
        <div class="fieldset-body">
        <div id="form1">
          <table class="form-table" border="0" cellpadding="1"  cellspacing="4">
           <colgroup align="right" width="25%"></colgroup>
           <colgroup align="left" width="25%"></colgroup>
                <tr>
                    <td class="form-label">原密保手机:</td>
                    <td>
                       <input id="oldphone" name="oldphone" class="mini-text" value="${mub.m_phone }" required="true" readonly="readonly" /> 
                    </td>
                </tr>
                 <tr>
                    <td class="form-label">新密保手机:</td>
                    <td>
                       <input id="phone" name="phone" class="mini-text" vtype="minLength:11;maxLength:11" required="true"  /> 
                    </td>
                </tr>
                 <tr>
                    <td class="form-label">校验码:</td>
                    <td>
                       <input id="code" name="code" class="mini-text"  required="true" /> 
                    </td>
                    <td> <input value="发送校验码" type="button" id="send" onclick="send()" /></td>
                </tr>
                <tr>
	            <td>
	                <input value="提交" type="button" id="dosubmit" onclick="submitForm()" />
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
	var phone=$("#phone").val();
	if(!(/^1[345678]\d{9}$/.test(phone))){ 
        mini.alert("手机号码有误，请重填");  
        return false; 
    }
	var code=$("#code").val();
	if(code==null||code==''){
		 mini.alert("请填写校验码");  
		 return false;
	}
	$.ajax({
		   type: "POST",
		   async: true,
		   url: "<%=request.getContextPath()%>/work/userAccount/phoneBindChange.action",
		   data: "code="+code,
		   success: function(data){
					var obj=eval('('+data+')');
					if(obj.error){
						mini.alert(obj.error);
						return false;
					}else{
						mini.alert("提交成功！");
						$("#dosubmit").attr("disabled",true);
					}
		   }
		});
   }
function send(){
	
	var phone=$("#phone").val();
	if(!(/^1[345678]\d{9}$/.test(phone))){ 
        alert("手机号码有误，请重填");  
        return false; 
    }
	$("#send").attr("disabled",true);
	
	var t;
	var i=59;
	$.ajax({
	   type: "POST",
	   async: true,
	   url: "${pageContext.request.contextPath}/work/userAccount/sendSmsBindChange.action",
	   data: "phone="+$("#phone").val(),
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
</script>
</html>
