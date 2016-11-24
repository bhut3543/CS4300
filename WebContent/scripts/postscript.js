$(document).ready(function() {
  $("#editbutton").click(function() {
    if($("#editbutton").html() == 'Edit Post') {
      console.log("EDIT");
    } else {
    	$("#editbutton").html("Edit Post");
      //perform save code here with AJAX!!!
    $.post("http://localhost:8080/4300Project/FinalProjServlet?action=update",
    {
      action:"upload",
      postman_id:2,
      vin:"12387912038dsufh",
      year:2012,
      make:"Lamborghini",
      model:'Murcielago',
      title:'Screaming V12',
      price:12312,
      description:'This is for my wife',
      color:'GOLD',
      body_style:'COUPE',
      drive_type:'RWD',
      engine:'GAS',
      hp:500,
      odometer:123130,
      has_carfax:true
    },
    function(data, status){
        alert("Data: " + data + "\nStatus: " + status);
    });

    }
    var title = $("#title").text();
	var price = $("#price").text().substring(1);
	var description = $("#description").text();
	var color = $("#color").text().substring(7);
	var bodyStyle = $("#bodyStyle").text().substring(12);
	var driveType = $("#driveType").text().substring(12);
	var engine = $("#engine").text().substring(8);
	var horsepower = $("#horsepower").text().substring(12);
	var odometer = $("#odometer").text().substring(10);
	var carfax = $("#carfax").text().substring(8);

	$("#editbutton").html("Save Changes");
  	$("#title").replaceWith("<input type=\"text\" value=\"" + title + "\"/><br/><br/>");
  	$("#price").replaceWith("$<input type=\"text\" value=\"" + price + "\"/><br/><br/>");
  	$("#color").replaceWith("Color: <input type=\"text\" value=\"" + color + "\"/><br/><br/>");
  	$("#bodyStyle").replaceWith("Body Style: <input type=\"text\" value=\"" + bodyStyle + "\"/><br/><br/>");
  	$("#driveType").replaceWith("Drive Type: <input type=\"text\" value=\"" + driveType + "\"/><br/><br/>");
  	$("#engine").replaceWith("Engine: <input type=\"text\" value=\"" + engine + "\"/><br/><br/>");
  	$("#horsepower").replaceWith("Horsepower: <input type=\"text\" value=\"" + horsepower + "\"/><br/><br/>");
  	$("#odometer").replaceWith("Odometer: <input type=\"text\" value=\"" + odometer + "\"/><br/><br/>");
  	$("#carfax").replaceWith("CarFax: <input type=\"text\" value=\"" + carfax + "\"/><br/><br/>");
  	$("#description").replaceWith("<br/><textarea value=\"" + description + "\"/><br/><br/>");
  });
});
