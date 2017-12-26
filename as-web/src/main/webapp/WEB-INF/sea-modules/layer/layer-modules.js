define(function(require, exports, module) {
	require("layer-css");
	require("jquery");
  	var layer = require("layer");
  	var util = {};
 	util.myalert = function(content) {
  	  	layer.alert(content);
  	};

  // 暴露对应接口
  module.exports = util;
});