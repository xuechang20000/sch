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
                          <input id="stu_level" name="stu_level" class="mini-combobox"  
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=stu_level" showNullItem="true" allowInput="true"/> 
                   &nbsp;&nbsp;&nbsp;&nbsp;
                   	 学生姓名:
                          <input id="stu_name" name="stu_name" class="mini-textbox"/>
                          &nbsp;&nbsp;&nbsp;&nbsp; 
                    	自：
                    	<input class="mini-datepicker" style="width:150px;" id="s_date" name="s_date"  />
                    	至
                    	<input class="mini-datepicker" style="width:150px;" id="e_date" name="e_date" />
                    	<a class="mini-button" id="id_onSerach" iconCls="icon-edit" onclick="onSerach">查询</a>
    </fieldset>
   <div id="datagrid1" class="mini-datagrid" style="width:100%;height:300px;" allowResize="true"
        url="<%=request.getContextPath()%>/work/f10010202/queryStuListByCurentUserPub.action" 
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
			<div field="learningformor" width="60" headerAlign="center" align="center"  allowSort="true" >学习形式</div>  
			<div field="manualschool" width="60" headerAlign="center" align="center"  allowSort="true" >手输院校</div> 
			<div field="manualspecialty" width="60" headerAlign="center" align="center"  allowSort="true" >手输专业</div> 
			<div field="blongrelation" width="60" headerAlign="center" align="center"  allowSort="true"  renderer='oncodeRender'>隶属关系</div>
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
	var stu_level=mini.get("stu_level").getValue();
	var stu_name =mini.get("stu_name").getValue();
	var s_date =mini.get("s_date").getValue();
	var e_date =mini.get("e_date").getValue();
	if(s_date) s_date=mini.formatDate(s_date,'yyyyMMdd');
	if(e_date) e_date=mini.formatDate(e_date,'yyyyMMdd');
	Web.util.load("datagrid1",{stu_level:stu_level,stu_name:stu_name,s_date:s_date,e_date:e_date});
}

function onrenderDO(e){

	var link ='<a href="javascript:onOpenNext()">查询</a>';
	return link;
}

function onOpenNext(){
	var stuid=grid.getSelected().stuid;
	//Web.util.openWindow("<%=request.getContextPath()%>/pages/f10/f1001/f100101/"+id+".jsp",{stuid:stuid},1100,600);
	mini.open({
			url :'<%=request.getContextPath()%>/pages/f10/f1001/f100102/f10010202/detail.jsp?stuid='+stuid,
			title : "操作审批",
			width : 1100,
			height : 600,
			onload : function() {
			},
			ondestroy : function(action) {
				//onSerach();
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
</script>
</html>
