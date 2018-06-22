$(function(){
    Menu.list();
});

var Menu = {
    list:function(){},
    del:function(id){},
    toEdit:function(id){},
    toAdd : function(id){}
};


Menu.list = function(){
    $.ajax({
        url: Utils.baseUrl() + 'menu/list',
        data:'',
        dataType:'json',
        type:'post',
        success:function(result){
            if(result.ret === 1){
                var tables = $.fn.lx.tables;
                tables.setting = {
                    head    :   ['菜单','url','权限key','类型','图标','排序','操作'],
                    data    :   result.data,
                    value   :   function(item){
                        return [
                            item.name,
                            item.url,
                            item.perm,

                                (function(type){ // type 类型
                                    var typeName = '';
                                    if(type === 0){
                                        typeName = '目录';
                                    } else if(type === 1){
                                        typeName = '菜单';
                                    } else if(type === 2){
                                        typeName = '按钮';
                                    } else {
                                        typeName = '菜单';
                                    }
                                    return typeName;
                                })(item.type),

                                (function(item){ // icon 图标
                                    return item?item:'无';
                                })(item.icon),

                            item.seq,

                                (function(id){ // 操作
                                    var html = '';
                                    html += '<a href="javascript:void(0)" class="l_pub_df_a    " onclick="Menu.toEdit('+id+')">修改</a>&nbsp;&nbsp;';
                                    html += '<a href="javascript:void(0)" class="l_pub_df_a     " onclick="Menu.del('+id+')">删除</a>';
                                    return html;
                                })(item.id)

                        ];
                    }
                };
                tables.init('l_div_table');
            } else {
                console.log('获取菜单列表失败!, result:');
                console.log(result);
            }
        }
    })
};