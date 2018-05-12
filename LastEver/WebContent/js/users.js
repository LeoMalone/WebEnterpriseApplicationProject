var cookie = "language";
var language;
var cookies = decodeURIComponent(document.cookie);
var cook = cookies.split(';');

var lang = {en: "//cdn.datatables.net/plug-ins/1.10.16/i18n/English.json", 
	fr: '//cdn.datatables.net/plug-ins/1.10.16/i18n/French.json'};

for(var i = 0; i < cook.length; i++){
	var c = cook[i];
	while(c.charAt(0) == ' '){
		c = c.substring(1);
	}
	if(c.indexOf(cookie) == 0){
		language = c.substring(cookie.length, c.length);
		if(language.charAt(0) == '=')
			language = language.substring(1);
	}
}

$(document).ready(function() {
	$('#users').DataTable({ 
		order: [[0, "asc"]],
		pageLength: 10,
		"lengthMenu": [ [10, 25, 50, -1], [10, 25, 50, "All"] ],
		"columns": [
		    { "searchable": false },
		    null,
		    null,
		    null,
		    { "searchable": false },
		    null,
		    { "searchable": false },
		    { "searchable": false },
		    { "searchable": false },
		    { "searchable": false },
		    { "searchable": false }
		  ],
		"language": {
			"url": lang[language]
		}
	});
});

$(document).ready(function() {
	$('#unactivated-users-table').DataTable({ 
		order: [[0, "asc"]],
		pageLength: 10,
		"lengthMenu": [ [10, 25, 50, -1], [10, 25, 50, "All"] ],
		"columns": [
		    { "searchable": false },
		    null,
		    null,
		    null,
		    { "searchable": false },
		    null,
		    { "searchable": false },
		    { "searchable": false }
		  ],
		"language": {
			"url": lang[language]
		}
	});
});