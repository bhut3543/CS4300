$(document).ready(function() {
  var selectedVals = [];
  // $("#filtermakes").hide();
	$(".filtermake").prop('checked', false);

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

  $("#mypostscheckbox").change(function() {
    if(this.checked) {
      $(".posts").hide();
      var currentId = $("#currentId").text();
      console.log("id " + currentId);
      $(".posts").each(function(i, obj) {
        if(obj.innerHTML.replace(/\s/g, '').includes("divid=\"currentId\">"+currentId)) {
          $(this).show("slow");
        }
      })
    } else {
      showCheckedVals();
    }
  });
  $("#colorsubmit").click(function() {
    var query = $("#colorfilter").val().toLowerCase();
    if(query.length > 0) {
      $(".posts").hide();
      $(".posts").each(function(i, obj) {
        if(obj.innerHTML.replace(/\s/g, '').toLowerCase().includes(("divid=\"color\">"+query).toLowerCase())) {
          $(this).show("slow");
        }
      })
    } else {
      showCheckedVals();
    }
  });
  $("#odosubmit").click(function() {
    var query = $("#odometerfilter").val();
    query = Number(query);
    if(query > 0) {
      $(".posts").hide();
      $(".posts").each(function(i, obj) {
        //get odometer value
        var html = obj.innerHTML.toLowerCase().replace(/\s/g, '');
        var odo = html.substring(html.indexOf("divid=\"odometer\">"));
        odo = Number(odo.substring(17, odo.indexOf('<')));
        console.log(odo + " < " + query + "? " + (odo < query));
        if(odo < query) {
          $(this).show("slow");
        }
      })
    } else {
      showCheckedVals();
    }
  });

  $(".filtermake").change(function() {
    selectedVals = [];
    $(".filtermake:checked").each(function() {
      selectedVals.push(this.value);
    });
    showCheckedVals();
  });
  // $("#makedropdown").click(function() {
  //   if($("#filtermakes").is(":visible")) {
  //     $("#filtermakes").hide("slow");
  //   } else {
  //     $("#filtermakes").show("slow");
  //   }
  // })
  $("#options").accordion({
    collapsible: true,
    heightStyle: "content",
    active: "none"
  });

  function showCheckedVals() {
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
    });
    // $(".hide").hide("slow");
  }
});
