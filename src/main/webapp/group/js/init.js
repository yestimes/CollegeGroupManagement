$(document).ready(function(){
  $.getJSON("/getGroupConf", function(res){
    console.log(res);
    $("#org-name-box").text(res.o_name);
    $("#org-desc-box").text(res.o_description);
    $("#gma-name-box").text(res.gma_name);
    $("#ga1-name-box").text(res.ga_names[0]);
    $("#ga2-name-box").text(res.ga_names[1]);


  });
});
