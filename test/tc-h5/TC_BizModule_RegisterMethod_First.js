require("./TC_BizModule_RegisterMethod_Third.js");

registerMethods({$externFuncWithParam: function(msg) {
  var message = msg;
  var okTitle = "OK";
  var params = {
    'message':message,
    'okTitle':okTitle,
  }
  var self = this;
  this.$call('modal','alert',params, function() {
  });
}});

registerMethods({$externFuncWithParamFirst: function(msg) {
  return msg+"| ";
}});

registerMethods({$externFuncWithCallback: function(msg, cb) {
  cb("callBack:"+msg);
}});

registerMethods({$externFuncWithMainFunc: function(msg) {
  this.mainMethod(msg)
}});

registerMethods({$externFuncRequireAnother: function(msg) {
  this.$externFuncWithThird(msg+":First;");
}});

registerMethods({$externFuncWithSameMethod: function(msg) {
  var message = msg+"：调用第一个文件";
  var okTitle = "OK";
  var params = {
    'message':message,
    'okTitle':okTitle,
  }
  var self = this;
  this.$call('modal','alert',params, function() {
  });
}});



