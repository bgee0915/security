
var Utils = {
    baseUrl : function(){},   // 地址前缀
    loadCss : function(){}, // 加载css
}

Utils.baseUrl = function(){
    var baseUrl;
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0, index + 1);
    if (document.location.origin) {
        baseUrl = document.location.origin + result + '/';
    } else {
        baseUrl = location.protocol + '//' + location.host + result + '/';
    }
    return baseUrl;
}

Utils.loadCss = function(url){
    $('<link>').attr({
        rel:'stylesheet',
        type:'text/css',
        href:url
    }).appendTo('head');
}
