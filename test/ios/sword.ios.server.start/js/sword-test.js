#import "./sword-lib.js"
#import "./sword-handler.js"

alert("hello");

var taskInfo = {};
taskInfo['actionId'] = 1;
taskInfo['context'] = "application";
taskInfo['actionName'] = "windows";
taskInfo['params'] = {};

var result = $.dispatch(1, taskInfo);
$.log(JSON.stringify(result));
alert(JSON.stringify(result));

/*
$.http.get("http://127.0.0.1:34123",4,function(result){

	$.log(JSON.stringify(result));
		   });
$.http.get("http://m.baidu.com/",10,function(result){

	$.log(JSON.stringify(result));
		   });
*/



//$.app().logElementTree();


/*

var button = $.target().app().mainWindow().element('button[name=.*淘宝]');

$.log(button.inspect());
$.options.showLog = false;
$.tarBar("[visible=1]").button("[name=.*淘宝]").tap();
	  
*/

/*

var attrstr = "[name=我的淘宝]";
var locate = $.toLocate(attrstr);
$.log(JSON.stringify(locate));
 */





