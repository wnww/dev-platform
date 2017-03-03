
//一个月有多少天
function getDaysInMonth(year,month){
    var lastday = new Date(year,month,0);
    return lastday.getDate();
}

//一个月的第一天是星期几，一月month=
function firstDayNum(paraYear,month){
      var mth = parseInt(month)-1;
      var temp = new Date(paraYear,mth,1);
      //temp.setDate('0');
      return temp.getDay();
}

/**
 * 
 * @param {Object} dayStr
 * @param {Object} isWorkday
 */
function setDayCss(dayStr,isWorkday){
	var dayObj = document.getElementById(dayStr);
	if(dayObj){		
		if("T"==isWorkday){
		}else{
			dayObj.className = dayObj.className+" calendar-selected ";			
		}
	}

}


//生成某个月的日历
function workDay(year,month){
	
	var all = 7 * 6 ;
	var totalDay = getDaysInMonth(year,month);//该月有多少天
	var numOfWeek  = firstDayNum(year,month);//该月第一天是星期几
	if(numOfWeek==0){
		numOfWeek = 7;		
	}
	//alert(month+":"+numOfWeek);
	
	var str_1 = '<div style="width:190px;height:205px;" class="easyui-calendar calendar">';
	var str_2 = '<div class="calendar-body" style="width:190px;height:180px;">	'	;
	var str_3 = '<div class="calendar-header"><span>'+year+'年'+month+'月</span></div>';
	var str_4 = '<table cellspacing="0" cellpadding="0" border="0">';
	var str_6 = '<thead><tr><th>一</th><th>二</th><th>三</th><th>四</th><th>五</th><th>六</th><th>日</th></tr></thead>';
	var str_7 = '<tbody>';
	
	document.write(str_1);
	document.write(str_2);
	document.write(str_3);
	document.write(str_4);
	document.write(str_6);
	document.write(str_7);
	
	var monthStr =""+ month;
	if(monthStr.length<2){
		monthStr = "0"+monthStr;
	}
	//alert(monthStr);
	var dayNum = 1;
	
	for(var i=1;i<=all;i++){
		var txt = "&nbsp;";
		var css = "calendar-day";
		var idtxt = "";
		var oncliktxt ="";
//		alert("numOfWeek:"+numOfWeek);
//		alert("totalDay:"+totalDay);
//		alert("dayNum:"+dayNum);
		if(i>=numOfWeek && dayNum<=totalDay){//	calendar-selected		
			
			txt = ""+(dayNum);
			var dayStr = ""+dayNum;
			if(dayStr.length<2){
				dayStr="0"+dayStr;				
			}
			idtxt = 'id="'+year+''+monthStr+''+dayStr+'" ';
			oncliktxt=' onClick="clickDateNode(this);" ';
			dayNum++ ;						
		}
		if(i%7==0){
			css = "calendar-day calendar-sunday";
		}
		if((i+1)%7==0 ){
			css = "calendar-day calendar-saturday";
		}
		
		if((i-1)%7==0){ document.write('<tr>');}
		//alert("到达"+ '<td '+idtxt+' class="'+css+'" '+oncliktxt+'>'+txt+'</td>');
		document.write('<td '+idtxt+' class="'+css+'" '+oncliktxt+'>'+txt+'</td>');
		if(i%7==0){document.write('</tr>');} 	
	}
	
	var tail = '</tbody></table></div></div>';
	document.write(tail);
	
}