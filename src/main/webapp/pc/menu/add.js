$(function(){
    Add.init();
});


var Add = {
    init            :   function(){},        // 初始化
    icons           :   function(){},        // 图片列表
    pMenus          :   function(){},        // 父菜单
    add             :   function(menu){},    // 添加
    fill            :   function(){},        // 填充信息
    clickIconInput  :   function(){},        // 点击展示图片
    iconInputBlur   :   function(){},        // 离开焦点
    iconSel         :   function(obj){},     // 图片点击选择
    data            : {
        iconsList   :   '',         //  图片列表
        pMenuList   :   ''          //  父菜单
    }
};


Add.pMenus = function(){
    $.ajax({
        url     :   Utils.baseUrl() + 'menu/list',
        data    :   '',
        dataType:   'json',
        type    :   'post',
        async   :   false,
        success :   function(result){
            if(result.ret === 1){
                Add.data.pMenuList = result.data;
            } else {
                console.log('获取菜单列表失败, result:');
                console.log(result);
            }
        }
    });
};

Add.init = function(){
    Add.pMenus();
    Add.icons();
    Add.fill();
};

Add.fill = function(){
    // 填充父菜单列表
    var pMenuHtml = '';
    if(Add.data.pMenuList && Add.data.pMenuList.length > 0){
        pMenuHtml += '<option value="0"></option>';
        $.each(Add.data.pMenuList,function(index,item){
            pMenuHtml += '<option value="' + item.id + '">';
            pMenuHtml += item.name;
            pMenuHtml += '</option>';
        });
        $('#pid').html(pMenuHtml);
    }

    // 填充 icons
    if(Add.data.iconsList && Add.data.iconsList.length > 0){
        var iconsHtml = '';
        $.each(Add.data.iconsList,function(index, item){
            iconsHtml += '<img class="l_pub_img_icons" src="' + Utils.baseUrl() + item.url + '" data-url="'+ item.url +'"  onclick="Add.iconSel(this);" />';
        });
        $('#icon_lists').html(iconsHtml);
    }
};

Add.icons = function(){
    $.ajax({
        url     :   Utils.baseUrl() + 'img/listIcon',
        data    :   '',
        type    :   'post',
        dataTYpe:   'json',
        async   :   false,
        success :   function(result){
            if(result.ret === 1){
                Add.data.iconsList = result.data;
            } else {
                console.log('获取图片列表失败, result:');
                console.log(result);
            }
        }
    })
};

Add.clickIconInput = function(){
    if($('#icon_lists').is(':hidden')){
        $('#icon_lists').show();
    } else {
        $('#icon_lists').hide();
    }
};

Add.iconInputBlur = function(){
};

Add.iconSel = function(obj){
    var _this = $(obj);
    $('#icon_url').val(_this.attr('data-url'));
    $('#iconImg').prop('src',Utils.baseUrl() + _this.attr('data-url'));
    $('#iconImg').show();
    $('#icon_lists').hide();
};

Add.add = function(){
    var name = $('#name').val();
    var pid = $('#pid').val();
    var url = $('#url').val();
    var perm = $('#perm').val();
    var type = $('#type').val();
    var icon_url = $('#icon_url').val();

    if(!name){
        alert('名字不能为空！');
        return false;
    }
    if(!pid){
        alert('父菜单不能为空！');
        return false;
    }
    if(!url){
        alert('地址不能为空！');
        return false;
    }
    if(!perm){
        alert('权限不能为空！');
        return false;
    }
    if(!type){
        alert('类型不能为空！');
        return false;
    }
    if(!icon_url){
        alert('图标不能为空！');
        return false;
    }

    var data = {
        pid     :   pid,
        name    :   name,
        url     :   url,
        perm    :   perm,
        type    :   type,
        icon    :   icon_url
    };

    $.ajax({
        url     :   Utils.baseUrl() + 'menu/add',
        data    :   data,
        type    :   'post',
        dataType:   'json',
        success :   function(result){
            if(result.ret === 1){
                alert('添加成功');
                setTimeout(function(){
                    window.location.href = 'menu.html';
                },1000);
            } else {
                alert('添加失败');
                console.log('添加菜单失败, result:');
                console.log(result);
            }
        }
    })

};