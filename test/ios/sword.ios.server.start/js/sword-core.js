/**
* SWword的工具方法类
**/
var Sword =  {},$ = Sword;

//设置运行的默认参数，取消UIAAutomation框架默认的时间等待机制
var target = UIATarget.localTarget();
//target.setTimeout(0);

//设置onAlert时间为true，不要点击默认按钮
UIATarget.onAlert = function onAlert(alert) {
    return true;
}

/**
* SwordElement 对象是对UIAElement对象的一层封装
* @param element 传入的UIAElement对象
* @param parent 父级的SwordElement对象
* @param locator 前台传入的locator属性
* @param signId 当前元素随机分配的ID
* @return 返回一个SwordElement对象
*/
function SwordElement(element,parent,locator,signId,superElement,treePath,elementTree){
	this._element = element;
	this.parentElement = parent;
	this.locator = locator;
	this.signId = signId;
	if(parent){
		this.parentSignId = parent.signId;
	}else{
		this.parentSignId = 0;
	}
	this.superElement = superElement;
	this.treePath = treePath;
	this.elementTree = elementTree;
}

/**
* 对于UIAApplication类的封装类
**/
function SwordApplication(application){
	this._application = application;
}

/**
* 对于UIATarget类的封装类
**/
function SwordTarget(target){
	this._target = target;
}

(function($){
	/* sword对象的默认配置项*/
	$.options = {
		timeout:3000, // 页面元素查找定位的最长超时时间
		showLog:true, //是否打印日志
		throwIfNotFound:true, //查找失败时是否抛出异常
 		runInRemote:true //是否通过remote的方式进行运行,目前发现在instrument中运行，直接throw会crash，改用log.error
	};

	// 扩展类
	$.extend = function(destination, source){
		for (var property in source) {
		  destination[property] = source[property];
		}
		return destination;
	};

	$.merge = function(d,o){
	  for(var name in d){
	    if(typeof o[name] === 'undefined'){
	      o[name] = d[name];
	    }
	  }
	  return o;
	}
	
	//对于sword的常用工具类
	$.extend(Sword,{
		retry:function(){
			var f = arguments[0];
			var maxTries = 3;
			var delay = 0.5;
			if (arguments.length > 1) {
				maxTries = arguments[1];
			}
			if (arguments.length > 2) {
				delay = arguments[2];
			}

			var tries = 0;
			var exception = null;
			while (tries < maxTries) {
			try {
			  f();
			  return;  // if we get here, our function must have passed (no exceptions)
			}
			catch(e) {
			  exception = e;
			  tries++;
			  UIATarget.localTarget().delay(delay);
			}
			}
			throw exception;
		},
		// 判断传入的参数是否是函数
	   isFunction: function(value){
	       return ({}).toString.call(value) == "[object Function]";
	   },
	   // 判断传入的对象是否是Object
	   isObject: function(value){
	   	   return value instanceof Object;
	   },
	   //判断传入的对象是否是Array
	   isArray: function(value){
	   	   return value instanceof Array;
	   },
	   // 判断传入的参数是否是一个类似colleciton的对象
	   isCollection: function(value){
			return typeof value.length == 'number';
	   },
	    // 去除数组中的非空元素
	   compact: function(array) {
			return array.filter(function(item) {
				return item !== undefined && item !== null;
			});
		},
	    flatten: function(array) {
			return array.length > 0 ? [].concat.apply([], array) : array;
		},
		uniq: function(array) {
			return array.filter(function(item, index, array) {
				return array.indexOf(item) == index;
			});
		},
		//判断输入的对象是否为blank
		isBlank: function(obj){
			if(!obj){
				return true;
			}
		   if(typeof obj  == "string"){
			    return ( 0 === obj.length || /^\s*$/.test(obj));
		   }
		   return false;
		},
		//遍历元素
		each: function(elements, callback) {
			var i, key;
			if ($.isCollection(elements)) {
				for (i = 0; i < elements.length; i++) {
					if (callback.call(elements[i], i, elements[i]) === false)
						return elements;
				}
			} else { 
				for (key in elements) {
					if (callback.call(elements[key], key, elements[key]) === false)
						return elements;
				}
			}
			return elements;
		},
		/* 判断传入的对象是否是nil对象 */
		isNil :function(element){
			if(!element) return true;
			return (element.toString() == "[object UIAElementNil]");
		},
		/* 得到指定object的类名 */
		getClassName: function(obj){
			var thisType = this.toString().split(" ")[1];
	        thisType = thisType.substr(0, thisType.length - 1);
	        return thisType; 
		}

	});

	/*对log系统的扩展 */
	$.extend(Sword,{
		log: function(s, level) {
	        level = level || 'message';
	        if (level === 'error') $.error(s);
	        else if (level === 'warn') $.warn(s);
	        else if (level === 'debug') $.debug(s);
	        else $.message(s);
	    },
	    error: function(s) {UIALogger.logError(s); },
	    warn: function(s) { UIALogger.logWarning(s); },
	    debug: function(s) { UIALogger.logDebug(s); },
	    message: function(s) { UIALogger.logMessage(s); },
	    info:function(s){$.message(s);},
		throwException:function(s){
			if($.options.runInRemote){
			 	throw new Error(s);
			 }else{
			 	$.error(s);
			 }
		},
	    target: function(){
	    	if(this._swordtarget == undefined){
	    		var _target =  UIATarget.localTarget();
	    		this._swordtarget = new SwordTarget(_target);
	    	}
	    	return this._swordtarget;
	    },
	    application: function(){
	    	if(this._swordapplication == undefined){
	    		var _app = UIATarget.localTarget().frontMostApp();
	    		this._swordapplication = new SwordApplication(_app);
	    	}
	    	return this._swordapplication;
	    },
	    app: function(){ //application的别名
	    	return this.application();
	    }
	});


	/** 对于javascript原生对象的扩展 */
	$.extend(String.prototype,{
		trim:function() {
		  return this.replace(/^\s+|\s+$/g,"");
		},
		ltrim:function() {
		  return this.replace(/^\s+/,"");
		},
		rtrim:function() {
		  return this.replace(/\s+$/,"");
		}
	});



	/* 对UIAAutomation类库的工具类 */
	$.extend(SwordElement.prototype, {
		  name: function(){
		  	return this._element.name();
		  },
		  label: function(){
		  	return this._element.label();
		  },
		  value: function(){
		  	return this._element.value();
		  },
		  isVisible:function(){
		  	return this._element.isVisible();
		  },
		  rect:function(){
		  	return this._element.rect();
		  },
		  hitpoint: function(){
		  	return this._element.hitpoint();
		  }	,
		  checkIsValid:function(){
		  	return this._element.checkIsValid();
		  },
		  hasKeyboardFocus:function(){
		  	return this._element.hasKeyboardFocus();
		  },
		  isEnabled :function(){
		  	return this._element.isEnabled();
		  },
		  isValid :function(){
		  	return this._element.isVali();
		  },
		  waitForInvalid :function(){
		  	return this._element.waitForInvalid();
		  },
		  getType: function(){
		  	var thisType = this._element.toString().split(" ")[1];
	        thisType = thisType.substr(0, thisType.length - 1);
	        return thisType;
		  },
		  toString:function(){
		 	return '[object SwordElement ' + this.getType() + ']'; 
		  },
		  /**
		  * 返回元素的常用属性  
		  */
		  properties: function(){
		  	var properties = {};
		  	properties.className = this.getType();
		  	properties.rect = this.rect();
			var name = this.name();
			var label = this.label();
			var value = this.value();
			var visible = this.isVisible();
			var signId = this.signId;
			var parentSignId = this.parentSignId;
			properties.visible = visible;
			if(!$.isNil(name)){ properties.name = name;};
			if(!$.isNil(label)){ properties.label = label;};
			if(!$.isNil(value)){ properties.value = value;};
			if(!$.isNil(signId)){ properties.signId = signId;};
			if(!$.isNil(parentSignId)){ properties.parentSignId = parentSignId;};
			return properties;
		   },
		  /*返回当前元素的可读字符串*/	
		  inspect:function(){
		  	return JSON.stringify(this.properties());
		  }

	});

	/**对于UIAApplication类的扩展 */
	$.extend(SwordApplication.prototype, {
		  actionSheet:function(){
		  	var actionSheet = this._application.actionSheet();
		  	return new SwordElement(actionSheet,null,null,1,null,null,1,null,"0|",null);
		  },
		  alert:function(){
		  	var _alert = this._application.alert();
		  	return new SwordElement(_alert,null,null,1,null,null,1,null,"0|",null);
		  },
		  bundleID:function(){
		  	return this._application.bundleID();
		  },
		  editingMenu:function(){
		  	var el = this._application.editingMenu();
		  	return new SwordElement(el,null,null,1,null,null,1,null,"0|",null);
		  },
		  interfaceOrientation:function(){
		  	return this._application.interfaceOrientation();
		  },
		  keyboard: function(){
		  	var el = this._application.keyboard();
		  	return new SwordElement(el,null,null,1,null,null,1,null,"0|",null); 
		  },
		  mainWindow: function(){
			var el = this._application.mainWindow();
		  	return new SwordElement(el,null,null,1,null,null,1,null,"0|",null); 
		  },
		  navigationBar :function(){
		  	var el = this._application.navigationBar();
		  	return new SwordElement(el,null,null,1,null,null,1,null,"0|",null); 
		  },
		  preferencesValueForKey:function(key){
		  	return this._application.preferencesValueForKey(key);
		  },
		  setPreferencesValueForKey: function(value,key){
		  	this._application.setPreferencesValueForKey(value,key);
		  },
		  statusBar:function(){
		  	var el = this._application.statusBar();
		  	return new SwordElement(el,null,null,1,null,null,1,null,"0|",null); 
		  },
		  tarBar:function(){
		  	var el = this._application.tarBar();
		  	return new SwordElement(el,null,null,1,null,null,1,null,"0|",null); 
		  },
		  toolBar:function(){
		  	var el = this._application.toolBar();
		  	return new SwordElement(el,null,null,1,null,null,1,null,"0|",null); 
		  },
		  version: function(){
		  	return this._application.version();
		  },
		  windows: function(){
		  	var _windows = this._application.windows();
		  	var results = [];
		  	$.each(_windows,function(i,win){
		  		var e =new SwordElement(win,null,null,i,null,null,1,null,"0|",null);
		  		results.push(e);
		  	});
		  	return results;
		  },
		  /*
		  window: function(idx){
		  	var offset = 0;
		  	if ( typeof(idx) == "number" ){
		  		offset = idx;
		  	}
		  	var _windows = this._application.windows();
		  	offset = offset >= 0 && offset < _windows.length ? offset : 0;
		  	var e = new SwordElement(_windows[offset],null,null,1,null,null,1,null,"0|",null);
		  	return e;	
		  },
		  */
		  /**
		   * A shortcut for getting the current view controller's title from the
		   * navigation bar. If there is no navigation bar, this method returns null
		   */
		  navigationTitle: function() {
		    navBar = this._application.mainWindow().navigationBar();
		    if (navBar) {
		      return navBar.name();
		    }
		    return null;
		  },

		  /**
		   * A shortcut for checking that the interface orientation in either
		   * portrait mode
		   */
		  isPortraitOrientation: function() {
		    var orientation = this._application.interfaceOrientation();
		    return orientation == UIA_DEVICE_ORIENTATION_PORTRAIT ||
		      orientation == UIA_DEVICE_ORIENTATION_PORTRAIT_UPSIDEDOWN;
		  },

		  /**
		   * A shortcut for checking that the interface orientation in one of the
		   * landscape orientations.
		   */
		  isLandscapeOrientation: function() {
		    var orientation = this._application.interfaceOrientation();
		    return orientation == UIA_DEVICE_ORIENTATION_LANDSCAPELEFT ||
		      orientation == UIA_DEVICE_ORIENTATION_LANDSCAPERIGHT;
		  },
		  input:function(arg1){
		  	var stringValue = arg1;
			return this._application.keyboard().typeString(stringValue);
		  }
	});
	
	/** 对于SwordTarget对象的的常用扩展类 */
	$.extend(SwordTarget.prototype, {
		  frontMostApp:function(){
		  	return $.application();
		  },
		  application:function(){
			 return this.frontMostApp();
		  },
		  app:function(){
			 return this.frontMostApp();
		  },
		  host: function(){
		  	return this._target.host();
		  },
		  deactivateAppForDuration:function(duration){
		  	return this._target.deactivateAppForDuration(duration);
		  },
		  model: function(){
		  	return this._target.model();
		  },
		  name: function(){
			return this._target.name();
		  },
		  rect: function(){
		  	return this._target.rect();
		  },
		  systemName: function(){
		  	return this._target.systemName();
		  },
		  systemVersion: function(){
			return this._target.systemVersion();
		  },
		  deviceOrientation: function(){
		  	return this._target.deviceOrientation();
		  },
		  setDeviceOrientation:function(deviceOrientation){
			this._target.setDeviceOrientation(deviceOrientation);
		  },
		  setLocation:function(coordinates){
		  	return this._target.setLocation(coordinates);
		  },
		  setLocationWithOptions:function(coordinates, options){
			return this._target.setLocationWithOptions(coordinates,options);
		  },
		  clickVolumeDown:function(){
		  	this._target.clickVolumeDown();
		  },
		  clickVolumeUp: function(){
		  	this._target.clickVolumeUp();
		  },
		  holdVolumeDown:function(duration){
			this._target.holdVolumeDown(duration);
		  },
		  holdVolumeUp:function(duration){
			this._target.holdVolumeUp(duration);
		  },
		  lockForDuration:function(duration){
			this._target.lockForDuration(duration);
		  },
		  shake:function(){
		  	this._target.shake();
		  },
		  dragFromToForDuration:function(fromPointObject, toPointObject, duration){
		  	this._target.dragFromToForDuration(fromPointObject, toPointObject, duration);
		  },
		  doubleTap:function(tapPointObject){
		  	if(tapPointObject instanceof SwordElement){
		  		tapPointObject = tapPointObject._element;
		  	}
		  	this._target.doubleTap(tapPointObject);
		  },
		  flickFromTo: function(fromPointObject, toPointObject){
		  	this._target.flickFromTo(fromPointObject,toPointObject);
		  },
		  pinchCloseFromToForDuration:function(fromPointObject, toPointObject,duration){
			this._target.pinchCloseFromToForDuration(fromPointObject, toPointObject,duration);
		  },
		  pinchOpenFromToForDuration:function(fromPointObject, toPointObject,duration){
			this._target.pinchOpenFromToForDuration(fromPointObject, toPointObject,duration);
		  },
		  rotateWithOptions:function(location,options){
		  	this._target.rotateWithOptions(location,options);
		  },
		  tap:function(tapPointObject){
			if(tapPointObject instanceof SwordElement){
		  		tapPointObject = tapPointObject._element;
		  	}
		  	this._target.tap(tapPointObject);
		  },
		  tapWithOptions:function(tapPointObject,options){
			if(tapPointObject instanceof SwordElement){
		  		tapPointObject = tapPointObject._element;
		  	}
		  	this._target.tapWithOptions(tapPointObject,options);
		  },
		  touchAndHold:function(tapPointObject,duration){
		  	if(tapPointObject instanceof SwordElement){
		  		tapPointObject = tapPointObject._element;
		  	}
		  	this._target.touchAndHold(tapPointObject,duration);
		  },
		  deactivateApp: function(duration){
		  	return this._target.deactivateApp(duration);
		  },
		  delay:function(timeInterval){
			return this._target.delay(timeInterval);
		  },
		  captureRectWithName:function(rect,imageName){
		  	this._target.captureRectWithName(rect,imageName);
		  },
		  captureScreenWithName:function(imageName){
		  	this._target.captureScreenWithName(imageName);
		  },
		  popTimeout:function(){
		  	return this._target.popupTimeout();
		  },
		  pushTimeout:function(timeoutValue){
		  	$.warn("这个方法目前没有起效，使用$.options.timeout");
		  },
		  setTimeout:function(timeout){
		  	$.warn("这个方法目前没有起效，使用$.options.timeout");
		  },
		  timeout: function(){
		  	return this._target.timeout();
		  },
		  
		  /**
		   * A shortcut for checking that the interface orientation in either
		   * portrait mode
		   */
		  isPortraitOrientation: function() {
		    var orientation = this._target.deviceOrientation();
		    return orientation == UIA_DEVICE_ORIENTATION_PORTRAIT ||
		      orientation == UIA_DEVICE_ORIENTATION_PORTRAIT_UPSIDEDOWN;
		   },

		  /**
		   * A shortcut for checking that the interface orientation in one of the
		   * landscape orientations.
		   */
		  isLandscapeOrientation: function() {
		    var orientation = this._target.deviceOrientation();
		    return orientation == UIA_DEVICE_ORIENTATION_LANDSCAPELEFT ||
		      orientation == UIA_DEVICE_ORIENTATION_LANDSCAPERIGHT;
		   },

		   /**
		  * A convenience method for detecting that you're running on an iPad
		  */
		  isDeviceiPad: function() {
		    return this._target.model().match(/^iPad/) !== null;
		  },

		  /**
		   * A convenience method for detecting that you're running on an
		   * iPhone or iPod touch
		   */
		  isDeviceiPhone: function() {
		    return this._target.model().match(/^iPhone/) !== null;
		  }
	});

})(Sword);
