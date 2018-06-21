$(function(){
    Menu.list();
});

var Menu = {
    list:function(){}
};

Menu.list = function(){
    $.ajax({
        url:Utils.baseUrl() + 'menu/list',
        data:'',
        type:'post',
        dataType:'json',
        success:function(result){
            console.log(result);
            if(result.ret === 1){
                var html = '';
                $.each(result.data,function(index,item){
                    html += '<div class="l_divli pm ' + item.perm + '" data-url="' + item.url + '">';
                    html += '<span>' + item.name + '</span>';
                    html += '</div>';
                });
                console.log(html);
                $('#menu').append(html);
            } else {
                console.log('获取菜单列表信息失败, reuslt,');
                console.log(result);
            }
        }
    })
};

// 菜单点击事件
$('.l_divli').click(function(){
    $('#l_ifram').attr('src',$(this).attr('data-url'));
});



