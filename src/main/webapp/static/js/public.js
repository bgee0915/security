var baseUrl;
var pathName = document.location.pathname;
var index = pathName.substr(1).indexOf("/");
var result = pathName.substr(0, index + 1);
if (document.location.origin) {
    baseUrl = document.location.origin + result + '/';
} else {
    baseUrl = location.protocol + '//' + location.host + result + '/';
}

