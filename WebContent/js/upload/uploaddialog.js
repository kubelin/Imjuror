// 이 플러그인의 기본 원리는 html의 form태그를 숨겨놓고 버튼을 클릭할 시에 이를 dialog창 형태로 띄우는 것임.

// 페이지에 있는 동영상 업로드 버튼 클릭 이벤트 정의 -> 246line
// 탭1의 업로드 버튼 클릭  이벤트 정의 -> 86line
// 탭1의 취소 버튼 클릭 이벤트 정의-> 183line
// 탭2의 업로드 버튼 클릭 이벤트 정의-> 193line
// 탭2의 취소 버튼 클릭 이벤트 정의-> 240line

$(function() {
	

	$( "#dialog:ui-dialog" ).dialog( "destroy" );
	
	var entryList;
	var entryId;
	
	var title = $( "#title" ),
		description = $( "#description" ),	
		category = $( "#category" ),
		fileUpload = $( "#fileUpload" ),
		allFields = $( [] ).add( title ).add( description ).add( category );
		//tips = $( ".validateTips" );	
	
	var title2 = $( "#title2" ),
		description2 = $( "#description2" ),	
		keyword2 = $( "#keyword2" ),
		category2 = $( "#category2" ),
		secret2 = $( "#secret2" ),
		videoid = $( "#videoid" ),
		allFields2 = $( [] ).add( title2 ).add( keyword2 ).add( description2 ).add( category2 ).add( secret2 );

	$( ".entry_fuck" ).live('click', function(){
		entryId = $(this).attr('id');
		//alert(entryId);
		
		$( ".entry_fuck" ).css("border-style", "solid").css("border-color","white").css("border-width","1px");
		$( "#"+entryId ).css("border-style", "solid").css("border-color","blue");
	});
	
	// 다이얼로그창  정의		
	$( "#dialog-form" ).dialog({
		autoOpen: false,
		open: function(event, ui) {
			if(checkBrower() == "Internet Explorer") {
				$( "#dialog-form" ).dialog( "option", "height", 425 );
				$( "#dialog-form" ).dialog( "option", "width", 450 );
				$( "#description" ).css("width", "408");
				$( "#tabs" ).css("height", "405");
			} else {
				$( "#dialog-form" ).dialog( "option", "height", 502 );
				$( "#dialog-form" ).dialog( "option", "width", 455 );	
				$( "#tabs" ).css("height", "460");
			}
	
			$( "#tabs" ).tabs();
		},
		modal: true, 	
		close: function() {
			allFields.val( "" ).removeClass( "ui-state-error" );
		}
	});
	
	
	// 업로드중 정의
	$( "#uploading" ).dialog({
		autoOpen: false,
		disabled: true,
		height: 255,
		width: 210,
		modal: true,
	//			buttons: {	
	//				"취소": function() {
	//					//$( "#dialog-form" ).dialog( "close" );
	//					
	//					refresh();
	//								
	//					$( "#uploading" ).dialog( "close" );
	//					
	//				}
	//			},
		close: function() {
			//location.href="http://localhost:8080/StudyGroup/upload.html";
			//refresh();
			//$( "#uploading" ).remove();
			allFields.val( "" ).removeClass( "ui-state-error" );
		}
	});
	
	
	// 탭1 엄로드 버튼 정의
	$( "#tab1upload" )
	.button()
	.click(function() {
		var bValid = true;
		allFields.removeClass( "ui-state-error" );
					
		bValid = bValid && checkLength( title, "title", 2, 50 );
		bValid = bValid && checkLength( description, "description", 6, 80 );
		bValid = bValid && checkBlank( fileUpload );
				
		if ( bValid ) {
			
			// success.do를 통해 videoUrl과 videoToken값을 얻어옴
			$.ajax({
				url:"./getVideoUrl.do",
				type:"post",
				dataType: "json",
				async:"false",
				data: {
					"title" : title.val(),
					"description" : description.val(),
					//"keyword" : keyword.val(),
					"category" : category.val(),
					//"secret" : secret.val(),
					//"token" : token
				},
				success: function(data) {
					//alert("getVideoUrl success");
					var videoUrl = data["videoUrl"];
					var videoToken = data["videoToken"];
					
					$( "#uploading" ).dialog( "open" );
					spinner("progress", 50, 80, 8, 25, "#444", 800);
					//spinner("progress", 20, 40, 8, 14, "#444", 800);
		            
					// youtube서버 측에 동영상을 올리고 그 동영상의 상태와 동영상id값을 얻어옴
		            $('#fileUploadForm').ajaxSubmit({
		            	url: videoUrl + "?token=" + videoToken + "&nexturl=http://localhost:8080/IamJuror/idSave.do",
		            	dataType: 'json',
		            	async:"false",
			            success: function(data) {
			            	alert("성공" + data["status"] + data["videoid"]);
			            },
			            error: function(xhr, option, error) {
			            						            					            	
			            	$.ajax({
			            		url:"./idReturn.do",
								type:"post",
								dataType: "json",
								async: "false",
								success: function(data) {
									var status = data["status"];
									var id = data["videoid"];
									//alert("status = " + status + "\n" + "id = " + id);
									// 직접 업로드 하기 val에 넣기
									$('#targetFile').val(id);
									
									
									// 세션에 저장하는 ajax
									$.ajax({
					            		url:"./sendDirect.do",
										type:"post",
										async:"false",
										dataType: "json",
										data: {
											"title" : title.val(),
											"description" : description.val(),
											"status" : status,
											"videoid" : id
										},
										success: function(data) {
											alert("동영상이 성공적으로 업로드되었습니다.");
											$( "#dialog-form" ).dialog( "close" );
											$("#uploading").dialog( "close" );
											$('#submit-btn').attr('class','btn btn-primary btn-large');
											//$('progress').spinner('remove')
										},
										error: function(xhr, option, error) {
											alert(xhr.responseText);
										}
					            	});
					            	
								},
								error: function(xhr, option, error) {
									alert(xhr.responseText);
								}
			            	});
			            	// error code end
						} 
		            });  // $('#fileUploadForm').ajaxSubmit end
		            
				}, // success end
				error: function(xhr, option, error) {
					alert(xhr.responseText);
				}
			});
		
			
		} else {
			alert("모든 항목이 채워지지 않았거나 입력한 글자 수가 잘못되었습니다.");
		} // valid end
	});
	
	
	// 탭1 취소 버튼 정의
	$( "#tab1cancel" )
	.button()
	.click(function() {
		
		$( "#dialog-form" ).dialog( "close" );
	});
	
	
	// 탭2 업로드 버튼 정의
	$( "#tab2upload" )
	.button()
	.click(function() {
		var bValid = true;
		allFields2.removeClass( "ui-state-error" );
					
		//bValid = bValid && checkLength( title2, "title2", 2, 50 );
		//bValid = bValid && checkLength( description2, "description2", 6, 80 );
		//bValid = bValid && checkLength( keyword2, "keyword2", 2, 30 );
		//bValid = bValid && checkBlank( category2 );
		//bValid = bValid && checkBlank( secret2 );
		//bValid = bValid && checkBlank( videoid );
		
		//bValid = bValid && checkRegexp( name, /^[a-z]([0-9a-z_])+$/i, "Username may consist of a-z, 0-9, underscores, begin with a letter." );
		// From jquery.validate.js (by joern), contributed by Scott Gonzalez: http://projects.scottsplayground.com/email_address_validation/
		//bValid = bValid && checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. ui@jquery.com" );
		//bValid = bValid && checkRegexp( password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9" );
	
		if ( bValid ) {
			
			$.ajax({
				url:"./sendLink.do",
				type:"post",
				dataType: "json",
				data: {
					"videoid" : entryId 
				},
				success: function(data) {
					//alert("동영상 ID정보를 서버에 업로드했습니다.")
					$( "#dialog-form" ).dialog( "close" );
				}, // success end
				error: function(data) {

				} // success end
			});  // $.ajax end
		
		} else {
			alert("모든 항목이 채워지지 않았거나 입력한 글자 수가 잘못되었습니다.");
		}			
		
			
		//$( "#uploading" ).dialog( "close" );
		//$( "#dialog-form" ).dialog( "close" );
	});
	
	
	// 탭2 취소 버튼 정의	
	$( "#tab2cancel" )
	.button()
	.click(function() {
		
		//alert(entryList);
		
		$( "#dialog-form" ).dialog( "close" );
	});
	
	// 메인 페이지 업로드 버튼 정의
	$( "#upload-video" )
		.button()
		.click(function() {
			
			// checkout.do를 통해 세션에 token값이 저장되어 있는지 확인 
			$.ajax({
				url:"./checkout.do",
				type:"post",
				dataType: "json",
				success: function(data) {
					var token = data["token"];
					//alert(token);
					
					if(token == null) {
				        var config = {
				            'client_id': '566725873556.apps.googleusercontent.com',
				            'scope' : ['https://gdata.youtube.com','https://www.googleapis.com/auth/plus.me']
				        };
				              
				        gapi.auth.authorize(config, function() {
				        	var temp = gapi.auth.getToken();
				            access_token = temp.access_token;
			                //alert("access_token : " + access_token);
			                
				            // fake.do를 통해 세션에 UserName과 token을 저장
			                $.ajax({
			                	url: "./fake.do",
			                	data: {
			                		"token" : access_token,
			                	},
			                	async: "false",
			                	type:"post",
			                	dataType: "json",
			                	success: function(data) {
			                		//console.log("success");	                	
			                	},
			                	error: function(xhr, option, error) {
			                		//console.log(xhr.responseText);
			                	}
			                });

	
				        });
				        
					} else if(token != null){
						
						$.ajax({
                			url: "./receive.do",
                			type: "post",
                			dataType: "json",
                			async: "false",
                			success: function(data) {
                				//console.log("this is token is not null");
                				//console.log(data.entryVOList[0].thumbnail_default);
                				entryList = data;
                				
                				var st= "<ul id='videoUl'>";
                				
                				for(var i=0;i<data.entryVOList.length;i++) {
                					st = st + "<li><a href='#'><img style = 'margin: 5px;' class ='entry_fuck' src=" + data.entryVOList[i].thumbnail_default + " id=" + data.entryVOList[i].videoid + "></a></li>";
                				}
                				
                				st = st + "</ul>";
                				
                				$( "#tabs-2-insert" ).html(st);
                				
                				$( "#dialog-form" ).dialog( "open" );
                			}
                		});
                		
                		
					}
				},
				error: function(xhr, option, error) {
					alert(xhr.responseText);
				}	
			});
			
	});

});