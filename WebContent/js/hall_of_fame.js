/*demo용 url을 아무거나 지정*/
$.ajax({
	dataType:"json",
	url:"http://14.63.225.151/IamJuror/getUserFightList.do?userId=00000",
	async : false,
	success:function(data){
		printVideoMainSllider(data);
		printVideoSliderNavigator(data);
		printRankList(data);
	},
	error:function(){
		alert('서버와 통신할 수 없습니다.');
	}
});
/*지금은 사용하지 않는다.*/
function makeRandomColorClass(){
	var colorNumber = 'color'+Math.ceil(Math.random()*3);
	
};
	
function printVideoMainSllider(data){
	var inputHTML = '';
		/*데모용 삽입*/
		/*for(var i=0;i<data;i++){...}*/
		inputHTML += '<li><div class="representation-video-area-fixed color'+0+'">&nbsp;';
		inputHTML += '<div class="profile"><p class="title">VOICE TOP1<br/>최성봉 님<br/></p><p><a href="#">최근 대결로 바로가기</a></p></div>';
		inputHTML += '<div class="video">';
		inputHTML += '<iframe width="560" height="315" src="http://www.youtube.com/embed/tZ46Ot4_lLo?wmode=opaque" frameborder="0" allowfullscreen></iframe>';
		inputHTML += '<img src="" style="display:none;" /></div></div></li>';
		
		inputHTML += '<li><div class="representation-video-area-fixed color'+1+'">&nbsp;';
		inputHTML += '<div class="profile"><p class="title">VOICE TOP2<br/>수잔보일 님<br/></p><p><a href="#">최근 대결로 바로가기</a></p></div>';
		inputHTML += '<div class="video">';
		inputHTML += '<iframe width="560" height="315" src="http://www.youtube.com/embed/CVHXi8tqbQY?wmode=opaque" frameborder="0" allowfullscreen></iframe>';
		inputHTML += '<img src="" style="display:none;" /></div></div></li>';
		
		inputHTML += '<li><div class="representation-video-area-fixed color'+2+'">&nbsp;';
		inputHTML += '<div class="profile"><p class="title">DANCE TOP1<br/>DUAL Rafa.Anjiinho e Gaa alves! 님<br/></p><p><a href="#">최근 대결로 바로가기</a></p></div>';
		inputHTML += '<div class="video">';
		inputHTML += '<iframe width="560" height="315" src="http://www.youtube.com/embed/bpScHG6hWno?wmode=opaque" frameborder="0" allowfullscreen></iframe>';
		inputHTML += '<img src="" style="display:none;" /></div></div></li>';
		
		inputHTML += '<li><div class="representation-video-area-fixed color'+3+'">&nbsp;';
		inputHTML += '<div class="profile"><p class="title">DANCE TOP2<br/>Sam B 님<br/></p><p><a href="#">최근 대결로 바로가기</a></p></div>';
		inputHTML += '<div class="video">';
		inputHTML += '<iframe width="560" height="315" src="http://www.youtube.com/embed/sSc5gG0Ugqg?wmode=opaque" frameborder="0" allowfullscreen></iframe>';
		inputHTML += '<img src="" style="display:none;" /></div></div></li>';
		
		inputHTML += '<li><div class="representation-video-area-fixed color'+0+'">&nbsp;';
		inputHTML += '<div class="profile"><p class="title">Instrument TOP1<br/>MaTt Huguet 님<br/></p><p><a href="#">최근 대결로 바로가기</a></p></div>';
		inputHTML += '<div class="video">';
		inputHTML += '<iframe width="560" height="315" src="http://www.youtube.com/embed/6k3l3bku4E8?wmode=opaque" frameborder="0" allowfullscreen></iframe>';
		inputHTML += '<img src="" style="display:none;" /></div></div></li>';
		
		inputHTML += '<li><div class="representation-video-area-fixed color'+1+'">&nbsp;';
		inputHTML += '<div class="profile"><p class="title">Instrument TOP2<br/>정성하 님<br/></p><p><a href="#">최근 대결로 바로가기</a></p></div>';
		inputHTML += '<div class="video">';
		inputHTML += '<iframe width="560" height="315" src="http://www.youtube.com/embed/o1G7dhWBNCM?wmode=opaque" frameborder="0" allowfullscreen></iframe>';
		inputHTML += '<img src="" style="display:none;" /></div></div></li>';
		

	
	$('.main-slider-content ul').append(inputHTML);
};


function printVideoSliderNavigator(data){
	var inputHTML = '';
	/*데모용 삽입*/
	//for(var i=0;i<data;i++){..}
		inputHTML += '<li><div class="navigator-inner-div">';
		inputHTML += '<img src="img/person_1.jpg" />';
		inputHTML += '<p>장르 : 발라드<br/>순위 : TOP1!</p></div></li>';
		
		inputHTML += '<li><div class="navigator-inner-div">';
		inputHTML += '<img src="img/person_2.jpg" />';
		inputHTML += '<p>장르 : VOICE<br/>순위 : TOP2!</p></div></li>';
		
		inputHTML += '<li><div class="navigator-inner-div">';
		inputHTML += '<img src="img/person_3.jpg" />';
		inputHTML += '<p>장르 : DANCE<br/>순위 : TOP1!</p></div></li>';
		
		inputHTML += '<li><div class="navigator-inner-div">';
		inputHTML += '<img src="img/person_4.jpg" />';
		inputHTML += '<p>장르 : DANCE<br/>순위 : TOP2!</p></div></li>';
		
		inputHTML += '<li><div class="navigator-inner-div">';
		inputHTML += '<img src="img/person_5.jpg" />';
		inputHTML += '<p>장르 : Instrument<br/>순위 : TOP1!</p></div></li>';
		
		inputHTML += '<li><div class="navigator-inner-div">';
		inputHTML += '<img src="img/person_6.jpg" />';
		inputHTML += '<p>장르 : Instrument<br/>순위 : TOP2!</p></div></li>';
		
	
	$('.navigator-content ul').append(inputHTML);
};

function printRankList(data){
	var inputHTML = '';
	//for(var i=0;i<data;i++){
		inputHTML += '<tr><td class="rank-number">1위</td>';
		inputHTML += '<td><img src="img/person_1.jpg" /></td>';
		inputHTML += '<td>수잔보일</td><td>VOICE</td><td>lv23(18승)</td></tr>';
		
		inputHTML += '<tr><td class="rank-number">2위</td>';
		inputHTML += '<td><img src="img/person_2.jpg" /></td>';
		inputHTML += '<td>최성봉</td><td>VOICE</td><td>lv23(18승)</td></tr>';
		
		inputHTML += '<tr><td class="rank-number">3위</td>';
		inputHTML += '<td><img src="img/person_3.jpg" /></td>';
		inputHTML += '<td>Gaa alves</td><td>DANCE</td><td>lv23(18승)</td></tr>';
		
		inputHTML += '<tr><td class="rank-number">4위</td>';
		inputHTML += '<td><img src="img/person_4.jpg" /></td>';
		inputHTML += '<td>Sam B</td><td>DANCE</td><td>lv23(18승)</td></tr>';
		
		inputHTML += '<tr><td class="rank-number">5위</td>';
		inputHTML += '<td><img src="img/person_5.jpg" /></td>';
		inputHTML += '<td>MaTt Huguet </td><td>Instrument</td><td>lv23(18승)</td></tr>';
		
		inputHTML += '<tr><td class="rank-number">6위</td>';
		inputHTML += '<td><img src="img/person_6.jpg" /></td>';
		inputHTML += '<td>정성하</td><td>Instrument</td><td>lv23(18승)</td></tr>';
		
	$('.rank-content-wrap table').append(inputHTML);
};

