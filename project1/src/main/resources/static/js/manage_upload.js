
new Vue({
	el: "#app",
	data(){
		return{
			activeIndex:'3',
			userName: 'root',
			urlInput: '',
			fileList: [],
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
		urlSubmit(){
			var self = this;
			if(self.urlInput.length == 0){
				this.$message({
				  message: 'url不能为空! ',
				  type: 'warning'
				});
				return;
			}
			this.$confirm('请确认您要上传的文件是否正确, 此操作将无法撤回!    url: '+self.urlInput, '提示', {
				  confirmButtonText: '确定',
				  cancelButtonText: '取消',
				  type: 'warning'
				}).then(() => {
					var address = "uploadData"; //后端传url的接口
					var xmlhttp = new XMLHttpRequest();
					xmlhttp.open('post', address , true);
					xmlhttp.setRequestHeader("Content-type", "application/json");
					var data = {url: self.urlInput};
					console.log(data);
					xmlhttp.send(JSON.stringify(data));
					xmlhttp.onreadystatechange = function () {
						if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
							var response = xmlhttp.responseText;
							self.$message({
								type: 'info',
								message: response
							});
						}
					}
//				})
				}).catch(() => {
                  self.$message({
                    type: 'info',
                    message: '已取消上传'
                  });
				});
		},
		fileSubmit(){
			var self = this;
			var names = "";
			console.log(self.fileList);
			if(self.fileList.length == 0){
				this.$message({
				  message: '未上传文件! ',
				  type: 'warning'
				});
				return;
			}
			for(var i=0; i<self.fileList.length;i++){
				if(i != 0){
					names+=", ";
				}
				names+=self.fileList[i].name;
				console.log(self.fileList[i].url);
			}
			this.$confirm('请确认您要上传的文件是否正确, 此操作将无法撤回!    文件名: '+names, '提示', {
				  confirmButtonText: '确定',
				  cancelButtonText: '取消',
				  type: 'warning'
				}).then(() => {
				  this.$message({
					type: 'success',
					message: '上传成功!'
				  });
				}).catch(() => {
				  this.$message({
					type: 'info',
					message: '已取消上传'
				  });          
				});
		},
		handleChange(file, fileList){
			this.fileList = fileList.slice(0);
		},
		handleRemove(file, fileList){
			this.fileList = fileList.slice(0);
		}
	},
})