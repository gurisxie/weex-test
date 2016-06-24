/**
* sword中对于元素查找的对象方法集
**/

/**
* 表示对象往shortname的映射关系
* @param className 表示当前元素的类名
* @param shortName 表示映射的名
* @param multi 当前类型元素在界面上是否可以有多个
**/
#import "./sword-core.js"

function ElementType(className,shortName,multi,multiShortName){
	var result = new Object();
	result.className = className;
	result.shortName = shortName;
	result.multi = multi;
	if(arguments.length > 3){
		result.multiShortName = multiShortName;
	}
	return result;
}

(function($){
	/** 
	* UIAAutomation对象的映射对象
	**/
	$.classNameToTypes = {
	    'UIAActionSheet' : new ElementType('UIAActionSheet','actionSheet',false),
	    'UIAActivityIndicator':new ElementType('UIAActivityIndicator','activityIndicator',true,'activityIndicators'),
	    'UIAActivityView':new ElementType('UIAActivityView','activityView',false),
	    'UIAAlert': new ElementType('UIAAlert','alert',false),
	    'UIAButton': new ElementType('UIAButton','button',true,'buttons'),
	    'UIACollectionView': new ElementType('UIACollectionView','collectionView',true,'collectionViews'),
	    'UIAEditingMenu': new ElementType('UIAEditingMenu','editingMenu',false),
	    'UIAElement': new ElementType('UIAElement','element',true,'elements'),
 		'UIAImage': new ElementType('UIAImage','image',true,'images'),
	    'UIAKey': new ElementType('UIAKey','key',true,'keys'),
	    'UIAKeyboard': new ElementType('UIAKeyboard','keyboard',false),
	    'UIALink': new ElementType('UIALink','link',true,'links'),
	    'UIANavigationBar': new ElementType('UIANavigationBar','navigationBar',false),
	    'UIAPageIndicator':new ElementType('UIAPageIndicator','pageIndicator',true,'pageIndicators'), 
	    'UIAPicker': new ElementType('UIAPicker','picker',true,'pickers'), 
	    'UIAPickerWheel': new ElementType('UIAPickerWheel','pickerWheel',false),  //不是很确定
	    'UIAPopover': new ElementType('UIAPageIndicator','popover',false),
	    'UIAProgressIndicator': new ElementType('UIAProgressIndicator','progressIndicator',true,'progressIndicators'), 
	    'UIAScrollView': new ElementType('UIAScrollView','scrollView',true,'scrollViews'),
	    'UIASearchBar': new ElementType('UIASearchBar','searchBar',true,'searchBars'),
	    'UIASecureTextField': new ElementType('UIASecureTextField','secureTextField',true,'secureTextFields'),
	    'UIASegmentedControl': new ElementType('UIASecureTextField','segmentedControl',true,'segmentedControls'),
	    'UIASlider': new ElementType('UIASlider','slider',true,'sliders'),
	    'UIAStaticText': new ElementType('UIAStaticText','staticText',true,'staticTexts'),
	    'UIAStatusBar': new ElementType('UIAStatusBar','statusBar',false),
	    'UIASwitch': new ElementType('UIASwitch','switch',true,'switches'),
	    'UIATabBar': new ElementType('UIATabBar','tabBar',true,'tabBars'),
	    'UIATableCell': new ElementType('UIATableCell','cell',true,'cells'),
	    'UIATableGroup': new ElementType('UIATableGroup','group',true,'groups'),
	    'UIATableView': new ElementType('UIATableView','tableView',true,'tableViews'),
	    'UIATextField': new ElementType('UIATextField','textField',true,'textFields'),
	    'UIATextView': new ElementType('UIATextView','textView',true,'textViews'),
	    'UIAToolbar': new ElementType('UIAToolbar','toolbar',true,'toolbars'),
	    'UIAWebView': new ElementType('UIAWebView','webView',true,'WebViews'),
	    'UIAWindow': new ElementType('UIAWindow','window',true,'windows')   
	};

	/**
	* shortName到类名的映射关系 
	**/
	$.typeToClassNames = (function(){
		var results = {};
		for(var className in $.classNameToTypes){
			var type = $.classNameToTypes[className].shortName;
			results[type] = className;
		}
		return results;
	})();


	/**
	 * 支持的locator属性
	 **/
	$.supportLocatorKeys = {
		"className":1,
		"name":2,
		"label":3,
		"value":4,
		"visible":5, //是否可见
		"index":6  //index，从0开始
	}

	/**
	* 当前mainWindowElement的实例
	*/
	$.mainWindow = function(){
		var _mainWindow = target.frontMostApp().mainWindow();
		var mainWindowElement = new SwordElement(_mainWindow,null,null,0,null,"0|",null);
		return mainWindowElement;
	};

	
	$.targetWindow = function(locator){
		var offset = 0;
		if ( typeof(locator) == "number" ){
			offset = locator;
		}else{
		  	offset = locator.index;
		}	
		var _windows = target.frontMostApp().windows();  	
		offset = offset >= 0 && offset < _windows.length ? offset : 0;
		var targetSwordElement = new SwordElement(_windows[offset],null,null,0,null,"0|",null);
		$.message("offset is : " + offset);
		return targetSwordElement;
	}
	

	/**
	* 根据locator获取top的element
	*/
	$.topElement = function(locator){
		//如果locator是顶层的，需要先找到顶层的element
		if(locator.isTop==true){
			var className = locator.className;
			if($.isBlank(className)){
				return null;
			}
			var shortName = $.classNameToTypes[className].shortName;
			var app = target.frontMostApp();
			var _element;
			if(className=="UIAWindow"){
				if(locator.index > -1){
					var _windows = app.windows();
					if(locator.index > _windows.length-1) return null;
					_element = _windows[locator.index];
				}else{
					_element = app.mainWindow();
				}
			}else{
				_element = app[shortName].apply(app);
			}
			swordElement = new SwordElement(_element,null,null,1,null,"0|",null);
			return swordElement;
		}else{
			return null;
		}
	}
	
	/**
	* 获取指定context下面的所有的元素树
	* @param swordElement 要查找的根元素，为空则表示是mainWindow
	* @return 返回一个element的数组，通过signId和parentSignId获取父子关系
	*/
	$.elementTree = function(swordElement,callback){
	    swordElement = swordElement || $.mainWindow();
	    superElement = swordElement;
		 //获取当前element中的子元素
		 var _getChildElements = function(swordElement,array,superElement,callback){
		 	var element = swordElement._element;
		 	if($.isNil(element)){ //如果是空元素，直接返回
		 		return array;
		 	}
		 	var children =element.elements();
		 	var len = children.length;
		 	if(len > 0){
				var childElements = [];
		 		//先取得所有第一级子对象的属性
			 	$.each(children,function(i,child){
			 		//获取到child的遍历路径，供获取defaultLocator使用
			 		var treePath = swordElement.treePath+i+"|";
			 		var childElement = new SwordElement(child,swordElement,null,array.length+1,superElement,treePath,null);
			 		array.push(childElement);
					childElements.push(childElement);
			 		if(callback && $.isFunction(callback)){ //如果有回调函数
			 			var result = callback.call(childElement,childElement);
			 			if(!result){
			 				return result;
			 			}
			 		}
			 	});
			 	//递归获取子对象的子对象
			 	$.each(childElements,function(i,child){
			 		_getChildElements(child,array,superElement,callback);
			 	});
			 }
		 };
		 //如果superElement已经获取过一次elementTree，则不重新获取直接返回
		 var tree = superElement.elementTree;
		 if(!tree){
			 tree = [];
			 _getChildElements(swordElement,tree,superElement,callback);
			 superElement.elementTree = tree;
		 }
		 return tree;
	}
	
	/**
	 * 得到当前locator的父级locate对象
	 **/
	$._parentLocate = function(locator){
		var _locator = JSON.parse(JSON.stringify(locator));
		var child = _locator.child;
		var evalStr="delete _locator";
		if(child){
		 	evalStr = evalStr +".child";
			while(child){
			  if(child.child){
			     evalStr = evalStr +".child";
			     child = child.child;
			  }else{
			    eval(evalStr);
			    break;
			  }
			}
			return _locator;
		}else{
			return {};
		}  	
	}


	/**
	* 从指定的元素集合中
	* @param elementTree 要查找的元素集合
	* @param locator 当前元素定位的属性
	**/
	$._locateOnce = function(locator,elementTree){
		var founds = [];
		var index = locator.index;
		$.each(elementTree,function(index,swordElement){
			var match = true;
			for(var attrName in locator){
	    		var attr = $.supportLocatorKeys[attrName];
	    		var expectedValue = locator[attrName];
	    		if(attr){
	    			switch(attr){
	    				case $.supportLocatorKeys.className:
	    					var className = swordElement.getType();
	    					try{
	    						var regexp = new RegExp(expectedValue);
	    						if(!regexp.test(className)){
		    						match = false;
		    					}
	    					}catch(e){
	    						match = false;
	    					}
	    					break
	    				case $.supportLocatorKeys.name:
	    					var name = swordElement.name();
	    					if(!(name==expectedValue)){
	    						try{
		    						var regexp = new RegExp(expectedValue);
		    						if(!regexp.test(name)){
			    						match = false;
			    					}
	    						}catch(e){
		    						match = false;
		    					}
	    					}
	    					break
	    				case $.supportLocatorKeys.label:
	    					var label = swordElement.label();
	    					if(!(label==expectedValue)){
	    						try{
			    					var regexp = new RegExp(expectedValue);
			    					if(!regexp.test(label)){
			    						match = false;
			    					}
		    					}catch(e){
		    						match = false;
		    					}
	    					}
	    					break;
	    				case $.supportLocatorKeys.value:
	    					var value = swordElement.value();
	    					if(!(value==expectedValue)){
	    						try{
	    							var regexp = new RegExp(expectedValue);
			    					if(!regexp.test(value)){
			    						match = false;
			    					}
	    						}catch(e){
		    						match = false;
		    					}
	    					}
	    					break;
	    				case $.supportLocatorKeys.visible:
	    					var value = swordElement.isVisible();
			   				if(expectedValue.toString() == "true") expectedValue=1;
			   				else if(expectedValue.toString() == "false")expectedValue=0;
	    					if(value != parseInt(expectedValue)){
	    						match = false;
	    					}
	    					break;
	    				default:
	    					break;
	    			}
	    		}		   
	    		if(!match){
	    			break;
	    		}
	    	} // end for
	    	//判断当前的元素是否满足需求
	    	if(match){
	    		swordElement.locator = locator;
	    		founds.push(swordElement);
	    	}
	    	return true;
		}) // end each
		//判断是否有index属性

	    if(index > -1){
	        if(index <= founds.length -1 ){ 
			 return [founds[index]];
	        }
	        else{
	         return [];
	        }
	    }else{
		  return founds;
	    }
	}


	/**
	* 在指定的context中查找复合条件的元素
	* @param locator 要查找的属性
	* @param context 要查找的父对象
	* @return 找到元素，则返回SwordElement的数组
	**/
	$._find = function(locator,swordElement){
		elementTree = $.elementTree(swordElement);
		//批量查找	

		if( locator.className == "UIAWindow"){
			if( locator.child ){
				locator =  locator.child;
			}else{
				return[swordElement];
			}
		}

		var foundElements = $._locateOnce(locator, elementTree);
		var child = locator.child;
		while(child){
			if(foundElements.length > 0){
				elementTree = $.elementTree(foundElements[0]);
				foundElements = $._locateOnce(child, elementTree);
				child = child.child;
			}else{
				foundElements = [];
				break;
			}
		}
	   return foundElements;
	}

	/**
	* 在指定的context中查找复合条件的元素
	* @param locator 要查找的属性
	* @param context 要查找的父对象
	* @param timeout 超时的时间
	* @param throwIfNotFound 如果在指定的时间内没有找到，是否抛出异常
	* @return 找到元素，则返回SwordElement对象
	**/

	$.locate = function(locator,swordElement,timeout,throwIfNotFound){
		if(!locator) locator={};
		if(locator.isTop==true){
			swordElement = $.topElement(locator);
			locator = locator.child;
			if(!locator){
				return swordElement;
			}
		}
		//此处默认没有parentElement时，默认使用的是mainWindow
		//TODO 拓展为多窗口操作
		swordElement = swordElement || $.mainWindow(locator);
		if( locator.className == "UIAWindow"){
			swordElement = $.targetWindow(locator);
		}
		var start = new Date().getTime();
 		var stop = new Date().getTime();
		var foundElements = $._find(locator,swordElement);
		while(timeout && foundElements.length <= 0){         		
			if(stop - start <= timeout){
				//如果没有找到重置parent的elementTree
				swordElement.elementTree = null;
				foundElements = $._find(locator,swordElement);
				stop = new Date().getTime();
			}else{
				if(foundElements.length <= 0 && throwIfNotFound){
					$.throwException("在指定的时间内:" + timeout + "ms内，元素没有出现,locator属性为:" + JSON.stringify(locator));
				}
	 			break;
			}
		}
	 	if(foundElements.length > 0){
	 		return foundElements[0];
	 	} else{
	 		return null;
	 	}
	}


	/**
	* 在指定的context中查找复合条件的元素组合
	* @param locator 要查找的属性
	* @param context 要查找的父对象
	* @return 找到元素，则返回SwordElement对象数组，否则返回空数组
	**/
	$.locates = function(locator,swordElement){
		if(!locator) locator={};
		//$.message(" 0 isTop 0 " + locator.isTop);
		if(locator.isTop==true){
			swordElement = $.topElement(locator);
			locator = locator.child;
			if(!locator){
				return [swordElement];
			}
		}
		swordElement = swordElement || $.mainWindow();
		var foundElements = $._find(locator,swordElement);
		return foundElements;
	}


	// 对于各自特定对象的查找方法
	$.extend(Sword,{
		//查找指定的元素
		element:function(locator,timeout,throwIfNotFound,parentElement){
			target.setTimeout(0);
			try{
				timeout = timeout || $.options.timeout;
				throwIfNotFound = throwIfNotFound || true;
				return $.locate(locator,parentElement,timeout,throwIfNotFound);
			}finally{
				target.setTimeout(1);
			}
		},
		_typeElement: function(locator,className,timeout,throwIfNotFound,parentElement){
			target.setTimeout(0);
			try{
				if(!locator) locator={};
				if(typeof locator == "string"){ //如果是xpath
					locator = $.xpathToLocate(locator);
				}
				var lastLocator = locator;
				while(lastLocator.child){
					lastLocator = lastLocator.child;
				}
				lastLocator.className = className;
				var typeElement = $.element(locator,timeout,throwIfNotFound,parentElement);
				return typeElement;
			}finally{
				target.setTimeout(1);
			}
		},
		// 查找指定的元素集合
		elements: function(locator,parentElement){
			target.setTimeout(0);
			try{
				var elements = $.locates(locator,parentElement);
				return elements;
			}finally{
				target.setTimeout(1);
			}
		},
		_typeElements:function(locator,className,parentElement){
			target.setTimeout(0);
			try{
				if(!locator) locator={};
				if(typeof locator == "string"){ //如果是xpath
					locator = $.xpathToLocate(locator);
				}
				var lastLocator = locator;
				while(lastLocator.child){
					lastLocator = lastLocator.child;
				}
				lastLocator.className = className;
				var _typeElements = $.elements(locator,parentElement);
				return _typeElements;
			}finally{
				target.setTimeout(1);
			}
		}
	});
	
	/**
	* 扩展SwordElement类的常用方法
	**/
	$.extend(SwordElement.prototype,{
		//查找指定的元素
		element:function(locator,timeout,throwIfNotFound){
			return $.element(locator,timeout,throwIfNotFound,this);
		},
		_typeElement: function(locator,className,timeout,throwIfNotFound){
			return $._typeElement(locator,className,timeout,throwIfNotFound,this);
		},
		// 查找指定的元集合
		elements: function(locator ){
			return $.elements(locator,this);
		},
		_typeElements:function(locator,className){
			return $._typeElements(locator,className,this);
		}
	});

	//扩展所有的指定元素
	for(var className in $.classNameToTypes){
		if(className != 'UIAElement'){
			var shortName = $.classNameToTypes[className].shortName;
			var multiShortName = $.classNameToTypes[className].multiShortName;
			
			//单元素方法类
			$[shortName] = new Function("locator","timeout","throwIfNotFound","parentElement","return $._typeElement(locator,'" + className + "', timeout, throwIfNotFound,parentElement);");
			
			/*
				function原型
				function shortName(locator, timeout, throwIfNotFound, parentElement)
				{
					// className is in classNameToTypes
					return $._typeElement(locator, className, timeout, throwIfNotFound, parentElement);
				}
			*/

 			//对SwordElement类的扩展
			SwordElement.prototype[shortName] = new Function("locator","timeout","throwIfNotFound","return this._typeElement(locator,'" + className + "', timeout, throwIfNotFound);");
 	
			//集合方法类
			if(multiShortName && $.classNameToTypes[className].multi){
 				// 对根方法的扩展
				$[multiShortName] = new Function("locator","parentElement","return $._typeElements(locator,'" + className + "',parentElement);");

				//对swordElement的对象集合方法
				SwordElement.prototype[multiShortName] = new Function("locator","return this._typeElements(locator,'" + className + "');");	
			} 
		}	
	}
})(Sword);



 

