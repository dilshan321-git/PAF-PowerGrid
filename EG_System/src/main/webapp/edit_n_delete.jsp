<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>title</title>
	
    
    <script src="assets/js/croppie.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.js"></script>
	
</head>
<body>
	
    <div class="container">
		
<br>
<p></p>
	                    <div>User List</div>
	                    <div>
	                        <div id="usersDiv">
	                    	
	            			</div>
	                    </div>
	                    
	                   
<div id="hideDiv" style="display: none">
    <form id="userRegister">
    <input type="hidden" id="edit_id" name="edit_id">
          <div>
              <label>Usage</label>
              <div>
                   <input type="text" id="usage" class="form-control" name="usage">
              </div>
          </div>
          
          <div>
              <label>Unit Type</label>
              <div>
                  <input type="text" id="unit_type" class="form-control" name="unit_type">
              </div>
          </div>
          
          <div>
              <label>Description</label>
              <div>
                  <input type="text" id="description" class="form-control" name="description">
              </div>
          </div>
          
          
          
          <div>
              <button type="submit" class="btn btn-success">
                  Edit
              </button>
          </div>
  	</form>
</div> 
	                    
</div>
	
</body>
</html>

<script>

    function deletes(id) {
    	if (confirm("Delete ?") == true) {
        	$.ajax({
                type: "DELETE",
                url: "rest/power",
                data: JSON.stringify({ 'id' : id}),
                dataType: "json",
    			contentType : 'application/json',
                success: function(data){
                	if(data['success']=="1"){
                		alert("Delete Successfull!");
    					reload();
    				}else if(data['success']=="1"){
    					alert("Delete Unsuccessful!");
    				}
                },
                failure: function(errMsg) {
                    alert('Error');
                }
            });
    	}
    }

    $(document).ready(function () {

        $("#userRegister").validate({
        	    rules: {
        	    		
        				usage: "required",
        				unit_type: "required",
        				description: "required"
           		 },
            
            
           		 messages: {
           			   
            			usage: "Required!",            	
            			unit_type: " Required!",
            			description: " Required!"               
          		  },
            
            
            submitHandler: function () {
            	var fromData = JSON.stringify({
            		"id" : $('#edit_id').val(),
                    "usage" : $('#usage').val(),
                    "unit_type" : $('#unit_type').val(),
                    "description" : $('#description').val()                  
                });
            	
            	console.log(fromData);

                $.ajax({
                    type: "PUT",
                    url: 'rest/power',
                    dataType : 'json',
    				contentType : 'application/json',
    				data: fromData,
                    success: function(data){
                    	if(data['success']=="1"){
                    		alert("Edit Successfull!");
                        	document.getElementById("hideDiv").style.display = "none";
                            $("#userRegister")[0].reset();
    						reload();
    					}else{
    						alert("Unsuccessful!");
    					}
                    },
                    failure: function(errMsg) {
                    	alert("Connection Error!");
                    }
                });
            }
        });

        $("#userRegister").submit(function(e) {
            e.preventDefault();
        });

    });

    function reload(){
    	$.ajax({
            type: "GET",
            url: "rest/power",
            success: function(data){
            	$("#usersDiv").html(data);
            },
            failure: function(errMsg) {
                alert('Error');
            }
        });
    }

    reload();
    
    function edit(id) {
    	document.getElementById("hideDiv").style.display = "block";
    	$.ajax({
            type: "POST",
            url: "rest/power/get",
            data: JSON.stringify({ 'id' : id}),
            dataType: "json",
			contentType : 'application/json',
            success: function(data){
            	console.log(data),
                $('#edit_id').val(data['id']),
                $('#usage').val(data['usage']),
                $('#unit_type').val(data['unit_type']),
                $('#description').val(data['description'])
                
            },
            failure: function(errMsg) {
                alert('Error');
            }
        });

        
    }
    
    
</script>