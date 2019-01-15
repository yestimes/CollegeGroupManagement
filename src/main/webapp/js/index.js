$(document).ready(function(){
    $.getJSON("/getIndexInitInfo",function(result){
        console.log(result);

        if (result.status == 200){
          //初始化页面信息
          $("#user-type-box").text(result.userType);
          $("#user-college-box").text(result.college);
          $("#student-name-box").text(result.name);
          //根据权限，初始化左边菜单
          if(result.permission == 0){
            $("#group-manager-box").remove();
            $("#control-box").empty();
          }
          else if(result.permission == 1 || result.permission == 2){
            $("#control-box").empty();
          }
          else {
            //sa user
            //do not delete element
          }
        }else {
          alert("获取用户信息失败，请登陆");
          window.location = "/login.html";
        }
    });

    $.getJSON("/getActiSuggest", function(res) {
        if (res.status == 200) {
            for (i in res.list) {
                var tr = document.createElement("tr");
                var groupId = document.createElement("td");
                groupId.innerHTML = res.list[i].ac_id;

                var group_name = document.createElement("td");
                group_name.innerHTML = res.list[i].ac_name;

                var orga_td = document.createElement("td");
                var olink = document.createElement("a");
                olink.className += "cssclass";
                olink.innerHTML = "直达";
                olink.setAttribute("href", "/EditActi?o_id=" + res.list[i].ac_id);
                orga_td.appendChild(olink);


                var del_td = document.createElement("td");
                var rlink = document.createElement("a");
                rlink.className += "cssclass";
                rlink.innerHTML = "参加";
                rlink.setAttribute("href", "/EditActi?o_id=" + res.list[i].ac_id);
                del_td.appendChild(rlink);

                tr.appendChild(groupId);
                tr.appendChild(group_name);
                tr.appendChild(orga_td);
                tr.appendChild(del_td);


                var container = document.getElementById("table-body-box");
                container.appendChild(tr);
            }
        } else {
            alert("获取数据失败，状态码: " + res.status);
            window.location = "/login.html";
        }
    });
});


//退出登陆
function userLogout(){
  alert("logout");
  $.getJSON("/userLogout", function(res){
    if (res.status == 200){
      alert("再见，" + res.username);
      }
      else {
        alert(res.info);
      }
      window.location = "/login.html";
    });
}




