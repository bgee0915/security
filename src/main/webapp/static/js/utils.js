
var Utils = {
    baseUrl : function(){},                     // 地址前缀
    loadCss : function(url){},                  // 加载css
    getUrlParam : function(name){},             // 获取路径后面的参数
    getCookie : function(name){},               // 获取 cookie 的值
    check : {
        validTel : function(tel){}
    }
};


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
};

Utils.loadCss = function(url){
    $('<link>').attr({
        rel:'stylesheet',
        type:'text/css',
        href:url
    }).appendTo('head');
};

// 获取路径后面的参数
Utils.getUrlParam = function(name){
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return "";
};

// 获取 cookie 的值
Utils.getCookie = function(name){
    var strCookie = document.cookie;
    var arrCookie = strCookie.split("; ");
    for (var i = 0; i < arrCookie.length; i++) {
        var arr = arrCookie[i].split("=");
        if (arr[0] == name) return arr[1];
    }
    return "";
};

// 检查手机号
Utils.check.validTel = function(tel){
    return /^[1][3,4,5,7,8][0-9]{9}$/.test(tel);
};