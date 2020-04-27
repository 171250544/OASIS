

new Vue({
	el: "#app",
	data(){
		return{
			activeIndex: '1',
			activeNames: [],
			value: 'ALL',
			options: [{
				value: 'ALL',
				label: 'ALL'
			}, {
				value: 'author',
				label: '作者'
			}, {
				value: 'affiliations',
				label: '机构'
			}, {
				value: 'meeting',
				label: '会议'
			}, {
				value: 'keyWord',
				label: '研究关键词'
			}],
			input: '',
			tableData: [
//				 {
//				 	id: 134,
//				 	authorname: "Lin Wang",
//				 	year: "2019",
//				 	title: "qenoin qwenoa qw eoq  osdivn rei aosidqwteo oiqw a doiq",
//				 	affiliations: "Nanjing University"
//				 }
			],
			formLabelAlign: {
				author: '',
				affiliations: '',
				keyWord: '',
				meeting: '',
				timeBegin: '',
				timeEnd: ''
			},
			resultCount: 0,
			loading: false,
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
		if(url.startsWith("%7B")){
			url = unescape(url);
			var data = JSON.parse(url);
			this.formLabelAlign.author = data.author;
			this.formLabelAlign.affiliations = data.affiliations;
			this.formLabelAlign.keyWord = data.keyWord;
			this.formLabelAlign.meeting = data.meeting;
			this.formLabelAlign.timeBegin = data.timeBegin;
			this.formLabelAlign.timeEnd = data.timeEnd;
            if(this.formLabelAlign.author != ''){
                this.activeNames.push("1");
            }
            if(this.formLabelAlign.affiliations != ''){
                this.activeNames.push("2");
            }
            if(this.formLabelAlign.timeBegin != '' || this.formLabelAlign.timeEnd != ''){
                this.activeNames.push("3");
            }
            if(this.formLabelAlign.keyWord != ''){
                this.activeNames.push("4");
            }
            if(this.formLabelAlign.meeting != ''){
                this.activeNames.push("5");
            }
			this.advanceSearch(url);
		}
		else{
			url = url.split("=");
			url[1] = unescape(url[1]);
			if(url[0] == "ALL"){
				this.input = url[1];
				url = {stringforsearch: url[1]};
				url = JSON.stringify(url);
				this.basicSearch(url);
			}
			else{
				this.value = url[0];
				this.input = url[1];
				switch(url[0]){
					case "author":
						this.formLabelAlign.author = url[1];
						break;
					case "affiliations":
						this.formLabelAlign.affiliations = url[1];
						break;
					case "keyWord":
						this.formLabelAlign.keyWord = url[1];
						break;
					case "meeting":
						this.formLabelAlign.meeting = url[1];
						break;
					default:
						alert("init error");
				}
				this.advanceSearch(JSON.stringify(this.formLabelAlign));
			}
		}
	},
	methods: {
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
		handleChange(val) {
			// console.log(val);
		},
		submit(){
		    this.loading = true;
			if(this.value == "ALL"){
				var data = {stringforsearch: this.input};
				data = JSON.stringify(data);
				this.basicSearch(data);
			}
			else{
				this.formLabelAlign.author = "";
				this.formLabelAlign.affiliations = "";
				this.formLabelAlign.keyWord = "";
				this.formLabelAlign.meeting = "";
				switch(this.value){
					case "author":
						this.formLabelAlign.author = this.input;
						break;
					case "affiliations":
						this.formLabelAlign.affiliations = this.input;
						break;
					case "keyWord":
						this.formLabelAlign.keyWord = this.input;
						break;
					case "meeting":
						this.formLabelAlign.meeting = this.input;
						break;
					default:
						alert("search error");
				}
				this.advanceSearch(JSON.stringify(this.formLabelAlign));
			}
		},
		asideSubmit(){
		    this.loading = true;
			this.advanceSearch(JSON.stringify(this.formLabelAlign));
		},
		basicSearch(args){
			var backendAddress1 = '/String';//普通搜索url
			console.log("basicSearch:")
			console.log(args);
			this.post(args, backendAddress1);
		},
		advanceSearch(args){
			var backendAddress2 = '/List';//高级搜索url
			console.log("advanceSearch:")
			console.log(args);
			this.post(args, backendAddress2);
		},
		post(data, address){
			var xmlhttp = new XMLHttpRequest();
			var self = this;
			xmlhttp.open('post', address , true);
			xmlhttp.setRequestHeader("Content-type", "application/json");
			xmlhttp.send(data);
			xmlhttp.onreadystatechange = function () {
			    if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
					var ans = xmlhttp.responseText;
					console.log(ans);
					ans = ans.substr(0,ans.length-1);
					var ansArray = ans.split("}");
					ansArray.pop();
					for(var i=0; i<ansArray.length; i++){
						ansArray[i] = ansArray[i].substr(1);
						ansArray[i] += "}";
					}
					self.resultCount = ansArray.length;
					self.tableData = [];
					for(var i=0; i<ansArray.length; i++){
						var temp = JSON.parse(ansArray[i]);
						self.tableData.push(temp);
					}
					self.loading = false;
				}
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
	}
})
