<%@ page contentType="text/html; charset=utf-8"%>

	原学历层次：
		<input id="oldeducationlevel" name="oldeducationlevel" class="mini-combobox" style="width:150px;"
			   textField="name" valueField="id"  onvaluechanged="onSelectchanged"  url="/sch/admin/querySubSchoolsById.action" showNullItem="true" allowInput="true"/>
&nbsp;&nbsp;
	


	报考层次:
	
		<input id="examlevel" name="examlevel" class="mini-combobox" style="width:150px;"
			   textField="name" valueField="id" onvaluechanged="onSelectchanged" showNullItem="true" allowInput="true"/>
&nbsp;&nbsp;
	考试科类：
	
		<input id="examclass" name="examclass" class="mini-combobox" style="width:150px;"
			   textField="name" valueField="id" onvaluechanged="onSelectchanged" showNullItem="true" allowInput="true"/>
<br>


	第一志愿院校 ：
	
		<input id="firstwishschool" name="firstwishschool" class="mini-combobox" style="width:150px;"
			   textField="name" valueField="id" onvaluechanged="onSelectchanged" showNullItem="true" allowInput="true"/>
&nbsp;&nbsp;
	第一志愿专业：
	
		<input id="firstwishspecialty" name="firstwishspecialty" class="mini-combobox" style="width:150px;"
			   textField="name" valueField="id" onvaluechanged="onSelectchanged" showNullItem="true" allowInput="true"/>

<script>
    function onSelectchanged(e){
        if(!e.value) return;
        var parentid=e.value;
        var id;
        var id2;
        if("oldeducationlevel"==e.sender.id){
            id="examlevel";
            mini.get("examclass").setValue('');
            mini.get("firstwishschool").setValue('');
            mini.get("firstwishspecialty").setValue('');
        }
        if("examlevel"==e.sender.id){
            id="examclass";
            mini.get("firstwishschool").setValue('');
            mini.get("firstwishspecialty").setValue('');
        }
        if("examclass"==e.sender.id){
            id="firstwishschool";
            mini.get("firstwishspecialty").setValue('');
        }
        if("firstwishschool"==e.sender.id){
            id="firstwishspecialty";
        }
        if("collectwishschool"==e.sender.id){
            id="collectwishspecialty";
        }
        if("firstwishspecialty"==e.sender.id){
            Web.util.request("/sch/admin/querySchoolByid.action","",{id:parentid},function(data){
                //mini.get('firstwishlength').setValue(data.ext);
            });
        }
        var url="/sch/admin/querySubSchoolsById.action?parentid="+parentid;
        mini.get(id).setUrl(url);
        if(id2){
            mini.get(id2).setUrl(url);
        }
    }
</script>
