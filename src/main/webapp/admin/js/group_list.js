

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


    $.getJSON("/getOrgaList", function (res) {
      if (res.status == 200){
          for ( i in res.data){
            var sortedList = document.createElement("li");
            sortedList.className += "col-md-2 col-sm-4 col-xs-6";

            var iconSpan = document.createElement("span");
            iconSpan.className += "lnr lnr-map-marker";

            var nameLink = document.createElement("a");
            nameLink.className += "cssclass";
            nameLink.innerHTML = res.data[i].o_name;
            nameLink.setAttribute("href","/OrgaPaneCenter?o_id=" + + res.data[i].o_id);

            sortedList.appendChild(iconSpan);
            sortedList.appendChild(nameLink);

            var ulist = document.getElementById("groups-ul");
            ulist.appendChild(sortedList);
          }
      }
    });

});
/*
<li class="col-md-2 col-sm-4 col-xs-6">
  <span class="lnr lnr-map-marker"></span> <span class="cssclass">lnr lnr-map-marker</span>
</li>

var txt1="<p>Text.</p>";              // 以 HTML 创建新元素
var txt2=$("<p></p>").text("Text.");  // 以 jQuery 创建新元素
var txt3=document.createElement("p");
txt3.innerHTML="Text.";               // 通过 DOM 来创建文本
$("body").append(txt1,txt2,txt3);        // 追加新元素
*/
