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
                    <td >姓名：</td>
                    <td>
                          <input id="stu_name" name="stu_name" class="mini-textbox" enabled="false" vtype="rangeLength:2,50" />
                          <input id="stuid" name="stuid" class="mini-textbox" visible="false"  />
                          <input id="stu_cardid" name="stu_cardid" class="mini-textbox" value="0"/>
                    </td>
                    <td >网上报名信息是否录入：</td>
                    <td>
                          <input id="isenroll" name="isenroll" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/>
                           
                    </td>
                </tr>
                 <tr>
                    <td >招办信息确认已完成：</td>
                    <td>
                        <input id="isenrollensure" name="isenrollensure" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=isenrollensure" showNullItem="true" allowInput="true"/> 
                            
                    </td>
                </tr>
                 <tr>
                    <td >网上银行缴费确认：</td>
                    <td>
                       <input id="isnetpay" name="isnetpay" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=isnetpay" showNullItem="true" allowInput="true"/>
                    </td>
                </tr>
                <tr>
                    <td >准考证领取卡报名序号：</td>
                    <td>
                       <input id="enrollnumber" name="enrollnumber" class="mini-textbox"   vtype="maxLength:24" /> 
                    </td>
                    <td >准考证号：</td>
                    <td>
                          <input id="examnumber" name="examnumber" class="mini-textbox"   vtype="maxLength:24" /> 
                    </td>
                </tr>
                 <tr>
                    <td >考点名称：</td>
                    <td>
                        <input id="examaddress" name="examaddress" class="mini-textbox"   vtype="maxLength:24" /> 
                    </td>
                    <td >准考证发放：</td>
                    <td>
                      <input id="isexamcard" name="isexamcard" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/>
                    </td>
                </tr>
                 <tr>
                    <td >原始成绩:</td>
                    <td>
                       <input id="initscore" name="initscore" class="mini-textbox"  onvaluechanged="refreashcore()" vtype="int" />
                    </td>
                    <td >加分：</td>
                    <td>
                          <input id="plusscore" name="plusscore" class="mini-textbox" onvaluechanged="refreashcore()" vtype="int" /> 
                    </td>
                </tr>
                <tr>
                    <td >最终成绩:</td>
                    <td>
                       <input id="finalscore" name="finalscore" class="mini-textbox" onvaluechanged="refreashcore()" vtype="maxLength:18" /> 
                    </td>
                    <td >是否作弊：</td>
                    <td>
                          <input id="ischeat" name="ischeat" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/>
                    </td>
                </tr>
                <tr>
                    <td >是否过录取线：</td>
                    <td>
                       <input id="isenterline" name="isenterline" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/>

                    </td>
                </tr>
                <tr>
                    <td >是否录取:</td>
                    <td>
                         <input id="isenter" name="isenter" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=isenter" showNullItem="true" allowInput="true"/>

                    </td>
                    <td >最终录取结果：</td>
                    <td>
                           <input id="finalenter" name="finalenter" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=finalenter" showNullItem="true" allowInput="true"/>

                    </td>
                </tr>
                 <tr>
                    <td >最终录取层次：</td>
                    <td>
                    <input id="oldeducationlevel" name="oldeducationlevel" class="mini-combobox" style="width:150px;" visible="false" 
                          textField="name" valueField="id"  onvaluechanged="onSelectchanged"  url="<%=request.getContextPath()%>/admin/querySubSchoolsById.action" showNullItem="true" allowInput="true"/> 
                            
                       <input id="examlevel" name="examlevel" class="mini-combobox" style="width:150px;" 
                          textField="name" valueField="id" onvaluechanged="onSelectchanged" showNullItem="true" allowInput="true"/> 
                    </td>
                    <td >最终录取科类：</td>
                    <td>
                          <input id="examclass" name="examclass" class="mini-combobox" style="width:150px;" 
                          textField="name" valueField="id" onvaluechanged="onSelectchanged" showNullItem="true" allowInput="true"/> 
                    </td>
                </tr>
                <tr>
                    <td >最终录取学校：</td>
                    <td>
                       <input id="firstwishschool" name="firstwishschool" class="mini-combobox" style="width:150px;" 
                          textField="name" valueField="id" onvaluechanged="onSelectchanged" showNullItem="true" allowInput="true"/> 
                    </td>
                    <td >最终录取专业：</td>
                    <td>
                          <input id="firstwishspecialty" name="firstwishspecialty" class="mini-combobox" style="width:150px;" 
                          textField="name" valueField="id" onvaluechanged="onSelectchanged" showNullItem="true" allowInput="true"/> 
                    </td>
                </tr>
                 <tr>
                    <td >最终学习形式：</td>
                    <td>
                       <input id="learningform" name="learningform" class="mini-combobox" style="width:150px;" 
                          textField="name" valueField="id" onvaluechanged="onSelectchanged" showNullItem="true" allowInput="true"/> 
                    </td>
                    <td >学制：</td>
                    <td>
                          <input id="firstwishlength" name="firstwishlength" class="mini-textbox"  enabled='false' /> 
                    </td>
                </tr>
                <tr>
                    <td >是否生成学籍：</td>
                    <td>
                      <input id="isstudent" name="isstudent" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/>
                    </td>
                    <td >学籍注册处：</td>
                    <td>
                          <input id="studentaddress" name="studentaddress" class="mini-textbox"   vtype="maxLength:24" /> 
                    </td>
                </tr>
                <tr>
                    <td >学费标准 ：</td>
                    <td>
                       <input id="tuition" name="tuition" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=tuition" showNullItem="true" allowInput="true"/> 
                           
                    </td>
                    <td >第一年应缴学费：</td>
                    <td>
                           <input id="tuitionfirst" name="tuitionfirst" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=tuition" showNullItem="true" allowInput="true"/> 
                        
                    </td>
                </tr>
                 <tr>
                    <td >书费预收标准：</td>
                    <td>
                       <input id="bookfee" name="bookfee" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=bookfee" showNullItem="true" allowInput="true"/> 
                        
                    </td>
                    <td >入学时间：</td>
                    <td>
                          <input id="inschooldate" name="inschooldate" class="mini-datepicker" format="yyyyMMdd"  /> 
                    </td>
                </tr>
                <tr>
               		<td>预计毕业时间：</td>
                    <td>
                          <input id="outschooldate" name="outschooldate" class="mini-datepicker" format="yyyyMMdd" /> 
                    </td>
                    <td>毕业证书是否发放：</td>
                    <td>
                        <input id="isgraduate" name="isgraduate" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/>                    </td>
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
$(".form-table td:even").css("text-align","right");
$(".form-table td:odd").css("text-align","left");
$("input").css("width","150px");
$("#comments").css("width","350px");
$("#doSubmit").css("width","75px");
mini.parse();

//初始化加载
var stuid='${param.stuid}';
loadForm();
var age;
function loadForm() {
  //加载表单数据
   var url = '<%=request.getContextPath()%>/work/f100101/queryStuExtByid.action?stuid='+stuid;
   Web.util.formLoad("form1",url,'','',function(){
   			//重新加载学校、专业
   			Web.util.request(url,'','',function(data,textstatus){
   					for(var v in data){
   						if('oldeducationlevel'==v){
	   							onLoadSelectchanged(v,data[v]);
	   							mini.get(v).setValue(data[v]);
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
				 age=getAgeByIDcard(mini.get("stu_cardid").getValue());
				 if(age>=25) mini.get("plusscore").setValue(20);
				 refreashcore();
   });
   
}

function submitForm() {
	 var form = new mini.Form("#form1");
     form.validate();
     if (form.isValid() == false) {
      mini.alert('填写有误，请修正！');
      return;
      }
     mini.confirm("确定要保存？", "确定？",
            function (action) {
                if (action == "ok") {
                    //提交表单数据
					var url = '<%=request.getContextPath()%>/work/f100101/updateStuExt.action';
					var form = new mini.Form("#form1");                
					var data = form.getData(true);      //获取表单多个控件的数据
					Web.util.requestAsync(url,'',data,function(data,textstatus){
							mini.alert("保存成功！ ");
							$("#doSubmit").attr("disabled","disabled");
						});
                } 
            }
        );
	
      
   }
 
  
function onSelectchanged(e){

	onLoadSelectchanged(e.sender.id,e.value);

}
function onLoadSelectchanged(parentid,value){
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
		mini.get("firstwishspecialty").setValue('');
		mini.get("learningform").setValue('');
	}
	if("firstwishschool"==parentid){
		id="firstwishspecialty";
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
	}

	function getAgeByIDcard(UUserCard){
		var myDate = new Date();
		var month = myDate.getMonth() + 1;
		var day = myDate.getDate();

		var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1;
		if (UUserCard.substring(10, 12) < month || UUserCard.substring(10, 12) == month && UUserCard.substring(12, 14) <= day) {
			age++;
			} 
		return age;
		}
	function refreashcore(){
		var initscore=mini.get("initscore").getValue();
		initscore=parseInt(initscore);
		var plusscore=mini.get("plusscore").getValue();
		plusscore=parseInt(plusscore);
		mini.get("finalscore").setValue(initscore+plusscore);
		}
</script>
</html>
