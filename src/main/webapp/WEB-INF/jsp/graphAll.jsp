<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>시세정보 조이</title>

    <!-- Bootstrap Core CSS -->
    <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Theme CSS -->
    <link href="/css/clean-blog.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<!-- 그래프 소스 추가 -->
<!--    <link type="text/css" rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css">
	<link type="text/css" rel="stylesheet" href="https://tech.shutterstock.com/rickshaw/src/css/graph.css">
	<link type="text/css" rel="stylesheet" href="https://tech.shutterstock.com/rickshaw/src/css/detail.css">
	<link type="text/css" rel="stylesheet" href="https://tech.shutterstock.com/rickshaw/src/css/legend.css">
	<link type="text/css" rel="stylesheet" href="https://tech.shutterstock.com/rickshaw/examples/css/extensions.css?v=2">
 -->
 
	<script src="https://tech.shutterstock.com/rickshaw/vendor/d3.v3.js"></script>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>

	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.15/jquery-ui.min.js"></script>

	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Class.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Compat.ClassList.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Renderer.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Renderer.Area.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Renderer.Line.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Renderer.Bar.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Renderer.ScatterPlot.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Renderer.Stack.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.RangeSlider.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.RangeSlider.Preview.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.HoverDetail.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Annotate.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Legend.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Axis.Time.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Behavior.Series.Toggle.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Behavior.Series.Order.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Behavior.Series.Highlight.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Smoother.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Fixtures.Time.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Fixtures.Time.Local.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Fixtures.Number.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Fixtures.RandomData.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Fixtures.Color.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Color.Palette.js"></script>
	<script src="https://tech.shutterstock.com/rickshaw/src/js/Rickshaw.Graph.Axis.Y.js"></script>

	<script src="https://tech.shutterstock.com/rickshaw/examples/js/extensions.js"></script>

<!-- 그래프 소스 추가 -->

</head>
<body>

<!-- Navigation -->
    <nav class="navbar navbar-default navbar-custom navbar-fixed-top">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    Menu <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand" href="/index.html">Start Bootstrap</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="/index.html">Home</a>
                    </li>
                    <li>
                        <a href="about.html">About</a>
                    </li>
                    <li>
                        <a href="post.html">Sample Post</a>
                    </li>
                    <li>
                        <a href="contact.html">Contact</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Header -->
    <!-- Set your background image for this header on the line below. -->
    <header class="intro-header" style="background-image: url('/img/post-bg.jpg')">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <div class="post-heading">
                        <h1>주식시세정보</h1>
                        <h2 class="subheading">현재 주가 및 검색 트랜드</h2>
                        <!-- <span class="meta">Posted by <a href="#">Start Bootstrap</a> on August 24, 2014</span> -->
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- Post Content -->
    <article>
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                
                    
                    <div id="chartContainer" style="height: 600px; width: 100%;"></div>
					
                
                    <p>Never in all their history have men been able truly to conceive of the world as one: a single sphere, a globe, having the qualities of a globe, a round earth in which all the directions eventually meet, in which there is no center because every point, or none, is center — an equal earth which all men occupy as equals. The airman's earth, if free men make it, will be truly round: a globe in practice, not in theory.</p>

                    <p>Science cuts two ways, of course; its products can be used for both good and evil. But there's no turning back from science. The early warnings about technological dangers also come from science.</p>

                    <p>What was most significant about the lunar voyage was not that man set foot on the Moon but that they set eye on the earth.</p>

                    <p>A Chinese tale tells of some men sent to harm a young girl who, upon seeing her beauty, become her protectors rather than her violators. That's how I felt seeing the Earth for the first time. I could not help but love and cherish her.</p>

                    <p>For those who have seen the Earth from space, and for the hundreds and perhaps thousands more who will, the experience most certainly changes your perspective. The things that we share in our world are far more valuable than those which divide us.</p>

                    <h2 class="section-heading">The Final Frontier</h2>

                    <p>There can be no thought of finishing for ‘aiming for the stars.’ Both figuratively and literally, it is a task to occupy the generations. And no matter how much progress one makes, there is always the thrill of just beginning.</p>

                    <p>There can be no thought of finishing for ‘aiming for the stars.’ Both figuratively and literally, it is a task to occupy the generations. And no matter how much progress one makes, there is always the thrill of just beginning.</p>

                    <blockquote>The dreams of yesterday are the hopes of today and the reality of tomorrow. Science has not yet mastered prophecy. We predict too much for the next year and yet far too little for the next ten.</blockquote>

                    <p>Spaceflights cannot be stopped. This is not the work of any one man or even a group of men. It is a historical process which mankind is carrying out in accordance with the natural laws of human development.</p>

                    <h2 class="section-heading">Reaching for the Stars</h2>

                    <p>As we got further and further away, it [the Earth] diminished in size. Finally it shrank to the size of a marble, the most beautiful you can imagine. That beautiful, warm, living object looked so fragile, so delicate, that if you touched it with a finger it would crumble and fall apart. Seeing this has to change a man.</p>

                    <a href="#">
                        <img class="img-responsive" src="/img/post-sample-image.jpg" alt="">
                    </a>
                    <span class="caption text-muted">To go places and do things that have never been done before – that’s what living is all about.</span>

                    <p>Space, the final frontier. These are the voyages of the Starship Enterprise. Its five-year mission: to explore strange new worlds, to seek out new life and new civilizations, to boldly go where no man has gone before.</p>

                    <p>As I stand out here in the wonders of the unknown at Hadley, I sort of realize there’s a fundamental truth to our nature, Man must explore, and this is exploration at its greatest.</p>

                    <p>Placeholder text by <a href="http://spaceipsum.com/">Space Ipsum</a>. Photographs by <a href="https://www.flickr.com/photos/nasacommons/">NASA on The Commons</a>.</p>
                </div>
            </div>
        </div>
    </article>

    <hr>

    <!-- Footer -->
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <ul class="list-inline text-center">
                        <li>
                            <a href="#">
                                <span class="fa-stack fa-lg">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-twitter fa-stack-1x fa-inverse"></i>
                                </span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="fa-stack fa-lg">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-facebook fa-stack-1x fa-inverse"></i>
                                </span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="fa-stack fa-lg">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-github fa-stack-1x fa-inverse"></i>
                                </span>
                            </a>
                        </li>
                    </ul>
                    <p class="copyright text-muted">Copyright &copy; Your Website 2016</p>
                </div>
            </div>
        </div>
    </footer>

    <!-- jQuery -->
    <script src="/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Contact Form JavaScript -->
    <script src="/js/jqBootstrapValidation.js"></script>
    <script src="/js/contact_me.js"></script>

    <!-- Theme JavaScript -->
    <script src="/js/clean-blog.min.js"></script>

	<!-- for chart -->
	<script src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script>
	<script src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>


<script>

$(document).ready(function() {
	getHistoricalData();
});


function getHistoricalData() {
	
	$.get("/marketdata/historicalPrice/${marketCode}/${issueCode}", function(data, status){
		if (status == "success")
		{
			console.info(data);
			var jsonData = JSON.parse(data);
			console.info(data.length);
			graphDraw(jsonData);
		}
	});
}


function graphDraw(jsonObj) {
	var options = {
		title: {
			text: ${name}
		},
		animationEnabled: true,
		exportEnabled: true,
		data: [
		{
			type: "spline", //change it to line, area, column, pie, etc
			dataPoints: [
				/* { x: 10, y: 10 },
				{ x: 20, y: 12 },
				{ x: 30, y: 8 },
				{ x: 40, y: 14 },
				{ x: 50, y: 6 },
				{ x: 60, y: 24 },
				{ x: 70, y: -4 },
				{ x: 80, y: 10 } */
			]
		}
		]
	};
	
	
	for(var i = 0; i < jsonObj.length; i++) {
	    var obj = jsonObj[i];
		console.log(i);
	    console.log(obj);
	    options.data[0].dataPoints.push( { x : obj.BzDd , y : obj.trdPrc  });
	    					    
	}
	
	$("#chartContainer").CanvasJSChart(options);
}

function graphDraw2() {

	var options = {
		exportEnabled: true,
		animationEnabled: true,
		title:{
			text: "주가 VS 검색트랜드"
		},
		subtitles: [{
			text: "Click Legend to Hide or Unhide Data Series"
		}],
		axisX: {
			title: "States"
		},
		axisY: {
			title: "Units Sold",
			titleFontColor: "#4F81BC",
			lineColor: "#4F81BC",
			labelFontColor: "#4F81BC",
			tickColor: "#4F81BC",
			includeZero: false
		},
		axisY2: {
			title: "Profit in USD",
			titleFontColor: "#C0504E",
			lineColor: "#C0504E",
			labelFontColor: "#C0504E",
			tickColor: "#C0504E",
			includeZero: false
		},
		toolTip: {
			shared: true
		},
		legend: {
			cursor: "pointer",
			itemclick: toggleDataSeries
		},
		data: [{
			type: "spline",
			name: "Units Sold",
			showInLegend: true,
			xValueFormatString: "MMM YYYY",
			yValueFormatString: "#,##0 Units",
			dataPoints: [
				{ x: new Date(2016, 0, 1),  y: 120 },
				{ x: new Date(2016, 1, 1), y: 135 },
				{ x: new Date(2016, 2, 1), y: 144 },
				{ x: new Date(2016, 3, 1),  y: 103 },
				{ x: new Date(2016, 4, 1),  y: 93 },
				{ x: new Date(2016, 5, 1),  y: 129 },
				{ x: new Date(2016, 6, 1), y: 143 },
				{ x: new Date(2016, 7, 1), y: 156 },
				{ x: new Date(2016, 8, 1),  y: 122 },
				{ x: new Date(2016, 9, 1),  y: 106 },
				{ x: new Date(2016, 10, 1),  y: 137 },
				{ x: new Date(2016, 11, 1), y: 142 }
			]
		},
		{
			type: "spline",
			name: "Profit",
			axisYType: "secondary",
			showInLegend: true,
			xValueFormatString: "MMM YYYY",
			yValueFormatString: "$#,##0.#",
			dataPoints: [
				{ x: new Date(2016, 0, 1),  y: 19034.5 },
				{ x: new Date(2016, 1, 1), y: 20015 },
				{ x: new Date(2016, 2, 1), y: 27342 },
				{ x: new Date(2016, 3, 1),  y: 20088 },
				{ x: new Date(2016, 4, 1),  y: 20234 },
				{ x: new Date(2016, 5, 1),  y: 29034 },
				{ x: new Date(2016, 6, 1), y: 30487 },
				{ x: new Date(2016, 7, 1), y: 32523 },
				{ x: new Date(2016, 8, 1),  y: 20234 },
				{ x: new Date(2016, 9, 1),  y: 27234 },
				{ x: new Date(2016, 10, 1),  y: 33548 },
				{ x: new Date(2016, 11, 1), y: 32534 }
			]
		}]
	};
	
	$("#chartContainer").CanvasJSChart(options);

	function toggleDataSeries(e) {
		if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
			e.dataSeries.visible = false;
		} else {
			e.dataSeries.visible = true;
		}
		e.chart.render();
	}
}


</script>
</body>
</html>