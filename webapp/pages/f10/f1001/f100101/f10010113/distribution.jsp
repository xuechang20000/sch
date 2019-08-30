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
	<legend><span>重新分配</span></legend>
	<div class="fieldset-body" id="form1">
		<table class="form-table" border="0" cellpadding="1" cellspacing="4">
			<colgroup align="right" width="25%"></colgroup>
			<colgroup align="left" width="25%"></colgroup>
			<tr>
				<td class="form-label">选择分配人：</td>
				<td>
					<input id="recorder" name="recorder" class="mini-combobox" style="width:150px;" required="true"
						   textField="name" valueField="userid"  url="<%=request.getContextPath()%>/work/f100101/loadAllUser.action" showNullItem="true" allowInput="true"/> <font color="red">*</font>
				</td>
			</tr>
			<tr>
				<td >
					<a class="mini-button" iconCls="icon-edit" onclick="onSaveUser">保存</a> &nbsp;&nbsp;&nbsp;
				</td>
			</tr>
		</table>
	</div>
</fieldset>
</body>
<script type="text/javascript">
    mini.parse();
    //接收参数
    var stuid=${param.stuid};


    function onSaveUser(){
        if(confirm("确定要重新分配吗？")){
            var recorder=mini.get('recorder').getValue();
            if(!recorder) {
                alert('请选择分配人');
                return;
            }
            Web.util.request('<%=request.getContextPath()%>/work/f100101/distributePreStudent.action?stuid='+stuid+'&userid='+recorder,"",{},function(data){
                alert("保存成功!");
            });
        }
    }

</script>
</html>
