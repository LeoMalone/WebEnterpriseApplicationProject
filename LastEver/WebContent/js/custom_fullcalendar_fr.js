$(document).ready(function() {
// page is now ready, initialize the calendar...

	$('#calendar').fullCalendar({
		header:{
			left: "month,agendaWeek",
			center: "title",
			right: "today prev,next"
		},
		eventRender: function(event, element) {
			element.popover({
				title: event.title,
				content: 'Lieu de jeu: ' + event.venue + '\nStatut du jeu: ' + event.gameStatus + '\n Score du jeu: ' + event.homeScore + ' - ' + event.awayScore,
				trigger: 'hover',
				placement: 'top',
				container: 'body'
			});
		},
		events: "/LastEver/calendarJson",
		defaultTimedEventDuration: '01:30:00',
		minTime: "10:00:00",
		maxTime: "22:30:00",
		theme: "bootstrap4",
		nowIndicator: "true",
		locale: "fr"
	});
});