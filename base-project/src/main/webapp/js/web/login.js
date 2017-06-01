$(function () {
	$('.keep-password').on('click', function () {
		if ($(this).hasClass('keep-password-active')) {
			$(this).removeClass('keep-password-active');
		}else{
			$(this).addClass('keep-password-active');
		}
	})
	$('.login-btn').on('click',function () {
		var username = $('#username').val();
		var password = $('#password').val();
		if (username.trim() == "" || password.trim() == "") {
			alert("用户名或密码不能为空！");
			return;
		}
		$.ajax({
			url:"api/userCol/login.do",
			method: "POST",
			async: true,
			data: {
				mobile: username,
				password: md5(password),
				appName: 'znxy'
			},
			success: function (res) {
				console.log(res);
				if (res.data.errorCode == 200) {
					sessionStorage.setItem('username', res.data.data.nickName ||　res.data.data.mobile);
                    localStorage.setItem('token', res.data.data.token);
                    localStorage.setItem('appName', "znxy");
					window.location.href = "index.do?token=" + res.data.data.token + "&appName=znxy";
				}else {
					alert("登录失败");
				}
			},
			error: function (err) {
				console.log(err);
			}
		})

	});

	$('.register-btn').on('click',function () {
		window.location.href = "register.do";
	});
})

// var Login = function () {
//
//     return {
//         //main function to initiate the module
//         init: function () {
//
//            $('.login-form').validate({
// 	            errorElement: 'label', //default input error message container
// 	            errorClass: 'help-inline', // default input error message class
// 	            focusInvalid: false, // do not focus the last invalid input
// 	            rules: {
// 	                username: {
// 	                    required: true
// 	                },
// 	                password: {
// 	                    required: true
// 	                },
// 	                remember: {
// 	                    required: false
// 	                }
// 	            },
//
// 	            messages: {
// 	                username: {
// 	                    required: "Username is required."
// 	                },
// 	                password: {
// 	                    required: "Password is required."
// 	                }
// 	            },
//
// 	            invalidHandler: function (event, validator) { //display error alert on form submit
// 	                $('.alert-error', $('.login-form')).show();
// 	            },
//
// 	            highlight: function (element) { // hightlight error inputs
// 	                $(element)
// 	                    .closest('.control-group').addClass('error'); // set error class to the control group
// 	            },
//
// 	            success: function (label) {
// 	                label.closest('.control-group').removeClass('error');
// 	                label.remove();
// 	            },
//
// 	            errorPlacement: function (error, element) {
// 	                error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
// 	            },
//
// 	            submitHandler: function (form) {
// 	                window.location.href = "index.do";
// 	            }
// 	        });
//
// 	        $('.login-form input').keypress(function (e) {
// 	            if (e.which == 13) {
// 	                if ($('.login-form').validate().form()) {
// 	                    window.location.href = "index.do";
// 	                }
// 	                return false;
// 	            }
// 	        });
//
// 	        $('.forget-form').validate({
// 	            errorElement: 'label', //default input error message container
// 	            errorClass: 'help-inline', // default input error message class
// 	            focusInvalid: false, // do not focus the last invalid input
// 	            ignore: "",
// 	            rules: {
// 	                email: {
// 	                    required: true,
// 	                    email: true
// 	                }
// 	            },
//
// 	            messages: {
// 	                email: {
// 	                    required: "Email is required."
// 	                }
// 	            },
//
// 	            invalidHandler: function (event, validator) { //display error alert on form submit
//
// 	            },
//
// 	            highlight: function (element) { // hightlight error inputs
// 	                $(element)
// 	                    .closest('.control-group').addClass('error'); // set error class to the control group
// 	            },
//
// 	            success: function (label) {
// 	                label.closest('.control-group').removeClass('error');
// 	                label.remove();
// 	            },
//
// 	            errorPlacement: function (error, element) {
// 	                error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
// 	            },
//
// 	            submitHandler: function (form) {
// 	                window.location.href = "index.do";
// 	            }
// 	        });
//
// 	        $('.forget-form input').keypress(function (e) {
// 	            if (e.which == 13) {
// 	                if ($('.forget-form').validate().form()) {
// 	                    window.location.href = "index.do";
// 	                }
// 	                return false;
// 	            }
// 	        });
//
// 	        jQuery('#forget-password').click(function () {
// 	            jQuery('.login-form').hide();
// 	            jQuery('.forget-form').show();
// 	        });
//
// 	        jQuery('#back-btn').click(function () {
// 	            jQuery('.login-form').show();
// 	            jQuery('.forget-form').hide();
// 	        });
//
// 	        $('.register-form').validate({
// 	            errorElement: 'label', //default input error message container
// 	            errorClass: 'help-inline', // default input error message class
// 	            focusInvalid: false, // do not focus the last invalid input
// 	            ignore: "",
// 	            rules: {
// 	                username: {
// 	                    required: true
// 	                },
// 	                password: {
// 	                    required: true
// 	                },
// 	                rpassword: {
// 	                    equalTo: "#register_password"
// 	                },
// 	                email: {
// 	                    required: true,
// 	                    email: true
// 	                },
// 	                tnc: {
// 	                    required: true
// 	                }
// 	            },
//
// 	            messages: { // custom messages for radio buttons and checkboxes
// 	                tnc: {
// 	                    required: "Please accept TNC first."
// 	                }
// 	            },
//
// 	            invalidHandler: function (event, validator) { //display error alert on form submit
//
// 	            },
//
// 	            highlight: function (element) { // hightlight error inputs
// 	                $(element)
// 	                    .closest('.control-group').addClass('error'); // set error class to the control group
// 	            },
//
// 	            success: function (label) {
// 	                label.closest('.control-group').removeClass('error');
// 	                label.remove();
// 	            },
//
// 	            errorPlacement: function (error, element) {
// 	                if (element.attr("name") == "tnc") { // insert checkbox errors after the container
// 	                    error.addClass('help-small no-left-padding').insertAfter($('#register_tnc_error'));
// 	                } else {
// 	                    error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
// 	                }
// 	            },
//
// 	            submitHandler: function (form) {
// 	                window.location.href = "index.do";
// 	            }
// 	        });
//
// 	        jQuery('#register-btn').click(function () {
// 	            jQuery('.login-form').hide();
// 	            jQuery('.register-form').show();
// 	        });
//
// 	        jQuery('#register-back-btn').click(function () {
// 	            jQuery('.login-form').show();
// 	            jQuery('.register-form').hide();
// 	        });
//         }
//
//     };
//
// }();