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
				<p id="color">Color: ${info.color}</p>
				<p id="bodyStyle">Body Style: ${info.bodyStyle}</p>
				<p id="driveType">Drive Type: ${info.driveType}</p>
				<p id="engine">Engine: ${info.engine}</p>
				<p id="horsepower">Horsepower: ${info.horsepower}</p>
				<p id="odometer">Odometer: ${info.odometer}</p>
				<p id="carfax">CarFax: ${post.hasCarfax?c}</p>
			</fieldset>
		<#if currentUsersPost == true>
			<button id="editbutton">Edit Post</button>
		</#if>
	</body>
</html>
