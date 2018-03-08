$(document).ready(function () {
	
	$('#datePickInput').datepicker({
		autoclose: true,
		todayHighlight: true,
		orientation: "bottom auto",
	    format: "yyyy-mm-dd"
	});
	
	$('.clockpicker').clockpicker({
		donetext: 'Done'
	});
});