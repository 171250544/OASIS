
new Vue({
	el:'#app',
	data(){
		return{
			activeIndex: "2",
			input: '',
			options: [{
				value: 'author',
				label: '作者'
			}, {
				value: 'affiliations',
				label: '机构'
			}, {
				value: 'ieeeTerm',
				label: '研究方向'
			}],
			value: 'author',
			rank:[
				// {
				// 	name: "Li Hua",
				// 	uri: "12",
				// 	activeness: 123
				// }
			],
			results:[],
			searchType: 0	//0代表无效，1代表搜索的是作者，2代表搜索的是机构，3代表搜索的是研究方向
		}
	},
	methods:{
		handleSelect(key, keyPath) {
			switch(key){
				case "1":
//					window.location.href="./main.html";
					  window.location.href="/main"
					break;
				case "2":
					break;
				case "3":
//					window.location.href="./manage_login.html";
					 window.location.href="/manage_login";
					break;
				default:
					console.log("err: "+key);
			}
		},
		submit(){
			if(this.input.length == 0){
				alert("内容不能为空!");
			}
			else{

				var searchAuthorAdd = "/getAuthorActivenessByName"	//搜索作者的后端url
				var searchAffAdd = "/getAffActivenessByName"	//搜索机构的后端url
				var searchTermAdd = "/getTermActivenessByName"	//搜索研究方向的后端url

				var rankAuthorAdd = "/getAllAuthorActiveness"	//作者活跃度排行榜url
				var rankAffAdd = "/getAllAffActiveness"		//机构活跃度排行榜url
				var rankTermAdd = "/getAllTermActiveness"	//研究方向活跃度排行榜url

				var finalAdd = "";
				var rankAdd = "";

				if(this.value == "author"){
					finalAdd = searchAuthorAdd;
					this.searchType = 1;
					rankAdd = rankAuthorAdd;
				}
				else if(this.value == "affiliations"){
					finalAdd = searchAffAdd;
					this.searchType = 2;
					rankAdd = rankAffAdd;
				}
				else if(this.value == "ieeeTerm"){
					finalAdd = searchTermAdd;
					this.searchType = 3;
					rankAdd = rankTermAdd;
				}

				var xmlhttp = new XMLHttpRequest();
				var self = this;
				self.results = [];
				self.rank = [];
				xmlhttp.open('post', finalAdd , true);
				xmlhttp.setRequestHeader("Content-type", "application/json");
				var data = {name: self.input};
				xmlhttp.send(JSON.stringify(data));
				xmlhttp.onreadystatechange = function(){
					if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
						var ans = xmlhttp.responseText;
						console.log(ans);
						ans = JSON.parse(ans);
						if(self.searchType == 1){
							for(var i = 0; i< ans.authorIds.length; i++){
								var temp = {
									name: ans.authorNames[i],
									uri: ans.authorIds[i],
									activeness: ans.authorActiveness[i]
								};
								self.results.push(temp);
							}
						}
						else if(self.searchType == 2){
							for(var i = 0; i< ans.affIds.length; i++){
								var temp = {
									name: ans.affNames[i],
									uri: ans.affIds[i],
									activeness: ans.affActiveness[i]
								};
								self.results.push(temp);
							}
						}
						else if(self.searchType == 3){
							for(var i = 0; i< ans.termNames.length; i++){
								var temp = {
									name: ans.termNames[i],
									uri: ans.termNames[i],
									activeness: ans.termActiveness[i]
								};
								self.results.push(temp);
							}
						}
					}
				}
				var xmlhttp2 = new XMLHttpRequest();
				var self = this;
				xmlhttp2.open('post', rankAdd , true);
				xmlhttp2.setRequestHeader("Content-type", "application/json");
				var data = {};
				xmlhttp2.send(JSON.stringify(data));
				xmlhttp2.onreadystatechange = function(){
					if(xmlhttp2.readyState == 4 && xmlhttp2.status == 200){
						var ans = xmlhttp2.responseText;
						console.log(ans);
						ans = JSON.parse(ans);
						if(self.searchType == 1){
							for(var i = 0; i< ans.authorIds.length; i++){
								var temp = {
									name: ans.authorNames[i],
									uri: ans.authorIds[i],
									activeness: ans.authorActiveness[i]
								};
								self.rank.push(temp);
							}
						}
						else if(self.searchType == 2){
							for(var i = 0; i< ans.affIds.length; i++){
								var temp = {
									name: ans.affNames[i],
									uri: ans.affIds[i],
									activeness: ans.affActiveness[i]
								};
								self.rank.push(temp);
							}
						}
						else if(self.searchType == 3){
							for(var i = 0; i< ans.termNames.length; i++){
								var temp = {
									name: ans.termNames[i],
									uri: ans.termNames[i],
									activeness: ans.termActiveness[i]
								};
								self.rank.push(temp);
							}
						}
					}
				}
			}
		},
		getChart(uri){

			//todo	更改获得图的url
			var chartAuthorAdd = "/getAuthorChart"	//作者图数据url
			var chartAffAdd = "/getAffChart"		//机构图数据url
			var chartTermAdd = "/getTermChart"	//研究方向图数据url


			var finalAdd = "";

			if(this.searchType == 1){
				finalAdd = chartAuthorAdd;
			}
			else if(this.searchType == 2){
				finalAdd = chartAffAdd;
			}
			else if(this.searchType == 3){
				finalAdd = chartTermAdd;
			}

			var xmlhttp = new XMLHttpRequest();
			var self = this;
			xmlhttp.open('post', finalAdd , true);
			xmlhttp.setRequestHeader("Content-type", "application/json");

			//alert 这里的data是url不同而变化的，如果是author和aff就是id，int格式，如果是term就是名字，String格式
			//		属性名都叫uri，对应三个方法
			//		相应的后端方法中均以params.get('uri')来获得参数
			var data = {uri: uri};
			xmlhttp.send(JSON.stringify(data));

			xmlhttp.onreadystatechange = function(){
				if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
					var ans = xmlhttp.responseText;
					console.log(ans);

					// 后端返回应为一个对象，包含三个属性：data, links, categories
					// data为点的数据（list）
					// links为边的数据（list）
					// categories为都有哪些种类（list）
					ans = JSON.parse(ans);

					//实际生成图，参数为后端传过来的data, links, categories
					self.generateChart(ans.data, ans.links, ans.categories);
				}
			}
		},
		generateChart(inputData, inputLinks, categories){
			var self = this;
			var dom = document.getElementById("chart");
			var myChart = echarts.init(dom);
			var app = {};
			option = null;
			myChart.showLoading();
			// $.get('les-miserables.gexf', function (xml) {
			    // var graph = echarts.dataTool.gexf.parse(xml);
			    // var categories = [];
			    // for (var i = 0; i < 9; i++) {
			    //     categories[i] = {
			    //         name: '类目' + i
			    //     };
			    // }
			    // graph.nodes.forEach(function (node) {
			    //     node.itemStyle = null;
			    //     node.value = node.symbolSize;
			    //     node.symbolSize /= 1.5;
			    //     node.label = {
			    //         show: node.symbolSize > 30
			    //     };
			    //     node.category = node.attributes.modularity_class;
			    // });
			option = {
				title: {
					text: self.input,
					subtext: 'Default layout',
					top: 'bottom',
					left: 'right'
				},
				tooltip: {},
				legend: [{
					// selectedMode: 'single',
					data: categories.map(function (a) {
						return a;
					})
				}],
				animationDuration: 1500,
				animationEasingUpdate: 'quinticInOut',
				series : [
					{
						name: 'Les Miserables',
						type: 'graph',
						layout: 'none',
						data: inputData,
						// nodes = [
						// 	{	//node1
						// 		name: "名字",
						// 		des: "鼠标移动上去时的描述",
						// 		symbolSize: 100,	//待议，预计为点的描述的大小
						// 		itemStyle: {}	,//待议，点的css
						// 		category: 1,	//点的分类
						// 		x = 145,	//x坐标
						// 		y = 243,	//y坐标
						// 		draggable = true, //是否可拖动
						// 		value = 23		//待议，一般与symbolSize设为相同，预计为点大小
						// 		lable = {show: true},	//设置标签是否可见
						// 	},
						// 	{	//node2
						// 		name: "名字",
						// 		des: "鼠标移动上去时的描述",
						// 		symbolSize: 100,	//待议，预计为点的描述的大小
						// 		itemStyle: {}	,//待议，点的css
						// 		category: 1,	//点的分类
						// 		x = 145,	//x坐标
						// 		y = 243,	//y坐标
						// 		draggable = true, //是否可拖动
						// 		value = 23		//待议，一般与symbolSize设为相同，预计为点大小
						// 		lable = {show: true},	//设置标签是否可见
						// 	},
						// 	...
						// ],
						links: inputLinks,
						categories: categories,
						roam: true,
						focusNodeAdjacency: true,
						itemStyle: {
							borderColor: '#fff',
							borderWidth: 1,
							shadowBlur: 10,
							shadowColor: 'rgba(0, 0, 0, 0.3)'
						},
						label: {
							position: 'right',
							formatter: '{b}'
						},
						lineStyle: {
							color: 'source',
							curveness: 0.3
						},
						emphasis: {
							lineStyle: {
								width: 8
							}
						}
					}
				]
			};

			myChart.setOption(option);
			// }, 'xml');;
			// if (option && typeof option === "object") {
			//     myChart.setOption(option, true);
			// }
			myChart.hideLoading();
		}
	},
	// mounted() {
	// 	this.generateChart();
	// }
})