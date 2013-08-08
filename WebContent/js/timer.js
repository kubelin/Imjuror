getLastestContestList();
setInterval(getLastestContestList, 360000);
setInterval(get_current_time, 1000); 

function getLastestContestList() {
	$.getJSON('getLastestContestList.do?', function(data) {
		var temp='<li class="nav-header" id="time"></li>';

		$.each(data.fightList, function(index, data) {
			temp += '<li><span>' + data.fightNo + '. ' + data.name + '님 대결 등록 </span></li>';
		});
		
		$('#update-list').html(temp);
	});
}

function get_current_time(){
     var time = document.getElementById( "time" );
     var now = new Date();
     var week = new Array("일","월","화","수","목","금","토");

     var clock = '신규 대결 등록<br/> (';
    // clock += now.getFullYear() + "/";
     clock += (now.getMonth() + 1) + "월";
     clock += now.getDate() + "일 ";
     clock += week[now.getDay()] + "요일 ";

     clock += now.getHours() + "시 기준)";
     
     time.innerHTML= clock;
}