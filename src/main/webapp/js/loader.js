$(function(){
    setTimeout(() => {
      document.querySelector('#post-loader').style.display = "block";
      $("#sideNav").load("/admin/sideNav.html");
    //   $("#footer").load("footer.html")
    }, 800);
      
});