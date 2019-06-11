<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>基本信息</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/miniui/boot.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/frame.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/pages/f10/f1001/f100101/permission.js"></script>
	 <style type="text/css">
	body{background-color:#fff;}
	#form1{width:80%;float:left;}
    </style>
    <script type="text/javascript">
    	
    </script>
</head>
<body>
<div id="form1">
          <table class="form-table" border="0" cellpadding="0"  cellspacing="4">
           <colgroup align="right" width="25%"></colgroup>
           <colgroup align="left" width="25%"></colgroup>
          <colgroup align="right" width="25%"></colgroup>
           <colgroup align="left" width="25%"></colgroup>
                <tr>
                    <td >部门名称:</td>
                    <td>
                       <input id="groupname" name="groupname" value="${groupname }" class="mini-textbox" enabled="false" /> <font color="red">*</font>
                       <input id="grouptypeclass" name="grouptypeclass" value="${grouptypeclass }" visible="false" class="mini-textbox" enabled="false" /> 
                       <input id="stuid" name="stuid"  visible="false" class="mini-textbox" enabled="false" /> 
                    </td>
                    <td >学历类别：</td>
                    <td>
                          <input id="educationtype" name="educationtype" class="mini-combobox" style="width:150px;" required="true"
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=educationtype" showNullItem="true" allowInput="true"/><font color="red">*</font> 
                           
                    </td>
                </tr>
                 <tr>
                    <td >学生级别（请严格核对）:</td>
                    <td>
                        <input id="stu_level" name="stu_level" class="mini-combobox" style="width:150px;" required="true"
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=stu_level" showNullItem="true" allowInput="true"/><font color="red">*</font> 
                            
                    </td>
                    <td >姓名：</td>
                    <td>
                          <input id="stu_name" name="stu_name" class="mini-textbox" required="true" vtype="rangeLength:2,50" /><font color="red">*</font> 
                    </td>
                </tr>
                 <tr>
                    <td >性别:</td>
                    <td>
                       <input id="sex" name="sex" class="mini-combobox" style="width:150px;" required="true"
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=sex" showNullItem="true" allowInput="true"/> <font color="red">*</font>
                    </td>
                    <td >民族：</td>
                    <td>
                          <input id="nation" name="nation" class="mini-combobox" style="width:150px;" required="true"
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=nation" showNullItem="true" allowInput="true"/> <font color="red">*</font>
                           
                    </td>
                </tr>
                <tr>
                    <td >政治面貌:</td>
                    <td>
                       <input id="politicalstatus" name="politicalstatus" class="mini-combobox" style="width:150px;" required="true"
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=politicalstatus" showNullItem="true" allowInput="true"/> <font color="red">*</font>
                            
                    </td>
                    <td >身份证号：</td>
                    <td>
                          <input id="cardid" name="cardid" class="mini-textbox" required="true" onvalidation="onIDCardsValidation" vtype="rangeLength:18,18" /><font color="red">*</font> 
                    </td>
                </tr>
                 <tr>
                    <td >出生年月日:</td>
                    <td>
                       <input id="birthday" name="birthday" class="mini-textbox" enabled="false" vtype="maxLength:24" /> 
                    </td>
                   <!--  <td >照片ID：</td>
                    <td>
                      <input id="photoid" name="photoid" class="mini-textbox" enabled="false" visible="false"/> 
                    </td> -->
                </tr>
                 <tr>
                    <td >手机:</td>
                    <td>
                       <input id="cellphone" name="cellphone" class="mini-textbox" required="true"  vtype="rangeLength:11,11" />
                       <input id="cellphone_dis" visible="false" enabled='false' class="mini-textbox"  required="true"  vtype="rangeLength:11,11" /><font color="red">*</font> 
                    </td>
                    <td >联系电话：</td>
                    <td>
                          <input id="phone" name="phone" class="mini-textbox"  vtype="rangeLength:6,20" /> 
                    </td>
                </tr>
                <tr>
                    <td >QQ:</td>
                    <td>
                       <input id="qq" name="qq" class="mini-textbox" vtype="maxLength:18" /> 
                    </td>
                    <td >其它联系方式：</td>
                    <td>
                          <input id="otherphone" name="otherphone" class="mini-textbox"  vtype="rangeLength:0,40" /> 
                    </td>
                </tr>
                <tr>
                    <td >微信号:</td>
                    <td>
                       <input id="email" name="email" class="mini-textbox" required="true" vtype="rangeLength:0,40" /> <font color="red">*</font>
                    </td>
                    <td >工作单位（请详细填写）：</td>
                    <td>
                          <input id="company" name="company" class="mini-textbox" required="true" vtype="rangeLength:4,50" /> <font color="red">*</font>
                    </td>
                </tr>
                <tr>
                    <td >客户群:</td>
                    <td>
                          <input id="clientclass" name="clientclass" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=clientclass" showNullItem="true" allowInput="true"/> 
                           
                    </td>
                    <td >家族住址：</td>
                    <td>
                          <input id="address" name="address" class="mini-textbox"  vtype="rangeLength:6,50" /> 
                    </td>
                </tr>
                <tr>
                    <td >学员信息采集归属地 ：<br/>填写标准：地级市名+区域或县级市名</td>
                    <td>
                       <input id="blongto" name="blongto" class="mini-textbox" vtype="maxLength:50" /> 
                    </td>
                    <td >考前辅导、事项办理就近区域 ：<br/>填写标准：地级市名+区域或县级市名</td>
                    <td>
                          <input id="nearby" name="nearby" class="mini-textbox"  vtype="rangeLength:6,50" /> 
                    </td>
                </tr>
                <tr>
                    <td >原学历层次：</td>
                    <td>
                        <input id="oldeducationlevel" name="oldeducationlevel" class="mini-combobox" style="width:150px;" required="true"
                          textField="name" valueField="id"  onvaluechanged="onSelectchanged"  url="<%=request.getContextPath()%>/admin/querySubSchoolsById.action" showNullItem="true" allowInput="true"/> <font color="red">*</font>
                            
                    </td>
                </tr>
                 <tr>
                    <td >报考层次:</td>
                    <td>
                       <input id="examlevel" name="examlevel" class="mini-combobox" style="width:150px;" required="true"
                          textField="name" valueField="id" onvaluechanged="onSelectchanged" showNullItem="true" allowInput="true"/><font color="red">*</font> 
                    </td>
                    <td >考试科类：</td>
                    <td>
                          <input id="examclass" name="examclass" class="mini-combobox" style="width:150px;" required="true"
                          textField="name" valueField="id" onvaluechanged="onSelectchanged" showNullItem="true" allowInput="true"/><font color="red">*</font> 
                    </td>
                </tr>
                <tr>
                    <td >第一志愿院校 ：<br/>选择后，手输院校将无法填写，请谨慎操作</td>
                    <td>
                       <input id="firstwishschool" name="firstwishschool" class="mini-combobox" style="width:150px;" required="true"
                          textField="name" valueField="id" onvaluechanged="onSelectchanged" showNullItem="true" allowInput="true"/><font color="red">*</font> 
                    </td>
                    <td >第一志愿专业：</td>
                    <td>
                          <input id="firstwishspecialty" name="firstwishspecialty" class="mini-combobox" style="width:150px;" required="true"
                          textField="name" valueField="id" onvaluechanged="onSelectchanged" showNullItem="true" allowInput="true"/><font color="red">*</font> 
                    </td>
                </tr>
                 <tr>
                    <td >学习形式：</td>
                    <td>
                       <input id="learningform" name="learningform" class="mini-combobox" style="width:150px;" required="true"
                          textField="name" valueField="id" onvaluechanged="onSelectchanged" showNullItem="true" allowInput="true"/><font color="red">*</font> 
                    </td>
                    <td >第一志愿学制 ：</td>
                    <td>
                          <input id="firstwishlength" name="firstwishlength" class="mini-textbox" required="true"  enabled='false' /><font color="red">*</font> 
                    </td>
                </tr>
                 <tr>
                    <td >征集志愿院校 ：</td>
                    <td>
                       <input id="collectwishschool" name="collectwishschool" class="mini-combobox" style="width:150px;" 
                          textField="name" valueField="id" onvaluechanged="onSelectchanged" showNullItem="true" allowInput="true"/> 
                    </td>
                    <td >征集志愿专业 ：</td>
                    <td>
                          <input id="collectwishspecialty" name="collectwishspecialty" class="mini-combobox" style="width:150px;" 
                          textField="name" valueField="id"  showNullItem="true" allowInput="true"/> 
                    </td>
                </tr>
                <tr>
                    <td >手输院校 ：</td>
                    <td>
                       <input id="manualschool" name="manualschool" class="mini-textbox" vtype="maxLength:100" /> 
                    </td>
                    <td >手输专业 ：</td>
                    <td>
                          <input id="manualspecialty" name="manualspecialty" class="mini-textbox"  vtype="rangeLength:6,100" /> 
                    </td>
                </tr>
                 <tr>
                    <td >手输学习形式 ：</td>
                    <td>
                       <input id="manualtype" name="manualtype" class="mini-textbox" vtype="maxLength:100" /> 
                    </td>
                    <td >手输学制 ：</td>
                    <td>
                          <input id="manuallength" name="manuallength" class="mini-textbox"  vtype="int" /> 
                    </td>
                </tr>
                <tr>
                    <td >获知学校来源 ：</td>
                    <td>
                       <input id="schoolresource" name="schoolresource" class="mini-combobox" style="width:150px;" required="true"
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=schoolresource" showNullItem="true" allowInput="true"/> <font color="red">*</font>
                           
                    </td>
                    <td >隶属关系 ：</td>
                    <td>
                           <input id="blongrelation" name="blongrelation" class="mini-combobox" style="width:150px;" required="true"
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=blongrelation" showNullItem="true" allowInput="true"/> <font color="red">*</font>
                        
                    </td>
                </tr>
                 <tr>
                    <td >介绍人 ：</td>
                    <td>
                       <input id="introducer" name="introducer" class="mini-textbox" vtype="maxLength:50" /> 
                    </td>
                    <td >介绍人电话 ：</td>
                    <td>
                          <input id="introducerphone" name="introducerphone" class="mini-textbox"  vtype="rangeLength:6,20" /> 
                    </td>
                </tr>
                 <tr>
                    <td >网络客服 ：</td>
                    <td>
                        <input id="netservice" name="netservice" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=netservice" showNullItem="true" allowInput="true"/> 
                         
                    </td>
                    <td >业绩人 ：</td>
                    <td>
                          <input id="performancer" name="performancer" class="mini-textbox" required="true" vtype="rangeLength:2,50" /><font color="red">*</font> 
                    </td>
                </tr>
                <tr>
                    <td >学员原归属人 ：</td>
                    <td>
                         <input id="oldbelong" name="oldbelong" class="mini-combobox" style="width:150px;" required="true"
                          textField="name" valueField="userid"  url="<%=request.getContextPath()%>/work/f100101/loadAllUser.action" showNullItem="true" allowInput="true"/> <font color="red">*</font>
                         
                    </td>
                    <td >跟进服务人 ：</td>
                    <td>
                           <input id="follow" name="follow" class="mini-combobox" style="width:150px;" required="true"
                          textField="name" valueField="userid"  url="<%=request.getContextPath()%>/work/f100101/loadAllUser.action" showNullItem="true" allowInput="true"/> <font color="red">*</font>
                        
                    </td>
                </tr>
                 <tr>
                    <td >录入人 ：   </td>
                    <td>
                    <input id="recorder" name="recorder" class="mini-combobox" style="width:150px;" required="true" enabled="false" value="${userid }"
                          textField="name" valueField="userid"  url="<%=request.getContextPath()%>/work/f100101/loadAllUser.action" showNullItem="true" allowInput="true"/> <font color="red">*</font>
                    </td>
                     <td >财务审核编号 ：</td>
                    <td>
                          <input id="financenumber" name="financenumber" class="mini-textbox" vtype="rangeLength:2,80" />
                    </td>
                </tr>
                 <tr>
                    <td >信息采集报名费 ：   </td>
                    <td>
                        <input id="entryfee" name="entryfee" class="mini-combobox" style="width:150px;" required="true"
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=entryfee" showNullItem="true" allowInput="true"/><font color="red">*</font>  
                          
                    </td>
                    <td >会员级别：</td>
                    <td>
                           <input id="memberlevel" name="memberlevel" class="mini-combobox" style="width:150px;" required="true"
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=memberlevel" showNullItem="true" allowInput="true"/><font color="red">*</font>  
                           
                    </td>
                </tr>
                 <tr>
                    <td >教材押金缴纳：   </td>
                    <td>
                         <input id="istextbook" name="istextbook" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/> 
                           
                    </td>
                    <td >光盘押金缴纳：</td>
                    <td>
                          <input id="isdisk" name="isdisk" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/> 
                          
                    </td>
                </tr>
                <tr>
                    <td >辅导教材领取：   </td>
                    <td>
                       <input id="istextbookcat" name="istextbookcat" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/> 
                           
                    </td>
                    <td >礼品领取 ：</td>
                    <td>
                            <input id="isgift" name="isgift" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/> 
                          
                    </td>
                </tr>
                <tr>
                    <td >专科学历真实可查：   </td>
                    <td>
                        <input id="iseduserach" name="iseduserach" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/> 
                           
                    </td>
                    <td >非当地户口开照相证明：</td>
                    <td>
                          <input id="isphoto" name="isphoto" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/> 
                          
                    </td>
                </tr>
                 <tr>
                    <td >生成正式报名信息 ：   </td>
                    <td>
                        <input id="iscreatenormal" name="iscreatenormal" class="mini-combobox" style="width:150px;" required="true"
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=iscreatenormal" showNullItem="true" allowInput="true"/> <font color="red">*</font>
                           
                    </td>
                     <td >信息建立时间 ：</td>
                    <td>
                          <input id="s_date" name="s_date" class="mini-datepicker" format="yyyyMMdd H:mm:ss" enabled="false" />
                    </td>
                </tr>
                <tr>
                 	<td >当前流程:</td>
                    <td>
                       <input id="processname" name="processname"  class="mini-textbox" enabled="false" /> 
                    </td>
                     <td >当前步骤:</td>
                    <td>
                       <input id="stepname" name="stepname"  class="mini-textbox" enabled="false" /> 
                    </td>
                </tr>
                 <tr>
                    <td >备注 ：   </td>
                    <td colspan="3">
                       <input id="comments" name="comments" class="mini-textarea" style="width:350px; height: 100px;"  vtype="maxLength:1000" /> 
                    </td>
                </tr>
                <tr>
            	<td></td>
	            <td colspan="2">
	                <input value="保存" id="doSubmit" type="button" onclick="submitForm()" />
	            </td>
	            <td></td>
        		</tr>
            </table>
</div>
</body>
<script type="text/javascript">
$("td:even").css("text-align","right");
$("td:odd").css("text-align","left");
$("input").css("width","150px");
$("#comments").css("width","350px");
$("#doSubmit").css("width","75px");
mini.parse();

//初始化加载
var stuid='${param.stuid}';
var processcode='${param.processcode}';
loadForm();
function loadForm() {
  //加载表单数据
   var url = '<%=request.getContextPath()%>/work/f100101/queryStudnetByStuid.action?stuid='+stuid;
   Web.util.formLoad("form1",url,'','',function(data){
   			//设置权限
   			var grouptypeclass=mini.get("grouptypeclass").getValue();
   			setPermission(grouptypeclass,processcode);
   			setdoSubmit(grouptypeclass,data.stepcode);
   			//重新加载学校、专业
   			Web.util.request(url,'','',function(data,textstatus){
   					
   					for(var v in data){
   						if('oldeducationlevel'==v){
	   							onLoadSelectchanged(v,data[v]);
   						}
   					}
   					for(var v in data){
   						if('examlevel'==v){
	   							onLoadSelectchanged(v,data[v]);
	   							mini.get(v).setValue(data[v]);
   						}
   					}
   					for(var v in data){
   						if('examclass'==v){
	   							onLoadSelectchanged(v,data[v]);
	   							mini.get(v).setValue(data[v]);
   						}
   					}
   					for(var v in data){
   						if('firstwishschool'==v){
	   							onLoadSelectchanged(v,data[v]);
	   							mini.get(v).setValue(data[v]);
   						}
   					}
   					for(var v in data){
   						if('collectwishschool'==v){
	   							onLoadSelectchanged(v,data[v]);
	   							mini.get(v).setValue(data[v]);
   						}
   					}
   					for(var v in data){
   						if('firstwishspecialty'==v){
	   							onLoadSelectchanged(v,data[v]);
	   							mini.get(v).setValue(data[v]);
   						}
   					}
   					for(var v in data){
   						if('learningform'==v){
	   							mini.get(v).setValue(data[v]);
   						}
   					}
  				 });
   			setbirthday();  
   });
   
   

}
function setIsVisible(){
	var grouptypeclass=mini.get("grouptypecalss").getValue();
	var form = new mini.Form("#form1");
	form.setEnabled(false);
}
function submitForm() {
	 var form = new mini.Form("#form1");
     form.validate();
    var grouptypeclass=mini.get("grouptypeclass").getValue();
     if (form.isValid() == false&&grouptypeclass!="06") {
      mini.alert('填写有误，请修正！');
      return;
      }
     mini.confirm("确定要保存？", "确定？",
            function (action) {
                if (action == "ok") {
                    //提交表单数据
					var url = '<%=request.getContextPath()%>/work/f100101/nextStep.action';
					var form = new mini.Form("#form1");                
					var data = form.getData(true);      //获取表单多个控件的数据
                    showHideMaps(data);//重新显示
					Web.util.requestAsync(url,'',data,function(data,textstatus){
							mini.alert("保存成功！ ");
							$("#doSubmit").attr("disabled","disabled");
						});
					/*Web.util.formSubmit("form1",url,"post",function(data,textstatus){
							mini.alert("保存成功！ ");
							$("#doSubmit").attr("disabled","disabled");
						});*/
                } 
            }
        );
	
      
   }

function showHideMaps(data){
    var _hideMaps=hideMaps;
    for(var obj in _hideMaps){
        data[obj]=_hideMaps[obj];
    }
}
  
function onSelectchanged(e){

	onLoadSelectchanged(e.sender.id,e.value);

}
function onLoadSelectchanged(parentid,value){
	//alert(parentid+":"+value);
	if("firstwishschool"==parentid&&!value){
		mini.get('manualschool').enable();
		mini.get('manualspecialty').enable();
		mini.get('manualtype').enable();
		mini.get('manuallength').enable();
	}
	if(!value) return;
	var parentvalue=value;
	var id;
	var id2;
	if("oldeducationlevel"==parentid){
		id="examlevel";
		mini.get("examclass").setValue('');
		mini.get("firstwishschool").setValue('');
		mini.get("firstwishspecialty").setValue('');
		mini.get("learningform").setValue('');
	}
	if("examlevel"==parentid){
		id="examclass";
		mini.get("firstwishschool").setValue('');
		mini.get("firstwishspecialty").setValue('');
		mini.get("learningform").setValue('');
	}
	if("examclass"==parentid){
		id="firstwishschool";
		id2="collectwishschool";
		mini.get("firstwishspecialty").setValue('');
		mini.get("learningform").setValue('');
	}
	if("firstwishschool"==parentid){
		id="firstwishspecialty";
		mini.get('manualschool').disable();
		mini.get('manualspecialty').disable();
		mini.get('manualtype').disable();
		mini.get('manuallength').disable();
		mini.get("learningform").setValue('');
	}
	if("collectwishschool"==parentid){
		id="collectwishspecialty";
	}
	if("firstwishspecialty"==parentid){
		id="learningform";
		Web.util.request("<%=request.getContextPath()%>/admin/querySchoolByid.action","",{id:parentvalue},function(data){
			mini.get('firstwishlength').setValue(data.ext);
		});
	}
	var url="<%=request.getContextPath()%>/admin/querySubSchoolsById.action?parentid="+parentvalue;
	mini.get(id).setUrl(url);
	if(id2){
	mini.get(id2).setUrl(url);
	}
}
</script>
<script type="text/javascript">
    	$(".form-table td:even").css("background-color","#c5d2e4");
    	$(".form-table td:odd").css("background-color","#f0f0f0");
    	var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"} 
    	function onIDCardsValidation(e) {
    	    if (e.isValid) {
    	        var pattern = /\d*/;
    	        if ((e.errorText=isCardID(e.value))!=true) {
    	            //e.errorText = "必须输入15~18位数字";
    	            e.isValid = false;
    	        }
    	    }
    	}

    	function isCardID(sId){
    		 var iSum=0 ;
    		 var info="" ;
    		 if(!/^\d{17}(\d|x)$/i.test(sId)) return "你输入的身份证长度或格式错误";
    		 sId=sId.replace(/x$/i,"a");
    		 if(aCity[parseInt(sId.substr(0,2))]==null) return "你的身份证地区非法";
    		 sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2));
    		 var d=new Date(sBirthday.replace(/-/g,"/")) ;
    		 if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate()))return "身份证上的出生日期非法";
    		 for(var i = 17;i>=0;i --) iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11) ;
    		 if(iSum%11!=1) return "你输入的身份证号非法";
    		 setbirthday(sBirthday)
    		 return true;
    		}
    	function setbirthday(sBirthday){
    		if(sBirthday==null){
    			var sId=mini.get("cardid").getValue();
    			sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2));
    			}
    		 var birthday=mini.get("birthday");
    		 if(birthday) birthday.setValue(sBirthday);
    	}
    </script>
</html>
