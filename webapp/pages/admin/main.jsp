<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>detail</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/miniui/boot.js "></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/frame.js "></script>
	
	<style type="text/css">
    </style>
    
</head>
<body>
	<h1 id="h_nodename"></h1>      
    <fieldset id="fd2">
        <legend><span>机构信息</span></legend>
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
           				 <input id="parentnodeid" name="parentnodeid" class="mini-textbox" allowInput="false" visible="false"/> 
           				  <input id="removed" name="removed" class="mini-textbox" allowInput="false" visible="false" /> 
           			</tr>
                <tr>
                    <td class="form-label">机构ID：</td>
                    <td>
                          <input id="nodeid" name="nodeid" class="mini-textbox" enabled="false" /> 
                    </td>
                    <td class="form-label">机构名称：</td>
                    <td>
                          <input id="nodename" name="nodename" class="mini-textbox" vtype="maxLength:50"  enabled="false"/> 
                    </td>
                    <td class="form-label">机构类型：</td>
                    <td>
                          <input id="nodetype" name="nodetype" class="mini-textbox" allowInput="false" enabled="false" /> 
                    </td>
                </tr>
                <tr>
                    <td class="form-label">负责人：</td>
                    <td >
                          <input id="principal" name="principal" class="mini-textbox" vtype="maxLength:24" /> 
                    </td>
                    <td class="form-label">联系电话：</td>
                    <td>
                           <input id="phone" name="phone" class="mini-textbox" vtype="maxLength:24" /> 
                    </td>
               		 <td class="form-label">联系地址：</td>
                    <td>
                          <input id="address" name="address" class="mini-textbox" vtype="maxLength:24" /> 
                    </td>
                    <td>
                    	<a class="mini-button" iconCls="icon-edit" onclick="onSaveOrgan">保存</a>
                    </td>
                </tr>
            </table>
        </div>
    </fieldset>
    <br/>
    <div style="width:100%;">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:100%;">
                        <a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
                        <a class="mini-button" iconCls="icon-add" onclick="edit()">编辑</a>
                        <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>       
                    </td>
                </tr>
            </table>           
        </div>
    </div>
    <div id="datagrid1" class="mini-datagrid" style="width:100%;height:350px;" allowResize="true"
        url="<%=request.getContextPath()%>/admin/queryUsersByNodeid.action"  idField="id" 
    >
        <div property="columns">
            <div type="checkcolumn" ></div>        
            <div field="userid" width="60" headerAlign="center" allowSort="true">用户ID</div>    
            <div field="loginname" width="100" headerAlign="center" allowSort="true">帐号</div>  
            <div field="name" width="60" headerAlign="center" allowSort="true">用户名</div> 
            <div field="sex" width="30" headerAlign="center" allowSort="true" renderer="onGenderRenderer">性别</div> 
            <div field="password" width="160" headerAlign="center" allowSort="true">密码</div> 
            <div field="groupname" width="140" headerAlign="center" allowSort="true">用户组</div>
            <div field="nodename" width="140" headerAlign="center" allowSort="true">机构</div>
            <div field="ctime" width="80" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="true">创建时间</div>     
        </div>
    </div>
</body>
<script type="text/javascript">
mini.parse();
//接收参数
var id=${param.id};

var grid = mini.get("datagrid1");
grid.load({nodeid:id});



	function add() {
    
mini.open({
			url :'<%=request.getContextPath()%>/pages/admin/addUser.jsp?nodeid='+id,
			title : "新增员工",
			width : 800,
			height : 500,
			onload : function() {
				var iframe = this.getIFrameEl();
				var data = {
					action : "new"
				};
				iframe.contentWindow.SetData(data);
			},
			ondestroy : function(action) {

				grid.reload();
			}
		});
	}
	function edit() {

		var row = grid.getSelected();
		if (row) {
			mini.open({
						url : '<%=request.getContextPath()%>/pages/admin/updateUser.jsp?userid='+row.userid,
						title : "编辑员工",
						width : 800,
						height : 500,
						onload : function() {
							/*var iframe = this.getIFrameEl();
							var data = {
								action : "edit",
								id : row.id
							};
							iframe.contentWindow.SetData(data);*/

						},
						ondestroy : function(action) {
							grid.reload();

						}
					});

		} else {
			alert("请选中一条记录");
		}

	}

	function remove() {

		var row = grid.getSelected();
		if (row) {
			mini.open({
						url : '<%=request.getContextPath()%>/pages/admin/preDelete.jsp?userid='+row.userid,
						title : "删除员工",
						width : 500,
						height : 300,
						onload : function() {
							/*var iframe = this.getIFrameEl();
							var data = {
								action : "edit",
								id : row.id
							};
							iframe.contentWindow.SetData(data);*/

						},
						ondestroy : function(action) {
							grid.reload();

						}
					});

		} else {
			alert("请选中一条记录");
		}
	}
	
	function search() {
		var key = mini.get("key").getValue();
		grid.load({
			key : key
		});
	}
	function onKeyEnter(e) {
		search();
	}
	/////////////////////////////////////////////////
	function onBirthdayRenderer(e) {
		var value = e.value;
		if (value)
			return mini.formatDate(value, 'yyyy-MM-dd');
		return "";
	}
	var Genders = [ {
		id : 1,
		text : '男'
	}, {
		id : 2,
		text : '女'
	} ];
	function onGenderRenderer(e) {
		for ( var i = 0, l = Genders.length; i < l; i++) {
			var g = Genders[i];
			if (g.id == e.value)
				return g.text;
		}
		return "";
	}

//加载数据
var url ='<%=request.getContextPath()%>/admin/queryOrganByNodeid.action';
Web.util.request(url,'POST',{nodeid:id},
	function(data){
		for (var v in data){
			if('nodename'==v)
				$("#h_nodename").html(data[v]);
			if(mini.get(v))
				mini.get(v).setValue(data[v]);
			}
	}
);

function onSaveOrgan(){
	 //提交表单数据
    var form = new mini.Form("#form1");            
    var data = form.getData();      //获取表单多个控件的数据
    var json = mini.encode(data);   //序列化成JSON
    $.ajax({
        url: "<%=request.getContextPath()%>/admin/updateOrgan.action",
        type: "post",
        data: { json: json },
        success: function (text) {
            alert("保存成功!");
        }
    });
}
</script>
</html>
