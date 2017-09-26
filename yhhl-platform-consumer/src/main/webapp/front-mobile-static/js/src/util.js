// currentModle 1:开发； 2:生产
var currentModle = 1;
//获取当前网址，如：
var curWwwPath=window.document.location.href;

//获取主机地址之后的目录如：/Tmall/index.jsp
var pathName=window.document.location.pathname;
var pos=curWwwPath.indexOf(pathName);

//获取主机地址，如：//localhost:8080
var localhostPath=curWwwPath.substring(0,pos);

//获取带"/"的项目名，如：/Tmall
var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);

var ctx = localhostPath+projectName;
if(currentModle==2){
	ctx = localhostPath;
}

function changeImgUrlToSmall(url){
	var tempUrl = url.substring(0,url.lastIndexOf("/"));
	var fileName = url.substring(url.lastIndexOf("/")+1);
	fileName = "s_"+fileName;
	return tempUrl+"/"+fileName;
}