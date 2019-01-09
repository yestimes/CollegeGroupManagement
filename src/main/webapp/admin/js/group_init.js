$(document).ready(function () {
  for (var i = 0; i < 10; i++){

    var sortedList = document.createElement("li");
    sortedList.className += "col-md-2 col-sm-4 col-xs-6";

    var iconSpan = document.createElement("span");
    iconSpan.className += "lnr lnr-map-marker";

    var nameLink = document.createElement("a");
    nameLink.className += "cssclass";
    nameLink.innerHTML = "社团 " + i;
    nameLink.setAttribute("href","/groups?o_id=" + i);

    sortedList.appendChild(iconSpan);
    sortedList.appendChild(nameLink);

    var ulist = document.getElementById("groups-ul");
    ulist.appendChild(sortedList);
    console.log(i);

  }
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
