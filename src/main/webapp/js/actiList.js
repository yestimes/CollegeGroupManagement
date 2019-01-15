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

  $.getJSON("/ActiList", function(res){
    if (res.status == 200){
        for ( i in res.list){
          var tr = document.createElement("tr");
          var groupId = document.createElement("td");
          groupId.innerHTML = res.list[i].ac_id;

          var group_name = document.createElement("td");
          group_name.innerHTML = res.list[i].ac_name;

          var orga_td = document.createElement("td");
          var olink = document.createElement("a");
          olink.className += "cssclass";
          olink.innerHTML = "社团";
          olink.setAttribute("href","/EditActi?o_id=" + res.list[i].ac_id);
          orga_td.appendChild(olink);


          var del_td = document.createElement("td");
          var rlink = document.createElement("a");
          rlink.className += "cssclass";
          rlink.innerHTML = "参加";
          rlink.setAttribute("href","/EditActi?o_id=" + res.list[i].ac_id);
          del_td.appendChild(rlink);

          tr.appendChild(groupId);
          tr.appendChild(group_name);
          tr.appendChild(orga_td);
          tr.appendChild(del_td);


          var container = document.getElementById("table-body-box");
          container.appendChild(tr);
        }
      }else {
        alert("获取数据失败，状态码: " + res.status);
      }
  });

});
