
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
            html += '<table id="l_lx_tb"><thead><tr>';
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

            $('#l_lx_tb').css({
                "width":"100%",
                "border-top":"1px solid #000000",
                "border-left":"1px solid #000000"
            })
            $('#l_lx_tb tr:even').css("background-color","#F9F9F9");
            $('#l_lx_tb tr:odd').css("background-color","#FFFFFF");
            $('#l_lx_tb tr th').css({
                'text-align':'center',
                'background-color':'#D7D7D7',
                'font-size':'14px',
                'height':'40px',
                'line-height':'40px',
                'font-weight':'bold',
                "border-right":"1px solid #000000",
                "border-bottom":"1px solid #000000"
            })
            $('#l_lx_tb tr td').css(
                {
                    'border': '0px solid #FFFFFF ',
                    'height':'40px',
                    'line-height':'40px',
                    'text-align':'center',
                    "border-right":"1px solid #000000",
                    "border-bottom":"1px solid #000000"
                }
            );
            $('#l_lx_tb tr td').attr('valign','center');
        },

    }
};


