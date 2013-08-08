/*facebook 아이디 겟*/
function getLoginUserId(){
	var userId = $.cookie('userInfo').split(',')[0];
	console.log(userId);
	return userId;
};


$.ajax({
	dataType:"json",
	url:"http://14.63.225.151/IamJuror/getJuror.do?userId="+getLoginUserId(),
	async : false,
	success:function(data){
		printUserInfo(data);
	},
	error:function(){
		alert('!서버와 통신할 수 없습니다.');
	}
});
/*수정후 화면 복구..다시 짠다면 이렇게 하지 않고 div를 덧씌우는 형태로 할것이다.*/
function printIntroduction(data){
	alert(data.jurorVO[0].selfIntroduction);
	var inputHTML = '';
	inputHTML +='<p>'+data.jurorVO[0].selfIntroduction+'</p>';
	inputHTML +='<a href="javascript:modifyForm();"class="profile-modify">수정</a></div>';
	
	$('.selfIntroduction').html(inputHTML);
}	

/*수정*/
$('#modify-form').live("submit",function(event){
	$.ajax({
		type : "post",
		url : "http://14.63.225.151/IamJuror/updateJuror.do",
		data : "selfIntroduction="+$('.selfIntroductionForm').val()+"&userId="+getLoginUserId()+"&nickName=닉네임받지않음",
		dataType : "json",
		async : true,
		success:function(data){
			alert('!:'+data.jurorVO[0].selfIntroduction);
			printIntroduction(data);

		},
		error:function(){
			alert('서버와 통신할 수 없습니다.');
		}
	});
	event.preventDefault();
	event.stopPropagation();
});
/*취소 시 이벤트*/
$('.modifyCancel').live("click",function(event){
	$.get("http://14.63.225.151/IamJuror/getJuror.do?userId="+getLoginUserId(),function(data){printIntroduction(data);});
	event.preventDefault();
});
/*더보기 시 이벤트*/
$('.viewMore').click(function(){
	$.get("http://14.63.225.151/IamJuror/getJuror.do?userId="+getLoginUserId(),function(data){
		printUploadedVideo(data);
	});
	return false;
});

$.ajax({
	dataType:"json",
	url:"http://14.63.225.151/IamJuror/getUserFightList.do?userId="+getLoginUserId(),
	async : false,
	success:function(data){
		printUploadedVideo(data);
	},
	error:function(){
		alert('서버와 통신할 수 없습니다.');
	}
});

/*validation을 만족하고 수정이 완료되면, 다시 정보 페이지로 돌아간다 */
/*로그인이 된 사용자인지 확인한다.*/
function modifyForm(){
	if(true){}//로그인
	
	var inputHTML = '';
	inputHTML += '<form id="modify-form" method="post">';
	inputHTML += '<textarea name="selfIntroduction" class="selfIntroductionForm"></textarea>';
	inputHTML += '</br><input type="submit" value="완료"/>';
	inputHTML += '<input type="button" class="modifyCancel" value="취소"/>';
	inputHTML += '</form>';
	
	$('.selfIntroduction').html(inputHTML);
}






function printUserInfo(data){
	var inputHTML = '';
	/*adding first template*/
	inputHTML +='<div class="user-bar first-templete">';
	inputHTML +='<div class="userPhoto"><img class="profile-img" src="'+data.jurorVO[0].picture+'"></div>';
	inputHTML +='<div class="userNameAndLevel"></div>';
	inputHTML +='<div class="userInfo">';
	inputHTML +='<p>이름&nbsp;&nbsp; :  &nbsp;&nbsp;'+data.jurorVO[0].name+'<br/>';
	inputHTML +='<p>등급&nbsp;&nbsp; : &nbsp;&nbsp;'+data.jurorVO[0].userLevel+'<br/>';
	inputHTML +='<p>전적&nbsp;&nbsp; : &nbsp;&nbsp;'+data.jurorVO[0].record+'<br/>';
	inputHTML +='<p>가입일&nbsp;&nbsp; : &nbsp;&nbsp;'+data.jurorVO[0].regDate+'</p>';
	inputHTML +='<p>SNS &nbsp;&nbsp; : &nbsp;&nbsp;<a href="'+data.jurorVO[0].link+'">facebook</a>';
	inputHTML +='</div>';
	inputHTML +='<div class="userInfo"><p>하고싶은말</p>';
	inputHTML +='<div class="selfIntroduction"><p>'+data.jurorVO[0].selfIntroduction+'</p>';
	inputHTML +='<a href="javascript:modifyForm();"class="profile-modify">수정</a></div></div>';
	inputHTML +='</div>';
  	
	$('#profile-content').append(inputHTML);
}
function printUploadedVideo(data){
	/*printing uploaded videos*/
	var inputHTML = '';
	for(var i=0;  i<data.fightList.length; i++){
		inputHTML +='<div class="user-bar">';
		inputHTML +='<div id="uploadedVideo">';
		inputHTML +='<img class="video-img one" src="http://i.ytimg.com/vi/'+data.fightList[i].artist1.video+'/0.jpg">';
		inputHTML +='<div class="vs">vs</div>';
		inputHTML +='<img class="video-img two" src="http://i.ytimg.com/vi/'+data.fightList[i].artist2.video+'/0.jpg">';
		inputHTML +='<div class="userInfo fightInfo">';
		inputHTML +='<p class="subTitle">'+data.fightList[i].fight.title+'</p>';
		inputHTML +='<p>'+data.fightList[i].fight.regDate+'</p>';
		inputHTML +='<p>'+((i%2)?"승":"패")+'</p></div></div></div>';
	}
	//result:승/패/대전중data.fightList[0].fight.
	$('#profile-content').append(inputHTML);
	
}

/*수정 후*/
