function changeTabToLogIn() {
    document.querySelector(".login-tab").style.display = 'block';
    document.querySelector(".signup-tab").style.display = 'none';
}
function changeTabToSignUp() {
    document.querySelector(".login-tab").style.display = 'none';
    document.querySelector(".signup-tab").style.display = 'block';
}

document.querySelector("body > div > div.login-tab.form-container > form").addEventListener('submit', function (event) {
    event.preventDefault(); 
    var email = document.querySelector("body > div > div.login-tab.form-container > form > div:nth-child(3) > input[type=email]").value;

    var password = document.querySelector("body > div > div.login-tab.form-container > form > div:nth-child(4) > input[type=password]").value;


    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'login'); 
    xhr.withCredentials = true;
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.onload = function () {
        if (xhr.status === 401) {
            var response = JSON.parse(xhr.responseText);
            alert(response.message);
        }
        else if (xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);
            if (response.success) {
                // alert(response.message);
                window.location.href = response.redirectUrl;
            } else {
                alert(response.message);
            }
        } 
    };
    xhr.send('email=' + encodeURIComponent(email) + '&password=' + encodeURIComponent(password));
});








function login() {
    preventDefault()
    var email = document.querySelector("body > div > div.login-tab.form-container > form > div:nth-child(3) > input[type=email]").value;

    var password = document.querySelector("body > div > div.login-tab.form-container > form > div:nth-child(4) > input[type=password]").value;

    var xhttp = new XMLHttpRequest();
    xhttp.onload = function () {
        if (xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);
            if (response.success) {
                // Redirect to the success page
                window.location.href = response.redirectUrl;
            } else {
                // Display error message to the user
                errorMessage.textContent = response.errorMessage;
            }
        }
    }
    xhttp.open("post", "login");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.send(`email=${email}&password=${password}`);
    return false;
}