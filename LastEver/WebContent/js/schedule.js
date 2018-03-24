$(document).ready(function() {
	$('#schedule').DataTable({ 
		searching: false,
		order: [[0, "asc"]],
		pageLength: 25,
		lengthChange: false
	});
});