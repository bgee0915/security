$(function(){
    Edit.init();

});

var Edit = {
    init:function(){},
    info:function(){},
    roles:function(){},
    fill:function(){},
    edit:function(){},
    cancel:function(){},
    data : {
        id : null,
        account : null,
        accountRole : null,
        roles : null
    }
};

// 初始化
Edit.init = function(){
    Edit.data.id = Utils.getUrlParam('id');
    Edit.info();
    Edit.roles();
    Edit.fill();
};

// 账号信息
Edit.info = function(){
    $.ajax({
        url: Utils.baseUrl() + 'account/gets',
        data:{id:Edit.data.id},
        type:'post',
        dataType:'json',
        async:false,
        success:function(result){
            if(result.ret === 1 && result.data){
                Edit.data.account = result.data;
                Edit.data.accountRole = result.data.roles;
            } else {
                alert('获取信息失败 id:' + Edit.data.id);
            }
        }
    });
};

Edit.roles = function(){
    $.ajax({
        url: Utils.baseUrl() + 'role/list',
        data: '',
        type: 'post',
        dataType: 'json',
        async: false,
        success: function (result) {
            if (result.ret === 1) {
                Edit.data.roles = result.data;
            } else {
                alert('获取角色列表失败');
            }
        }
    });
};

// 信息填充
Edit.fill = function(){
    // 账号信息
    var acct = Edit.data.account;
    if(acct){
        $('#account').val(acct.account);
        $('#tel').val(acct.tel);
        $('#headimg').attr('src',acct.headimg);
        $('input:radio[name=sex][value='+ acct.sex +']').attr('checked',true);
    }

    // 权限列表
    var roleHtml = '<label>角色：</label>';
    var roles = Edit.data.roles;
    if(roles){
        $.each(roles,function(index,item){
            roleHtml += '<input type="checkbox" name="roles" value="'+ item.id +'"/>' + item.name + '&nbsp;';
        });
        $('#roles').html(roleHtml);
    }

    // 勾选权限
    var accountRole = Edit.data.accountRole;
    if(accountRole){
        $.each(accountRole,function(index,item){
            $('input:checkbox[name=roles][value='+ item.id +']').attr('checked',true);
        });
    }

};

// 编辑保存
Edit.edit = function(){

    var roles = [];
    $('input:checkbox[name=roles]:checked').each(function(index,item){
        roles.push($(this).val());
    });

    var account = Edit.data.account;
    var acct = {};
    acct.id = account.id;
    acct.account = $('#account').val();
    acct.tel = $('#tel').val();
    acct.sex = $('input:radio[name=sex]:checked').val();
    acct.roles = roles;

    $.ajax({
        url: Utils.baseUrl() + 'account/edit',
        data:acct,
        type:'post',
        dataType:'json',
        async:false,
        success:function(result){
            if(result.ret === 1){
                alert('保存成功');
                setTimeout(function(){
                    window.location.href = 'account.html';
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

