$(document).ready(function() {
  $("#filtermakes").hide();
	$(".filtermake").prop('checked', true);

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

  $(".filtermake").click(function() {
    var selectedVals = [];
    $(".filtermake:checked").each(function() {
      selectedVals.push(this.value);
    })
    console.log(selectedVals);
    if(selectedVals.length == 0) {
        $(".posts").show("slow");
        return;
    }
    $(".posts").hide();
    $(".posts").each(function(i, obj) {
      for(var i = 0; i < selectedVals.length; i++) {
        if(!obj.innerHTML.includes(selectedVals[i])) {
          // $(this).hide("slow");
          // continue;
        } else {
          $(this).show("slow");
          continue;
        }
      }
      // $(".hide").hide("slow");
    })
  });

  $("#makedropdown").click(function() {
    if($("#filtermakes").is(":visible")) {
      $("#filtermakes").hide("slow");
    } else {
      $("#filtermakes").show("slow");
    }
  })
});
