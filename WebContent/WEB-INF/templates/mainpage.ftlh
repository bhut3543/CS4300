<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<script src="scripts/validate.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" href="css/layout.css">
		<title>Vehicles for Harambe</title>
	<</head>
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
		<h1>Welcome ${username}!</h1>
    <a href="login.html">Login to VFH</a><br /><br />
    <a href="FinalProjServlet?action=view">View Classifieds</a>
    <h1>Post to Classifieds</h1>
    <form method="post" action="FinalProjServlet" enctype="multipart/form-data">
    <input class="hidden" type="hidden" name="action" value="upload"  />
      <label>VIN #: <input type="text" name="vin" required/></label><br />
      <label>Year: <input type="number" name="year" required/></label><br />
	  <label>Make:
	  <select name="make">
            <option value="Acura">Acura</option>
            <option value="Alfa Romeo">Alfa Romeo</option>
            <option value="Ariel">Ariel</option>
            <option value="Aston Martin">Aston Martin</option>
            <option value="Audi">Audi</option>
            <option value="BMW">BMW</option>
            <option value="Bentley">Bentley</option>
            <option value="Buick">Buick</option>
            <option value="Cadillac">Cadillac</option>
            <option value="Chevrolet">Chevrolet</option>
            <option value="Chrysler">Chrysler</option>
            <option value="Dodge">Dodge</option>
            <option value="FIAT">FIAT</option>
            <option value="Ferrari">Ferrari</option>
            <option value="Fisker">Fisker</option>
            <option value="Ford">Ford</option>
            <option value="GMC">GMC</option>
            <option value="Honda">Honda</option>
            <option value="Hyundai">Hyundai</option>
            <option value="Infiniti">Infiniti</option>
            <option value="Jaguar">Jaguar</option>
            <option value="Jeep">Jeep</option>
            <option value="Kia">Kia</option>
            <option value="Lamborghini">Lamborghini</option>
            <option value="Land Rover">Land Rover</option>
            <option value="Lexus">Lexus</option>
            <option value="Lincoln">Lincoln</option>
            <option value="Lotus">Lotus</option>
            <option value="MINI">MINI</option>
            <option value="Maserati">Maserati</option>
            <option value="Mazda">Mazda</option>
            <option value="McLaren">McLaren</option>
            <option value="Mercedes">Mercedes Benz</option>
            <option value="Nissan">Nissan</option>
            <option value="Porsche">Porsche</option>
            <option value="Ram">Ram</option>
            <option value="Rolls">Rolls Royce</option>
            <option value="Scion">Scion</option>
            <option value="Subaru">Subaru</option>
            <option value="Tesla">Tesla</option>
            <option value="Toyota">Toyota</option>
            <option value="Volkswagen">Volkswagen</option>
            <option value="Volvo">Volvo</option>
          	<option value="smart">smart</option>
      </select></label><br />
      <label>Model: <input type="text" name="model" required/></label><br />
      <label>Title: <input type="text" name="title" required/></label><br />
      <label>Price: $<input type="text" name="price" required/></label><br />
      <label>Description: <input type="text" name="description" required/></label><br />
      <label>Color: <input type="text" name="color" required/></label><br />
      <label>Body Style: <input type="text" name="body_style" required/></label><br />
      <label>Drive Type: <input type="text" name="drive_type" required/></label><br />
      <label>Engine Type: <input type="text" name="engine" required/></label><br />
      <label>Horsepower: <input type="number" name="hp" required/></label><br />
      <label>Odometer: <input type="number" name="odometer" required/></label><br />
      <label>Has CarFax?: <input type="checkbox" name="has_carfax" required/></label><br />
      <input type="file" name="pic1" /><br />
      <input type="file" name="pic2" /><br />
      <input type="file" name="pic3" /><br />
      <input type="file" name="pic4" /><br />
      <input type="file" name="pic5" /><br />
      <input type="submit" value="Upload!"/>
    </form>
	</body>
</html>
