$.extend($.fn.validatebox.defaults.rules, {
				CHS: {
					validator: function (value, param) {
						return /^[\u0391-\uFFE5]+$/.test(value);
					},
					message: '请输入汉字'
				},
				english : {// 验证英语
			        validator : function(value) {
			            return /^[A-Za-z]+$/i.test(value);
			        },
			        message : '请输入英文'
			    },
			    ip : {// 验证IP地址
			        validator : function(value) {
			            return /\d+\.\d+\.\d+\.\d+/.test(value);
			        },
			        message : 'IP地址格式不正确'
			    },
				ZIP: {
					validator: function (value, param) {
						return /^[0-9]\d{5}$/.test(value);
					},
					message: '邮政编码不存在'
				},
				QQ: {
					validator: function (value, param) {
						return /^[1-9]\d{4,10}$/.test(value);
					},
					message: 'QQ号码不正确'
				},
				mobile: {
					validator: function (value, param) {
						return /^(?:13\d|15\d|18\d)-?\d{5}(\d{3}|\*{3})$/.test(value);
					},
					message: '手机号码不正确'
				},
				tel:{
					validator:function(value,param){
						return /^(\d{3}-|\d{4}-)?(\d{8}|\d{7})?$/.test(value);
					},
					message:'电话号码不正确'
				},
				number: {
					validator: function (value, param) {
						return /^[0-9]+.?[0-9]*$/.test(value);
					},
					message: '请输入数字'
				},
				integer:{
					validator:function(value,param){
						return /^[+]?[1-9]\d*$/.test(value);
					},
					message: '请输入整数'
				},
				range:{
					validator:function(value,param){
						if(/^[1-9]\d*$/.test(value)){
							return value >= param[0] && value <= param[1]
						}else{
							return false;
						}
					},
					message:'输入的数字在{0}到{1}之间'
				},
				minLength:{
					validator:function(value,param){
						return value.length >=param[0]
					},
					message:'至少输入{0}个字'
				},
				maxLength:{
					validator:function(value,param){
						return value.length<=param[0]
					},
					message:'最多{0}个字'
				},
				//select即选择框的验证
				selectValid:{
					validator:function(value,param){
						if(value == param[0]){
							return false;
						}else{
							return true ;
						}
					},
					message:'请选择'
				},
				loginName: {
					validator: function (value, param) {
						return /^[\u0391-\uFFE5\w]+$/.test(value);
					},
					message: '登录名称只允许汉字、英文字母、数字及下划线。'
				},
				equalTo: {
					validator: function (value, param) {
						return value == $(param[0]).val();
					},
					message: '两次输入的字符不一至'
				}
			});


/** 
 * 扩展树表格级联勾选方法： 
 */  
$.extend($.fn.treegrid.methods,{  
    /** 
     * 级联选择 
     * @param {Object} target 
     * @param {Object} param  
     *      param包括两个参数: 
     *          id:勾选的节点ID
     *          imgsrc:图片地址 
     *          deepCascade:是否深度级联 
     * @return {TypeName}  
     */  
    cascadeCheck : function(target,param){  
        var opts = $.data(target[0], "treegrid").options;  
        if(opts.singleSelect)  
            return;  
        var idField = opts.idField;//这里的idField其实就是API里方法的id参数  
        var status = false;//用来标记当前节点的状态，true:勾选，false:未勾选  
        var selectNodes = $(target).treegrid('getSelections');//获取当前选中项  
        for(var i=0;i<selectNodes.length;i++){  
            if(selectNodes[i][idField]==param.id)  
                status = true;  
        }  
        var opSrc=$("#hasGive_"+param.id).attr("src");
        var tmppic = param.imgsrc+"tree_dnd_yes.png";
        var flag = "add";
        if(opSrc.indexOf("tree_dnd_yes.png")>0){
        	tmppic = param.imgsrc+"tree_dnd_no.png";
        	flag = "del";
        }
        if(flag=="del"){
	        $.messager.confirm('确认', '您确定要删除这个权限吗</font> ？',
					function(r) {
						if (r) {
		    giveActs(param.id,flag,tmppic);
	
			var mark = checkChildren(target[0],param.id,idField,flag);
	        //级联选择父节点  
	        selectParent(target[0],param.id,idField,status,param.imgsrc,mark,flag);  
	        selectChildren(target[0],param.id,idField,param.deepCascade,status,param.imgsrc,flag);  
						}
	        });
        }else{
        	giveActs(param.id,flag,tmppic);
        	
			var mark = checkChildren(target[0],param.id,idField,flag);
	        //级联选择父节点  
	        selectParent(target[0],param.id,idField,status,param.imgsrc,mark,flag);  
	        selectChildren(target[0],param.id,idField,param.deepCascade,status,param.imgsrc,flag);  
        }
        /** 
         * 级联选择父节点 
         */  
        function selectParent(target,id,idField,status,tmppic,mark,opType){  
            var parent = $(target).treegrid('getParent',id);  
            if(parent){  
                var parentId = parent[idField];  
                if("del"==opType && !mark){
                	if(status){  
                		$(target).treegrid('select',parentId);  
                	} else { 
                		$(target).treegrid('unselect',parentId);  
                	}
                	giveActs(parentId,"del",tmppic+"tree_dnd_no.png");
                }else if("add"==opType && !mark){
                	$(target).treegrid('select',parentId); 
                	giveActs(parentId,"add",tmppic+"tree_dnd_yes.png");
                }
                selectParent(target,parentId,idField,status,tmppic,mark,opType);  
            }  
        }  
        /** 
         * 级联选择子节点 
         */  
        function selectChildren(target,id,idField,deepCascade,status,tmppic,opType){  
            //深度级联时先展开节点  
            if(!status&&deepCascade)  
                $(target).treegrid('expand',id);  
            //根据ID获取下层孩子节点  
            var children = $(target).treegrid('getChildren',id);  
            for(var i=0;i<children.length;i++){  
                var childId = children[i][idField];  

                if(status){  
                    $(target).treegrid('select',childId); 
                } else { 
                    $(target).treegrid('unselect',childId);  
                }
                if("del"==opType){
                	if($("#hasGive_"+childId).attr("src").indexOf("tree_dnd_yes.png")>0){
	                	$("#hasGive_"+childId).attr("src",tmppic+"tree_dnd_no.png");
	                	giveActs(childId,"del",tmppic+"tree_dnd_no.png");
                	}
                }else{
                	$("#hasGive_"+childId).attr("src",tmppic+"tree_dnd_yes.png");
                	giveActs(childId,"add",tmppic+"tree_dnd_yes.png");
                }
                selectChildren(target,childId,idField,deepCascade,status,tmppic,opType);//递归选择子节点  
            }  
        }  
        
        function checkChildren(target,id,idField,flag){
        	 var parent = $(target).treegrid('getParent',id);  
    	    var pid = id;
            if(parent){  
            	pid = parent[idField];  
            }
            var mark = false;
            var children = $(target).treegrid('getChildren',pid);
            if(children){
	            for(var i=0;i<children.length;i++){  
	            	 var childId = children[i][idField]; 
	            	 if(childId == id){
	            	 }else{
	            		 if($("#hasGive_"+childId).attr("src").indexOf("tree_dnd_yes.png")>0){
	            			 return true;
	            		 }
	            		 mark = checkSubInfo(target,childId,idField);//递归选择子节点  
	            	 }
	            }
            }
            return mark;
        }
        function checkSubInfo(target,id,idField){
        	 var children = $(target).treegrid('getChildren',id);  
        	 if(children){
	             for(var i=0;i<children.length;i++){  
	             	 var childId = children[i][idField]; 
	             	  if($("#hasGive_"+childId).attr("src").indexOf("tree_dnd_yes.png")>0){
	             		 return true;
	             	  }
	             	  return checkSubInfo(target,childId,idField);//递归选择子节点  
	             }
        	 }
             return false;
        }
    }  
});  