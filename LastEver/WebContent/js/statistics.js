$(document).ready(function() {
	$('#statistics').DataTable({ 
		searching: false,
		order: [[4, "desc"]],
		pageLength: 25,
		lengthChange: false
		});
});