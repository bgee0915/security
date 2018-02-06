$(function(){
    var acct = new Acct;
    acct.list();
})


var Acct = function(){
    'use strict';

    this.get = function(){
        alert('get');
    }

    this.list = function(){
        $.ajax({
            url: baseUrl + 'account/list',
            data:'',
            type:'post',
            dataType:'json',
            success:function(result){
                var html = '';
                html += '<table><thead><tr><th>'
                     +  'id' + '</th><th>'
                     +  '账号' +'</th><th>'
                     +  '手机号' +'</th><th>'
                     +  '性别' +'</th><th>'
                     +  '头像' +'</th><th>'
                     +  '创建时间' + '</th><th>'
                     +  '创建人' + '</th></tr></thead><tbody><tr><td>'

                if(result.ret === 1 && result.data){
                    var list = result.data;
                    $.each(list,function(index,item){
                        html += item.id + '</td><td>'
                             + item.account + '</td><td>'
                             + item.tel + '</td><td>'
                             + item.sex + '</td><td>'
                             + item.headimg + '</td><td>'
                             + item.createDate + '</td><td>'
                             + item.createBy + '</td></tr>';
                    });

                    html += '</tbody></table>';

                } else {
                    html = '';
                }

                $('#l_div_table').html(html);
            }
        })
    };
}