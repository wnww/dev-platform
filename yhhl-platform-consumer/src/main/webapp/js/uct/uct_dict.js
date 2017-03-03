
var webCtx="/";


//标的企业经济类型
var jjlxurl = webCtx + "/dict/pub/dict-select.action?cateEname=jjlx";

//公司类型
var gslxurl = webCtx + "/dict/pub/dict-select.action?cateEname=gslx";

//币种[支持汇率]
var moneytypeurl=webCtx + "/dict/pub/dict-select.action?cateEname=moneytype";

//所属行业
var industryurl=webCtx + "/dict/pub/dict-select.action?cateEname=industry";

//省
var provinceurl=webCtx + "/dict/pub/dict-select.action?cateEname=province";

//央企名录
var YQlisturl=webCtx + "/dict/pub/dict-select.action?cateEname=yqlist";

//单据科目
var billtypeurl=webCtx + "/dict/pub/dict-select.action?cateEname=billtype";	

//转让方经济类型
var sellerjjlxurl=webCtx + "/dict/pub/dict-select.action?cateEname=sellerjjlx";

//受让方经济类型
var buyerjjlxurl=webCtx + "/dict/pub/dict-select.action?cateEname=buyerjjlx";

//G0项目类型
var urltypeG0=webCtx + "/dict/pub/dict-select.action?cateEname=typeG0";;

//----------------

	function loadSelect(paraSelectId,paraCateEname){
		var myUrl = webCtx+'/dict/pub/dict-select.action?cateEname='+paraCateEname;
		//alert(myUrl);
		$('#'+paraSelectId).combobox({
			 url:myUrl,
			 valueField:'id',
			 editable:false,
			 textField:'text'		    
		});
	}

	function setSelectValue(paraSelectId,paraVlue){
		$('#'+paraSelectId).combobox('setValue',paraVlue);
	}
	
	/**
	*省份城市
	*/	

	var dictProvId ='';//省份的下拉列表id
	var dictCityId='';//城市的下拉列表id
	//var dictProvValue=''; //省份的初始值
	//var dictCityValue='';//城市的初始值
	
	function reloadCity(){
		var provinceTmp = $('#'+dictProvId).combobox('getValue');
		//alert(dictCityId);
		$('#'+dictCityId).combobox('clear');
		//alert(provinceTmp);
		if(provinceTmp.length>0){			
			var cityUrlTmp=webCtx+'/dict/pub/dict-select.action?cateEname=cty&fatherValue='+provinceTmp;
			$('#'+dictCityId).combobox('reload',cityUrlTmp);	
			//alert(cityUrlTmp);
		}else{
			$('#'+dictCityId).combobox('loadData',[]);
		}
	}
	
	function loadProvince(){
		$('#'+dictProvId).combobox({
			 url:webCtx+'/dict/pub/dict-select.action?cateEname=cty&fatherValue=0',
			 valueField:'id',
			 editable:false,
			 textField:'text',
			 editable:false,
			 onChange :function(){reloadCity();}		    
		});	
	}
	
	function loadCity(provinceValue){
		var cityUrl=webCtx+'/dict/pub/dict-select.action?cateEname=cty&fatherValue='+provinceValue;
		$('#'+dictCityId).combobox({
			    url:cityUrl,		    
			    valueField:'id',
			    textField:'text',
			    editable:false,
			    textField:'text'

		});			
	}
	
	function setProvinceValue(dictProvValue){
		$('#'+dictProvId).combobox('setValue',dictProvValue);
	}
	
	function setCityValue(dictCityValue){
		$('#'+dictCityId).combobox('setValue',dictCityValue);
	}