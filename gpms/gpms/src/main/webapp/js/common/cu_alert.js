$(document).ready(function () {

    $(window).on("beforeunload", function() {
       if ( check == true ) {
           
           return "입력한 내용이 저장되지 않습니다.\n"
                  + "정말 종료하시겠습니까?";
           
       }
    });
    
});