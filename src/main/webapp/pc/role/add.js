var Add = {
    add:function(){},
    cancel:function(){}
};

// 保存
Add.add = function(){

    var name = $('#name').val();
    var key = $('#key').val();

    if(!name){
        alert('请输入正确的角色名字');
        return false;
    }
    if(!key){
        alert('请输入正确格式的 key值');
        return false;
    }

    var role = {};
    role.name = name;
    role.keys = key;
    role.status = 1;

    $.ajax({
        url: Utils.baseUrl() + 'role/add',
        data:role,
        type:'post',
        dataType:'json',
        async:false,
        success:function(result){
            if(result.ret === 1){
                alert('添加成功');
                setTimeout(function(){
                    window.location.href = 'role.html';
                },1000);
            } else {
                alert('添加失败 ' + result.msg);
            }
        }
    });
};

Add.cancel = function(){
    $('input').val('');
};

