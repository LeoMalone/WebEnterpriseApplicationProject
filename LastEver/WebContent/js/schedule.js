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
	$('#schedule').DataTable({ 
		searching: false,
		order: [[0, "asc"]],
		pageLength: 25,
		lengthChange: false,
		"language": {
			"url": lang[language]
		}
	});
});