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
// 正式环境下ctx为无工程名
if(currentModle==2){
	ctx = localhostPath;
}
// 如果有大图，小图区分，这个是将大图地址替换为小图地址
function changeImgUrlToSmall(url){
	var tempUrl = url.substring(0,url.lastIndexOf("/"));
	var fileName = url.substring(url.lastIndexOf("/")+1);
	fileName = "s_"+fileName;
	return tempUrl+"/"+fileName;
}
// 多张图片，只取出第一张
function getFirstImg(ctx,imgUrl){
	if(imgUrl.length==0 || imgUrl==""){
		return ctx+"/images/default.jpg";
	}
	var imgUrls = "";
	if(imgUrl.indexOf(",")>0){
		imgUrls = imgUrl.split(",");
		return ctx+imgUrls[0];
	}else{
		return ctx+imgUrl;
	}
}
// 弹出提示
function alertMsg(msg,gotoUrl){
	$.confirm({
	    title: '',
	    content: msg,
	    autoClose: '确定|3000',
	    buttons: {
	        确定: function () {
	           if(gotoUrl && gotoUrl!="" && gotoUrl!=undefined){
	        	   document.location.href=ctx+gotoUrl;
	           }
	        }
	    }
	});
}

/**
 * 数字格式化成货币格式
 * @param {Object} val
 * @return {TypeName} 
 */
function moneyFormatter(val) {
	var f1 = val;
	var f2 = (Math.round((f1-0) * 100)) / 100;
	f2 = Math.floor(f2) == f2 ? f2 + ".00" : (Math.floor(f2 * 10) == f2 * 10) ? f2 + '0' : f2;
	f2 = String(f2);
	r = /(\d+)(\d{3})/;
	fs = String(f2);
	while (r.test(f2)) {
	      f2 = f2.replace(r, '$1' + ',' + '$2');
	}
	return ('￥' + f2);
}

/**
 * 数字格式化成货币格式
 * @param {Object} val
 * @return {TypeName} 
 */
function moneyFormatterNoY(val) {
	var f1 = val;
	var f2 = (Math.round((f1-0) * 100)) / 100;
	f2 = Math.floor(f2) == f2 ? f2 + ".00" : (Math.floor(f2 * 10) == f2 * 10) ? f2 + '0' : f2;
	f2 = String(f2);
	r = /(\d+)(\d{3})/;
	fs = String(f2);
	while (r.test(f2)) {
	      f2 = f2.replace(r, '$1' + ',' + '$2');
	}
	return (f2);
}
function  formatPercent(val) {  
    var reg = /[\$,%]/g;  
    var key = parseFloat( String(val).replace(reg, '')).toFixed(2); // toFixed小数点后两位  
    return isNaN(key) ? 0.00 : key;  
};
function  formatPrice(val) {  
	var result = "";
	if(val==null||val==""){
		result = "-";
	}else{
		//result = val/100;
		var f1 = val;
		var f2 = (Math.round((f1-0) * 100)) / 10000;
		f2 = Math.floor(f2) == f2 ? f2 + ".00" : (Math.floor(f2 * 10) == f2 * 10) ? f2 + '0' : f2;
		f2 = String(f2);
		r = /(\d+)(\d{3})/;
		fs = String(f2);
		while (r.test(f2)) {
		      f2 = f2.replace(r, '$1' + ',' + '$2');
		}
		result = f2;
	}
	return result;
};
function  formatValue(val) {  
	var result = "";
	if(val==null){
		result = "-";
	}else{
		result = val;
	}
	return result;
};

function dateFormat(dtdt){
	dtdt = dtdt+"";
	var dt = new Array();
	for(var i=0; i<dtdt.length; i++){
		dt[i] = dtdt.charAt(i);
	}
	var dDate = "";
	for(var i=0; i<dt.length; i++){
		dDate = dDate+dt[i];
		if((i+1)%2==0 && i>2 && i<7){
			dDate = dDate+"-";
		}else if((i+1)%2==0 && i>7 && i<dt.length-1){
			dDate = dDate+":";
		}else if(i==7){
			dDate = dDate+" ";
		}
	}
	return dDate;
}

String.prototype.endWith=function(s){
  if(s==null||s==""||this.length==0||s.length>this.length){
     return false;
  }
  if(this.substring(this.length-s.length)==s){
     return true;
  }else{
     return false;
  }
};

String.prototype.startWith=function(s){
	  if(s==null||s==""||this.length==0||s.length>this.length){
		  return false;
	  }
	  if(this.substr(0,s.length)==s){
	     return true;
	  }else{
	     return false;
	  }
	  //return true;
};