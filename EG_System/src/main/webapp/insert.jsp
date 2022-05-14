<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Title</title>
	
    <script src="assets/js/croppie.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.js"></script>
	
</head>
<body>
	
    <div class="container">
		
<br>
<p></p>

	                    <div>Power Consumption</div>
	                        <form id="userRegister">
	                            <div>
	                                <label>Usage</label>
	                                <div>
	                                    <input type="text" id="usage" class="form-control" name="usage">
	                                </div>
	                            </div>
	                            
	                            
	                            <div>
	                             	<label>Unit Type</label>
	                             	<div>
	                             		<input type ="text" id="unit_type" class="form-control" name="unit_type">
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
	                                    Add
	                                </button>
	                                <a href="edit_n_delete.jsp" class="btn btn-success">
	                                    List
	                                </a>
	                            </div>
	                    	</form>
	                    </div>

</body>
</html>
<script>

$(document).ready(function () {

    $("#userRegister").validate({
        rules: {
        	usage: "required",
        	unit_type: "required",
        	description: "required"        	
        },
        
        
        messages: {
        	usage: "u Required!",
        	unit_type: "u Required!",
        	description: "d Required!"           
        },
        
        
        submitHandler: function () {
        	var fromData = JSON.stringify({
                "usage" : $('#usage').val(),
                "unit_type" : $('#unit_type').val(),
                "description" : $('#description').val(),
              });
        	
        	console.log(fromData);

            $.ajax({
                type: "POST",
                url: 'rest/power',
                dataType : 'json',
				contentType : 'application/json',
				data: fromData,
                success: function(data){
                	console.log(data);
                	if(data['success']=="1"){
                		alert("Added Successfull!");
                        $("#userRegister")[0].reset();
					}else{
						alert("Unsuccessfull!");
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

</script>