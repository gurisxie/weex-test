registerMethods({$externFuncWithParamSec: function(msg) {
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
registerMethods({$externFuncWithSameMethod: function(msg) {
  var message = msg+"：调用第二个文件";
  var okTitle = "OK";
  var params = {
    'message':message,
    'okTitle':okTitle,
  }
  var self = this;
  this.$call('modal','alert',params, function() {
  });
}});