$(function () {

    // 初始化表格
    initTable();


});

initTable = function () {
    $("#logTable").bootstrapTable({
        url: "/operatorLog/list",
        method: 'post',
        toolbar:'#toolbar',
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        queryParams: getParams,         //传递参数（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        smartDisplay: false,
        search: true,                      //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        height: 480,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        columns: [{
            field: 'id',
            title: 'id'
        }, {
            field: 'type',
            title: '类型'
        }, {
            field: 'title',
            title: '标题',
            width: 100
        }, {
            field: 'content',
            title: '内容',
            width: 100
        }, {
            field: 'method',
            title: '方法名称',
            width: 120
        }, {
            field: 'remoteAddr',
            title: '请求地址',
            width: 120
        }, {
            field: 'requestUri',
            title: 'URI',
            width: 150
        }, {
            field: 'params',
            title: '提交参数',
            width: 150
        }, {
            field: 'exception',
            title: '异常信息',
            width: 120
        }, {
            field: 'result',
            title: '执行结果'
        }, {
            field: 'createTime',
            title: '操作时间',
            formatter:function (res) {
                var date = new Date(res);
                return date.toLocaleString().replace(/\//g,'-');
            }
        }, {
            field: 'costTime',
            title: '执行耗时(ms)'
        }, {
            field: 'userName',
            title: '操作用户'
        }],
        responseHandler: function (res) {
            return res.data;
        }
    })
}

getParams = function (params) {
    return {
        offset: params.offset,
        limit: params.limit
    }
}