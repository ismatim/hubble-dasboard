 $(document).ready(function(){
      $('[data-toggle="tooltip"]').tooltip(); 
    });

/*<![CDATA[*/

function setBlockColor(id,number){
  var number = number;
  var el = document.getElementById(id);
  var isNumber = isNaN(number);
  if(!isNaN(id.slice(-2))){
    el.innerHTML = '';
    el.classList.add('block-padding');
  }
  if(number== null || number<0){
    el.classList.add('block-grey');
    checkBlockNumber(id,'block-grey');
    return;
  }
  if(number>90 && !isNumber){
    el.classList.add('block-green');
    checkBlockNumber(id,'block-green');
    return;
  }
  if(number<=90 && number>=80 && !isNumber){
    el.classList.add('block-yellow');
    checkBlockNumber(id,'block-yellow');
    return;
  }
  if(number<80 && !isNumber){
    el.classList.add('block-red');
    checkBlockNumber(id,'block-red');
    return;
  }

}

function checkBlockNumber(id,color){

  var idNum = id.slice(-2);
  if(!isNaN(idNum)){
    switch(color) {
    case "block-grey":
      $("#"+id).append("<a class='icon-center center-block' href='#'><span class='fa fa-exclamation fa-2x' aria-hidden='true' style='color:#f7f7f7' data-toggle='tooltip' data-placement='bottom' title='Sin informacion'></a></span>");        
      break;
    case "block-green":
      $("#"+id).append("<a class='icon-center center-block' href='#'><span class='fa fa-check-circle-o fa-2x' aria-hidden='true' style='color:#f7f7f7' data-toggle='tooltip' data-placement='bottom' title='sobre 90% en los ultimos "+idNum+" minutos'></a></span>");
      break;
    case "block-yellow":
     $("#"+id).append("<a class='icon-center center-block' href='#'><span class='fa fa-exclamation-triangle fa-2x' aria-hidden='true' style='color:#f7f7f7' data-toggle='tooltip' data-placement='bottom' title='Debajo de 90% en los ultimos "+idNum +" minutos'></a></span>");
      break;
    case "block-red":
    $("#"+id).append("<a class='icon-center center-block' href='#'><span class='fa fa-times-circle-o fa-2x' aria-hidden='true' style='color:#f7f7f7' data-toggle='tooltip' data-placement='bottom' title='Debajo de 80% en los ultimos "+idNum +"  minutos'></a></span>");
      break;
    }
    

  }

}
/*]]>*/

  