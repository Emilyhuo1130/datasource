/*!
 * jquery.msg.js
 * @author 云淡然 http://qianduanblog.com
 * @versio 1.0
 * @for ie9+、chrome、firefox
 * 2013年10月23日15:37:04
 */
(function(e,g,c){
	var f="jquery_msg____",
	i=0,
	d={css:"../jsp/css/jquerymsg.css",
			duration:123,msg:"等待...",
			type:"info",
			position:{}
	};
	e.extend({msg:function(){
		var j=arguments,k=j.length;
		if(k==1&&h(j[0])){
			b({msg:j[0]});
			}else{if(k==2&&e.type(j[0])=="string"&&h(j[1])){
				b({type:j[0],msg:j[1]});
				}else{if(k==1&&e.type(j[0])=="object"){
					b(j[0]);}}}}});e.msg.defaults=d;
	function h(j){return e.type(j)=="string"||e.type(j)=="number";
	}
	
	function b(l){
		var t=e.extend({},d,l),k=f+new Date().getTime(),
		r=e('<div style="display:none" class="'+f+" "+f+t.type+'" id="'+k+'"><div class="'+f+'body">'+t.msg+"</div></div>").appendTo("body"),q=e(window).width(),m=e(window).height(),n=0,j=0,s=0,o=0;r.data("timer",0);
		r.data("options",t);
		if(!i){
			e('<link rel="stylesheet" href="'+t.css+'" />').appendTo("head").load(function(){i=1;p();});}else{p();
			}function p(){
				if(t.position.left===c){n=r.width();s=(q-n)/2;if(s<0){s=0;}
				}else{s=t.position.left;
				}if(t.position.top===c){j=r.height();o=(m-j)/2;
				if(o<0){o=0;}
				}else{o=t.position.top;}r.css({left:s,top:o}).fadeIn(t.duration);a(r);}r.hover(
						function(){a(r,true);},
						function(){a(r);});}
	                    function a(j,k){var m=j.data("timer"),l=j.data("options");
	                    if(m){clearTimeout(m);m=0;
	                    }if(!k){m=setTimeout(
	                    		function(){j.fadeOut(l.duration,function(){e(this).remove();});},3000);
	                    j.data("timer",m);}}})(jQuery,this);