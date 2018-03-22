$(document).ready(function() {
	$('#statistics').DataTable({ 
		searching: false,
		order: [[4, "desc"]],
		pageLength: 25,
		lengthMenu: [ [25, 50, 100], [25, 50, 100]]
		});
});