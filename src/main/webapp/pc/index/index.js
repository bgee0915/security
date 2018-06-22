$(function(){
    Left.list();
});

var Left = {
    list:function(){},
    skip:function(obj){} //跳转
};

Left.list = function(){
    $.ajax({
        url:Utils.baseUrl() + 'menu/list',
        data:'',
        type:'post',
        dataType:'json',
        success:function(result){
            if(result.ret === 1){
                var html = '';
                $.each(result.data,function(index,item){
                    html += '<div onclick="Left.skip(this);" class="l_divli pm ' + item.perm + '" data-url="' + item.url + '">';
                    html += '<span>' + item.name + '</span>';
                    html += '</div>';
                });
                $('#menu').append(html);
            } else {
                console.log('获取菜单列表信息失败, reuslt,');
                console.log(result);
            }
        }
    })
};

Left.skip = function(obj){
    $('#l_ifram').attr('src',$(obj).attr('data-url'));
};




