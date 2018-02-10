var Add = {
    add:function(){}
};

// 保存
Add.add = function(){
    var account = $('#account').val();
    var pass1 = $('#pass').val();
    var pass2 = $('#repeatPass').val();
    var tel = $('#tel').val();
    var sex = $('input:radio[name=sex]:checked').val();

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

    var acct = {};
    acct.account = account;
    acct.tel = tel;
    acct.sex = sex;
    acct.pass = pass1;

    $.ajax({
        url: Utils.baseUrl() + 'account/add',
        data:acct,
        type:'post',
        dataType:'json',
        async:false,
        success:function(result){
            if(result.ret === 1 && result.data > 0){
                alert('添加成功');
                setTimeout(function(){
                    window.location.href = 'account.html';
                },1000);
            } else {
                alert('添加失败 account --> ');
            }
        }
    });
};

// 添加按钮点击
$('.l_bt_add').click(function(){
    Add.add();
});