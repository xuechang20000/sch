<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/miniui/boot.js "></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/javascript/frame.js "></script>
	<title>欢迎页</title>
<style type="text/css">
body, div, ul, ol, li, dl, dt, dd, h1, h2, h3, h4, h5, h6, p, span, em, strong, img, form, fieldset, input, textarea, button, pre, table, tr, th, td, blockquote, code, label, cite, i { padding: 0; margin: 0 }
body{ font-family:微软雅黑;}
.huanying{text-align:center;  padding-top:230px; overflow: hidden;}
</style>	
</head>

<body>
<br/>
<h2>待办事项</h2>
<br/>
 <div id="datagrid1" class="mini-datagrid" style="width:350px;height:350px;" allowResize="true" onload="onRender"
        url="<%=request.getContextPath()%>/work/f100101/queryStuCountsByCurentUser.action"  idField="id"  pageSize='100'
    >
        <div property="columns">
            <div field="processcode" width="60" headerAlign="center" align="center" allowSort="true">流程代码</div>    
            <div field="processname" width="100" headerAlign="center" align="center" allowSort="true">流程名称</div>  
            <div field="vcount" width="40" headerAlign="center" align="center" allowSort="true">待办业务量</div>  
        </div>
    </div>
<!-- <div class="huanying"><img src="<%=request.getContextPath()%>/resources/images/login/huanying.png"/></div>
 -->
</body>
<script type="text/javascript">
mini.parse();
var grid=mini.get("datagrid1");
	grid.load({},function(){
		onRender();
		});

renderPreBM();
renderDiaodui();
function onRender(){

	var rows=grid.findRows(function(row){
		return true;
	});
for(var i=0;i<rows.length;i++){
	///alert(rows[i].processname);
	
	var trees=$('#leftTree', window.parent.document).find(".mini-menuitem-text").filter(function(){
		return rows[i].processname==$(this).html();
	});
	trees.each(function(j){
		//alert($(this).html());
		    var initval=$(this).html();
			$(this).html(initval+'('+rows[i].vcount+')');
		});
	}
}
function  renderPreBM() {
    var  url="<%=request.getContextPath()%>/work/f10010113/queryStuListByCurentUserPreCount.action";
    Web.util.request(url,"post",{},function(value){
        setCount('预报名登记',value);
    })
}
function  renderDiaodui() {
    var  url="<%=request.getContextPath()%>/work/f100101/queryStuListByCurentUserPubCount.action";
    Web.util.request(url,"post",{iscreatenormal:3},function(value){
        setCount('掉队',value);
    })
}
function setCount(name,vcount) {
    var trees=$('#leftTree', window.parent.document).find(".mini-menuitem-text").filter(function(){
        return $(this).html()==name;
    });
    trees.each(function(j){
        //alert($(this).html());
        var initval=$(this).html();
        $(this).html(initval+'('+vcount+')');
    });
}
</script>
</html>
