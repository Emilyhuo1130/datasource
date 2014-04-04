<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
    <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<html>
  <head>
    
    <title>日志查看</title>
      <script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
       <script type="text/javascript">
       $(document).ready(function(){
    		   jQuery(".news .mCon_btn").hover(function(){
    			   jQuery(this).parent().find(".mCon_btn").removeClass("mCon_Cutbtn");
    			   jQuery(this).addClass("mCon_Cutbtn");
    			   var cutNum=jQuery(this).parent().find(".mCon_btn").index(this);
    			   jQuery(this).parents(".news").find(".mCon_conTxt").hide();
    			   jQuery(this).parents(".news").find(".mCon_conTxt").eq(cutNum).show();
    			   
    		   });

       });
       
       
       </script>
      

  </head>
  
  <body>
    
     <div class="news">
        <div id="title">
          <ul>
            <li class="mCon_btn mCon_Cutbtn"><a href="#" ><p>媒体报道</p></a><>
            <li class="mCon_btn"><a href="#"><p>公司动态</p></a><>
            <li class="mCon_btn"><a href="#"><p>健康养生</p></a><>
          </ul>
        </div>
        
          <div class="mCon_conTxt">
             <div id="src1"><span style="color:#F00">推荐：</span><a href="/baodao/19.html">【中国经济网】首批假冒艾菲迪</a>...</div>                 
               <div id="src2">　　自2012年6月份在网络直销以来，其卓越的效果受到了全国各地消费者的青睐。艾菲迪...</div>
                <div id="pic9"></div>
                <div id="src1"><a href="ys/340.html">我们应积极参与并推广中国粥养生文</a>...</div>
                  <div id="src2">　　深入挖掘《粥谱》等传统饮食文献资源，为当前人类健康事业服务，创造更多的社会...</div>
                  <div id="pic9"></div>
                  <div id="src1"><a href="ys/339.html">艾菲迪克建议平时要养成正确的饮粥</a>...</div>
                 <div id="src2">　　随着我们物质生活水平的不断提高，饮食的多样化发展，使得粥在我们饮食生活中的...</div>
             <div id="pic9"></div>
               <div id="src1"><a href="ys/338.html">《粥谱》中各类粥方比例统计</a>...</div>
           <div id="src2">　　粥是饮食中最简也是最繁的工艺，既是食膳也是药膳。按养生理论，在制粥过程中，...</div>
             <div id="pic9"></div>
          <div id="src1"><a href="ys/337.html">用粥养生取材需遍及"谷、果、畜、</a>...</div>
         <div id="src2">　　民以食为天。自古以来，先民们就已经知道食物在填饱肚子的同时，不仅具有补充营...</div>          
          <div id="pic9"></div>         
          </div>
          
          <div class="mCon_conTxt" style="display:none;">
           <div id="src1"><span style="color:#F00">推荐：</span><a href="/baodao/19.html">【中国经济网】首批假冒艾菲迪</a>...</div>                 
               <div id="src2">　　自2012年6月份在网络直销以来，其卓越的效果受到了全国各地消费者的青睐。艾菲迪...</div>
                <div id="pic9"></div>
                <div id="src1"><a href="ys/340.html">我们应积极参与并推广中国粥养生文</a>...</div>
                  <div id="src2">　　深入挖掘《粥谱》等传统饮食文献资源，为当前人类健康事业服务，创造更多的社会...</div>
                  <div id="pic9"></div>
          </div>
          
          <div class="mCon_conTxt"  style="display:none;">
            <div id="src1"><span style="color:#F00">推荐：</span><a href="/baodao/19.html">【中国经济网】首批假冒艾菲迪</a>...</div>                 
               <div id="src2">　　自2012年6月份在网络直销以来，其卓越的效果受到了全国各地消费者的青睐。艾菲迪...</div>
                <div id="pic9"></div>
                <div id="src1"><a href="ys/340.html">我们应积极参与并推广中国粥养生文</a>...</div>
                  <div id="src2">　　深入挖掘《粥谱》等传统饮食文献资源，为当前人类健康事业服务，创造更多的社会...</div>
                  <div id="pic9"></div>
                  <div id="src1"><a href="ys/340.html">我们应积极参与并推广中国粥养生文</a>...</div>
                  <div id="src2">　　深入挖掘《粥谱》等传统饮食文献资源，为当前人类健康事业服务，创造更多的社会...</div>
                  <div id="pic9"></div>
                  
          </div>          
      </div>

      
  </body>
</html>
