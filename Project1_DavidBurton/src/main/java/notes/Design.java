package notes;

public class Design {
}

//User enters the url

//See a login page
//Bar for username (Textbox?)
//Bar for password
//Button to login.
//Button to register new account.

//Three screens to go to from here:
//Register new account
//Submit reimbursement request
//Accept/reject reimbursement requests

//Two kinds of users: Employee and Employer or whatever they get called
//Employee: can submit a reimbursement request
//Boxes (textboxes?) to accept input for the amount, and a description of why. (other information will be pulled from the logged in User object)
//Can view submitted requests that coincide with your ID.
//Displays the amount, date/time submitted,

//Employer: can accept or reject reimbursement requests
//Sees a list of reimbursement requests, each of which has a button to accept or reject.
//When hitting it, changes the data to accepted or rejected and removes it (since it's resolved)

//Use ArrayList to hold all the Users, Reimbursements, Roles, etc
//Send each of the newly created users/reimbursements/roles etc objects to the database
//Get the ID after adding them
//Then add to the previous ArrayList.
//Don't need to spend anywhere near as much time interacting with the database, which cuts down on
//Wait. No. I'd still need to get the new ID for each of these things... crud.
//Well, it will save time if/when I need to go through the entire list.