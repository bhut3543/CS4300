<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<script src="scripts/jquery.js" type="text/javascript"></script>
		<script src="scripts/jquery-ui.js" type="text/javascript"></script>
		<script src="scripts/postscript.js" type="text/javascript"></script>
		<link rel="stylesheet" href="css/jquery-ui.css" type="text/css" />
		<link rel="stylesheet" href="css/jquery-ui.structure.css" type="text/css" />
		<link rel="stylesheet" href="css/jquery-ui.theme.css" type="text/css" />
		<link rel="stylesheet" href="css/classifieds.css" type="text/css" />
		<title>Project</title>
	</head>
	<body>
		<h1 id="title">${post.title}</h1>
    	<h2 id="price">$${post.price}</h2>
    	<p>Uploaded on: ${post.postTime}</p>
    	<img src="/4300Project/FinalProjServlet?action=getImg&pic=1&postId=${post.id}" onerror="this.style.display='none'" alt="" /></td>
    	<img src="/4300Project/FinalProjServlet?action=getImg&pic=2&postId=${post.id}" onerror="this.style.display='none'" alt=""/></td>
    	<img src="/4300Project/FinalProjServlet?action=getImg&pic=3&postId=${post.id}" onerror="this.style.display='none'" alt=""/></td>
    	<img src="/4300Project/FinalProjServlet?action=getImg&pic=4&postId=${post.id}" onerror="this.style.display='none'" alt=""/></td>
    	<img src="/4300Project/FinalProjServlet?action=getImg&pic=5&postId=${post.id}" onerror="this.style.display='none'" alt=""/></td>
    	<p id="description">${post.description}</p>
    	<p id="hasCarfax">Has Carfax: ${post.hasCarfax?c}</p>
    	<p>Current User's Post?: ${currentUsersPost?c}</p>
			<fieldset>
				<legend>Specs</legend>
				<p id="year">Year: ${info.year}</p>
				<p id="make">Make: ${info.make}</p>
				<p id="model">Model: ${info.model}</p>
				<p id="vin">VIN #: ${info.vin}</p>
				<p id="color">Color: ${info.color}</p>
				<p id="bodyStyle">Body Style: ${info.bodyStyle}</p>
				<p id="driveType">Drive Type: ${info.driveType}</p>
				<p id="engine">Engine: ${info.engine}</p>
				<p id="horsepower">Horsepower: ${info.horsepower}</p>
				<p id="odometer">Odometer: ${info.odometer}</p>
				<p id="carfax">CarFax: ${post.hasCarfax?c}</p>
			</fieldset>
			
    	<p id="addr">${post.addr}</p>
		<div id="map"></div>
		<script>
      		function initMap() {
	        	var uluru = {lat: -25.363, lng: 131.044};
	        	var map = new google.maps.Map(document.getElementById('map'), {
	          	zoom: 14,
	          	center: uluru
	        	});
	        	var marker = new google.maps.Marker({
	          	position: uluru,
	          	map: map
	        	});
	        	var geocoder = new google.maps.Geocoder();
				geocodeAddress(geocoder, map);
				google.maps.event.trigger(map, "resize");

      		}
      		function geocodeAddress(geocoder, resultsMap) {
		        var address = document.getElementById('addr').innerHTML;
		        console.log(address);
		        geocoder.geocode({'address': address}, function(results, status) {
		          if (status === 'OK') {
		            resultsMap.setCenter(results[0].geometry.location);
		            var marker = new google.maps.Marker({
		              map: resultsMap,
		              position: results[0].geometry.location
		            });
		          } else {
		            alert('Geocode was not successful for the following reason: ' + status);
          }
        });
      }

    	</script>
    	<script async defer
    		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAjPgnid6PBQ1jSOibvwcG-YRLIkvlp2_o&callback=initMap">
    	</script>

		<#if currentUsersPost == true>
			<button id="editbutton">Edit Post</button>
			<button id="deletebutton">Delete Post</button>
		</#if>
		<div id="dialog" title="Confirm Delete">
			<h3>Please re-enter your login credentials to delete this post</h3>
				<label>Username: <input type="text" id="user" /></label>
				<label>Password: <input type="password" id="pass" /></label>
				<input type="submit" id="deletesubmit" value="Delete"/>
			
		</div>
	</body>
</html>
