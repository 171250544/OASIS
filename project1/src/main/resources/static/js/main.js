
new Vue({
	el: '#app',
	data: function() {
		return {
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
			value: 'ALL',
			input: '',
			activeIndex: '1',
			drawer: false,
			formLabelAlign: {
			  author: '',
			  affiliations: '',
			  keyWord: '',
			  meeting: '',
			  timeBegin: '',
			  timeEnd: ''
			},
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
		submit(){
			if(this.input.length == 0){
				alert("内容不能为空!");
			}
			else{
//				window.location.href="./result.html?"+this.value+"="+this.input;
				  window.location.href="/result?"+this.value+"="+escape(this.input);
			}
			// console.log("搜索:"+this.input);
		},
		advanceSubmit(){
//			window.location.href="./result.html?"+JSON.stringify(this.formLabelAlign);
			 window.location.href="/result?"+escape(JSON.stringify(this.formLabelAlign));
		}
	}
})