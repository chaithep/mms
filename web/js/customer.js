/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$('.btn-success').click( function(e) {
    e.preventDefault();
     
    var dataString = $("form.singup-form").serialize();
	alert("data-form :"+dataString);
        
});

// --- DOCUMENT READY ---
$(document).ready(function() {  
    
});

function passConfirm() {
        var pass1 = document.getElementById("inputPassword");
        var pass2 = document.getElementById("inputRePassword");
        var message = document.getElementById('confirmMessage');
    //Set the colors we will be using ...
    var goodColor = "#66cc66";
    var badColor = "#ff6666";
    //Compare the values in the password field 
    //and the confirmation field
    if(pass1.value == pass2.value){
        //The passwords match. 
        //Set the color to the good color and inform
        //the user that they have entered the correct password 
        //pass2.style.backgroundColor = goodColor;
        //message.style.color = goodColor;
        message.innerHTML = "Passwords Match!"
    }else{
        //The passwords do not match.
        //Set the color to the bad color and
        //notify the user.
        //pass2.style.backgroundColor = badColor;
        //message.style.color = badColor;
        message.innerHTML = "Passwords Do Not Match!"
    }
    }

