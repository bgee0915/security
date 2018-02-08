$(function(){
    var acct = new Acct;
    acct.list();
});


var Acct = function(){
    'use strict';

    this.list = function(){
        $.ajax({
            url: Utils.baseUrl() + 'account/list',
            data:'',
            type:'post',
            dataType:'json',
            success:function(result){
                var tables = $.fn.lx.tables;
                tables.setting = {
                    head:['id','账号','手机号码','性别','头像','创建时间','创建人','操作'],
                    data:result.data,
                    value:function(item){
                        return [
                            item.id,
                            item.account,
                            item.tel,
                            item.sex === 0 ? '女':'男',
                            item.headimg,
                            item.createDate,
                            item.createBy,
                            (function(id){
                                var html = '';
                                html += '<a href="">修改</a>&nbsp;&nbsp;'
                                html += '<a href="javascript:void(0)" onclick="new Acct().del('+id+')">删除</a>';
                                return html;
                            })(item.id)
                        ];
                    }
                };
                tables.init('l_div_table');
            }
        })
    };

    this.del = function(id){
        $.ajax({
            url: Utils.baseUrl() + 'account/del',
            data:{id:id},
            type:'post',
            dataType:'json',
            success:function(result){
                if(result.ret === 1 && result.data > 0){
                    alert('刪除成功');
                } else {
                    alert('刪除失敗');
                }
                new Acct().list();
            }
        })

    }

};


