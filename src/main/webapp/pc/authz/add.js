$(function(){
    Add.init();
});

var Add = {
    init:function(){},
    fill:function(){},
    add:function(){},
    cancel:function(){},
    data : {
        pid:null
    }
};

// 初始化
Add.init = function(){
    Add.data.pid = Utils.getUrlParam('pid');
    Add.fill();
};


Add.fill = function(){
};

// 保存
Add.add = function(){
    var name = $('#name').val();
    var keys = $('#keys').val();
    var pid = Add.data.pid && Add.data.pid != 'undefined' ? Add.data.pid :  0 ;

    if(!name){
        alert('账号输入格式不正确');
        return false;
    }

    var authz = {};
    authz.name = name;
    authz.keys = keys;
    authz.pid = pid;

    $.ajax({
        url: Utils.baseUrl() + 'authz/add',
        data:authz,
        type:'post',
        dataType:'json',
        success:function(result){
            if(result.ret === 1){
                alert('添加成功');
                setTimeout(function(){
                    window.location.href = 'authz.html';
                },1000);
            } else {
                alert('添加失败');
            }
        }
    });
};

Add.cancel = function(){
    $('input').val('');
};

