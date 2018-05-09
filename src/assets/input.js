
$('.txtOnly').bind('keydown', function(event) {
    var key = event.which;
    if (key >=48 && key <= 57) {
      event.preventDefault();
    }
  });


  