<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>修改密保问题</title>
	<script type="text/javascript" src="<c:url value="/resources/miniui/boot.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/javascript/frame.js"/>"></script>
	 <style type="text/css">
	body{background-color:#fff;}
    fieldset{border:solid 1px #aaa; padding:10px;}        
    </style>
</head>
<body>
    <fieldset id="fd2">
        <legend><span>密保问题修改</span></legend>
        <div class="fieldset-body">
        <div id="form1">
          <table class="form-table" border="0" cellpadding="1"  cellspacing="4">
           <colgroup align="right" width="25%"></colgroup>
           <colgroup align="left" width="30%"></colgroup>
           <colgroup align="left" width="30%"></colgroup>
                <tr>
                    <td class="form-label">密保问题一：</td>
                    <td>
                       <input id="question1" name="question1" class="mini-combobox"  textField="text" valueField="id" emptyText="请选择..." nullItemText="请选择..." required="true"
                        data="[{id:'01',text:'你母亲的姓名'},{id:'02',text:'你父亲的姓名'},{id:'03',text:'你对象的姓名'}
                        ,{id:'04',text:'你小学老师的姓名'},{id:'05',text:'你中学老师的姓名'},{id:'06',text:'你大学老师的姓名'}
                        ,{id:'07',text:'你朋友的姓名'}]" /> 
                    </td>
                    <td>
                    <input id="answer1" name="answer1" class="mini-textbox"  required="true"  /> 
                    </td>
                </tr>
                 <tr>
                    <td class="form-label">密保问题二：</td>
                    <td>
                       <input id="question2" name="question2" class="mini-combobox"  textField="text" valueField="id" emptyText="请选择..." nullItemText="请选择..." required="true"
                        data="[{id:'01',text:'你母亲的姓名'},{id:'02',text:'你父亲的姓名'},{id:'03',text:'你对象的姓名'}
                        ,{id:'04',text:'你小学老师的姓名'},{id:'05',text:'你中学老师的姓名'},{id:'06',text:'你大学老师的姓名'}
                        ,{id:'07',text:'你朋友的姓名'}]" /> 
                    </td>
                    <td>
                    <input id="answer2" name="answer2" class="mini-textbox"  required="true"  /> 
                    </td>
                </tr>
                <tr>
	            <td colspan="2">
	                <input value="提交" type="button" onclick="submitForm()" />
	            </td>
	             <td>
	                <input value="返回首页" type="button" onclick="back()" />
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
	//提交表单数据
	var url = '<%=request.getContextPath()%>/work/userAccount/updatePassport.action';
	Web.util.formSubmit("form1",url,"post",function(data,textstatus){
			mini.alert("提交成功！ ");
		});
   }
function back() {
	   window.parent.location.href="<%=request.getContextPath()%>/pages/index.jsp"
	    }
</script>
</html>
