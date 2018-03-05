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
        <legend><span>员工信息</span></legend>
        <div class="fieldset-body" id="form1">
        <table class="form-table" border="0" cellpadding="1" cellspacing="4">
           <colgroup align="right" width="15%"></colgroup>
           <colgroup align="left" width="15%"></colgroup>
          <colgroup align="right" width="15%"></colgroup>
           <colgroup align="left" width="15%"></colgroup>
          <colgroup align="right" width="15%"></colgroup>
           <colgroup align="left" width="15%"></colgroup>
           <colgroup align="left" width="15%"></colgroup>
                <tr>
                    <td class="form-label">用户组名称：</td>
                    <td>
                          <input id="groupname" name="groupname" class="mini-textbox"  required="true"/> 
                    </td>
                </tr>
                <tr>
                    <td class="form-label">用户组类别</td>
                    <td >
						<div id="grouptype" class="mini-radiobuttonlist" repeatItems="2" repeatLayout="table" required="true" repeatDirection="vertical"
						    textField="grouptypename" valueField="grouptypeid" 
						    url="<%=request.getContextPath()%>/admin/queryAllGroupType.action" >
						</div>
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
var groupid=${param.groupid};
loadForm();
function loadForm(){
	var url='<%=request.getContextPath()%>/admin/queryGroupByGroupid.action';
	 $.ajax({
	        url: url,
	        type: "post",
	        data: {groupid:groupid},
	        success: function (text) {
	        	 var obj=mini.decode(text,false);
	        	 mini.get("groupname").setValue(obj.groupname);
	        	 mini.get("grouptype").setValue(obj.grouptype);
	        }
	    });
}
function onSaveGroup(){
	
	 //提交表单数据
    var form = new mini.Form("#form1");  
    form.validate();
    if (form.isValid() == false) return;
    
    var groupname=mini.get("groupname").getValue();
    var grouptypeid=mini.get("grouptype").getValue();
    
    $.ajax({
        url: "<%=request.getContextPath()%>/admin/updateGroup.action",
        type: "post",
        data: {groupid:groupid, groupname: groupname,grouptype:grouptypeid },
        success: function (text) {
            alert("保存成功!");
            mini.get("id_saveuser").disable();
        }
    });
}
</script>
</html>
