
// 自定义 table 插件
$.fn.lx = {
    tables : {
        setting :{
            head:[],
            data:{},
            value:function(item){}
        },
        init : function(id){
            var setting = $.fn.lx.tables.setting;
            var html = '';
            html += '<table><thead><tr>';
            $.each(setting.head,function(index,item){
                html += '<th>' + item + '</th>';
            });
            html += '</tr></thead>';
            html += '<tbody>';
            $.each(setting.data,function(dataIndex,dataItem){
                html += '<tr>';
                $.each(setting.value(dataItem),function(valueIndex,valueItem){
                   html += '<td>' + valueItem + '</td>';
                });
                html += '</tr>';
            });
            html += '</tbody></table>';
            $('#' + id).html(html);
        }
    }
};


