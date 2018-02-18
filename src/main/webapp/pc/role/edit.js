$(function(){
Edit.init();
});

var Edit = {
    init:function(){},
    add:function(){},
    cancel:function(){},
    info:function(){},
    fill:function(){},
    data :{
        id : '',
        role:''
    }
};

// 初始化
Edit.init = function(){
    var id = Utils.getUrlParam('id');
    Edit.data.id = id;
    Edit.info();
    Edit.fill();
};

Edit.info = function(){
    $.ajax({
        url: Utils.baseUrl() + 'role/get',
        data:{id:Edit.data.id},
        type:'post',
        dataType:'json',
        async:false,
        success:function(result){
            if(result.ret === 1  ){
                Edit.data.role = result.data;
            } else {
                alert('获取信息失败 id:' + Edit.data.id);
            }
        }
    })
};

// 信息填充
Edit.fill = function(){
    var role = Edit.data.role;
    if(role){
        $('#name').val(role.name);
        $('#keys').val(role.keys);
    }
};

// 保存
Edit.add = function(){

    var name = $('#name').val();
    if(!name){
        alert('请输入正确的角色名字');
        return false;
    }
    var role  = {
        id:Edit.data.id,
        name:name
    };
    $.ajax({
        url: Utils.baseUrl() + 'role/edit',
        data:role,
        type:'post',
        dataType:'json',
        async:false,
        success:function(result){
            if(result.ret === 1){
                alert('保存成功');
                setTimeout(function(){
                    window.location.href = 'role.html';
                },1000);
            } else {
                alert('保存失败 ' + result.msg);
            }
        }
    });
};

Edit.cancel = function(){
    $('input').val('');
};

