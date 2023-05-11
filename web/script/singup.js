document.querySelector(".signup-tab form").addEventListener('submit', function(event) {
    event.preventDefault();
    var formelement = document.querySelector("body > div > div.signup-tab.form-container > form");
    var formdata = new FormData(formelement);

    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'saveUser'); 
    xhr.withCredentials = true;
    xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded' );
    xhr.onload = function () {
        console.log("Response Received");
        if (xhr.status == 200) {
            var response = JSON.parse(xhr.responseText);
            console.log(response);
            if (response.success) {
                console.log("Reseting form");
                formelement.reset();
                alert(response.message);
            }
        }  else {
            alert("Something went wrong");
            alert(xhr.status);
        }
    };
    var encodedFormData = new URLSearchParams(formdata).toString();
    console.log(encodedFormData);
    xhr.send(encodedFormData);

});