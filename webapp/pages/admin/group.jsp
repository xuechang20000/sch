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
	<h1 id="h_nodename">用户组织维护</h1>      
  
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
   <div id="datagrid1" class="mini-datagrid" style="width:100%;height:380px;" allowResize="true"
        url="<%=request.getContextPath()%>/admin/queryAllGroups.action"  idField="id"  pageSize='100'
    >
        <div property="columns">
            <div type="checkcolumn" ></div>        
            <div field="groupid" width="120" headerAlign="center" allowSort="true">用户组ID</div>    
            <div field="groupname" width="120" headerAlign="center" allowSort="true">用户组名称</div>  
            <div field="grouptype" width="120" headerAlign="center" allowSort="true">用户组类别</div>  
            <div field="status" width="120" headerAlign="center" allowSort="true" renderer="renStatus">状态</div>     
        </div>
    </div>
</body>
<script type="text/javascript">
mini.parse();
//接收参数
var grid=mini.get("datagrid1");
grid.load();

function add() {
    
mini.open({
			url :'<%=request.getContextPath()%>/pages/admin/addGroup.jsp',
			title : "新增组织",
			width : 700,
			height : 300,
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
		var groupid=row.groupid;
		if (row) {
			mini.open({
						url : '<%=request.getContextPath()%>/pages/admin/updateGroup.jsp?groupid='+groupid,
						title : "编辑组织",
						width : 700,
						height : 300,
						onload : function() {

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

		var rows = grid.getSelecteds();
		if (rows.length > 0) {
			if (confirm("确定删除选中记录？")) {
				var ids = [];
				for ( var i = 0, l = rows.length; i < l; i++) {
					var r = rows[i];
					ids.push(r.groupid);
				}
				var id = ids.join(',');
				grid.loading("操作中，请稍后......");
				$.ajax({
							url : '<%=request.getContextPath()%>/admin/deleteGroup.action?groupid='+id,
							success : function(text) {
								grid.reload();
							},
							error : function() {
							}
						});
			}
		} else {
			alert("请选中一条记录");
		}
	}
function renStatus(v){
	if(v.value=='1') return '正常'
	else
		return '失效';
}
</script>
</html>
