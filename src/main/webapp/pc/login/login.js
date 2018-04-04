$(function(){
    lg.init();
})


var lg = {
    init:function(){},
    code:function(){},
    login:function(){}

};

lg.init = function(){
    lg.code();
};

lg.code = function(){
    $('#code').attr('src', Utils.baseUrl() + 'login/code?v=' + Math.random());
};

lg.login = function(){
    var account = $('#account').val();
    var pass = $('#pass').val();
    $.ajax({
        url : Utils.baseUrl() + 'login/login',
        data : {account:account,pass:pass},
        type : 'post',
        dataType : 'json',
        success : function(result){
            if(result.ret === 1){
                window.location.href = '../index/index.html';
            } else {
                alert('账号密码错误');
            }
        }
    })
};

