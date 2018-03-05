<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>detail</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/miniui/boot.js "></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/md5.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/frame.js "></script>
	
	<style type="text/css">
    </style>
    
</head>
<body>
	<h1 id="h_nodename"></h1>      
    <fieldset id="fd2">
        <legend><span>字典信息</span></legend>
        <div class="fieldset-body" id="form1">
        <table  border="0" cellpadding="1" cellspacing="4">
           <colgroup align="right" width="15%"></colgroup>
           <colgroup align="left" width="15%"></colgroup>
          <colgroup align="right" width="15%"></colgroup>
           <colgroup align="left" width="15%"></colgroup>
          <colgroup align="right" width="15%"></colgroup>
           <colgroup align="left" width="15%"></colgroup>
           <colgroup align="left" width="15%"></colgroup>
                <tr>
                    <td class="form-label">字典名称：</td>
                    <td>
                          <input id="aaa103" name="aaa103" class="mini-textbox"  required="true"/> 
                    </td>
                    <td rowspan="2">
                    	<a class="mini-button" id="id_saveuser" iconCls="icon-edit" onclick="onSaveGroup">保存</a>
                    </td>
                </tr>
            </table>
        </div>
    </fieldset>
</body>
<script type="text/javascript">
mini.parse();
var aaa100='${param.aaa100}';
function onSaveGroup(){
	
	 //提交表单数据
    var form = new mini.Form("#form1");  
    form.validate();
    if (form.isValid() == false) return;
    
    var aaa103=mini.get("aaa103").getValue();
    
    $.ajax({
        url: "<%=request.getContextPath()%>/admin/addAa10.action",
        type: "post",
        data: {aaa100:aaa100, aaa103: aaa103},
        success: function (text) {
            mini.alert("保存成功!");
            mini.get("id_saveuser").disable();
        }
    });
}
</script>
</html>
