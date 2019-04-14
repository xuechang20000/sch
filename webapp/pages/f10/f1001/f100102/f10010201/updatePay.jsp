<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>detail</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/miniui/boot.js "></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/md5.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/frame.js "></script>
	
	<style type="text/css">
    </style>
    
</head>
<body>
	<h1 id="h_nodename"></h1>      
    <fieldset id="fd2">
        <legend><span>缴费信息</span></legend>
        <div class="fieldset-body" id="form1">
        <table class="form-table" border="0" cellpadding="1" cellspacing="4">
           <colgroup align="right" width="20%"></colgroup>
           <colgroup align="left" width="25%"></colgroup>
          <colgroup align="right" width="20%"></colgroup>
           <colgroup align="left" width="25%"></colgroup>
                <tr>
                    <td >年度：</td>
                    <td>
                          <input id="year" name="year" class="mini-textbox" enabled="false"  /> 
                    </td>
                    <td >第几年：</td>
                    <td>
                          <input id="seqnumber" name="seqnumber" class="mini-textbox" enabled="false" /> 
                    </td>
                </tr>
                 <tr>
                    <td >学费应缴：</td>
                    <td>
                          <input id="payable" name="payable" class="mini-textbox" vtype="int" /> 
                    </td>
                    <td >学费实缴：</td>
                    <td>
                          <input id="paidin" name="paidin" class="mini-textbox" vtype="int" /> 
                    </td>
                </tr>
                <tr>
                    <td >书费应缴：</td>
                    <td>
                          <input id="bookable" name="bookable" class="mini-textbox" vtype="int" /> 
                    </td>
                    <td >书费实缴：</td>
                    <td>
                          <input id="bookin" name="bookin" class="mini-textbox" vtype="int" /> 
                    </td>
                </tr>
                <tr>
                    <td >是否缴费：</td>
                    <td>
                          <input id="ispay" name="ispay" class="mini-combobox"  textField="text" valueField="id" 
     						data="[{'id':'0','text':'未缴费'},{'id':'1','text':'已缴费'}]"	required="true" allowInput="true"/>   
                    </td>
                    <td >财务审核收据编号：</td>
                <td>
                        <input id="ticketnumber" name="ticketnumber" class="mini-textbox"  />
                    </td>
                </tr>
                <tr>
                    <td >备注：</td>
                    <td colspan="3">
                          <input id="comments" name="comments" class="mini-textbox" style="width:200px" vtype="rangeLength:0,100" /> 
                    </td>
                </tr>
                  <tr>
            	<td></td>
	            <td colspan="2">
	                <input value="保存" id="doSubmit" type="button" onclick="submitForm()" />
	            </td>
        		</tr>
            </table>
        </div>
    </fieldset>
</body>
<script type="text/javascript">
mini.parse();
var payid=${param.payid};
loadForm();
function loadForm(){
	var url='<%=request.getContextPath()%>/work/f10010201/queryStudentPayByPayid.action';
	 Web.util.formLoad("form1",url,'',{payid:payid},function(){

		 });
}
function submitForm(){
	
	 //提交表单数据
    var form = new mini.Form("#form1");  
    form.validate();
    if (form.isValid() == false) return;
    
    var payable=mini.get("payable").getValue();
    var paidin=mini.get("paidin").getValue();
    var bookable=mini.get("bookable").getValue();
    var bookin=mini.get("bookin").getValue();
    var ispay=mini.get("ispay").getValue();
    var comments=mini.get("comments").getValue();
    var ticketnumber=mini.get("ticketnumber").getValue();
    $.ajax({
        url: "<%=request.getContextPath()%>/work/f10010201/updateStudentPay.action",
        type: "post",
        data: {payid:payid, payable:payable,paidin:paidin,bookable:bookable,bookin:bookin,ispay:ispay,comments:comments,ticketnumber:ticketnumber },
        success: function (text) {
            alert("保存成功!");
            mini.get("id_saveuser").disable();
        }
    });
}
</script>
</html>
