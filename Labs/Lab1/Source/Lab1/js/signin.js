function dofirst(){
    var submit = document.getElementById("submit");
    submit.addEventListener("click",saveData,false);
}

function saveData(){
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    sessionStorage.setItem(email,password);
    display(email);
}
function display(email){
    var login = document.getElementById("login");
    var l2 = sessionStorage.getItem(email);
    login.innerHTML = "Welcome " + email;
}

window.addEventListener("load",dofirst,false);