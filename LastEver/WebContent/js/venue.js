$(document).ready(function() {
	$('#schedule').DataTable({ 
		searching: false,
		order: [[0, "asc"]],
		pageLength: 20,
		lengthChange: false
	});
});