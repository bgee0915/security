
$(function(){
    Role.list();
});


var Role = {
    list : function(){
        $.ajax({
            url: Utils.baseUrl() + 'role/list',
            data:'',
            type:'post',
            dataType:'json',
            success:function(result){
                var tables = $.fn.lx.tables;
                tables.setting = {
                    head:['id','角色名','key值'],
                    data:result.data,
                    value:function(item){
                        return [item.id,item.name,item.keys];
                    }
                };
                tables.init('l_div_table');

            }
        })
    },


};


