function login() {
  fetch('http://localhost:7000/', {
      'method': 'POST',
      'credentials': 'include',
      'headers': {
          'Content-Type': 'application/json'
      },
      'body': JSON.stringify(loginInfo)
  })
}
function checkIfLoggedIn() {
  fetch('http://localhost:7000/', {
      'method': 'GET',
      'credentials': 'include',
  })
}
function logout() {
  fetch('http://localhost:7000/logout', {
      'method': 'POST',
      'credentials': 'include',
  })
}
/*function checkUsername() {
  var usercheck;
  try 
  {
  usercheck= new XMLHttpRequest();
  }
  catch (tryMicrosoft) 
  {
    try
    { 
      usercheck= new ActiveXObject("Msxml2.XMLHTTP");
    } 
    catch (otherMicrosoft) 
    {
      try
      {
        usercheck= new ActiveXObject("Microsoft.XMLHTTP");
      }
  
      catch (failed) 
      {
        request= null;
      }
    }
  }
    var url= "localhost:7000/";
    var user= document.getElementById("employee_username").value;
    var pw= document.getElementById("employee_password").value;
    var vars= "username="+user+"&password="+pw;
    request.open("POST", url, true);
  
     request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  
  request.onreadystatechange= function()
  {
    if (request.readyState == 4 && request.status == 200)  
    {
      var return_data=  request.responseText;
      document.getElementById("status").innerHTML= return_data;
    }
  }
  request.send(vars);
}*/




function showReimbursements()
{
  $(document).ready(function () {  
    $('#requestTable').DataTable({  
        "ajax": {  
            "url": "localhost:7000/reimbursementform.html",  
            "type": "GET",  
            "datatype": "json"  
        },  
        "columns": [  
            { "data": "employee_name" },  
            { "data": "reimbursement_purpose" },  
            { "data": "reimbursement_type" },  
            { "data": "reimbursement_amount" },  
            { "data": "reimbursement_comments" },
            { "data": "reimbursement_status"}  
        ]  
    });  
});           
}

/*function login(form) {
  var un = form.Username.value;
    var pw = form.Password.value;
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("post", "index.html", true);
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            loginResults();
        }
    }
  }
  window.addEventListener(window,"load", function() {
    var loginForm = document.getElementById("login-form");
    window.addEventListener(loginForm, "submit", function() {
         login(loginForm);
     });
 });
 function loginResults() {
  var loggedIn = document.getElementById("LoggedIn");
  var badLogin = document.getElementById("BadLogin");
  if (xmlhttp.responseText.indexOf("failed") == -1) {
      loggedIn.innerHTML = "Logged in as " + xmlhttp.responseText;
      loggedIn.style.display = "block";
      form.style.display = "none";
  } else {
      badLogin.style.display = "block";
      form.Username.select();
      form.Username.className = "Highlighted";
      setTimeout(function() {
          badLogin.style.display = 'none';
      }, 3000);
  }
}*/
  /*
    const username = document.getElementById("employee_username").value;
    const password = document.getElementById("employee_password").value;
  
    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", "../index.html");
    xhttp.setRequestHeader("Content-Type", "application/json ;charset=UTF-8");
    xhttp.send(JSON.stringify({
      "employee_username": username,
      "employee_password": password
    }));
    xhttp.onreadystatechange = function () {
      if (this.readyState == 4) {
        const objects = JSON.parse(this.responseText);
        console.log(objects);
        if (objects['status'] == 'ok') {
          localStorage.setItem("jwt", objects['accessToken']);
          Swal.fire({
            text: objects['message'],
            icon: 'success',
            confirmButtonText: 'OK'
          }).then((result) => {
            if (result.isConfirmed) {
              window.location.href = './index.html';
            }
          });
        } else {
          Swal.fire({
            text: objects['message'],
            icon: 'error',
            confirmButtonText: 'OK'
          });
        }
      }
    };
    return false;
  }*/
        
function openReimbursements(evt, requestStatusType)
{
   // Declare all variables
  var i, tabcontent, tablinks;

  // Get all elements with class="tabcontent" and hide them
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }

  // Get all elements with class="tablinks" and remove the class "active"
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }

  // Show the current tab, and add an "active" class to the button that opened the tab
  document.getElementById(requestStatusType).style.display = "block";
  evt.currentTarget.className += " active";
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