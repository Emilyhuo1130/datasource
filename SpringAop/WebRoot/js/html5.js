var i=2;
function oninput(){
	$("#select1").append("<option>第"+i+"页</option>");
	i++;
}


function getimage(){
		/**圆圈图***/
				var c=document.getElementById("myCanvas");
				var cxt=c.getContext("2d");
				cxt.fillStyle="#E0E0FF";//圆圈填充色
				//cxt.strokeStyle="#EA5145";
				cxt.beginPath();
				cxt.arc(300,140,120,0,Math.PI*2,true);
				cxt.closePath();
				cxt.fill();
				
				var c2=document.getElementById("myCanvas");
				var cxt2=c2.getContext("2d");
				cxt2.font = "normal 13px Arial";
				
				for(var i=1;i<=12;i++){
					if(i%10==0){
						cxt2.fillStyle="#FF0000";
					}else if(i%4==0){
						cxt2.fillStyle="#ffc000";
					}else if(i%3==0){
						cxt2.fillStyle="#558ED5";
					}else if(i%2==0){
						cxt2.fillStyle="#00B050";
					}else{
						cxt2.fillStyle="#00B050";
					}
						cxt2.beginPath();
						//cxt2.strokeStyle="#E0E0FF";
						cxt2.arc(300+120*Math.cos((Math.PI/6)*i),140+120*Math.sin((Math.PI/6)*i),10,0,Math.PI*2,true);
						cxt2.closePath();
						cxt2.fill();
						
						cxt2.fillStyle="black";
						cxt2.fillText("   "+i+"号线",300+120*Math.cos((Math.PI/6)*i),140+120*Math.sin((Math.PI/6)*i));
				}
				
			var g=document.getElementById("myCanvas");
			var image1=g.getContext("2d");
			for(var i=1;i<=4;i++){
			
			if(i==1){
				image1.fillStyle="#FF0000";
			}else if(i==2){
				image1.fillStyle="#ffc000";
			}else if(i==3){
				image1.fillStyle="#558ED5";
			}else if(i==4){
				image1.fillStyle="#00B050";
			
			}
				image1.beginPath();
				image1.arc(600,40+40*i,8,0,Math.PI*2,true);
 				image1.closePath();
				image1.fill();
				
			}
			image1.fillStyle="black";
			image1.font="normal 13px Arial";
			image1.fillText("代表运营质量很差",630,44+40);
			image1.fillText("代表运营质量一般",630,44+80);
			image1.fillText("代表运营质量良好",630,44+120);
			image1.fillText("代表运营质量健康",630,44+160);
			
				 
}

/**用划线的方法画园*/
function getLine(){
	var c=document.getElementById("myCanvas2");
		var cxt=c.getContext("2d");
		cxt.beginPath();//开始画图
		cxt.moveTo(50,50);
		cxt.lineTo(50,80);
		cxt.lineTo(30,100);
		cxt.strokeStyle="#FF0000";
		cxt.lineWidth=3.0;
		cxt.stroke();//绘色。画图结束
		
		
		cxt.beginPath();
		cxt.moveTo(150,100);
		for(var i=0;i<360;i++){
			cxt.lineTo(100+50*Math.cos((Math.PI/180)*i),100-50*Math.sin((Math.PI/180)*i));
		}
		cxt.strokeStyle="#558ED5";
		cxt.lineWidth=1.0;
		cxt.stroke();
	
	
}

function getRect(){
	var c=document.getElementById("myCanvas3");
	var cxt=c.getContext("2d");
	cxt.fillRect(10,10,300,200);
	
	cxt.strokeRect(400,10,700,300);
	
}
