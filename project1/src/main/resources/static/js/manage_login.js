

new Vue({
	el: "#app",
	data(){
		return{
			activeIndex : '3',
			formLabelAlign: {
			  username: '',
			  password: ''
			},
			
		}
	},
	methods:{
		submit(){
			// 后端暂未开发登录系统, 前端使用假数据代替
			if(this.formLabelAlign.username == 'root' && this.formLabelAlign.password == "123456"){
//				window.location.href="./manage_merge.html";
				window.location.href="/manage_merge";
			}
			else{
			    this.$message.error('用户名或密码错误');
			}
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
	}
})