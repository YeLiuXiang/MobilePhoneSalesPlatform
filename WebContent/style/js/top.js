var localObj = window.location;
var contextPath = localObj.pathname.split("/")[1];
var server_context = localObj.protocol + "//" + localObj.host + "/" + contextPath;
function exit(){
	$.get(server_context+"/Exit",function(){window.location.reload();});
}