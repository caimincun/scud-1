//页面元素重置iframe大小
function resizeIframe(){
    //console.log($(document.body).height());
    $(parent.document).find('iframe').height($(document.body).height());
}
$(document).ready(function(){
    resizeIframe();
});
