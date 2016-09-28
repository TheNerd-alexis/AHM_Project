
function submitCheck(){
	var _input = [];
	_input.push(document.getElementsByName("id")[0]);
	_input.push(document.getElementsByName("name")[0]);
	_input.push(document.getElementsByName("email")[0]);
	_input.push(document.getElementsByName("phone")[0]);
	_input.push(document.getElementsByName("pwd")[0]);
	_input.push(document.getElementsByName("pwdCheck")[0]);
	
	for(var i in _input){
		if(_input[i].value.length<1){
			_input[i].focus();
			alert('양식을 채워주세요.');
			return false;
		};
	}
	
	if(! pwdCheck()){
		alert('비밀번호가 일치하지 않습니다.');
		document.getElementsByName("pwd")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("idCheckFlag")[0].value < 0){
		alert('아이디 중복 확인하세요.');
		document.getElementsByName("idCheckBtn")[0].focus();
		return false;
	}
	
	return true;
}

function pwdCheck() {
	var _pwd = document.getElementsByName("pwd")[0].value;
	var _pwdCheck = document.getElementsByName("pwdCheck")[0].value;
	
	if(_pwd != _pwdCheck)
		return false;
	else
		return true;
}

function idCheck() {
	var _id = document.getElementsByName("id")[0].value;
	
	if (_id.length < 1)
		return false;
	
	$.ajax({
		url : "idCheck.do",
		data : {
			id : _id,
		},
		type : "post",
		success : function(data) {
			if(data.idCheckResult == true){
				document.getElementsByName("idCheckFlag")[0].value = -1;
				alert('중복된 아이디입니다.');
			}else{
				document.getElementsByName("idCheckFlag")[0].value = 1;
				alert('가입 가능한 아이디입니다.');
			}
		},
		error : function(request, status, error) {

			alert("error" + error);
		}
	});
	
}