$(document).ready(function() {
  $("#editbutton").click(function() {
		var title = $("#title").text();
		var price = $("#price").text();
		var description = $("#description").text();

	$("#editbutton").html("Save Changes");
  	$("#title").replaceWith("<input type=\"text\" value=\"" + title + "\"/><br/><br/>");
  	$("#price").replaceWith("$<input type=\"text\" value=\"" + price + "\"/><br/><br/>");
  	$("#color").replaceWith("Color: <input type=\"text\" value=\"" + price + "\"/><br/><br/>");
  	$("#bodyStyle").replaceWith("Body Style: <input type=\"text\" value=\"" + price + "\"/><br/><br/>");
  	$("#driveType").replaceWith("Drive Type: <input type=\"text\" value=\"" + price + "\"/><br/><br/>");
  	$("#engine").replaceWith("Engine: <input type=\"text\" value=\"" + price + "\"/><br/><br/>");
  	$("#horsepower").replaceWith("Horsepower: <input type=\"text\" value=\"" + price + "\"/><br/><br/>");
  	$("#odometer").replaceWith("Odometer: <input type=\"text\" value=\"" + price + "\"/><br/><br/>");
  	$("#carfax").replaceWith("CarFax: <input type=\"text\" value=\"" + price + "\"/><br/><br/>");
  	$("#description").replaceWith("<br/><textarea value=\"" + description + "\"/><br/><br/>");
  });
});
