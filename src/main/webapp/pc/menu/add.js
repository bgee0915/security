$(function(){
    Add.init();
});


var Add = {
    icons   :   function(){},
    pMenus  :   function(){},     // 父菜单
    add     :   function(menu){}, // 添加
    fill    :   function(){},     // 填充信息
    init    :   function(){},     // 初始化
    data    : {
        iconsList   :   '',
        pMenuList   :   ''          //  父菜单
    }
};


Add.pMenu = function(){
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
    Add.pMenu();
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

    // icons
    var iconsHtml = '';
    if(Add.data.iconsList && Add.data.iconsList.length > 0){
        $.each(Add.data.iconsList,function(index, item){
            iconsHtml += '<img src="' + Utils.baseUrl() + item.url + '" />';
        });
        $('#xxx').html(iconsHtml);
        console.log(iconsHtml);
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

