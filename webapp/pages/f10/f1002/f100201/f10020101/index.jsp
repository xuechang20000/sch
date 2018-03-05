<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>基本信息</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/miniui/boot.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/frame.js"></script>
	
	 <style type="text/css">
	body{background-color:#fff;}
	#form1{width:90%;float:left;}
	#img{float:left;display: block; width:110px; background-color:#eeeeee; border-color:#000;}
    </style>
    
    
    
</head>
<body>
<div id="form1">
          <table class="form-table" border="0" cellpadding="0"  cellspacing="1">
           <colgroup align="right" width="20%"></colgroup>
           <colgroup align="left" width="30%"></colgroup>
          <colgroup align="right" width="25%"></colgroup>
           <colgroup align="left" width="25%"></colgroup>
                <tr>
                    <td >部门名称:</td>
                    <td>
                       <input id="deptName" value="${groupname }" class="mini-textbox" enabled="false" /> <font color="red">*</font>
                    </td>
                    <td >学历类别：</td>
                    <td>
                          <input id="educationtype2" name="educationtype2" class="mini-combobox" style="width:150px;" required="true"
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=educationtype2" showNullItem="true" allowInput="true"/><font color="red">*</font> 
                           
                    </td>
                </tr>
                 <tr>
                    <td >学生级别（请严格核对）:</td>
                    <td>
                        <input id="stu_level2" name="stu_level2" class="mini-combobox" style="width:150px;" required="true"
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=stu_level2" showNullItem="true" allowInput="true"/><font color="red">*</font> 
                            
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
                    <td >照片ID：</td>
                    <td>
                      <input id="photoid" name="photoid" class="mini-textbox" enabled="false" /> 
                    </td>
                </tr>
                 <tr>
                    <td >手机:</td>
                    <td>
                       <input id="cellphone" name="cellphone" class="mini-textbox" required="true"  vtype="rangeLength:11,11" /><font color="red">*</font> 
                    </td>
                    <td >联系电话：</td>
                    <td>
                          <input id="phone" name="phone" class="mini-textbox"  vtype="rangeLength:6,20" /> 
                    </td>
                </tr>
                 <tr>
                    <td >电话修改1：</td>
                    <td>
                       <input id="phone1" name="phone1" class="mini-textbox"   vtype="rangeLength:6,20" />
                    </td>
                    <td >电话修改2：</td>
                    <td>
                          <input id="phone2" name="phone2" class="mini-textbox"  vtype="rangeLength:6,20" /> 
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
                       <input id="email" name="email" class="mini-textbox" required="true" vtype="rangeLength:0,40" /><font color="red">*</font>
                    </td>
                    <td >工作单位（请详细填写）：</td>
                    <td>
                          <input id="company" name="company" class="mini-textbox" required="true" vtype="rangeLength:2,50" /> <font color="red">*</font>
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
                    <td >学员所在地 ：
                    <td colspan="3">
                       <input id="blongto" name="blongto" class="mini-textbox" vtype="maxLength:50" /> 填写标准：地级市名+区域或县级市名</td>
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
                    <td >班主任(学习顾问) ：</td>
                    <td>
                           <input id="follow" name="follow" class="mini-combobox" style="width:150px;" required="true"
                          textField="name" valueField="userid"  url="<%=request.getContextPath()%>/work/f100101/loadAllUser.action" showNullItem="true" allowInput="true"/> <font color="red">*</font>
                        
                    </td>
                    <td >辅导员(客服代表)：</td>
                    <td>
                        <input id="netservice2" name="netservice2" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=netservice2" showNullItem="true" allowInput="true"/> 
                         
                    </td>
                    
                </tr>
                 <tr>
                    <td >录入人：</td>
                    <td>
                    <input id="recorder" name="recorder" class="mini-combobox" style="width:150px;" required="true" enabled="false" value="${user.userid }"
                          textField="name" valueField="userid"  url="<%=request.getContextPath()%>/work/f100101/loadAllUser.action" showNullItem="true" allowInput="true"/> <font color="red">*</font>
                    </td>
                     <td >业绩人 ：</td>
                    <td>
                          <input id="performancer" name="performancer" class="mini-textbox" required="true" vtype="rangeLength:2,50" /><font color="red">*</font> 
                    </td>
                </tr>
                
                <tr>
                	<td >报名费：</td>
                    <td>
                        <input id="entryfee2" name="entryfee2" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=entryfee2" showNullItem="true" allowInput="true"/> 
                    </td>
                    <td>新华社照片采集费：</td>
                    <td>
                        <input id="intestfee" name="intestfee" class="mini-textbox"  vtype="float" />元
                    </td>
                </tr>
                 <tr>
                	<td >学号：</td>
                    <td>
                        <input id="stunumber" name="stunumber" class="mini-textbox"  vtype="rangeLength:2,50"/>
                        密码：<input id="stupassword" name="stupassword" class="mini-textbox"   vtype="rangeLength:2,50" />
                    </td>
                   <td >统考帐号：</td>
                    <td>
                        <input id="examnumber" name="examnumber" class="mini-textbox"   vtype="rangeLength:2,50"/>
                        密码：<input id="exampassword" name="exampassword" class="mini-textbox"  vtype="rangeLength:2,50" />
                    </td>
                </tr>
                  <tr>
                	<td >是否有计算机证书：</td>
                    <td colspan="3">
                        <input id="pcis" name="pcis" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/> 
                     <span>
                       	 是否代办：
 						<input id="pcdealis" name="pcdealis" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/> 
						 代办时间：
						   <input id="pcdealtime" name="pcdealtime" class="mini-datepicker" format="yyyyMMdd"  />
						 代办金额：
						   <input id="pcdealmoney" name="pcdealmoney" class="mini-textbox"  vtype="float" />
						 出证时间：
						   <input id="pcouttime" name="pcouttime" class="mini-datepicker" format="yyyyMMdd"  />
					</span>
						 是否申请免考：
						  <input id="pcexamis" name="pcexamis" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/> 
                    </td>
                </tr>
                <tr>
                	<td >是否有英语等级证书：</td>
                    <td colspan="3">
                        <input id="engis" name="engis" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/> 
                     <span>
                       	 是否代办：
 						<input id="engdealis" name="engdealis" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/> 
						 代办时间：
						   <input id="engdealtime" name="engdealtime" class="mini-datepicker" format="yyyyMMdd"  />
						 代办金额：
						   <input id="engdealmoney" name="engdealmoney" class="mini-textbox"  vtype="float" />
						 出证时间：
						   <input id="engouttime" name="engouttime" class="mini-datepicker" format="yyyyMMdd"  />
					</span>
						 是否申请免考：
						  <input id="engexamis" name="engexamis" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/> 
                    </td>
                </tr>
                
                <tr>
                	<td >计算机统考：</td>
                    <td colspan="3">
                     	是否通过：
                        <input id="pctkpassis" name="pctkpassis" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/> 
                       	 是否替考：
 						<input id="pctkreplaceis" name="pctkreplaceis" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/> 
						 统考地点：
						   <input id="pctkexamaddress" name="pctkexamaddress" class="mini-textbox" vtype="rangeLength:2,50" />
						 通过时间：
						   <input id="pctkpasstime" name="pctkpasstime" class="mini-datepicker" format="yyyyMMdd"  />
						 替考金额：
						   <input id="pctkreplacemoney" name="pctkreplacemoney" class="mini-textbox"  vtype="float" />
                    </td>
                </tr>
                <tr>
                	<td >英语统考：</td>
                    <td colspan="3">
                     	是否通过：
                        <input id="engtkpassis" name="engtkpassis" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/> 
                       	 是否替考：
 						<input id="engtkreplaceis" name="engtkreplaceis" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/> 
						 统考地点：
						   <input id="engtkexamaddress" name="engtkexamaddress" class="mini-textbox" vtype="rangeLength:2,50"  />
						 通过时间：
						   <input id="engtkpasstime" name="engtkpasstime" class="mini-datepicker" format="yyyyMMdd"  />
						 替考金额：
						   <input id="engtkreplacemoney" name="engtkreplacemoney" class="mini-textbox"  vtype="float" />
                    </td>
                </tr>
                 <tr>
                	<td >第一次统考：</td>
                    <td colspan="3">
                        <input id="tk1is" name="tk1is" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/>
                                                 统考金额：
						   <input id="tk1money" name="tk1money" class="mini-textbox"  vtype="float" /> 
                       	 统考是否通过：
 						<input id="tk1passis" name="tk1passis" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/> 
                    </td>
                </tr>
                <tr>
                	<td >第二次统考：</td>
                    <td colspan="3">
                        <input id="tk2is" name="tk2is" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/>
                                                 统考金额：
						   <input id="tk2money" name="tk2money" class="mini-textbox"  vtype="float" /> 
                       	 统考是否通过：
 						<input id="tk2passis" name="tk2passis" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/> 
                    </td>
                </tr>
                <tr>
                	<td >第三次统考：</td>
                    <td colspan="3">
                        <input id="tk3is" name="tk3is" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/>
                                                 统考金额：
						   <input id="tk3money" name="tk3money" class="mini-textbox"  vtype="float" /> 
                       	 统考是否通过：
 						<input id="tk3passis" name="tk3passis" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/> 
                    </td>
                </tr>
                <tr>
                	<td >第四次统考：</td>
                    <td colspan="3">
                        <input id="tk4is" name="tk4is" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/>
                                                 统考金额：
						   <input id="tk4money" name="tk4money" class="mini-textbox"  vtype="float" /> 
                       	 统考是否通过：
 						<input id="tk4passis" name="tk4passis" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/> 
                    </td>
                </tr>
                <tr>
                	<td >第五次统考：</td>
                    <td colspan="3">
                        <input id="tk5is" name="tk5is" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/>
                                                 统考金额：
						   <input id="tk5money" name="tk5money" class="mini-textbox"  vtype="float" /> 
                       	 统考是否通过：
 						<input id="tk5passis" name="tk5passis" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/> 
                    </td>
                </tr>
                <tr>
                	<td >是否大包：</td>
                    <td colspan="3">
                        <input id="tkallis" name="tkallis" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/>
                                                 大包金额：
						   <input id="tkallmoney" name="tkallmoney" class="mini-textbox"  vtype="float" /> 
                       	 统考是否通过：
 						<input id="tkallpassis" name="tkallpassis" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/> 
                    </td>
                </tr>
                <tr>
                	<td >作业是否完成：</td>
                    <td colspan="3">
                        <input id="workcompleateis" name="workcompleateis" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/>
                    </td>
                </tr>
                <tr>
                	<td >是否报考学位证：</td>
                    <td colspan="3">
                        <input id="degreeis" name="degreeis" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/>
                                                 通知学员时间  第一次：
						    <input id="degreenotice1time" name="degreenotice1time" class="mini-datepicker" format="yyyyMMdd"  />
						    第二次：
						    <input id="degreenotice2time" name="degreenotice2time" class="mini-datepicker" format="yyyyMMdd"  />
                       	 是否通过：
 						<input id="degreepassis" name="degreepassis" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/>
                                                 通过时间：
						    <input id="degreepasstime" name="degreepasstime" class="mini-datepicker" format="yyyyMMdd"  /> 
                    </td>
                </tr>
                
                 <tr>
                    <td >层次:</td>
                    <td>
                       <input id="examlevel2" name="examlevel2" class="mini-combobox" style="width:150px;" required="true"
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=examlevel2" showNullItem="true" allowInput="true"/><font color="red">*</font> 
                    </td>
                    <td >院校 ：
                    <td>
                       <input id="firstwishschool2" name="firstwishschool2" class="mini-combobox" style="width:150px;" required="true"
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=firstwishschool2" showNullItem="true" allowInput="true"/><font color="red">*</font> 
                    </td>
                   </tr>
                   <tr>
                    <td >专业：</td>
                    <td>
                          <input id="firstwishspecialty2" name="firstwishspecialty2" class="mini-combobox" style="width:150px;" required="true"
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=firstwishspecialty2" showNullItem="true" allowInput="true"/><font color="red">*</font> 
                    </td>
                    <td >学制 ：</td>
                    <td>
                           <input id="firstwishlength2" name="firstwishlength2" class="mini-combobox" style="width:150px;"   required="true"
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=firstwishlength2" showNullItem="true" allowInput="true"/><font color="red">*</font> 
                           
                    </td>
                </tr>
                
                 <tr>
                    <td >学费标准：   </td>
                    <td>
                        <input id="stufeetype" name="stufeetype" class="mini-combobox" style="width:150px;" required="true"
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=stufeetype" showNullItem="true" allowInput="true"/><font color="red">*</font>  
                          	金额：
                          	<input id="stufeemoney" name="stufeemoney" class="mini-textbox"  vtype="float" /> 
                    </td>
                    <td >书费预收标准：</td>
                    <td>
                           <input id="bookfeetype" name="bookfeetype" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=bookfeetype" showNullItem="true" allowInput="true"/> 
                    </td>
                </tr>
                  <tr>
                    <td >入学时间：   </td>
                    <td>
                        <input id="inschooltime" name="inschooltime" class="mini-datepicker" format="yyyyMMdd" required="true" /> <font color="red">*</font> 
                    </td>
                    <td >就近面授、考试地点：</td>
                    <td>
                           <input id="nearbyaddress" name="nearbyaddress" class="mini-combobox" style="width:150px;" required="true"
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=nearbyaddress" showNullItem="true" allowInput="true"/><font color="red">*</font>  
                    </td>
                </tr>
                <tr>
                	<td >书费缴费：</td>
                    <td colspan="3">
                    	应缴：
                        <input id="bookfeeshould" name="bookfeeshould" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=bookfeetype" showNullItem="true" allowInput="true"/>
                                                 实缴：
						<input id="bookfeeactual" name="bookfeeactual" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=bookfeetype" showNullItem="true" allowInput="true"/>
                       	是否缴费：
 						<input id="bookfeeis" name="bookfeeis" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/> 
                    </td>
                </tr>
                <tr>
                	<td >第一年缴费：</td>
                    <td colspan="3">
                    	应缴：
                        <input id="stufee1should" name="stufee1should" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=stufeetype" showNullItem="true" allowInput="true"/>
                                                 实缴：
						<input id="stufee1actual" name="stufee1actual" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=stufeetype" showNullItem="true" allowInput="true"/>
                       	是否缴费：
 						<input id="stufee1is" name="stufee1is" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/> 
                    </td>
                </tr>
                <tr>
                	<td >第二年缴费：</td>
                    <td colspan="3">
                    	应缴：
                        <input id="stufee2should" name="stufee2should" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=stufeetype" showNullItem="true" allowInput="true"/>
                                                 实缴：
						<input id="stufee2actual" name="stufee2actual" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=stufeetype" showNullItem="true" allowInput="true"/>
                       	是否缴费：
 						<input id="stufee2is" name="stufee2is" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/> 
                    </td>
                </tr>
                 <tr>
                	<td >学籍注册处：</td>
                    <td >
                        <input id="sturegisteraddress" name="sturegisteraddress" class="mini-combobox" style="width:150px;" required="true"
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=sturegisteraddress" showNullItem="true" allowInput="true"/><font color="red">*</font> 
                    </td>
                    
                </tr>
                <tr>
                	<td >预计毕业时间：</td>
                    <td >
                       <input id="graduatetimeabout" name="graduatetimeabout" class="mini-datepicker" required="true" format="yyyyMMdd"  /> <font color="red">*</font> 
                    </td>
                    <td >毕业时间：</td>
                    <td >
                     <input id="inschooltime" name="inschooltime" class="mini-datepicker" format="yyyyMMdd"  /> 
                    </td>
                </tr>
                <tr>
                	<td >毕业证号：</td>
                    <td >
                         <input id="graduatenumber" name="graduatenumber"  class="mini-textbox" vtype="rangeLenth:2,50"  /> 
                    </td>
                    <td >毕业证书：</td>
                    <td >
                        <input id="graduatecardis" name="graduatecardis" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=is" showNullItem="true" allowInput="true"/>
                    </td>
                </tr>
                <tr>
                	<td >状态：</td>
                    <td >
                        <input id="nowstatus" name="nowstatus" class="mini-combobox" style="width:150px;" 
                          textField="aaa103" valueField="aaa102"  url="<%=request.getContextPath()%>/common/code.action?codename=nowstatus" showNullItem="true" allowInput="true"/>
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
	                <input value="提交" id="doSubmit" type="button" onclick="submitForm()" />
	            </td>
        		</tr>
            </table>
</div>
<!-- 
<div id="img">
	<img src="" alt="" width="100" />
	<br/>
</div>
 -->
</body>
<script type="text/javascript">
$("td:even").css("text-align","right");
$("td:odd").css("text-align","left");
$("input").css("width","150px");
$("#stunumber,#stupassword,#examnumber,#exampassword,#stufeetype,#stufeemoney").css("width","70px");
$("#pcis,#pcdealis,#pcexamis,#engis,#engdealis,#engexamis,#pctkpassis,#pctkreplaceis,#engtkpassis,#engtkreplaceis,#tk1passis,#tk2passis ,#tk3passis,#tk4passis,#tk5passis,#tkallis,#tkallpassis,#workcompleateis,#degreeis,#degreepassis,#bookfeeis,#stufee1is,#stufee2is,#tk1is,#tk2is,#tk3is,#tk4is,#tk5is").css("width","40px");
$("#pcdealtime,#pcouttime,#engdealtime,#engouttime,#pctkpasstime,#engtkpasstime,#degreenotice1time,#degreenotice2time,#degreepasstime").css("width","90px");
$("#pcdealmoney,#tkallmoney,#engdealmoney,#pctkreplacemoney,#engtkreplacemoney,#tk1money,#tk2money,#tk3money,#tk4money,#tk5money,#tkallmoney,#bookfeeshould,#bookfeeactual,#stufee1should,#stufee1actual,#stufee2should,#stufee2actual").css("width","50px");
$("#pctkexamaddress,#engtkexamaddress").css("width","100px");
$("#comments").css("width","350px");
$("#doSubmit").css("width","75px");
mini.parse();

function submitForm() {
	 var form = new mini.Form("#form1");
     form.validate();
     if (form.isValid() == false) {
      mini.alert('填写有误，请修正！');
      return;
      }
     
	//提交表单数据
	var url = '<%=request.getContextPath()%>/work/f10020101/studentRemoteAdd.action';

	    var form = new mini.Form("#form1");                
		var data = form.getData(true);      //获取表单多个控件的数据
		Web.util.requestAsync(url,"post",data,function(data,textstatus){
			mini.alert("提交成功！ ");
			$("#doSubmit").attr("disabled","disabled");
		});
		
      
   }
</script>

<script type="text/javascript">
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
