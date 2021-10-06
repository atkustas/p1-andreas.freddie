const url1 = "http://localhost:8090/"

document.getElementById("add").addEventListener("click", changeVisForm);
document.getElementById("subForm").addEventListener("click", changeVisForm2);
document.getElementById("view").addEventListener("click", changeVisList1);
document.getElementById("update").addEventListener("click", changeVisUpdate);
document.getElementById("listBut").addEventListener("click", changeVisList2);
document.getElementById("subUp").addEventListener("click", changeVisUpdate2);

function addFunc(){

    document.getElementById("MasterForm").focus();

}

function changeVisForm(){

    document.getElementById("MasterForm").style.visibility="visible";

}

function changeVisForm2(){

    document.getElementById("MasterForm").style.visibility="hidden";

}

function changeVisList1(){

   
        document.getElementById("ticketList").style.visibility="visible";
    

}

function changeVisList2(){

   
    document.getElementById("ticketList").style.visibility="hidden";


}

function changeVisUpdate(){

    document.getElementById("updateApp").style.visibility="visible";

}

function changeVisUpdate2(){

    document.getElementById("updateApp").style.visibility="hidden";

}

async function assembleFunc() {

    //send a fetch request to our data
    let response = await fetch(url + "avengers", {credentials: "include"});
    let usern = document.getElementById("username").value;
    let userp = document.getElementById("password").value;

    console.log(response);

      //we want to send the user/pass as JDON, so we need to make a JS object to send
      let user={
        username:usern,
        password:userp
    }
    
    console.log(user);

    if (response.status === 200) {

        let data = await response.json();

        for (let avenger of data) {

            let row = document.createElement("tr");

            let cell0 = document.createElement("td");
            cell0.innerHTML = avenger.av_id;
            row.appendChild(cell0);

            let cell1 = document.createElement("td");
            cell1.innerHTML = avenger.av_name;
            row.appendChild(cell1);

            let cell2 = document.createElement("td");
            cell2.innerHTML = avenger.av_power;
            row.appendChild(cell2);

            let cell3 = document.createElement("td");
            cell3.innerHTML = avenger.first_name;
            row.appendChild(cell3);

            let cell4 = document.createElement("td");
            cell4.innerHTML = avenger.last_name;
            row.appendChild(cell4);

            let cell5 = document.createElement("td");
            cell5.innerHTML = avenger.power_level;
            row.appendChild(cell5);

            let cell6 = document.createElement("td");
            cell6.innerHTML = avenger.home_fk.home_name;
            row.appendChild(cell6);


            // <th>ID</th>
            //<th>Hero Name</th>
            //<th>Hero Power</th>
            // <th>First Name</th>
            //<th>Last Name</th>
            // <th>Power Level</th>
            // <th>Home Name</th>




            //the tr called row that was created above in loop needs to get added to the table body
            document.getElementById("avengerBody").appendChild(row);


        }





    }




}
