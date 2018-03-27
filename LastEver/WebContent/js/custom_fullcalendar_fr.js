$(document).ready(function() {
// page is now ready, initialize the calendar...

	$('#calendar').fullCalendar({
		header:{
			left: "month,agendaWeek",
			center: "title",
			right: "today prev,next"
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