<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<script src="scripts/jquery.js" type="text/javascript"></script>
		<script src="scripts/jquery-ui.js" type="text/javascript"></script>
		<script src="scripts/classifiedscript.js" type="text/javascript"></script>
		<link rel="stylesheet" href="css/jquery-ui.css" type="text/css" />
		<link rel="stylesheet" href="css/jquery-ui.structure.css" type="text/css" />
		<link rel="stylesheet" href="css/jquery-ui.theme.css" type="text/css" />
		<link rel="stylesheet" href="css/layout.css" type="text/css" />
		<title>Vehicles for Harambe</title>
	</head>
	<body id="top">
<div class="wrapper col1">
  <div id="topbar">
    <p>Tel: (678)334-6798 | Mail: Support@Vehicls4Harambe.com</p>
    <ul>
      <li><a href="#">Home</a></li>
      <li><a href="#">About</a></li>
      <li><a href="#">Classifieds</a></li>
      <li class="last"><a href="login.html">Sign in</a></li>
    </ul>
    <br class="clear" />
  </div>
</div>
<!-- ####################################################################################################### -->
<div class="wrapper col2">
  <div id="header">
    <div class="fl_left">
      <h1><a href="index.html">Vehicles For Harambe</a></h1>
      <p>Be Strong For Harambe</p>
    </div>
    <div class="fl_right"> <a href="#"><img src="images/demo/468x60.gif" alt="" /></a> </div>
    <br class="clear" />
  </div>
</div>
<!-- ####################################################################################################### -->
<div class="wrapper col3">
  <div id="topnav">
    <ul>
      <li class="active"><a href="index.html">Homepage</a></li>
      <li><a href="pages/full-width.html">About Us</a></li>
      <li><a href="pages/full-width.html">Classifieds</a></li>
    </ul>
    <br class="clear" />
  </div>
</div>
		<div id="classifieds">
			<h1>Classifieds</h1>
			<form method="get" action="FinalProjServlet">
				<input class="hidden" type="hidden" name="action" value="view"  />
				<label>Search Classifieds: <input id="searchbox" type="text" name="search" required/></label>
				<input type="submit" value="Search Classifieds!" />
			</form>
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
					<tr class="posts">
						<td><img src="/4300Project/FinalProjServlet?action=getImg&pic=1&postId=${post.id}" onerror="this.src='img/error.jpeg'" alt=""/></td>
				<!-- <td><img src="pic.jpg" alt=""/></td> -->
						<td>${post.title}</td>
						<td>${post.year}</td>
						<td>${post.make}</td>
						<td>${post.model}</td>
						<td><a href="/4300Project/FinalProjServlet?action=classified&postId=${post.id}" alt="">View Classified</a>
					</tr>
				</#list>
			</table>
		</div>
		<div id="options">
			<div class="dropdowns" id="makedropdown">Filter by Make</div>
			<fieldset id="filtermakes">
				<legend>Filter by Make</legend>
				<input type="checkbox" class="filtermake"  value="Acura"/>Acura<br/>
				<input type="checkbox" class="filtermake"  value="Alfa Romeo"/>Alfa Romeo<br/>
				<input type="checkbox" class="filtermake"  value="Ariel"/>Ariel<br/>
				<input type="checkbox" class="filtermake"  value="Aston Martin"/>Aston Martin<br/>
				<input type="checkbox" class="filtermake"  value="Audi"/>Audi<br/>
				<input type="checkbox" class="filtermake"  value="BMW"/>BMW<br/>
				<input type="checkbox" class="filtermake"  value="Bentley"/>Bentley<br/>
				<input type="checkbox" class="filtermake"  value="Buick"/>Buick<br/>
				<input type="checkbox" class="filtermake"  value="Cadillac"/>Cadillac<br/>
				<input type="checkbox" class="filtermake"  value="Chevrolet"/>Chevrolet<br/>
				<input type="checkbox" class="filtermake"  value="Chrysler"/>Chrysler<br/>
				<input type="checkbox" class="filtermake"  value="Dodge"/>Dodge<br/>
				<input type="checkbox" class="filtermake"  value="FIAT"/>FIAT<br/>
				<input type="checkbox" class="filtermake"  value="Ferrari"/>Ferrari<br/>
				<input type="checkbox" class="filtermake"  value="Fisker"/>Fisker<br/>
				<input type="checkbox" class="filtermake"  value="Ford"/>Ford<br/>
				<input type="checkbox" class="filtermake"  value="GMC"/>GMC<br/>
				<input type="checkbox" class="filtermake"  value="Honda"/>Honda<br/>
				<input type="checkbox" class="filtermake"  value="Hyundai"/>Hyundai<br/>
				<input type="checkbox" class="filtermake"  value="Infiniti"/>Infiniti<br/>
				<input type="checkbox" class="filtermake"  value="Jaguar"/>Jaguar<br/>
				<input type="checkbox" class="filtermake"  value="Jeep"/>Jeep<br/>
				<input type="checkbox" class="filtermake"  value="Kia"/>Kia<br/>
				<input type="checkbox" class="filtermake"  value="Lamborghini"/>Lamborghini<br/>
				<input type="checkbox" class="filtermake"  value="Land Rover"/>Land Rover<br/>
				<input type="checkbox" class="filtermake"  value="Lexus"/>Lexus<br/>
				<input type="checkbox" class="filtermake"  value="Lincoln"/>Lincoln<br/>
				<input type="checkbox" class="filtermake"  value="Lotus"/>Lotus<br/>
				<input type="checkbox" class="filtermake"  value="MINI"/>MINI<br/>
				<input type="checkbox" class="filtermake"  value="Maserati"/>Maserati<br/>
				<input type="checkbox" class="filtermake"  value="Mazda"/>Mazda<br/>
				<input type="checkbox" class="filtermake"  value="McLaren"/>McLaren<br/>
				<input type="checkbox" class="filtermake"  value="Mercedes"/>Mercedes Benz<br/>
				<input type="checkbox" class="filtermake"  value="Nissan"/>Nissan<br/>
				<input type="checkbox" class="filtermake"  value="Porsche"/>Porsche<br/>
				<input type="checkbox" class="filtermake"  value="Ram"/>Ram<br/>
				<input type="checkbox" class="filtermake"  value="Rolls"/>Rolls Royce<br/>
				<input type="checkbox" class="filtermake"  value="Scion"/>Scion<br/>
				<input type="checkbox" class="filtermake"  value="Subaru"/>Subaru<br/>
				<input type="checkbox" class="filtermake"  value="Tesla"/>Tesla<br/>
				<input type="checkbox" class="filtermake"  value="Toyota"/>Toyota<br/>
				<input type="checkbox" class="filtermake"  value="Volkswagen"/>Volkswagen<br/>
				<input type="checkbox" class="filtermake"  value="Volvo"/>Volvo<br/>
				<input type="checkbox" class="filtermake"  value="smart"/>smart<br/>
			</fieldset>
		</div>


	</body>
</html>
