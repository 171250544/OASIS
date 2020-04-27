
new Vue({
	el: "#app",
	data(){
		return{
			affName: 'Victor R.Basili',
			synonym: '',
			loading: false,
			activeIndex: "1",
			tableData:[
				{
					id: 1,
					title: 'wo men shi guan jun hahahahah',
					year: '2019',
					authorNames:['Lin Wangxun','Zhou Hua'],
					authorIds:[12,43],
					affIds: [],
					affNames: [],
					refCount: 19,
				}
			],
			introduction: '暂无数据',
			terms:[
				"wetwerwa",
				"fown w iqewq",
				"vcpvbwe"
				
			],
			friendAffs:[
				{
					id: 1,
					name: "Tom lirender"
				},
				{
					id: 2,
					name: "Tom fadsvcv lirender"
				},
			],
			authors: [
				{
					id: 1,
					name: "Tom lirender"
				},
				{
					id: 2,
					name: "Tom fadsvcv lirender"
				},
				
			],
			squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
			huoyuedu: 65,
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
		},
		checkTerm(name){
			name = escape(name);
//			window.location.href="./ieeeTerms.html?"+name;
			window.location.href="/ieeeTerms?"+name;
		},
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
		if(isNaN(url))
		{
			alert("参数不符合要求！");
			return;
		}
		
		var xmlhttp = new XMLHttpRequest();
		var self = this;
		self.tableData = [];
		self.terms = [];
		self.authors = [];
		self.friendAffs = [];
		var address = '/getAffView';	//backendUrl
		xmlhttp.open('post', address , true);
		xmlhttp.setRequestHeader("Content-type", "application/json");
		var data = {AffId: url};
		xmlhttp.send(JSON.stringify(data));
		xmlhttp.onreadystatechange = function(){
			if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
				var ans = xmlhttp.responseText;
				console.log(ans);
				ans = JSON.parse(ans);
				self.affName = ans.affName;
				self.terms = ans.terms;
				self.huoyuedu = ans.huoyuedu;
				for(var i = 0; i< ans.synonym.length; i++){
					if(i != 0){
						self.synonym += ", ";
					}
					self.synonym += ans.synonym[i];
				}
				for(var i = 0; i< ans.authorsID.length; i++){
					var temp = {id: ans.authorsID[i], name: ans.authorsName[i]};
					self.authors.push(temp);
				}
				for(var i = 0; i< ans.friendAffID.length; i++){
					var temp = {id: ans.friendAffID[i], name: ans.friendAffName[i]};
					self.friendAffs.push(temp);
				}
				self.tableData = ans.papers;
				self.loading = false;
			}
		}
	}
	
})