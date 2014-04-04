<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date"%>
<!DOCTYPE div PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	</head>
	<body>
		<div class="steps_title">
  <img src="../../images/alert_step2_title.png" />
</div>
<div id="query_result">
  <div style="background-image: url('../../images/table_background.png');">
    <table id="query_result_table" border="0" >
      <caption>
      </caption>
      <thead>
      <tr id="query_result_table_header">
        <th scope="col" >资产编号</th>
        <th scope="col" >资产名称</th>
        <th scope="col" >所属系统</th>
        <th scope="col" >告警类型</th>
        <th scope="col" >告警等级</th>
        <th scope="col" >告警内容</th>
        <th scope="col" >线路</th>
        <th scope="col" >车站</th>
        <th scope="col" >告警时间</th>
        <th scope="col" >状态</th>
        <th scope="col" >健康状况</th>
        <th scope="col" >来自</th>
        <th scope="col" ></th>
      </tr>
      </thead>
       <%
      	Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dataString = format.format(date);
       %>
		
      <tbody id="query_result_table_body">
      <tr style="height: 26px; border-bottom: 1px solid gray">
        <td>1038478392139</td>
        <td>道岔</td>
        <td>信号系统</td>
        <td>故障告警</td>
        <td>一级</td>
        <td>南京东路站道岔无表示报警</td>
        <td>10号线</td>
        <td>南京东路</td>
        <td><%=dataString%></td>
        <td>确认中</td>
        <td>系统生成</td>
        <td>确认</td>
        <td>详情</td>
      </tr>
      </tbody>
    </table>
  </div>

  <form action="#" method="post">
    <div id="active_analysis" style="margin-top: 30px;">
   
      <div id="device_tree_img"  style="width: 250px; " >
	      <label for="img_device_tree" class="label_text">设备树</label><br/>
	      <table>
	      	<tbody>
	      		<tr>
	      			<td valign="top">
	      				<div id="img_device_tree">
	      					<ul id="mal_equipmentTree" class="ztree"></ul>
	      	 			 </div>
	      			</td>
	      		</tr>
	      	</tbody>
	      </table>
      </div>
      
      <div id="online_view" " style="width: 250px; ">
        <label for="img_online_view" class="label_text">在途视图</label><br/>
        <table>
	      	<tbody>
	      		<tr>
	      			<td valign="top">
	      				<div>
        	 				<img src="../../images/_temp1.PNG" style="width: 100%; height: 250px; " />
       					 </div>
	      			</td>
	      		</tr>
	      	</tbody>
	      </table>
      </div>
      
      <div id="malfunction_tree" style="width: 250px; ">
        <label for="img_malfunction_tree" class="label_text">故障树</label><br/>
        	<table>
	      	<tbody>
	      		<tr>
	      			<td valign="top">
	      				<div  id="img_device_tree">
      						<ul id="mal_faultTree" class="ztree"></ul>
      	 				 </div>
	      			</td>
	      		</tr>
	      	</tbody>
	      </table>
      </div>
	
      			
      

      <script type="text/javascript">
        initLineChart('#line_chart svg');
      </script>
	
      <div id="reason" style="width: 100%; display: inline-block;">
        <label for="text_reason" class="label_text">故障可能原因</label><br/>
        <textarea style="background-color: transparent; width: 100%; font-size: 14px;" placeholder="say something." rows="5" cols="1" id="text_reason" name="text_reason">1、定（反）位表示回路室外X2(X3)开路
        </textarea>
      </div>


      <div id="impact" style="width: 100%; display: inline-block;">
        <label for="text_impact" class="label_text">故障影响</label><br/>
        <textarea style="background-color: transparent; width: 100%; font-size: 14px;" placeholder="say something." rows="5" cols="1" id="text_impact" name="text_impact">    道岔无表示可能是定位无表示，也可能是反位无表示，造成的原因可能有多重，不同原因引起的故障所造成的影响可大可小，较大的影响可能会造成列车出轨、列车相撞等，应立刻通知相关人员进行检查。
        </textarea>
      </div>


      <div id="strategy" style="width: 100%; display: inline-block;">
        <label for="text_strategy" class="label_text">维修策略</label><br/>
        <textarea style="background-color: transparent; width: 100%;font-size: 14px;" placeholder="say something." rows="6" cols="1" id="text_strategy" name="text_strategy">1、检查室外二极管是否异常，如异常更换二极管
2、定位时检查室外X2回路电缆是否断开
3、反位时检查室外X3回路电缆是否断开</textarea>
      </div>
    </div>
    <div style="text-align:right; max-width: 100%;margin-top: 20px;" >
      <a class="abort" onclick="OnAbortProcess('check.html');"></a>
      <a class="button_check" onclick="OnCheckProcess('check.html');"></a>
    </div>
    <div style="display:block; clear: both;margin-top: 8px;">
      <div id="alert_info_header">
        <div id="sub_nav" style="line-height: 30px; font-weight: bold; font-size: 15px; color: #424242;">
          消息提示
        </div>
      </div>
      <div id="alert_content" style="padding-top: 0px; height: 208px; border-bottom: 1px solid gray;" >
        <div id="alert_content_datagrid" style="margin: 0px; width: 100%; background: none;">

          <div id="alert_content_table" >
            <div style="background-image: url('/assets/table_background.png');">
              <table id="query_result_table" border="0" style="height: 208px;">
                <caption>
                </caption>
                <thead>
                <tr id="query_result_table_header">

                  <th scope="col" >序号</th>
                  <th scope="col" >消息内容</th>
                  <th scope="col" >时间</th>
                </tr>
                </thead>
                <tbody id="query_result_table_body">
                <tr>
                  <td>1</td>
                  <td>核实员***， 已开始对“告警编号+资产明编号+资产名称+所属系统+告警类型.....”的核实流程。</td>
                  <td>2013-05-20 12：53：12</td>
                </tr>
                <tr style="color: #e4460d;">
                  <td>2</td>
                  <td>核实员***， 已终止对“告警编号+资产明编号+资产名称+所属系统+告警类型.....”的核实流程。</td>
                  <td>2013-05-20 12：53：12</td>
                </tr>
                <tr ></tr>
                <tr ></tr>
                <tr ></tr>
                <tr ></tr>
                <tr ></tr>
                <tr ></tr>
                <tr ></tr>
                <tr ></tr>
                <tr ></tr>
                <tr ></tr>
                <tr ></tr>
                <tr ></tr>
                <tr ></tr>
                <tr ></tr>
                <tr ></tr>
                <tr ></tr>
                <tr ></tr>
                <tr ></tr>
                <tr ></tr>
                <tr ></tr>

                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </form>
  </div>
	</body>
</html>

