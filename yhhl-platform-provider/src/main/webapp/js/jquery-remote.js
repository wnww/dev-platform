function doRmeote(params,remoteKey,serviceName){
	$.ajax({
		url: 'doService.do?remoteKey='+remoteKey+'&serviceName='+serviceName,
		type: 'POST',
		cache: false,
		data: params,
		success:function(data){
	    	var result = data;//jQuery.parseJSON(data);
	    	return result;
	    },
	    error:function(messg)  { 
       	    $.messager.alert('错误提示', messg.responseText, 'error');
       }  
	});
}