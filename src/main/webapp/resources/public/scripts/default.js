$(function () {
    InitLeftMenu();
    $('body').layout();
})

function InitLeftMenu() {
    $('.easyui-accordion li a').click(function () {
        addTab($(this).text(), $(this).attr('href'), $(this).attr('index'));
        $('.easyui-accordion li div').removeClass('selected');
        $(this).parent().addClass('selected');
    }).hover(function () {
        $(this).parent().addClass('hover');
    }, function () {
        $(this).parent().removeClass('hover');
    });
}

function addTab(subtitle, url, index) {
    if ($('#tabs').tabs('exists', subtitle)) {
		$('#tabs').tabs('select', subtitle);
    } else {
        $('#tabs').tabs('add', {
            title: subtitle,
            content: createFrame(url, index),
            closable: true,
            width: $('#mainPanle').width() - 10,
            height: $('#mainPanle').height() - 26
        });
    }
	return false;
}

function createFrame(url, index) {
    var s = '<iframe name="mainFrame_'+index+'" scrolling="no" src="' + url + '" style="width:100%;height:100%;border:0;overflow-x:hidden"></iframe>';
    return s;
}