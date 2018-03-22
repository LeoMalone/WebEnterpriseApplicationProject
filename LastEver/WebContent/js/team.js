$(document).ready(function() {
	$('#statistics').DataTable({ 
		searching: false,
		order: [[4, "desc"]]
	});
});

$(document).ready(function() {
	$('#schedule').DataTable({ 
		searching: false,
		order: [[0, "asc"]]
	});
});

$(document).ready(function() {
	$('#results').DataTable({ 
		searching: false,
		order: [[0, "desc"]]
	});
});