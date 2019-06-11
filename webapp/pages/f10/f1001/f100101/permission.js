
//基本信息
var baseinfo =['educationtype','stu_level','stu_name','sex','nation','politicalstatus','cardid','cellphone','phone','qq','otherphone','email','company','clientclass','address','blongto','nearby'];
//学校专业信息
var schoolinfo=['oldeducationlevel','examlevel','examclass','firstwishschool','firstwishspecialty','learningform','collectwishschool','collectwishspecialty','manualschool','manualspecialty','manualtype'];
//其它信息
var otherinfo=['schoolresource','blongrelation','introducer','introducerphone','netservice','performancer','oldbelong','follow','iseduserach','isphoto','iscreatenormal'];
//财务信息
var fundinfo=['entryfee','memberlevel','istextbook','isdisk','istextbookcat','isgift','financenumber'];

//隐藏属性
var hideMaps={};
function setPermission(grouptypeclass,processcode){
	/*管理员		01
	总校校长		02
	分校校长		03
	主任		04
	员工		05
	财务		06*/
	//关键信息隐藏
	if('04'==grouptypeclass||'06'==grouptypeclass){
		var cellphone=mini.get("cellphone").getValue()
		mini.get("cellphone_dis").setValue(cellphone.substr(0,3)+"****"+cellphone.substr(7,4));
		mini.get("cellphone_dis").show();
		mini.get("cellphone").hide();
		mini.get("phone").hide();
		mini.get("qq").hide();
		mini.get("email").hide();
		mini.get("company").hide();
	}
	if('01'==grouptypeclass||'02'==grouptypeclass||'03'==grouptypeclass){
		
	}else if('06'==grouptypeclass){
		setDisable(baseinfo);
		setDisable(schoolinfo);
		setDisable(otherinfo);
		setHide(['cardid','cellphone','phone','qq','email','address'])
	}else if('05'==grouptypeclass){
		//setDisable(baseinfo);
		//setDisable(schoolinfo);
		//setDisable(otherinfo);
		setDisable(fundinfo);
	}else if('04'==grouptypeclass){
		setDisable(fundinfo);
		//setDisable(baseinfo);
		//setDisable(schoolinfo);
		//setDisable(otherinfo);
	}else{
		setDisable(baseinfo);
		setDisable(schoolinfo);
		setDisable(otherinfo);
		setDisable(fundinfo);
	}
	//根据流程判断
	//财务审核都可以修改
	if(processcode=='B'){
		setEnable(baseinfo);
		setEnable(schoolinfo);
		setEnable(otherinfo);
		setEnable(fundinfo);
	}
	//报名前确认员工，主任可以修改
	/*if(processcode=='C'&&('05'==grouptypeclass||'04'==grouptypeclass)){
		setEnable(baseinfo);
		setEnable(schoolinfo);
		setEnable(otherinfo);
	}*/
}
function setdoSubmit(grouptypeclass,stepcode){

if('04'==grouptypeclass&&'2'!=stepcode.substr(1,1)){
		$("#doSubmit").hide();
	}
if('02'==grouptypeclass&&'5'!=stepcode.substr(1,1)){
	$("#doSubmit").hide();
}
if('03'==grouptypeclass&&'4'!=stepcode.substr(1,1)){
	$("#doSubmit").hide();
}
}
function setDisable(list){
	for(var i=0;i<list.length;i++){
		mini.get(list[i]).disable();
	}
}
function setEnable(list){
	for(var i=0;i<list.length;i++){
		mini.get(list[i]).enable();
	}
}
function setHide(list) {
    for(var i=0;i<list.length;i++){
    	hideMaps[list[i]]=mini.get(list[i]).getValue();
        mini.get(list[i]).setValue("******");
    }
}