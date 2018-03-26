$(document).ready(function() {
	$('#statistics').DataTable({ 
		searching: false,
		order: [[4, "desc"]],
		lengthChange: false
	});
});

$(document).ready(function() {
	$('#schedule').DataTable({ 
		searching: false,
		order: [[0, "asc"]],
		pageLength: 15,
		lengthChange: false
	});
});

$(document).ready(function() {
	$('#results').DataTable({ 
		searching: false,
		order: [[0, "desc"]],
		pageLength: 15,
		lengthChange: false
	});
});