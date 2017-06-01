function keyPress(ob) {
    if (!ob.value.match(/^[+]?\d*?\.?\d*?$/)) ob.value = ""; else ob.t_value = ob.value; if (ob.value.match(/^(?:[+]?\d+(?:\.\d+)?)?$/)) ob.o_value = ob.value;
}
function keyUp(ob) {
    if (!ob.value.match(/^[+]?\d*?\.?\d*?$/)) ob.value = ""; else ob.t_value = ob.value; if (ob.value.match(/^(?:[+]?\d+(?:\.\d+)?)?$/)) ob.o_value = ob.value;if(ob.value.match(/^\.\d+$/))ob.value=0+ob.value;
}
function onBlur(ob) {
 	if(!ob.value.match(/^(?:[+]?\d+(?:\.\d+)?|\.\d*?)?$/))ob.value="";else{if(ob.value.match(/^\.\d+$/))ob.value=0+ob.value;if(ob.value.match(/^\.$/))ob.value=0;ob.o_value=ob.value};
}
function disabledNum(obj,count){
    var key=obj.value;
    if(key!=""&&count!=""){
    	var ck_num=Math.pow(10, count);
    	if(Number(key)>=ck_num){
    		obj.value=ck_num;
    	}
    	//onBlur(obj);
    	//keyPress(obj);
    	keyUp(obj);
        
     }
}
function disabledNum_N(obj,count){
	var key=obj.value;
	if(!key.match(/^\-?[0-9]*\.?\d*$/)){
		obj.value="";
		key="";
	}
    if(key!=""&&count!=""){
    	var ck_num=Math.pow(10, count);
    	if(Number(key)>=ck_num){
    		obj.value=ck_num;
    	}
    	if(Number(key)<=-ck_num){
    		obj.value=-ck_num;
    	}
    }
}
	function ReturnPage(pUrl){
		window.location.href=pUrl+'jsp/cesuan/cesuan.jsp';
	}
