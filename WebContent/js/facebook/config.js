TempUser = new Array();

window.fbAsyncInit = function() {
	FB.init({ 
		appId: '248124368633532', 	//자신의 app id로 변경, 앱 설정에서 url도 변경
		status: true,
		cookie: true,
		xfbml: true,
		frictionlessRequests: true,
		useCachedDialogs: true,
		oauth: true
	});

	FB.Event.subscribe('auth.statusChange', status);
};

(function() {
	var e = document.createElement('script');
	e.type = 'text/javascript';
	e.src = document.location.protocol + '//connect.facebook.net/ko_KR/all.js';
	e.async = true;
	document.getElementById('fb-root').appendChild(e);
}());

//로그인,로그아웃,새로고침,페이지 전환 등등 시마다 유저 상태 확인
function status(response) {		
	
	if(response.status === 'not_authorized')
		document.body.className = 'not_authorized';
	else if(response.status === 'connected')	{
		document.body.className = 'connected';
		
		accessToken = response.authResponse.accessToken;
		
		FB.api('/me', function(response) {
			var name = '<a id="face-a" href="' + response.link + '"><span id="face-link">' + response.name + '</span></a>' + '&nbsp;&nbsp;&nbsp;' + '<a href=' + response.link + '><img src=img/Facebook_by_Geniuos.jpg /></a><br/>';
			var picture = '<a href="' + response.link +'"><img src=' + 'https://graph.facebook.com/' + response.id + '/picture'+'/></a>';
			
			$('#myInfo').append(name).append(picture);
			
			//페이스북에서 로그인을 하고 들어온 경우
			if(TempUser.length == 0)	{
				TempUser.push(response.id);
		   		TempUser.push(response.name);
				TempUser.push(accessToken);
				$.cookie('userInfo', TempUser ,{ path:'/' });
			}
		});
	}

}