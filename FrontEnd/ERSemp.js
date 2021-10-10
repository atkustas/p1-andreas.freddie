const url1 = "http://localhost:8090/"

document.getElementById("addEmp").addEventListener("click", changeVisEmpForm);
document.getElementById("empsubForm").addEventListener("click", changeVisEmpForm);

document.getElementById("viewEmp").addEventListener("click", changeVisEmpList);
document.getElementById("listButEmpD").addEventListener("click", changeVisEmpList);

document.getElementById("updateEmp").addEventListener("click", changeVisUpdateEmp);
document.getElementById("empsubUp").addEventListener("click", changeVisUpdateEmp);



function addFunc(){

    document.getElementById("employeeMainCon").style.visibility= !document.getElementById("employeeMainCon").style.visibility;

}

function changeVisEmpForm(){

    document.getElementById("MasterForm").style.visibility= !document.getElementById("MasterForm").style.visibility;

}

function changeVisEmpList(){

   
        document.getElementById("ticketList").style.visibility= !document.getElementById("ticketList").style.visibility;
    

}

function changeVisUpdateEmp(){

    document.getElementById("empupdateApp").style.visibility= !document.getElementById("empupdateApp").style.visibility;

}



