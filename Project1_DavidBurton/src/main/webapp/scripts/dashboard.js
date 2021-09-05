const urlParams = new URLSearchParams(window.location.search)
const userId = urlParams.get("id")
const logout = document.getElementById("logout-btn")

logout.onclick = async function(){
    await fetch(`${domain}/api/logout`)
    window.location = `${domain}/`
}

window.onload = async function(){
    //console.log("In dashboard.js")
    
    //console.log(`Fetching: ${domain}/api/check-session`)
    const sessionRes = await fetch(`${domain}/api/check-session`)

    const sessionUserData = await sessionRes.json()

    const roleId = sessionUserData.data.userRoleID;
    
    console.log("double if")
    if(sessionUserData.data){
        if(sessionUserData.data.userID != userId){
            await fetch(`${domain}/api/logout`)
            window.location = `${domain}/`
        }
    }
    //console.log("outside populateData");
    populateData(roleId);
}

async function populateData(roleId){
    //console.log("inside populateData");
    //console.log(roleId)

    let manager = true;

    if(roleId == 1)
        manager = false;
    
    //console.log("manager")
    //console.log(manager)

    const listResponse = await fetch(`${domain}/api/reimbursements?id=${userId}`)
    const listData = await listResponse.json();
    const userResponse = await fetch(`${domain}/api/getAllUsers`)
    const userData = await userResponse.json();

    //console.log("dashboard.js.PopulateData()")
    listData.data.sort((a,b) => {
        if(a.name < b.name)
            return -1;
        
        if(a.name > b.name)
            return 1;

        return 0;
    })

    //let thePage = document.createElement("container")

    let theTable = document.getElementById("table-container");
    //let submissionForm = document.createElement("form");
    let submissionForm = document.getElementById("newSub-form");
    let pendingTable = document.createElement("div"); //1
    let acceptedTable = document.createElement("div"); //2
    let rejectedTable = document.createElement("div"); //3
    let fulfilledTable = document.createElement("div"); //4

    pendingTable.innerHTML = "<h2 class=\"table-title\">Pending<h2>"
    acceptedTable.innerHTML = "<h2 class=\"table-title\">Accepted<h2>"
    rejectedTable.innerHTML = "<h2 class=\"table-title\">Rejected<h2>"
    fulfilledTable.innerHTML = "<h2 class=\"table-title\">Fulfilled<h2>"

    //Create a form for submitting new remibursement requests,
    //and add it to the table immediately.

    submissionForm.onsubmit = async function(e){
        e.preventDefault();
        //We need
        //AuthorID, amount, desc, type
        //We can get the submitter's ID from the url.
        //Pass these to the back end, and you can make a
        //new reimbursement there
        
        let newAmount = document.getElementById("newAmount").value
        let newDesc = document.getElementById("newDescription").value
        let newType = document.getElementById("newType").value
        console.log("newType")
        console.log(newType)
        console.log("newType")
        console.log(newType.value)

        let subResponse = await fetch(`${domain}/api/submit`,{
            method: "POST",
            body: JSON.stringify({
                reim_ID: 0,
                amount: newAmount,
                description: newDesc,
                author: userId,
                status: 1,
                type: newType
            })
        })

        let subData = await subResponse.json();
        console.log(subData)

        window.location = `${domain}/dashboard/?id=${userId}`

    }

    //Display it if the user is NOT a finance manager.
    //Then, list the rest.
    for(let count = 1; count <= 4; count++){

    let header = document.createElement("div")
    let headAmount = document.createElement("span")
    headAmount.style.width = "250px"
    let headFirstName = document.createElement("span")
    headFirstName.style.width = "250px"
    let headLastName = document.createElement("span")
    headLastName.style.width = "250px"
    let headDesc = document.createElement("span")
    headDesc.style.width = "250px"
    let headSubmitted = document.createElement("span")
    headSubmitted.style.width = "250px"
    let headResolved = document.createElement("span")
    headResolved.style.width = "250px"
    let headType = document.createElement("span")
    headType.style.width = "250px"
    let headResolverFN = document.createElement("span")
    headResolverFN.style.width = "250px"
    let headResolverLN = document.createElement("span")
    headResolverLN.style.width = "250px"
    let headButtonCont = document.createElement("span")
    headButtonCont.style.width = "250px"

    headAmount.innerText = "Amount Requested"
    headFirstName.innerText = "Submitter Name"
    headLastName.innerText = "Submitter Last Name"
    headDesc.innerText = "Description"
    headSubmitted.innerText = "Date and Time Submitted"
    headResolved.innerText = "Date and Time Resolved"
    headType.innerText = "Type of Reimbursement"
    headResolverFN.innerText = "Resolver First Name"
    headResolverLN.innerText = "Resolver Last Name"
    headButtonCont.innerText = "Accept/Reject"

    header.className = "row"
    headAmount.className = "headerCell"
    headFirstName.className = "headerCell"
    headLastName.className = "headerCell"
    headDesc.className = "headerCell"
    headSubmitted.className = "headerCell"
    headResolved.className = "headerCell"
    headType.className = "headerCell"
    headResolverFN.className = "headerCell"
    headResolverLN.className = "headerCell"
    headButtonCont.className = "headerCell"

    header.id = "header"

    header.appendChild(headFirstName)
    header.appendChild(headLastName)
    header.appendChild(headDesc)
    header.appendChild(headSubmitted)
    header.appendChild(headAmount)
    header.appendChild(headType)
    header.appendChild(headResolved)
    header.appendChild(headResolverFN)
    header.appendChild(headResolverLN)

    //if they're a manager, add a spot for the buttons.
    if(manager && count == 1)
    header.appendChild(headButtonCont)
    
    if (count == 1)
    pendingTable.appendChild(header)
    else if(count == 2)
    acceptedTable.appendChild(header)
    else if(count == 3)
    rejectedTable.appendChild(header)
    else if(count == 4)
    fulfilledTable.appendChild(header)
    }

    listData.data.forEach(element => {
        //Consider: If the user is a finance manager, we only need to send back the name/last name of the original user.
        //If the user is looking at their own, we only need to send back the name of the resolver.
        //Problem: if the resolver is nobody, then what?
        //Put an entry 0 in the resolver table. make it the default for an unresolved reimbursement.
        /*
        console.log("element")
        console.log(element)
        console.log("element.data")
        console.log(element.data)
        console.log("element.type")        
        console.log(element.type) //this one works
        */
        
        //console.log(element)
        //console.log("Array functioning.")
        let row = document.createElement("div")
        let amount = document.createElement("span") //check
        let firstName = document.createElement("span") //check
        let lastName = document.createElement("span") //check
        let desc = document.createElement("span") //check
        let submitted = document.createElement("span") //check
        let resolved = document.createElement("span")
        let type = document.createElement("span") //check
        //let status = document.createElement("div")
        let resolverFirstName = document.createElement("span") //check
        let resolverLastName = document.createElement("span") //check
        let btnCont = document.createElement("span") //check
        let acptBtn = document.createElement("button") //check
        let rejBtn = document.createElement("button") //check

        let count = 0;

        amount.style.width = "250px"
        firstName.style.width = "250px"
        lastName.style.width = "250px"
        desc.style.width = "250px"
        submitted.style.width = "250px"
        resolved.style.width = "250px"
        type.style.width = "250px"
        resolverFirstName.style.width = "250px"
        resolverLastName.style.width = "250px"
        btnCont.style.width = "250px"

        row.className = "row"
        amount.className = "cell"
        firstName.className = "cell"
        lastName.className = "cell"
        desc.className = "cell"
        submitted.className = "cell"
        resolved.className = "cell"
        type.className = "cell"
        resolverFirstName.className = "cell"
        resolverLastName.className = "cell"
        btnCont.className = "button cell"
        acptBtn.className = "button"
        rejBtn.className = "button"
        acptBtn.id = "accept"
        rejBtn.id = "reject"


        userData.data.forEach(u => {

            if(element.resolver == "" && count == 0)
            {
                resolverFirstName.innerText = "Pending"
                resolverLastName.innerText = "Pending"
                resolved.innerText = "Pending"
                count += 1;
            }
            
            if(element.author == u.userID)
            {
                firstName.innerText = u.firstName
                lastName.innerText = u.lastName
                count += 1;
            }

            if(element.resolver == u.userID){
                //console.log("Resolver")
                //console.log(element.resolved)
                resolverFirstName.innerText = u.firstName
                resolverLastName.innerText = u.lastName
                resolved.innerText = element.resolved
                count += 1;
            }
        })


        amount.innerText = element.amount;
        desc.innerText = element.description;
        submitted.innerText = element.submitted;

        if(resolved.innerText == null)
            resolved.innerText = "Not resolved";


        //I was going to do this and the user data with views but that would require retooling of the database that I no longer have time for

        //Type: 1 Lodging, 2 Travel, 3 Food, 4 Other
        console.log("type")
        console.log(element.type)

        if(element.type == 1)
            type.innerText = "Lodging";
        else if(element.type == 2)
            type.innerText = "Travel";
        else if(element.type == 3)
            type.innerText = "Food";
        else if(element.type == 4)
            type.innerText = "Other";

        //Now append all of the items to the row.
        
        row.appendChild(firstName)
        row.appendChild(lastName)
        row.appendChild(desc)
        row.appendChild(submitted)

        row.appendChild(amount)
        row.appendChild(type)

        row.appendChild(resolved)
        row.appendChild(resolverFirstName)
        row.appendChild(resolverLastName)


        //https://www.w3schools.com/howto/howto_js_dropdown.asp
        
        
        //if the reimbursement is pending, add buttons to accept/reject


        //console.log("element")    
        //console.log(element)
            if(manager && element.status == 1){
            acptBtn.onclick = async function(){
            let checkAcpt = await fetch(`${domain}/api/accept?id=${userId}&reimb=${element.reim_ID}`)
            
            console.log("checkAcpt")
            console.log(checkAcpt)
            
            //if(checkAcpt.success)
            window.location = `${domain}/dashboard/?id=${userId}`
        }
            rejBtn.onclick = async function(){
            let checkRej = await fetch(`${domain}/api/reject?id=${userId}&reimb=${element.reim_ID}`)
            

            console.log("checkRej")
            console.log(checkRej)

            //if(checkRej.success)
            window.location = `${domain}/dashboard/?id=${userId}`
        }

            acptBtn.innerText = "Accept"
            rejBtn.innerText = "Reject"
            
            btnCont.appendChild(acptBtn);
            btnCont.appendChild(rejBtn);
            row.appendChild(btnCont);
        }

        //Use status to determine which table the Reimbursement is sorted into
        //Status: 1 pending, 2 accepted, 3 rejected, 4 fulfilled
        

        if (element.status == 1)
        pendingTable.appendChild(row);
        else if (element.status == 2)
        acceptedTable.appendChild(row);
        else if (element.status == 3)
        rejectedTable.appendChild(row);
        else if (element.status == 4)
        fulfilledTable.appendChild(row);
    });

    pendingTable.className = "subtable"
    acceptedTable.className = "subtable"
    rejectedTable.className = "subtable"
    fulfilledTable.className = "subtable"


    pendingTable.id = "pending table"
    acceptedTable.id = "accepted table"
    rejectedTable.id = "rejected table"
    fulfilledTable.id = "fulfilled table"
    
    console.log("add tables")

    theTable.appendChild(pendingTable)
    theTable.appendChild(acceptedTable)
    theTable.appendChild(rejectedTable)
    //theTable.appendChild(fulfilledTable)
    
    //thePage.appendChild(theTable)

}