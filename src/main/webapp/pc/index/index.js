$(function(){
    var i = new Index;
});

var Index = function(){
    'use strict';
    this.xxx = function(){}
}


// 菜单点击事件
$('.l_divli').click(function(){
    $('#l_ifram').attr('src',$(this).attr('data-url'));
})



