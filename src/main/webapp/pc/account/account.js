$(function(){
    var acct = new Acct;
    acct.list();
});


var Acct = function(){
    'use strict';

    this.get = function(){
        alert('get');
    };

    this.list = function(){
        $.ajax({
            url: baseUrl + 'account/list',
            data:'',
            type:'post',
            dataType:'json',
            success:function(result){
                var tables = $.fn.lx.tables;
                tables.setting = {
                    head:['id','账号','手机号码','性别','头像','创建时间','创建人'],
                    data:result.data,
                    value:function(item){
                        return [item.id,item.account,item.tel,item.sex == 0?'女':'男',item.headimg,item.createDate,item.createBy];
                    }
                };
                tables.init('l_div_table');

            }
        })
    };
};


