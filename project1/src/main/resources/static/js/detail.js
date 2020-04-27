
new Vue({
	el: "#app",
	data(){
		return{
			activeIndex: "1",
			id: "",
			title: "",
			authors: [],
			affiliations: [],
			meeting: "",
			year: 0,
			abstracts: "",
			doi: "",
			link: "",
			authorkey: "",
			ieeeterms: [],
			citationcount: 0,
			referencecount: 0,
			publisher: "",
			identifier: "",
			activeIndex: '1',
		}
	},
	methods:{
		initFake(){
			this.title = "wo men shi guan jun hahahahah";
			this.authors = [{id: 12, name: "Lin Wangxun"},{id: 34, name: "Zhou Hua"}];
			this.id = "37";
			this.affiliations = [{id: 21, name: "sdfafwdv sdf qwe"},{id: 84, name: "qwevqw eaw qerqwer qwerqwer"}]
			this.meeting = "qtryjdfgo boinoiw qwoiny oalsdknt";
			this.year = 2019;
			this.abstracts = "qkljbg vaerb iufqer qutiq divp9uqw eioqubv iuq eroigbvi u irugbqieu qrue bqoirbtqio uviqubt oiqwbg qibg q";
			this.doi = "1684-32163217.aovir0-4m";
			this.link = "http://www.bilibili.com/";
			this.authorkey = "qerunf uhwer gu qopweht qpo opqwu trpqu";
			// this "poqipp q[[b[vipoowi obop bot]]]";
			this.ieeeterms = [{name: "wewer qwe fd"},{name: "asdfnoqwe qwe"}]
			this.citationcount = 3891;
			this.referencecount = 69;
			this.publisher = "jwoiet nbu oai r qperqewr";
			this.identifier = "97f615138babcav";
		},
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
        checkMeeting(name){
            name = escape(name);
//			window.location.href="./meeting.html?"+name;
            window.location.href="/meeting?"+name;
        },
	},
	mounted() {
		// this.initFake();
		
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
		var address = '/Det';	//backendUrl
		xmlhttp.open('post', address , true);
		xmlhttp.setRequestHeader("Content-type", "application/json");
		var data = {id: url};
		xmlhttp.send(JSON.stringify(data));
		xmlhttp.onreadystatechange = function(){
			if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
				var ans = xmlhttp.responseText;
				console.log(ans);
				ans = JSON.parse(ans);
				self.id = ans.id;
				self.title = ans.title;
				self.meeting = ans.meeting;
				self.year = ans.year;
				self.abstracts = ans.abstracts;
				self.doi = ans.doi;
				self.link = ans.link;
				self.authorkey = ans.authorkey;
				self.citationcount = ans.citationcount;
				self.referencecount = ans.referencecount;
				self.publisher = ans.publisher;
				self.identifier = ans.identifier;
				for(var i = 0; i< ans.ieeeterms.length; i++){
					var temp = {name: ans.ieeeterms[i]};
					self.ieeeterms.push(temp);
				}
				for(var i = 0; i< ans.authorIds.length; i++){
					var temp = {id: ans.authorIds[i], name: ans.authorNames[i]};
					self.authors.push(temp);
				}
				for(var i = 0; i< ans.affIds.length; i++){
					var temp = {id: ans.affIds[i], name: ans.affNames[i]};
					self.affiliations.push(temp);
				}
			}
		}
	},
})