	initMainEvent();
	
function initMainEvent(){
	$('#body-container').load('http://localhost:8080/IamJuror/main.html');
	$('ul#top-navi li').on('click', loadBody);
};

// navi-top event 등록   
function loadBody(event)	{
	switch($(this).attr('id'))	{
		case 'nav-main': $('#body-container').load('http://localhost:8080/IamJuror/main.html'); break;
		case 'nav-overrall': $('#body-container').load('http://localhost:8080/IamJuror/contestList.html'); break;
		case 'nav-rank': $('#body-container').load('http://localhost:8080/IamJuror/hall_of_fame.html'); break;
		case 'person-info': $('#body-container').load('http://localhost:8080/IamJuror/profilePage.html'); break;	
		default: break;
	}
}   
