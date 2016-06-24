/**
* 对SwordElement动作事件的扩展
**/
#import "./sword-core.js"
#import "./sword-find.js"

(function($){
	//对于swordElement对象的动作扩展
	$.extend(SwordElement.prototype,{
		getDefaultLocator:function(){
			target.setTimeout(0);
 			var locator = {};
 			locator.className = this.getType();
			if(!$.isBlank(this.name())){
				locator.name = this.name();
			}else if(!$.isBlank(this.label())){
				locator.label = this.label();
			}
			target.setTimeout(1);
			//自动计算index
			var parentElement = this.superElement;
			if(parentElement){
				var childs = parentElement.elements(locator);
				var cur = this;
				if(childs.length >= 1){ //如果有多个符合条件的
					$.each(childs,function(idx,child){
						if(cur.treePath==child.treePath){
							locator.index = idx;
							return false;
						}
						return true;
					})
				}
			}
			return locator;
		},
		//当前控件的自我描述字符串
		_selfInspect:function(results){
			results = results || [];
			if(this.locator == null|| this.locator == {}){ //如果没有locator属性，则自动计算locator属性
				this.locator = this.getDefaultLocator();
			}
			var locator = this.locator;
			var className = locator.className;
			if(!className ){
				className = 'UIAElement';
			}
			if(className != 'UIAWindow'){
			var classType = $.classNameToTypes[className];
			var shortName = 'element';
			if(classType){
				shortName = classType.shortName;
			}
			var str = '.'+ shortName + '("';
			for(var key in locator){
				if(key != 'className' && key != 'index'){
					str = str + '[' + key + '=' + locator[key] + ']';
				}
			}
			if(locator.index){
					str = str + '[' + locator.index + ']';
			}
			str = str + '")';
			results.push(str);
			if(this.parentElement){
				this.parentElement._selfInspect(results);
			}
			 }
			return '$' + results.reverse().join('');
		},
		_printLog:function(methodName){
			 if($.options.showLog) $.debug("[动作] " + this._selfInspect() + "." + methodName + "()");
		},
		/*UIAElement默认的动作*/
		doubleTap:function(){
			this._printLog("doubleTap");
			this._element.doubleTap();
		},
		dragInsideWithOptions:function(options){
			this._printLog("dragInsideWithOptions")
			this._element.dragInsideWithOptions(options);
		},
		flickInsideWithOptions:function(options){
			this._printLog("flickInsideWithOptions");
			this._element.flickInsideWithOptions(options);
		},
		rotateWithOptions:function(options){
			this._printLog("rotateWithOptions");
			this._element.rotateWithOptions(options);
		},
		scrollToVisible:function(){
			this._printLog("scrollToVisible");
			this._element.scrollToVisible();
		},
		tap:function(){
			this._printLog("tap");
			this._element.tap();
		},
		tapWithOptions:function(options){
			this._printLog("tapWithOptions");
			this._element.tapWithOptions(options);
		},
		touchAndHold:function(duration){
			this._printLog("touchAndHold");
			this._element.touchAndHold(duration);
		},
		twoFingerTap:function(){
			this._printLog("twoFingerTap");
			this._element.twoFingerTap();
		},
		cancelButton:function(){
			if(this._element.cancelButton instanceof Function){
				this._printLog("cancelButton");
				this._element.cancelButton().tap();
				
			}
		},
		defaultButton:function(){
			if(this._element.defaultButton instanceof Function){
				this._printLog("defaultButton");
				this._element.defaultButton().tap();
				
			}
		},
		logElement:function(){
			this._element.logElement();
		},
		logElementTree:function(){
			this._element.logElementTree();
		},
		withName: function(name){
			var _element = this._element.withName(name);
			return new SwordElement(_element,this,null,1);
		},
		withPredicate : function(predicateString){
			var _element = this._element.withPredicate(predicateString);
			return new SwordElement(_element,this,null,1);
		},
		withValueForKey: function(value,key){
			var _element = this._element.withValueForKey(value,key);
			return new SwordElement(_element,this,null,1);
		},
		setValue:function(value){
			if(this._element.setValue instanceof Function){
				this._printLog("setValue");
				this._element.setValue(value);
			}
		},
		selectValue:function(value){
			if(this._element.selectValue instanceof Function){
				this._printLog("selectValue");
				this._element.selectValue(value);
			}
		},
		scrollUp:function(options){
			if(this._element.scrollUp instanceof Function){
				this._printLog("scrollUp");
				this._element.scrollUp();
			}
		},
		scrollDown:function(options){
			if(this._element.scrollDown instanceof Function){
				this._printLog("scrollDown");
				this._element.scrollDown();
			}
		},
		scrollLeft:function(options){
			if(this._element.scrollLeft instanceof Function){
				this._printLog("scrollLeft");
				this._element.scrollLeft();
			}
		},
		scrollRight:function(options){
			if(this._element.scrollRight instanceof Function){
				this._printLog("scrollRight");
				this._element.scrollRight();
			}
		},
		scrollToElementWithName:function(name){
			if(this._element.scrollToElementWithName instanceof Function){
				this._printLog("scrollToElementWithName");
				var _element = this._element.scrollToElementWithName(name);
				return new SwordElement(_element,this,null,1);
			}
		},
		scrollToElementWithPredicate:function(predicateString){
			if(this._element.scrollToElementWithPredicate instanceof Function){
				this._printLog("scrollToElementWithPredicate");
				var _element = this._element.scrollToElementWithPredicate(predicateString);
				return new SwordElement(_element,this,null,1);
			}
			return null;
		},
		scrollToElementWithValueForKey:function(value,key){
			if(this._element.scrollToElementWithValueForKey instanceof Function){
				this._printLog("scrollToElementWithValueForKey");
				var _element = this._element.scrollToElementWithValueForKey(value,key);
				return new SwordElement(_element,this,null,1);
			}
			return null;
		},

		/**
		* 在指定的时间内等待页面元素出现
		*/
		 waitUntilVisible: function (timeoutInSeconds) {
		    timeoutInSeconds = timeoutInSeconds === null ? 5 : timeoutInSeconds;
		    var element = this._element;
		    var delay = 0.25;
		    $.retry(function() {
		      if(!element.isVisible()) {
		         $.throwException("Element (" +  element + ") didn't become visible within " + timeoutInSeconds + " seconds.");
		      }
		    }, timeoutInSeconds/delay, delay);
		  },
		 
		  /*
		  * 等待元素出现
		  */  
		  waitUntilInvisible: function (timeoutInSeconds) {
		    timeoutInSeconds = timeoutInSeconds == null ? 5 : timeoutInSeconds;
		    var element = this._element;
		    var delay = 0.25;
		    retry(function() { 
		      if(element.isVisible()) {
		        $.throwException("Element (" +  element + ") didn't become invisible within " + timeoutInSeconds + " seconds.");
		      }
		    }, timeoutInSeconds/delay, delay);
		  },
		  
		  /**
		   * 等待元素出现后点击
		   */
		  vtap: function() {
		    this.waitUntilVisible(10);
		    this.tap();
		  },
		  /**
		   * 点击后等待元素valid
		   */
		  tapAndWaitForInvalid: function() {
		    this.tap();
		    this.waitForInvalid();
		  }
	});

})(Sword);