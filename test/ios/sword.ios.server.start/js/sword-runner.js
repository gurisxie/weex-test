/**
* sword 远程启动的主入口
**/

#import "./sword-lib.js"
#import "./sword-handler.js"

(function($){
	/* http模拟runner */
	$.runner = {"tester":0};
 	$.runner.options = {
 		"getRunIdUrl": "http://127.0.0.1:53129/getRunId",
		"taskUrl": "http://127.0.0.1:53129/task",
		"resultUrl": "http://127.0.0.1:53129/result",
		"executeTimeout": 10 //任务执行的超时时间
	};
	$.extend($.runner,{
		getRunId: function(){ //获取本次运行的RunId
			var runId = 0;
			var options = $.runner.options;
			$.http.get(options.getRunIdUrl,options.executeTimeout,function(result){
				if(!result.exitCode == 0){
					$.error("获取运行RunId失败，失败原因:" + result.stderr);
				}else{
					runId = parseInt(result.stdout);
				}
			});
			return runId;
		},
		getTask:function(runId){ //获取当前运行的任务
			var options = $.runner.options;
			var taskInfo = null;
			$.http.post(options.taskUrl,{"runId":runId},options.executeTimeout,function(result){
				if(!result.exitCode == 0){
					$.error("获取服务器任务失败，失败原因为:" + result.stderr);
				}else{
					if(!$.isBlank(result.stdout)){ //如果返回结果非空
						taskInfo = JSON.parse(result.stdout);
					}else{
						//$.http.postInfo("no task info ");
						taskInfo = {};
					}
				}

			});
			return taskInfo;
		},
		executeTask:function(runId,taskInfo){ // 处理当前的任务信息
			if(taskInfo=={}){
				return true;
			}
			//return false;
 			return $.dispatch(runId,taskInfo);
		},
		start: function(){ //启动Runner
			var runId = this.getRunId();
			$.message("Current runId is " + runId);
			if(runId > 0){
				var running = true;
				while(running){
					var taskInfo = this.getTask(runId);
					if(null == taskInfo){
						running = false;
						break;
					}else{
						var continueFlag = this.executeTask(runId,taskInfo);
						if(!continueFlag){
							running = false;
							break;
						}else{
							$.target().delay(0.1);
						}
					}
				}
			}
		}
	});
	$.runner.start();
	//$.http.postInfo("I am finished ~");
})(Sword)
