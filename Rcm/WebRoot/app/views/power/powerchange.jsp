<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<!DOCTYPE div PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
 </HEAD>

<BODY>
<div style="text-align:center;">
	<div style="height: 100px;"></div>
			
		
<div style="text-align:center;">
<div >
	<div>
		<ul class="list">
			<span style="color: #424242">选择角色:</span>
			<select  style="width:165px; height:22px">
				<option>--请选择--</option>
					<option>系统管理员</option>
					<option>COCC值班主任</option>
					<option>COCC维保总调</option>
					<option>COCC总电调</option>
					<option>OCC维保调度员</option>
					<option>OCC电调</option>
					<option>主任工程师</option>
					<option>班组</option>
			</select>
			<br>
			
			<span style="color: #424242">分配权限:</span> 
			<input id="citySel" type="text" readonly value="" style="width:160px;" onClick="showMenu();" />
		</ul>
	</div>
	
	</p>
		<div style="height: 240px;"></div>	
		<tr>
        <td style="padding-left: 250px;">
          <a style=" display: inline-block;vertical-align: middle;" class="cancel" onclick="#;" >
          </a>
          <a style="display: inline-block;vertical-align: middle;" class="button_save" onclick="#;" >
         </a>
        </td>
      </tr>
	
</div>

<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
	<ul id="treeDemo" class="ztree" style="margin-top:0; width:180px; height: 300px;"></ul>
</div>
</div>
</div>
</BODY>
</HTML>