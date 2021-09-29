const url = "http://localhost:8090/"


document.getElementById("loginButton").addEventListener("click", loginFunc);


//login functionalltiy
async function loginFunc(){

    let usern = document.getElementById("username").value;
    let userp = document.getElementById("password").value;

    //we want to send the user/pass as JDON, so we need to make a JS object to send
    let user={
        username:usern,
        password:userp
    }
    
    console.log(user);

    let response = await fetch(url + "login", {

        method: "POST",
        body: JSON.stringify(user),
        credentials: "include"
    });

    console.log(response.status);

    if(response.status === 200){

        document.getElementById("login-row").innerText="welcome!"


    }else{
        document.getElementById("login-row").innerText="Login failed! Try Again..."
    }

}