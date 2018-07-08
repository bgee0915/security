$(function(){
    Edit.init();
});



var Edit = {
    init            :   function(){},           // 初始化
    icons           :   function(){},           // 图片列表
    pMenus          :   function(){},           // 父菜单
    info            :   function(){},           // 菜单信息
    fill            :   function(){},           // 填充
    clickIconInput  :   function(){},           // 点击展示图片
    iconSel         :   function(obj){},        // 图片点击选择
    edit            :   function(){},
    data            :   {
        iconsList   :   '',         //  图片列表
        pMenuList   :   '',         //  父菜单
        id          :   '',         //  菜单id
        menu        :   ''          //  菜单信息
    }
};


Edit.init = function(){
    Edit.data.id = Utils.getUrlParam('id');
    Edit.pMenus();
    Edit.icons();
    Edit.info();
    Edit.fill();
};


Edit.pMenus = function(){
    $.ajax({
        url     :   Utils.baseUrl() + 'menu/list',
        data    :   '',
        dataType:   'json',
        type    :   'post',
        async   :   false,
        success :   function(result){
            if(result.ret === 1){
                Edit.data.pMenuList = result.data;
            } else {
                console.log('获取菜单列表失败, result:');
                console.log(result);
            }
        }
    });
};

Edit.icons = function(){
    $.ajax({
        url     :   Utils.baseUrl() + 'img/listIcon',
        data    :   '',
        type    :   'post',
        dataTYpe:   'json',
        async   :   false,
        success :   function(result){
            if(result.ret === 1){
                Edit.data.iconsList = result.data;
            } else {
                console.log('获取图片列表失败, result:');
                console.log(result);
            }
        }
    })
};

Edit.info = function(){
    $.ajax({
        url     :   Utils.baseUrl() + "menu/get",
        data    :   {id : Edit.data.id},
        type    :   'post',
        dataType:   'json',
        async   :   false,
        success :   function(result){
            if(result.ret === 1){
                Edit.data.menu = result.data;
            } else {
                console.log("获取菜单信息失败,result:");
                console.log(result);
            }
        }
    })
};

Edit.clickIconInput = function(){
    if($('#icon_lists').is(':hidden')){
        $('#icon_lists').show();
    } else {
        $('#icon_lists').hide();
    }
};


Edit.iconSel = function(obj){
    var _this = $(obj);
    $('#icon_url').val(_this.attr('data-url'));
    $('#iconImg').prop('src',Utils.baseUrl() + _this.attr('data-url'));
    $('#iconImg').show();
    $('#icon_lists').hide();
};

Edit.fill = function(){
    // 填充父菜单列表
    var pMenuHtml = '';
    if(Edit.data.pMenuList && Edit.data.pMenuList.length > 0){
        pMenuHtml += '<option value="0"></option>';
        $.each(Edit.data.pMenuList,function(index,item){
            pMenuHtml += '<option value="' + item.id + '">';
            pMenuHtml += item.name;
            pMenuHtml += '</option>';
        });
        $('#pid').html(pMenuHtml);
    }

    // 填充 icons
    if(Edit.data.iconsList && Edit.data.iconsList.length > 0){
        var iconsHtml = '';
        $.each(Edit.data.iconsList,function(index, item){
            iconsHtml += '<img class="l_pub_img_icons" src="' + Utils.baseUrl() + item.url + '" data-url="'+ item.url +'"  onclick="Edit.iconSel(this);" />';
        });
        $('#icon_lists').html(iconsHtml);
    }

    // 填充菜单信息
    $('#name').val(Edit.data.menu.name);
    $('#pid').val(Edit.data.menu.name);
    $('#url').val(Edit.data.menu.url);
    $('#perm').val(Edit.data.menu.perm);
    $('#name').val(Edit.data.menu.name);
    $('#pid option[value="'+ Edit.data.menu.pid +'"]').attr('selected',true);
    $('#type option[value="'+ Edit.data.menu.perm +'"]').attr('selected',true);

    console.log(Edit.data.menu.icon);
    $('#icon_url').val(Edit.data.menu.icon);
    $('#iconImg').prop('src',Utils.baseUrl() + Edit.data.menu.icon);
    $('#iconImg').show();
};


Edit.edit = function(){
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
        id      :   Edit.data.id,
        pid     :   pid,
        name    :   name,
        url     :   url,
        perm    :   perm,
        type    :   type,
        icon    :   icon_url
    };

    $.ajax({
        url     :   Utils.baseUrl() + 'menu/edit',
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