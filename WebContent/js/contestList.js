// 동영상 팝업 설정
$(function(){	
   $.nyroModalSettings({
		processHandler : function(settings) {
			$.nyroModalSettings({
				type : 'swf',
				height : 385,
				width : 640
			});
		}
	});   
});

$("#enroll").click(function(){
	$('#showList').load('http://localhost:8080/IamJuror/applyContest.html');
});


//페이징 설정
var pagingParam = {
	'totalSize' : 0,	// 전체 자료 개수
	'pageNo' : 1,		// 현재 페이지
	'pageSize': 5,      // 페이지 당 개수
	'pageListSize': 5,  // 페이지 바
	'pageClickFunctionName': 'viewContestList'
};
var selectGenre = 'selectAll';
viewContestList(1);	  

//if($.cookie('userInfo') == null)
//	$('#enroll').attr('class','btn btn-success btn-large disabled');

//왼쪽 사이드 탭에서 다른 메뉴 선택시 목록 갱신
$('li.contestList').bind('click', function() {
	$('li.contestList').removeClass('active');
	$(this).addClass('active');
	selectGenre = this.id;
	viewContestList(1);
});
		
//모든 목록 제거
function removeContestList() {
	$('#showList').children().remove();
}

//선택한 메뉴의 목록 보기 + 페이징
function viewContestList(pageNo) {
	
	removeContestList();
	
	var isActive = 'isActive=1&';
	if(!($('#contestStatus')[0]).checked)
		isActive = 'isActive=0&';
	
	$.getJSON('http://14.63.225.151/IamJuror/getContestListCount.do?' + isActive + 'selectGenre=' + selectGenre, function(data) {
		$('#showList').html($('<h3 style="padding-left:12%; margin-bottom:2%;">대결 목록의 수는 ' + data.fightCount + '개</h3>'));
		pagingParam.totalSize = data.fightCount;	//해당 장르의 전체 개수 변경
	});
	
	$.getJSON('http://14.63.225.151/IamJuror/getContestList.do?' + isActive + 'selectGenre=' + selectGenre + '&pageNo=' + pageNo, function(data) {
		$.each(data.fightList, setContestList);
		$('#showList').append('<div id="paging_bar" align="center"></div>');
		$('.nyroModal').nyroModal();
		pagingParam.pageNo = pageNo;			//현재 페이지 변경
		$('#paging_bar').magefister4jPaging(pagingParam);
	});
}

function setContestList(index, value)	{
	
	console.log(value);
	
	var border_span = $('<div class="span12"></div>');
	var leftSide_span = $('<div class="span1"></div>');
	var rightSide_span = $('<div class="span1"></div>');
	var center_span = $('<div class="span10"></div>');
	
	var center_span_top = $('<div id="viewContest"></div>').html('<span>no. ' + value.fight.fightNo + '&nbsp&nbsp</span>' + 
							'<img src="img/thumbs-up.png" /><span id="up" style="color:silver;">' + '&nbsp&nbsp' + value.fight.score + '&nbsp&nbsp' +
							(value.fight.startDate).substr(0,10) + ' ~ ' + (value.fight.endDate).substr(0,10))
							.on("click", function () {
								$('#body-container').load('http://localhost:8080/IamJuror/contestView.html');
								contestNo = value.fight.fightNo;
							});
	
	var center_span_middle = $('<div class="row-fluid"></div>');
	
	var center_span_middle_left = $('<div class="span5"></div>');
	if(value.artist1)	
		center_span_middle_left.append(middle_contents(value.artist1));
	else	
		center_span_middle_left.append(middle_contents("")).on("click", function() {
			$('#body-container').load('http://localhost:8080/IamJuror/contestView.html');
			contestNo = value.fight.fightNo;
		});
	
	var center_span_middle_center = $('<div class="span2"></div>');

	var center_span_middle_right = $('<div class="span5"></div>');
	if(value.artist2)	
		center_span_middle_right.append(middle_contents(value.artist2));
	else	
		center_span_middle_right.append(middle_contents("")).on("click", function() {
			$('#body-container').load('http://localhost:8080/IamJuror/contestView.html');
			contestNo = value.fight.fightNo;
		});
	
	var bottom_span = $('<div class="well" id="contest_title"></div>').html(value.fight.title).bind("click", function() {
		$('#body-container').load('http://localhost:8080/IamJuror/contestView.html');
		contestNo = value.fight.fightNo;
	});
	
	center_span_middle.append(center_span_middle_left).append(center_span_middle_center).append(center_span_middle_right);
	center_span.append(center_span_top).append(center_span_middle).append(bottom_span);
	border_span.append(leftSide_span).append(center_span).append(rightSide_span);
	
	$('#showList').append(border_span);
}

function middle_contents(artist) {
	if(artist.length == 0)
		return  '<span align="center"><h4 id="waitingText">도전하세요!</h4></span><a href="#"><img src="img/waiting.jpg" id="waitingImg" /></a>'; 
				
	else		
		return  '<a href='+ artist.link + '><h4 align="center">' + artist.nickName + '</h4></a>' +
				'<a href="http://www.youtube.com/v/' + artist.video + '" class="nyroModal">' +																				
				'<img src=' + 'http://i.ytimg.com/vi/' + artist.video + '/0.jpg onmouseout="mouseOutImage(this)" onmouseover="mousOverImage(this)" style="border:3px solid #fff" />' + 
				'</a>';
}

function mousOverImage(img){
	img.style.border = "3px solid #C6F";
}
function mouseOutImage(img){
	img.style.border = "3px solid #fff";
}
	 