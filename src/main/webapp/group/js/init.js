$(document).ready(function(){
  $.getJSON("/getGroupConf", function(res){
    console.log(res);
    $("#o-id").val(res.o_id);
    $("#o-id-again").val(res.o_id);
    $("#org-name-box").text(res.o_name);
    $("#org-desc-box").text(res.o_description);
    $("#gma-name-box").text(res.gma_name);
    $("#ga1-name-box").text(res.ga_names[0]);
    $("#ga2-name-box").text(res.ga_names[1]);

  });
});
