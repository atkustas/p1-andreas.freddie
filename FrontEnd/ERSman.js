const url1 = "http://localhost:8090/"

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

