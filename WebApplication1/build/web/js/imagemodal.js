//            $(document).on("click", "#somebutton", function() { 
//                $.get("someservlet", function(responseText) {   
//                    $("#somediv").text(responseText);          
//                });
//            });




//$("#somebutton").click(function() {
//$.ajax({
//    url:"/someservlet",
//    data: {id:$("#somebutton").val()}
//})
// .done(function(response) {
////    alert("do whatever you want to do with the response")
//// });
////});
//
//
//            $(document).on("click", "#somebutton", function() { 
//                $.ajax({
//          
//         type:"GET",                    
//         url:"someservlet",                
//         data: {"get_id":$("#somebutton").val()},                       
//         success:function(data){             
//             
//             $("#somediv").html(data);            
//             
//         }            
//     });
//            });

//$.ajax({
//          
//         type:"GET",                    
//         url:"someservlet",                
//         data: {id:$("#somebutton").val()},                       
//         success:function(data){             
//             
//             $("#somediv").html(data);            
//             
//         }            
//     });






//window.onload = function(){ 
//    // Get the modal
//var modal = document.getElementById("myModal");
//
//// Get the button that opens the modal
//var btn = document.getElementById("myBtn");
//
//// Get the <span> element that closes the modal
//var span = document.getElementsByClassName("close")[0];
//
//// When the user clicks on the button, open the modal
//btn.onclick = function() {
//  modal.style.display = "block";
//}
//
//// When the user clicks on <span> (x), close the modal
//span.onclick = function() {
//  modal.style.display = "none";
//}
//
//// When the user clicks anywhere outside of the modal, close it
//window.onclick = function(event) {
//  if (event.target == modal) {
//    modal.style.display = "none";
//  }
//}
//};



//// Get the button that opens the modal
//var btn = document.querySelectorAll("button.modal-button");
//
//// All page modals
//var modals = document.querySelectorAll('.modal');
//
//// Get the <span> element that closes the modal
//var spans = document.getElementsByClassName("close");
//
//// When the user clicks the button, open the modal
//for (var i = 0; i < btn.length; i++) {
// btn[i].onclick = function(e) {
//    e.preventDefault();
//    modal = document.querySelector(e.target.getAttribute("href"));
//    modal.style.display = "block";
// }
//}
//
//// When the user clicks on <span> (x), close the modal
//for (var i = 0; i < spans.length; i++) {
// spans[i].onclick = function() {
//    for (var index in modals) {
//      if (typeof modals[index].style !== 'undefined') modals[index].style.display = "none";    
//    }
// }
//}
//
//// When the user clicks anywhere outside of the modal, close it
//window.onclick = function(event) {
//    if (event.target.classList.contains('modal')) {
//     for (var index in modals) {
//      if (typeof modals[index].style !== 'undefined') modals[index].style.display = "none";    
//     }
//    }
//}


var modals = document.getElementsByClassName('modal');
var btns = document.getElementsByClassName("openmodal");
var spans=document.getElementsByClassName("close");

for(let i=0;i<btns.length;i++){
   btns[i].onclick = function() {
      modals[i].style.display = "block";
   }
}
for(let i=0;i<spans.length;i++){
    spans[i].onclick = function() {
       modals[i].style.display = "none";
    }
 }
 for(let i=0;i<btns.length;i++){
   modals[i].onclick = function() {
      modals[i].style.display = "none";
   }
}

var modals1 = document.getElementsByClassName('modal1');
var btns1 = document.getElementsByClassName("openmodal1");
var spans1 =document.getElementsByClassName("close1");
var btnsclose1 = document.getElementsByClassName("closemodal");

for(let i=0;i<btns1.length;i++){
   btns1[i].onclick = function() {
      modals1[i].style.display = "block";
   }
}
for(let i=0;i<spans1.length;i++){
    spans1[i].onclick = function() {
       modals1[i].style.display = "none";
    }
 }
 for(let i=0;i<btns1.length;i++){
   modals1[i].onclick = function() {
      modals1[i].style.display = "none";
   }
}
for(let i=0;i<btnsclose1.length;i++){
   btnsclose1[i].onclick = function() {
      modals1[i].style.display = "none";
   }
}



