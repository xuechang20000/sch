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
    <fieldset id="fd1">
    <legend><span>查询条件</span></legend>
					学生级别：                
                          <input id="stu_level" name="stu_level" class="mini-combobox"  
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=stu_level" showNullItem="true" allowInput="true"/> 
                   &nbsp;&nbsp;&nbsp;&nbsp;
                   	 学生姓名:
                          <input id="stu_name" name="stu_name" class="mini-textbox" vtype="rangeLength:2,50"/>
                          &nbsp;&nbsp;&nbsp;&nbsp; 
                    	自：
                    	<input class="mini-datepicker" style="width:150px;" id="s_date" name="s_date"  />
                    	至
                    	<input class="mini-datepicker" style="width:150px;" id="e_date" name="e_date" />
                    	是否在藉：
                    	<input id="isstudent" name="isstudent" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/> 
                         <br/>
                                                  用户组名称:
                          <input id="groupname" name="groupname" class="mini-textbox" vtype="rangeLength:2,50"/>
                          &nbsp;&nbsp;
                          	跟进服务人：
                            <input id="follow" name="follow" class="mini-combobox" style="width:150px;" 
                          textField="name" valueField="userid"  url="<%=request.getContextPath()%>/work/f100101/loadAllUser.action" showNullItem="true" allowInput="true"/>
                                                  业务流程： 
                          <input id="process" name="process" class="mini-combobox"  
                          textField="name" valueField="id"  url="<%=request.getContextPath()%>/work/f10010202/queryProcessStepParams.action?param=process" showNullItem="true" allowInput="true"/> 
                      业务步骤： 
                          <input id="step" name="step" class="mini-combobox"  
                          textField="name" valueField="id"  url="<%=request.getContextPath()%>/work/f10010202/queryProcessStepParams.action?param=step" showNullItem="true" allowInput="true"/> 
                    	
                    	<a class="mini-button" id="id_onSerach" iconCls="icon-add" onclick="exprotExcel2();">导出</a>
                    	<a class="mini-button" id="id_download" iconCls="icon-add" onclick="download();">查询下载</a>
    </fieldset>

	<fieldset id="fd2" style="width: 62%;float:left">
    <legend><span>导出项选择</span></legend>
					<div id="cbl1" class="mini-checkboxlist" repeatItems="6" repeatLayout="table"
				    textField="colname" valueField="columnname" value="" 
				    url="<%=request.getContextPath()%>/work/f100101/queryExportSets.action?iscall=1" >
			</div>
    </fieldset>
	<fieldset id="fd3" style="width: 30%;float:left;margin-left: 10px">
    <legend><span>导出项选择</span></legend>
		<div id="datagrid2" class="mini-datagrid" style="width:100%;height:300px;" allowResize="true"
			 url="<%=request.getContextPath()%>/common/querySavedFiles.action"
			 idField="stuid"  pageSize='100'  sortMode="client"    >
			<div property="columns">
				<div field="fileid" width="120" headerAlign="center" align="center" visible="false" allowSort="true">学生id</div>
				<div field="filepath" width="100" headerAlign="center" align="center" allowSort="true">文件</div>
				<div field="ctime" width="70" headerAlign="center" align="center" dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="true">创建时间</div>
				<div field="iscomplete" width="60" headerAlign="center" align="center" visible="false" allowSort="true"></div>
				<div field="do" width="40" headerAlign="center" align="center"  allowSort="true" renderer='onrenderDO'>下载</div>
			</div>
		</div>
	</fieldset>

    
   <div id="datagrid1" class="mini-datagrid" style="width:100%;height:300px;" allowResize="true" visible="false"
        url="<%=request.getContextPath()%>/work/f100101/queryExport.action" 
         idField="stuid"  pageSize='100'  sortMode="client"    >
        <div property="columns">
            <div field="stuid" width="120" headerAlign="center" align="center" visible="false" allowSort="true">学生id</div>    
            <div field="groupname" width="70" headerAlign="center" align="center" allowSort="true">用户组名称</div>  
            <div field="stu_name" width="60" headerAlign="center" align="center" allowSort="true">学生姓名</div>  
            <div field="cellphone" width="90" headerAlign="center"  align="center" allowSort="true" >手机</div> 
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
     <div id="exprotIfreamDiv__">
	    <iframe id="exportIFrame__" style="display:none;" src="<%=request.getContextPath()%>/pages/common/exportContent.jsp">
	    </iframe>
	</div>
</body>
<script type="text/javascript">
mini.parse();
var grid=mini.get("datagrid1");
var grid2=mini.get("datagrid2");
Web.util.load("datagrid1",{});
Web.util.load("datagrid2",{"userid":'${user.userid}'});
var usergrouptype='${user.grouptypeclass}';
mini.get("cbl1").setValue('deptname,stuid,educationtype,stu_level,stu_name,sex,nation,politicalstatus,cardid,cellphone,phone,qq');
function download() {
    Web.util.load("datagrid2",{"userid":'${user.userid}'});
}
function getParams(){
	/*var stu_level=mini.get("stu_level").getValue();
	var stu_name =mini.get("stu_name").getValue();
	var s_date =mini.get("s_date").getValue();
	var e_date =mini.get("e_date").getValue();
	if(s_date) s_date=mini.formatDate(s_date,'yyyyMMdd');
	if(e_date) e_date=mini.formatDate(e_date,'yyyyMMdd');
	return {stu_level:stu_level,stu_name:stu_name,s_date:s_date,e_date:e_date};*/
	var stu_level=mini.get("stu_level").getValue();
	var stu_name =mini.get("stu_name").getValue();
	var groupname =mini.get("groupname").getValue();
	var follow =mini.get("follow").getValue();
	var s_date =mini.get("s_date").getValue();
	var e_date =mini.get("e_date").getValue();
	var isstudent =mini.get("isstudent").getValue();
	if(s_date) s_date=mini.formatDate(s_date,'yyyyMMdd');
	if(e_date) e_date=mini.formatDate(e_date,'yyyyMMdd');
	var process =mini.get("process").getValue();
	var step =mini.get("step").getValue();
	if(process=='B') step='';
	return {stu_level:stu_level,stu_name:stu_name,s_date:s_date,e_date:e_date,isstudent:isstudent,groupname:groupname,followor:follow,process:process,step:step};
}
function exprotExcel(){
	var grid=mini.get("datagrid1");
	
	var params = getParams();
	
	if(!params){
		return;
	}
    
	    var cs=new Array();
	    var columns = mini.get("cbl1").getValue();
	    var url="<%=request.getContextPath()%>/work/f100101/queryExportSetsByarrays.action";
	    Web.util.request(url,'',{arrays:columns},function(data,textstatus){
	    	 for (var i= data.length-1; i>=0;i--) {
	         	  var c = {header:data[i].colname,field:data[i].columnname,width:100,code:data[i].iscode};
	              cs.push(c);
	    		 }
    		    cs=cs.reverse();
	    	    params.url = grid.url;
	    	  	params.header = mini.encode(cs);
	    	  	params.pageIndex = 0;
	    	  	params.pageSize = 65535;

	    	  	var form = document.getElementById('exportIFrame__').contentWindow.document.getElementById('excelForm__');
	    	  	for(o in params){
	    	  		//alert(o+":"+params[o]);
	    	  		
	    	  		if(!document.getElementById('exportIFrame__').contentWindow.document.getElementById(o))
	    	  			var input = $('#<input name="'+o+'" id="'+o+'"/>').appendTo(form);
	    	  		document.getElementById('exportIFrame__').contentWindow.document.getElementById(o).value = params[o];
	    	  		
	    	  	}
	    	  	form.submit();
	    	  	
	        });
  	
}
function exprotExcel2(){
	var grid=mini.get("datagrid1");

	var params = getParams();

	if(!params){
		return;
	}

	    var cs=new Array();
	    var columns = mini.get("cbl1").getValue();
	    var url="<%=request.getContextPath()%>/work/f100101/queryExportSetsByarrays.action";
	    Web.util.request(url,'',{arrays:columns},function(data,textstatus){
	    	 for (var i= data.length-1; i>=0;i--) {
	         	  var c = {header:data[i].colname,field:data[i].columnname,width:100,code:data[i].iscode};
	              cs.push(c);
	    		 }
    		    cs=cs.reverse();
	    	    params.url = grid.url;
	    	  	params.header = mini.encode(cs);
	    	  	params.pageIndex = 0;
	    	  	params.pageSize = 65535;

	    	  	var url="<%=request.getContextPath()%>/common/saveExcel.action";
				Web.util.request(url,"post",params,function () {

                })
            mini.showTips({
                content: "<font color='blue'>后台正在导出，请稍后</font>",
                timeout: 3000
            });
				mini.get("id_onSerach").disable();
            Web.util.load("datagrid2",{"userid":'${user.userid}'});
	        });

}
function onrenderDO(e){
    var iscomplete=e.record.iscomplete;
    console.info(e)
    if('1'==iscomplete)
    var link ='<a href="javascript:onDO()">下载</a>';
    else
        var link ='正在生成……';
    return link;
}

function onDO(){
    var fileid=grid2.getSelected().fileid;
    var filepath=grid2.getSelected().filepath;
    var url='<%=request.getContextPath()%>/common/downloadFile.action?fileid='+fileid+"&filepath="+filepath;
    window.open(url);
}
$(document).ready(function () {

    if(usergrouptype=='06') $("#id_onSerach").hide();
})
</script>
</html>
