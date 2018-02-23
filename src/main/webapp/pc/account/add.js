$(function(){
   Add.init();
});

var Add = {
    init:function(){},
    roleList:function(){},
    fill:function(){},
    add:function(){},
    cancel:function(){},
    data : {
        roles : ''
    }
};

// 初始化
Add.init = function(){
    Add.roleList();
    Add.fill();
};

// 角色列表
Add.roleList = function(){
    $.ajax({
        url : Utils.baseUrl() + 'role/list',
        data : '',
        type : 'post',
        dataType : 'json',
        async : false,
        success : function(result){
            if(result.ret === 1){
                Add.data.roles = result.data;
            } else {
                alert('获取角色列表失败');
            }
        }
    })
};

Add.fill = function(){
    var roleHtml = '<label>角色：</label>';
    var roles = Add.data.roles;
    if(roles){
        $.each(roles,function(index,item){
            roleHtml += '<input type="checkbox" name="roles" value="'+ item.id +'"/>' + item.name + '&nbsp;';
        });
        $('#roles').html(roleHtml);
    }
};

// 保存
Add.add = function(){
    var account = $('#account').val();
    var pass1 = $('#pass').val();
    var pass2 = $('#repeatPass').val();
    var tel = $('#tel').val();
    var sex = $('input:radio[name=sex]:checked').val();
    var roles = [];
    $('input:checkbox[name=roles]:checked').each(function(index,item){
        roles.push($(this).val());
    });

    console.log(roles);

    if(!account || account.length > 20){
        alert('账号输入格式不正确');
        return false;
    }
    if(pass1 != pass2 || !pass1 || !pass2){
        alert('两次密码不一致，请重新输入');
        return false;
    }
    if(!Utils.check.validTel(tel)){
        alert('手机号格式不正确，请重新输入');
        return false;
    }
    if(!tel){
        alert('性别不对，请重新输入');
        return false;
    }
    if(!roles){
        alert('请选择角色');
        return false;
    }

    var acct = {};
    acct.account = account;
    acct.tel = tel;
    acct.sex = sex;
    acct.pass = pass1;
    acct.roles = roles;

    $.ajax({
        url: Utils.baseUrl() + 'account/add',
        data:acct,
        type:'post',
        dataType:'json',
        success:function(result){
            if(result.ret === 1){
                alert('添加成功');
                setTimeout(function(){
                    window.location.href = 'account.html';
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

