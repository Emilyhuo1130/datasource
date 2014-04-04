(function(){var D=this,y,q=D.jQuery,d=D.$,c=D.jQuery=D.$=function(F,E){return new c.fn.init(F,E)
},j=/^[^<]*(<(.|\s)+>)[^>]*$|^#([\w-]+)$/,x=/^.[^:#\[\.,]*$/;
c.fn=c.prototype={init:function(F,I){F=F||document;
if(F.nodeType){this[0]=F;
this.length=1;
this.context=F;
return this
}if(typeof F==="string"){var H=j.exec(F);
if(H&&(H[1]||!I)){if(H[1]){F=c.clean([H[1]],I)
}else{var E=document.getElementById(H[3]);
if(E&&E.id!=H[3]){return c().find(F)
}var G=c(E||[]);
G.context=document;
G.selector=F;
return G
}}else{return c(I).find(F)
}}else{if(c.isFunction(F)){return c(document).ready(F)
}}if(F.selector&&F.context){this.selector=F.selector;
this.context=F.context
}return this.setArray(c.isArray(F)?F:c.makeArray(F))
},selector:"",jquery:"1.3.2",size:function(){return this.length
},get:function(E){return E===y?Array.prototype.slice.call(this):this[E]
},pushStack:function(G,E,F){var H=c(G);
H.prevObject=this;
H.context=this.context;
if(E==="find"){H.selector=this.selector+(this.selector?" ":"")+F
}else{if(E){H.selector=this.selector+"."+E+"("+F+")"
}}return H
},setArray:function(E){this.length=0;
Array.prototype.push.apply(this,E);
return this
},each:function(E,F){return c.each(this,E,F)
},index:function(E){return c.inArray(E&&E.jquery?E[0]:E,this)
},attr:function(G,E,H){var F=G;
if(typeof G==="string"){if(E===y){return this[0]&&c[H||"attr"](this[0],G)
}else{F={};
F[G]=E
}}return this.each(function(I){for(G in F){c.attr(H?this.style:this,G,c.prop(this,F[G],H,I,G))
}})
},css:function(F,E){if((F=="width"||F=="height")&&parseFloat(E)<0){E=y
}return this.attr(F,E,"curCSS")
},text:function(E){if(typeof E!=="object"&&E!=null){return this.empty().append((this[0]&&this[0].ownerDocument||document).createTextNode(E))
}var F="";
c.each(E||this,function(){c.each(this.childNodes,function(){if(this.nodeType!=8){F+=this.nodeType!=1?this.nodeValue:c.fn.text([this])
}})
});
return F
},wrapAll:function(F){if(this[0]){var E=c(F,this[0].ownerDocument).clone();
if(this[0].parentNode){E.insertBefore(this[0])
}E.map(function(){var G=this;
while(G.firstChild){G=G.firstChild
}return G
}).append(this)
}return this
},wrapInner:function(E){return this.each(function(){c(this).contents().wrapAll(E)
})
},wrap:function(E){return this.each(function(){c(this).wrapAll(E)
})
},append:function(){return this.domManip(arguments,true,function(E){if(this.nodeType==1){this.appendChild(E)
}})
},prepend:function(){return this.domManip(arguments,true,function(E){if(this.nodeType==1){this.insertBefore(E,this.firstChild)
}})
},before:function(){return this.domManip(arguments,false,function(E){this.parentNode.insertBefore(E,this)
})
},after:function(){return this.domManip(arguments,false,function(E){this.parentNode.insertBefore(E,this.nextSibling)
})
},end:function(){return this.prevObject||c([])
},push:[].push,sort:[].sort,splice:[].splice,find:function(F){if(this.length===1){var E=this.pushStack([],"find",F);
E.length=0;
c.find(F,this[0],E);
return E
}else{return this.pushStack(c.unique(c.map(this,function(G){return c.find(F,G)
})),"find",F)
}},clone:function(H){var F=this.map(function(){if(!c.support.noCloneEvent&&!c.isXMLDoc(this)){var I=this.outerHTML;
if(!I){var J=this.ownerDocument.createElement("div");
J.appendChild(this.cloneNode(true));
I=J.innerHTML
}return c.clean([I.replace(/ jQuery\d+="(?:\d+|null)"/g,"").replace(/^\s*/,"")])[0]
}else{return this.cloneNode(true)
}});
if(H===true){var E=this.find("*").andSelf(),G=0;
F.find("*").andSelf().each(function(){if(this.nodeName!==E[G].nodeName){return 
}var K=c.data(E[G],"events");
for(var J in K){for(var I in K[J]){c.event.add(this,J,K[J][I],K[J][I].data)
}}G++
})
}return F
},filter:function(E){return this.pushStack(c.isFunction(E)&&c.grep(this,function(G,F){return E.call(G,F)
})||c.multiFilter(E,c.grep(this,function(F){return F.nodeType===1
})),"filter",E)
},closest:function(F){var E=c.expr.match.POS.test(F)?c(F):null,G=0;
return this.map(function(){var H=this;
while(H&&H.ownerDocument){if(E?E.index(H)>-1:c(H).is(F)){c.data(H,"closest",G);
return H
}H=H.parentNode;
G++
}})
},not:function(F){if(typeof F==="string"){if(x.test(F)){return this.pushStack(c.multiFilter(F,this,true),"not",F)
}else{F=c.multiFilter(F,this)
}}var E=F.length&&F[F.length-1]!==y&&!F.nodeType;
return this.filter(function(){return E?c.inArray(this,F)<0:this!=F
})
},add:function(E){return this.pushStack(c.unique(c.merge(this.get(),typeof E==="string"?c(E):c.makeArray(E))))
},is:function(E){return !!E&&c.multiFilter(E,this).length>0
},hasClass:function(E){return !!E&&this.is("."+E)
},val:function(F){if(F===y){var I=this[0];
if(I){if(c.nodeName(I,"option")){return(I.attributes.value||{}).specified?I.value:I.text
}if(c.nodeName(I,"select")){var M=I.selectedIndex,G=[],H=I.options,L=I.type=="select-one";
if(M<0){return null
}for(var J=L?M:0,E=L?M+1:H.length;
J<E;
J++){var K=H[J];
if(K.selected){F=c(K).val();
if(L){return F
}G.push(F)
}}return G
}return(I.value||"").replace(/\r/g,"")
}return y
}if(typeof F==="number"){F+=""
}return this.each(function(){if(this.nodeType!=1){return 
}if(c.isArray(F)&&/radio|checkbox/.test(this.type)){this.checked=(c.inArray(this.value,F)>=0||c.inArray(this.name,F)>=0)
}else{if(c.nodeName(this,"select")){var N=c.makeArray(F);
c("option",this).each(function(){this.selected=(c.inArray(this.value,N)>=0||c.inArray(this.text,N)>=0)
});
if(!N.length){this.selectedIndex=-1
}}else{this.value=F
}}})
},html:function(E){return E===y?(this[0]?this[0].innerHTML.replace(/ jQuery\d+="(?:\d+|null)"/g,""):null):this.empty().append(E)
},replaceWith:function(E){return this.after(E).remove()
},eq:function(E){return this.slice(E,+E+1)
},slice:function(){return this.pushStack(Array.prototype.slice.apply(this,arguments),"slice",Array.prototype.slice.call(arguments).join(","))
},map:function(E){return this.pushStack(c.map(this,function(G,F){return E.call(G,F,G)
}))
},andSelf:function(){return this.add(this.prevObject)
},domManip:function(E,H,G){if(this[0]){var M=(this[0].ownerDocument||this[0]).createDocumentFragment(),J=c.clean(E,(this[0].ownerDocument||this[0]),M),L=M.firstChild;
if(L){for(var K=0,I=this.length;
K<I;
K++){G.call(F(this[K],L),this.length>1||K>0?M.cloneNode(true):M)
}}if(J){c.each(J,r)
}}return this;
function F(N,O){return H&&c.nodeName(N,"table")&&c.nodeName(O,"tr")?(N.getElementsByTagName("tbody")[0]||N.appendChild(N.ownerDocument.createElement("tbody"))):N
}}};
c.fn.init.prototype=c.fn;
function r(F,E){if(E.src){c.ajax({url:E.src,async:false,dataType:"script"})
}else{c.globalEval(E.text||E.textContent||E.innerHTML||"")
}if(E.parentNode){E.parentNode.removeChild(E)
}}function w(){return +new Date
}c.extend=c.fn.extend=function(){var J=arguments[0]||{},G=1,H=arguments.length,I=false,F;
if(typeof J==="boolean"){I=J;
J=arguments[1]||{};
G=2
}if(typeof J!=="object"&&!c.isFunction(J)){J={}
}if(H==G){J=this;
--G
}for(;
G<H;
G++){if((F=arguments[G])!=null){for(var L in F){var K=J[L],E=F[L];
if(J===E){continue
}if(I&&E&&typeof E==="object"&&!E.nodeType){J[L]=c.extend(I,K||(E.length!=null?[]:{}),E)
}else{if(E!==y){J[L]=E
}}}}}return J
};
var t=/z-?index|font-?weight|opacity|zoom|line-?height/i,f=document.defaultView||{},k=Object.prototype.toString;
c.extend({noConflict:function(E){D.$=d;
if(E){D.jQuery=q
}return c
},isFunction:function(E){return k.call(E)==="[object Function]"
},isArray:function(E){return k.call(E)==="[object Array]"
},isXMLDoc:function(E){return E.nodeType===9&&E.documentElement.nodeName!=="HTML"||!!E.ownerDocument&&c.isXMLDoc(E.ownerDocument)
},globalEval:function(E){if(E&&/\S/.test(E)){var G=document.getElementsByTagName("head")[0]||document.documentElement,F=document.createElement("script");
F.type="text/javascript";
if(c.support.scriptEval){F.appendChild(document.createTextNode(E))
}else{F.text=E
}G.insertBefore(F,G.firstChild);
G.removeChild(F)
}},nodeName:function(E,F){return E.nodeName&&E.nodeName.toUpperCase()==F.toUpperCase()
},each:function(G,F,E){var K,H=0,I=G.length;
if(E){if(I===y){for(K in G){if(F.apply(G[K],E)===false){break
}}}else{for(;
H<I;
){if(F.apply(G[H++],E)===false){break
}}}}else{if(I===y){for(K in G){if(F.call(G[K],K,G[K])===false){break
}}}else{for(var J=G[0];
H<I&&F.call(J,H,J)!==false;
J=G[++H]){}}}return G
},prop:function(I,E,H,G,F){if(c.isFunction(E)){E=E.call(I,G)
}return typeof E==="number"&&H=="curCSS"&&!t.test(F)?E+"px":E
},className:{add:function(F,E){c.each((E||"").split(/\s+/),function(G,H){if(F.nodeType==1&&!c.className.has(F.className,H)){F.className+=(F.className?" ":"")+H
}})
},remove:function(F,E){if(F.nodeType==1){F.className=E!==y?c.grep(F.className.split(/\s+/),function(G){return !c.className.has(E,G)
}).join(" "):""
}},has:function(E,F){return E&&c.inArray(F,(E.className||E).toString().split(/\s+/))>-1
}},swap:function(I,H,E){var F={};
for(var G in H){F[G]=I.style[G];
I.style[G]=H[G]
}E.call(I);
for(var G in H){I.style[G]=F[G]
}},css:function(G,L,J,I){if(L=="width"||L=="height"){var F,E={position:"absolute",visibility:"hidden",display:"block"},K=L=="width"?["Left","Right"]:["Top","Bottom"];
function H(){F=L=="width"?G.offsetWidth:G.offsetHeight;
if(I==="border"){return 
}c.each(K,function(){if(!I){F-=parseFloat(c.curCSS(G,"padding"+this,true))||0
}if(I==="margin"){F+=parseFloat(c.curCSS(G,"margin"+this,true))||0
}else{F-=parseFloat(c.curCSS(G,"border"+this+"Width",true))||0
}})
}if(G.offsetWidth!==0){H()
}else{c.swap(G,E,H)
}return Math.max(0,Math.round(F))
}return c.curCSS(G,L,J)
},curCSS:function(M,J,K){var G,I=M.style;
if(J=="opacity"&&!c.support.opacity){G=c.attr(I,"opacity");
return G==""?"1":G
}if(J.match(/float/i)){J=o
}if(!K&&I&&I[J]){G=I[J]
}else{if(f.getComputedStyle){if(J.match(/float/i)){J="float"
}J=J.replace(/([A-Z])/g,"-$1").toLowerCase();
var H=f.getComputedStyle(M,null);
if(H){G=H.getPropertyValue(J)
}if(J=="opacity"&&G==""){G="1"
}}else{if(M.currentStyle){var E=J.replace(/\-(\w)/g,function(N,O){return O.toUpperCase()
});
G=M.currentStyle[J]||M.currentStyle[E];
if(!/^\d+(px)?$/i.test(G)&&/^\d/.test(G)){var L=I.left,F=M.runtimeStyle.left;
M.runtimeStyle.left=M.currentStyle.left;
I.left=G||0;
G=I.pixelLeft+"px";
I.left=L;
M.runtimeStyle.left=F
}}}}return G
},clean:function(L,K,H){K=K||document;
if(typeof K.createElement==="undefined"){K=K.ownerDocument||K[0]&&K[0].ownerDocument||document
}if(!H&&L.length===1&&typeof L[0]==="string"){var G=/^<(\w+)\s*\/?>$/.exec(L[0]);
if(G){return[K.createElement(G[1])]
}}var F=[],J=[],E=K.createElement("div");
c.each(L,function(P,S){if(typeof S==="number"){S+=""
}if(!S){return 
}if(typeof S==="string"){S=S.replace(/(<(\w+)[^>]*?)\/>/g,function(T,U,V){return V.match(/^(abbr|br|col|img|input|link|meta|param|hr|area|embed)$/i)?T:U+"></"+V+">"
});
var O=S.replace(/^\s+/,"").substring(0,10).toLowerCase();
var Q=!O.indexOf("<opt")&&[1,"<select multiple='multiple'>","</select>"]||!O.indexOf("<leg")&&[1,"<fieldset>","</fieldset>"]||O.match(/^<(thead|tbody|tfoot|colg|cap)/)&&[1,"<table>","</table>"]||!O.indexOf("<tr")&&[2,"<table><tbody>","</tbody></table>"]||(!O.indexOf("<td")||!O.indexOf("<th"))&&[3,"<table><tbody><tr>","</tr></tbody></table>"]||!O.indexOf("<col")&&[2,"<table><tbody></tbody><colgroup>","</colgroup></table>"]||!c.support.htmlSerialize&&[1,"div<div>","</div>"]||[0,"",""];
E.innerHTML=Q[1]+S+Q[2];
while(Q[0]--){E=E.lastChild
}if(!c.support.tbody){var R=/<tbody/i.test(S),N=!O.indexOf("<table")&&!R?E.firstChild&&E.firstChild.childNodes:Q[1]=="<table>"&&!R?E.childNodes:[];
for(var M=N.length-1;
M>=0;
--M){if(c.nodeName(N[M],"tbody")&&!N[M].childNodes.length){N[M].parentNode.removeChild(N[M])
}}}if(!c.support.leadingWhitespace&&/^\s/.test(S)){E.insertBefore(K.createTextNode(S.match(/^\s*/)[0]),E.firstChild)
}S=c.makeArray(E.childNodes)
}if(S.nodeType){F.push(S)
}else{F=c.merge(F,S)
}});
if(H){for(var I=0;
F[I];
I++){if(c.nodeName(F[I],"script")&&(!F[I].type||F[I].type.toLowerCase()==="text/javascript")){J.push(F[I].parentNode?F[I].parentNode.removeChild(F[I]):F[I])
}else{if(F[I].nodeType===1){F.splice.apply(F,[I+1,0].concat(c.makeArray(F[I].getElementsByTagName("script"))))
}H.appendChild(F[I])
}}return J
}return F
},attr:function(J,F,L){if(!J||J.nodeType==3||J.nodeType==8){return y
}var G=!c.isXMLDoc(J),E=L!==y;
F=G&&c.props[F]||F;
if(J.tagName){var K=/href|src|style/.test(F);
if(F=="selected"&&J.parentNode){J.parentNode.selectedIndex
}if(F in J&&G&&!K){if(E){if(F=="type"&&c.nodeName(J,"input")&&J.parentNode){throw"type property can't be changed"
}J[F]=L
}if(c.nodeName(J,"form")&&J.getAttributeNode(F)){return J.getAttributeNode(F).nodeValue
}if(F=="tabIndex"){var H=J.getAttributeNode("tabIndex");
return H&&H.specified?H.value:J.nodeName.match(/(button|input|object|select|textarea)/i)?0:J.nodeName.match(/^(a|area)$/i)&&J.href?0:y
}return J[F]
}if(!c.support.style&&G&&F=="style"){return c.attr(J.style,"cssText",L)
}if(E){J.setAttribute(F,""+L)
}var I=!c.support.hrefNormalized&&G&&K?J.getAttribute(F,2):J.getAttribute(F);
return I===null?y:I
}if(!c.support.opacity&&F=="opacity"){if(E){J.zoom=1;
J.filter=(J.filter||"").replace(/alpha\([^)]*\)/,"")+(parseInt(L)+""=="NaN"?"":"alpha(opacity="+L*100+")")
}return J.filter&&J.filter.indexOf("opacity=")>=0?(parseFloat(J.filter.match(/opacity=([^)]*)/)[1])/100)+"":""
}F=F.replace(/-([a-z])/ig,function(M,N){return N.toUpperCase()
});
if(E){J[F]=L
}return J[F]
},trim:function(E){return(E||"").replace(/^\s+|\s+$/g,"")
},makeArray:function(E){var F=[];
if(E!=null){var G=E.length;
if(G==null||typeof E==="string"||c.isFunction(E)||E.setInterval){F[0]=E
}else{while(G){F[--G]=E[G]
}}}return F
},inArray:function(H,E){for(var F=0,G=E.length;
F<G;
F++){if(E[F]===H){return F
}}return -1
},merge:function(I,F){var G=0,H,E=I.length;
if(!c.support.getAll){while((H=F[G++])!=null){if(H.nodeType!=8){I[E++]=H
}}}else{while((H=F[G++])!=null){I[E++]=H
}}return I
},unique:function(F){var E=[],K={};
try{for(var G=0,H=F.length;
G<H;
G++){var J=c.data(F[G]);
if(!K[J]){K[J]=true;
E.push(F[G])
}}}catch(I){E=F
}return E
},grep:function(G,F,E){var H=[];
for(var I=0,J=G.length;
I<J;
I++){if(!E!=!F(G[I],I)){H.push(G[I])
}}return H
},map:function(F,E){var G=[];
for(var H=0,I=F.length;
H<I;
H++){var J=E(F[H],H);
if(J!=null){G[G.length]=J
}}return G.concat.apply([],G)
}});
var h=navigator.userAgent.toLowerCase();
c.browser={version:(h.match(/.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/)||[0,"0"])[1],safari:/webkit/.test(h),opera:/opera/.test(h),msie:/msie/.test(h)&&!/opera/.test(h),mozilla:/mozilla/.test(h)&&!/(compatible|webkit)/.test(h)};
c.each({parent:function(E){return E.parentNode
},parents:function(E){return c.dir(E,"parentNode")
},next:function(E){return c.nth(E,2,"nextSibling")
},prev:function(E){return c.nth(E,2,"previousSibling")
},nextAll:function(E){return c.dir(E,"nextSibling")
},prevAll:function(E){return c.dir(E,"previousSibling")
},siblings:function(E){return c.sibling(E.parentNode.firstChild,E)
},children:function(E){return c.sibling(E.firstChild)
},contents:function(E){return c.nodeName(E,"iframe")?E.contentDocument||E.contentWindow.document:c.makeArray(E.childNodes)
}},function(F,E){c.fn[F]=function(G){var H=c.map(this,E);
if(G&&typeof G=="string"){H=c.multiFilter(G,H)
}return this.pushStack(c.unique(H),F,G)
}
});
c.each({appendTo:"append",prependTo:"prepend",insertBefore:"before",insertAfter:"after",replaceAll:"replaceWith"},function(F,E){c.fn[F]=function(K){var H=[],J=c(K);
for(var I=0,L=J.length;
I<L;
I++){var G=(I>0?this.clone(true):this).get();
c.fn[E].apply(c(J[I]),G);
H=H.concat(G)
}return this.pushStack(H,F,K)
}
});
c.each({removeAttr:function(E){c.attr(this,E,"");
if(this.nodeType==1){this.removeAttribute(E)
}},addClass:function(E){c.className.add(this,E)
},removeClass:function(E){c.className.remove(this,E)
},toggleClass:function(E,F){if(typeof F!=="boolean"){F=!c.className.has(this,E)
}c.className[F?"add":"remove"](this,E)
},remove:function(E){if(!E||c.filter(E,[this]).length){c("*",this).add([this]).each(function(){c.event.remove(this);
c.removeData(this)
});
if(this.parentNode){this.parentNode.removeChild(this)
}}},empty:function(){c(this).children().remove();
while(this.firstChild){this.removeChild(this.firstChild)
}}},function(F,E){c.fn[F]=function(){return this.each(E,arguments)
}
});
function B(F,E){return F[0]&&parseInt(c.curCSS(F[0],E,true),10)||0
}var z="jQuery"+w(),n=0,e={};
c.extend({cache:{},data:function(G,F,H){G=G==D?e:G;
var E=G[z];
if(!E){E=G[z]=++n
}if(F&&!c.cache[E]){c.cache[E]={}
}if(H!==y){c.cache[E][F]=H
}return F?c.cache[E][F]:E
},removeData:function(G,F){G=G==D?e:G;
var E=G[z];
if(F){if(c.cache[E]){delete c.cache[E][F];
F="";
for(F in c.cache[E]){break
}if(!F){c.removeData(G)
}}}else{try{delete G[z]
}catch(H){if(G.removeAttribute){G.removeAttribute(z)
}}delete c.cache[E]
}},queue:function(G,F,E){if(G){F=(F||"fx")+"queue";
var H=c.data(G,F);
if(!H||c.isArray(E)){H=c.data(G,F,c.makeArray(E))
}else{if(E){H.push(E)
}}}return H
},dequeue:function(E,H){var F=c.queue(E,H),G=F.shift();
if(!H||H==="fx"){G=F[0]
}if(G!==y){G.call(E)
}}});
c.fn.extend({data:function(F,H){var E=F.split(".");
E[1]=E[1]?"."+E[1]:"";
if(H===y){var G=this.triggerHandler("getData"+E[1]+"!",[E[0]]);
if(G===y&&this.length){G=c.data(this[0],F)
}return G===y&&E[1]?this.data(E[0]):G
}else{return this.trigger("setData"+E[1]+"!",[E[0],H]).each(function(){c.data(this,F,H)
})
}},removeData:function(E){return this.each(function(){c.removeData(this,E)
})
},queue:function(F,E){if(typeof F!=="string"){E=F;
F="fx"
}if(E===y){return c.queue(this[0],F)
}return this.each(function(){var G=c.queue(this,F,E);
if(F=="fx"&&G.length==1){G[0].call(this)
}})
},dequeue:function(E){return this.each(function(){c.dequeue(this,E)
})
}});
(function(){var G=/((?:\((?:\([^()]+\)|[^()]+)+\)|\[(?:\[[^[\]]*\]|['"][^'"]*['"]|[^[\]'"]+)+\]|\\.|[^ >+~,(\[\\]+)+|[>+~])(\s*,\s*)?/g,P=0,L=Object.prototype.toString;
var J=function(X,T,Y,AB){Y=Y||[];
T=T||document;
if(T.nodeType!==1&&T.nodeType!==9){return[]
}if(!X||typeof X!=="string"){return Y
}var Z=[],V,AE,AH,AI,AC,U,W=true;
G.lastIndex=0;
while((V=G.exec(X))!==null){Z.push(V[1]);
if(V[2]){U=RegExp.rightContext;
break
}}if(Z.length>1&&Q.exec(X)){if(Z.length===2&&M.relative[Z[0]]){AE=N(Z[0]+Z[1],T)
}else{AE=M.relative[Z[0]]?[T]:J(Z.shift(),T);
while(Z.length){X=Z.shift();
if(M.relative[X]){X+=Z.shift()
}AE=N(X,AE)
}}}else{var AD=AB?{expr:Z.pop(),set:I(AB)}:J.find(Z.pop(),Z.length===1&&T.parentNode?T.parentNode:T,F(T));
AE=J.filter(AD.expr,AD.set);
if(Z.length>0){AH=I(AE)
}else{W=false
}while(Z.length){var AG=Z.pop(),AF=AG;
if(!M.relative[AG]){AG=""
}else{AF=Z.pop()
}if(AF==null){AF=T
}M.relative[AG](AH,AF,F(T))
}}if(!AH){AH=AE
}if(!AH){throw"Syntax error, unrecognized expression: "+(AG||X)
}if(L.call(AH)==="[object Array]"){if(!W){Y.push.apply(Y,AH)
}else{if(T.nodeType===1){for(var AA=0;
AH[AA]!=null;
AA++){if(AH[AA]&&(AH[AA]===true||AH[AA].nodeType===1&&O(T,AH[AA]))){Y.push(AE[AA])
}}}else{for(var AA=0;
AH[AA]!=null;
AA++){if(AH[AA]&&AH[AA].nodeType===1){Y.push(AE[AA])
}}}}}else{I(AH,Y)
}if(U){J(U,T,Y,AB);
if(K){hasDuplicate=false;
Y.sort(K);
if(hasDuplicate){for(var AA=1;
AA<Y.length;
AA++){if(Y[AA]===Y[AA-1]){Y.splice(AA--,1)
}}}}}return Y
};
J.matches=function(T,U){return J(T,null,null,U)
};
J.find=function(Z,AB,AA){var Y,W;
if(!Z){return[]
}for(var V=0,U=M.order.length;
V<U;
V++){var X=M.order[V],W;
if((W=M.match[X].exec(Z))){var T=RegExp.leftContext;
if(T.substr(T.length-1)!=="\\"){W[1]=(W[1]||"").replace(/\\/g,"");
Y=M.find[X](W,AB,AA);
if(Y!=null){Z=Z.replace(M.match[X],"");
break
}}}}if(!Y){Y=AB.getElementsByTagName("*")
}return{set:Y,expr:Z}
};
J.filter=function(AC,AB,AF,V){var U=AC,AH=[],AA=AB,Y,AI,Z=AB&&AB[0]&&F(AB[0]);
while(AC&&AB.length){for(var X in M.filter){if((Y=M.match[X].exec(AC))!=null){var T=M.filter[X],AG,AE;
AI=false;
if(AA==AH){AH=[]
}if(M.preFilter[X]){Y=M.preFilter[X](Y,AA,AF,AH,V,Z);
if(!Y){AI=AG=true
}else{if(Y===true){continue
}}}if(Y){for(var W=0;
(AE=AA[W])!=null;
W++){if(AE){AG=T(AE,Y,W,AA);
var AD=V^!!AG;
if(AF&&AG!=null){if(AD){AI=true
}else{AA[W]=false
}}else{if(AD){AH.push(AE);
AI=true
}}}}}if(AG!==y){if(!AF){AA=AH
}AC=AC.replace(M.match[X],"");
if(!AI){return[]
}break
}}}if(AC==U){if(AI==null){throw"Syntax error, unrecognized expression: "+AC
}else{break
}}U=AC
}return AA
};
var M=J.selectors={order:["ID","NAME","TAG"],match:{ID:/#((?:[\w\u00c0-\uFFFF_-]|\\.)+)/,CLASS:/\.((?:[\w\u00c0-\uFFFF_-]|\\.)+)/,NAME:/\[name=['"]*((?:[\w\u00c0-\uFFFF_-]|\\.)+)['"]*\]/,ATTR:/\[\s*((?:[\w\u00c0-\uFFFF_-]|\\.)+)\s*(?:(\S?=)\s*(['"]*)(.*?)\3|)\s*\]/,TAG:/^((?:[\w\u00c0-\uFFFF\*_-]|\\.)+)/,CHILD:/:(only|nth|last|first)-child(?:\((even|odd|[\dn+-]*)\))?/,POS:/:(nth|eq|gt|lt|first|last|even|odd)(?:\((\d*)\))?(?=[^-]|$)/,PSEUDO:/:((?:[\w\u00c0-\uFFFF_-]|\\.)+)(?:\((['"]*)((?:\([^\)]+\)|[^\2\(\)]*)+)\2\))?/},attrMap:{"class":"className","for":"htmlFor"},attrHandle:{href:function(T){return T.getAttribute("href")
}},relative:{"+":function(Z,AB,Y){var W=typeof AB==="string",AA=W&&!/\W/.test(AB),X=W&&!AA;
if(AA&&!Y){AB=AB.toUpperCase()
}for(var V=0,U=Z.length,T;
V<U;
V++){if((T=Z[V])){while((T=T.previousSibling)&&T.nodeType!==1){}Z[V]=X||T&&T.nodeName===AB?T||false:T===AB
}}if(X){J.filter(AB,Z,true)
}},">":function(X,AA,Y){var V=typeof AA==="string";
if(V&&!/\W/.test(AA)){AA=Y?AA:AA.toUpperCase();
for(var T=0,Z=X.length;
T<Z;
T++){var W=X[T];
if(W){var U=W.parentNode;
X[T]=U.nodeName===AA?U:false
}}}else{for(var T=0,Z=X.length;
T<Z;
T++){var W=X[T];
if(W){X[T]=V?W.parentNode:W.parentNode===AA
}}if(V){J.filter(AA,X,true)
}}},"":function(U,Y,W){var T=P++,X=H;
if(!Y.match(/\W/)){var V=Y=W?Y:Y.toUpperCase();
X=E
}X("parentNode",Y,T,U,V,W)
},"~":function(U,Y,W){var T=P++,X=H;
if(typeof Y==="string"&&!Y.match(/\W/)){var V=Y=W?Y:Y.toUpperCase();
X=E
}X("previousSibling",Y,T,U,V,W)
}},find:{ID:function(W,T,U){if(typeof T.getElementById!=="undefined"&&!U){var V=T.getElementById(W[1]);
return V?[V]:[]
}},NAME:function(T,W,X){if(typeof W.getElementsByName!=="undefined"){var Z=[],V=W.getElementsByName(T[1]);
for(var U=0,Y=V.length;
U<Y;
U++){if(V[U].getAttribute("name")===T[1]){Z.push(V[U])
}}return Z.length===0?null:Z
}},TAG:function(T,U){return U.getElementsByTagName(T[1])
}},preFilter:{CLASS:function(U,AA,T,Z,X,Y){U=" "+U[1].replace(/\\/g,"")+" ";
if(Y){return U
}for(var V=0,W;
(W=AA[V])!=null;
V++){if(W){if(X^(W.className&&(" "+W.className+" ").indexOf(U)>=0)){if(!T){Z.push(W)
}}else{if(T){AA[V]=false
}}}}return false
},ID:function(T){return T[1].replace(/\\/g,"")
},TAG:function(T,V){for(var U=0;
V[U]===false;
U++){}return V[U]&&F(V[U])?T[1]:T[1].toUpperCase()
},CHILD:function(T){if(T[1]=="nth"){var U=/(-?)(\d*)n((?:\+|-)?\d*)/.exec(T[2]=="even"&&"2n"||T[2]=="odd"&&"2n+1"||!/\D/.test(T[2])&&"0n+"+T[2]||T[2]);
T[2]=(U[1]+(U[2]||1))-0;
T[3]=U[3]-0
}T[0]=P++;
return T
},ATTR:function(V,Z,T,Y,W,X){var U=V[1].replace(/\\/g,"");
if(!X&&M.attrMap[U]){V[1]=M.attrMap[U]
}if(V[2]==="~="){V[4]=" "+V[4]+" "
}return V
},PSEUDO:function(V,Y,T,X,W){if(V[1]==="not"){if(V[3].match(G).length>1||/^\w/.test(V[3])){V[3]=J(V[3],null,null,Y)
}else{var U=J.filter(V[3],Y,T,true^W);
if(!T){X.push.apply(X,U)
}return false
}}else{if(M.match.POS.test(V[0])||M.match.CHILD.test(V[0])){return true
}}return V
},POS:function(T){T.unshift(true);
return T
}},filters:{enabled:function(T){return T.disabled===false&&T.type!=="hidden"
},disabled:function(T){return T.disabled===true
},checked:function(T){return T.checked===true
},selected:function(T){T.parentNode.selectedIndex;
return T.selected===true
},parent:function(T){return !!T.firstChild
},empty:function(T){return !T.firstChild
},has:function(U,T,V){return !!J(V[3],U).length
},header:function(T){return/h\d/i.test(T.nodeName)
},text:function(T){return"text"===T.type
},radio:function(T){return"radio"===T.type
},checkbox:function(T){return"checkbox"===T.type
},file:function(T){return"file"===T.type
},password:function(T){return"password"===T.type
},submit:function(T){return"submit"===T.type
},image:function(T){return"image"===T.type
},reset:function(T){return"reset"===T.type
},button:function(T){return"button"===T.type||T.nodeName.toUpperCase()==="BUTTON"
},input:function(T){return/input|select|textarea|button/i.test(T.nodeName)
}},setFilters:{first:function(U,T){return T===0
},last:function(T,W,V,U){return W===U.length-1
},even:function(U,T){return T%2===0
},odd:function(U,T){return T%2===1
},lt:function(U,T,V){return T<V[3]-0
},gt:function(U,T,V){return T>V[3]-0
},nth:function(U,T,V){return V[3]-0==T
},eq:function(U,T,V){return V[3]-0==T
}},filter:{PSEUDO:function(X,T,U,Y){var AA=T[1],V=M.filters[AA];
if(V){return V(X,U,T,Y)
}else{if(AA==="contains"){return(X.textContent||X.innerText||"").indexOf(T[3])>=0
}else{if(AA==="not"){var W=T[3];
for(var U=0,Z=W.length;
U<Z;
U++){if(W[U]===X){return false
}}return true
}}}},CHILD:function(AC,V){var Y=V[1],T=AC;
switch(Y){case"only":case"first":while(T=T.previousSibling){if(T.nodeType===1){return false
}}if(Y=="first"){return true
}T=AC;
case"last":while(T=T.nextSibling){if(T.nodeType===1){return false
}}return true;
case"nth":var U=V[2],AB=V[3];
if(U==1&&AB==0){return true
}var X=V[0],AA=AC.parentNode;
if(AA&&(AA.sizcache!==X||!AC.nodeIndex)){var W=0;
for(T=AA.firstChild;
T;
T=T.nextSibling){if(T.nodeType===1){T.nodeIndex=++W
}}AA.sizcache=X
}var Z=AC.nodeIndex-AB;
if(U==0){return Z==0
}else{return(Z%U==0&&Z/U>=0)
}}},ID:function(U,T){return U.nodeType===1&&U.getAttribute("id")===T
},TAG:function(U,T){return(T==="*"&&U.nodeType===1)||U.nodeName===T
},CLASS:function(U,T){return(" "+(U.className||U.getAttribute("class"))+" ").indexOf(T)>-1
},ATTR:function(W,U){var T=U[1],Y=M.attrHandle[T]?M.attrHandle[T](W):W[T]!=null?W[T]:W.getAttribute(T),X=Y+"",V=U[2],Z=U[4];
return Y==null?V==="!=":V==="="?X===Z:V==="*="?X.indexOf(Z)>=0:V==="~="?(" "+X+" ").indexOf(Z)>=0:!Z?X&&Y!==false:V==="!="?X!=Z:V==="^="?X.indexOf(Z)===0:V==="$="?X.substr(X.length-Z.length)===Z:V==="|="?X===Z||X.substr(0,Z.length+1)===Z+"-":false
},POS:function(V,Y,T,W){var X=Y[2],U=M.setFilters[X];
if(U){return U(V,T,Y,W)
}}}};
var Q=M.match.POS;
for(var S in M.match){M.match[S]=RegExp(M.match[S].source+/(?![^\[]*\])(?![^\(]*\))/.source)
}var I=function(U,T){U=Array.prototype.slice.call(U);
if(T){T.push.apply(T,U);
return T
}return U
};
try{Array.prototype.slice.call(document.documentElement.childNodes)
}catch(R){I=function(V,U){var X=U||[];
if(L.call(V)==="[object Array]"){Array.prototype.push.apply(X,V)
}else{if(typeof V.length==="number"){for(var T=0,W=V.length;
T<W;
T++){X.push(V[T])
}}else{for(var T=0;
V[T];
T++){X.push(V[T])
}}}return X
}
}var K;
if(document.documentElement.compareDocumentPosition){K=function(T,V){var U=T.compareDocumentPosition(V)&4?-1:T===V?0:1;
if(U===0){hasDuplicate=true
}return U
}
}else{if("sourceIndex" in document.documentElement){K=function(T,V){var U=T.sourceIndex-V.sourceIndex;
if(U===0){hasDuplicate=true
}return U
}
}else{if(document.createRange){K=function(U,X){var T=U.ownerDocument.createRange(),W=X.ownerDocument.createRange();
T.selectNode(U);
T.collapse(true);
W.selectNode(X);
W.collapse(true);
var V=T.compareBoundaryPoints(Range.START_TO_END,W);
if(V===0){hasDuplicate=true
}return V
}
}}}(function(){var T=document.createElement("form"),U="script"+(new Date).getTime();
T.innerHTML="<input name='"+U+"'/>";
var V=document.documentElement;
V.insertBefore(T,V.firstChild);
if(!!document.getElementById(U)){M.find.ID=function(W,X,Y){if(typeof X.getElementById!=="undefined"&&!Y){var Z=X.getElementById(W[1]);
return Z?Z.id===W[1]||typeof Z.getAttributeNode!=="undefined"&&Z.getAttributeNode("id").nodeValue===W[1]?[Z]:y:[]
}};
M.filter.ID=function(X,Y){var W=typeof X.getAttributeNode!=="undefined"&&X.getAttributeNode("id");
return X.nodeType===1&&W&&W.nodeValue===Y
}
}V.removeChild(T)
})();
(function(){var T=document.createElement("div");
T.appendChild(document.createComment(""));
if(T.getElementsByTagName("*").length>0){M.find.TAG=function(X,W){var V=W.getElementsByTagName(X[1]);
if(X[1]==="*"){var U=[];
for(var Y=0;
V[Y];
Y++){if(V[Y].nodeType===1){U.push(V[Y])
}}V=U
}return V
}
}T.innerHTML="<a href='#'></a>";
if(T.firstChild&&typeof T.firstChild.getAttribute!=="undefined"&&T.firstChild.getAttribute("href")!=="#"){M.attrHandle.href=function(U){return U.getAttribute("href",2)
}
}})();
if(document.querySelectorAll){(function(){var T=J,U=document.createElement("div");
U.innerHTML="<p class='TEST'></p>";
if(U.querySelectorAll&&U.querySelectorAll(".TEST").length===0){return 
}J=function(W,V,Y,Z){V=V||document;
if(!Z&&V.nodeType===9&&!F(V)){try{return I(V.querySelectorAll(W),Y)
}catch(X){}}return T(W,V,Y,Z)
};
J.find=T.find;
J.filter=T.filter;
J.selectors=T.selectors;
J.matches=T.matches
})()
}if(document.getElementsByClassName&&document.documentElement.getElementsByClassName){(function(){var T=document.createElement("div");
T.innerHTML="<div class='test e'></div><div class='test'></div>";
if(T.getElementsByClassName("e").length===0){return 
}T.lastChild.className="e";
if(T.getElementsByClassName("e").length===1){return 
}M.order.splice(1,0,"CLASS");
M.find.CLASS=function(V,W,U){if(typeof W.getElementsByClassName!=="undefined"&&!U){return W.getElementsByClassName(V[1])
}}
})()
}function E(T,Y,X,AC,Z,AB){var AA=T=="previousSibling"&&!AB;
for(var V=0,U=AC.length;
V<U;
V++){var AD=AC[V];
if(AD){if(AA&&AD.nodeType===1){AD.sizcache=X;
AD.sizset=V
}AD=AD[T];
var W=false;
while(AD){if(AD.sizcache===X){W=AC[AD.sizset];
break
}if(AD.nodeType===1&&!AB){AD.sizcache=X;
AD.sizset=V
}if(AD.nodeName===Y){W=AD;
break
}AD=AD[T]
}AC[V]=W
}}}function H(T,Y,X,AC,Z,AB){var AA=T=="previousSibling"&&!AB;
for(var V=0,U=AC.length;
V<U;
V++){var AD=AC[V];
if(AD){if(AA&&AD.nodeType===1){AD.sizcache=X;
AD.sizset=V
}AD=AD[T];
var W=false;
while(AD){if(AD.sizcache===X){W=AC[AD.sizset];
break
}if(AD.nodeType===1){if(!AB){AD.sizcache=X;
AD.sizset=V
}if(typeof Y!=="string"){if(AD===Y){W=true;
break
}}else{if(J.filter(Y,[AD]).length>0){W=AD;
break
}}}AD=AD[T]
}AC[V]=W
}}}var O=document.compareDocumentPosition?function(U,T){return U.compareDocumentPosition(T)&16
}:function(U,T){return U!==T&&(U.contains?U.contains(T):true)
};
var F=function(T){return T.nodeType===9&&T.documentElement.nodeName!=="HTML"||!!T.ownerDocument&&F(T.ownerDocument)
};
var N=function(Z,X){var U=[],V="",W,T=X.nodeType?[X]:X;
while((W=M.match.PSEUDO.exec(Z))){V+=W[0];
Z=Z.replace(M.match.PSEUDO,"")
}Z=M.relative[Z]?Z+"*":Z;
for(var Y=0,AA=T.length;
Y<AA;
Y++){J(Z,T[Y],U)
}return J.filter(V,U)
};
c.find=J;
c.filter=J.filter;
c.expr=J.selectors;
c.expr[":"]=c.expr.filters;
J.selectors.filters.hidden=function(T){return T.offsetWidth===0||T.offsetHeight===0
};
J.selectors.filters.visible=function(T){return T.offsetWidth>0||T.offsetHeight>0
};
J.selectors.filters.animated=function(T){return c.grep(c.timers,function(U){return T===U.elem
}).length
};
c.multiFilter=function(U,V,T){if(T){U=":not("+U+")"
}return J.matches(U,V)
};
c.dir=function(T,W){var V=[],U=T[W];
while(U&&U!=document){if(U.nodeType==1){V.push(U)
}U=U[W]
}return V
};
c.nth=function(V,W,T,U){W=W||1;
var X=0;
for(;
V;
V=V[T]){if(V.nodeType==1&&++X==W){break
}}return V
};
c.sibling=function(U,T){var V=[];
for(;
U;
U=U.nextSibling){if(U.nodeType==1&&U!=T){V.push(U)
}}return V
};
return ;
D.Sizzle=J
})();
c.event={add:function(I,F,H,E){if(I.nodeType==3||I.nodeType==8){return 
}if(I.setInterval&&I!=D){I=D
}if(!H.guid){H.guid=this.guid++
}if(E!==y){var G=H;
H=this.proxy(G);
H.data=E
}var K=c.data(I,"events")||c.data(I,"events",{}),J=c.data(I,"handle")||c.data(I,"handle",function(){return typeof c!=="undefined"&&!c.event.triggered?c.event.handle.apply(arguments.callee.elem,arguments):y
});
J.elem=I;
c.each(F.split(/\s+/),function(L,M){var N=M.split(".");
M=N.shift();
H.type=N.slice().sort().join(".");
var O=K[M];
if(c.event.specialAll[M]){c.event.specialAll[M].setup.call(I,E,N)
}if(!O){O=K[M]={};
if(!c.event.special[M]||c.event.special[M].setup.call(I,E,N)===false){if(I.addEventListener){I.addEventListener(M,J,false)
}else{if(I.attachEvent){I.attachEvent("on"+M,J)
}}}}O[H.guid]=H;
c.event.global[M]=true
});
I=null
},guid:1,global:{},remove:function(L,G,J){if(L.nodeType==3||L.nodeType==8){return 
}var F=c.data(L,"events"),K,I;
if(F){if(G===y||(typeof G==="string"&&G.charAt(0)==".")){for(var H in F){this.remove(L,H+(G||""))
}}else{if(G.type){J=G.handler;
G=G.type
}c.each(G.split(/\s+/),function(M,O){var Q=O.split(".");
O=Q.shift();
var N=RegExp("(^|\\.)"+Q.slice().sort().join(".*\\.")+"(\\.|$)");
if(F[O]){if(J){delete F[O][J.guid]
}else{for(var P in F[O]){if(N.test(F[O][P].type)){delete F[O][P]
}}}if(c.event.specialAll[O]){c.event.specialAll[O].teardown.call(L,Q)
}for(K in F[O]){break
}if(!K){if(!c.event.special[O]||c.event.special[O].teardown.call(L,Q)===false){if(L.removeEventListener){L.removeEventListener(O,c.data(L,"handle"),false)
}else{if(L.detachEvent){L.detachEvent("on"+O,c.data(L,"handle"))
}}}K=null;
delete F[O]
}}})
}for(K in F){break
}if(!K){var E=c.data(L,"handle");
if(E){E.elem=null
}c.removeData(L,"events");
c.removeData(L,"handle")
}}},trigger:function(H,L,G,J){var F=H.type||H;
if(!J){H=typeof H==="object"?H[z]?H:c.extend(c.Event(F),H):c.Event(F);
if(F.indexOf("!")>=0){H.type=F=F.slice(0,-1);
H.exclusive=true
}if(!G){H.stopPropagation();
if(this.global[F]){c.each(c.cache,function(){if(this.events&&this.events[F]){c.event.trigger(H,L,this.handle.elem)
}})
}}if(!G||G.nodeType==3||G.nodeType==8){return y
}H.result=y;
H.target=G;
L=c.makeArray(L);
L.unshift(H)
}H.currentTarget=G;
var I=c.data(G,"handle");
if(I){I.apply(G,L)
}if((!G[F]||(c.nodeName(G,"a")&&F=="click"))&&G["on"+F]&&G["on"+F].apply(G,L)===false){H.result=false
}if(!J&&G[F]&&!H.isDefaultPrevented()&&!(c.nodeName(G,"a")&&F=="click")){this.triggered=true;
try{G[F]()
}catch(E){}}this.triggered=false;
if(!H.isPropagationStopped()){var K=G.parentNode||G.ownerDocument;
if(K){c.event.trigger(H,L,K,true)
}}},handle:function(L){var J,I;
L=arguments[0]=c.event.fix(L||D.event);
L.currentTarget=this;
var F=L.type.split(".");
L.type=F.shift();
J=!F.length&&!L.exclusive;
var H=RegExp("(^|\\.)"+F.slice().sort().join(".*\\.")+"(\\.|$)");
I=(c.data(this,"events")||{})[L.type];
for(var E in I){var G=I[E];
if(J||H.test(G.type)){L.handler=G;
L.data=G.data;
var K=G.apply(this,arguments);
if(K!==y){L.result=K;
if(K===false){L.preventDefault();
L.stopPropagation()
}}if(L.isImmediatePropagationStopped()){break
}}}},props:"altKey attrChange attrName bubbles button cancelable charCode clientX clientY ctrlKey currentTarget data detail eventPhase fromElement handler keyCode metaKey newValue originalTarget pageX pageY prevValue relatedNode relatedTarget screenX screenY shiftKey srcElement target toElement view wheelDelta which".split(" "),fix:function(I){if(I[z]){return I
}var G=I;
I=c.Event(G);
for(var H=this.props.length,F;
H;
){F=this.props[--H];
I[F]=G[F]
}if(!I.target){I.target=I.srcElement||document
}if(I.target.nodeType==3){I.target=I.target.parentNode
}if(!I.relatedTarget&&I.fromElement){I.relatedTarget=I.fromElement==I.target?I.toElement:I.fromElement
}if(I.pageX==null&&I.clientX!=null){var J=document.documentElement,E=document.body;
I.pageX=I.clientX+(J&&J.scrollLeft||E&&E.scrollLeft||0)-(J.clientLeft||0);
I.pageY=I.clientY+(J&&J.scrollTop||E&&E.scrollTop||0)-(J.clientTop||0)
}if(!I.which&&((I.charCode||I.charCode===0)?I.charCode:I.keyCode)){I.which=I.charCode||I.keyCode
}if(!I.metaKey&&I.ctrlKey){I.metaKey=I.ctrlKey
}if(!I.which&&I.button){I.which=(I.button&1?1:(I.button&2?3:(I.button&4?2:0)))
}return I
},proxy:function(E,F){F=F||function(){return E.apply(this,arguments)
};
F.guid=E.guid=E.guid||F.guid||this.guid++;
return F
},special:{ready:{setup:g,teardown:function(){}}},specialAll:{live:{setup:function(F,E){c.event.add(this,E[0],u)
},teardown:function(E){if(E.length){var F=0,G=RegExp("(^|\\.)"+E[0]+"(\\.|$)");
c.each((c.data(this,"events").live||{}),function(){if(G.test(this.type)){F++
}});
if(F<1){c.event.remove(this,E[0],u)
}}}}}};
c.Event=function(E){if(!this.preventDefault){return new c.Event(E)
}if(E&&E.type){this.originalEvent=E;
this.type=E.type
}else{this.type=E
}this.timeStamp=w();
this[z]=true
};
function C(){return false
}function m(){return true
}c.Event.prototype={preventDefault:function(){this.isDefaultPrevented=m;
var E=this.originalEvent;
if(!E){return 
}if(E.preventDefault){E.preventDefault()
}E.returnValue=false
},stopPropagation:function(){this.isPropagationStopped=m;
var E=this.originalEvent;
if(!E){return 
}if(E.stopPropagation){E.stopPropagation()
}E.cancelBubble=true
},stopImmediatePropagation:function(){this.isImmediatePropagationStopped=m;
this.stopPropagation()
},isDefaultPrevented:C,isPropagationStopped:C,isImmediatePropagationStopped:C};
var s=function(G){var F=G.relatedTarget;
while(F&&F!=this){try{F=F.parentNode
}catch(E){F=this
}}if(F!=this){G.type=G.data;
c.event.handle.apply(this,arguments)
}};
c.each({mouseover:"mouseenter",mouseout:"mouseleave"},function(E,F){c.event.special[F]={setup:function(){c.event.add(this,E,s,F)
},teardown:function(){c.event.remove(this,E,s)
}}
});
c.fn.extend({bind:function(G,E,F){return G=="unload"?this.one(G,E,F):this.each(function(){c.event.add(this,G,F||E,F&&E)
})
},one:function(H,E,G){var F=c.event.proxy(G||E,function(I){c(this).unbind(I,F);
return(G||E).apply(this,arguments)
});
return this.each(function(){c.event.add(this,H,F,G&&E)
})
},unbind:function(E,F){return this.each(function(){c.event.remove(this,E,F)
})
},trigger:function(F,E){return this.each(function(){c.event.trigger(F,E,this)
})
},triggerHandler:function(F,E){if(this[0]){var G=c.Event(F);
G.preventDefault();
G.stopPropagation();
c.event.trigger(G,E,this[0]);
return G.result
}},toggle:function(E){var F=arguments,G=1;
while(G<F.length){c.event.proxy(E,F[G++])
}return this.click(c.event.proxy(E,function(H){this.lastToggle=(this.lastToggle||0)%G;
H.preventDefault();
return F[this.lastToggle++].apply(this,arguments)||false
}))
},hover:function(F,E){return this.mouseenter(F).mouseleave(E)
},ready:function(E){g();
if(c.isReady){E.call(document,c)
}else{c.readyList.push(E)
}return this
},live:function(E,G){var F=c.event.proxy(G);
F.guid+=this.selector+E;
c(document).bind(A(E,this.selector),this.selector,F);
return this
},die:function(E,F){c(document).unbind(A(E,this.selector),F?{guid:F.guid+this.selector+E}:null);
return this
}});
function u(E){var F=RegExp("(^|\\.)"+E.type+"(\\.|$)"),H=true,G=[];
c.each(c.data(this,"events").live||[],function(K,I){if(F.test(I.type)){var J=c(E.target).closest(I.data)[0];
if(J){G.push({elem:J,fn:I})
}}});
G.sort(function(J,I){return c.data(J.elem,"closest")-c.data(I.elem,"closest")
});
c.each(G,function(){if(this.fn.call(this.elem,E,this.fn.data)===false){return(H=false)
}});
return H
}function A(E,F){return["live",E,F.replace(/\./g,"`").replace(/ /g,"|")].join(".")
}c.extend({isReady:false,readyList:[],ready:function(){if(!c.isReady){c.isReady=true;
if(c.readyList){c.each(c.readyList,function(){this.call(document,c)
});
c.readyList=null
}c(document).triggerHandler("ready")
}}});
var p=false;
function g(){if(p){return 
}p=true;
if(document.addEventListener){document.addEventListener("DOMContentLoaded",function(){document.removeEventListener("DOMContentLoaded",arguments.callee,false);
c.ready()
},false)
}else{if(document.attachEvent){document.attachEvent("onreadystatechange",function(){if(document.readyState==="complete"){document.detachEvent("onreadystatechange",arguments.callee);
c.ready()
}});
if(document.documentElement.doScroll&&D==D.top){(function(){if(c.isReady){return 
}try{document.documentElement.doScroll("left")
}catch(E){setTimeout(arguments.callee,0);
return 
}c.ready()
})()
}}}c.event.add(D,"load",c.ready)
}c.each(("blur,focus,load,resize,scroll,unload,click,dblclick,mousedown,mouseup,mousemove,mouseover,mouseout,mouseenter,mouseleave,change,select,submit,keydown,keypress,keyup,error").split(","),function(E,F){c.fn[F]=function(G){return G?this.bind(F,G):this.trigger(F)
}
});
c(D).bind("unload",function(){for(var E in c.cache){if(E!=1&&c.cache[E].handle){c.event.remove(c.cache[E].handle.elem)
}}});
(function(){c.support={};
var F=document.documentElement,G=document.createElement("script"),E=document.createElement("div"),K="script"+(new Date).getTime();
E.style.display="none";
E.innerHTML='   <link/><table></table><a href="/a" style="color:red;float:left;opacity:.5;">a</a><select><option>text</option></select><object><param/></object>';
var H=E.getElementsByTagName("*"),J=E.getElementsByTagName("a")[0];
if(!H||!H.length||!J){return 
}c.support={leadingWhitespace:E.firstChild.nodeType==3,tbody:!E.getElementsByTagName("tbody").length,objectAll:!!E.getElementsByTagName("object")[0].getElementsByTagName("*").length,htmlSerialize:!!E.getElementsByTagName("link").length,style:/red/.test(J.getAttribute("style")),hrefNormalized:J.getAttribute("href")==="/a",opacity:J.style.opacity==="0.5",cssFloat:!!J.style.cssFloat,scriptEval:false,noCloneEvent:true,boxModel:null};
G.type="text/javascript";
try{G.appendChild(document.createTextNode("window."+K+"=1;"))
}catch(I){}F.insertBefore(G,F.firstChild);
if(D[K]){c.support.scriptEval=true;
delete D[K]
}F.removeChild(G);
if(E.attachEvent&&E.fireEvent){E.attachEvent("onclick",function(){c.support.noCloneEvent=false;
E.detachEvent("onclick",arguments.callee)
});
E.cloneNode(true).fireEvent("onclick")
}c(function(){var L=document.createElement("div");
L.style.width=L.style.paddingLeft="1px";
document.body.appendChild(L);
c.boxModel=c.support.boxModel=L.offsetWidth===2;
document.body.removeChild(L).style.display="none"
})
})();
var o=c.support.cssFloat?"cssFloat":"styleFloat";
c.props={"for":"htmlFor","class":"className","float":o,cssFloat:o,styleFloat:o,readonly:"readOnly",maxlength:"maxLength",cellspacing:"cellSpacing",rowspan:"rowSpan",tabindex:"tabIndex"};
c.fn.extend({_load:c.fn.load,load:function(G,K,F){if(typeof G!=="string"){return this._load(G)
}var I=G.indexOf(" ");
if(I>=0){var J=G.slice(I,G.length);
G=G.slice(0,I)
}var H="GET";
if(K){if(c.isFunction(K)){F=K;
K=null
}else{if(typeof K==="object"){K=c.param(K);
H="POST"
}}}var E=this;
c.ajax({url:G,type:H,dataType:"html",data:K,complete:function(L,M){if(M=="success"||M=="notmodified"){E.html(J?c("<div/>").append(L.responseText.replace(/<script(.|\s)*?\/script>/g,"")).find(J):L.responseText)
}if(F){E.each(F,[L.responseText,M,L])
}}});
return this
},serialize:function(){return c.param(this.serializeArray())
},serializeArray:function(){return this.map(function(){return this.elements?c.makeArray(this.elements):this
}).filter(function(){return this.name&&!this.disabled&&(this.checked||/select|textarea/i.test(this.nodeName)||/text|hidden|password|search/i.test(this.type))
}).map(function(F,G){var E=c(this).val();
return E==null?null:c.isArray(E)?c.map(E,function(I,H){return{name:G.name,value:I}
}):{name:G.name,value:E}
}).get()
}});
c.each("ajaxStart,ajaxStop,ajaxComplete,ajaxError,ajaxSuccess,ajaxSend".split(","),function(F,E){c.fn[E]=function(G){return this.bind(E,G)
}
});
var i=w();
c.extend({get:function(F,H,E,G){if(c.isFunction(H)){E=H;
H=null
}return c.ajax({type:"GET",url:F,data:H,success:E,dataType:G})
},getScript:function(F,E){return c.get(F,null,E,"script")
},getJSON:function(F,G,E){return c.get(F,G,E,"json")
},post:function(F,H,E,G){if(c.isFunction(H)){E=H;
H={}
}return c.ajax({type:"POST",url:F,data:H,success:E,dataType:G})
},ajaxSetup:function(E){c.extend(c.ajaxSettings,E)
},ajaxSettings:{url:location.href,global:true,type:"GET",contentType:"application/x-www-form-urlencoded",processData:true,async:true,xhr:function(){return D.ActiveXObject?new ActiveXObject("Microsoft.XMLHTTP"):new XMLHttpRequest()
},accepts:{xml:"application/xml, text/xml",html:"text/html",script:"text/javascript, application/javascript",json:"application/json, text/javascript",text:"text/plain",_default:"*/*"}},lastModified:{},ajax:function(Q){Q=c.extend(true,Q,c.extend(true,{},c.ajaxSettings,Q));
var H,J=/=\?(&|$)/g,V,G,K=Q.type.toUpperCase();
if(Q.data&&Q.processData&&typeof Q.data!=="string"){Q.data=c.param(Q.data)
}if(Q.dataType=="jsonp"){if(K=="GET"){if(!Q.url.match(J)){Q.url+=(Q.url.match(/\?/)?"&":"?")+(Q.jsonp||"callback")+"=?"
}}else{if(!Q.data||!Q.data.match(J)){Q.data=(Q.data?Q.data+"&":"")+(Q.jsonp||"callback")+"=?"
}}Q.dataType="json"
}if(Q.dataType=="json"&&(Q.data&&Q.data.match(J)||Q.url.match(J))){H="jsonp"+i++;
if(Q.data){Q.data=(Q.data+"").replace(J,"="+H+"$1")
}Q.url=Q.url.replace(J,"="+H+"$1");
Q.dataType="script";
D[H]=function(X){G=X;
M();
P();
D[H]=y;
try{delete D[H]
}catch(Y){}if(L){L.removeChild(E)
}}
}if(Q.dataType=="script"&&Q.cache==null){Q.cache=false
}if(Q.cache===false&&K=="GET"){var I=w();
var F=Q.url.replace(/(\?|&)_=.*?(&|$)/,"$1_="+I+"$2");
Q.url=F+((F==Q.url)?(Q.url.match(/\?/)?"&":"?")+"_="+I:"")
}if(Q.data&&K=="GET"){Q.url+=(Q.url.match(/\?/)?"&":"?")+Q.data;
Q.data=null
}if(Q.global&&!c.active++){c.event.trigger("ajaxStart")
}var U=/^(\w+:)?\/\/([^\/?#]+)/.exec(Q.url);
if(Q.dataType=="script"&&K=="GET"&&U&&(U[1]&&U[1]!=location.protocol||U[2]!=location.host)){var L=document.getElementsByTagName("head")[0];
var E=document.createElement("script");
E.src=Q.url;
if(Q.scriptCharset){E.charset=Q.scriptCharset
}if(!H){var S=false;
E.onload=E.onreadystatechange=function(){if(!S&&(!this.readyState||this.readyState=="loaded"||this.readyState=="complete")){S=true;
M();
P();
E.onload=E.onreadystatechange=null;
L.removeChild(E)
}}
}L.appendChild(E);
return y
}var O=false;
var N=Q.xhr();
if(Q.username){N.open(K,Q.url,Q.async,Q.username,Q.password)
}else{N.open(K,Q.url,Q.async)
}try{if(Q.data){N.setRequestHeader("Content-Type",Q.contentType)
}if(Q.ifModified){N.setRequestHeader("If-Modified-Since",c.lastModified[Q.url]||"Thu, 01 Jan 1970 00:00:00 GMT")
}N.setRequestHeader("X-Requested-With","XMLHttpRequest");
N.setRequestHeader("Accept",Q.dataType&&Q.accepts[Q.dataType]?Q.accepts[Q.dataType]+", */*":Q.accepts._default)
}catch(W){}if(Q.beforeSend&&Q.beforeSend(N,Q)===false){if(Q.global&&!--c.active){c.event.trigger("ajaxStop")
}N.abort();
return false
}if(Q.global){c.event.trigger("ajaxSend",[N,Q])
}var R=function(X){if(N.readyState==0){if(T){clearInterval(T);
T=null;
if(Q.global&&!--c.active){c.event.trigger("ajaxStop")
}}}else{if(!O&&N&&(N.readyState==4||X=="timeout")){O=true;
if(T){clearInterval(T);
T=null
}V=X=="timeout"?"timeout":!c.httpSuccess(N)?"error":Q.ifModified&&c.httpNotModified(N,Q.url)?"notmodified":"success";
if(V=="success"){try{G=c.httpData(N,Q.dataType,Q)
}catch(Z){V="parsererror"
}}if(V=="success"){var Y;
try{Y=N.getResponseHeader("Last-Modified")
}catch(Z){}if(Q.ifModified&&Y){c.lastModified[Q.url]=Y
}if(!H){M()
}}else{c.handleError(Q,N,V)
}P();
if(X){N.abort()
}if(Q.async){N=null
}}}};
if(Q.async){var T=setInterval(R,13);
if(Q.timeout>0){setTimeout(function(){if(N&&!O){R("timeout")
}},Q.timeout)
}}try{N.send(Q.data)
}catch(W){c.handleError(Q,N,null,W)
}if(!Q.async){R()
}function M(){if(Q.success){Q.success(G,V)
}if(Q.global){c.event.trigger("ajaxSuccess",[N,Q])
}}function P(){if(Q.complete){Q.complete(N,V)
}if(Q.global){c.event.trigger("ajaxComplete",[N,Q])
}if(Q.global&&!--c.active){c.event.trigger("ajaxStop")
}}return N
},handleError:function(G,E,F,H){if(G.error){G.error(E,F,H)
}if(G.global){c.event.trigger("ajaxError",[E,G,H])
}},active:0,httpSuccess:function(E){try{return !E.status&&location.protocol=="file:"||(E.status>=200&&E.status<300)||E.status==304||E.status==1223
}catch(F){}return false
},httpNotModified:function(H,F){try{var E=H.getResponseHeader("Last-Modified");
return H.status==304||E==c.lastModified[F]
}catch(G){}return false
},httpData:function(F,I,H){var G=F.getResponseHeader("content-type"),E=I=="xml"||!I&&G&&G.indexOf("xml")>=0,J=E?F.responseXML:F.responseText;
if(E&&J.documentElement.tagName=="parsererror"){throw"parsererror"
}if(H&&H.dataFilter){J=H.dataFilter(J,I)
}if(typeof J==="string"){if(I=="script"){c.globalEval(J)
}if(I=="json"){J=D["eval"]("("+J+")")
}}return J
},param:function(F){var H=[];
function E(I,J){H[H.length]=encodeURIComponent(I)+"="+encodeURIComponent(J)
}if(c.isArray(F)||F.jquery){c.each(F,function(){E(this.name,this.value)
})
}else{for(var G in F){if(c.isArray(F[G])){c.each(F[G],function(){E(G,this)
})
}else{E(G,c.isFunction(F[G])?F[G]():F[G])
}}}return H.join("&").replace(/%20/g,"+")
}});
var a={},b,v=[["height","marginTop","marginBottom","paddingTop","paddingBottom"],["width","marginLeft","marginRight","paddingLeft","paddingRight"],["opacity"]];
function l(G,F){var E={};
c.each(v.concat.apply([],v.slice(0,F)),function(){E[this]=G
});
return E
}c.fn.extend({show:function(J,F){if(J){return this.animate(l("show",3),J,F)
}else{for(var G=0,L=this.length;
G<L;
G++){var I=c.data(this[G],"olddisplay");
this[G].style.display=I||"";
if(c.css(this[G],"display")==="none"){var E=this[G].tagName,K;
if(a[E]){K=a[E]
}else{var H=c("<"+E+" />").appendTo("body");
K=H.css("display");
if(K==="none"){K="block"
}H.remove();
a[E]=K
}c.data(this[G],"olddisplay",K)
}}for(var G=0,L=this.length;
G<L;
G++){this[G].style.display=c.data(this[G],"olddisplay")||""
}return this
}},hide:function(I,E){if(I){return this.animate(l("hide",3),I,E)
}else{for(var H=0,G=this.length;
H<G;
H++){var F=c.data(this[H],"olddisplay");
if(!F&&F!=="none"){c.data(this[H],"olddisplay",c.css(this[H],"display"))
}}for(var H=0,G=this.length;
H<G;
H++){this[H].style.display="none"
}return this
}},_toggle:c.fn.toggle,toggle:function(E,G){var F=typeof E==="boolean";
return c.isFunction(E)&&c.isFunction(G)?this._toggle.apply(this,arguments):E==null||F?this.each(function(){var H=F?E:c(this).is(":hidden");
c(this)[H?"show":"hide"]()
}):this.animate(l("toggle",3),E,G)
},fadeTo:function(F,E,G){return this.animate({opacity:E},F,G)
},animate:function(E,G,I,H){var F=c.speed(G,I,H);
return this[F.queue===false?"each":"queue"](function(){var M=c.extend({},F),K,J=this.nodeType==1&&c(this).is(":hidden"),L=this;
for(K in E){if(E[K]=="hide"&&J||E[K]=="show"&&!J){return M.complete.call(this)
}if((K=="height"||K=="width")&&this.style){M.display=c.css(this,"display");
M.overflow=this.style.overflow
}}if(M.overflow!=null){this.style.overflow="hidden"
}M.curAnim=c.extend({},E);
c.each(E,function(O,S){var R=new c.fx(L,M,O);
if(/toggle|show|hide/.test(S)){R[S=="toggle"?J?"show":"hide":S](E)
}else{var Q=S.toString().match(/^([+-]=)?([\d+-.]+)(.*)$/),T=R.cur(true)||0;
if(Q){var N=parseFloat(Q[2]),P=Q[3]||"px";
if(P!="px"){L.style[O]=(N||1)+P;
T=((N||1)/R.cur(true))*T;
L.style[O]=T+P
}if(Q[1]){N=((Q[1]=="-="?-1:1)*N)+T
}R.custom(T,N,P)
}else{R.custom(T,S,"")
}}});
return true
})
},stop:function(G,F){var E=c.timers;
if(G){this.queue([])
}this.each(function(){for(var H=E.length-1;
H>=0;
H--){if(E[H].elem==this){if(F){E[H](true)
}E.splice(H,1)
}}});
if(!F){this.dequeue()
}return this
}});
c.each({slideDown:l("show",1),slideUp:l("hide",1),slideToggle:l("toggle",1),fadeIn:{opacity:"show"},fadeOut:{opacity:"hide"}},function(F,E){c.fn[F]=function(G,H){return this.animate(E,G,H)
}
});
c.extend({speed:function(H,E,G){var F=typeof H==="object"?H:{complete:G||!G&&E||c.isFunction(H)&&H,duration:H,easing:G&&E||E&&!c.isFunction(E)&&E};
F.duration=c.fx.off?0:typeof F.duration==="number"?F.duration:c.fx.speeds[F.duration]||c.fx.speeds._default;
F.old=F.complete;
F.complete=function(){if(F.queue!==false){c(this).dequeue()
}if(c.isFunction(F.old)){F.old.call(this)
}};
return F
},easing:{linear:function(H,E,F,G){return F+G*H
},swing:function(H,E,F,G){return((-Math.cos(H*Math.PI)/2)+0.5)*G+F
}},timers:[],fx:function(G,F,E){this.options=F;
this.elem=G;
this.prop=E;
if(!F.orig){F.orig={}
}}});
c.fx.prototype={update:function(){if(this.options.step){this.options.step.call(this.elem,this.now,this)
}(c.fx.step[this.prop]||c.fx.step._default)(this);
if((this.prop=="height"||this.prop=="width")&&this.elem.style){this.elem.style.display="block"
}},cur:function(E){if(this.elem[this.prop]!=null&&(!this.elem.style||this.elem.style[this.prop]==null)){return this.elem[this.prop]
}var F=parseFloat(c.css(this.elem,this.prop,E));
return F&&F>-10000?F:parseFloat(c.curCSS(this.elem,this.prop))||0
},custom:function(E,I,H){this.startTime=w();
this.start=E;
this.end=I;
this.unit=H||this.unit||"px";
this.now=this.start;
this.pos=this.state=0;
var F=this;
function G(J){return F.step(J)
}G.elem=this.elem;
if(G()&&c.timers.push(G)&&!b){b=setInterval(function(){var J=c.timers;
for(var K=0;
K<J.length;
K++){if(!J[K]()){J.splice(K--,1)
}}if(!J.length){clearInterval(b);
b=y
}},13)
}},show:function(){this.options.orig[this.prop]=c.attr(this.elem.style,this.prop);
this.options.show=true;
this.custom(this.prop=="width"||this.prop=="height"?1:0,this.cur());
c(this.elem).show()
},hide:function(){this.options.orig[this.prop]=c.attr(this.elem.style,this.prop);
this.options.hide=true;
this.custom(this.cur(),0)
},step:function(I){var H=w();
if(I||H>=this.options.duration+this.startTime){this.now=this.end;
this.pos=this.state=1;
this.update();
this.options.curAnim[this.prop]=true;
var F=true;
for(var G in this.options.curAnim){if(this.options.curAnim[G]!==true){F=false
}}if(F){if(this.options.display!=null){this.elem.style.overflow=this.options.overflow;
this.elem.style.display=this.options.display;
if(c.css(this.elem,"display")=="none"){this.elem.style.display="block"
}}if(this.options.hide){c(this.elem).hide()
}if(this.options.hide||this.options.show){for(var J in this.options.curAnim){c.attr(this.elem.style,J,this.options.orig[J])
}}this.options.complete.call(this.elem)
}return false
}else{var E=H-this.startTime;
this.state=E/this.options.duration;
this.pos=c.easing[this.options.easing||(c.easing.swing?"swing":"linear")](this.state,E,0,1,this.options.duration);
this.now=this.start+((this.end-this.start)*this.pos);
this.update()
}return true
}};
c.extend(c.fx,{speeds:{slow:600,fast:200,_default:400},step:{opacity:function(E){c.attr(E.elem.style,"opacity",E.now)
},_default:function(E){if(E.elem.style&&E.elem.style[E.prop]!=null){E.elem.style[E.prop]=E.now+E.unit
}else{E.elem[E.prop]=E.now
}}}});
if(document.documentElement.getBoundingClientRect){c.fn.offset=function(){if(!this[0]){return{top:0,left:0}
}if(this[0]===this[0].ownerDocument.body){return c.offset.bodyOffset(this[0])
}var F=this[0].getBoundingClientRect(),J=this[0].ownerDocument,L=J.body,I=J.documentElement,E=I.clientTop||L.clientTop||0,K=I.clientLeft||L.clientLeft||0,H=F.top+(self.pageYOffset||c.boxModel&&I.scrollTop||L.scrollTop)-E,G=F.left+(self.pageXOffset||c.boxModel&&I.scrollLeft||L.scrollLeft)-K;
return{top:H,left:G}
}
}else{c.fn.offset=function(){if(!this[0]){return{top:0,left:0}
}if(this[0]===this[0].ownerDocument.body){return c.offset.bodyOffset(this[0])
}c.offset.initialized||c.offset.initialize();
var N=this[0],K=N.offsetParent,J=N,H=N.ownerDocument,F,L=H.documentElement,O=H.body,E=H.defaultView,I=E.getComputedStyle(N,null),G=N.offsetTop,M=N.offsetLeft;
while((N=N.parentNode)&&N!==O&&N!==L){F=E.getComputedStyle(N,null);
G-=N.scrollTop,M-=N.scrollLeft;
if(N===K){G+=N.offsetTop,M+=N.offsetLeft;
if(c.offset.doesNotAddBorder&&!(c.offset.doesAddBorderForTableAndCells&&/^t(able|d|h)$/i.test(N.tagName))){G+=parseInt(F.borderTopWidth,10)||0,M+=parseInt(F.borderLeftWidth,10)||0
}J=K,K=N.offsetParent
}if(c.offset.subtractsBorderForOverflowNotVisible&&F.overflow!=="visible"){G+=parseInt(F.borderTopWidth,10)||0,M+=parseInt(F.borderLeftWidth,10)||0
}I=F
}if(I.position==="relative"||I.position==="static"){G+=O.offsetTop,M+=O.offsetLeft
}if(I.position==="fixed"){G+=Math.max(L.scrollTop,O.scrollTop),M+=Math.max(L.scrollLeft,O.scrollLeft)
}return{top:G,left:M}
}
}c.offset={initialize:function(){if(this.initialized){return 
}var F=document.body,J=document.createElement("div"),L,K,H,M,G,I,N=F.style.marginTop,E='<div style="position:absolute;top:0;left:0;margin:0;border:5px solid #000;padding:0;width:1px;height:1px;"><div></div></div><table style="position:absolute;top:0;left:0;margin:0;border:5px solid #000;padding:0;width:1px;height:1px;" cellpadding="0" cellspacing="0"><tr><td></td></tr></table>';
G={position:"absolute",top:0,left:0,margin:0,border:0,width:"1px",height:"1px",visibility:"hidden"};
for(I in G){J.style[I]=G[I]
}J.innerHTML=E;
F.insertBefore(J,F.firstChild);
L=J.firstChild,K=L.firstChild,M=L.nextSibling.firstChild.firstChild;
this.doesNotAddBorder=(K.offsetTop!==5);
this.doesAddBorderForTableAndCells=(M.offsetTop===5);
L.style.overflow="hidden",L.style.position="relative";
this.subtractsBorderForOverflowNotVisible=(K.offsetTop===-5);
F.style.marginTop="1px";
this.doesNotIncludeMarginInBodyOffset=(F.offsetTop===0);
F.style.marginTop=N;
F.removeChild(J);
this.initialized=true
},bodyOffset:function(F){c.offset.initialized||c.offset.initialize();
var E=F.offsetTop,G=F.offsetLeft;
if(c.offset.doesNotIncludeMarginInBodyOffset){E+=parseInt(c.curCSS(F,"marginTop",true),10)||0,G+=parseInt(c.curCSS(F,"marginLeft",true),10)||0
}return{top:E,left:G}
}};
c.fn.extend({position:function(){var J=0,I=0,G;
if(this[0]){var H=this.offsetParent(),F=this.offset(),E=/^body|html$/i.test(H[0].tagName)?{top:0,left:0}:H.offset();
F.top-=B(this,"marginTop");
F.left-=B(this,"marginLeft");
E.top+=B(H,"borderTopWidth");
E.left+=B(H,"borderLeftWidth");
G={top:F.top-E.top,left:F.left-E.left}
}return G
},offsetParent:function(){var E=this[0].offsetParent||document.body;
while(E&&(!/^body|html$/i.test(E.tagName)&&c.css(E,"position")=="static")){E=E.offsetParent
}return c(E)
}});
c.each(["Left","Top"],function(G,F){var E="scroll"+F;
c.fn[E]=function(H){if(!this[0]){return null
}return H!==y?this.each(function(){this==D||this==document?D.scrollTo(!G?H:c(D).scrollLeft(),G?H:c(D).scrollTop()):this[E]=H
}):this[0]==D||this[0]==document?self[G?"pageYOffset":"pageXOffset"]||c.boxModel&&document.documentElement[E]||document.body[E]:this[0][E]
}
});
c.each(["Height","Width"],function(J,H){var F=J?"Left":"Top",I=J?"Right":"Bottom",G=H.toLowerCase();
c.fn["inner"+H]=function(){return this[0]?c.css(this[0],G,false,"padding"):null
};
c.fn["outer"+H]=function(K){return this[0]?c.css(this[0],G,false,K?"margin":"border"):null
};
var E=H.toLowerCase();
c.fn[E]=function(K){return this[0]==D?document.compatMode=="CSS1Compat"&&document.documentElement["client"+H]||document.body["client"+H]:this[0]==document?Math.max(document.documentElement["client"+H],document.body["scroll"+H],document.documentElement["scroll"+H],document.body["offset"+H],document.documentElement["offset"+H]):K===y?(this.length?c.css(this[0],E):null):this.css(E,typeof K==="string"?K:K+"px")
}
})
})();