<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head>
	<%String userName =(String)session.getAttribute("username"); %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<style type="">
		
/* .tog{
	display:none;
} */

div.lineInfo
{
    background-color: #DDDDDD;
    border: 1px solid #DDDDDD;
    color: #000;
}
div.lineInfo
{
    position: absolute;
    z-index: 10;
    min-width: 200px;
    min-height: 150px;
    display: none;
    opacity:.8;
    padding: 3px;
    overflow: visible;
    text-align: left;
	left:50%;
	top:17%
}

div.LineHealth
{
    background-color: #DDDDDD;
    border: 1px solid #DDDDDD;
    opacity:.8;
    color: #000;
}
div.LineHealth
{
    position: absolute;
    z-index: 10;
    min-width: 200px;
    min-height: 150px;
    display: none;
    opacity:.8;
    padding: 3px;
    overflow: visible;
    text-align: left;
	left:50%;
	top:63%
}
.ulli{
	color:#0989aa;
}
	</style>
		<script type="text/javascript">
 		$(document).ready(function(){
 			OnOperativeIndexUpdate();
 			OnMainPageQuery({'warningType':'故障告警','statments':'未处理'});
 			
        	 //图表模块
        	 getRateOfWarning(); 
			
        	 
        	 
         	 $("#subNavi ul li a").click(function(){
         		 //alert($(this).html());
         		// alert($(this).parent().siblings("li").find("a").html());//比较
        	 	if($(this).attr("color")!="#0989aa"){
        				$(this).css("color","#0989aa");
        				$(this).parent().siblings("li").find("a").css("color","#000000");
        		}  
        		
        	}); 
         	 //模块切换
         	 $("div ul li").find("a").each(function(i,e){
         		 var $e = $(e);
         		 $e.bind('click',function(){
         			 var $tog = $(".tog:eq("+i+")");
         			$tog.show().siblings("div[class=tog]").hide();
         			$tog.find("table tbody").attr("id","query_result_table_body");
         			$tog.siblings().find("table tbody").attr("id","") ;
         			//切换分页的id
         			//alert($tog.find("span[class='span1']").html());
         			$tog.find("span[class='span1']").attr("id","main_page_nowpage");
         			$tog.find("span[class='span2']").attr("id","main_page_totalpage");
         			$tog.find("span[class='span3']").attr("id","alert_count");
         			
         			//兄弟模块的分页id赋空值
         			$tog.siblings().find("span[class='span1']").attr("id","") ;
         			$tog.siblings().find("span[class='span2']").attr("id","") ;
         			$tog.siblings().find("span[class='span3']").attr("id","") ;
         			
         			//条件中的状态跟着变化
         			if(i==0){$("#condition7").val("未处理");
         			}else if(i==1){
         				$("#condition7").val("确认中");
         			}else if(i==2){
         				$("#condition7").val("已处理");
         			}
         			//图表id切换
         			 blockChange();
         		 });
         		 
         	 });
         
         	 
         	
         	
         	 
 		});
 		function showDetail(flag){
 			if(flag){
 				$("#showImage").slideDown("slow");
 				$("#showLineHealth").slideDown("slow");
 			}else{
 				$("#showImage").slideUp("slow");
 				$("#showLineHealth").slideUp("slow");
 			}
 		}
 		//通知单的浮动效果函数
 		function showDetail_text(flag,a) {
 									
 	          var detailDiv = a.parentNode.getElementsByTagName("div")[0];
 				//var detailDiv = '显示所有的权限';
 	          if (flag) {
 	              detailDiv.style.display = "block";
 	          }
 	          else
 	              detailDiv.style.display = "none";
 	} 
 		function blockChange(){
 			//alert($(".tog:eq("+0+")").css("display")+","+$(".tog:eq("+1+")").css("display")+","+$(".tog:eq("+2+")").css("display"));
 			if($(".tog:eq("+0+")").css("display")==="block"){
					OnMainPageQuery({'warningType':'故障告警','statments':'未处理'});
		 	}
	    	if($(".tog:eq("+1+")").css("display")==="block"){
					OnMainPageQuery({'warningType':'故障告警','statments':'确认中'});
			 	}
	     	if($(".tog:eq("+2+")").css("display")==="block"){
					OnMainPageQuery({'warningType':'故障告警','statments':'已处理'});
			 	}
 		}
 		
 	
	</script>
	</head>
	<body>
	
	
		<div style="float: left;margin-top: 20px; margin-right: 8px; width: 512px; height: 10px; display: inline-block;vertical-align: top;">
		</div>

		<div style="display:block; float: left; width: 100%; height: 8px;"></div>
			<div style="display:block; clear:both;">
  		<!-- 子菜单始 -->
   	  		<div  id="subNavi" style="position:absolute;z-index:100;left:16%;font-size:.85em;"><ul>
		      <li class="list_item">
		        <a  href="javascript:" style="color:#0989aa;" >未处理告警</a>
		      </li>
		      <li class="list_separator">
		        |
		      </li>
		      <li class="list_item">
		        <a href="javascript:;">处理中的告警</a>
		      </li>
		       <li class="list_separator">
		        |
		      </li>
		      <li class="list_item">
		        <a href="javascript:;">处理完成告警</a>
		      </li>
   	  		</ul></div>
   	  		<!-- 子菜单终-->
   	  		
   	  		
   	  
    				<div id="sub_nav" style="line-height: 30px; font-weight: bold; font-size: 15px; color: #424242;">
      	
    				</div>
    				 <div id="main_alert_content" style="height:800px;"><!--主要内容  -->
    				<div id="alert_content_datagrid">
    				     	<div id="alert_content_query_criteria">
      							 <form action="#" method="post">
         							 <div>
            							<div style="display:inline-block;">
             								 <div id="query_criteria" >
               									  <label for="condition1"></label>
               										  <select id="condition1" hidden="hidden" class="combobox" name="criteria[condition1]">
                 										 <option value="">告警类型</option>
                  										<option value="故障告警" selected="selected">故障告警</option>
                  										<option value="故障预警">故障预警</option>
                  										
                									</select> 
                								<label for="condition2"></label>
                									<select id="condition2" class="combobox" name="criteria[condition2]">
                 										 <option value="">所属系统</option>
                  										 <option value="信号系统">信号系统</option>
                  										 <option value="综合监控系统">综合监控系统</option>
                 										
                									</select>
               									 <label for="condition3"></label>
                									<select id="condition3" class="combobox" name="criteria[condition3]">
                  										<option value="">线路</option>
                  										<option value="10">10号线</option>                		
                									</select>
                								<label for="condition4"></label>
                									<select id="condition4" class="combobox" name="criteria[condition4]">
                  										<option value="">车站</option>
                  										<option value="虹桥火车站">虹桥火车站</option>          
                   										<option value="T2航站楼站">T2航站楼站</option>
                   										<option value="T1航站楼站">T1航站楼站</option>
                   										<option value="上海动物园站">上海动物园站</option>
                   										<option value="龙溪路站">龙溪路站</option>
                   										<option value="水城路站">水城路站</option>
                   										<option value="伊犁站">伊犁站</option>
                   										<option value="宋园路站">宋园路站</option>
                   										<option value="虹桥路站">虹桥路站</option>
                   										<option value="上海交大站">上海交大站</option>
                   										<option value="上海图书馆站">上海图书馆站</option>
                   										<option value="陕西路南站">陕西路南站</option>
                   										<option value="新天地站">新天地站</option>
                   										<option value="老西门站">老西门站</option>
                   										<option value="豫园站">豫园站</option>
                   										<option value="南京东路站">南京东路站</option>
                   										<option value="天潼路站">天潼路站</option>
                   										<option value="四川北路站">四川北路站</option>
                   										<option value="海伦路站">海伦路站</option>
                   										<option value="邮电新村站">邮电新村站</option>
                   										<option value="四平路站">四平路站</option>
                   										<option value="四平路站">c</option>
                   										<option value="国权路站 ">国权路站</option>
                   										<option value="五角场站">五角场站</option>
                   										<option value="江湾体育场站">江湾体育场站</option>
                   										<option value="三门路站">三门路站</option>
                   										<option value="殷高东路站">殷高东路站</option>
                   										<option value="新江湾城站">新江湾城站</option>
                   										<option value="航中路站">航中路站</option>
                   										<option value="紫藤路站">紫藤路站</option>
                   										<option value="龙柏新村站">龙柏新村站</option>
                   										<option value="吴中路停车场站">吴中路停车场站</option> 
                   										<option value="控制中心">控制中心</option>                                                             										                  										                            										
                									</select>
                								<label for="condition5"></label>
                									<select id="condition5" class="combobox" name="criteria[condition5]">
                  										<option value="">资产名称</option>
                  										          <option value="转辙机">转辙机</option>
															      <option value="隧道风机">隧道风机</option>
															      <option value="信号机">信号机</option>
															      <option value="开关">开关</option>
															      <option value=" 隧道"> 隧道</option>
															      <option value="轨道">轨道</option>
															      <option value="触网">触网</option>
															      <option value="桥梁 ">桥梁 </option>
                  										
                									</select>
                								<label for="condition6"></label>
                									<select id="condition6" class="combobox" name="criteria[condition6]">
                 				 						<option value="">告警等级</option>
                  										<option value="1">一级</option>
                  										<option value="2">二级</option>
                  										<option value="3">三级</option>
                  										<option value="4">四级</option>
                									</select>
                								<label for="condition7"></label>
                									<select id="condition7" class="combobox" name="criteria[condition7]" style="margin-right:0px;" hidden=hidden;>
                  										<option value="">状态</option>
                  										<option value="未处理">未处理</option>
                  										<option value="确认中">确认中</option>
                  										<option value="已处理">已处理</option>
                  										<option value="已取消">已取消</option>
                									</select>
              								</div>
             								 <div id="time_range">
                								<label for="start_time"><span style="font-weight: normal; font-size: 13px;">开始时间</span></label>
                									<input value ="" id="main_page_start_time" type="date" id="start_time" name="criteria[start_time]" style="height: 21px;" />
                									<div style="width:3%;display: inline-block;"></div>
                								<label for="end_time"><span style="font-weight: normal; font-size: 13px;">结束时间</span></label>
                									<input value="" id="main_page_end_time" type="date" id="end_time" name="criteria[end_time]" style="height: 21px;" />
                									<a style="float: right" class="button_query" onclick="selectData();" >
                									</a>
              								</div>
           								 </div>
          							</div>
       							</form>
      						</div>
    				
 				
 				 
 				 <!-- ************************未处理告警***************************** -->
 				 	<div class="tog" style="display:block;">
    				
      						<div id="alert_content_table">
      							<div id="main_alert_content" style="padding-top: 0px; height: 288px; width:941px; border-bottom: 1px solid gray;" >
        							<div style="background-image: url('../../images/table_background.png');">
          								<table id="query_result_table" border="0" >		
            								<thead id="main_page_thead">
            									<tr id="query_result_table_header">
              										<th scope="col" style="width: 20px;" ></th>
              										<th scope="col" style="background-image: none; width: 20px;">
                									<label for="select_all"></label>
                									<input id="select_all"  type="checkbox" onclick="CheckboxSelect('select_all')" /></th>
              										<th scope="col" >序号</th>
              										<th scope="col" >告警编号</th>
              										<th scope="col" >设备编号</th>
              										<th scope="col" >资产名称</th>
              										<th scope="col" >所属系统</th>
              										<th scope="col" >部件描述</th>
             				 						<th scope="col" >线路</th>
              										<th scope="col" >车站</th>
              										<th scope="col" >告警类型</th>
              										<th scope="col" >告警等级</th>
              										<th scope="col" >告警内容</th>
              										<th scope="col" >告警时间</th>
              										<th scope="col" >状态</th>
              										<th scope="col" ></th>
        							<th scope="col" ></th>
           										 </tr>
           										 
            								</thead >
            								<tbody id="query_result_table_body">
       											
       										
           								 	</tbody>
          								</table>
        							</div>
        						</div>
        					</div>   
        					<div id="query_result_footer">
          						<div id="query_result_footer_current_page">
            						<img src="../../images/previous_triangle.png" onclick="backpage();" />
            							第<span id="main_page_nowpage" class="span1"></span>页
            						<img onclick="nextpage({'statments':'未处理'});" src="../../images/previous_triangle.png" style="transform:rotate(180deg);-webkit-transform:rotate(180deg);" />
          						</div>
          						<div id="query_result_footer_total_page">
          	 							共<span id="main_page_totalpage" class="span2"></span> 页
          						</div>
          						<div style="float: right; padding-right: 20px;">
           							 当前告警总数: <span id="alert_count" class="span3"></span>条
          						</div>
      					</div>	
      					
      					
      			</div>	<!-- 未处理告警结束-->	
      					
      
      						
      			<!-- 处理中的告警 -->
      				<div class="tog" style="display:none">
      						<div id="alert_content_table">
      							<div id="main_alert_content" style="padding-top: 0px; height: 288px; width:941px; border-bottom: 1px solid gray;" >
        							<div style="background-image: url('../../images/table_background.png');">
          								<table id="query_result_table" border="0" >		
            								<thead id="main_page_thead">
            									<tr id="query_result_table_header">
              										<th scope="col" style="width: 20px;" ></th>
              										<th scope="col" style="background-image: none; width: 20px;">
                									<label for="select_all"></label>
                									<input id="select_all"  type="checkbox" onclick="CheckboxSelect('select_all')" /></th>
              										<th scope="col" >序号</th>
              										<th scope="col" >告警编号</th>
              										<th scope="col" >设备编号</th>
              										<th scope="col" >资产名称</th>
              										<th scope="col" >所属系统</th>
              										<th scope="col" >部件描述</th>
             				 						<th scope="col" >线路</th>
              										<th scope="col" >车站</th>
              										<th scope="col" >告警类型</th>
              										<th scope="col" >告警等级</th>
              										<th scope="col" >告警内容</th>
              										<th scope="col" >告警时间</th>
              										<th scope="col" >状态</th>
              										<th scope="col" ></th>
        											<th scope="col" ></th>
           										 </tr>
            								</thead >
            								<tbody id="query_result_table_body">
       											 
           								 	</tbody>
            							</table>
        							</div>
        						</div>
        					</div>   
        					<div id="query_result_footer">
          						<div id="query_result_footer_current_page">
            						<img src="../../images/previous_triangle.png" onclick="backpage();" />
            							第<span id="main_page_nowpage" class="span1"></span>页
            						<img onclick="nextpage({'statments':'确认中'});" src="../../images/previous_triangle.png" style="transform:rotate(180deg);-webkit-transform:rotate(180deg);" />
          						</div>
          						<div id="query_result_footer_total_page">
          	 							共<span id="main_page_totalpage" class="span2"></span> 页
          						</div>
          						<div style="float: right; padding-right: 20px;">
           							 当前告警总数: <span id="alert_count" class="span3"></span>条
          						</div>
          					</div>
          								
          					</div><!-- 处理中的告警结束-->
      				
      				<!-- 处理完成的告警 -->
      				<div class="tog" style="display:none;">
      						<div id="alert_content_table">
      							<div id="main_alert_content" style="padding-top: 0px; height: 288px; width:941px; border-bottom: 1px solid gray;" >
        							<div style="background-image: url('../../images/table_background.png');">
          								<table id="query_result_table" border="0" >		
            								<thead id="main_page_thead">
            									<tr id="query_result_table_header">
              										<th scope="col" style="width: 20px;" ></th>
              										<th scope="col" style="background-image: none; width: 20px;">
                									<label for="select_all"></label>
                									<input id="select_all"  type="checkbox" onclick="CheckboxSelect('select_all')" /></th>
              										<th scope="col" >序号</th>
              										<th scope="col" >告警编号</th>
              										<th scope="col" >设备编号</th>
              										<th scope="col" >资产名称</th>
              										<th scope="col" >所属系统</th>
              										<th scope="col" >部件描述</th>
             				 						<th scope="col" >线路</th>
              										<th scope="col" >车站</th>
              										<th scope="col" >告警类型</th>
              										<th scope="col" >告警等级</th>
              										<th scope="col" >告警内容</th>
              										<th scope="col" >告警时间</th>
              										<th scope="col" >状态</th>
              										<th scope="col" ></th>
        											<th scope="col" ></th>
           										 </tr>
           										 
            								</thead >
            								<tbody id="query_result_table_body">
       											
           								 	</tbody>
          								</table>
        							</div>
        						</div>
        					</div>   
        					<div id="query_result_footer">
          						<div id="query_result_footer_current_page">
            						<img src="../../images/previous_triangle.png" onclick="backpage();" />
            							第<span id="main_page_nowpage" class="span1"></span>页
            						<img onclick="nextpage({'statments':'已处理'});" src="../../images/previous_triangle.png" style="transform:rotate(180deg);-webkit-transform:rotate(180deg);" />
          						</div>
          						<div id="query_result_footer_total_page" class="span2">
          	 							共<span id="main_page_totalpage"></span> 页
          						</div>
          						<div style="float: right; padding-right: 20px;">
           							 当前告警总数: <span id="alert_count" class="span3"></span>条
          						</div>
          					</div>
    					
    					</div><!-- 处理完成的告警完 -->
    					
    			</div><!-- 蓝色衬底 -->
  			</div>
  				 <!-- 统计图 -->
		    		 <div id="chart1" style="position:relative;top:-300px;margin: auto;width: 900px; ">
		    		 <span style="position:relative;top:-20px;left:400px;font-size:.7em;color:#000;">告警数量统计表</span>
			     		 <svg style="height: 250px;"></svg>
	  				 </div>
  				 </div>
  				 
			<div class="TrOverlay"></div>
			
	</body>
</html>
