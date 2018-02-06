var lg = {
    login:function(){}
};

lg.login = function(){
    var account = $('#account').val();
    var pass = $('#pass').val();
    $.ajax({
        url : baseUrl + 'login/login',
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

console.log(typeof lg);