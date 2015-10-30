/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var webapp = "http://172.16.70.100/mxerve/";
//var webapp = "/mxerve/";

var ActUnit= [];

$('.logout').click( function(e) {
    e.preventDefault();
    
    localStorage.removeItem('role');
    localStorage.removeItem('current-src');
    window.location.href = "/";
    
}); 

$('.btn-signin').click( function(e) {
    e.preventDefault();
     
    var dataString = $("form.login-form").serialize();
	//alert("data-form :"+dataString);
   
    $.ajax({
            type:'GET',
            url: webapp+"user/login" ,
            data: dataString,
			
            contentType: "application/json",
            
		success: function (data) {
                                
                if (data.status){
                    user = data.data;
                    localStorage.setItem('role',user);
                   
                    window.location.href = "home.html";
                } else {
                    //alert("data :"+data.msg);
                    
                    document.getElementById('msg').innerHTML = data.msg;
		    console.log(data);
                }
            }
        });
});

$('.btn-user-edit').click( function(e) {
    e.preventDefault();
    
    var role = JSON.parse(localStorage.getItem('role'));
    var dataString = $("form.user-profile-form").serialize();	
    var idupdate = role.iduser;
        //alert(" data-form-edit c :"+dataString+" id : "+idupdate);
    $.ajax({
            type: "PUT",
            url: webapp+"user/"+idupdate,
            data: dataString,
			
            contentType: "application/x-www-form-urlencoded",
            
            success: function (data) {
                console.log("data :"+JSON.stringify(data));
                
		if (data.status){
                    window.location.href = "home.html";
                } else {
                    console.log(data.msg);
                }
            }
        });
    
});
$('.btn-user-edit-pwd').click( function(e) {
    e.preventDefault();
    
    var role = JSON.parse(localStorage.getItem('role'));
    var dataString = $("form.user-setpwd-form").serialize();	
    var idupdate = role.iduser;
        //alert(" data-form-edit c :"+dataString+" id : "+idupdate);
    $.ajax({
            type: "PUT",
            url: webapp+"user/"+idupdate,
            data: dataString,
			
            contentType: "application/x-www-form-urlencoded",
            
            success: function (data) {
                console.log("data :"+JSON.stringify(data));
                
		if (data.status){
                    window.location.href = "home.html";
                } else {
                    console.log(data.msg);
                }
            }
        });
    
});

$('.btn-user-insert').click( function(e) {
    e.preventDefault();
      
    var dataString = $("form.user-form").serialize();	
    var idupdate = document.getElementById('iduser').value;
		
        //alert(" data-form :"+dataString);
	
    var URL = "";
    var formtype = document.getElementById('form-type').value;
    
    if (formtype === "POST") {
        URL = webapp+"user";
        console.log("insert user");
    }
    if (formtype === "PUT") {
        URL = webapp+"user/"+idupdate;
        console.log("update id :"+idupdate);
    }
  
    $.ajax({
            type: formtype,
            url: URL ,
            data: dataString,
			
            contentType: "application/x-www-form-urlencoded",
            
		success: function (data) {
		//data = JSON.parse(data);
                
                //alert("data :"+JSON.stringify(data));
                console.log("data :"+JSON.stringify(data));
                
		if (data.status){
                    //parent.window.location="manageuser.html";
                    parent.window.location = parent.window.location.href;
                } else {
                    //alert("data :"+data.msg);
                    //document.getElementById('msg').innerHTML = data.msg;
                    console.log(data.msg);
                }
            }
        });
});


$('.btn-repair-job-com').click( function(e) {
    e.preventDefault();
      
    var dataString = $("form.repair-com-form").serialize();	
    //var idupdate = document.getElementById('iduser').value;
		
    alert(" data-form-repair-com :"+dataString);
    console.log(" data-form-repair-com :"+dataString);
	
   // var URL = "";
    //var formtype = document.getElementById('form-type').value;
    
    /*if (formtype === "POST") {
        URL = webapp+"user";
        console.log("insert user");
    }
    if (formtype === "PUT") {
        URL = webapp+"user/"+idupdate;
        console.log("update id :"+idupdate);
    }*/
  
   /* $.ajax({
            type: formtype,
            url: URL ,
            data: dataString,
			
            contentType: "application/x-www-form-urlencoded",
            
		success: function (data) {
		//data = JSON.parse(data);
                
                //alert("data :"+JSON.stringify(data));
                console.log("data :"+JSON.stringify(data));
                
		if (data.status){
                    //parent.window.location="manageuser.html";
                    parent.window.location = parent.window.location.href;
                } else {
                    //alert("data :"+data.msg);
                    //document.getElementById('msg').innerHTML = data.msg;
                    console.log(data.msg);
                }
            }
        });*/
});




//--- Bootstrap 3 Modal external URL --

$('a.myprofile').on('click', function(e) {
    e.preventDefault();
    var url = $(this).attr('href');     
     //alert(url);    
    $(".modal-body-profile").html('<iframe class="iProfile" id="iProfile"  width="100%" height="100%" frameborder="0" scrolling="no" allowtransparency="true" src="'+url+'"></iframe>');  
    
    $("form.user-profile-form").show();
    $("form.user-setpwd-form").hide();
    
    $('.modal-title-profile').html('<i class="fa fa-user"></i> My Profile');
    
});

$('a.setpwd').on('click', function(e) {
    e.preventDefault();
    var url = $(this).attr('href');     
    
    //alert(url);    
   
    $(".modal-body-profile").html('<iframe class="iProfile" id="iProfile"  width="100%" height="100%" frameborder="0" scrolling="no" allowtransparency="true" src="'+url+'"></iframe>');
    
    //alert("iframe load !");    
     
    $("form.user-profile-form").hide();
    $("form.user-setpwd-form").show();
    
    $('.modal-title-profile').html('<i class="fa fa-user"></i> Set Password'); 
      
});

$('a.repair-service').on('click', function(e) {
    e.preventDefault();
    var url = $(this).attr('href');     
     //alert(url);    
    $(".modal-body repair-service").html('<iframe class="iRepair" id="iRepair"  width="100%" height="100%" frameborder="0" scrolling="no" allowtransparency="true" src="'+url+'"></iframe>');         
   
    $('.modal-title-ex-file').html('<i class="fa fa-wrench"></i> Repair & Services ');
    
    $('.cust-info').innerHTML = "LOAD CUSTOMER INFO ... ";
    
    
});




//--- Set iframe content --
$('a.menu-bar').on('click', function(e) {
    e.preventDefault();
    
    var url = $(this).attr('href');  
    
    //alert("Open menu 1 :"+url);
         
    localStorage.setItem("current-src", url);
    //document.getElementById('ifrm-content').src =  url;

    $("#ifrm-content").attr("src",url);
    
});

//--- user for after add class menu-bar ---
$(document).on('click', "a.menu-bar", function() {
             
    //alert("a.menu-bar");
    var url = $(this).attr('href');  
    
    //alert("Open menu 2 :"+url);
         
    localStorage.setItem("current-src", url);   
    
      
    
});



$(document).on('click', "a.menu-bar-unit", function() {
             
    //alert("a.menu-bar");
    //var url = $(this).attr('href');  
    
    //alert("Menu unit :"+url);
    
    $("#ifrm-content").attr("src","job.html");
    //document.getElementById('ifrm-content').src = "job.html";
    
          
});

              



// --- DOCUMENT READY ---
$(document).ready(function() {  
   
    var role = JSON.parse(localStorage.getItem('role'));
      
    if ((localStorage.getItem('role') === null) && (window.location.pathname!=="/")){
        window.location.href = "/";
    } 
    if ((localStorage.getItem('role') !== null) && (window.location.pathname==="/")){
        window.location.href = "/home.html";
    } 
    if (localStorage.getItem('role') !== null) {
        checkRole(role.idrole);
        $(".username").text(" "+role.username+" ");
        //$("#img-profile").attr('src',"data:image/png;base64,"+role.photo);
        
        //$("#img-profile").attr('src',datapic);
        $("#img-profile").attr('src',role.photo);
        $("#img-edit-myprofile").attr('src',role.photo);
    }
    
	            
    if ((localStorage.getItem('role') === null) && (window.location.pathname!=="/")){
        window.location.href = "/";
    } 
    
    
    
    //--- Check iFrame load Ready ---
    $('.iProfile').ready(function () {
        //Everything you need. 
        //alert('myframe profile is loaded');
        
        if (role !== null) {
            $("input#username.form-control").val(role.username);
            $("input#pwd.form-control").val(role.pwd);
            
            $("input#name.form-control").val(role.name);
            $("input#lastname.form-control").val(role.lastname);
            $("input#email.form-control").val(role.email);
            $("input#tel.form-control").val(role.tel);
                       
            $("textarea#address.form-control").val(role.address);
                                   
            //--- show cust info in repair.html ---
            $("p.cust-info").html("<h4> <b> customer info : </b> "+role.name+"    "+role.lastname+"</h4>");
            $("input.cust-info#iduser").val(role.iduser);
            
        }
        
        //$("select#select-dept.form-control").innerHTML += "<option value='0'>value0</option>";

        $("form.user-profile-form").show();
        $("form.user-setpwd-form").hide();     

    });
               
    
    var regExp = /manageuser/g;
        var testString = window.location.href;//Inyour case it would be window.location;
            if(regExp.test(testString)) // This doesn't work, any suggestions.                 
            {                      
                                
                showListData('role');
                showListData('unit');
                showListData('position');
                showListData('dept');
                
                showCheckboxData('unit');   
                
                //--- show all user in datatable --
                if (role.idrole===1) show_user_role(0);
                    else show_user_role(4);
                
            }
            
            regExp =/home/g;
            if(regExp.test(testString))              
            {                      
                                
                loadmenubar();                
                               
                document.getElementById('ifrm-content').src = "job.html";
                
                
                $('.iRepair').ready(function () {
      
                    $("#cust-info").innerHTML=role.username;

                    //--- show device type com on repair.html ---
                    shDevice_type(1);

                }); 
                
                //--- for sub dropdown menu ---
                $('ul.dropdown-menu [data-toggle=dropdown]').on('click', function(event) {
			event.preventDefault(); 
			event.stopPropagation(); 
			$(this).parent().siblings().removeClass('open');
			$(this).parent().toggleClass('open');
		});
                
                
                
            }
 
       
});
	

// --- FUNCTION ---  

function change_unit(unit){
    
    //alert("Select unit :"+unit);
   
    $('.navbar-brand').text(":: "+unit+" ::");   
    
       
    document.getElementById("newjob-menubar").href = unit+".html";
   
}


function encodeImageFileAsURL(){

    var filesSelected = document.getElementById("inputFileToLoad").files;
    if (filesSelected.length > 0)
    {
        var fileToLoad = filesSelected[0];

        var fileReader = new FileReader();

        fileReader.onload = function(fileLoadedEvent) {
            var srcData = fileLoadedEvent.target.result; // <--- data: base64
            //role = JSON.parse(localStorage.getItem('role'));
            //role.photo = srcData;
            //localStorage.setItem('role',role);
            $('#photo').val(srcData);
            document.getElementById("img-edit-myprofile").src = srcData;
            console.log("Converted Base64 version is "+srcData);
        };
        fileReader.readAsDataURL(fileToLoad);
    }
}
function resizeImage(input) {
    var filesToUpload = input.files;
    var file = filesToUpload[0];

    //var img = document.createElement("img");
    var img = document.getElementById("img-edit-myprofile");
    var reader = new FileReader();
    reader.onload = function (e) { 
        
        img.src = e.target.result; 
        var canvas = document.createElement('canvas');
    var ctx = canvas.getContext("2d");
    ctx.drawImage(img, 0, 0);

    var MAX_WIDTH = 180;
    var MAX_HEIGHT = 120;
    var width = img.width;
    var height = img.height;

    if (width > height) {
        if (width > MAX_WIDTH) {
            height *= MAX_WIDTH / width;
            width = MAX_WIDTH;
        }
    } else {
        if (height > MAX_HEIGHT) {
            width *= MAX_HEIGHT / height;
            height = MAX_HEIGHT;
        }
    }
    canvas.width = width;
    canvas.height = height;
    var ctx = canvas.getContext("2d");
    ctx.drawImage(img, 0, 0, width, height);

    var dataurl = canvas.toDataURL("image/png");
    img.src = dataurl;
    $('#photo').val(dataurl);
    console.log("Converted Base64  "+dataurl);
        
    };
    reader.readAsDataURL(file);


}

function loadmenubar(){
    //alert("loadjobmenu");
    $.ajax({
            type:'GET',
            url: webapp+'unit?action=1' ,
            contentType: "application/json",
            success: function (data) {
                // data = JSON.parse(data);
                //data = JSON.stringify(data);
                shData = data;
                
                //console.log("loadjobmenu :"+JSON.stringify(shData));
                
                var html="";
                var html1="";
                var role = JSON.parse(localStorage.getItem('role'));
                shDataActUnit = role.actunit.split(",");
                for(var key in shData.data) {
                    
                    if (key !== '5') html += "<li><a data-toggle='modal' class='repair-service' href='/repair_"+shData.data[key].filename+".html' data-target='#myModal-repair-"+shData.data[key].filename+"'><i class='fa fa-plus-circle'></i>"  +" "+shData.data[key].menu+"</a></li>";                                      
                    
                    if (shDataActUnit[parseInt(key)+1] === 'true') 
                       
                        html1 += "<li><a class='menu-bar-unit' id='menu-unit-"+key+"'  href=javascript:change_unit('"+shData.data[key].shortname+"') ><i class='fa fa-arrow-circle-right'></i>"  +" "+shData.data[key].shortname+"</a></li>";
                        
                        //--- show main unit (set page in Add Job menubar ---
                        if(key==0){
                            change_unit(shData.data[key].shortname);
                            //alert(key+" : "+shData.data[key].shortname);
                        }
                        
                }
                
                html += "<li class='divider'></li>";
                html += "<li><a href='job_status.html' target='ifrm'><i class='fa fa-arrow-circle-right'></i> Job Status History </a></li>";
                
                                
                document.getElementById("job-menu").innerHTML = html; 
                document.getElementById("unit-menu").innerHTML = html1;
                if (html1 === "") $('.menu-bar-unit').hide();
                }
    });

    

}


function shDevice_type(unit){
    
    $.ajax({
            type:'GET',
            url: webapp+'devicetype?unit='+unit ,
            contentType: "application/json",
            success: function (data) {
                // data = JSON.parse(data);
                //data = JSON.stringify(data);
                shData = data;
                
                //console.log("DATA :"+JSON.stringify(shData));
                
                var html="<option value='0'>-- Select --</option>";
                
                for(var key in shData.data) {
                    
                    html += "<option value=" + shData.data[key].id + ">" +shData.data[key].details + "</option>";
                    
                }
                
                document.getElementById("iddevice_type").innerHTML = html; 
                
                
            }
    });

    

}

function check_device_type() {
       
   //--- 1 is PC (show COM-NAME) ---
   select_label = $('select option:selected').text();
   
   //alert("Select Device : "+value);
   //alert("Select Device : "+select_label);
      
    if (select_label === 'PC'){
        $('div.form-group.com-name.hidden').removeClass('hidden');
    } else {
        $('div.form-group.com-name').addClass('hidden');
    }
    
}




function openlink(link){
    alert("OpenLink"+link);
}


function checkRole(idrole){
    $('.admin').hide();
    $('.manager').hide();
    $('.director').hide();
    switch (idrole) {
        case 1:
            $('.admin').show();
            break;
        case 2:
            $('.director').show();
            break;
        case 3:
            $('.manager').show();
            break;
        case 4:
            $('.staff').show();
            break;
    }
}
        
function show_user_role(idrole){
           //alert(idrole); 
           var dataurl = webapp+'/user?role='+idrole;
           var role = JSON.parse(localStorage.getItem('role'));
           if (idrole === 4 ) {
               dataurl = webapp+'/user?role='+idrole+"&column=idunit&value="+role.idunit;
           }
           $('#datatable-user').bootstrapTable('refresh', {
                    url: dataurl
           });
}
        
        
        //--- JS ShowDataList in Select form ---
        function showListData(table){ 
            
            $.ajax({
            type:'GET',
            url: webapp+table ,
            contentType: "application/json",
            success: function (data) {
                // data = JSON.parse(data);
                //console.log("Data : "+JSON.stringify(data));
                shData = data;
                var html="";
                var html1="";
                var role = JSON.parse(localStorage.getItem('role'));
                html += "<option value='0'>Select</option>";
                if (role.idrole===1) html1 += "<option value='0'>Select</option>";
                for(var key in shData.data) {
                    html += "<option value=" + shData.data[key].id + ">" +shData.data[key].name + "</option>";
                    if (table==='role'){
                        //var role = JSON.parse(localStorage.getItem('role'));
                        if (role.idrole===1) {
                            html1 += "<option value=" + shData.data[key].id + ">" +shData.data[key].name + "</option>";
                        }
                        if ((role.idrole===3) &&((key==='3') || (key==='4'))) {
                            html1 += "<option value=" + shData.data[key].id + ">" +shData.data[key].name + "</option>";
                        }
                    }
                }
                    if (table==='role'){
                         document.getElementById("select-"+table).innerHTML = html; 
                         document.getElementById("dropdown-sel-"+table).innerHTML = html1;
                    }else{
                        document.getElementById("select-"+table).innerHTML = html; 
                    }
                }
            });
            
        }
        
        //--- JS ShowCheckBox input form ---
        function showCheckboxData(table){
            $.ajax({
            type:'GET',
            url: webapp+table+'?action=1' ,
            contentType: "application/json",
            success: function (data) {
                // data = JSON.parse(data);
                //data = JSON.stringify(data);
                shData = data;
                var html="";
                for(var key in shData.data) {
                    html += "<label class='checkbox'><input type='checkbox' id='checkunit"+shData.data[key].id+"' value='"+shData.data[key].id+"' onclick='select_actunit(this.value,this.checked)'>"+shData.data[key].shortname+"</label>";
                    
                }
                
                document.getElementById("check-"+table).innerHTML = html; 
                }
            });
            
        }
        
        function select_actunit(id,chk){
            
            //alert("id : "+id+" value :"+value);
            ActUnit[id]=chk;
            document.getElementById('actunit').value=ActUnit;
            //console.log("ActUnit : "+ActUnit);
        }
	
	function change_to_insert() {
	
	//alert("change to insert");
	$('.modal-title').html('<i class="fa fa-user"></i> New User');
	
			document.getElementById('iduser').value = "";
			document.getElementById('name').value = "";
			document.getElementById('lastname').value = "";
			
			document.getElementById('address').value = "";
			document.getElementById('tel').value = "";
			
			document.getElementById('username').value = "";
			document.getElementById('pwd').value = "";
			                        
                        document.getElementById('form-type').value = "POST";
	}
	
		
	
    //--- JS For Editable Table (edit/delete) ---
	function operateFormatter(value, row, index) {
        return [
		   ' <a href="#" data-toggle="modal" data-target="#userform_popup" class="edit ml10">',
		   '   <i class="fa fa-pencil-square-o"></i>  ',
		   ' </a>',
						
           ' <a href="#" data-toggle="modal" data-target="#deleteuser" class="remove ml10">',
		   '   <i class="fa fa-times"></i>',
		   ' </a>'
		   
           
        ].join('');
    }

    window.operateEvents = {
       
        'click .edit': function (e, value, row, index) {
            $('.modal-title').html('<i class="fa fa-user"></i> Edit User Profile');
			
            document.getElementById('iduser').value = row.iduser;
            document.getElementById('name').value = row.name;
            document.getElementById('lastname').value = row.lastname;
			
            document.getElementById('address').value = row.address;
            document.getElementById('email').value = row.email;
            document.getElementById('tel').value = row.tel;
            document.getElementById('actunit').value = row.actunit;			
            ActUnit = row.actunit.split(",");
            for (var i in ActUnit) {
               if (ActUnit[i]==='true') {
                   document.getElementById("checkunit"+i).checked = true;
               } else {
                    if (i>0) document.getElementById("checkunit"+i).checked = false;
               }
               
            }
            document.getElementById('username').value = row.username;
            document.getElementById('pwd').value = null;
            document.getElementById("select-dept").value = row.iddept;
            document.getElementById("select-unit").value = row.idunit;
            document.getElementById("select-position").value = row.idposition;
            document.getElementById("select-role").value = row.idrole;
	
            document.getElementById('form-type').value = "PUT";

        },
        'click .remove': function (e, value, row, index) {
	
			
			var r = confirm("Are you sure to remove : "+row.name+" "+row.lastname+" ?");
			if (r === true) {

					$.ajax({
					type:'DELETE',
					url: webapp+"user/"+row.iduser,
								
					contentType: "application/x-www-form-urlencoded",
					//useDefaultXhrHeader: false,
					
					 success: function (data) {
					
							if (data.status){
								//parent.window.location="manageuser.html";
                                                                parent.window.location = parent.window.location.href;
                                                                
							} else {
								//alert("data :"+data.msg);
								//document.getElementById('msg').innerHTML = data.msg;
								console.log(data.msg);
							}
					  }
				   
				   });
		   
		   } else {
				
		   }
		   
        },
		
		'click .add': function (e, value, row, index) {
			
                    //document.getElementById('iduser').value = row.iduser;
			
                    //alert('You click add user');
                    //console.log(value, row, index);
                    //alert('You want to remove '+row.name+' ,iduser :'+row.iduser);
        }
		
    };
 //--- JS For Editable Table (edit/delete) ---
