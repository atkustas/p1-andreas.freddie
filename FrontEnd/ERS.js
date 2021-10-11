const url = "http://localhost:8090/"

let i = null;
var userOb;
var allTickets= [i];
let buttonId;
let buttonName;
var btn;
var btn2;

document.getElementById("loginButton").addEventListener("click", loginFunc);
//document.getElementById("view").addEventListener("click", changeVisList1);
document.getElementById("viewEmp").addEventListener("click", userTicketsEmp);
document.getElementById("empsubForm").addEventListener("click", newTicket);
document.getElementById("viewEmpMan").addEventListener("click", viewTicketsMang);


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

        //document.getElementById("login-row").innerText = "Welcome!"
        console.log("Welcome!")

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

        //populate table
        for(ticket of data){

            let row = document.createElement("tr"); //create table row
            let cell = document.createElement("td"); //create cell for the field
            cell.innerHTML = ticket.re_id;
            row.appendChild(cell);

            let cell2 = document.createElement("td"); //create cell for the field
            cell2.innerHTML = ticket.re_author.id;
            row.appendChild(cell2);

            let cell3 = document.createElement("td"); //create cell for the field
            cell3.innerHTML = ticket.re_amount;
            row.appendChild(cell3);

            let cell4 = document.createElement("td"); //create cell for the field
            cell4.innerHTML = ticket.re_submitted;
            row.appendChild(cell4);

            let cell5 = document.createElement("td"); //create cell for the field
            cell5.innerHTML = ticket.re_type_id.re_type;
            row.appendChild(cell5);

            let cell6 = document.createElement("td"); //create cell for the field
            cell6.innerHTML = ticket.re_status_id.re_status;
            row.appendChild(cell6);

            let cell7 = document.createElement("td"); //create cell for the field
            if(typeof ticket.re_resolver !== 'undefined'){
                cell7.innerHTML = ticket.re_resolver.id;
            }else{
                cell7.innerHTML = "N/A";
                
            }
            
            row.appendChild(cell7);

            let cell8 = document.createElement("td"); //create cell for the field
            if(typeof ticket.re_resolver !== 'undefined'){
                cell8.innerHTML = ticket.re_resolved;
            }else{
                cell8.innerHTML = "TBD";
            }
            row.appendChild(cell8);

            let cell9 = document.createElement("td");
            cell9.innerHTML = ticket.re_desc;
            row.appendChild(cell9);

            document.getElementById("empBody").appendChild(row);

        }

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

    let type_id = 0;
    
    let amount = document.getElementById("EmpFormControlInputAmt").value;
    let timeSubmitted = "10/11/2021"; //edit current date function
    let type = document.getElementById("exampleFormControlSelect1").value;
    let desc = document.getElementById("EmpFormControlTextarea1").value;

        if(type == "Business"){
            type_id = 1;
        }else if(type = "Travel"){
            type_id = 2;
        }else if(type = "Medical"){
            type_id= 3;
        }else{
            type_id = 4;
        }
    
        let type_id_ob = {
            re_type: type,
            re_type_id: type_id
            
        };

        let status_id_ob = {
            re_status: "Pending",
            re_status_id: 3
            
        };


    let ticket = {
        re_amount: amount,
        re_submitted: timeSubmitted,
        re_resolved: null,
        re_desc: desc,
        re_receipt: null,
        re_author: userOb,
        re_resolver: null,
        re_status_id: status_id_ob,
        re_type_id: type_id_ob
    };

    console.log(ticket);
    console.log("sending request");

    let response = await fetch(url + "newticket", {


        method: "POST",
        mode: 'cors',
        body: JSON.stringify(ticket),
        credentials: "include"

    });

    console.log("request got back");
    console.log(response.status);

    if(response.status === 201){
        
        console.log("New ticket submitted.");
        document.getElementById("EmpFormControlInputAmt").value = null;
        document.getElementById("EmpFormControlTextarea1").value = null;

        //call function to disappear ticket form and show success message
        changeHideEmpForm()
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
        console.log(data);
        console.log(data[0].re_id);
        allTickets = data;

    for(ticket of data){
        let row = document.createElement("tr"); //create table row
        let cell = document.createElement("td"); //create cell for the field
        
        cell.innerHTML = ticket.re_id;
        row.appendChild(cell);

        let cell2 = document.createElement("td"); //create cell for the field
        cell2.innerHTML = ticket.re_author.id;
        row.appendChild(cell2);

        let cell3 = document.createElement("td"); //create cell for the field
        cell3.innerHTML = ticket.re_amount;
        row.appendChild(cell3);

        let cell4 = document.createElement("td"); //create cell for the field
        cell4.innerHTML = ticket.re_submitted;
        row.appendChild(cell4);

        let cell5 = document.createElement("td"); //create cell for the field
        cell5.innerHTML = ticket.re_type_id.re_type;
        row.appendChild(cell5);

        let cell6 = document.createElement("td"); //create cell for the field
        cell6.innerHTML = ticket.re_status_id.re_status;
        row.appendChild(cell6);

        let cell7 = document.createElement("td"); //create cell for the field
        if(typeof ticket.re_resolver !== 'undefined'){
            cell7.innerHTML = ticket.re_resolver.id;
        }else{
            cell7.innerHTML = "N/A";
            
        }
        
        row.appendChild(cell7);

        let cell8 = document.createElement("td"); //create cell for the field
        if(typeof ticket.re_resolver !== 'undefined'){
            cell8.innerHTML = ticket.re_resolved;
        }else{
            cell8.innerHTML = "TBD";
        }
        row.appendChild(cell8);

        let cell9 = document.createElement("td");
        var btn = document.createElement("button");
        btn.innerHTML = "Approve";
        btn.name = "approve";
        btn.id = ticket.re_id;
        btn.className = "approveDeny";
        btn.addEventListener("click", function(){
            buttonId = this.id;
            buttonName = "approve"
            approveDeny();
            console.log("ticket.re_id is: " + ticket.re_id);
            console.log(btn.id);
            console.log(btn.name);
            console.log("buttonId is: " +buttonId);
        });
        

        cell9.appendChild(btn);
        row.appendChild(cell9);

        var btn2 = document.createElement("button");
        btn2.innerHTML = "Deny";
        btn2.name = "deny";
        btn2.id = ticket.re_id;
        btn2.className = "approveDeny";
        btn2.addEventListener("click", function(){
            buttonId = this.id;
            buttonName = "deny"
            approveDeny();
            console.log("ticket.re_id is: " + ticket.re_id);
            console.log(btn2.id);
            console.log(btn2.name);
            console.log("buttonId is: " +buttonId);
        });
        

        cell9.appendChild(btn2);
        row.appendChild(cell9);

        let cell10 = document.createElement("td");
        cell10.innerHTML = ticket.re_desc;
        row.appendChild(cell10);


        document.getElementById("manEmpBody").appendChild(row);

        changeVisListManEmp()

            
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

    console.log(allTickets);
    console.log("allTickets length is: " + allTickets.length);

    console.log("buttonId is: " + buttonId);
    console.log("buttonName is: " +buttonName);


    for(i = 0; i < allTickets.length; i++){
        if(buttonId === allTickets[i].re_id){
           ticket = allTickets[i];
        }else{
            i++;
        };
    };

    if(buttonName === "approve"){
        ticket.re_status_id.re_status_id = 1,
        ticket.re_status_id.re_status = "Approved";
    }

    if(buttonName === "deny"){
        ticket.re_status_id.re_status_id = 2,
        ticket.re_status_id.re_status = "Denied";
    }

    ticket.re_resolver = userOb;

    ticket.re_resolved = "10/11/2021";

    //construct ticket from form data same as newticket()
    //don't forget timestamp
    //set resolver to userOb (manager is signed in at this point)

    let response = await fetch(url + "approvedeny", {

        method: "POST",
        body: JSON.stringify(ticket),
        credentials: "include"

    });

    if(response.status === 200){

        console.log(ticket);
        
        // let data = await response.json();
        // console.log(JSON.stringify(data));
        // console.log("Ticket status updated.");

        //disappear ticket & show success message in window

    }


}

//Manager functions

//normal show add,view, or update buttons for just the manger adding tickets
//document.getElementById("addMan").addEventListener("click", changeVisFormManTick);
//document.getElementById("updateMan").addEventListener("click", changeVisUpdateManSelf);
//look at manager sumbited tickets
//document.getElementById("viewMan").addEventListener("click", changeVisListManSelf);

//normal hide buttons for manger, link to the buttons above^^
document.getElementById("mansubForm").addEventListener("click", changeHideFormManTick);
document.getElementById("subUpMan").addEventListener("click", changeHideUpdateManSelf);
document.getElementById("manListButD").addEventListener("click", changeHideListManEmp);

//manger special forms/list and buttons
//displays manger buttons to change employee tickets
document.getElementById("updateStatMan").addEventListener("click", changeVisUpdateManEmp);
document.getElementById("viewEmpMan").addEventListener("click", changeVisListManEmp);

////hide buttons for emp forms and list
document.getElementById("subUpMan2").addEventListener("click", changeHideUpdateManEmp);
//document.getElementById("manEmpListButD").addEventListener("click", changeHideListManSelf);

//document.getElementById("subForm2").addEventListener("click", changeVisForm3);

function changeVisManPage() {

    document.getElementById("ManMainCon").style.visibility = "visible";
    document.getElementById("homeheader-row").style.visibility = "hidden";
    document.getElementById("logincontainer").style.visibility = "hidden";
    }


function changeVisFormManTick() {

    document.getElementById("ManMasterForm").style.visibility = "visible";

}

function changeHideFormManTick() {

    document.getElementById("ManMasterForm").style.visibility = "hidden";

}


function changeVisListManEmp() {


    document.getElementById("ManticketList").style.visibility = "visible";


}

function changeHideListManEmp() {


    document.getElementById("ManticketList").style.visibility = "hidden";
    $("#manEmpTable tbody tr").remove();

}

function changeVisListManSelf() {


    document.getElementById("ManticketList2").style.visibility = "visible";


}

function changeHideListManSelf() {


    document.getElementById("ManticketList2").style.visibility = "hidden";


}

function changeVisUpdateManSelf() {

    document.getElementById("updateAppMan").style.visibility = "visible";

}

function changeHideUpdateManSelf() {

    document.getElementById("updateAppMan").style.visibility = "hidden";

}

function changeVisUpdateManEmp() {

    document.getElementById("updateAppMan2").style.visibility = "visible";

}

function changeHideUpdateManEmp() {

    document.getElementById("updateAppMan2").style.visibility = "hidden";

}

//Employee Functions
//sprite toggles
var TicketForm = new Boolean(false);
var ListEmp = new Boolean(false);
var UpForm = new Boolean(false);

//show/hide add ticket forms
document.getElementById("addEmp").addEventListener("click", changeVisEmpForm);
//document.getElementById("empsubForm").addEventListener("click", changeHideEmpForm);

//show/hide list forms
document.getElementById("viewEmp").addEventListener("click", changeVisEmpList);
document.getElementById("manListButD").addEventListener("click", changeHideEmpList);

//show/hide update forms
//document.getElementById("updateEmp").addEventListener("click", changeVisUpdateEmp);
//document.getElementById("empsubUp").addEventListener("click", changeHideUpdateEmp);



function changeVisEmpPage(){

    document.getElementById("employeeMainCon").style.visibility= "visible";
    document.getElementById("homeheader-row").style.visibility = "hidden";
    document.getElementById("myHeader").style.visibility = "hidden";
    document.getElementById("logincontainer").style.visibility = "hidden";
}

function changeVisEmpForm(){

   
    document.getElementById("EmpMasterForm").style.visibility = "visible";
   

}

function changeHideEmpForm(){

    

    document.getElementById("EmpMasterForm").style.visibility= "hidden";

}


function changeVisEmpList(){

    
    document.getElementById("ticketListEmp").style.visibility= "visible";
    

}

function changeHideEmpList(){

   
    document.getElementById("ticketListEmp").style.visibility= "hidden";
    $("#empTable tbody tr").remove();

}

function changeVisUpdateEmp(){
    
    
    document.getElementById("updateticketForm").style.visibility= "visible";

}

function changeHideUpdateEmp(){
    
   
    document.getElementById("updateticketForm").style.visibility= "hidden";

}

function currentDate(){
    
    new Date();

}
