
// 自定义 table
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
                "border-top":"1px solid #ccc",
                "border-left":"1px solid #ccc"
            });
            $('#l_lx_tb tr:even').css("background-color","#F9F9F9");
            $('#l_lx_tb tr:odd').css("background-color","#FFFFFF");
            $('#l_lx_tb tr th').css({
                'text-align':'center',
                'background-color':'#e2e2e2',
                'font-size':'14px',
                'height':'40px',
                'line-height':'40px',
                'font-weight':'bold',
                "border-right":"1px solid #ccc",
                "border-bottom":"1px solid #ccc"
            });
            $('#l_lx_tb tr td').css(
                {
                    'border': '0px solid #FFFFFF ',
                    'height':'40px',
                    'line-height':'40px',
                    'text-align':'center',
                    "border-right":"1px solid #ccc",
                    "border-bottom":"1px solid #ccc"
                }
            );
            $('#l_lx_tb tr td').attr('valign','center');
        }
    }
};


var authzList = [];
var filterPm = function(){
    var baseUrl;
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0, index + 1);
    if (document.location.origin) {
        baseUrl = document.location.origin + result + '/';
    } else {
        baseUrl = location.protocol + '//' + location.host + result + '/';
    }

    $.ajax({
        url: baseUrl + 'login/accountAuthz',
        data:'',
        dataType:'json',
        type:'post',
        async:false,
        success:function(result){
            if(result.ret === 1){
                $.each(result.data,function(index,item){
                    authzList.push(item.keys);
                });
            } else {
                alert('初始化权限失败');
                return false;
            }
        }
    });

    var pm = $('.pm');
    $.each(pm,function(index,item){
        var that = $(this);
        var curr = $(this).attr('class');
        var currPmArray = $.trim(curr.split('pm ')[1]).split(' ');
        $.each(currPmArray, function(currIndex, currItem){
            if($.inArray(currItem,authzList) < 0){
                // that.css("display","none");
            }
        })
    });

    var style = '.pm {display:none;}\n';
    for (var i in authzList) {
        style += '.pm.' + authzList[i] + ' {display:block;}\n';
        style += '.btn.pm.' + authzList[i] + ' {display:inline-block;}\n';
        style += 'a.pm.' + authzList[i] + ' {display:inline;}\n';
        style += 'table.pm.' + authzList[i] + ' {display:table;}\n';
    }
    var D = document,
    styleDom = D.createElement('style');
    styleDom.setAttribute("type", "text/css");
    styleDom.styleSheet && ($.styleSheet.cssText = css)
    || styleDom.appendChild(D.createTextNode(style));
    D.getElementsByTagName('head')[0].appendChild(styleDom);
};
filterPm();