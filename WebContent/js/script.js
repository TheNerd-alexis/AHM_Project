function kCalendar(id, date) {
	var kCalendar = document.getElementById(id);

	if (typeof (date) !== 'undefined') {
		date = date.split('-');
		date[1] = date[1] - 1;
		date = new Date(date[0], date[1], date[2]);
	} else {
		var date = new Date();
	}
	var currentYear = date.getFullYear();
	// 년도를 구함

	var currentMonth = date.getMonth() + 1;
	// 연을 구함. 월은 0부터 시작하므로 +1, 12월은 11을 출력

	var currentDate = date.getDate();
	// 오늘 일자.

	date.setDate(1);
	var currentDay = date.getDay();
	// 이번달 1일의 요일은 출력. 0은 일요일 6은 토요일

	var dateString = new Array('sun', 'mon', 'tue', 'wed', 'thu', 'fri', 'sat');
	var lastDate = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
	if ((currentYear % 4 === 0 && currentYear % 100 !== 0)
			|| currentYear % 400 === 0)
		lastDate[1] = 29;
	// 각 달의 마지막 일을 계산, 윤년의 경우 년도가 4의 배수이고 100의 배수가 아닐 때 혹은 400의 배수일 때 2월달이 29일
	// 임.

	var currentLastDate = lastDate[currentMonth - 1];
	var week = Math.ceil((currentDay + currentLastDate) / 7);
	// 총 몇 주인지 구함.

	if (currentMonth != 1)
		var prevDate = currentYear + '-' + (currentMonth - 1) + '-'
				+ currentDate;
	else
		var prevDate = (currentYear - 1) + '-' + 12 + '-' + currentDate;
	// 만약 이번달이 1월이라면 1년 전 12월로 출력.

	if (currentMonth != 12)
		var nextDate = currentYear + '-' + (currentMonth + 1) + '-'
				+ currentDate;
	else
		var nextDate = (currentYear + 1) + '-' + 1 + '-' + currentDate;
	// 만약 이번달이 12월이라면 1년 후 1월로 출력.

	if (currentMonth < 10)
		var currentMonth = '0' + currentMonth;
	// 10월 이하라면 앞에 0을 붙여준다.

	var calendar = '';

	calendar += '<div id="header">';
	calendar += '			<span><a href="#" class="button left" onclick="kCalendar(\''
			+ id + '\', \'' + prevDate + '\')"><</a></span>';
	calendar += '			<span id="date">' + currentYear + '년 ' + currentMonth
			+ '월</span>';
	calendar += '			<span><a href="#" class="button right" onclick="kCalendar(\''
			+ id + '\', \'' + nextDate + '\')">></a></span>';
	calendar += '		</div>';
	calendar += '		<table border="0" cellspacing="0" cellpadding="0">';
	calendar += '			<caption>' + currentYear + '년 ' + currentMonth
			+ '월 달력</caption>';
	calendar += '			<thead>';
	calendar += '				<tr>';
	calendar += '				  <th class="sun" scope="row">일</th>';
	calendar += '				  <th class="mon" scope="row">월</th>';
	calendar += '				  <th class="tue" scope="row">화</th>';
	calendar += '				  <th class="wed" scope="row">수</th>';
	calendar += '				  <th class="thu" scope="row">목</th>';
	calendar += '				  <th class="fri" scope="row">금</th>';
	calendar += '				  <th class="sat" scope="row">토</th>';
	calendar += '				</tr>';
	calendar += '			</thead>';
	calendar += '			<tbody>';

	var dateNum = 1 - currentDay;

	for (var i = 0; i < week; i++) {
		calendar += '			<tr>';
		for (var j = 0; j < 7; j++, dateNum++) {
			if (dateNum < 1 || dateNum > currentLastDate) {
				calendar += '				<td class="' + dateString[j] + '"> </td>';
				continue;
			}
			calendar += '				<td class="' + dateString[j] + '">'
					+ '<a href = "javascript:findInformation(' + currentYear
					+ ', ' + currentMonth + ', ' + dateNum + ');">' + dateNum
					+ '</a>' + '</td>';
		}
		calendar += '			</tr>';
	}

	calendar += '			</tbody>';
	calendar += '		</table>';

	kCalendar.innerHTML = calendar;
}

function findInformation(year, month, date) {

	if (date < 10)
		date = '0' + date;
	if (month < 10)
		month = '0' + month;

	var myDate = year + '' + month + '' + date;

	if ($("#menutab1").hasClass("active")) {
		$.ajax({
			url : "dayBodyInfo.do",
			data : {
				date : myDate
			},
			type : "post",
			success : function(data) {
				$("#tab1").empty();
				var result = '';
				if (data.dayBodyInfo != null) {
					result += '<br>'
						+ '<div align="center"><font color="darkblue" style="font-weight: bold;" >[신체정보]</font></div><br>'
						+ '<div style="margin-left: 30%">'					
						+ '<font style="font-weight: bold;">몸무게 - </font>' + data.dayBodyInfo.weight + 'kg <br>'
						+ '<font style="font-weight: bold;">키 - </font>' + data.dayBodyInfo.height + 'cm <br>'
						+ '<font style="font-weight: bold;">체지방률 - </font>' + data.dayBodyInfo.fat_rate + '% <br>'
						+ '<font style="font-weight: bold;">근육량 - </font>' + data.dayBodyInfo.muscle_rate + '% <br>'
						+ '<font style="font-weight: bold;">수분량 - </font>' + data.dayBodyInfo.water_rate + '% <br>'
						+ '<font style="font-weight: bold;">비만도 - </font>' + data.dayBodyInfo.mass_index + '% <br>'
						+ '</div>'
						+ '<br>'	
			} else {
				 result += '<br><div align="center" ><font color="gray">(해당 날짜에 입력된 신체정보가 없습니다)</font></div><br>'
			 }
				$("#tab1").append(result);
			},
			error : function(request, status, error) {

				alert("error" + error);
			}
		});
	}

	if ($("#menutab2").hasClass("active")) {

		$.ajax({
			url : "dayExercise.do",
			data : {
				date : myDate
			},
			type : "post",
			success : function(data) {
				$("#tab2").empty();
				 var result = '';
				 if (data.dayExercise != null) {
					 result += '<br><font color="darkblue" style="font-weight: bold;" >[' + data.dayExercise.ex_title + ']</font><br><br>'
				 + data.dayExercise.content + '<br><br>'
			} else {
				 result += '<br><div align="center" ><font color="gray">(해당 날짜에 입력된 운동정보가 없습니다)</font></div><br>'
			 }
				$("#tab2").append(result);
			},
			error : function(request, status, error) {

				alert("error" + error);
			}
		});
	}

	if ($("#menutab3").hasClass("active")) {

		$.ajax({
			url : "dayMenu.do",
			data : {
				date : myDate
			},
			type : "post",
			success : function(data) {
				$("#tab3").empty();
				 var result = '';
				 if (data.dayMenu != null) {
						result += '<br><div align="center" ><font color="darkblue" style="font-weight: bold;" >[' + data.dayMenu.title + ']</font></div>'
						+ '<br>'
						+ '<div style="margin-left: 10%">'
						+ '<font style="font-weight: bold;">아침 - </font>' + data.dayMenu.morning + '<br>'
						+ '<font style="font-weight: bold;">점심 - </font>' + data.dayMenu.lunch + '<br>'
						+ '<font style="font-weight: bold;">저녁 - </font>' + data.dayMenu.dinner + '<br>'
						+ '<br></div></div>'
				 } else {
					 result += '<br><div align="center" ><font color="gray">(해당 날짜에 입력된 식단정보가 없습니다)</font></div><br>'
				 }
				$("#tab3").append(result);
			},
			error : function(request, status, error) {

				alert("error" + error);
			}
		});
	}

	if ($("#menutab4").hasClass("active")) {
		$.ajax({
			url : "dayMessage.do",
			data : {
				date : myDate
			},
			type : "post",
			success : function(data) {
				$("#tab4").empty();
				var result = '';
				if (data.dayMessage != null) {
					result += '<br><font color="darkblue" style="font-weight: bold;" >[트레이너의 메시지]</font><br><br>'
				+ data.dayMessage.message
				+ '<br><br>'
				} else {
					 result += '<br><div align="center" ><font color="gray">(해당 날짜에 입력된 메시지가 없습니다)</font></div><br>'
				 }
				$("#tab4").append(result);
			},
			error : function(request, status, error) {

				alert("error" + error);
			}
		});
	}
}
