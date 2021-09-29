const dbconn = mariadb.createPool({

})


function login(form) {
    const username = form.Username.value;
    const password = form.Password.value;
    let url = "http://localhost:8000/reimbursementsrus/login";
    let xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", "Login", true);
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            
        }
    }
    window.addEventListener(window,"load", function() {
        var loginForm = document.getElementById("login-form");
        window.addEventListener(loginForm, "submit", function() {
             login(loginForm);
         });
     });
               
}
        
function showReinbursements()
{
    document.getElementById('display').innerHTML = document.getElementById("reimbursement_id").value;
}
/*function submitlogin()
{
    let submit_button = document.querySelector('[type=submit]');
    submit_button.addEventListener('click', (event) => {
        if(event.cancelable)
        {
            event.preventDefault();
        }
        login();
    })
}*/