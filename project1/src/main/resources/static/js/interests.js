window.onload() = function{
	getInterests();
}

function getInterests(){
	var backendAddress = '/interests';	//后端url
	
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open('post', backendAddress , true);
	xmlhttp.setRequestHeader("Content-type", "application/json");
	var senddata={};
	xmlhttp.send(JSON.stringify(senddata));	//发送数据
	xmlhttp.onreadystatechange = function () {
		if ( xmlhttp.status == 200){
			var ans = xmlhttp.responseText;
			ans = JSON.parse(ans);
			let httptext = "<h1>兴趣点：</h1>";
			httptext += "<h2 class=\"intContent\">南大共发论文数量: " + ans.paperCount + "篇</h2>";
			httptext += "<h2 class=\"intContent\">南大2019比2018多发论文" + ans.increase + "篇</h2>";
			httptext += "<h2 class=\"intContent\">南大论文涉及最多的5个关键词: " + ans.keywords[0]+", "+
																			ans.keywords[1]+", " + 
																			ans.keywords[2]+", " + 
																			ans.keywords[3]+", " + 
																			ans.keywords[4]+"</h2>";
			document.getElementById("interestTable").innerHTML = httptext;
		}
	}
}

function setRandom(Id){
	var obj = document.getElementById(Id);
	var top = Math.floor(Math.random()*100)+"%"
	obj.style.top = top;
	var left = Math.floor(Math.random()*100)+"%"
	obj.style.left = left;
	var r = Math.floor(Math.random()*255);
	var g = Math.floor(Math.random()*255);
	var b = Math.floor(Math.random()*255);
	obj.style.color = "rgb("+r+","+g+","+b+")";
}