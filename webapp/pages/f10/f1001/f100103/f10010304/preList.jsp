<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>detail</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/miniui/boot.js "></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/frame.js "></script>
	
	<style type="text/css">
	#fd2{margin-bottom: 5px;}
    </style>
    
</head>
<body>   
    <fieldset id="fd2">
    <legend><span>查询条件</span></legend>
					
                    	是否已经处理：
                    	<input id="remind" name="remind" class="mini-combobox" style="width:150px;"  textField="text" valueField="id"
                       data='[{"id":"0","text":"未处理"},{"id":"1","text":"已经处理"}]' showNullItem="true" allowInput="true"/> 
                    	<a class="mini-button" id="id_onSerach" iconCls="icon-edit" onclick="onSerach">查询</a>
    </fieldset>
   <div id="datagrid1" class="mini-datagrid" style="width:100%;height:300px;" allowResize="true"
        url="<%=request.getContextPath()%>/work/f10010304/queryStuListByBirthdayRemind.action" 
         idField="stuid"  pageSize='100' pagerButtons="#exportExcel" sortMode="client"    >
        <div property="columns">
            <div field="stuid" width="120" headerAlign="center" align="center" visible="false" allowSort="true">学生id</div>    
            <div field="groupname" width="70" headerAlign="center" align="center" allowSort="true">用户组名称</div>  
            <div field="stu_name" width="60" headerAlign="center" align="center" allowSort="true">学生姓名</div>  
            <div field="cellphone" width="90" headerAlign="center"  align="center" allowSort="true" renderer='oncellphoneRender'>手机</div> 
            <div field="stu_level" width="60" headerAlign="center"  align="center" allowSort="true" renderer='oncodeRender'>学生级别</div> 
			<div field="recorderor" width="60" headerAlign="center"  align="center" allowSort="true" >学习顾问</div> 
			<div field="followor" width="60" headerAlign="center"  align="center" allowSort="true" >跟进服务人</div> 
			<div field="examlevelor" width="60" headerAlign="center"  align="center" allowSort="true" >报考层次</div> 
			<div field="examclassor" width="60" headerAlign="center" align="center"  allowSort="true" >考试科类</div> 
			<div field="firstwishschoolor" width="60" headerAlign="center" align="center"  allowSort="true" >一志愿院校</div> 
			<div field="firstwishspecialtyor" width="60" headerAlign="center" align="center"  allowSort="true" >一志愿专业</div>
			<div field="birthday" width="60" headerAlign="center" align="center"  allowSort="true" >生日</div>  
			<!--<div field="learningformor" width="60" headerAlign="center" align="center"  allowSort="true" >学习形式</div>  
			<div field="manualschool" width="60" headerAlign="center" align="center"  allowSort="true" >手输院校</div> 
			<div field="manualspecialty" width="60" headerAlign="center" align="center"  allowSort="true" >手输专业</div> 
			 <div field="blongrelation" width="60" headerAlign="center" align="center"  allowSort="true"  renderer='oncodeRender'>隶属关系</div> -->
			<div field="remind" width="60" headerAlign="center" align="center"  allowSort="true" renderer='onRemindRender' >是否处理</div> 
			<div field="proce_stepname" width="140" headerAlign="center" align="center"  allowSort="true" >当前状态</div>
			<div field="do" width="100" headerAlign="center" align="center"  allowSort="true" renderer='onrenderDO'>详情</div>        
        </div>
    </div>
     <jsp:include page="/pages/common/exportExcel.jsp"></jsp:include>
</body>
<script type="text/javascript">
mini.parse();
var grid=mini.get("datagrid1");
Web.util.load("datagrid1",{});
var usergrouptype='${user.grouptypeclass}';


function onSerach(){
	var remind=mini.get("remind").getValue();
	Web.util.load("datagrid1",{remind:remind});
}

function onrenderDO(e){

	var link ='<a href="javascript:onDO()">处理</a>';
	return link;
}

function onDO(){
	var stuid=grid.getSelected().stuid;
	var url='<%=request.getContextPath()%>/work/f10010304/updateBirthdayRemind.action?stuid='+stuid;
	Web.util.requestAsync(url,'POST','',
			function(data){
				mini.alert("处理成功！");
				onSerach();
			});
}

function onDelete(){
	var stuid=grid.getSelected().stuid;
	var url = '<%=request.getContextPath()%>/work/f100101/deleteStudent.action';
	 mini.confirm("删除后将无法恢复，确定删除记录？", "确定？",function(action){
		 if (action == "ok") {
				Web.util.requestAsync(url,'POST',{stuid:stuid},
						function(data){
							mini.alert("删除成功！");
							onSerach();
						});
         	} 
		 });

}

function oncodeRender(e){
	var retStr = '';
	var rootPath=getWebRootPath();
	var url = '<%=request.getContextPath()%>/common/codes.action?codename='+e.field;
	Web.util.requestAsync(url,'POST','',
			function(data){
				for(var i=0;i<data.length;i++) {
	                var g = data[i];
	                if(g.aaa102==e.value){
	                	retStr=g.aaa103;
	                }
	            }
			}
	);
	return retStr;
}
function oncellphoneRender(e){
	if('04'==usergrouptype||'06'==usergrouptype){
		return e.value.substr(0,3)+"****"+e.value.substr(7,4)
	}else{
		return e.value;
	}
}
function onRemindRender(e){
	if(e.value=='1') return '已处理';
	return '未处理';
}
$(document).ready(function () {

    if(usergrouptype=='06') $("#exportExcel").hide();
})
</script>
</html>
