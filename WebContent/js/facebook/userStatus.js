function login() {

	FB.login(function (response) {
		if(response.authResponse)	{
			
			FB.api('/me', function(response) {
				alert(response.name + ' 로그인');
				
		   		_id = response.id;
		   		_name = response.name;
		   		_gender = response.gender;
		   		_picture = 'https://graph.facebook.com/' + response.id + '/picture';
		   		_link = response.link;
		   	
				FB.getLoginStatus(function(response){
					TempUser.push(_id);
			   		TempUser.push(_name);
					TempUser.push(response.authResponse.accessToken);
					$.cookie('userInfo', TempUser ,{ path:'/' });
					alert("로그인 : " + $.cookie('userInfo'));
				});
		   					
				//서버로 유저 아이디를 보내서 가입한 유저인지 체크하고 미 가입자면 유저 정보 전송 및 담벼락에 글 쓰기
				$.getJSON('isMember.do?userId=' + _id , function(data) {
					alert("userCheck : " + data.result);
					if(data.result != 'OK')	{
						sendUserData();	
						addMember();
					}
				});
			});
		}
	}, {scope: 'email, publish_stream'}	);
}

function logout() {		
	FB.logout(function (response) {
		alert($.cookie('userInfo').split(',')[1] + " 로그아웃");
		$.cookie('userInfo',null,{ path:'/' } );
		window.location.reload();
	});
}


function addComment(commentObj) {
	
	var id = $.cookie('userInfo').split(',')[0];
	
	FB.api(id + '/feed?', 'post',
		{	
			message: $.cookie('userInfo').split(',')[1] + '님이 ' + commentObj.cheerUser + '을(를) 응원합니다.' + commentObj.comment, 
			access_token: $.cookie('userInfo').split(',')[2],
			link: 'http://localhost:8080/IamJuror/main.html',
			caption: '너도 도전을 하든지 평가를 하든지',
	        // picture: 'http://fbrell.com/f8.jpg',
	        // name: 'Facebook Dialogs',
		}, 
	
		function(response) {
			if (!response || response.error) {
				alert('담벼락 등록 실패');
			} else {
				alert('Post ID: ' + response.id);
			}
		});
}


function addMember() {
	
	var id = $.cookie('userInfo').split(',')[0];
	
	FB.api(id + '/feed?', 'post',
		{	
			message: $.cookie('userInfo').split(',')[1] + '님이' + '당신도 심사위원' + '앱에 가입하셨습니다.', 
			access_token: $.cookie('userInfo').split(',')[2],
			link: 'http://localhost:8080/IamJuror/main.html',
			caption: '너도 도전을 하든지 평가를 하든지'
	        //  picture: 'http://fbrell.com/f8.jpg',
	        // name: 'Facebook Dialogs',
		}, 
	
		function(response) {
			if (!response || response.error) {
				alert('담벼락 등록 실패');
			} else {
				alert('Post ID: ' + response.id);
			}
		});
}
//240192342759702|WiB4k0EROHamVEiO0NSP6DuhiQU