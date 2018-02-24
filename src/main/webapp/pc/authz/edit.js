$(function(){
    Edit.init();

});

var Edit = {
    init:function(){},
    info:function(){},
    fill:function(){},
    edit:function(){},
    cancel:function(){},
    data : {
        id : null,
        authz:null
    }
};

// 初始化
Edit.init = function(){
    Edit.data.id = Utils.getUrlParam('id');
    Edit.info();
    Edit.fill();
};

// 账号信息
Edit.info = function(){
    $.ajax({
        url: Utils.baseUrl() + 'authz/get',
        data:{id:Edit.data.id},
        type:'post',
        dataType:'json',
        async:false,
        success:function(result){
            if(result.ret === 1){
                Edit.data.authz = result.data;
            } else {
                alert('获取信息失败 id:' + Edit.data.id);
            }
        }
    });
};


// 信息填充
Edit.fill = function(){
    // 账号信息
    var authz = Edit.data.authz;
    if(authz){
        $('#name').val(authz.name);
        $('#keys').val(authz.keys);
    }
};

// 编辑保存
Edit.edit = function(){


    var authz = Edit.data.authz;
    var au = {};
    au.id = authz.id;
    au.name = $('#name').val();
    au.keys = $('#keys').val();

    $.ajax({
        url: Utils.baseUrl() + 'authz/edit',
        data:au,
        type:'post',
        dataType:'json',
        async:false,
        success:function(result){
            if(result.ret === 1){
                alert('保存成功');
                setTimeout(function(){
                    window.location.href = 'authz.html';
                },1000);
            } else {
                alert('保存失败 account --> ');
                log.error(Edit.data.account);
            }
        }
    });
};

Edit.cancel = function(){
    $('input').val('');
};

