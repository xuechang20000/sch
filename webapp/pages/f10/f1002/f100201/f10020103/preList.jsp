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
					学生级别：                
                          <input id="stu_level2" name="stu_level2" class="mini-combobox"  
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=stu_level2" showNullItem="true" allowInput="true"/> 
                   &nbsp;&nbsp;&nbsp;&nbsp;
                   	 学生姓名:
                          <input id="stu_name" name="stu_name" class="mini-textbox"/>
                          &nbsp;&nbsp;&nbsp;&nbsp; 
                    	自：
                    	<input class="mini-datepicker" style="width:150px;" id="s_date" name="s_date"  />
                    	至
                    	<input class="mini-datepicker" style="width:150px;" id="e_date" name="e_date" />
                    	<br/>
                    	状态：                
                          <input id="nowstatus" name="nowstatus" class="mini-combobox"  
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=nowstatus" showNullItem="true" allowInput="true"/>
                                                  用户组名称:
                          <input id="groupname" name="groupname" class="mini-textbox" vtype="rangeLength:2,50"/>
                          &nbsp;&nbsp;
                          	跟进服务人：
                            <input id="follow" name="follow" class="mini-combobox" style="width:150px;" 
                          textField="name" valueField="userid"  url="<%=request.getContextPath()%>/work/f100101/loadAllUser.action" showNullItem="true" allowInput="true"/> 
                              
                    	<a class="mini-button" id="id_onSerach" iconCls="icon-edit" onclick="onSerach">查询</a>
    </fieldset>
   <div id="datagrid1" class="mini-datagrid" style="width:100%;height:300px;" allowResize="true"
        url="<%=request.getContextPath()%>/work/f10020102/queryStuRemoteListByCurentUserPub.action"  idField="stuid"  pageSize='100'
    >
        <div property="columns">
            <div field="stuid" width="120" headerAlign="center" align="center" visible="false" allowSort="true">学生id</div>    
            <div field="groupname" width="70" headerAlign="center" align="center" allowSort="true">用户组名称</div>  
            <div field="stu_name" width="60" headerAlign="center" align="center" allowSort="true">学生姓名</div>  
            <div field="cellphone" width="90" headerAlign="center"  align="center" allowSort="true" >手机</div> 
            <div field="stu_level2" width="60" headerAlign="center"  align="center" allowSort="true" renderer='oncodeRender'>学生级别</div> 
            <div field="educationtype2" width="60" headerAlign="center"  align="center" allowSort="true" renderer='oncodeRender'>学历类别</div> 
			<div field="performancer" width="60" headerAlign="center"  align="center" allowSort="true" >业绩人</div> 
			<div field="followor" width="60" headerAlign="center"  align="center" allowSort="true" >班主任</div> 
			<div field="netservice2" width="60" headerAlign="center"  align="center" allowSort="true" renderer='oncodeRender'>铺导员</div> 
			<div field="examlevel2" width="60" headerAlign="center"  align="center" allowSort="true" renderer='oncodeRender'>层次</div> 
			<div field="firstwishschool2" width="60" headerAlign="center" align="center"  allowSort="true" renderer='oncodeRender'>院校</div> 
			<div field="firstwishspecialty2" width="60" headerAlign="center" align="center"  allowSort="true" renderer='oncodeRender'>专业</div>  
			<div field="stufeetype" width="60" headerAlign="center" align="center"  allowSort="true" renderer='oncodeRender'>学费标准</div> 
			<div field="blongrelation" width="60" headerAlign="center" align="center"  allowSort="true"  renderer='oncodeRender'>学籍注册处</div>
			<div field="nowstatus" width="60" headerAlign="center" align="center"  allowSort="true"  renderer='oncodeRender'>状态</div>
			<div field="do" width="120" headerAlign="center" align="center"  allowSort="true" renderer='onrenderDO'>操作</div>        
        </div>
    </div>
</body>
<script type="text/javascript">
mini.parse();
var grid=mini.get("datagrid1");
grid.load({seq_year:'one'});
var usergrouptype='${user.grouptypeclass}';


function onSerach(){
	var stu_level2=mini.get("stu_level2").getValue();
	var stu_name =mini.get("stu_name").getValue();
	var s_date =mini.get("s_date").getValue();
	var e_date =mini.get("e_date").getValue();
	var groupname =mini.get("groupname").getValue();
	var follow =mini.get("follow").getValue();
	var nowstatus =mini.get("nowstatus").getValue();
	if(s_date) s_date=mini.formatDate(s_date,'yyyyMMdd');
	if(e_date) e_date=mini.formatDate(e_date,'yyyyMMdd');
	grid.load({stu_level2:stu_level2,stu_name:stu_name,s_date:s_date,e_date:e_date,processcode:id,groupname:groupname,followor:follow,nowstatus:nowstatus});
}

function onrenderDO(e){

	var link ='<a href="javascript:onOpenNext()">操作</a>';
	return link;
}

function onOpenNext(){
	var stuid=grid.getSelected().stuid;
	var url='<%=request.getContextPath()%>/pages/f10/f1002/f100201/f10020102/detail.jsp?stuid='+stuid;
	mini.open({
			url :url,
			title : "操作审批",
			width : 1100,
			height : 600,
			onload : function() {
			},
			ondestroy : function(action) {
				onSerach();
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
</script>
</html>
