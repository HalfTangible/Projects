/*let loginForm = document.getElementById("login-form");

//window.onload = async function(){
    /*const sessionRes = await fetch(`${domain}/check-session`)
    const sessionUserData = sessionRes.json();
    console.log("after onload")
    if(sessionUserData.data){
        window.location = `${domain}/dashboard?id=${sessionUserData.data.id}`
    }
//}

loginForm.onsubmit = async function(e){
    e.preventDefault();

    console.log("submitted")

    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    console.log(username,password);

    //Okay. It gets the username and response. Now what?
    //Need to send these to the backend. If 

    //how do we send values to the backend?
    //fetch, async await, xml objects, etc

    let response = await fetch(`${domain}/api/login`,{
        method: "POST",
        body: JSON.stringify({
            username: username,
            password: password
        })
    })

    console.log(response);
    let responseData = await response.json();
    console.log("Past responsedata");
    console.log(responseData);


    if(responseData.success){
        console.log("Inside if");
        window.location = `${domain}/dashboard?id=${responseData.data.id}`
    }else{
        console.log("Inside else");
        let messageElem = document.getElementById("login-message")
        messageElem.style = "background-color: white"
        messageElem.innerText = responseData.message
    }
}*/



    //The following code comes (mostly) from Shane. We used it to try and account for possible errors in the above code, and I kept it because we found the problem elsewhere
    //and I wanted to be dead sure this worked.

    let loginForm = document.getElementById("login-form");
     
    /* window.onload = async function(){
        const sessionRes = await fetch(${domain}/api/check-session)
    
    } */
    
    loginForm.onsubmit = async function(e){
        e.preventDefault();
    
        let username = document.getElementById("username").value;
        let password = document.getElementById("password").value;
    
        console.log(username, password);
    
        let response = await fetch(`${domain}/api/login`,{
            method: "POST",
            body: JSON.stringify({
                username: username,
                password: password
            })
        })
    
        let responseData = await response.json();
        console.log("responseData:")
        console.log(responseData);
        let userInfo = responseData.data;
    
          if(responseData.success){
            console.log("UserInfo: ")  
            console.log(userInfo)
            window.location = `${domain}/dashboard/?id=${userInfo.userID}`
            
            /*if(userInfo.userRoleIdFk === 1){
            //window.location = ${domain}/employee?id=${userInfo.userId}&roleId=${userInfo.userRoleIdFk}
            }
            if(userInfo.userRoleIdFk === 2){
            //    window.location = ${domain}/financeManager?id=${userInfo.userId}&roleId=${userInfo.userRoleIdFk}
            }*/
        }else{
            console.log("responsedata failure")
            let messageElem = document.getElementById("login-message")
            messageElem.innerText = responseData.message
        } 
    }