<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<script src="scripts/validate.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" href="css/main.css">
		<title>Vehicles for Harambe</title>
	</head>
	<body>
		<h1>Welcome ${username}!</h1>
    <a href="login.html">Login to VFH</a><br /><br />
    <a href="FinalProjServlet?action=view">View Classifieds</a>
    <h1>Post to Classifieds</h1>
    <form method="post" action="FinalProjServlet" enctype="multipart/form-data">
    <input class="hidden" type="hidden" name="action" value="upload"  />
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
      <input type="file" name="pic1" /><br />
      <input type="file" name="pic2" /><br />
      <input type="file" name="pic3" /><br />
      <input type="file" name="pic4" /><br />
      <input type="file" name="pic5" /><br />
      <input type="submit" value="Upload!"/>
    </form>
	</body>
</html>
