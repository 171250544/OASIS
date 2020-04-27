
new Vue({
	el: "#app",
	data(){
		return{
			termName: 'Software Engineer',
			loading: false,
			activeIndex: "1",
			tableData:[
				{
					id: 1,
					title: 'wo men shi guan jun hahahahah',
					year: '2019',
					authorNames:['Lin Wangxun','Zhou Hua'],
					authorIDS:[12,43],
					ref: 19,
				}
			],
			introduction: '暂无数据',
			affName:[
				{
					id: 1,
					name: "Nanjing University"
				},
			],
			authorName:[
				{
					id: 23,
					name: 'Li Yunfu'
				}
			],
			squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
			huoyuedu: 65,
			paperCount: 0,
			ref: 0,
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
		checkDetails(id){
//			window.location.href="./detail.html?"+id;
			window.location.href="/detail?"+id;
		},
		checkAuthor(id){
//			window.location.href="./author.html?"+id;
			window.location.href="/author?"+id;
		},
		checkAffiliation(id){
//			window.location.href="./affiliation.html?"+id;
			window.location.href="/affiliation?"+id;
		}
	},
	mounted() {
		this.loading = true;
		var url = location.search;
		if(url.length == 0){
			console.log("no url message")
			return;
		}
		url = url.substr(1);
		url = unescape(url);
		
		var xmlhttp = new XMLHttpRequest();
		var self = this;
		self.authorName = [];
		self.affName = [];
		var address = '/getTermView'	//backendUrl
		xmlhttp.open('post', address , true);
		xmlhttp.setRequestHeader("Content-type", "application/json");
		var data = {termName: url};
		xmlhttp.send(JSON.stringify(data));
		xmlhttp.onreadystatechange = function(){
			if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
				var ans = xmlhttp.responseText;
				console.log(ans);
				ans = JSON.parse(ans);
				self.termName = ans.name;
				self.tableData = ans.auVos;
				self.huoyuedu = ans.huoyuedu;
				self.paperCount = ans.paperCount;
				self.ref = ans.ref;
				for(var i = 0; i< ans.authorNames.length; i++){
					var temp = {id: ans.authorIds[i], name: " "+ans.authorNames[i]}
					self.authorName.push(temp);
				}
				for(var i = 0; i< ans.affNames.length; i++){
					var temp = {id: ans.affIds[i], name: ans.affNames[i]}
					self.affName.push(temp);
				}
				self.loading = false;
			}
		}
	}
})