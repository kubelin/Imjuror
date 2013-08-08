var selectGenre = "selectAll";

	getFamousValue();
	initMainEvent();

function initMainEvent(){
	
	$('#vote1').live('click', function() {
		$('#body-container').load('http://localhost:8080/IamJuror/contestView.html');
	});

	$('.contestList').on('click', function() {
		$('.contestList').removeClass('active');
		$(this).addClass('active');
		selectGenre = this.id;
		$('#galleryTitle').html(selectGenre + '의 TOP 5');
		getFamousValue();
	});
	
	$('#who-label').tooltip({title : '당신의 한표를 기다립니다 ^^',  placement : 'right'});
	$('#hot-label').tooltip({title : '가장 뜨거운 대결!!',  placement : 'top'});
};

 

// 메인 대결을 불러온다.
function getHotValue(data){
	($('#hottest-compe object:first').attr('id') == 'ytPlayer') ? removeCompeDivs() : createCompeDivs();
	createCompeDivs();
	loadHotUT(data.artist1);
	loadHotUT(data.artist2);
}

// competitorDIV 생성
function createCompeDivs(){
	$('#hottest-compe').prepend('<div id="competitor1"></div>');  
	$('#hottest-compe2').prepend('<div id="competitor2"></div>');
}

// competitorDIV 삭제
function removeCompeDivs(){
	$('object').remove();
}

// 핫한 대결을 가져오자.	
function loadHotUT(data) {
	var width = $('#hottest-compe').width(); 
	var params = { allowScriptAccess: "always" , allowFullScreen : "true"};
	var atts = { id: "ytPlayer" };
	var position = (data.videoSequence == '2')? 'competitor1' : 'competitor2';
	
	swfobject.embedSWF("http://www.youtube.com/v/" + data.video + "?version=3&enablejsapi=1&playerapiid=player1", position, width , ((width/16)*11) , "9", null, null, params, atts);
	
	if(position == 'competitor1')
		$('#compe1-comment').html('<h4>' + data.nickName + '</h4>' + '<h4>' + data.artistComment + '</h4>');
	else if(position == 'competitor2')
		$('#compe2-comment').html('<h4>' + data.nickName + '</h4>' + '<h4>' + data.artistComment + '</h4>');
}

//인기 대결 가져오기 받을때 5명분을 받아야 함.
function getFamousValue(){
	$('#img_gallery').children().remove();
	$('#carousel .es-carousel ul').children().remove();
	$.getJSON('http://14.63.225.151/IamJuror/getFamousList.do?selectGenre=' + selectGenre, function(data) {
		$.each(data.fightList, loadFamousUT);
		
		$('#carousel').elastislide({
			imageW 		: 500,
			minItems	: 3,
			margin		: 0,
			border		: 0,
			onClick: function(data){ exchangeMain(data); } 
		});	
		$('#carousel').elastislide('destroy');	
	});
}

// 인기 있는 대결들을 로딩
function loadFamousUT(index, data)	{ 
	if(index == 0)	{
		getHotValue(data);
		contestNo = data.fight.fightNo;
	}

	var tempDIV = [];
	tempDIV.push('<li><span id="thumb-li"><img src="http://i.ytimg.com/vi/',data.artist1.video,'/1.jpg" alt="image01" style="margin:0px"/>vs');
	tempDIV.push('<img src="http://i.ytimg.com/vi/',data.artist2.video,'/1.jpg" alt="image01" style="margin:0px"/><span>');
	tempDIV.push('<div id="famous-comment" class="comment" style="margin-right:18px">',data.artist1.nickName,'</div>');
	tempDIV.push('<div id="famous-comment" class="comment">',data.artist2.nickName,'</div><div value="fa1" style="display:none;">',data.artist1.artistComment,'</div><div value="fa2" style="display:none;">',data.artist2.artistComment,'</div></span></span></li>');

	$('#carousel .es-carousel ul').append(tempDIV.join(''));
}

//famouseImg -> hottest Competition 으로.
function exchangeMain(data){
	// 동영상 switch
	$('#hottest-compe object').replaceWith(loadTempObject(data.find('img')[0].src.split('/')[4]));
	$('#hottest-compe2 object').replaceWith(loadTempObject(data.find('img')[1].src.split('/')[4]));
	// 타이틀 및 제목 switch
	$('#compe1-comment').html('<h4>' + data.find('span')[1].childNodes[0].textContent + '</h4>' + '<h4>' + data.find('span')[1].childNodes[2].textContent + '</h4>');
	$('#compe2-comment').html('<h4>' + data.find('span')[1].childNodes[1].textContent + '</h4>' + '<h4>' + data.find('span')[1].childNodes[3].textContent + '</h4>');
}

function loadTempObject(videoId){
	var width = $('#hottest-compe').width();
	return '<object type="application/x-shockwave-flash" id="ytPlayer"'+
			'data="http://www.youtube.com/v/'+ videoId +'?version=3&amp;enablejsapi=1&amp;playerapiid=player1" width="'+ width +'" height="'+ (width/16)*11 +'">'+
			'<param name="allowScriptAccess" value="always"><param name="allowFullScreen" value="true"></object>';
}