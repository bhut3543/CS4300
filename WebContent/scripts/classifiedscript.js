$(document).ready(function() {
  var makes = ["Acura",
  "Alfa Romeo",
  "Ariel",
  "Aston Martin",
  "Audi",
  "BMW",
  "Bentley",
  "Buick",
  "Cadillac",
  "Chevrolet",
  "Chrysler",
  "Dodge",
  "FIAT",
  "Ferrari",
  "Fisker",
  "Ford",
  "GMC",
  "Honda",
  "Hyundai",
  "Infiniti",
  "Jaguar",
  "Jeep",
  "Kia",
  "Lamborghini",
  "Land Rover",
  "Lexus",
  "Lincoln",
  "Lotus",
  "MINI",
  "Maserati",
  "Mazda",
  "McLaren",
  "Mercedes",
  "Nissan",
  "Porsche",
  "Ram",
  "Rolls",
  "Scion",
  "Subaru",
  "Tesla",
  "Toyota",
  "Volkswagen",
  "Volvo",
  "smart"]
  $("#searchbox").autocomplete({
    source: makes
  })
});
