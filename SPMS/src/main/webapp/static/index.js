let spmsUrl = 'http://localhost:8080/SPMS';
let myForm = document.getElementById('myForm');
let loggedInUser = null;
checkLogin();
getData();
//document.getElementById('getData').onclick = getData;

function getData(){
    myForm.innerHTML +=
       '<h3>Welcome and please select what would you like to do </h3>';
    if(!loggedInUser){
       
        myForm.innerHTML += `
        <button type="button" class="button" id="loginBtn">Log In</button><br>
        <button type-"button" class="button" id="registerBtn">Register</button>
        `;
        let loginBtn = document.getElementById("loginBtn");
        loginBtn.onclick = loginDisplay;
        let registrationBtn = document.getElementById("registerBtn");
        registrationBtn.onclick = registrationDisplay;
    }else{
        myForm.innerHTML +=`
        <button type="button" class="button" onclick="location.href='story.html';">View Stories</button>
        <button type="button" class="button" onclick="logout()">Log out</button>
        `;
    }

}
function loginDisplay() {
    myForm.innerHTML = `
        <h2> Welcome to Jagabani SPMS </h2><br><br>
        <form id="myForm" method="post" name="myForm">
              <input id="username" type="text" name="username" placeholder="Username" required="required" />
              <input id="passwd" type="password" name="password" placeholder="Password" required="required" />
              <button id="loginBtn" type="button" class="btn btn-primary btn-block btn-large">Log In</button>
        </form>
    `;

    let loginBtn = document.getElementById("loginBtn");
    if (!loggedInUser) loginBtn.onclick = login;
}

function registrationDisplay() {
    myForm.innerHTML = `
        <h2>Register a new Author</h2><br><br>
        <form id="myForm" method="post" name="myForm">
              <input id="username" type="text" name="username" placeholder="Username" required="required" />
              <input id="password" type="password" name="password" placeholder="Password" required="required" />
              <input id="firstName" type="text" name="firstName" placeholder="First Name" required="required" />
              <input id="lastName" type="text" name="lastName" placeholder="Last Name" required="required" />
              <button id="loginbtn" type="button" class="btn btn-primary btn-block btn-large">Log In</button>
        </form>
    `;

    let registerBtn = document.getElementById("registerBtn");
    if (!loggedInUser) registerBtn.onclick = register;
}

async function login() {
    let url = spmsUrl + '/user/login?';
    url += 'user=' + document.getElementById("username").value + '&';
    url += 'passwd=' + document.getElementById("passwd").value;
    console.log(url);
    let response = await fetch(url, {method: 'POST'});

    switch (response.status) {
        case 200: // successful
            loggedInUser = await response.json();
            console.log(loggedInUser);
            getData();
            break;
        case 400: // incorrect password
            alert('Incorrect password, try again.');
            document.getElementById('pass').value = '';
            break;
        case 404: // user not found
            alert('That user does not exist.');
            document.getElementById('user').value = '';
            document.getElementById('passwd').value = '';
            break;
        default: // other error
            alert('Something went wrong.');
            break;
    }
}

async function register() {
    let user = {};
    user.id = 0;
    user.points = 0;
    user.username = document.getElementById("username").value;
    user.password = document.getElementById("passwd").value;
    user.firstName = document.getElementById("firstName").value;
    user.lastName = document.getElementById("lastName").value;
    user.role = {};
    user.role.name = "Author";
    user.role.id = 4;
    user.genres = {};
    user.outRequests = {};
    user.inRequests = {};
    user.pitches = {};

    let url = spmsUrl + '/user';
    let response = await fetch(url, {method:'POST', body:JSON.stringify(user)});
    
    switch (response.status) {
        case 201: // successful
            loggedInUser = await response.json();
            getData();
            break;
        case 409: // Username already taken
            alert('That username is already taken, try again.');
            document.getElementById('username').value = '';
            break;
        default: // other error
            alert('Something went wrong.');
            break;
    }
}

async function logout() {
    let url = spmsUrl + '/user/login';
    let response = await fetch(url, {method:'DELETE'});

    if (response.status != 200) alert('Something went wrong.');
    loggedInUser = null;
    window.location.href = "index.html";
    //getData();
}

async function checkLogin() {
    let url = spmsUrl + '/user';
    let response = await fetch(url, {method:'GET'});
    console.log(response);
    if (response.status === 200) {
        loggedInUser = await response.json();
    } 
    getData();
}