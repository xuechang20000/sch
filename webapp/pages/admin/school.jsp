<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>detail</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/miniui/boot.js "></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/frame.js "></script>
	
	<style type="text/css">
	.c_list{padding:0px 8px 0px 0px;margin:0px;width:15%;float:left;}
    </style>
    
</head>
<body>
	<h1 id="h_nodename">学校专业级联关系维护</h1>  
	<div style="width:100%;">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:100%;">
                        <a class="mini-button" iconCls="icon-add" onclick="add()">增加子结点</a>
                        <a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>       
                    </td>
                </tr>
            </table>           
        </div>
    </div>    
<div class="c_list">
    <div id="datagridA" class="mini-datagrid" style="width:140px;height:350px;" allowResize="true"
        url="<%=request.getContextPath()%>/admin/querySubSchoolsById.action" onrowclick="onrowclickA"  idField="id" >
        <div property="columns">
            <div type="checkcolumn" ></div>        
            <div field="id"  headerAlign="center" visible='false'>id</div>    
            <div field="name"  headerAlign="center" allowSort="true">原学历层次</div>  
        </div>
    </div>
</div>   
<div class="c_list">
    <div id="datagridB" class="mini-datagrid" style="width:140px;height:350px;" allowResize="true" visible="false"
        url="<%=request.getContextPath()%>/admin/querySubSchoolsById.action" onrowclick="onrowclickB" idField="id" >
        <div property="columns">
            <div type="checkcolumn" ></div>        
            <div field="id"  headerAlign="center" visible='false'>id</div>    
            <div field="name"  headerAlign="center" allowSort="true">报考层次</div>  
        </div>
    </div>
</div>  
<div class="c_list">
    <div id="datagridC" class="mini-datagrid" style="width:140px;height:350px;" onrowclick="onrowclickC" visible="false" allowResize="true"
        url="<%=request.getContextPath()%>/admin/querySubSchoolsById.action"  idField="id" >
        <div property="columns">
            <div type="checkcolumn" ></div>        
            <div field="id"  headerAlign="center" visible='false'>id</div>    
            <div field="name"  headerAlign="center" allowSort="true">考试科类</div>  
        </div>
    </div>
</div>
<div class="c_list">
    <div id="datagridD" class="mini-datagrid" style="width:140px;height:350px;" onrowclick="onrowclickD" allowResize="true" visible="false"
        url="<%=request.getContextPath()%>/admin/querySubSchoolsById.action"  idField="id" >
        <div property="columns">
            <div type="checkcolumn" ></div>        
            <div field="id"  headerAlign="center" visible='false'>id</div>    
            <div field="name"  headerAlign="center" allowSort="true">第一志愿院校</div>  
        </div>
    </div>
</div>  
<div class="c_list">
    <div id="datagridE" class="mini-datagrid" style="width:140px;height:350px;" onrowclick="onrowclickE" allowResize="true" visible="false"
        url="<%=request.getContextPath()%>/admin/querySubSchoolsById.action"  idField="id" >
        <div property="columns">
            <div type="checkcolumn" ></div>        
            <div field="id"  headerAlign="center" visible='false'>id</div>    
            <div field="name"  headerAlign="center" allowSort="true">第一志愿专业</div>
            <div field="ext"  headerAlign="center" allowSort="true">学制(年)</div>  
        </div>
    </div>
</div> 
<div class="c_list">
    <div id="datagridF" class="mini-datagrid" style="width:140px;height:350px;" onrowclick="onrowclickF" allowResize="true" visible="false"
        url="<%=request.getContextPath()%>/admin/querySubSchoolsById.action"  idField="id" >
        <div property="columns">
            <div type="checkcolumn" ></div>        
            <div field="id"  headerAlign="center" visible='false'>id</div>    
            <div field="name"  headerAlign="center" allowSort="true">学习形式</div>  
        </div>
    </div>
</div> 
</body>
<script type="text/javascript">
mini.parse();
var grid = mini.get("datagridA");
grid.load();
var datagrid;
var recordid;
function onrowclickA(e){
	onrowclick(e);
    var record = e.record;
    var grid = mini.get("datagridB");
	grid.load({parentid:record.id});
	datagrid='datagridA';
	recordid=record.id;
}
function onrowclickB(e){
	onrowclick(e);
    var record = e.record;
    var grid = mini.get("datagridC");
	grid.load({parentid:record.id});
	datagrid='datagridB';
	recordid=record.id;
}
function onrowclickC(e){
	onrowclick(e);
    var record = e.record;
    var grid = mini.get("datagridD");
	grid.load({parentid:record.id});
	datagrid='datagridC';
	recordid=record.id;
}
function onrowclickD(e){
	onrowclick(e);
    var record = e.record;
    var grid = mini.get("datagridE");
	grid.load({parentid:record.id});
	datagrid='datagridD';
	recordid=record.id;
}
function onrowclickE(e){
	onrowclick(e);
    var record = e.record;
    var grid = mini.get("datagridF");
	grid.load({parentid:record.id});
	datagrid='datagridE';
	recordid=record.id;
}
function onrowclickF(e){
    var record = e.record;
	datagrid='datagridF';
	recordid=record.id;
}
function onrowclick(e){
	var grid=e.sender;
	var id=grid.id.substr(8,1);
	var idP1=id.charCodeAt(0) +1;
    var type=String.fromCharCode(idP1);
    mini.get('datagrid'+type).show();
    for(var i=idP1+1;i<=70;i++){
        type=String.fromCharCode(i);
    	mini.get('datagrid'+type).hide();
    }
}

function add() {
if(!datagrid){
	mini.alert("请选择一条记录！");
}
var grid=datagrid.substr(8,1);
mini.open({
			url :'<%=request.getContextPath()%>/pages/admin/addSchool.jsp?recordid='+recordid+'&datagrid='+grid,
			title : "新增",
			width : 800,
			height : 200,
			onload : function() {
			},
			ondestroy : function(action) {
				var type=grid.charCodeAt(0) +1;
    				type=String.fromCharCode(type)
				mini.get('datagrid'+type).reload();
			}
		});
}
	function remove() {
		grid = mini.get(datagrid);
		var rows = grid.getSelecteds();
		if (rows.length > 0) {
			if (confirm("确定删除选中记录["+rows[0].name+"]？")) {
				var ids = [];
				for ( var i = 0, l = rows.length; i < l; i++) {
					var r = rows[i];
					ids.push(r.id);
				}
				var id = ids.join(',');
				grid.loading("操作中，请稍后......");
				$.ajax({
							url : '<%=request.getContextPath()%>/admin/deleteSchool.action?id='+id,
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

</script>
</html>
