function Request(valuename)    // javascript로 웹페이지주소의 파라미터 긁어오기
{
    var rtnval;
    var nowAddress = unescape(location.href);
    var parameters = new Array();
    parameters = (nowAddress.slice(nowAddress.indexOf("?") + 1, nowAddress.length)).split("&");

    for (var i = 0; i < parameters.length; i++) {
        if (parameters[i].indexOf(valuename) != -1) {
            rtnval = parameters[i].split("=")[1];
            if (rtnval == undefined || rtnval == null) {
                rtnval = "";
            }
            return rtnval;
        }
    }
}

function checkBrower() {      // 현재 브라우저가 어떤 브라우저인지 체크하여 그 값을 반환
	 var agt=navigator.userAgent.toLowerCase();
	 if (agt.indexOf("opera") != -1) return 'Opera'; 
	 if (agt.indexOf("staroffice") != -1) return 'Star Office'; 
	 if (agt.indexOf("webtv") != -1) return 'WebTV'; 
	 if (agt.indexOf("beonex") != -1) return 'Beonex'; 
	 if (agt.indexOf("chimera") != -1) return 'Chimera'; 
	 if (agt.indexOf("netpositive") != -1) return 'NetPositive'; 
	 if (agt.indexOf("phoenix") != -1) return 'Phoenix'; 
	 if (agt.indexOf("firefox") != -1) return 'Firefox'; 
	 if (agt.indexOf("safari") != -1) return 'Safari'; 
	 if (agt.indexOf("skipstone") != -1) return 'SkipStone'; 
	 if (agt.indexOf("msie") != -1) return 'Internet Explorer'; 
	 if (agt.indexOf("netscape") != -1) return 'Netscape'; 
	 if (agt.indexOf("mozilla/5.0") != -1) return 'Mozilla'; 
	 if (agt.indexOf('\/') != -1) { 
	 	if (agt.substr(0,agt.indexOf('\/')) != 'mozilla') { 
	 		return navigator.userAgent.substr(0,agt.indexOf('\/'));
	 	} else return 'Netscape';
	 } else if (agt.indexOf(' ') != -1) return navigator.userAgent.substr(0,agt.indexOf(' ')); else return navigator.userAgent;
} 

function refresh() {
	location.href="http://localhost:8080/IamJuror/main.html";
}

function checkLength( o, n, min, max ) {
	if ( o.val().length > max || o.val().length < min ) {
		o.addClass( "ui-state-error" );
		return false;
	} else {
		return true;
	}
}

function checkBlank(o) {
	if( o.val() == "카테고리 선택") {
		o.addClass( "ui-state-error" );
		return false;
	} else if( o.val() == "공개/비공개 선택") {
		o.addClass( "ui-state-error" );
		return false;
	} else if( o.val() == "") {
		o.addClass( "ui-state-error" );
		return false;
	} else {
		return true;
	}
}

//function checkRegexp( o, regexp, n ) {
//if ( !( regexp.test( o.val() ) ) ) {
//	o.addClass( "ui-state-error" );
//	updateTips( n );
//	return false;
//} else {
//	return true;
//}
//}