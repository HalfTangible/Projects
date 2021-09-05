/* This will be used in order to create a new account. */

let registerForm = document.getElementById("register-form");

registerForm.onsubmit = async function(e){
    e.preventDefault();

    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let firstName = document.getElementById("firstname").value;
    let lastName = document.getElementById("lastname").value;
    let email = document.getElementById("email").value;

    let response = await fetch(`${domain}/api/register`,{
        method: "POST",
        body: JSON.stringify({
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            email: email
        })
    })

    let responseData = await response.json();
    console.log(responseData)

    if(responseData.success){
        console.log("Response success.")
        window.location = `${domain}/dashboard?id=${responseData.data.id}`
    }

}