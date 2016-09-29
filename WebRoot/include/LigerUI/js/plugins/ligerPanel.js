/**
* jQuery ligerUI 1.2.4
* 
* http://ligerui.com
*  
* Author daomi 2014 [ gd_star@163.com ] 
* 
*/
(function ($)
{

    $.fn.ligerPanel = function (options)
    {
        return $.ligerui.run.call(this, "ligerPanel", arguments);
    };

    $.ligerDefaults.Panel = {
        width: 400,
        height : 300,
        title: 'Panel',
        content: null,      //内容
        url: null,          //远程内容Url
        frameName: null,     //创建iframe时 作为iframe的name和id 
        data: null,          //可用于传递到iframe的数据
        showClose: true,    //是否显示关闭按钮
        showToggle: true,    //是否显示收缩按钮 
        icon: null,          //左侧按钮
        onClose:null,       //关闭前事件
        onClosed:null,      //关闭事件
        onLoaded:null, 		//url模式 加载完事件
        showMax: true,  //     自己新加的
        showMin: false  //     自己新加的
    };

    $.ligerMethos.Panel = {};

    $.ligerui.controls.Panel = function (element, options)
    {
        $.ligerui.controls.Panel.base.constructor.call(this, element, options);
    };
    $.ligerui.controls.Panel.ligerExtend($.ligerui.core.UIComponent, {
        __getType: function ()
        {
            return 'Panel';
        },
        __idPrev: function ()
        {
            return 'Panel';
        },
        _extendMethods: function ()
        {
            return $.ligerMethos.Panel;
        },
        _init: function ()
        {
            var g = this, p = this.options;
            $.ligerui.controls.Panel.base._init.call(this);
            p.content = p.content || $(g.element).html(); 
        },
        _render: function ()
        {
            var g = this, p = this.options; 
            g.panel = $(g.element).addClass("l-panel").html("");
            g.panel.append('<div class="l-panel-header"><span></span><div class="icons"></div></div><div class="l-panel-content"></div>');
             
            g.set(p);
 
            g.panel.bind("click.panel", function (e)
            { 
                var obj = (e.target || e.srcElement), jobj = $(obj);
                if (jobj.hasClass("l-panel-header-toggle"))
                {
                    g.toggle();
                } else if (jobj.hasClass("l-panel-header-close"))
                {
                    g.close();
                } else if (jobj.hasClass("l-panel-header-other"))
                {
                    g.max();
                } else if (jobj.hasClass("l-panel-header-other-min"))
                {
                    g.min();
                }
            });
        },
        _setChildren: function(children)
        {
            var g = this, p = this.options;
            var tagNames = {
                input : ["textbox", "combobox", "select"] 
            };
            var PluginNameMatchs  = 
            {
                "grid" : "ligerGrid",
                "toolbar":"ligerToolBar",
                "tree":"ligerTree",
                "form":"ligerForm",
                "menu":"ligerMenu",
                "menubar":"ligerMenuBar",
                "portal":"ligerPortal",
                "combobox":"ligerComboBox",
                "textbox":"ligerTextBox",
                "spinner":"ligerSpinner",
                "listbox":"ligerListBox",
                "checkbox":"ligerCheckBox",
                "radio":"ligerRadio",
                "checkboxlist":"ligerCheckBoxList",
                "radiolist":"ligerRadioList",
                "popupedit":"ligerPopupEdit",
                "button":"ligerButton",
                "dateeditor":"ligerDateEditor",
                "dialog":"ligerDialog",
                "panel":"ligerPanel",
                "layout":"ligerLayout",
                "accordion":"ligerAccordion",
                "tab":"ligerTab" 
            }; 
            if (!children || !children.length) return;
            for (var i = 0; i < children.length; i++)
            {
                var child = children[i], type = child.type;
                var tagName = tagNames[type] || "div"; 
                var plugin = PluginNameMatchs[type];
                if (!plugin) continue;
                var element = document.createElement(tagName);
                g.panel.find(".l-panel-content").append(element);
                var childOp = $.extend({},child);
                childOp.type = null;
                $(element)[plugin](childOp);
            }
        },
        collapse: function ()
        {
            var g = this, p = this.options;
            var toggle = g.panel.find(".l-panel-header .l-panel-header-toggle:first");
            if (toggle.hasClass("l-panel-header-toggle-hide")) return;
            g.toggle();
        },
        expand: function ()
        {
            var g = this, p = this.options;
            var toggle = g.panel.find(".l-panel-header .l-panel-header-toggle:first");
            if (!toggle.hasClass("l-panel-header-toggle-hide")) return;
            g.toggle();
        },
        toggle : function()
        {
            var g = this, p = this.options;
            var toggle = g.panel.find(".l-panel-header .l-panel-header-toggle:first");
            if (toggle.hasClass("l-panel-header-toggle-hide"))
            {
                toggle.removeClass("l-panel-header-toggle-hide");
            } else
            {
                toggle.addClass("l-panel-header-toggle-hide");
            }
            g.panel.find(".l-panel-content:first").toggle("normal");
        },
        //自己新添加的方法 打开一个新的tab
        max: function()
        {
            var g = this, p = this.options;
            g.doMax();
            //window.top.f_addTab(p.title,p.title,p.url);
        },
        //自己新添加的方法 放大panel
        doMax: function()
        {
            var g = this, p = this.options;
            var plen = $("#portalMain .l-panel").length;
            var p_index = 0;
		    $("#portalMain .l-panel").fadeOut(1000,function(){
		    	p_index = p_index + 1;
		    	if(p_index === plen){
		    	 	var width = $(document.body).width() - 45, height = $(document).height() - 40;
					var obj = {
						width:width,
						title: p.title,
						height: height,
						url: p.url
					  }
			         if(typeof my_max_add == "function"){     
		           		my_max_add(obj);
		           	 }
		    	}
			 });
            return false;
        },
        //自己新添加的方法 打开一个新的tab
        min: function()
        {
            var g = this, p = this.options;
            if(typeof my_min_remove == "function"){     
           		my_min_remove();
           	 }
           	$("#portalMain .l-panel").fadeIn(1000);
        },
        _setShowToggle:function(v)
        {
            var g = this, p = this.options;
            var header = g.panel.find(".l-panel-header:first");
            if (v)
            {
                var toggle = $("<div class='l-panel-header-toggle'></div>"); 
                toggle.appendTo(header.find(".icons")); 
            } else
            {
                header.find(".l-panel-header-toggle").remove();
            }
        },
        _setContent: function (v)
        {
            var g = this, p = this.options;
            var content = g.panel.find(".l-panel-content:first");
            if (v)
            {
                content.html(v);
            }
        },
        _setUrl: function (url)
        {
            var g = this, p = this.options;
            var content = g.panel.find(".l-panel-content:first");
            if (url)
            {
                g.jiframe = $("<iframe frameborder='0'></iframe>");
                var framename = p.frameName ? p.frameName : "ligerpanel" + new Date().getTime();
                g.jiframe.attr("name", framename);
                g.jiframe.attr("id", framename);
                content.prepend(g.jiframe); 

                setTimeout(function ()
                {
                    if (content.find(".l-panel-loading:first").length == 0)
                        content.append("<div class='l-panel-loading' style='display:block;'></div>");
                    var iframeloading = $(".l-panel-loading:first", content);
                    g.jiframe[0].panel = g;//增加窗口对panel对象的引用
                    /*
                    可以在子窗口这样使用：
                    var panel = frameElement.panel;
                    var panelData = dialog.get('data');//获取data参数
                    panel.set('title','新标题'); //设置标题
                    panel.close();//关闭dialog 
                    */
                    g.jiframe.attr("src", p.url).bind('load.panel', function ()
                    {
                        iframeloading.hide();
                        g.trigger('loaded');
                    });
                    g.frame = window.frames[g.jiframe.attr("name")];
                }, 0); 
            }
        },
        _setShowClose: function (v)
        {
            var g = this, p = this.options;
            var header = g.panel.find(".l-panel-header:first");
            if (v)
            {
                var btn = $("<div class='l-panel-header-close'></div>"); 
                btn.appendTo(header.find(".icons"));
            } else
            {
                header.find(".l-panel-header-close").remove();
            }
        },
        //自己新添加的方法 
        _setShowMax: function (v)
        {  
            var g = this, p = this.options;
            var header = g.panel.find(".l-panel-header:first");
            if (v)
            {
                var btn = $("<div class='l-panel-header-other'></div>"); 
                btn.appendTo(header.find(".icons"));
            } else
            {
                header.find(".l-panel-header-other").remove();
            }
        },
         //自己新添加的方法 
        _setShowMin: function (v)
        {  
            var g = this, p = this.options;
            var header = g.panel.find(".l-panel-header:first");
            if (v)
            {
                var btn = $("<div class='l-panel-header-other-min'></div>"); 
                btn.appendTo(header.find(".icons"));
            } else
            {
                header.find(".l-panel-header-other-min").remove();
            }
        },
        close:function()
        {
            var g = this, p = this.options;
            if (g.trigger('close') == false) return;
            g.panel.remove();
            g.trigger('closed');
        }, 
        show: function ()
        {
            this.panel.show();
        },
        _setIcon : function(url)
        {
            var g = this;
            if (!url)
            {
                g.panel.removeClass("l-panel-hasicon");
                g.panel.find('img').remove();
            } else
            {
                g.panel.addClass("l-panel-hasicon");
                g.panel.append('<img src="' + url + '" />');
            }
        }, 
        _setWidth: function (value)
        { 
            value && this.panel.width(value);
        },
        _setHeight: function (value)
        { 
            var g = this, p = this.options;
            var header = g.panel.find(".l-panel-header:first");
            this.panel.find(".l-panel-content:first").height(value - header.height());
        },
        _setTitle: function (value)
        {
            this.panel.find(".l-panel-header span:first").text(value);
        } 
    }); 


})(jQuery);