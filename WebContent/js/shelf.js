
$(function() {	//点击“全选”复选框实现全选
    $(".checkBoxAll").click(function() {
    	var self = this,
    		dself = $(this);
		$(".subCheckBox").each(function(){
			$(this).prop("checked",self.checked);
		})
    });
    var $subBox = $(".subCheckBox");
    $subBox.click(function(){
		$(".checkBoxAll").prop("checked",$subBox.length == $(".subCheckBox:checked").length ? true : false);
    });
});


