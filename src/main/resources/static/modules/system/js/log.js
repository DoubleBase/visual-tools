$(function () {
    layui.use(['table'], function () {
        var table = layui.table;
        table.render({
            elem: '#logTable',
            height: 480,
            url: '/operatorLog/list',
            request:{
                pageName:'pageNo',
                limitNames:'pageSize'
            },
            title: '操作日志表',
            page: true,
            cols: [[
                {field: 'id', title: 'id', width: 80, fixed: 'left'},
                {field: 'type', title: '类型', width: 80},
                {field: 'title', title: '标题', width: 100},
                {field: 'content', title: '内容', width: 80},
                {field: 'method', title: '方法名称', width: 100},
                {field: 'remoteAddr', title: '请求地址', width: 100},
                {field: 'requestUri', title: 'URI', width: 120},
                {field: 'params', title: '提交参数', width: 120},
                {field: 'exception', title: '异常信息', width: 100},
                {field: 'result', title: '执行结果', width: 200},
                {field: 'createTime', title: '操作时间', width: 120},
                {field: 'costTime', title: '执行耗时(ms)', width: 100},
                {field: 'userName', title: '操作用户', width: 100}
            ]],
            limits:[10,20,50,100],
            parseData: function (res) {
                console.log(res)
                return {
                    "code": res.code,
                    "data": res.data
                }
            }
        })
    })

});