/**
 * Created by changwang.song on 2017/12/26.
 */
function extend(obj1,obj2){
    for(var attr in obj2){
        obj1[attr] =  obj2[attr];
    }
}
var projectStageCur=0;

function SetStep(arg){
    this.body=document.body;
    this.opt = {
        show:false,
        content:'.stepCont',
        pageCont:'#pageCont',
        imgWidth:20,
        stepContainerMar:20,
        nextBtn:'.nextBtn',
        prevBtn:'.prevBtn',
       steps:arg.steps,
        //pageClass:'',//分页的类或则id
        stepCounts:5,//总共的步骤数
       curStep:arg.curStep,//当前显示第几页
        animating:false,
        showBtn:true,//是否生成上一步下一步操作按钮
        clickAble:true,//是否可以通过点击进度条的节点操作进度
        onLoad: function(){

        }
    }
     projectStageCur = arg.curStep; //记录项目的当前状态
    //console.dir(arg.curStep)
    this.init(arg)
}
//初始化 生成页面的进度条和按钮
SetStep.prototype.init=function(arg){
    var _that=this;
    extend(this.opt,arg);
    this.opt.stepCounts=this.opt.steps.length;
    this.content=$(this.opt.content);
    this.pageCont=this.content.find(this.opt.pageCont)
    var w_con=$(this.content).width();
    var w_li=(w_con-this.opt.stepContainerMar*2)/this.opt.stepCounts/2;
    var stepContainer=this.content.find('.ystep-container');
    this.stepContainer=stepContainer;
    var stepsHtml=$("<ul class='ystep-container-steps'></ul>");
    var stepDisc = "<li class='ystep-step ystep-step-undone' title='Click on the stage for details'></li>";
    var stepP=$("<div class='ystep-progress'>"+
                "<p class='ystep-progress-bar'><span class='ystep-progress-highlight' style='width:0%'></span></p>"+
            "</div>");
    //注释自动生成的按钮
    // var stepButtonHtml =$( "<div class='step-button'><button type='button' class='btn btn-default prevBtn' id='prevBtn' class='prevBtn'>上一步</button>"+
    //                     "<button type='button' class='btn btn-default nextBtn' id='nextBtn' class='nextBtn'>下一步</button></div>");
    stepP.css('width',w_li*2*(this.opt.stepCounts-1));
    stepP.find('.ystep-progress-bar').css('width',w_li*2*(this.opt.stepCounts-1))
    for(var i=0;i<this.opt.stepCounts;i++){
        if(i==0){
            var _s=$(stepDisc).html("<label class='stageLabel'>"+this.opt.steps[i]+"</label>").addClass('')
        }else{
            var _s=$(stepDisc).html("<label class='stageLabel'>"+this.opt.steps[i]+"</label>")
        }
        // console.dir(_s);
        stepsHtml.append(_s);
    }
    stepsHtml.find('li').css('width','100px').css('height','50px').css('marginRight',w_li*2-100)
    stepContainer.append(stepsHtml).append(stepP);

    stepContainer.css('left',(w_con-stepP.width()-this.opt.imgWidth-10-this.opt.stepContainerMar*2)/2)
    this.content.css('overflow','hidden')
    this.setProgress(this.stepContainer,this.opt.curStep,this.opt.stepCounts)
    //判断参数 是否显示按钮 并绑定点击事件
    if(this.opt.showBtn){
       // this.content.append(stepButtonHtml) //注释自动添加按钮
        this.prevBtn=this.content.find(this.opt.prevBtn)
        this.nextBtn=this.content.find(this.opt.nextBtn)
        this.prevBtn.on('click',function(){
            // if($(this).hasClass('handleAble')){
            if($(_that).attr('disabled')||_that.opt.animating){
                return false;
            }else{
                _that.opt.animating=true;
                _that.opt.curStep--;
                _that.setProgress(_that.stepContainer,_that.opt.curStep,_that.opt.stepCounts)
            }
        })
        this.nextBtn.on('click',function(){
            // if($(this).hasClass('handleAble')){
            if($(_that).attr('disabled')||_that.opt.animating){
                return false;
            }else{
                _that.opt.animating=true;
                _that.opt.curStep++;
                _that.setProgress(_that.stepContainer,_that.opt.curStep,_that.opt.stepCounts)
            }
        })
    }
    //判断时候可点击进度条 并绑定点击事件
    if(this.opt.clickAble){
        //console.dir(_that.opt.curStep +"/////"+_that.opt.stepCounts);
        stepsHtml.find('li').on('click',function(){
         //   console.dir($(this).index() + 1 +">>>>"+projectStageCur);

            if($(this).index()>=projectStageCur){ //之后的步骤不允许点击查看

            }else{
                _that.opt.curStep=$(this).index()+1;
                _that.setProgress(_that.stepContainer,_that.opt.curStep,_that.opt.stepCounts)

            }
        })
    }
     $(window).resize(function(){
        var w_con=$(_that.content).width();
        var w_li=w_con/_that.opt.stepCounts/2;
        stepP.css('width',w_li*2*(_that.opt.stepCounts-1));
        stepP.find('.ystep-progress-bar').css('width',w_li*2*(_that.opt.stepCounts-1))
        stepsHtml.find('li').css('width','100px').css('marginRight',w_li*2-100)
        stepContainer.css('left',(w_con-stepP.width()-_that.opt.imgWidth-10-_that.opt.stepContainerMar*2)/2)
     })
}
//设置进度条
SetStep.prototype.setProgress=function(n,curIndex,stepsLen){
      var _that=this;
        //获取当前容器下所有的步骤
        var $steps = $(n).find("li");
        var $progress =$(n).find(".ystep-progress-highlight");
        //判断当前步骤是否在范围内
        if(1<=curIndex && curIndex<=$steps.length){
          //更新进度
          var scale = "%";
          scale = Math.round((curIndex-1)*100/($steps.length-1))+scale;
          $progress.animate({
            width: scale
          },{
            speed: 1000,
            done: function() {
              //移动节点
              $steps.each(function(j,m){
                var _$m = $(m);
                var _j = j+1;
                if(_j < curIndex){
                  _$m.attr("class","ystep-step-done");
                }else if(_j === curIndex){
                  _$m.attr("class","ystep-step-active");
                }else if(_j > curIndex){
                  _$m.attr("class","ystep-step-undone");
                }
              })
              if(_that.opt.showBtn){
                  if(curIndex==1){
                      _that.prevBtn.attr('disabled','true')
                      _that.nextBtn.removeAttr('disabled')
                  }else if(curIndex==stepsLen){
                      _that.prevBtn.removeAttr('disabled')
                      _that.nextBtn.attr('disabled','true')
                  }else if(1<curIndex<stepsLen){
                      _that.prevBtn.removeAttr('disabled')
                      _that.nextBtn.removeAttr('disabled')
                  }
              }
               _that.checkPage(_that.pageCont,_that.opt.curStep,_that.opt.stepCounts)
               _that.opt.animating=false;
            }
          });  
        }else{
            return false;
        }
}
//改变 分页显示
SetStep.prototype.checkPage=function(pageCont,curStep,steps){
    for(var i = 1; i <= steps; i++){
        // console.dir(i + "--------" + curStep);
            if(i === curStep){
              // pageCont.find
                $('#stage_'+i).css("display","block");
              // console.log( $('#stage_'+i).text())
            }else{
              // pageCont.find
                $('#stage_'+i).css("display","none");
            }
    }
}