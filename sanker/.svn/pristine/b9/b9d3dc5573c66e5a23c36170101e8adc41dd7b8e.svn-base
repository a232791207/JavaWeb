var thresholdcolors=[['20%','darkred'], ['10%','red']] //[chars_left_in_pct, CSS color to apply to output]
var uncheckedkeycodes=/(8)|(13)|(16)|(17)|(18)/  //keycodes that are not checked, even when limit has been reached.
thresholdcolors.sort(function(a,b){return parseInt(a[0])-parseInt(b[0])}) //sort thresholdcolors by percentage, ascending
var mouseEventStyle = " class=\"btns_mouseout\" onmouseover=\"this.className='btns_mouseover'\" onmouseout=\"this.className='btns_mouseout'\"";
$(document).ready(function(){
	var $targetfields=$("textarea[maxLength]") //get  TEXTAREAs on page with "maxLength" attr defined
	setformfieldsize($targetfields)

	$.each($("input:button"),function(){
		this.className="btns_mouseout";
		if(!($.browser.msie && $.browser.version == "6.0")){
			$(this).mouseover(function(){this.className="btns_mouseover";});
			$(this).mousedown(function(){this.className="btns_mousedown";});
			$(this).mouseout(function(){this.className="btns_mouseout";});
		}
	});
	
	$.each($("input[type=submit]"),function(){
		this.className="btns_mouseout";
		if(!($.browser.msie && $.browser.version == "6.0")){
			$(this).mouseover(function(){this.className="btns_mouseover";});
			$(this).mousedown(function(){this.className="btns_mousedown";});
			$(this).mouseout(function(){this.className="btns_mouseout";});
		}
	});
	
	$.each($("input[type=reset]"),function(){
		this.className="btns_mouseout";
		if(!($.browser.msie && $.browser.version == "6.0")){
			$(this).mouseover(function(){this.className="btns_mouseover";});
			$(this).mousedown(function(){this.className="btns_mousedown";});
			$(this).mouseout(function(){this.className="btns_mouseout";});
		}
	});
	
	  $.fn.numeral = function() {
            $(this).css("ime-mode", "disabled");
            this.bind("keypress",function(event) {
                if (event.keyCode == 46) {
                    if (this.value.indexOf(".") != -1) {
                        return false;
                    }
                } else {
                    return event.keyCode >= 46 && event.keyCode <= 57;
                }
            });
            this.bind("blur", function() {
                if (this.value.lastIndexOf(".") == (this.value.length - 1)) {
                    this.value = this.value.substr(0, this.value.length - 1);
                } else if (isNaN(this.value)) {
                    this.value = "";
                }
            });
            this.bind("paste", function() {
                var s = clipboardData.getData('text');
                if (!/\D/.test(s));
                value = s.replace(/^0*/, '');
                return false;
            });
            this.bind("dragenter", function() {
                return false;
            });
            this.bind("keyup", function() {
            if (/(^0+)/.test(this.value)) {
                this.value = this.value.replace(/^0*/, '');
                } 
            });
        };
        
          $.fn.intInput = function() {
            $(this).css("ime-mode", "disabled");
            this.bind("keypress",function(event) {
                    return event.keyCode >46 && event.keyCode <= 57;
            });
            this.bind("blur", function() {
               if (isNaN(this.value)) {
                    this.value = "";
                }
            });
            this.bind("paste", function() {
                var s = clipboardData.getData('text');
                if (!/\D/.test(s));
                value = s.replace(/^0*/, '');
                return false;
            });
            this.bind("dragenter", function() {
                return false;
            });
            this.bind("keyup", function() {
            if (/(^0+)/.test(this.value)) {
                this.value = this.value.replace(/^0*/, '');
                } 
            });
        };
	
});



function MultiSelector(list_target, max)   
  {     
    // Where to write the list    
    this.list_target = list_target;   
    // How many elements?     
    this.count = 0;    
    // How many elements?     
    this.id = 0;     
    // Is there a maximum?   
    if (max)   
    {   
        this.max = max;  
    }    
    else   
    {     
        this.max = -1;     
    };  
      /**  
      * Add a new file input element  
      */  
      this.addElement = function(element)   
      {   
         // Make sure it's a file input element   
          if (element.tagName == 'INPUT' && element.type == 'file')   
          {   
              // Element name -- what number am I?   
              element.name = 'imageFile';   
              // Add reference to this object   
              element.multi_selector = this;   
              element.style.width = 70;
              // What to do when a file is selected   
              element.onchange = function()   
              {   
                  // New file input   
                  var new_element = document.createElement('input');   
                  new_element.type = 'file';
                  new_element.name = 'imageFile'; 
                  new_element.size = 1;   
                  new_element.className = "addfile";
                  new_element.style.width = 70; 
                  // Add new element   
                  this.parentNode.insertBefore(new_element, this);   
                  // Apply 'update' to element   
                  this.multi_selector.addElement(new_element);   
                  // Update list    
                this.multi_selector.addListRow(this);   
                  // Hide this: we can't use display:none because Safari doesn't like it    
                this.style.position = 'absolute';     
                this.style.left = '-1000px';    
            };   
    
            // If we've reached maximum number, disable input element   
              if (this.max != -1 && this.count >= this.max)   
              {     
                element.disabled = true;  
            };   
              // File element counter     
            this.count++;     
            // Most recent element     
            this.current_element = element;     
        }     
        else     
        {     
            // This can only be applied to file input elements!     
            alert('Error: not a file input element');     
        };     
    };   
  
  	  
  
    /**   
     * Add a new row to the list of files   
     */    
    this.addListRow = function(element)     
    {     
        // Row div   
        var new_row = document.createElement('p');   
          // Delete button    
        var new_row_button = document.createElement('a');   
        new_row_button.style.textDecoration = 'none';
        new_row_button.href = '#';          
        new_row_button.innerHTML = '<font color="red">X</font>&nbsp;&nbsp;';   
          // References     
        new_row.element = element;   
        // Delete function    
        new_row_button.onclick = function()   
        {     
            // Remove element from form   
            this.parentNode.element.parentNode.removeChild(this.parentNode.element);   
              // Remove this row from the list     
            this.parentNode.parentNode.removeChild(this.parentNode);   
             // Decrement counter     
            this.parentNode.element.multi_selector.count--;   
              // Re-enable input element (if it's disabled)     
            this.parentNode.element.multi_selector.current_element.disabled = false;   
              // Appease Safari     
            // without it Safari wants to reload the browser window  
            // which nixes your already queued uploads    
            return false;    
        };     
        // Set row value   
        
        var fname = element.value;
        fname = fname.substring(fname.lastIndexOf("\\") + 1,fname.length);  
        new_row.innerHTML = fname + " ";   
        // Add button    
        new_row.appendChild(new_row_button);     
        // Add it to the list     
        this.list_target.appendChild(new_row);     
    };     
};

function MultiSelectorVideo(list_target, max)   
  {     
    // Where to write the list    
    this.list_target = list_target;   
    // How many elements?     
    this.count = 0;    
    // How many elements?     
    this.id = 0;     
    // Is there a maximum?   
    if (max)   
    {   
        this.max = max;  
    }    
    else   
    {     
        this.max = -1;     
    };  
      /**  
      * Add a new file input element  
      */  
      this.addElement = function(element)   
      {   
         // Make sure it's a file input element   
          if (element.tagName == 'INPUT' && element.type == 'file')   
          {   
              // Element name -- what number am I?   
              element.name = 'videoFile';   
              // Add reference to this object   
              element.multi_selector = this;   
              element.style.width = 70;
              // What to do when a file is selected   
              element.onchange = function()   
              {   
                  // New file input   
                  var new_element = document.createElement('input');   
                  new_element.type = 'file';
                  new_element.name = 'videoFile'; 
                  new_element.size = 1;   
                  new_element.className = "addfile";
                  new_element.style.width = 70; 
                  // Add new element   
                  this.parentNode.insertBefore(new_element, this);   
                  // Apply 'update' to element   
                  this.multi_selector.addElement(new_element);   
                  // Update list    
                this.multi_selector.addListRow(this);   
                  // Hide this: we can't use display:none because Safari doesn't like it    
                this.style.position = 'absolute';     
                this.style.left = '-1000px';    
            };   
    
            // If we've reached maximum number, disable input element   
              if (this.max != -1 && this.count >= this.max)   
              {     
                element.disabled = true;  
            };   
              // File element counter     
            this.count++;     
            // Most recent element     
            this.current_element = element;     
        }     
        else     
        {     
            // This can only be applied to file input elements!     
            alert('Error: not a file input element');     
        };     
    };   
  
  	  
  
    /**   
     * Add a new row to the list of files   
     */    
    this.addListRow = function(element)     
    {     
        // Row div   
        var new_row = document.createElement('p');   
          // Delete button    
        var new_row_button = document.createElement('a'); 
        new_row_button.id="close"+this.count;
        new_row_button.style.textDecoration = 'none';
        new_row_button.href = '#';          
        new_row_button.innerHTML = '<font color="red">X</font>&nbsp;&nbsp;';   
          // References     
        new_row.element = element;   
        // Delete function    
        new_row_button.onclick = function()   
        {     
            // Remove element from form   
            this.parentNode.element.parentNode.removeChild(this.parentNode.element);   
              // Remove this row from the list     
            this.parentNode.parentNode.removeChild(this.parentNode);   
             // Decrement counter     
            this.parentNode.element.multi_selector.count--;   
              // Re-enable input element (if it's disabled)     
            this.parentNode.element.multi_selector.current_element.disabled = false;   
              // Appease Safari     
            // without it Safari wants to reload the browser window  
            // which nixes your already queued uploads    
            return false;    
        };     
        // Set row value   
        
        var fname = element.value;
        fname = fname.substring(fname.lastIndexOf("\\") + 1,fname.length);  
        new_row.innerHTML = fname + " ";   
        // Add button    
        new_row.appendChild(new_row_button);     
        // Add it to the list     
        this.list_target.appendChild(new_row);     
    };     
};

/****************************************/
function FileJs(list_target,max,fileName,fileType)   
{     
  // Where to write the list    
  this.list_target = list_target;   
  // How many elements?     
  this.count = 0;    
  // How many elements?     
  this.id = 0;     
  // Is there a maximum?   
  if (max)   
  {   
      this.max = max;  
  }    
  else   
  {     
      this.max = -1;     
  };  
    /**  
    * Add a new file input element  
    */  
    this.addElement = function(element)   
    {   
       // Make sure it's a file input element   
        if (element.tagName == 'INPUT' && element.type == 'file')   
        {   
            // Element name -- what number am I?   
            element.name = fileName;   
            // Add reference to this object   
            element.multi_selector = this;   
            element.style.width = 70;
            // What to do when a file is selected   
            element.onchange = function()   
            {   
                // New file input   
                var new_element = document.createElement('input');   
                new_element.type = 'file';
                new_element.name = fileName; 
                new_element.size = 1;   
                new_element.className = "addfile";
                new_element.style.width = 70; 
                // Add new element   
                this.parentNode.insertBefore(new_element, this);   
                // Apply 'update' to element   
                this.multi_selector.addElement(new_element);   
                // Update list    
              this.multi_selector.addListRow(this);   
                // Hide this: we can't use display:none because Safari doesn't like it    
              this.style.position = 'absolute';     
              this.style.left = '-1000px';    
              checkFile(0,this,fileType);
          };   
  
          // If we've reached maximum number, disable input element   
            if (this.max != -1 && this.count >= this.max)   
            {     
              element.disabled = true;  
          };   
            // File element counter     
          this.count++;     
          // Most recent element     
          this.current_element = element;     
      }     
      else     
      {     
          // This can only be applied to file input elements!     
          alert('Error: not a file input element');     
      };     
  };   

	  

  /**   
   * Add a new row to the list of files   
   */    
  this.addListRow = function(element)     
  {     
      // Row div   
      var new_row = document.createElement('p');   
        // Delete button    
      var new_row_button = document.createElement('a'); 
      new_row_button.id="close"+this.count;
      new_row_button.style.textDecoration = 'none';
      new_row_button.href = '#';          
      new_row_button.innerHTML = '<font color="red">X</font>&nbsp;&nbsp;';   
        // References     
      new_row.element = element;   
      // Delete function    
      new_row_button.onclick = function()   
      {     
          // Remove element from form   
          this.parentNode.element.parentNode.removeChild(this.parentNode.element);   
            // Remove this row from the list     
          this.parentNode.parentNode.removeChild(this.parentNode);   
           // Decrement counter     
          this.parentNode.element.multi_selector.count--;   
            // Re-enable input element (if it's disabled)     
          this.parentNode.element.multi_selector.current_element.disabled = false;   
            // Appease Safari     
          // without it Safari wants to reload the browser window  
          // which nixes your already queued uploads    
          return false;    
      };     
      // Set row value   
      
      var fname = element.value;
      fname = fname.substring(fname.lastIndexOf("\\") + 1,fname.length);  
      new_row.innerHTML = fname + " ";   
      // Add button    
      new_row.appendChild(new_row_button);     
      // Add it to the list     
      this.list_target.appendChild(new_row);     
  };     
};
/***************************************/
 


function setformfieldsize($fields, optsize, optoutputdiv){
	var $=jQuery
	$fields.each(function(i){
		var $field=$(this)
		$field.data('maxsize', optsize || parseInt($field.attr('maxLength'))) //max character limit
		var statusdivid=optoutputdiv || $field.attr('data-output') //id of DIV to output status
		$field.data('$statusdiv', $('#'+statusdivid).length==1? $('#'+statusdivid) : null)
		$field.unbind('keypress.restrict').bind('keypress.restrict', function(e){
			setformfieldsize.restrict($field, e)
		})
		$field.unbind('keyup.show').bind('keyup.show', function(e){
			setformfieldsize.showlimit($field)
		})
		setformfieldsize.showlimit($field) //show status to start
	})
}

setformfieldsize.restrict=function($field, e){
	var keyunicode=e.charCode || e.keyCode
	if (!uncheckedkeycodes.test(keyunicode)){
		if (getStringLength($field.val()) >= $field.data('maxsize')){ //if characters entered exceed allowed
			if (e.preventDefault)
				e.preventDefault()
			return false
		}
	}
}

setformfieldsize.showlimit=function($field){
	if (getStringLength($field.val()) > $field.data('maxsize')){
		var trimmedtext=getSubString($field.val(), $field.data('maxsize'))
		$field.val(trimmedtext)
	}
	if ($field.data('$statusdiv')){
		$field.data('$statusdiv').css('color', '').html($field.val().length)
		var pctremaining=($field.data('maxsize')-getStringLength($field.val()))/$field.data('maxsize')*100 //calculate chars remaining in terms of percentage
		for (var i=0; i<thresholdcolors.length; i++){
			if (pctremaining<=parseInt(thresholdcolors[i][0])){
				$field.data('$statusdiv').css('color', thresholdcolors[i][1])
				break
			}
		}
	}
}

function getStringLength(str){
	var strlength=0;
   	for (i=0;i<str.length;i++)
  	{
    	if (isChinese(str.charAt(i))==true)
        	strlength=strlength + 2;
     	else
        	strlength=strlength + 1;
  	}
	return strlength;
}

function isChinese(str)
{
   var lst = /[u00-uFF]/;       
   return !lst.test(str);      
}

function getSubString(str,len)
{
	var strlen = 0; 
  	var s = "";
  	for(var i = 0;i < str.length;i++)
  	{
   		if(isChinese(str.charAt(i))==true){
    		strlen += 2;
   		}else{ 
    		strlen+=1;
   		}
   		s += str.charAt(i);
   		if(strlen >= len){ 
    		return s ;
   		}
  	}
 	return s;
 } 
 
 function setInputDisable(className){
	 	 if(!className){
	 		 className="disable";
	 	 }
		 $.each($("."+className),function(){
					$(this).attr("disabled","disabled");
					$(this).css("background","#d7d7d7");
					$(this).val("");
				}
		);
 }
function setInputEnable(className){
	 	 if(!className){
	 		 className="disable";
	 	 }
		 $.each($("."+className),function(){
					$(this).removeAttr("disabled");
					$(this).css("background","#ffffff");
				}
		);
 }
 
function setSelectOption(selId, value){
	var opts = document.getElementById(selId);
	if(value){
		for(var i=0;i<opts.options.length;i++){
			if(value==opts.options[i].value){
				opts.options[i].selected = 'selected';
				//alert(opts.options[i].value);
				break;
			}
		}
	}
}

//从array中移除value
function removeFromArray(array, value) {
    var arr = null;
    if (array) {
        arr = $.grep(array, function (n, i) {
            return n != value;
        });
    }
    return arr;
}

function goBack(){
	history.go(-1);
}