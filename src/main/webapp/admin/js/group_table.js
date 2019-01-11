

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
  /**
  <tr>
    <td>#</td>
    <td>name</td>
    <td><a href="#">删除</a></td>
  </tr>
  */
    $.getJSON("/getOrgaList", function (res) {
      if (res.status == 200){
          for ( i in res.data){
            var tr = document.createElement("tr");

            var groupId = document.createElement("td");
            groupId.innerHTML = res.data[i].o_id;

            var group_name = document.createElement("td");
            group_name.innerHTML = res.data[i].o_name;

            var editTd = document.createElement("td");
            var elink = document.createElement("a");
            elink.className += "cssclass";
            elink.innerHTML = "编辑";
            elink.setAttribute("href","/OrgaEditWould?o_id=" + res.data[i].o_id);
            editTd.appendChild(elink);

            var deleteTd = document.createElement("td");
            var dlink = document.createElement("a");
            dlink.className += "cssclass";
            dlink.innerHTML = "删除";
            dlink.setAttribute("href","/DeleteOrga?o_id=" + res.data[i].o_id);
            deleteTd.appendChild(dlink);

            tr.appendChild(groupId);
            tr.appendChild(group_name);
            tr.appendChild(editTd);
            tr.appendChild(deleteTd);


            var container = document.getElementById("table-body-box");
            container.appendChild(tr);
          }
      }
    });

});
