<%@ page language="java"  pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
<title>404</title>

<style type="text/css">
body { margin: 0; background: #67ace4; font-family: "微软雅黑"; }
#wrap { margin: 100px auto 0; width: 399px; }
#text { text-align: center; padding-top: 20px; }
#text strong { display: inline-block; font-size: 14px; font-weight: normal; }
#text strong * { float: left; }
#text a { line-height: 14px; color: #fff; text-decoration: none; padding: 6px 14px; margin: 1px 0 0 10px; border: 1px solid #fff; border-radius: 14px; user-select: none; -webkit-user-select: none; -webkit-tap-highlight-color: rgba(0,0,0,0); transition: 0.2s ease-out; -o-transition: 0.2s ease-out; -moz-transition: 0.2s ease-out; -webkit-transition: 0.2s ease-out; }
#text a:hover, #text a:active { background: #fff; color: #000; }

@media screen and (max-width: 414px) {
#wrap, #wrap img, #text span { width: 200px; }
#text span { background-position: center; margin-bottom: 16px; }
#text a:nth-of-type(1) { margin-left: 0; }
}
</style>

</head>
<body >

<div id="wrap">
	<div>
		<img src="<%=request.getContextPath()%>/pages/common/error/404.png" alt="404">
	</div>
	<div id="text">
		<strong>
			<span></span>
			<a href="javascript:window.location.href='<%=request.getContextPath()%>/pages/index.jsp'">返回首页</a>
			<a href="javascript:window.history.back();">返回上一页</a>
		</strong>
	</div>
</div>


</body></html>
