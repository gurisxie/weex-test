registerMethods({$externFuncWithThird: function(msg) {
  var message = msg+"Third.";
  var okTitle = "OK";
  var params = {
    'message':message,
    'okTitle':okTitle,
  }
  var self = this;
  this.$call('modal','alert',params, function() {
  });
}});
