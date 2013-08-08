function sendUserData() {
	var user = {			
			userId 		: _id,				//아이디
			name		: _name,			//이름
			gender		: _gender,			//성별
			picture		: _picture,			//사진
			link		: _link				//담벼락 주소
	};
	
	$.post('sendUserData.do', 
		user, 
		
		function(data) {

		},
			
		'json'
		);
}

