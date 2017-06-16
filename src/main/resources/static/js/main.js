var params;
var cmsArticleCode;

// 参考onBeginEdit
var lastEditRowNo;

$(function(){
	initDefault();
	initKeywordDatagrid();
})

/**
 * 初始化默认值
 */
function initDefault() {
	// init default cms process date
	var day1 = new Date();
	day1.setTime(day1.getTime()-24*60*60*1000);
	var yesterday = dateformatter(day1);
	$('#cmsDate').datebox('setValue', yesterday);
	
	// init default copy article
	params = {
		"info": {
		    "title" : "习近平主持召开政治局会议 分析研究当前经济形势和经济工作",
		    "content" : "会议认为，今年以来，在错综复杂的国内外形势下，各地区各部门按照党中央决策部署，主动适应经济发展新常态，坚持新发展理念，坚持以推进供给侧结构性改革为主线，积极推进结构调整和新旧动能转换，有效防控风险，推动经济社会发展取得了来之不易的成绩。一季度经济运行稳中向好、实现良好开局，增长和效益回升，市场预期改善，内需对经济增长的贡献加大，新技术新产品新服务不断涌现并快速成长，产业优化升级不断推进，就业继续增加，城乡居民收入增长有所加快。各方面对新发展理念和供给侧结构性改革的认识逐步加深、行动更为自觉，这对转变经济发展方式、保持经济平稳健康发展具有重要意义。",
		    
	    },
	    "count": "8",
	    "onlytag": "false"
	};
	$('#copylayout #title').textbox('setValue', params.info.title);
	$('#copylayout #content').textbox('setValue', params.info.content);
	$('#copylayout #count').textbox('setValue', params.count);
}

function initKeywordDatagrid() {
	var options = {
		// 不要自动加载数据
		autoLoad:false,
		onBeforeLoad:function(){
			var opts = $(this).datagrid('options');
			return opts.autoLoad;
		},
		// 记录最后一次编辑的行号, 在保存处理前,先触发endEdit事件, 通过refreshRow来获取最新编辑数据, 参考onEndEdit
		onBeginEdit:function(index,row){
			lastEditRowNo = index;
		},
		// 参考onBeginEdit
		onEndEdit:function(index,row){
			$(this).datagrid('refreshRow', index);
		},
		// 处理checkbox需要点击两次的问题
		onClickCell: function (rowIndex, field, value) {
			var ed = $(this).datagrid('getEditor', {index:rowIndex,field:field});
			var cb = ed.target.get(0);
			
			if(cb.type=="checkbox") {
				var oldvalue=cb.checked;
				cb.checked=!oldvalue;
			}
	    },
		url:'main/tags',
	    columns:[[
	        {field:'wname',title:'关键词',width:80},
	        {field:'family',title:'大类别',width:80},
	        {field:'category',title:'细类别',width:80},
	        {field:'weight',title:'权重',width:50, sortable:true},
	        {field:'freq',title:'总频次',width:50},
	        {field:'removed',title:'从关键词表剔除',width:100, editor:{type:'checkbox',options:{on:'1',off:'0'}}, formatter:formatCheckbox},
	        {field:'removedArticle',title:'从文本剔除',width:100, editor:{type:'checkbox',options:{on:'1',off:'0'}}, formatter:formatCheckbox},
	        {field:'note',title:'备注说明',width:200, editor:'text'/*, formatter:formatText*/}
	    ]],
		queryParams : params,
		loader : function(param, success, error) {    
			 var opts=$(this).datagrid("options");
	         $.ajax({  
	             type : opts.method,  
	             url : opts.url,  
	             contentType: "text/plain", // 设置请求头信息  
	             data : JSON.stringify(param), 
	             success : function(result) { 
	            	 if(result.length == 0)
	            		 return;
	            	 else
	                     success(result);                  
	             }  
	         });  
	     }  
	};
	
	// 重载datagrid loader方法
	var dg1 = $('#copylayout #keywordsTable').datagrid(options);
	var dg2 = $('#cmslayout #keywordsTable').datagrid(options);
	
	dg1.datagrid('enableCellEditing');
	dg2.datagrid('enableCellEditing');
	
	// 加载拷贝文章分词结果
	$('#copylayout #keywordsTable').datagrid('options').autoLoad = true;
	$('#copylayout #keywordsTable').datagrid('load',params);
	
}

function formatCheckbox(val,row){
    if (val == '1'){
        return '<input type="checkbox" style="margin-left: 0" checked/>';
    } else {
    	return '<input type="checkbox" style="margin-left: 0"/>';
    }
}

function formatText(val,row){
	if( val == null)
		val = "";
    return '<input type="text" value="' + val + '"/>';
}

function dateformatter(date){
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}
function dateparser(s){
    if (!s) return new Date();
    var ss = (s.split('-'));
    var y = parseInt(ss[0],10);
    var m = parseInt(ss[1],10);
    var d = parseInt(ss[2],10);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
        return new Date(y,m-1,d);
    } else {
        return new Date();
    }
}

/**
 * 执行算法分词操作
 */
function tagQuery() {
	// get current tab's title
	var p = $('#process-tabs').tabs('getSelected');  
	var title = p.panel('options').title;
	
	var tab;
	if(title == "拷贝文章处理") {
		tab = 0;
	} else {
		tab = 1;
	}
	
	
	// default to cms
	var title = $('#cmslayout #title').val();
	var content = $('#cmslayout #content').val();
	var count = $('#cmslayout #count').val();
	
	if(tab == 0) {
		title = $('#copylayout #title').val();
		content = $('#copylayout #content').val();
		count = $('#copylayout #count').val();
	}
	
	params = {
		"info" : {
			"title": title,
			"content": content
			
		},
		"count" : count
	};
	
	if(tab == 0) {
		$('#copylayout #keywordsTable').datagrid('options').autoLoad = true;
		$('#copylayout #keywordsTable').datagrid('load',params);
		
		//$('#copylayout #keywordsTable').datagrid('reload');
		
	} else {
		$('#cmslayout #keywordsTable').datagrid('options').autoLoad = true;
		$('#cmslayout #keywordsTable').datagrid('load',params);
	}
		
}

/**
 * 保存用户对关键词的处理结果
 */
function saveTagProcess() {
	var validate = $("input[name=operatorId]:visible").validatebox('isValid');
	if(validate == false) {
		alert("请输入操作员id");
		return;
	}
	
	// get current tab's title
	var p = $('#process-tabs').tabs('getSelected');  
	var title = p.panel('options').title;
	
	var tab;
	if(title == "拷贝文章处理") {
		tab = 0;
	} else {
		tab = 1;
	}
	
	// default to cms
	var articleFrom = "1";
	var cmsDate = $("#cmsDate").val();
	var operatorId = $("input[name=operatorId]:visible").val();
	var operatorDept = $("input[name=operatorDept]:visible").val();
	
	var userCount = $('#cmslayout #count').val();
	
	if(tab == 0) {
		// 拷贝文章
		articleFrom = "99";
		cmsDate = null;
		userCount = $('#copylayout #count').val();
	}
	
	var rows;
	var missed;
	if(tab == 0) {
		$('#copylayout #keywordsTable').datagrid("endEdit", lastEditRowNo);
		rows = $('#copylayout #keywordsTable').datagrid('getRows');
		missed = $("#copylayout #missDiv .easyui-textbox");
	} else {
		$('#cmslayout #keywordsTable').datagrid("endEdit", lastEditRowNo);
		rows = $('#cmslayout #keywordsTable').datagrid('getRows');
		missed = $("#cmslayout #missDiv .easyui-textbox");
	}
	
	
	var keywordsProcess = rows;
	var missedKeywords = [];
	
	missed.each(function() { 
		var s = $(this).val();
		if(s != null && s != "")
			missedKeywords.push($(this).val()); 
	});
	
	//没有分词结果并且没有缺失关键词, 直接返回
	if(keywordsProcess.length == 0 && missedKeywords.length == 0) {
		return;
	}
	
	var topicId = "0";
	if(tab == 1) {
		topicId = cmsArticleCode;
	}
	
	params = {
		"operatorId" : operatorId, 
		"operatorDept" : operatorDept, 
		"userCount" : userCount,
		"articleFrom" : articleFrom,
		"cmsCreateTime" : cmsDate,
		"topicId" : topicId,
		"keywordsProcess" : keywordsProcess,
		"missedKeywords" : missedKeywords
	};
	
	$.ajax({
		url: "main/saveTagProcess",
		type: "post",
		contentType: "text/plain",
		data: JSON.stringify(params),
		success:function(res){
			alert(res);
		}
	})
}

/**
 * 载入下一篇待处理CMS文章
 */
function loadNextCms() {
	var cmsDate = $("#cmsDate").val();
	var infoCode = $("#infoCode").val();
	$.ajax({
		url: "main/loadNextCms",
		type: "post",
		data: {
			cmsDate:cmsDate,infoCode:infoCode
		},
		success:function(res){
			if(res == "error") {
				alert("server error");
				return;
			}
			if(res.msg != undefined) {
				alert(res.msg);
				return;
			}
			
		
			var article = res;
			
			cmsArticleCode = article.code;
			
			var title = article.title;
			var content = article.content;
			var content_text = $(content).text();
			
			$('#cmslayout #title').textbox('setValue', title);
			$('#cmslayout #content').textbox('setValue', content_text);
		}
	})
}