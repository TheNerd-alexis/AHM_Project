<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>AJS HEALTH MANAGER</title>
<link rel="stylesheet" href="css/style.css">

<script src="https://code.jquery.com/jquery-1.12.4.min.js"
	integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
	crossorigin="anonymous">
	
</script>

<script src="js/script.js">
	
</script>

<script type="text/javascript">
$(function() {
	$("#menutab1").on("click",
					function() {
						$("#tab1").empty();
						$("#tab1")
								.append(
										'<c:if test="${!empty bodyinfo_key}">'
												+ '<br>'
												+ '<div align="center">'
												+ '<font color="darkblue" style="font-weight: bold;">[신체정보]</font>'
												+ '</div>'
												+ '<br>'
												+ '<div style="margin-left: 30%">'
												+ '<font style="font-weight: bold;">몸무게 - </font>${weight}kg <br>'
												+ '<font style="font-weight: bold;">키 - </font>${height}cm <br>'
												+ '<font style="font-weight: bold;">체지방률 - </font>${fat_rate}% <br>'
												+ '<font style="font-weight: bold;">근육량 - </font>${muscle_rate}% <br>'
												+ '<font style="font-weight: bold;">수분량 - </font>${water_rate}% <br>'
												+ '<font style="font-weight: bold;">비만도 - </font>${mass_index}% <br>'
												+ '</div>'
												+ '<br>'
												+ '</c:if>'
												+ '<c:if test="${empty bodyinfo_key}">'
												+ '<br>'
												+ '<div align="center">(해당 날짜에 입력된 신체정보가 없습니다)</div>'
												+ '<br>' + '</c:if>')
					});
	$("#menutab2").on("click",
			function() {
				$("#tab2").empty();
				$("#tab2")
						.append('<br><c:if test="${!empty exercise_key}">'
						+ '<font color="darkblue" style="font-weight: bold;">[${ex_title}]</font>'
						+ '<br><br>${content}<br><br>'
					+ '</c:if>'
					+ '<c:if test="${empty exercise_key}">'
					+ '<div align="center">(해당 날짜에 입력된 운동정보가 없습니다)</div>'
					+ '<br>'
					+ '</c:if>')
	});
	$("#menutab3").on("click",
			function() {
				$("#tab3").empty();
				$("#tab3")
						.append('<c:if test="${!empty daymenu_key}">'
						+ '<br>'
						+ '<div align="center">'
						+ '<font color="darkblue" style="font-weight: bold;">[${title}]</font>'
						+ '</div>'
						+ '<br>'
						+ '<div style="margin-left: 10%">'
						+ '<font style="font-weight: bold;">아침 - </font> ${morning}<br>'
						+ '<font style="font-weight: bold;">점심 - </font> ${lunch}<br>'
						+ '<font style="font-weight: bold;">저녁 - </font> ${dinner}<br>'
						+ '<br>'
						+ '</div>'
						+ '</c:if>'
						+ '<c:if test="${empty daymenu_key}">'
						+ '<br>'
						+ '<div align="center">(해당 날짜에 입력된 식단정보가 없습니다)</div>'
						+ '<br>'
						+ '</c:if>')
	});
	$("#menutab4").on("click",
			function() {
				$("#tab4").empty();
				$("#tab4")
						.append('<c:if test="${!empty message_key}">'
						+ '<br>'
						+ '<font color="darkblue" style="font-weight: bold;">[트레이너의 메시지]</font>'
						+ '<br><br>${message}<br>'
						+ '<br>'
						+ '</c:if>'
						+ '<c:if test="${empty message_key}">'
						+ '<br>'
						+ '<div align="center">(해당 날짜에 입력된 메시지가 없습니다)</div>'
						+ '<br>'
						+ '</c:if>')
	});
});
</script>


<script type="text/javascript">
	$(function() {

		$(".tab_content").hide();
		$(".tab_content:first").show();

		$("ul.tabs li").click(function() {

			$("ul.tabs li").removeClass("active").css("color", "black");
			$(this).addClass("active").css("color", "darkred");

			$(".tab_content").hide()
			var activeTab = $(this).attr("rel");
			$("#" + activeTab).fadeIn()

		});
	});
</script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
<script src="chart/Chart.min.js"></script>
</head>
<body>
	<div class="memberMain">
		<div class="memberMain-left"
			style="position: relative; height: 550px;">
			<div style="width: 50%">
				<canvas id="canvas" height="180" width="260"></canvas>
			</div>

			<script type="text/javascript">
				var week4 = function() {
					return ${weekfour};
				};
				var week3 = function() {
					return ${weekthree};
				};
				var week2 = function() {
					return ${weektwo};
				};
				var week1 = function() {
					return ${weekone};
				};
				var week = function() {
					return ${weight};
				};
				var months = [ "January", "February", "March", "April", "May",
						"June", "July", "August", "September", "October",
						"November", "December" ];
				var lineChart = null;
				var lineChartData = {
					labels : [ "4주전", "3주전", "2주전", "1주전", "현재"],
					datasets : [
							{
								label : "My First dataset",
								fillColor : "rgba(220,220,220,0.2)",
								strokeColor : "rgba(220,220,220,1)",
								pointColor : "rgba(220,220,220,1)",
								pointStrokeColor : "#fff",
								pointHighlightFill : "#fff",
								pointHighlightStroke : "rgba(220,220,220,1)",
								data : [ week4(),
								         week3(),
								         week2(),
								         week1(),
								         week()
								          ]
							},
							{
								label : "My Second dataset",
								fillColor : "rgba(151,187,205,0.2)",
								strokeColor : "rgba(151,187,205,1)",
								pointColor : "rgba(151,187,205,1)",
								pointStrokeColor : "#fff",
								pointHighlightFill : "#fff",
								pointHighlightStroke : "rgba(151,187,205,1)",
								data : [ week4(),
								         week3(),
								         week2(),
								         week1(),
								         week() ]
							} ]

				};

				$(function() {
					var ctx = document.getElementById("canvas")
							.getContext("2d");
					lineChart = new Chart(ctx).Line(lineChartData, {
						///Boolean - Whether grid lines are shown across the chart
						scaleShowGridLines : true,
						//String - Colour of the grid lines
						scaleGridLineColor : "rgba(0,0,0,0.05)",
						//Number - Width of the grid lines
						scaleGridLineWidth : 1,
						//Boolean - Whether the line is curved between points
						bezierCurve : true,
						//Number - Tension of the bezier curve between points
						bezierCurveTension : 0.4,
						//Boolean - Whether to show a dot for each point
						pointDot : true,
						//Number - Radius of each point dot in pixels
						pointDotRadius : 4,
						//Number - Pixel width of point dot stroke
						pointDotStrokeWidth : 1,
						//Number - amount extra to add to the radius to cater for hit detection outside the drawn point
						pointHitDetectionRadius : 20,
						//Boolean - Whether to show a stroke for datasets
						datasetStroke : true,
						//Number - Pixel width of dataset stroke
						datasetStrokeWidth : 2,
						//Boolean - Whether to fill the dataset with a colour
						datasetFill : true,
						onAnimationProgress : function() {
							console.log("onAnimationProgress");
						},
						onAnimationComplete : function() {
							console.log("onAnimationComplete");
						}
					});
				});

				

				$("canvas").on("click", function(e) {
					var activePoints = lineChart.getPointsAtEvent(e);
					console.log(activePoints);

					for ( var i in activePoints) {
						console.log(activePoints[i].value);
					}
				});
			</script>
			<br>
			<ul id="main_content_list">
				<div align="center">
					<font size="5">${member_name}</font>님의,
				</div>

				<c:if test="${!empty bodyinfo_key}">
					<font color="red" style="font-weight: bold;">[신체정보]</font>
					<br>
			몸무게(${weight}kg), 키(${height}cm), 체지방률(${fat_rate}%), 근육량(${muscle_rate}%), 수분량(${water_rate}%), 비만도(${mass_index}%) <br>
				</c:if>

				<c:if test="${!empty exercise_key}">
					<font color="red" style="font-weight: bold;">[운동세트]</font>
					<br>${content} <br>
				</c:if>

				<c:if test="${!empty daymenu_key}">
					<font color="red" style="font-weight: bold;">[식단]</font>
					<br>아침(${morning}), 점심(${lunch}), 저녁(${dinner}) <br>
				</c:if>

				<c:if test="${!empty message_key}">
					<font color="red" style="font-weight: bold;">[메시지]</font>
					<br>${message}
			</c:if>

				<c:if
					test="${empty bodyinfo_key && empty exercise_key && empty daymenu_key && empty message_key}">
					<br>
					<div align="center">오늘 정보가 없습니다</div>
				</c:if>

				<br>
				<br>
				<div style="position: absolute; bottom: 10px; margin-left: 40%;">
					<div align="center">
						<a href="logout.do">로그아웃</a>
					</div>
				</div>
			</ul>
		</div>

		<div class="memberMain-right">
			<div id="container">
				<ul class="tabs">
					<li id="menutab1" class="active" rel="tab1">상태</li>
					<li id="menutab2" rel="tab2">스케쥴</li>
					<li id="menutab3" rel="tab3">식 단</li>
					<li id="menutab4" rel="tab4">메시지</li>
				</ul>
				<div class="tab_container">
					<div id="tab1" class="tab_content">
						<c:if test="${!empty bodyinfo_key}">
							<br>
							<div align="center">
								<font color="darkblue" style="font-weight: bold;">[신체정보]</font>
							</div>
							<br>
							<div style="margin-left: 30%">
								<font style="font-weight: bold;">몸무게 - </font>${weight}kg <br>
								<font style="font-weight: bold;">키 - </font>${height}cm <br>
								<font style="font-weight: bold;">체지방률 - </font>${fat_rate}% <br>
								<font style="font-weight: bold;">근육량 - </font>${muscle_rate}% <br>
								<font style="font-weight: bold;">수분량 - </font>${water_rate}% <br>
								<font style="font-weight: bold;">비만도 - </font>${mass_index}% <br>
							</div>
							<br>
						</c:if>
						<c:if test="${empty bodyinfo_key}">
							<br>
							<div align="center">(해당 날짜에 입력된 신체정보가 없습니다)</div>
							<br>
						</c:if>
					</div>
					<div id="tab2" class="tab_content" align="center">
						<br>
						<c:if test="${!empty exercise_key}">
							<font color="darkblue" style="font-weight: bold;">[${ex_title}]</font>
							<br>
							<br>
		${content}
		<br>
							<br>
						</c:if>
						<c:if test="${empty exercise_key}">
							<div align="center">(해당 날짜에 입력된 운동정보가 없습니다)</div>
							<br>
						</c:if>
					</div>
					<div id="tab3" class="tab_content">
						<c:if test="${!empty daymenu_key}">
							<br>
							<div align="center">
								<font color="darkblue" style="font-weight: bold;">[${title}]</font>
							</div>
							<br>
							<div style="margin-left: 10%">
								<font style="font-weight: bold;">아침 - </font> ${morning}<br>
								<font style="font-weight: bold;">점심 - </font> ${lunch}<br>
								<font style="font-weight: bold;">저녁 - </font> ${dinner}<br>
								<br>
							</div>
						</c:if>
						<c:if test="${empty daymenu_key}">
							<br>
							<div align="center">(해당 날짜에 입력된 식단정보가 없습니다)</div>
							<br>
						</c:if>
					</div>
					<div id="tab4" class="tab_content" align="center">
						<c:if test="${!empty message_key}">
							<br>
							<font color="darkblue" style="font-weight: bold;">[트레이너의
								메시지]</font>
							<br>
							<br>
		${message}
		<br>
							<br>
						</c:if>
						<c:if test="${empty message_key}">
							<br>
							<div align="center">(해당 날짜에 입력된 메시지가 없습니다)</div>
							<br>
						</c:if>
					</div>

				</div>
			</div>
			<div id="kCalendar">
				<script>
					window.onload = function() {
						kCalendar('kCalendar');
					};
				</script>
			</div>

		</div>
	</div>
</body>
</html>
