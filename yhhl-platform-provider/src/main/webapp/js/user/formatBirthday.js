function formatDatebox(value) { 
	if (value == null || value == '') {  
        return '';  
    }  
    var dt = value.substring(0,10);
    return dt; 
}
function formatOper(value, row, index) {
	var str = "";
	var row = $('#dataPageList').datagrid('getData').rows[index];
	if(row.state=='已认证'){
		str = '<a id="freeze'+index
	     +'" class="l-btn l-btn-plain" onclick="deleteCate()" href="javascript:void(0)">'
		 +'<span class="l-btn-left">'
	     +'<span class="l-btn-text icon-add l-btn-icon-left">冻结</span>'
	     +'</span></a>';
		
	}else if(row.state=='冻结'){
		str = '<a id="thaw'+index+'" class="l-btn l-btn-plain" onclick="thaw()" href="javascript:void(0)">'
		 +'<span class="l-btn-left">'
	     +'<span class="l-btn-text icon-remove l-btn-icon-left">解冻</span>'
	     +'</span></a>';
	}
	return str;
}