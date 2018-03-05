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
    <fieldset id="fd2">
        <legend><span id="h_nodename"></span></legend>
        <div class="fieldset-body" id="form1">
        <table class="form-table" border="0" cellpadding="1" cellspacing="4">
           <colgroup align="right" width="15%"></colgroup>
           <colgroup align="left" width="15%"></colgroup>
          <colgroup align="right" width="15%"></colgroup>
           <colgroup align="left" width="15%"></colgroup>
          <colgroup align="right" width="15%"></colgroup>
          <colgroup align="right" width="15%"></colgroup>
                <tr>
                    <td>名称：</td>
                    <td >
                          <input id="name" name="name" class="mini-textbox" vtype="maxLength:50" required="true"/> 
                    </td>
                    <td>学制（填写专业时使用）：</td>
                    <td>
                          <input class="mini-textbox" style="width:50px;" id="ext" name="ext" vtype="float" enabled="false"  required="true"/>    
                    </td>
                    <td rowspan="2">
                    	<a class="mini-button" id="id_saveuser" iconCls="icon-edit" onclick="onSaveUser">保存</a>
                    </td>
                </tr>
            </table>
        </div>
    </fieldset>
</body>
<script type="text/javascript">
mini.parse();
//接收参数
var recordid=${param.recordid};
var datagrid='${param.datagrid}';
var h_nodename;
switch(datagrid)
{
case 'A':
  h_nodename='新增报考层次';
  break;
case 'B':
  h_nodename='新增考试科类';
  break;
case 'C':
  h_nodename='新增第一志愿院校';
  break;
case 'D':
  h_nodename='新增第一志愿专业';
  mini.get("ext").enable();
  break;
case 'E':
  h_nodename='新增学习形式';
  break;
case 'F':
  h_nodename='新增学习形式';
  break;
}
$("#h_nodename").html(h_nodename);
function onSaveUser(){
 //提交表单数据
    var form = new mini.Form("#form1");  
    form.validate();
    if (form.isValid() == false) return;
    
    var name=mini.get("name").getValue();
    var ext=mini.get("ext").getValue();
    var type=datagrid.charCodeAt(0) +1;
    type=String.fromCharCode(type)
    var id=recordid;
    $.ajax({
        url: "<%=request.getContextPath()%>/admin/addSchool.action",
        type: "post",
        data: { name:name,parentid:id,type:type,ext:ext },
        success: function (text) {
            mini.get("id_saveuser").disable();
            this.destroy();
        }
    });
}
</script>
</html>
