$(function(){
    Authz.list();
});


var Authz = {
    list : function(){},
    del : function(id){},
    toEdit : function(id){},
    toAdd:function () {}
};

Authz.list = function(){
    $.ajax({
        url: Utils.baseUrl() + 'authz/list',
        data:'',
        type:'post',
        dataType:'json',
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

            var tables = $.fn.lx.tables;
            tables.setting = {
                head:['id','模块名','权限名','key', '操作'],
                data:resultData,
                value:function(item){
                    return [
                        item.id,
                        (function(item){
                            if(item.pid === 0){
                                return item.name;
                            } else {
                                return item.pName;
                            }
                        })(item),
                        (function(item){
                            if(item.pid === 0){
                                return '';
                            } else {
                                return item.name;
                            }
                        })(item),
                        item.keys ? item.keys : '',
                        (function(item){
                            var html = '';
                            if(item.pid === 0){
                                html += '<a href="javascript:void(0)" class="l_pub_df_a" onclick="Authz.toAdd('+item.id+')">添加</a>&nbsp;&nbsp;';
                                html += '<a href="javascript:void(0)" class="l_pub_df_a" onclick="Authz.toEdit('+item.id+')">修改</a>&nbsp;&nbsp;';
                                html += '<a href="javascript:void(0)" class="l_pub_df_a" onclick="Authz.del('+item.id+')">删除</a>';
                                return html;
                            } else {
                                html += '<a href="javascript:void(0)" class="l_pub_df_a" onclick="Authz.toEdit('+item.id+')">修改</a>&nbsp;&nbsp;';
                                html += '<a href="javascript:void(0)" class="l_pub_df_a" onclick="Authz.del('+item.id+')">删除</a>';
                                return html;
                            }
                        })(item)
                    ];
                }
            };
            tables.init('l_div_table');
        }
    })
};


Authz.del = function(id){
    $.ajax({
        url: Utils.baseUrl() + 'authz/del',
        data:{id:id},
        type:'post',
        dataType:'json',
        success:function(result){
            if(result.ret === 1){
                alert('刪除成功');
            } else {
                alert('刪除失敗');
            }
            Authz.list();
        }
    })
};

Authz.toAdd =  function(pid){
    window.location.href= 'add.html?pid=' + pid;
};

Authz.toEdit = function(id){
    window.location.href = 'edit.html?id='+id;
};


