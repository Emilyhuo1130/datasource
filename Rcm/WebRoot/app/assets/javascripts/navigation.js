//tabel 行添加浮动层方法    本节点   放置的内容   用户1 012   非用户1  312
function user1_goto(userName){
	var navi_json=getNaviList();
	if(userName=="陈强"){
		showFloatStack($("#Alert"),navi_json[0].value);
		showFloatStack($("#Analy"),navi_json[1].value);
		showFloatStack($("#Manage"),navi_json[2].value);
	}else if(userName=="张华"){
		showFloatStack($("#Alert"),navi_json[3].value);
		showFloatStack($("#Analy"),navi_json[1].value);
		showFloatStack($("#Manage"),navi_json[2].value);
	}else if(userName=="刘鹏"){
		showFloatStack($("#Alert"),navi_json[4].value);
		showFloatStack($("#Analy"),navi_json[1].value);
		showFloatStack($("#Manage"),navi_json[2].value);
	}else if(userName=="李红"){
		showFloatStack($("#Alert"),navi_json[5].value);
		showFloatStack($("#Analy"),navi_json[1].value);
		showFloatStack($("#Manage"),navi_json[2].value);
	}else if(userName=="朱军"){
		showFloatStack($("#Alert"),navi_json[0].value);
		showFloatStack($("#Analy"),navi_json[1].value);
		showFloatStack($("#Manage"),navi_json[2].value);
	}else if(userName=="周红"){//occ总调度
		showFloatStack($("#Alert"),navi_json[3].value);
		showFloatStack($("#Analy"),navi_json[1].value);
		showFloatStack($("#Manage"),navi_json[2].value);
	}else if(userName=="胡明"){//cocc
		showFloatStack($("#Alert"),navi_json[0].value);
		showFloatStack($("#Analy"),navi_json[1].value);
		showFloatStack($("#Manage"),navi_json[2].value);
	}else if(userName=="曹雪"){//occ专业工程师
		showFloatStack($("#Alert"),navi_json[4].value);
		showFloatStack($("#Analy"),navi_json[1].value);
		showFloatStack($("#Manage"),navi_json[2].value);
	}else if(userName=="周林"){//occ专业主任
		showFloatStack($("#Alert"),navi_json[5].value);
		showFloatStack($("#Analy"),navi_json[1].value);
		showFloatStack($("#Manage"),navi_json[2].value);
	}
	
} 
function getNaviList(){
	 var navi_json=[
				      {
				       	 "naviName":"naviAlert","value":"<div style='position:absolute;z-index:1000;left:25%;display:none;width:50%;background-image:url(../../images/background.png);'>"+
				       	 									"<ul style='list-style-type:none;line-height:30px;'>"+
				       	 									//"<li><a id='emedAlert' href='../alert/current_process_selector.jsp' >实时告警</a></li>"+
				       										//"<li><a id='hisAlart' href='../alert/history_process_selector.jsp' >历史告警</a></li>"+
				       	 									"<li><a id='emedAlert' href='../cocc/coccmain.jsp' >综合协调中心总调度</a></li>"+
				       	 									
				       										"<li>&nbsp;</li>"+
				       										"</ul></div>"
				       },
				      {
	 				     "naviName":"naviAnalysis","value":"<div style='position:absolute;z-index:1000;display:none;left:25%;width:50%;background-image:url(../../images/background.png);'>"+
	 				       	 									"<ul style='list-style-type:none;line-height:30px;'>"+
	 				       	 									"<li><a id='entiAny' href='../event/_sc.jsp'> 实件分析</a></li>"+
	 				       										"<li><a id='impAny' href='../impact/show.jsp'>影响分析</a></li>"+
	 				       										"<li><a id='kpiAny' href='../target/_repair_statistics.jsp'>指标分析</a></li>"+
	 				       									    "<li><a id='strateAny' href='../strategy/show.jsp'>策略制定</a></li>"+
	 				       									    "<li>&nbsp;</li>"+
	 				       										"<ul></div>"
	 				       },
	 				       {
		  				       "naviName":"naviReport","value":"<div style='position:absolute;z-index:1000;display:none;width:50%;display:none;left:25%;background-image:url(../../images/background.png);'>"+
									 "<ul style='list-style-type:none;line-height:30px;'>"+
									 "<li><a id='maintence'href='../ticket/_notification_ticket.jsp' >维修通知单</a></li>"+
									 "<li><a id='maintence'href='../ticket/_feedback_ticket.jsp' >维修反馈记录</a></li>"+
									 "<li><a id='planMainten' href='../alert/planMatntence_program.jsp' >计划修规程</a></li>"+
									 "<li><a id='countRep' href='../report/report_historyAlert_statistics_count.jsp' >统计报表</a></li>"+
									// "<li><a id='countRep' href='../report/report_historyAlert_statistics_count2.jsp' >统计报表</a></li>"+
									 "<li>&nbsp;</li>"+
									 "<ul></div>"
},
{
"naviName":"naviAlert2","value":"<div style='position:absolute;z-index:1000;left:25%;display:none;width:50%;background-image:url(../../images/background.png);'>"+
									"<ul style='list-style-type:none;line-height:30px;'>"+
									"<li><a id='emedAlert' href='../occ/occmain.jsp' >中央控制室调度</a></li>"+
									//"<li><a id='emedAlert' href='../occ/application.jsp' >OCC主页</a></li>"+
									//"<li><a id='hisAlart'  href='../alert/faultPrecautionaryAlert_check_list.jsp' >故障预警</a></li>"+
									"<li>&nbsp;</li>"+
									"</ul></div>"
},
{
"naviName":"naviAlert3","value":"<div style='position:absolute;z-index:1000;left:25%;display:none;width:50%;background-image:url(../../images/background.png);'>"+
	 									"<ul style='list-style-type:none;line-height:30px;'>"+
	 									"<li><a id='emedAlert' href='../occ/_audit_list.jsp' >故障告警</a></li>"+
										"<li><a id='hisAlart'  href='../occ/faultPrecautionaryAlert_check_list_user3.jsp' >故障预警</a></li>"+
										
										"<li>&nbsp;</li>"+
										"</ul></div>"
},
{
"naviName":"naviAlert4","value":"<div style='position:absolute;z-index:1000;left:25%;display:none;width:50%;background-image:url(../../images/background.png);'>"+
  	 									"<ul style='list-style-type:none;line-height:30px;'>"+
  	 									"<li><a id='emedAlert' href='../occ/faultAlert_check_list_user4.jsp' >故障告警</a></li>"+
  										"<li><a id='hisAlart'  href='../occ/faultPrecautionaryAlert_check_list_user4.jsp' >故障预警</a></li>"+
  										"<li><a id='hisAlart'  href='../occ/assessOfwarning.jsp' >评估建议</a></li>"+
  										"<li>&nbsp;</li>"+
  										"</ul></div>"
  }
	 				     
				                 								
				      ];
	 
				return navi_json;
				
				
}
//导航菜单
function   showFloatStack(jqueryNode,conTent){
	
	var $node = $(jqueryNode).parent();
	//jqueryNode为div 中的a
		$(jqueryNode).filter("[id!=mainpage]").mouseover(function(){
			//兄弟菜单的子菜单收回   本子菜单滑出
			$node.siblings("div").children().children().slideUp("normal");
			$node.append(conTent);
			$node.children("div").slideDown("fast");
			$node.children("div").mouseleave(function(){
				$node.children("div").slideUp("normal");
			});
			//$node.siblings("div").css({"opacity":".7"});
			$node.find("div  [id!=alertManage][id!=maintenceAnalysis][id!=maintenceManege][id!=mainpage] ").find("a").css({"font-size":".85em"});
});
	
	$node.mouseout(function(){
		$node.siblings("div [id!=mainpage] [id!=alertManage][id!=maintenceAnalysis][id!=maintenceManege]").mouseleave(function(){
				$(this).filter("[id!=mainpage]").slideUp("normal");
		});
		$node.mouseleave(function(){
			$node.find("div [id!=mainpage] [id!=alertManage][id!=maintenceAnalysis][id!=maintenceManege]").slideUp("normal");
			
		});
		
	});
	
}


/***页面标签***/
function changeLabel(user){
	if(user=="胡明"){
		
	}else if(user=="周红"){
		
	}else if(user=="曹雪"){
		$("#mainpage").slideUp("slow");
	}else if(user=="周林"){
		
	}
}