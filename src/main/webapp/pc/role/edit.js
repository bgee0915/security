$(function(){
Edit.init();
});

var Edit = {
    init:function(){},
    add:function(){},
    authzList:function(){},
    cancel:function(){},
    info:function(){},
    fill:function(){},
    data :{
        id : '',
        role:'',
        roleAuthz:null,
        authz:[]
    }
};

// 初始化
Edit.init = function(){
    Edit.data.id = Utils.getUrlParam('id');
    Edit.info();
    Edit.authzList();
    Edit.fill();
};

Edit.info = function(){
    $.ajax({
        url: Utils.baseUrl() + 'role/gets',
        data:{id:Edit.data.id},
        type:'post',
        dataType:'json',
        async:false,
        success:function(result){
            if(result.ret === 1  ){
                Edit.data.role = result.data;
                Edit.data.roleAuthz = result.data.authz;
            } else {
                alert('获取信息失败 id:' + Edit.data.id);
            }
        }
    })
};

Edit.authzList = function(){
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
            Edit.data.authz = resultData;
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


    var authz = Edit.data.authz;
    if(authz && authz != 'undefined'){
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

    var roleAuthz = Edit.data.roleAuthz;
    if(roleAuthz && roleAuthz.length > 0){
        $.each(roleAuthz,function(index,item){
            $('input:checkbox[name=authz][value='+ item.id +']').attr('checked',true);
        });
    }
};

// 保存
Edit.add = function(){

    var name = $('#name').val();
    if(!name){
        alert('请输入正确的角色名字');
        return false;
    }

    var authz = [];
    $('input:checkbox[name=authz]:checked').each(function(index,item){
        authz.push($(this).val());
    });

    var role  = {
        id:Edit.data.id,
        name:name,
        authz:authz
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

