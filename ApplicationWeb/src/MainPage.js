import React, { Component } from 'react';
import NavigationPanel from './NavigationPanel';
// import Profile from './Profile';
import TimeLine from './TimeLine';
// import FooterPanel from './FooterPanel';
import ProfileTimeLine from './ProfileTimeLine';
import axios from 'axios';




class MainPage extends Component {

    constructor(props) {
        super(props);
        this.state = {isConnected:false, page:"login", id:-1, login:"", key:"", nom:"", prenom:"", mail:"",
                      friendId:0, friendLogin:"", isFriend:1, friendNom:"", friendPrenom:"", friendMail:"",
                      messageStat:0, userStat:0, messages:{}}
                      // 0 = not friend, 1 = friend, 2 = myself
        this.getConnected = this.getConnected.bind(this);
        this.setLogout = this.setLogout.bind(this);
        this.goToLogin = this.goToLogin.bind(this);
        this.goToSignIn = this.goToSignIn.bind(this);
        this.goToTimeLine = this.goToTimeLine.bind(this);
        this.goToProfile = this.goToProfile.bind(this);

        this.getConnectedResponse = this.getConnectedResponse.bind(this); // pas sur s'il faut faire ca et aussi les mettre dans le naviation panel
        this.createAccount = this.createAccount.bind(this);
        this.createAccountResponse = this.createAccountResponse.bind(this);
        this.addFriend = this.addFriend.bind(this);
        this.removeFriend = this.removeFriend.bind(this);
        this.addFriendResponse = this.addFriendResponse.bind(this);
        //this.isFriendResponse = this.isFriendResponse.bind(this);
        this.removeFriendResponse = this.removeFriendResponse.bind(this);

        //this.getMessages = this.getMessages.bind(this);
        //this.goToPersonProfile = this.goToPersonProfile.bind(this);

        this.findUser = this.findUser.bind(this);
        this.getMessages = this.getMessages.bind(this);
        this.findUserResponse = this.findUserResponse.bind(this);
        this.getMessagesResponse = this.getMessagesResponse.bind(this);
        this.addMessage = this.addMessage.bind(this);
        this.addMessageResponse = this.addMessageResponse.bind(this);

    }
    
    
    
     
    getConnected (args) {
        const url = new URLSearchParams();
        url.append("username", args.username);
        url.append("password", args.password);
        console.log("login");
        axios.post("http://localhost:8080/ServerWeb/auth/login?" + url).then(response => this.getConnectedResponse(response));
    }

    getConnectedResponse (response) {
        var responseObject = response.data
        if (("message" in responseObject) && ("code" in responseObject)) {
            // we know its an error message
            alert(JSON.stringify(responseObject))
        } else {
            // user logged in correctly, we update the state
            this.setState({isConnected: true, page:"mur", id:responseObject["id"], login:responseObject["login"],
                           key:responseObject["key"], nom:responseObject["nom"], prenom:responseObject["prenom"],
                           mail:responseObject["mail"]});
        }
    }


    createAccount (args) {
        const url = new URLSearchParams();
        url.append("username", args.username);
        url.append("nom", args.nom);
        url.append("prenom", args.prenom);
        url.append("password", args.password);
        url.append("mail", args.mail);
        axios.post("http://localhost:8080/ServerWeb/user/create?"+url).then(response => this.createAccountResponse(response));
    }


    createAccountResponse (response) {
        var responseObject = response.data
        if (("message" in responseObject) && ("code" in responseObject)) {
            // we know its an error message
            alert(JSON.stringify(responseObject))
        } else {
            // user account creation succesful --> user needs to login
        	var i = this.state.userStat;
        	i += 1;
        	this.setState({userStat:i});
            this.goToLogin() 
        }
    }


        
    
    
    setLogout() { 
        const url = new URLSearchParams();
        url.append("key", this.state.key);
        axios.get('http://localhost:8080/ServerWeb/auth/logout?'+url).catch((error)=> console.log(error));
        this.setState({isConnected:false, page:"login", id:"", login:"", key:"", nom:"", prenom:"", mail:""});
    }
    
    goToLogin() {
        this.setState({page:"login"});
    }

    goToSignIn(){
        this.setState({isConnected:false, page:"signin"});
    }    

    goToTimeLine(){
    	this.getMessages("mur", this.state.login);
        this.setState({page:"mur"});
    }

    goToProfile(){
    	this.getMessages("user", this.state.login);
        this.setState({page:"profile"});
    }


    addFriend() {
        const url = new URLSearchParams();
        url.append("key", this.state.key);
        url.append("id_friend", this.state.friendId);
        axios.post("http://localhost:8080/ServerWeb/friends/add?"+url).then(response => 			this.addFriendResponse(response));
    }

    removeFriend() {
        const url = new URLSearchParams();
        url.append("key", this.state.key);
        url.append("id_friend", this.state.friendId);
        axios.post("http://localhost:8080/ServerWeb/friends/remove?"+url).then(response => 				this.removeFriendResponse(response));
    } 

    addFriendResponse (response) {
        var responseObject = response.data
        if (("message" in responseObject) && ("code" in responseObject)) {
            // we know its an error message
            alert(JSON.stringify(responseObject))
        } else {
            this.setState({isFriend:1}); // added as friend, we indicate the friendship, but this is not important, as any time we visit this friends profile, we will take the values from the databases, not this field
        }
    }


    removeFriendResponse (response) {
        var responseObject = response.data
        if (("message" in responseObject) && ("code" in responseObject)) {
            // we know its an error message
            alert(JSON.stringify(responseObject))
        } else {
            // user account creation succesful --> user needs to login
            this.setState({isFriend:0}); // added as friend, we indicate the friendship, but this is not important, as any time we visit this friends profile, we will take the values from the databases, not this field 
        }
    }


    addMessage(text) {
    	console.log(text);
        const url = new URLSearchParams();
        url.append("key", this.state.key);
        url.append("text", text);
        axios.post("http://localhost:8080/ServerWeb/comment/add?"+url).then(response => 			this.addMessageResponse(response));
    }

    addMessageResponse (response) {
    	console.log(response.data);
        var responseObject = response.data
        if (("message" in responseObject) && ("code" in responseObject)) {
            // we know its an error message
            alert(JSON.stringify(responseObject))
        } else {
        	var j = this.state.messageStat;
        	j += 1;
        	this.setState({userStat:j});
        }
    }


    getMessages(type, login){

        // on convertit type mur --> timeline et type profile --> user pour le servlet
        if (type === "mur") {
            type = "timeline";
        } else {
            type = "user";
        }
		    console.log("get messages ", login, type);
		    const url = new URLSearchParams();
		    url.append("key", this.state.key);
		    url.append("type", type);
		    url.append("user", login);
		    console.log(type, login);
		    axios.post("http://localhost:8080/ServerWeb/search?"+url).then(response => 				this.getMessagesResponse(response));


	}


    getMessagesResponse (response) {
    	console.log(response );
        var responseObject = response.data
        if (("message" in responseObject) && ("code" in responseObject)) {
            // we know its an error message
            alert(JSON.stringify(responseObject))
        } else {
        	console.log("response ", responseObject);
        	this.setState({messages:responseObject});

        }
    }


    
    




    findUser(login){
        console.log(login);
        this.getMessages("user", login);
        // we now have the string of the login
        const url = new URLSearchParams();
        url.append("key", this.state.key);
        url.append("username", login);
        axios.post("http://localhost:8080/ServerWeb/user/infos?"+url).then(response => this.findUserResponse(response));
    }

    findUserResponse (response) {
        var responseObject = response.data
        if (("message" in responseObject) && ("code" in responseObject)) {
            // we know its an error message
            alert(JSON.stringify(responseObject))
        } else {
        	
            this.setState({page:"personProfile", friendId:responseObject["id_user"], friendLogin:responseObject["username"],
            isFriend:responseObject["friend"], friendNom:responseObject["nom"], friendPrenom:responseObject["prenom"], friendMail:responseObject["mail"],});
        }
    }






    render() {
    	console.log("mainpage.js: ", this.state);
        return (
            <div className="main">
                <div className="height-full bg-light" id="MainPage">

                    {/*Panneau de navigation (Page de connexion, d'inscription ou header selon le cas)*/}
                    < NavigationPanel isConnected={this.state.isConnected} page={this.state.page} setLogout={this.setLogout} 
                    getConnected={this.getConnected} goToLogin ={this.goToLogin} goToSignIn={this.goToSignIn}
                    goToTimeLine={this.goToTimeLine} goToProfile={this.goToProfile} createAccount={this.createAccount}
                    findUser={this.findUser}/>

        <div className="container-fluid content-principal ">

                    { this.state.page==="connexion" ? 

                    <div></div>

                    :

                      this.state.page==="mur" ?
                    
                        //Page Timeline
                        <section>
                            < TimeLine page={this.state.page} messageStat={this.state.messageStat}
                            userStat={this.state.userStat} 
                            findUser={this.findUser} getMessages={this.getMessages}
                            addMessage={this.addMessage} login={this.state.login} messages={this.state.messages}/>
                        </section>
            
                    : 

                        this.state.page==="profile" ? 
                        
                        //Page profil
                        <section>
                            < ProfileTimeLine addFriend={this.addFriend} removeFriend={this.removeFriend} login={this.state.login}
                            id={this.state.id} nom={this.state.nom}
                            prenom={this.state.prenom} isFriend={2}
                            mail={this.state.mail}
                            findUser={this.findUser} getMessages={this.getMessages} page={this.state.page} messages={this.state.messages}/>
                        </section>
        
                    :

                       this.state.page==="personProfile" ? 
                        
                        //Page profil
                        <section>
                            < ProfileTimeLine addFriend={this.addFriend} removeFriend={this.removeFriend} login={this.state.friendLogin}
                            id={this.state.friendId} nom={this.state.friendNom}
                            prenom={this.state.friendPrenom} isFriend={this.state.isFriend}
                            findUser={this.findUser} 
                            mail={this.mailFriend} getMessages={this.getMessages} page={this.state.page} messages={this.state.messages}/> 
                        </section>
        
                    : 
                        //Erreur : Page inconnu
                        <div>
                        </div>
                    }
                    
                    </div>
                </div>
            </div>
        )

    }
}

export default MainPage;
