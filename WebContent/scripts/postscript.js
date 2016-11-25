$(document).ready(function() {
  $("#editbutton").click(function() {
    if($("#editbutton").html() == 'Edit Post') {
      console.log("EDIT");
    } else {
    	$("#editbutton").html("Edit Post");
      //perform save code here with AJAX!!!
      var postId = getQueryParams(window.location.search).postId;
      var title = $("#title").val();
  	  var price = $("#price").val();
  	  var description = $("#description").val();
  	  var color = $("#color").val();
  	  var bodyStyle = $("#bodyStyle").val();
  	  var driveType = $("#driveType").val();
  	  var engine = $("#engine").val();
  	  var horsepower = $("#horsepower").val();
  	  var odometer = $("#odometer").val();
  	  var carfax = $("#carfax").val();

    $.post("http://localhost:8080/4300Project/FinalProjServlet?action=update",
    {
      action:"upload",
      post_id:postId,
      vin:"12387912038dsufh",
      year:2012,
      make:"Lamborghini",
      model:'Murcielago',
      title:title,
      price:price,
      description:descirption,
      color:color,
      body_style:bodyStyle,
      drive_type:driveType,
      engine:engine,
      hp:horsepower,
      odometer:odometer,
      has_carfax:true
    },
    function(data, status){
        alert("Data: " + data + "\nStatus: " + status);
        window.location.reload();
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
  	$("#title").replaceWith("<input id=\"title\" type=\"text\" value=\"" + title + "\"/><br/><br/>");
  	$("#price").replaceWith("$<input id=\"price\" type=\"text\" value=\"" + price + "\"/><br/><br/>");
  	$("#color").replaceWith("Color: <input id=\"color\" type=\"text\" value=\"" + color + "\"/><br/><br/>");
  	$("#bodyStyle").replaceWith("Body Style: <input id=\"bodyStyle\" type=\"text\" value=\"" + bodyStyle + "\"/><br/><br/>");
  	$("#driveType").replaceWith("Drive Type: <input id=\"driveType\" type=\"text\" value=\"" + driveType + "\"/><br/><br/>");
  	$("#engine").replaceWith("Engine: <input id=\"engine\" type=\"text\" value=\"" + engine + "\"/><br/><br/>");
  	$("#horsepower").replaceWith("Horsepower: <input id=\"horsepower\" type=\"text\" value=\"" + horsepower + "\"/><br/><br/>");
  	$("#odometer").replaceWith("Odometer: <input id=\"odometer\" type=\"text\" value=\"" + odometer + "\"/><br/><br/>");
  	$("#carfax").replaceWith("CarFax: <input id=\"carfax\" type=\"text\" value=\"" + carfax + "\"/><br/><br/>");
  	$("#description").replaceWith("<br/><textarea id=\"description\" value=\"" + description + "\"/><br/><br/>");
  });

  function getQueryParams(qs) {
    qs = qs.split('+').join(' ');

    var params = {},
        tokens,
        re = /[?&]?([^=]+)=([^&]*)/g;

    while (tokens = re.exec(qs)) {
        params[decodeURIComponent(tokens[1])] = decodeURIComponent(tokens[2]);
    }

    return params;
}
});
