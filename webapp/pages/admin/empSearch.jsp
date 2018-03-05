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
	<h1 id="h_nodename">员工信息查询</h1>    
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:100%;">
                    </td>
                    <td style="white-space:nowrap;">
                        <input id="key" class="mini-textbox" emptyText="请输入帐号或用户名" style="width:150px;" onenter="onKeyEnter"/>   
                        <a class="mini-button" onclick="search()">查询</a>
                    </td>
                </tr>
            </table>           
        </div>
    <div id="datagrid1" class="mini-datagrid" style="width:100%;height:350px;" allowResize="true"
        url="<%=request.getContextPath()%>/admin/queryUsersByKeyWords.action"  idField="id" pageSize='100'
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
var grid = mini.get("datagrid1");
grid.load();
//接收参数
	function search() {
		var key = mini.get("key").getValue();
		grid.load({
			key : key
		});
	}
	function onKeyEnter(e) {
		search();
	}
	function search() {
        var key = mini.get("key").getValue();
        grid.load({ keywords: key });
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
	////////////////////////////////////////////////
</script>
</html>
