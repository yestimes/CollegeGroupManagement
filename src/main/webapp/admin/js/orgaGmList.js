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

  $.getJSON("/getOrgaGmList", function(res){
    if (res.status == 200){
        for ( i in res.data){
          var tr = document.createElement("tr");
          var groupId = document.createElement("td");
          groupId.innerHTML = res.data[i].o_id;

          var group_name = document.createElement("td");
          group_name.innerHTML = res.data[i].o_name;

          var gm_name = document.createElement("td");
          gm_name.innerHTML = res.data[i].s_name;

          var opr_td = document.createElement("td");
          var dlink = document.createElement("a");
          dlink.className += "cssclass";
          dlink.innerHTML = "变更";
          dlink.setAttribute("href","/orgaGmWound?o_id=" + res.data[i].o_id);
          opr_td.appendChild(dlink);

          tr.appendChild(groupId);
          tr.appendChild(group_name);
          tr.appendChild(gm_name);
          tr.appendChild(opr_td);


          var container = document.getElementById("table-body-box");
          container.appendChild(tr);
        }
      }else {
        alert("获取数据失败，状态码: " + res.status);
      }
  });

});
