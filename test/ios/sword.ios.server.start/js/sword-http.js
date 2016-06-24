/**
 * sword的http工具类
 **/
(function($){
	$.http = {};
	var CRUL_PATH = "/usr/bin/curl";
	$.serializeParams = function(params) {
		var len = params.Count;
		var index = 0;
		var sendString = "";
		for ( var name in params) {
			sendString = sendString + encodeURIComponent(name) + "="
					+ encodeURIComponent(params[name]);
			if (index < len - 1) {
				sendString = sendString + "&";
			}
			index = index + 1;

		}
		return sendString;
	}
	/*发送get请求*/
	$.http.get = function(url,timeout,callback){
		var result = target.host().performTaskWithPathArgumentsTimeout(CRUL_PATH, [url], timeout);
		if($.isFunction(callback)){
			callback(result);
		}
		return result;
	};

	/*发送post请求*/
	$.http.post = function(url,params,timeout,callback){
 		var sendParams =[];
 		for(var key in params){
 			sendParams.push("-d");
 			sendParams.push(encodeURIComponent(key) + "=" + encodeURIComponent(params[key]) );
 		}
 		sendParams.push(url);
		var result = target.host().performTaskWithPathArgumentsTimeout(CRUL_PATH, sendParams, timeout);
 
		if($.isFunction(callback)){
			callback(result);
		}
		return result;
	};
	
	/*发送信息,主要用于统一在服务端调试*/
	$.http.postInfo = function(message){
		var result = {};
		result.info = message;
		return $.http.post("http://127.0.0.1:53129/logs", result, 20);
	}

})(Sword);

