<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<script src="scripts/validate.js" type="text/javascript"></script>
		<link rel="stylesheet" href="css/classifieds.css" type="text/css" />
		<title>Vehicles for Harambe</title>
	</head>
	<body>
		<h1>Classifieds</h1>
    <table border=1>
      <tr>
        <th>Thumbnail</th>
        <th>Title</th>
        <th>Year</th>
        <th>Make</th>
        <th>Model</th>
        <th>View Classified</th>
      </tr>
      <#list posts as post>
      	<tr>
        	<td><img src="http://localhost:8080/4300Project/FinalProjServlet?action=getImg&pic=1&postId=${post.id}" alt=""/></td>
			<!-- <td><img src="pic.jpg" alt=""/></td> -->
        	<td>${post.title}</td>
        	<td>${post.year}</td>
        	<td>${post.make}</td>
        	<td>${post.model}</td>
        	<td><a href="/FinalProjServlet?action=classified&postId=${post.id}" alt="">View Classified</a>
        </tr>
      </#list>
    </table>

	</body>
</html>
