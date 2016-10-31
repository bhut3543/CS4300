<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<script rel="stylesheet" src="scripts/validate.js" type="text/javascript"></script>
		<link rel="stylesheet" href="css/classifieds.css" type="text/css" />
		<title>Project</title>
	</head>
	<body>
		<h1>${post.title}</h1>
    	<h2>$${post.price}</h2>
    	<p>Uploaded on: ${post.postTime}</p>
    	<img src="/4300Project/FinalProjServlet?action=getImg&pic=1&postId=${post.id}" alt=""/></td>
    	<img src="/4300Project/FinalProjServlet?action=getImg&pic=2&postId=${post.id}" alt=""/></td>
    	<img src="/4300Project/FinalProjServlet?action=getImg&pic=3&postId=${post.id}" alt=""/></td>
    	<img src="/4300Project/FinalProjServlet?action=getImg&pic=4&postId=${post.id}" alt=""/></td>
    	<img src="/4300Project/FinalProjServlet?action=getImg&pic=5&postId=${post.id}" alt=""/></td>
    	<p>${post.description}</p>
    	<p>Has Carfax: ${post.hasCarfax?c}</p>
	</body>
</html>
