	var editIndex = undefined;
	function endEditing(tabId){
		if (editIndex == undefined){return true}
		if ($('#'+tabId).datagrid('validateRow', editIndex)){
			//$('#'+tabId).datagrid('getRows')[editIndex]['operaton']= '<a href="javascript:void(0);" onclick="remove1(\''+tabId+'\')">删除</a>';
			$('#'+tabId).datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickRow(tabId,index){
		if (editIndex != index){
			if (endEditing(tabId)){
				$('#'+tabId).datagrid('selectRow', index)
						.datagrid('beginEdit', index);
				editIndex = index;
			} else {
				$('#'+tabId).datagrid('selectRow', editIndex);
			}
		}
	}

	function append(tabId){
		if (endEditing(tabId)){
			$('#'+tabId).datagrid('appendRow',{status:'P'});
			editIndex = $('#'+tabId).datagrid('getRows').length-1;
			$('#'+tabId).datagrid('selectRow', editIndex)
					.datagrid('beginEdit', editIndex);
		}
	}
	function removeInfo(tabId,url){
		var row = $('#'+tabId).datagrid('getSelected');
		if (row) {
	      if(!row.valueId){
	    	  var rowIndex = $('#'+tabId).datagrid('getRowIndex', row);
	    	  $('#'+tabId).datagrid('deleteRow', rowIndex);  
			  $('#'+tabId).datagrid('reload');//删除后重新加载下
			  editIndex = undefined;
		  }else{
			$.ajax({
				type: "post",
				url: url+"?id="+row.valueId,
				dataType: "json",
				success: function(data){
					var result = data;
					if(result.flag=='T'){
						$.messager.alert('结果', '操作成功', 'info');	
						 var rowIndex = $('#'+tabId).datagrid('getRowIndex', row);
						   $('#'+tabId).datagrid('deleteRow', rowIndex);  
						   $('#'+tabId).datagrid('reload');//删除后重新加载下
						   editIndex = undefined;
					}else if(result.flag=='H'){
						$.messager.alert('结果', result.msg, 'info');	
					}else{
						$.messager.alert('结果', '操作失败，请重试', 'error');	
					}
				}
			});
		  }
	    }
	}
	function accept(tabId,datas,url){
			if (endEditing(tabId)){
				doSubmit(url,datas,tabId);
			}
		}
	function saveTransactionTime(tabId,value4){
		$('#'+tabId).datagrid('acceptChanges');
		var row = $('#'+tabId).datagrid('getSelected');
		if (row){
			var datas ={rulesId:$("#rulesId").val(),rulesCode:$("#rulesCode").val(),sourceId:$("#sourceId").val(),value1:row.value1,value2:row.value2,value3:row.value3,value4:value4,valueId:row.valueId};
			accept(tabId,datas,'');
		}
	}
	
	function saveOther(tabId){
		$('#'+tabId).datagrid('acceptChanges');
		var row = $('#'+tabId).datagrid('getSelected');
		if (row){
			var datas ={rulesId:$("#rulesId").val(),rulesCode:$("#rulesCode").val(),sourceId:$("#sourceId").val(),value1:row.value1,value2:row.value2,valueId:row.valueId};
			accept(tabId,datas,'');
		}
	}
