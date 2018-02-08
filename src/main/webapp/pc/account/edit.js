$(function(){
    Edit.init();
});

var Edit = {
    init:function(){},
    info:function(){},
    fill:function(){},
    edit:function(){},
    data : {
        id : null,
        account : null,
    }
}

// 初始化
Edit.init = function(){
    var id = Utils.getUrlParam('id');
    Edit.data.id = id;
    Edit.info();
    Edit.fill();
}

// 账号信息
Edit.info = function(){
    $.ajax({
        url: Utils.baseUrl() + 'account/get',
        data:{id:Edit.data.id},
        type:'post',
        dataType:'json',
        async:false,
        success:function(result){
            if(result.ret === 1 && result.data){
                Edit.data.account = result.data;
            } else {
                alert('获取信息失败 id:' + Edit.data.id);
            }
        }
    })
}

// 信息填充
Edit.fill = function(){
    var acct = Edit.data.account;
    if(acct){
        $('#account').val(acct.account);
        $('#tel').val(acct.tel);
        $('#headimg').attr('src',acct.headimg);
        $('input:radio[name=sex][value='+ acct.sex +']').attr('checked',true);
    }
}

// 编辑保存
Edit.edit = function(){
    var account = Edit.data.account;
    var acct = {};
    acct.id = account.id;
    acct.account = $('#account').val();
    acct.tel = $('#tel').val();
    acct.headimg = $('#headimg').attr('src');
    acct.sex = $('input:radio[name=sex]:checked').val();

    $.ajax({
        url: Utils.baseUrl() + 'account/edit',
        data:acct,
        type:'post',
        dataType:'json',
        async:false,
        success:function(result){
            if(result.ret === 1 && result.data > 0){
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
}

// 编辑点击事件
$('.l_bt_edit').click(function(){
    Edit.edit();
})