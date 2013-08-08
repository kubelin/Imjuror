alert('선택한 대결 번호 : ' + contestNo);

var artist1 = null;
var artist2 = null;
var contest = null;
var jurorId = "";
if($.cookie('userInfo') == null)
	$('#scoreBox').css('display','none');
else
	jurorId = $.cookie('userInfo').split(',')[0];

$.getJSON('http://14.63.225.151/IamJuror/checkArtistScore.do?fightNo=' + contestNo + '&jurorId=' + jurorId, function(data) {
	if(data.result == 'deny')
		$('#scoreBox').css('display','none');
});

//각각의 동영상에 대한 평가 (가로 슬라이더)
$("#slider-range-max").slider({
	range: "max",
	min: 0,
	max: 10,
	value: 5,
	slide: function( event, ui ) {
		$("#caption1").val( ui.value );
		$("#caption2").val( 10-(ui.value) );
	}
});

var vote1 = $("#slider-range-max").slider("value");
$("#caption1").val( vote1 );
$("#caption2").val( 10 - vote1 );

$('#vote-button').bind('click', function(){
	var user1Score = $("#slider-range-max").slider("value");
	var user2Score = 10 - user1Score;
	alert(user1Score + " : " + user2Score);
	
	//평가한 점수를 서버에 전송하고 합산된 결과를 리턴받아서 출력
	$.getJSON('http://14.63.225.151/IamJuror/addArtistScore.do?fightNo=' + contestNo + 
			'&jurorId=' + $.cookie('userInfo').split(',')[0] +
			'&artist1Id=' + artist1.facebookId + '&artist1Score=' + user1Score + 
			'&artist2Id=' + artist2.facebookId + '&artist2Score=' + user2Score, function(data) {
		
		var user1Score = Number(data.getArtistScore[0].score);
		var user2Score = Number(data.getArtistScore[1].score);
		
		$('#mainPointBar').css('width', ((user1Score / (user1Score + user2Score)) * 100) + '%');
		$('#scoreBox').css('display','none');
	});
});

$.ajax({
	url : 'http://14.63.225.151/IamJuror/getContest.do?fightNo=' + contestNo,
	dataType : 'json',
	success : function(data) {
		console.log(data);
		
		artist1 = new artistData(data.fightList[0].artist1, data.artistScore[0]);
		if(data.artistScore.length == 2)
			artist2 = new artistData(data.fightList[0].artist2, data.artistScore[1]);
		contest = new contestData(data.fightList[0].fight);		
		
		viewContestInfo(artist1, artist2, contest);
	}
});

function artistData(artistData, scoreData) {
	this.nickName = artistData.nickName;
	this.title = artistData.artistComment;
	this.facebookLink = artistData.link;
	this.facebookId = artistData.userId;
	this.youtubeVideoId = artistData.video;
	this.contestNo = artistData.fightNo;
	
	if(artistData.videoSequence == 1)
		this.sequence = 'competitor1';
	else
		this.sequence = 'competitor2';
	
	if(scoreData != null)
		this.score = scoreData.score;
	else
		this.score = "5";
}
function contestData(contestData) {
	this.title = contestData.title;
	this.content = contestData.content;
	this.contestNo = contestData.fightNo;
	this.genreNo = contestData.genreNo;
	this.isActive = contestData.isActive;
	this.startDate = (contestData.startDate).substr(0,10);
	this.endDate = (contestData.endDate).substr(0,10);
	//this.modData = (contestData.modDate).substr(0,10);
	//this.regDate = (contestData.regDate).substr(0,10);
	//this.score = contestData.score;
}


function viewContestInfo(artist1, artist2, contest) {

	$('#fightTitle').html('No. ' + contest.contestNo + '&nbsp&nbsp' + contest.title + ' (' + contest.startDate + ' ~ ' + contest.endDate + ') ');
	
	var user1Score = Number(artist1.score);
	var user2Score = Number(5);
	
	loadHotUT(artist1);
	
	if(artist2 != null)	{
		user2Score = Number(artist2.score);
		loadHotUT(artist2);
	}
	else	{		
		$('#hottest-compe2').html('<a id="get-fight-button" class="btn btn-danger" href="#" onclick="contest();">대 결 신 청</a>');
		$('#compe2-comment').html('현재 대결자가 없습니다.<br/> 도전하세요');
	}
	
	$('#mainPointBar').css('width', ((user1Score / (user1Score + user2Score)) * 100) + '%');
}	

function contest() {
	alert("대결 신청");
	//대결 신청 폼 출력하고 입력정보를 서버로 전송하고 신청한 유저의 데이터를 받음
			
	//버튼과 코멘트를 삭제
		$('#hottest-compe2').children().remove();
		$('#compe2-comment').html('');
		
	//대결 신청한 유저의 비디오와 닉네임 타이틀 설명 삽입
		
}

function loadHotUT(data) {
	
	var width = $('#hottest-compe').width(); 
	var params = { allowScriptAccess: "always" , allowFullScreen : "true"};
	var atts = { id: "ytPlayer" };

	swfobject.embedSWF("http://www.youtube.com/v/" + data.youtubeVideoId + "?version=3&enablejsapi=1&playerapiid=player1",
			data.sequence, width , (width/16)*10 , "9", null, null, params, atts);
	
	if(data.sequence == 'competitor1')
		$('#compe1-comment').html('<h4>' + data.nickName + '</h4>' + '<h3>' + data.title + '</h3>');
	else if(data.sequence == 'competitor2')
		$('#compe2-comment').html('<h4>' + data.nickName + '</h4>' + '<h3>' + data.title + '</h3>');
}

//********************************** Comment JS ******************************************** //
		

		//대결번호 및 타이틀 셋팅.
		// 현재 artist 식별 id가 두개 존재. 차후에 수정. 

		// comment를 위한 Number. 차후 수정 필요.
			artist1Num = 1;
			artist2Num = 1;

		//hide comment-container  		
			$('#comment-container').hide();
			
		//commentMenu 클릭시. 댓글 5개 로딩.
			$('#comment-menu').one('click', function(){
				getCommentList(1,1,'http://14.63.225.151/IamJuror/getCommentList.do');
			});
			
			$('#comment-menu').click(function(){
				$('#comment-container').fadeToggle('normal');
			});
			
		//소셜 버튼 클릭.
			$('#s-icons a').click(function(){
				$(this).toggleClass("active");
				if( $('#s-icons .active').size() > 1 ){
					alert('소셜 버튼은 한개만 선택해 주세요');
					$(this).toggleClass("active");	
				}
			});
			/**
			 *   reply 보내기 & 받기
			 */
			// 버튼마다 이벤트 등록.	
			$('#send-reply-user1').on('click', addComment);
			$('#send-reply-user2').on('click', addComment);
			$('#comment-delete').live('click', deleteComment);
			
			// TotalCount 변경 연결하고 변경.
			artist1TotalCount = 15;
			artist2TotalCount = 20;
			
			// 리플 삭제
			function deleteComment(event){
				var $getArtistId = $(this).parents()[3];
				var $targetDiv = $(this).parents()[2];
				
				$.ajax({
					url: 'http://localhost:8080/StudyGroup/deleteTest.do',
					dataType: 'json',
					type: 'get',
					/*
					 * 서버로 보내주는 인자값.
					 * fightNo & commentId & artistNo 
					 * 현재는 임시값.
					 */ 
					data: {
						'artist' : $getArtistId.id,
						'fightNo' : contestNo,
						'commentId': $targetDiv.id,
						
					},
					success: function(data){
							if( confirm('정말로 삭제 하시겠습니까?') == true ){
								$($targetDiv).remove();	
							}else{ return false;}
						},
						error: function(){
							alert('댓글 삭제 실패.');
						}
				});
				return false;
			}
			
			//	var sendId = $.cookie('userInfo').split(',')[0];
			var sendId = $.cookie('userInfo').split(',')[0];
				console.debug(sendId);
			// 현재 시간 얻기 ex) 1222-02-02
				 function getTime(){
					var tempDate = []; 
					var num = 0;
					var time = new Date();
					tempDate[num++] = time.getFullYear() + '-';
					tempDate[num++] = time.getMonth() + '-';
					tempDate[num++] = time.getDate();
					return tempDate.join(''); 
				};
			
			// 코맨트 validation
				function valiComment(event){
					if( event == 'send-reply-user1'){
						if(document.getElementById('comment-textarea1').value.length != 0){
							return document.getElementById('comment-textarea1').value;
						}else{
							alert('한 글자 이상을 입력 해 주세요');
							return false;
						}	
					}else{ 
						if(document.getElementById('comment-textarea2').value.length != 0){
							return document.getElementById('comment-textarea2').value;
						}else{
							alert('한 글자 이상을 입력 해 주세요');
							return false;
						}	
					}
					
				}
			
			// 코맨트 객체 얻기
				 function SetComment(competitor){
				 	this.artist = null;
					this.userId =  sendId;
					this.regDate = getTime();
					this.jurorComment = valiComment(competitor); 
				}
			
			// 리플 리스트 받기 from server ( default is 5 each , total 10)
			// data로 fightNo  - 여기선 contestNo 을 보내준다. 
			function getCommentList(fightNo, pageNo, url){
				var commentList = [];
				$.ajax({
					url: url,
					dataType: 'json',
					type: 'get',
					data: {
						'fightNo': fightNo,
						'pageNo' : pageNo
					},
					success: function(data){
						console.debug('서버로 부터 데이터가 뭐 왔나 볼까요');
						console.debug(data);
						// 서버로 부터 받는 데이터에 commentNo가 포함되어 있다.
						createComment(data);
					},
					error: function(){
						alert('댓글 트레픽 초과.');
					}
					
				});
			}
			
			// 리플 동적 생성.
			// 기본이 5개 . 그 이상은 생성 안함.
			function createComment(data){
				
				 var countCommentList1 = data.countCommentList1 - (artist1Num*5) ;
				 var countCommentList2 = data.countCommentList2 - (artist2Num*5);
				 
					for (var i = 0; i < 5; i++) {
						if (data.commentList1 == null || typeof data.commentList1[i] == "undefined") {
							break;
						}
						else {
							artist1TotalCount--;
							appendComment(i, data.commentList1[i], countCommentList1);
						}
					}
					for (var i = 0; i < 5; i++) {
						if (data.commentList2 == null || typeof data.commentList2[i] == "undefined" ) {
							break;
						}
						else {
							artist2TotalCount--;
							appendComment(i, data.commentList2[i], countCommentList2);
						}
					}
			}
			
			/* refactorying... 
			function returnCommentDiv(data){
			}
			*/
			// 코맨트를 붙인다. 
			function appendComment(index, data, totalCount){
					
					var beginComment = null;
					if( data.artist == '1' ){
						beginComment = '#begin-comment1';
					}
					else{ 
						beginComment = '#begin-comment2';
					}
						var tempDiv = new Array();
						var num = 0;
						tempDiv[num++] = '<div id="'+data.commentId+'" class="span12 row-fluid well" style="margin:0px; padding-bottom:0px;"><div class="span2"><table class="table"><tbody>';
						tempDiv[num++] = '<tr><td><img src="img/person_1.jpg"></td></tr>';
						tempDiv[num++] = '<tr><td>아이디</td></tr>';
						tempDiv[num++] = '<tr><td>'+ data.regDate +'</td></tr>';
						tempDiv[num++] = '</tbody></table></div>';
						tempDiv[num++] = '<div class="span9"><p class="alert alert-block">'+ data.jurorComment +' <br><br><br></div>';
						tempDiv[num++] = '<div class="span1"><div class="row-fluid">';
						tempDiv[num++] = '<div id="confirmNo'+ data.commentId +'" style="display:none;">'+data.userId+'</div>';
						tempDiv[num++] = '<div id="comment-delete" class="span12"><a id="'+data.userId+'" class="btn btn-small">삭제</a></div>';
						tempDiv[num++] = '</div></div></div>';
						
						$(beginComment).append(tempDiv.join(''));
						
						var confirmUser = $('#confirmNo'+data.commentId).text();
						// 세션에서 아이디 빼오기
						// 같은 아이디가 아니면 삭제 버튼 제거
						if ( confirmUser != sendId ) {
							$('#'+data.userId+'').remove();
						}
						
						
						console.debug(totalCount + '있냐 없냐');
						// Total이 0 이 아니고 index가 끝일 경우 '더보기' 추가. 
						// refactorying 필요.
						if( totalCount > 0 && index == 4){
							var insertMoreBtn = null;
							(data.artist == '1') ? insertMoreBtn = '#artist1' : insertMoreBtn = '#artist2'
							
							if( insertMoreBtn == '#artist1'){
								$(insertMoreBtn).children().last().after('<div class="span12 row-fluid well btn btn-primary btn-large" id="p1ExtraBtn" style="margin:0; padding-bottom:0;" onclick="getExtraComment(\'artist1\');" ><h3> 더 보기 (+5) </h3></div>');	
							}else{
								alert('ar2');
								$(insertMoreBtn).children().last().after('<div class="span12 row-fluid well btn btn-primary btn-large" id="p2ExtraBtn" style="margin:0; padding-bottom:0;" onclick="getExtraComment(\'artist2\');" ><h3> 더 보기 (+5) </h3></div>');
							}
						}
			}
			
			// get extra comment from server.
			function getExtraComment(artist,artist2TotalCount){
				if(artist == 'artist1'){
					artist1Num++;
					getCommentList(contestNo,artist1Num,'http://14.63.225.151/IamJuror/getComment1List.do');
					var temp = $('#p1ExtraBtn').detach();
						alert(artist);
						
					if(artist1TotalCount > 0){
						//temp.appendTo($('#artist1').children().last());	
					}
				} else{
					artist2Num++;
					getCommentList(contestNo,artist2Num,'http://14.63.225.151/IamJuror/getComment2List.do');
					$('#p2ExtraBtn').remove();
				} 
			}
			
			// 리플 서버에 보내기. 	
			/*
			 *  < parameters >
			 * 	fightNo & userId & artist & jurorComment 
			 *  date는 sysdata로 대체.
			 */
				function addComment(){
					
					var competitor = $(this).attr('id'); 
					var userComment = new SetComment(competitor);
					if (userComment.jurorComment == false) {
						return false;
					}else {
						if( competitor == 'send-reply-user1'){
							userComment.artist = 1;
						}else{
							userComment.artist = 2;
						}
						console.debug(userComment);
						$.ajax({
							url: 'addComment.do',
							dataType: 'json',
							type: 'post',
							data: {
								'fightNo': contestNo, 
								'userId': userComment.userId,
								'artist': userComment.artist,
								//'date': userComment.sendDate
								'jurorComment' : userComment.jurorComment
							},
							success: function(data){
								userComment.commentId = data.commentId;
								appendComment(userComment);	
							},
							error: function(){
								alert('댓글 트레픽 초과.');
							}
						});
					}	
				};
				/*
				 * 
				 *	리플 작성을 할 경우. 하나만 작성.
				 *  여기서 삭제 이벤트 동적 바인딩.
				 *  data에 Comment 번호를 넣어서 top div에 Id 값으로 넣어주자.
				 *   
				 */
				function createList(data, userComment){
					// 데이터 랭스 style="display:none;"
					 
					console.debug(data, userComment);
					var beginComment = null;
					if( userComment.cheerUser == 'send-reply-user1' ){
						beginComment = '#begin-comment1';
					}else{
						beginComment = '#begin-comment2';
					}
					
						var tempDiv = new Array();
						var num = 0;
						tempDiv[num++] = '<div id="'+data+'"class="span12 row-fluid well" style="margin:0px; padding-bottom:0px;"><div class="span2"><table class="table"><tbody>';
						tempDiv[num++] = '<tr><td><img src="img/person_1.jpg"></td></tr>';
						tempDiv[num++] = '<tr><td>아이디</td></tr>';
						tempDiv[num++] = '<tr><td>12-09-02</td></tr>';
						tempDiv[num++] = '</tbody></table></div>';
						tempDiv[num++] = '<div class="span9"><p class="alert alert-block">하하하하 It s demo comment <br><br><br></div>';
						tempDiv[num++] = '<div class="span1"><div class="row-fluid">';
						tempDiv[num++] = '<div id="confirmUser" style="display:none;">'+userComment.sendId+'</div>';
						tempDiv[num++] = '<div id="comment-delete" class="span12"><a class="btn btn-small">삭제</a></div>';
						tempDiv[num++] = '</div></div></div>';
						
						$(beginComment).after(tempDiv.join(''));
						
						// 아이디 비교를 위해 따로 저장한 hidden input
						var confirmUser = $('#confirmUser').text();
						
						// 같은 아이디가 아니면 코맨트 삭제
						if (confirmUser != userComment.sendId) {
							$('#comment-delete').remove();
						}
				}	
			