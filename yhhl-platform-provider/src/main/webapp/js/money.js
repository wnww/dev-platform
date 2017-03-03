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