var flag = 'add';
var ipPatten = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\:([0-9]|[1-9]\d{1,3}|[1-5]\d{4}|6[0-5]{2}[0-3][0-5])$/;

$(function () {

    // 初始化表格
    initTable();

    loadConnection();
});
loadConnection = function () {
    $.ajax({
        url: '/zk/getConnection',
        type: 'GET',
        dataType: 'json',
        success: function (res) {
            if (res.code === 0) {
                setConnectInfo(res.data);
            }
        }
    });
};

setConnectInfo = function (info) {
    if ("无" !== info) {
        initTree();
    }
    $("#currentConnect").html("当前连接：" + info);
};
/**
 * 初始化连接表
 */
initTable = function () {
    $("#zkTable").bootstrapTable({
        url: "/connect/list",
        method: 'get',
        toolbar: '#toolbar',
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: false,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        smartDisplay: false,
        clickToSelect: true,
        singleSelect: true,                 //开启单选,想要获取被选中的行数据必须要有该参数
        search: false,                      //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: false,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        columns: [{
            checkbox: true  //第一列显示复选框
        }, {
            field: 'id',
            title: 'id',
            align: 'center'
        }, {
            field: 'name',
            title: '名称',
            align: 'center'
        }, {
            field: 'info',
            title: '连接信息',
            align: 'center'
        }],
        responseHandler: function (res) {
            return res.data;
        }
    })
};

/**
 * 获取参数
 * @param params
 * @returns {{offset: *, limit: (*|number)}}
 */
getParams = function (params) {
    return {
        offset: params.offset,
        limit: params.limit
    }
};
/**
 * 保存连接
 */
saveConnect = function () {
    if (flag === 'add') {
        addConnect();
    } else if (flag === 'update') {
        updateConnect();
    }

    $("#connectModal").modal('hide');

};
/**
 * 打开新增连接窗口
 */
openAddDialog = function () {
    $("#connectModal").modal('show')
    $("#connectTitle").text("新增连接");
    flag = 'add';

    $("#connectName").val("");
    $("#connectInfo").val("");
};
/**
 * 打开更新连接窗口
 */
openUpdateDialog = function () {
    $("#connectTitle").text("修改连接");
    flag = 'update';

    var row = $("#zkTable").bootstrapTable('getSelections');
    if (row.length === 0) {
        layer.msg("请先选择一行");
        return;
    }
    $("#connectId").val(row[0].id);
    $("#connectName").val(row[0].name);
    $("#connectInfo").val(row[0].info);
    $("#connectModal").modal('show')
};
/**
 * 新增连接
 */
addConnect = function () {
    var name = $("#connectName").val();
    var info = $("#connectInfo").val();
    if (!ipPatten.test(info)) {
        layer.msg("连接信息输入不正确,请重新输入");
        return;
    }
    $.ajax({
        url: '/connect/add',
        type: 'POST',
        data: {
            name: name,
            info: info
        },
        dataType: 'json',
        success: function (res) {
            if (res.code === 0) {
                layer.msg('添加成功');
                refreshTable();
            } else {
                layer.msg('添加失败');
            }
        },
        error: function () {
            layer.msg('添加失败');
        }
    })
};
/**
 * 修改连接
 */
updateConnect = function () {
    // 选择表格数据
    var name = $("#connectName").val();
    var info = $("#connectInfo").val();
    var id = $("#connectId").val();
    if (!ipPatten.test(info)) {
        layer.msg("连接信息输入不正确,请重新输入");
        return;
    }
    $.ajax({
        url: '/connect/update',
        type: 'POST',
        data: {
            id: id,
            name: name,
            info: info
        },
        dataType: 'json',
        success: function (res) {
            if (res.code === 0) {
                layer.msg('修改成功');
                refreshTable();
            }
        },
        error: function () {
            layer.msg('修改失败');
        }
    })
};
/**
 * 删除连接
 */
deleteConnect = function () {
    var row = $("#zkTable").bootstrapTable('getSelections');
    if (row.length === 0) {
        layer.msg("请先选择一行");
        return;
    }
    layer.confirm('确认删除选中的连接?', function (index) {
        var id = row[0].id;
        $.ajax({
            url: '/connect/delete',
            type: 'POST',
            data: {
                id: id
            },
            dataType: 'json',
            success: function (res) {
                if (res.code === 0) {
                    layer.msg('删除成功');
                    refreshTable();
                }
            },
            error: function () {
                layer.msg('删除失败');
            }
        });
        layer.close(index);
    });
};
/**
 * 刷新表格
 */
refreshTable = function () {
    $("#zkTable").bootstrapTable("refresh");
};
/**
 * 连接zk
 */
connectZk = function () {
    var row = $("#zkTable").bootstrapTable('getSelections');
    if (row.length === 0) {
        layer.msg("请先选择一行");
        return;
    }
    $.ajax({
        url: '/zk/connect',
        type: 'POST',
        data: {
            connectString: row[0].info
        },
        dataType: 'json',
        success: function (res) {
            if (res.code === 0) {
                layer.msg('连接成功');
                setConnectInfo(row[0].info);

            }
        },
        error: function () {
            layer.msg('连接失败');
        }
    });
};
/**
 * 关闭zk
 */
closeZk = function () {
    var row = $("#zkTable").bootstrapTable('getSelections');
    if (row.length === 0) {
        layer.msg("请先选择一行");
        return;
    }
    $.ajax({
        url: '/zk/close',
        type: 'POST',
        dataType: 'json',
        success: function (res) {
            if (res.code === 0) {
                layer.msg('关闭连接成功');
                var treeObj = $.fn.zTree.getZTreeObj("zkTree");
                treeObj.destroy();
                setConnectInfo("无")
            }
        },
        error: function () {
            layer.msg('关闭连接失败');
        }
    });
};
/**************************************zk树操作*****************************/
/**
 * 初始化树
 */
initTree = function () {
    var setting = {
        async: {
            url: '/zk/listNodeChildren',
            type: 'post',
            dataType: 'text',
            enable: true,
            autoParam: ["pathName"],
            dataFilter: function (treeId, parentNode, responseData) {
                if (responseData.code === 0) {
                    return responseData.data;
                }
            }
        },
        view: {
            expandSpeed: "",
            addHoverDom: addHoverDom,
            removeHoverDom: removeHoverDom,
            selectedMulti: false
        },
        edit: {
            enable: true
        },
        callback: {
            beforeRemove: function (treeId, treeNode) {
                var zTree = $.fn.zTree.getZTreeObj(treeId);
                zTree.selectNode(treeNode);
                return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
            },
            beforeRename: function (treeId, treeNode, newName, isCancel) {
                if (!isCancel) {
                    var path = treeNode.pathName;
                    var index = path.lastIndexOf("/");
                    var newPath = path.substr(0, index) + "/" + newName;
                    $.ajax({
                        url: '/zk/updateNodePath',
                        type: 'POST',
                        dataType: 'json',
                        data: {
                            oldPath: treeNode.pathName,
                            newPath: newPath
                        },
                        success: function (res) {
                            if (res.code === 0 && res.data === true) {
                                layer.msg('节点名称更新成功');
                                var treeObj = $.fn.zTree.getZTreeObj(treeId);
                                treeObj.reAsyncChildNodes(treeNode.getParentNode(), "refresh");
                            }
                        }
                    })
                }
            },
            onAsyncSuccess: function (event, treeId, treeNode, msg) {
                var obj = JSON.parse(msg);
                if (obj.data.length === 0) {
                    treeNode.isParent = false;
                    var treeObj = $.fn.zTree.getZTreeObj(treeId);
                    treeObj.updateNode(treeNode);
                }
            },
            onRemove: function (event, treeId, treeNode) {
                $.ajax({
                    url: '/zk/deleteNode',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        path: treeNode.pathName
                    },
                    success: function (res) {
                        if (res.code === 0) {
                            layer.msg('删除节点' + treeNode.name + '成功');
                        } else {
                            layer.msg('删除节点' + treeNode.name + '失败');
                            var treeObj = $.fn.zTree.getZTreeObj(treeId);
                            treeObj.reAsyncChildNodes(treeNode.getParentNode(), "refresh");
                        }

                    }
                })
            },
            onClick: function (event, treeId, treeNode) {
                if (treeNode.children === null) {
                    var treeObj = $.fn.zTree.getZTreeObj(treeId);
                    treeNode.isParent = true;
                    if (treeNode.name === "/") {
                        treeObj.reAsyncChildNodes(treeNode);
                    } else {
                        treeObj.reAsyncChildNodes(treeNode, "refresh");
                    }

                }
                getNodeProperties(treeNode.pathName);
            }
        }
    };

    function addHoverDom(treeId, treeNode) {
        var sObj = $("#" + treeNode.tId + "_span");
        if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
        var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
            + "' title='add node' onfocus='this.blur();'></span>";
        sObj.after(addStr);
        var btn = $("#addBtn_" + treeNode.tId);
        if (btn) btn.bind("click", function () {
            $.ajax({
                url: '/zk/createNode',
                type: 'POST',
                dataType: 'json',
                data: {
                    path: treeNode.pathName + "/new node",
                    value: ''
                },
                success: function (res) {
                    if (res.code === 0) {
                        var treeObj = $.fn.zTree.getZTreeObj(treeId);
                        treeObj.addNodes(treeNode, treeObj.transformToArray(res.data));
                    }
                }
            });
            return false;
        });
    }

    function removeHoverDom(treeId, treeNode) {
        $("#addBtn_" + treeNode.tId).unbind().remove();
        return false;
    }

    $.fn.zTree.init($("#zkTree"), setting, null);

};

getNodeProperties = function (path) {
    $.ajax({
        url: '/zk/getNodeInfoByPath',
        type: 'POST',
        dataType: 'json',
        data: {
            path: path
        },
        success: function (res) {
            if (res.code === 0) {
                var node = res.data;
                $("#nodeValue").val(node.nodeValue);
                $("#czxid").val(node.czxid);
                $("#mzxid").val(node.mzxid);
                $("#ctime").val(node.ctime);
                $("#mtime").val(node.mtime);
                $("#version").val(node.version);
                $("#cversion").val(node.cversion);
                $("#aversion").val(node.aversion);
                $("#ephemeralOwner").val(node.ephemeralOwner);
                $("#dataLength").val(node.dataLength);
                $("#numChildren").val(node.numChildren);
                $("#pzxid").val(node.pzxid);
            }
        }
    })
};

updateNodeValue = function () {
    var treeObj = $.fn.zTree.getZTreeObj("zkTree");
    var path = treeObj.getSelectedNodes()[0].pathName;
    var value = $("#nodeValue").val();
    $.ajax({
        url: '/zk/updateNode',
        type: 'POST',
        dataType: 'json',
        data: {
            path: path,
            value: value
        },
        success: function (res) {
            if (res.code === 0) {
                layer.msg("节点值更新成功");
            } else {
                layer.msg("节点值更新失败！！");
            }
        }
    })
};