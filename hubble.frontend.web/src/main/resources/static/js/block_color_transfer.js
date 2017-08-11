$(document).ready(function(){
  $('[data-toggle="tooltip"]').tooltip();
});

function setBlockColor(id,number,idButton){
  var number = number;

  var el = document.getElementById(id);
  el.innerHTML = '';
  el.classList.remove('block-grey');
  el.classList.remove('block-green');
  el.classList.remove('block-yellow');
  el.classList.remove('block-red');
  if(number== null || number<0){
    el.classList.add('block-grey');
    $("#"+id).append("<a id='"+idButton+"' class='icon-center center-block' href='#myModal' data-toggle='modal'><i class='fa fa-exclamation fa-lg' style='color:#f7f7f7;' data-toggle='tooltip' data-placement='bottom' title='Sin informacion' ></i></a>");
    return;
  }
  if(id.split("_")[2] == "performance"){
    number/=1000
  }
  if(number>90){
    el.classList.add('block-green');
    $("#"+id).append("<a id='"+idButton+"' class='icon-center center-block' href='#myModal' data-toggle='modal'><i class='fa fa-check-circle-o fa-lg' style='color:#f7f7f7;' data-toggle='tooltip' data-placement='top' title='Sobre 90% en los ultimos "+id.slice(-2) +" minutos' ></i></a>");
    return;
  }
  if(number<=90 && number>=80){

    el.classList.add('block-yellow');
    $("#"+id).append("<a id='"+idButton+"' class='icon-center center-block' href='#myModal' data-toggle='modal'><i class='fa fa-exclamation-triangle fa-lg' style='color:#f7f7f7;' data-toggle='tooltip' data-placement='bottom' title='Debajo de 90% en los ultimos "+id.slice(-2) +" minutos' ></i></a>");
    return;
  }
  if(number<80){
    el.classList.add('block-red');
    $("#"+id).append("<a id='"+idButton+"' class='icon-center center-block' href='#myModal' data-toggle='modal'><i class='fa fa-times-circle-o fa-lg' style='color:#f7f7f7;' data-toggle='tooltip' data-placement='top' title='Debajo de 80% en los ultimos "+id.slice(-2) +"  minutos' ></i></a>");
    return;
  }
}
