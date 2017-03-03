function initRadio(elemname,selVl){
	var myobj=document.getElementsByName(elemname);
	for ( var i = 0; i < myobj.length; i++) {
		if (myobj[i].value == selVl) {
			myobj[i].checked = "true";
		}
	}
 }
 
 function initSelect(elemid,selVl){
	var myobj=document.getElementById(elemid);
	
	for ( var i = 0; i < myobj.options.length; i++) {
		if (myobj.options[i].value == selVl) {
			myobj.selectedIndex = i;
		}
	}
 }