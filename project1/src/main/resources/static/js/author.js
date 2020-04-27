
new Vue({
	el: "#app",
	data(){
		return{
			authorName: 'Victor R.Basili',
			affiliation: 'University of Maryland Colloge Park',
			affiliationId: 1,
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
				
			],
			friendsName:[
				
			],
			squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
			huoyuedu: 65,
			paperCount: 0,
			meetingCount: 0,
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
		self.friendsName = [];
		var address = '/getAuthorView';	//backendUrl
		xmlhttp.open('post', address , true);
		xmlhttp.setRequestHeader("Content-type", "application/json");
		var data = {authorId: url};
		xmlhttp.send(JSON.stringify(data));
		xmlhttp.onreadystatechange = function(){
			if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
				var ans = xmlhttp.responseText;
				console.log(ans);
				ans = JSON.parse(ans);
				self.authorName = ans.authorName;
				self.affiliation = ans.affiliation;
				self.affiliationId = ans.affId;
				self.paperCount = ans.articleCount;
				self.ref = ans.citationCount;
				self.meetingCount = ans.meetingCount;
				self.huoyuedu = ans.huoyuedu;
				for(var i = 0; i< ans.terms.length; i++){
					var temp = {name: ans.terms[i]};
					self.terms.push(temp);
				}
				for(var i = 0; i< ans.friendsID.length; i++){
					var temp = {id: ans.friendsID[i], name: ans.friendsName[i]};
					self.friendsName.push(temp);
				}
				self.tableData = ans.papers;
				self.loading = false;
			}
		}
	}
	
})