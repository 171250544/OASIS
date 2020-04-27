
new Vue({
	el:'#app',
	data(){
		return{
			userName: 'root',
			activeIndex : '3',
			allSimilar: [],
			input: '',
			synonymData: [],
			multipleSelection: [],
			loading1: false,
			loading2: false,
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
		handleOpen(key, keyPath) {
			switch(key){
				case "1":
//					window.location.href="./manage_merge.html";
					 window.location.href="/manage_merge"
					break;
				case "2":
//					window.location.href="./manage_upload.html";
					 window.location.href="/manage_upload"
					break;
				default:
					console.log("err: "+key);
			}
		},
		submit(){
			this.loading1 = true;
			this.loading2 = true;
			var self = this;
			this.allSimilar = [];
			this.synonymData = [];
			var address1 = "getSynonym"; //查找已定义同义词表url
			var xmlhttp = new XMLHttpRequest();
			xmlhttp.open('post', address1 , true);
			xmlhttp.setRequestHeader("Content-type", "application/json");
			var data = {keyword:this.input};
			console.log(data);
			xmlhttp.send(JSON.stringify(data));
			xmlhttp.onreadystatechange = function () {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
					var response = xmlhttp.responseText;
					response = JSON.parse(response);
					for(var i=0; i<response.synonymList.length; i++){
						var temp = {synonym: response.synonymList[i]};
						self.synonymData.push(temp);
					}
					self.loading1 = false;
				}
			}
			var address2 = "searchSynonym"; //查找相似词url
			var xmlhttp2 = new XMLHttpRequest();
			xmlhttp2.open('post', address2 , true);
			xmlhttp2.setRequestHeader("Content-type", "application/json");
			var data2 = {keyword:this.input};
			console.log(data2);
			xmlhttp2.send(JSON.stringify(data2));
			xmlhttp2.onreadystatechange = function () {
				if (xmlhttp2.readyState == 4 && xmlhttp2.status == 200){
					var response2 = xmlhttp2.responseText;
					response2 = JSON.parse(response2);
					for(var i=0; i<response2.synonymList.length; i++){
						var temp = {synonym: response2.synonymList[i]};
						self.allSimilar.push(temp);
					}
					self.loading2 = false;
				}
			}
		},
		mergeSubmit(){
			if(this.multipleSelection.length == 0){
				this.$message({
				  message: '未选择要合并的词！',
				  type: 'warning'
				});
				return;
			}
			var address = "merge"; //查找已定义同义词表url
			var xmlhttp = new XMLHttpRequest();
			xmlhttp.open('post', address , true);
			xmlhttp.setRequestHeader("Content-type", "application/json");
			var targetList = [];
			for(var i=0; i<this.multipleSelection.length; i++){
				targetList.push(this.multipleSelection[i].synonym);
			}
			var data = {originWord:this.input, keywordList: targetList};
			console.log(data);
			var self = this;
			xmlhttp.send(JSON.stringify(data));
			xmlhttp.onreadystatechange = function () {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
					var response = xmlhttp.responseText;
					if(response == "success"){
						//alert成功，刷新表格
						self.$alert('成功定义为同义词！', '通知', {
						  confirmButtonText: '确定',
						  callback: action => {
							  self.submit();
						  }
						});
					}
					else{
						//alert失败，提示错误信息，刷新页面
						self.$alert('定义失败！本次操作无效', '通知', {
						  confirmButtonText: '确定',
						});
					}
				}
			}
		},
		handleSelectionChange(val) {
			this.multipleSelection = val;
			console.log(this.multipleSelection);
		}
	}
})