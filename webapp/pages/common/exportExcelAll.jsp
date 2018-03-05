<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


				    <!-- grid分页 -->
				    <div>
				    	<div id="exportExcel">
					        <span class="separator"></span>
					        <a class="mini-button" iconCls="icon-add" plain="true" id="add" onclick="exprotExcel(this)">导出本页</a>
					        <a class="mini-button" iconCls="icon-add" plain="true" id="add" onclick="exprotExcelAll(this)">导出全部</a>
					    </div>
					    
					    <div id="exprotIfreamDiv__">
						    <iframe id="exportIFrame__" style="display:none;" src="<%=request.getContextPath()%>/pages/common/exportContent.jsp">
						    </iframe>
					    </div>
				    </div>



	<script type="text/javascript">
		function exprotExcel(e){
			var grid = e.getParent().getParent();
			
			var paramsStr = $('#'+grid.id).attr('key___');
			var params = $.parseJSON(paramsStr);
			var columns = grid.getBottomColumns();
			
			if(!params){
				return;
			}
            
            function getColumns(columns) {
                columns = columns.clone();
                for (var i= columns.length-1; i>=0;i--) {
                    var column = columns[i];
                    if(!column.field||!column.visible){
                        columns.removeAt(i);
                    }else{
                    	var code = 0;
                    	if(column.renderer){
                    		code = 1;
                    	}
                    	var c = {header:column.header,field:column.field,width:column.width,code:code};
	                    columns[i] = c;
                    }
                }
                return columns;
            }
            
            var columns = getColumns(columns);
            var header = mini.encode(columns);
          	params.url = grid.url;
          	params.header = header;
          	params.pageIndex = grid.pageIndex;
          	params.pageSize = grid.pageSize;
          	
          	var form = document.getElementById('exportIFrame__').contentWindow.document.getElementById('excelForm__');
          	for(o in params){
          		var input = $('#<input name="'+o+'" id="'+o+'"/>').appendTo(form);
          		document.getElementById('exportIFrame__').contentWindow.document.getElementById(o).value = params[o];
          	}
          	form.submit();
		}
		function exprotExcelAll(e){
			var grid = e.getParent().getParent();
			
			var paramsStr = $('#'+grid.id).attr('key___');
			var params = $.parseJSON(paramsStr);
			var columns = grid.getBottomColumns();
			
			if(!params){
				return;
			}
            
            function getColumns(columns) {
                columns = columns.clone();
                for (var i= columns.length-1; i>=0;i--) {
                    var column = columns[i];
                    if(!column.field||!column.visible){
                        columns.removeAt(i);
                    }else{
                    	var code = 0;
                    	if(column.renderer){
                    		code = 1;
                    	}
                    	var c = {header:column.header,field:column.field,width:column.width,code:code};
	                    columns[i] = c;
                    }
                }
                return columns;
            }
            
            var columns = getColumns(columns);
            var header = mini.encode(columns);
          	params.url = grid.url;
          	params.header = header;
          	params.pageIndex = 0;
          	params.pageSize = 65535;
          	
          	var form = document.getElementById('exportIFrame__').contentWindow.document.getElementById('excelForm__');
          	for(o in params){
          		var input = $('#<input name="'+o+'" id="'+o+'"/>').appendTo(form);
          		document.getElementById('exportIFrame__').contentWindow.document.getElementById(o).value = params[o];
          	}
          	form.submit();
		}
       
	</script>
</html>
	