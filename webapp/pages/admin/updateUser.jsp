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
           				 <input id="nodeid" name="nodeid" class="mini-textbox" allowInput="false" visible="false"/> 
           				 <input id="groupids" name="groupids" class="mini-textbox" allowInput="false" visible="false"/> 
           			</tr>
                <tr>
                    <td class="form-label">员工ID：</td>
                    <td>
                          <input id="userid" name="userid" class="mini-textbox" enabled="false" /> 
                    </td>
                    <td class="form-label">帐号：</td>
                    <td>
                          <input id="loginname" name="loginname" class="mini-textbox" enabled="false" onvalidation="onEnglishAndNumberValidation" vtype="maxLength:50" required="true"/> 
                    </td>
                </tr>
                <tr>
                    <td class="form-label">姓名：</td>
                    <td >
                          <input id="name" name="name" class="mini-textbox" vtype="maxLength:24" required="true"/> 
                    </td>
                    <td class="form-label">性别：</td>
                    <td>
                          <input class="mini-combobox" style="width:150px;" name="sex" textField="text" valueField="id" data='[{"id":1,"text":"男"},{"id":2,"text":"女"}]' value="1" required="true"/>    
                    </td>
                </tr>
                
                <tr>
                    <td >
                    	<a class="mini-button" iconCls="icon-edit" onclick="onSaveUser">保存</a> &nbsp;&nbsp;&nbsp;
                    	<a class="mini-button" iconCls="icon-edit" onclick="onInitPassword">初始化密码</a>
                    </td>
                </tr>
            </table>
        </div>
    </fieldset>
    <br/>
    <div id="datagrid1" class="mini-datagrid" style="width:100%;height:280px;" allowResize="true"
        url="<%=request.getContextPath()%>/admin/queryAllGroups.action"  idField="id" pageSize=100 
    >
        <div property="columns">
            <div type="checkcolumn" ></div>        
            <div field="groupid" width="120" headerAlign="center" allowSort="true">用户组ID</div>    
            <div field="groupname" width="120" headerAlign="center" allowSort="true">用户组名称</div>  
            <div field="grouptype" width="120" headerAlign="center" allowSort="true">用户组类别</div>  
            <div field="status" width="120" headerAlign="center" allowSort="true">状态</div>     
        </div>
    </div>
</body>
<script type="text/javascript">
mini.parse();
//接收参数
var userid=${param.userid};
var grid = mini.get("datagrid1");


/**
 * 加载表单
 */
 function loadForm(){
	$.ajax({
	    url: "<%=request.getContextPath()%>/admin/queryUserById.action",
	    type: "post",
	    data: {userid:userid},
	    success: function (text) {
	    	var form = new mini.Form("#form1");
	    	form.setData(mini.decode(text,false),false);
	    }
	});
}
/**
 * 加载用户组
 */
 function loadGroups(){
	 grid.load({},function(){
		 
		 $.ajax({
			    url: "<%=request.getContextPath()%>/admin/queryGroupByUserid.action",
			    type: "post",
			    data: {userid:userid},
			    success: function (text) {
			        var rows=mini.decode(text,false);
			        for (var i = 0; i < grid.data.length; i++) {
		                for (var j = 0; j < rows.length; j++) {
		                    if (grid.data[i].groupid == rows[j].groupid) {
		                        grid.setSelected(grid.getRow(i));
		                    }
		                }
		            }
			        
			    }
			});
	 });
	 

}
function onSaveUser(){
	var rows = grid.getSelecteds();
	var groups=mini.encode(rows);
	
	if(rows.length==0){
		mini.alert("请选择用户组！");
		return;
	}
	 //提交表单数据
    var form = new mini.Form("#form1");  
    form.validate();
    if (form.isValid() == false) return;
    
    var data = form.getData();      //获取表单多个控件的数据
    var json = mini.encode(data);   //序列化成JSON
    
    $.ajax({
        url: "<%=request.getContextPath()%>/admin/updateUser.action",
        type: "post",
        data: { json: json,groups:groups },
        success: function (text) {
            alert("保存成功!");
        }
    });
}
function onInitPassword(){
	$.ajax({
        url: "<%=request.getContextPath()%>/admin/initUserPassword.action",
        type: "post",
        data: { userid:userid},
        success: function (text) {
            alert("保存成功!");
        }
    });
}
function onEnglishAndNumberValidation(e) {
    if (e.isValid) {
        if (isEnglishAndNumber(e.value) == false) {
            e.errorText = "必须输入英文+数字";
            e.isValid = false;
        }
    }
}
/* 是否英文+数字 */
function isEnglishAndNumber(v) {
    
    var re = new RegExp("^[0-9a-zA-Z\_]+$");
    if (re.test(v)) return true;
    return false;
}

</script>
<script>
loadForm();
loadGroups();
</script>
</html>
