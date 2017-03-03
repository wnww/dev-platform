/**
 * 中百信EasyUI工具类
 * @author 郝启敏
 **/
var ZBXTools = {};
/**
 *显示进度条 
 *@param msg 进度条需要提示的信息
 **/
ZBXTools.showProcess=function(msg){
	$.messager.progress({"msg":msg,text:''});
}

/**
 *关闭进度条
 **/
ZBXTools.hideProcess = function(){
	$.messager.progress('close');
}

/**
 *启用linkbutton按钮 
 * @param selector jquery选择器
 **/
ZBXTools.enableButton=function(selector){
	$(selector).linkbutton('enable');
}

/**
 *禁用linkbutton按钮 
 * @param selector jquery选择器
 **/
ZBXTools.disableButton=function(selector){
	$(selector).linkbutton('disable');
}
 /**
  * 设置单选按钮选中的值
  * @param elemid 对象id
  * @param selVl 值
  */
ZBXTools.initRadio=function(elemname,selVl){
	var myobj=document.getElementsByName(elemname);
	if(myobj==null)return;
	for ( var i = 0; i < myobj.length; i++) {
		if (myobj[i].value == selVl) {
			myobj[i].checked = "true";
		}
	}
 }
 /**
  * 设置下拉框选中的值
  * @param elemid 对象id
  * @param selVl 值
  */
 ZBXTools.initSelect=function(elemid,selVl){
	var myobj=document.getElementById(elemid);
	if(myobj==null)return;
	for ( var i = 0; i < myobj.options.length; i++) {
		if (myobj.options[i].value == selVl) {
			myobj.selectedIndex = i;
		}
	}
 }
/**
 * 获取下拉框选中的text
 * @param id 对象id
 * @return 选中的text
 */
 ZBXTools.getSelectTextById=function(id){
 	if(ZBXTools.getSelectValueById(id)!=''){
 		return $("#"+id).find("option:selected").text();
 	}
	return '';
 }
 /**
 * 获取下拉框选中的value
 * @param id 对象id
 * @return 选中的value
 */
 ZBXTools.getSelectValueById=function(id){
	return $("#"+id).find("option:selected").val();
 }
 /**
  * //消息提示
  * @param 头部面板上显示的标题文字。
  * @param 显示的消息文字。
  * @param 定义消息窗口的高度。
  * @param 定义消息窗口的宽度。
  * @param  如果定义为 0，除非用户关闭，消息窗口将不会关闭。如果定义为非 0 值，消息窗口将在超时后自动关闭。
  * @param 定义消息窗口如何显示。可用的值是： null、slide、fade、show。
  */
ZBXTools.showMsg=function(titleStr,msgStr,heightStr,widthStr,timeoutStr,showtypeStr){
	/**
	 * showType： 定义消息窗口如何显示。可用的值是： null、slide、fade、show。默认是 slide。
	 * showSpeed： 定义消息窗口完成显示所需的以毫秒为单位的时间。默认是 600。
	 * width： 定义消息窗口的宽度。默认是250。
	 * height： 定义消息窗口的高度。默认是100。
	 * msg： 显示的消息文字。
	 * title： 头部面板上显示的标题文字。
	 * timeout： 如果定义为 0，除非用户关闭，消息窗口将不会关闭。如果定义为非 0 值，消息窗口将在超时后自动关闭。
	 */
	$.messager.show({
		title:titleStr,
		showSpeed:1500,
		msg:msgStr,
		width:widthStr,
		height:heightStr,
		timeout:timeoutStr,
		showType:showtypeStr
	});
}