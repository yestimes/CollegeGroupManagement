

$(document).ready(function () {
    console.log("init");

    $.getJSON("/getIndexInitInfo",function(result){
        console.log(result);
        //初始化页面信息
        $("#student-name-box").text(result.name);
        //根据权限，初始化左边菜单
        if(result.permission == 0){
            window.location = "/index.html"
        }
        else if(result.permission == 1 || result.permission == 2){
            //$("#control-box").empty();
        }

    });

    $.getJSON("/OrgaCommList", function (res) {
        console.log(res);
        if (res.status == 200){
            for ( i in res.list){
                var tr = document.createElement("tr");

                var s_o_index = document.createElement("td");
                s_o_index.innerHTML = res.list[i].s_o_index;

                var o_id = document.createElement("td");
                o_id.innerHTML = res.list[i].o_id;

                var s_id = document.createElement("td");
                s_id.innerHTML = res.list[i].s_id;

                var s_o_comment = document.createElement("td");
                s_o_comment.innerHTML = res.list[i].s_o_comment;

                var s_o_comment_time = document.createElement("td");
                s_o_comment_time.innerHTML = res.list[i].s_o_comment_time;

                var deleteTd = document.createElement("td");
                var dlink = document.createElement("a");
                dlink.className += "cssclass";
                dlink.innerHTML = "删除";
                dlink.setAttribute("href","/DeleteOrgaComm?s_o_index=" + res.list[i].s_o_index);
                deleteTd.appendChild(dlink);

                tr.appendChild(s_o_index);
                tr.appendChild(o_id);
                tr.appendChild(s_id);
                tr.appendChild(s_o_comment);
                tr.appendChild(s_o_comment_time);
                tr.appendChild(deleteTd);


                var container = document.getElementById("table-body-box");
                container.appendChild(tr);
            }
        }
    });

});
