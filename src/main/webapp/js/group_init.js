$(document).ready(function () {

  $.getJSON("/getOrgaList", function (res) {
    if (res.status == 200){

      for ( i in res.data){
        var group_div = document.createElement("div");
        group_div.className += "col-sm-6 col-md-4 col-lg-3";

        var icon = document.createElement("i");
        icon.className += "mdi mdi-delete-variant";

        var nameLink = document.createElement("a");
        nameLink.className += "cssclass";
        nameLink.innerHTML = res.data[i].o_name;
        nameLink.setAttribute("href","/groups?o_id=" + res.data[i].o_id);

        group_div.appendChild(icon);
        group_div.appendChild(nameLink);

        var ulist = document.getElementById("group-list-box");
        ulist.appendChild(group_div);

      }
    }else {
      alert("获取社团信息失败:" + res.info);
    }
  });
  /*
  for (var i = 0; i < 10; i++){

    var group_div = document.createElement("div");
    group_div.className += "col-sm-6 col-md-4 col-lg-3";

    var icon = document.createElement("i");
    icon.className += "mdi mdi-delete-variant";

    var nameLink = document.createElement("a");
    nameLink.className += "cssclass";
    nameLink.innerHTML = "社团 " + i;
    nameLink.setAttribute("href","/groups?o_id=" + i);

    group_div.appendChild(icon);
    group_div.appendChild(nameLink);

    var ulist = document.getElementById("group-list-box");
    ulist.appendChild(group_div);
    console.log(i);

  }
  */
});
// <div class="col-sm-6 col-md-4 col-lg-3">
//     <i class="mdi mdi-delete-variant"></i>
//     <a href="http://baidu.com?o_id=666">百度</a>
// </div>
