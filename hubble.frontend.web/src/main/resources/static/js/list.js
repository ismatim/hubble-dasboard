$( document ).ready(function() {
    
    initEvents();
    
});

function initEvents() {
    
    $(".list").hover(function(){
        
        $(".list li:first span").stop().animate({borderWidth: "5", backgroundColor: "#3f3659", color: "#e5e3e8"},{duration: 170, complete: function() {}} ); 
        
    }, function () {
        
        $(".list li:first span").stop().animate({borderWidth: "2", backgroundColor: "#201c2b", color: "#b8b5c0"},{duration: 170, complete: function() {}} ); 

    });
    
}

function animateTable(e) { 
    
    
    
    
}