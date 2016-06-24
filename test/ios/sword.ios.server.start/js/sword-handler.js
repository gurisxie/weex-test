/**
* 远程命令的处理
**/
#import "./sword-monkey.js"
(function($){
	$ = $||{};
	
	$.handler = {};
 	
 	function createResultJSON(actionId,success,message,context){
 		var result = {};
 		result.actionId = actionId;
 		result.success = success;
 		result.message = message;
 		result.context = context;
 		return result;
 	}
	//target方面的handler
	$.handler.target = function(taskInfo){
		var result = null;
		var params = taskInfo.params;
		var target = $.target();
		result = target[taskInfo.actionName].apply(target,params);
		return result;
	};
	
	//application方面的handler
	$.handler.application = function(taskInfo){
		var app = $.application();
		var params = taskInfo.params;
		var actionName = taskInfo.actionName;
		
		$.debug(JSON.stringify(taskInfo));
		$.debug(JSON.stringify(params));

		if(actionName=="elements"){
			var className = params.className;
			var locator = params.locator;
			return $.handler._elements($,className,locator);
		}
		
		//TODO: params is json data, not array, apply method won't work well!!
		var result = app[actionName].apply(app,params);
		return result;
	};

	//element方面的handler
	$.handler.element = function(taskInfo){
		var params = taskInfo.params;
		var actionName = taskInfo.actionName;
		
		var elementClassName = params.elementClassName;
		var elementLocator = params.elementLocator;
		var shortName = $.classNameToTypes[elementClassName].shortName; //找到对应的element(UIA系列)名称所对应的short方法名，用于下一步定位元素
		var element = $[shortName](elementLocator); //定位element元素
		if(actionName=="elements"){
			var className = params.className;
			var locator = params.locator;
			return $.handler._elements(element,className,locator);
		}

		var methodParams = params.params;
		var result = element[actionName].apply(element,methodParams);
		return result;
	};
	
	//setting方面的handler
	$.handler.setting = function(taskInfo){
		//$.message("setting info " + JSON.stringify(taskInfo));
		if(taskInfo.actionName = "setTimeout"){
			$.options.timeout = taskInfo.params;
		}
		if(taskInfo.actionName = "setThrow"){
			$.options.throwIfNotFound = taskInfo.params;
		}
	};

	$.handler._elements = function(element,className,locator){
		var multiShortName = $.classNameToTypes[className].multiShortName;
		var elements = element[multiShortName](locator);
		result = [];
		$.each(elements,function(i,element){
			var defaultLocator = element.getDefaultLocator();
			result.push(defaultLocator);
		})
		return result;
	}
	
	$.dispatch = function(runId,taskInfo){
		var resultUrl = $.runner.options.resultUrl;
		var result = {};
		result.runId = runId;
		result.actionId = taskInfo.actionId;

		try{
			var rtn = null;
			switch(taskInfo.context){
			  case "target":
				    //收到退出消息
				  	if(taskInfo.actionName=="quit"){
				  		result.success = true;
						result.context = null;
						$.http.post(resultUrl,result,20);
						//$.setDeviceOrientation(UIA_DEVICE_ORIENTATION_PORTRAIT);
						return false;
					}
					rtn =$.handler.target(taskInfo);
				  	break;
			  case "application":
				rtn =$.handler.application(taskInfo);
			  	break;
			  case "element":
				rtn = $.handler.element(taskInfo);
			  	break;
			  case "topelement":
					rtn = $.handler.topelement(taskInfo);
				  	break;
			  case "setting":
				rtn = $.handler.setting(taskInfo);
			  	break;
			  case "monkey":
					UIAutoMonkey.RELEASE_THE_MONKEY();
					result.success = true;
					result.context = null;
					$.http.post(resultUrl,result,20);
					return false;
			  default:
			  	break;
			}
			result.success = true;
			result.context = JSON.stringify(rtn);

			//$.message("-->" + result.context);
		}catch(e){
			result.success = false;
			result.message = e.message;
			$.error(e.message);
		}
		var result = $.http.post(resultUrl,result,20);
		if(!result.exitCode == 0){
			return false;
		}
		return true;
	}
})(Sword);