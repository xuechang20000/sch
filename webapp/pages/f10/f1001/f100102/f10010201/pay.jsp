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
	<h1 id="h_nodename">缴费操作</h1>      
  
    <div style="width:100%;">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:100%;">
                        <a class="mini-button" iconCls="icon-add" onclick="edit()">缴费</a>     
                    </td>
                </tr>
            </table>           
        </div>
    </div>
   <div id="datagrid1" class="mini-datagrid" style="width:100%;height:200px;" allowResize="true"
        url="<%=request.getContextPath()%>/work/f10010201/queryStudentPayByStuid.action"  idField="id"  pageSize='10'
    >
        <div property="columns">
            <div type="checkcolumn" ></div>        
            <div field="stuid" width="120" headerAlign="center" visible="false" allowSort="true">学生ID</div>    
            <div field="year" width="40" headerAlign="center" allowSort="true">年度</div>  
            <div field="seqnumber" width="50" headerAlign="center" allowSort="true">第几年</div>  
            <div field="payable" width="60" headerAlign="center" allowSort="true">学费应缴</div> 
            <div field="paidin" width="60" headerAlign="center" allowSort="true">学费实缴</div> 
            <div field="paydate" width="90" headerAlign="center" allowSort="true" dataType="date" dateFormat="yyyy-MM-dd HH:mm:ss">学费实缴日期</div> 
            <div field="bookable" width="60" headerAlign="center" allowSort="true">书费应缴</div> 
            <div field="bookin" width="60" headerAlign="center" allowSort="true">书费实缴</div> 
            <div field="bookdate" width="90" headerAlign="center" allowSort="true" dataType="date" dateFormat="yyyy-MM-dd HH:mm:ss">书费实缴日期</div>    
            <div field="ispay" width="50" headerAlign="center" allowSort="true" renderer="renStatus">是否缴费</div>   
            <div field="comments" width="100" headerAlign="center" allowSort="true">备注</div>    
        </div>
    </div>
</body>
<script type="text/javascript">
mini.parse();
//接收参数
var stuid='${param.stuid}';
var id='${param.id}';
var grid=mini.get("datagrid1");
grid.load({stuid:stuid});

var h_name;
if('L'==id) h_name='第一年缴费提醒';
if('M'==id) h_name='第二年缴费提醒';
if('N'==id) h_name='第三年缴费提醒';
if('O'==id) h_name='第四年缴费提醒';
if('P'==id) h_name='第五年缴费提醒';
$("#h_nodename").html(h_name);

	function edit() {

		var row = grid.getSelected();
		var payid=row.payid;
		if (row) {
			mini.open({
						url : '<%=request.getContextPath()%>/pages/f10/f1001/f100102/f10010201/updatePay.jsp?payid='+payid,
						title : "操作缴费",
						width : 700,
						height : 300,
						onload : function() {

						},
						ondestroy : function(action) {
							grid.reload();

						}
					});

		} else {
			alert("请选中一条记录");
		}

	}

function renStatus(v){
	if(v.value=='1') return '已缴'
	else
		return '未缴';
}
</script>
</html>
