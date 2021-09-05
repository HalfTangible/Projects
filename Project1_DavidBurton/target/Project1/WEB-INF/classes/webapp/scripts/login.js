let loginForm = document.getElementById("login-form");

loginForm.onsubmit = async function(e){
    e.preventDefault();

    console.log("submitted")

    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    console.log(username,password);

    //how do we send values to the backend?
    //fetch, async await, xml objects, etc

    let response = await fetch(`${domain}/api/login`,{
        method: "POST",
        body: JSON.stringify({
            username: username,
            password: password
        })
    })

    let responseData = await response.json();
    console.log(responseData);

    if(responseData.success){
        window.location = `${domain}/dashboard?id=${responseData.data.id}`
    }
}