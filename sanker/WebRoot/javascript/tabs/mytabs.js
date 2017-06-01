
ETabs = {
 	lis:[],
 	tabid:"",
 	tabs:function(tabId){
 		var tab = $("#"+tabId);
 		ETabs.tabid = tabId;
 		ETabs.lis = tab.children("ul:first").children();
 		tab.children("ul:first").addClass("ui-tabs-nav")
 			.children("li:first").addClass("ui-tabs-selected");
 		tab.children("ul:first")
 			.children("li").children("a").each(
 				function(){
 					var href = $(this).attr("href");
 					var eTabs = "";
 					if(!$(this).attr("eTabs")){
 						eTabs = href;
 					}else{
 						eTabs = $(this).attr("eTabs");
 					}
 					$(this).attr("eTabs", eTabs);
 					$(this).attr("href", "javascript:;");
 					$(this).bind("click", function(){ETabs.toggle(eTabs, eTabs.substring(eTabs.length - 1))});
 				}
 			);
 		return ETabs;
 	},
 	
 	toggle:function(eTabs, index){
 		//alert(eTabs);
 		//alert(index);
 		_a = $("a[eTabs="+eTabs+"]");
 		var litemp = _a.parent().parent().children()
 		if(index > 0 && index <= ETabs.lis.length){
 			var i = 1;
 			litemp.each(
	 			function(){
	 				$(this).removeClass("ui-tabs-selected");
	 				if(i == index){
	 					$(this).addClass("ui-tabs-selected");
	 				}
	 				i++;
	 			}
	 		)
 		}else{
 			_a.parent().addClass("ui-tabs-selected");
 		}
 		$("#"+ETabs.tabid).children("div").each(
 			function(){
 				$(this).removeClass("ui-tabs-panel");
 				$(this).removeClass("ui-tabs-hide");
 				$(this).addClass("ui-tabs-panel");
 				$(this).addClass("ui-tabs-hide");
 			});
 		$("#"+eTabs.substring(1)).removeClass("ui-tabs-hide");
 		return ETabs;
 	}
 }