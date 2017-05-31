function WalletTopUp() {
	var account = $("#account").val();
	$.ajax({
		url:"/Moko_Management/StoreServlet2?method=WalletTopUp&account="+account,
		data:$('#walletTopUpForm').serialize(),
		type:"POST",
		dataType:"json",
		success:function(date){
			if(date){		
				alert("充值成功");
				}else{
					alert("充值失败");
				}
		},
		error:function(data){
			alert("提交失败");
		}
	});
	
} 