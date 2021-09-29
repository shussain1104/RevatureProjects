let role = sessionStorage.getItem('role');
let fetchUrl = 'http://localhost:7000/reimbursements';
let logoutUrl = 'http://localhost:7000/logout';

let id = document.getElementById('reimbursement_id');
let employee_id = document.getElementById('reimbursement_employee_id');
let purpose = document.getElementById('reimbursement_purpose');
let type = document.getElementById('reimbursement_type');
let amount = document.getElementById('reimbursement_amount');
let comment = document.getElementById('reimbursement_comments');


function getAllData() {
	

	fetch(fetchUrl).then(
		response => {
			response.json().then(
				json_body => {
					populateTable(json_body);
				}
			)
		}
	)
}

function insertReimbursements()
{
  let url = 'http://localhost:7000/reimbursementform.html';
	let xhr = new XMLHttpRequest();
	const data = new URLSearchParams();

	for(const pair of new FormData(formElement)) {
		data.append(pair[0], pair[1]);
	}

	xhr.onreadystatechange = function(){
		if(xhr.readyState === 4 && xhr.status === 200) {
			let result = JSON.parse(xhr.response);
			sessionStorage.setItem('reimbursement_employee_id', result['reimbursement_employee_id']);
      sessionStorage.setItem('reimbursement_purpose',result['reimbursement_purpose']);
      sessionStorage.setItem('reimbursement_type',result['reimbursement_type']);
      sessionStorage.setItem('reimbursement_amount',result['reimbursement_amount']);
      sessionStorage.setItem('reimbursement_comments',result['reimbursement_comments']);
			window.location = '/reimbursementform.html';
		}
	}

	xhr.open('POST', url);

	xhr.send(data);
}


function login(form) {
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
  window.onload = function() {
    if(sessionStorage.length > 0) {
      window.location.replace("/profile.html");
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
  if(xmlhttp.readyState === 4 && xmlhttp.status === 200) {
    let result = JSON.parse(xmlhttp.response);
    sessionStorage.setItem('username', result['username']);
    sessionStorage.setItem('password', result['password']);
    window.location = 'profile.html';
  }
}    
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
  for(i = 0; i < tabcontent.length; i++)
  {
    tabcontent[i].style.display = reimbursementResults();
  }
} 
function reimbursementResults()
{
  data.forEach(element => {
		var tr = document.createElement('tr');
		tr.innerHTML = '<td>' + element['reimbursement_id'] + '</td>'
    + '<td>' + element['reimbursement_employee_id'] + '</td>' +
    '<td>' + element['reimbursement_purpose'] + '</td>' +
    '<td>' + element['reimbursement_type'] + '</td>'
						'<td>$' + element['reimbursement_amount'] + '</td>' + 
            '<td>' + element['reimbursement_comments'] + '</td>'
						'<td>' + element['reimbursement_status'] + '</td>' +
						'<td><a href = \'reimbursementform.html?id=' + element['reimbursement_id'] + '\'>View</a></td>';
		if(element['status'] === 'accepted') {
			tr.classList.add('accepted');
		} else if (element['status'] === 'rejected') {
			tr.classList.add('rejected');
		} else {
			tr.classList.add('base');
		}
		table.appendChild(tr);
	});

}