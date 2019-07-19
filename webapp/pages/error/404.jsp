<%@ page contentType="text/html; charset=utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta charset="utf-8">
	<title>青岛市室内装饰行业协会室内环境监测中心</title>
	<meta name="renderer" content="webkit">
	<style>
		a{text-decoration: underline}
	</style>
</head>
<body class="childrenBody">
	<div style="text-align: center; padding:11% 0;">
		<i class="layui-icon" style="line-height:20rem; font-size:20rem; color: #393D50;">&#xe61c;</i>
		<p style="font-size: 20px; font-weight: 300; color: #999;">我勒个去，页面被外星人挟持了!<a onclick="detail()">详情</a></p>
		<p id="detail" style="display:none">${ex.message}</p>
	</div>
<script type="text/javascript">
	function detail() {
		document.getElementById("detail").style.display='block';
    }
</script>
</body>
</html>