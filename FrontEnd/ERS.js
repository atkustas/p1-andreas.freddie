const url = "http://localhost:8090/"

var userOb;

document.getElementById("loginButton").addEventListener("click", loginFunc);
//document.getElementById("view").addEventListener("click", changeVisList1);
document.getElementById("view").addEventListener("click", userTicketsEmp);
document.getElementById("add").addEventListener("click", newTicket);
document.getElementById("viewEmp").addEventListener("click", viewTicketsMang);


//login functionality
async function loginFunc() {

    let usern = document.getElementById("username").value;
    let userp = document.getElementById("password").value;

    //we want to send the user/pass as JSON, so we need to make a JS object to send
    let user = {
        username: usern,
        password: userp,
        role_id: role = null
    }

    console.log(user);

    let response = await fetch(url + "login", {

        method: "POST",
        body: JSON.stringify(user),
        credentials: "include"
    });


    console.log(response.status);
    console.log(response.body);

    if (response.status === 200) {

        document.getElementById("login-row").innerText = "Welcome!"

        let response2 = await fetch(url + "redirect", {

            method: "POST",
            body: JSON.stringify(user),
            credentials: "include"
        });
    
        let data = await response2.json();

        for (let user of data) {

            userOb = user;
            console.log(JSON.stringify(userOb));

            if (user.role_id.roleID == 1) {
               //display employee container / buttons
               console.log("Showing employee portal");
               changeVisEmpPage();
               console.log("Showing employee portal");
            }
            if (user.role_id.roleID == 2) {
                //display manager container / buttons
                console.log("Showing manager portal");
                changeVisManPage();
                console.log("Showing manager portal");
            }
       }


    } else {
        document.getElementById("login-row").innerText = "Login failed! Check credentials and try again."
    }


};

 //view tickets function
 async function userTicketsEmp(){

    console.log("inside the view tickets async")
    console.log("This is the userOb: " + userOb);

    let response = await fetch(url + "userticketsemp", {

        method: "POST",
        body: JSON.stringify(userOb),
        credentials: "include"

    });

    console.log(response.status);


    if(response.status === 200){

        let data = await response.json();

        console.log(JSON.stringify(data));

    }

};

//add ticket function
async function newTicket() {

    console.log("inside the newTicket async");
    console.log("This is the userOb: " + userOb);

    //populate form with userOb data (first/last name), user_id (regular employee is signed it at this point)
    //remember to use timestamp function to set re_submitted --- "timestamp = new Date();"

    //this will be where the JS reads the ticket parameters from the form inputs
    //document.getElement....value for each ticket.parameter
    //need to have all the parameters set for the server to accept the ticket
    
    let ticket;

    console.log(ticket);

    let response = await fetch(url + "newticket", {

        method: "POST",
        body: JSON.stringify(ticket),
        credentials: "include"

    });

    if(response.status === 200){
        
        let data = await response.json();
        console.log(JSON.stringify(data));
        console.log("New ticket submitted.");

        //call function to disappear ticket form and show success message

    }
};

async function viewTicketsMang(){

    console.log("inside the manager view all tickets async");
    console.log(userOb);

    let response = await fetch(url + "alltickets", {

        credentials: "include"

    });

    if(response.status === 200){

        let data = await response.json();
        console.log(JSON.stringify(data));
        console.log("All tickets viewed");

        for(let ticket of data){

            //populate each element with tickets received

        }
    }

};

//not sure if we need this one, skipping for now
async function ticketById(){
    //put on button viewable inside the all tickets table
    console.log("inside ticketById async");
    console.log(JSON.stringify(userOb));

    let response = await fetch(url + "ticketbyid")



}

async function approveDeny(){
    //put button on each ticket
    console.log("inside the approve/deny async");
    console.log(JSON.stringify(userOb));

    let ticket;
    //construct ticket from form data same as newticket()
    //don't forget timestamp
    //set resolver to userOb (manager is signed in at this point)

    let response = await fetch(url + "approvedeny", {

        method: "POST",
        body: JSON.stringify(ticket),
        credentials: "include"

    });

    if(response.status === 200){
        
        let data = await response.json();
        console.log(JSON.stringify(data));
        console.log("Ticket status updated.");

        //disappear ticket & show success message in window

    }


}

//Manger functions

//normal emp forms and bhuttons
document.getElementById("addMan").addEventListener("click", changeVisFormManTick);
document.getElementById("viewMan").addEventListener("click", changeVisListManSelf);
document.getElementById("updateMan").addEventListener("click", changeVisUpdateManSelf);

document.getElementById("mansubForm").addEventListener("click", changeVisFormManTick);
document.getElementById("subUpMan").addEventListener("click", changeVisUpdateManSelf);

//manger special forms/list and buttons
document.getElementById("subUpMan2").addEventListener("click", changeVisUpdateManEmp);
document.getElementById("viewEmpMan").addEventListener("click", changeVisListManEmp);
document.getElementById("updateStatMan").addEventListener("click", changeVisUpdateManEmp);

document.getElementById("manListButD").addEventListener("click", changeVisListManSelf);
document.getElementById("manEmpListButD").addEventListener("click", changeVisListManEmp);

//document.getElementById("subForm2").addEventListener("click", changeVisForm3);

function changeVisManPage() {

    document.getElementById("ManMainCon").style.visibility = "visible";

}


function changeVisFormManTick() {

    document.getElementById("ManMasterForm").style.visibility =  !document.getElementById("ManMasterForm").style.visibility;

}


function changeVisListManEmp() {


    document.getElementById("ManticketList").style.visibility = !document.getElementById("ManticketList").style.visibility;


}

function changeVisListManSelf() {


    document.getElementById("ManticketList2").style.visibility =  !document.getElementById("ManticketList2").style.visibility;


}

function changeVisUpdateManSelf() {

    document.getElementById("updateAppMan").style.visibility = !document.getElementById("updateAppMan").style.visibility;

}

function changeVisUpdateManEmp() {

    document.getElementById("updateAppMan2").style.visibility = !document.getElementById("updateAppMan2").style.visibility;

}


//Employee Functions
//sprite toggles
var TicketForm = new Boolean(false);
var ListEmp = new Boolean(false);
var UpForm = new Boolean(false);

document.getElementById("addEmp").addEventListener("click", changeVisEmpForm);
document.getElementById("empsubForm").addEventListener("click", changeVisEmpForm);

document.getElementById("viewEmp").addEventListener("click", changeVisEmpList);
document.getElementById("listButEmpD").addEventListener("click", changeVisEmpList);

document.getElementById("updateEmp").addEventListener("click", changeVisUpdateEmp);
document.getElementById("empsubUp").addEventListener("click", changeVisUpdateEmp);



function changeVisEmpPage(){

    document.getElementById("employeeMainCon").style.visibility= "visible";

}

function changeVisEmpForm(){

    this.TicketForm = !this.TicketForm;
    console.log(TicketForm);

    document.getElementById("EmpMasterForm").style.visibility= !document.getElementById("EmpMasterForm").style.visibility;

}

function changeVisEmpList(){

    this.ListEmp = !this.ListEmp;
    
    document.getElementById("ticketListEmp").style.visibility= !document.getElementById("ticketListEmp").style.visibility;
    

}

function changeVisUpdateEmp(){
    
    this.UpForm = !this.UpForm;

    document.getElementById("empupdateApp").style.visibility= !document.getElementById("empupdateApp").style.visibility;

}

