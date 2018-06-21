$(function(){
   Add.init();
});

var Add = {
    init:function(){},
    authzList:function(){},
    fill:function(){},
    add:function(){},
    cancel:function(){},
    data : {
        authz : []
    }
};

Add.init = function(){
    Add.authzList();
    Add.fill();
};

Add.authzList = function(){
    $.ajax({
        url: Utils.baseUrl() + 'authz/list',
        data:'',
        dataType:'json',
        type:'post',
        async:false,
        success:function(result){
            // 排序
            var pData = result.data;
            var sData = result.data;
            var resultData = [];
            for(var i=0 ;i<pData.length; i++){
                if(pData[i].pid === 0 && !pData[i].key ){
                    resultData.push(pData[i]);
                    for(var j=0; j<sData.length; j++) {
                        if (pData[i].id === sData[j].pid) {
                            resultData.push(sData[j]);
                        }
                    }
                }
            }
            Add.data.authz = resultData;
        }
    })
};

Add.fill = function(){
    var authz = Add.data.authz;
    if(authz && authz !== 'undefined'){
        var html = '';
        for(var i=0; i<authz.length; i++){
            var a = authz[i];
            if(a.pid === 0){
                if(i > 0){
                    html += '<br/>';
                }
                html += '<span>' + a.name + ': &nbsp;</span>';
            } else {
                html += '<input type="checkbox" name="authz" value="'+ a.id +'"/>' + a.name + '&nbsp;';
            }
        }
        $('#authz_div').html(html);
    }
};

// 保存
Add.add = function(){
    var name = $('#name').val();
    var key = $('#key').val();
    var authzArray = [];

    $('input:checkbox[name=authz]:checked').each(function(index,item){
        authzArray.push($(this).val());
    });

    if(!name){
        alert('请输入正确的角色名字');
        return false;
    }
    if(!key){
        alert('请输入正确格式的 key值');
        return false;
    }
    if(!authzArray || authzArray.length < 1){
        alert('请选择权限');
        return false;
    }

    var role = {};
    role.name = name;
    role.keys = key;
    role.status = 1;
    role.authz = authzArray;
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

