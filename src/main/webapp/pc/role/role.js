
$(function(){
    Role.list();
});


var Role = {
    list : function(){},
    del:function(){},
    toAdd : function(){},
    toEdit:function(){}
};

Role.list = function(){
    $.ajax({
        url: Utils.baseUrl() + 'role/list',
        data:'',
        type:'post',
        dataType:'json',
        success:function(result){
            var tables = $.fn.lx.tables;
            tables.setting = {
                head:['id','角色名','key值','操作'],
                data:result.data,
                value:function(item){
                    return [
                        item.id,
                        item.name,
                        item.keys,
                        (function(id){
                            var html = '';
                            html += '<a href="javascript:void(0)" class="l_pub_df_a  pm a_role_edit" onclick="Role.toEdit('+id+')">修改</a>&nbsp;&nbsp;';
                            html += '<a href="javascript:void(0)" class="l_pub_df_a  pm a_role_del" onclick="Role.del('+id+')">删除</a>';
                            return html;
                        })(item.id)
                    ];
                }
            };
            tables.init('l_div_table');
        }
    })
};

Role.del = function(id){
    $.ajax({
        url: Utils.baseUrl() + 'role/del',
        data:{id:id},
        type:'post',
        dataType:'json',
        success:function(result){
            if(result.ret === 1){
                alert('刪除成功');
            } else {
                alert('刪除失敗');
            }
            Role.list();
        }
    })
};

Role.toAdd = function(){
    window.location.href = 'add.html';
};

Role.toEdit = function(id){
    window.location.href = 'edit.html?id=' + id;
};

