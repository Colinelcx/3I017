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
        this.state = {isConnected:false, page:"login", id:12345, login:"chf", key:"1231236jgaduta", nom:"felten", prenom:"charel", mail:"",
                      friendId:0, friendLogin:"test123", isFriend:1, friendNom:"Test", friendPrenom:"Test",
                      messageStat:0, userStat:0};
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

        this.getMessages = this.getMessages.bind(this);
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
        this.setState({page:"mur"});
    }

    goToProfile(){
        this.setState({page:"profile"});
    }

    /*
    goToPersonProfile(args){
        // this function is called when we click on the name of a friend in the url of a message or when we succesfully search for a friend.
        // it updates all the fields in the state that correspond to values of this friend, so we can visit that friends profile
        //alert("test");
        console.log(args);
        this.setState({page:"personProfile"});
        //this.setState({page:"personProfile", friendId:args.id, friendLogin:args.login, isFriend:1, friendNom:args.nom, friendPrenom:args.prenom});
        //this.isFriend(); // update isFriend state

    }
    */

    addFriend() {
        const url = new URLSearchParams();
        url.append("key", this.state.key);
        url.append("id_friend", this.state.friendId);
        axios.post("http://localhost:8080/ServerWeb/friends/add?"+url).then(response => this.addFriendResponse(response));
    }

    removeFriend() {
        const url = new URLSearchParams();
        url.append("key", this.state.key);
        url.append("id_friend", this.state.friendId);
        axios.post("http://localhost:8080/ServerWeb/friends/remove?"+url).then(response => this.removeFriendResponse(response));
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

    /*
    isFriend() {
        const url = new URLSearchParams();
        url.append("key", this.state.key);
        url.append("id_friend", this.state.friendId);
        axios.post("http://localhost:8080/ServerWeb/friends/is"+url).then(response => this.isFriendResponse(response));
    }

    isFriendResponse (response) {
        var responseObject = JSON.parse(response);
        if (("message" in responseObject) && ("code" in responseObject)) {
            // we know its an error message
            alert(response.data)
        } else {
            if (responseObject["message"]==="true") {
                this.setState({isFriend:1});
            } else {
                this.setState({isFriend:0});
            }
        }
    }
    */


    addMessage(text) {
        const url = new URLSearchParams();
        url.append("id_user", this.state.id);
        url.append("text", text);
        axios.post("http://localhost:8080/ServerWeb/comment/add?"+url).then(response => this.addMessage(response));
    }

    addMessageResponse (response) {
        var responseObject = response.data
        if (("message" in responseObject) && ("code" in responseObject)) {
            // we know its an error message
            alert(JSON.stringify(responseObject))
        } else {
            // either do nothing or call getMessages to see the newly added message
            this.getMessages("timeline", this.state.login);
        }
    }



    getMessages(type, login){

        // on convertit type mur --> timeline et type profile --> user pour le servlet
        if (type === "mur") {
            type = "timeline";
        } else {
            type = "user";
        }

        console.log(type, login);
        const url = new URLSearchParams();
        url.append("key", this.state.key);
        url.append("type", type);
        url.append("username", login);
        // axios.post("http://localhost:8080/ServerWeb/search?"+url).then(response => this.getMessagesResponse(response));

        
        // this form must the output have, with these exact fields, brackets, quotes
        // '{"123":{"username":"test", "id":234, "nom":"testn", "prenom":"testpn", "date":"123", "text":"123"},
        // "456":{"username":"secondusername", "id":888, "nom":"secondn", "prenom":"seconpn", "date":"987", "text":"second text"}}'
        // which we can then iterate over as follows: JSON.parse(msgs).map(item => alert(item["id"]));
        return '{"123":{"username":"test", "id":234, "nom":"testn", "prenom":"testpn", "date":"123", "text":"123"}, "456":{"username":"secondusername", "id":888, "nom":"secondn", "prenom":"seconpn", "date":"987", "text":"second text"}}'
    }
s
    getMessagesResponse (response) {
        var responseObject = response.data
        if (("message" in responseObject) && ("code" in responseObject)) {
            // we know its an error message
            alert(JSON.stringify(responseObject))
        } else {
            return responseObject;
        }
    }


    
    




    findUser(login){
        console.log(login);
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
            isFriend:responseObject["friend"], friendNom:responseObject["nom"], friendPrenom:responseObject["prenom"]});
        }
    }






    render() {
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
                            addMessage={this.addMessage} login={this.state.login}/>
                        </section>
            
                    : 

                        this.state.page==="profile" ? 
                        
                        //Page profil
                        <section>
                            < ProfileTimeLine addFriend={this.addFriend} removeFriend={this.removeFriend} login={this.state.login}
                            id={this.state.id} nom={this.state.nom}
                            prenom={this.state.prenom} isFriend={2}
                            findUser={this.findUser} getMessages={this.getMessages} page={this.state.page}/>
                        </section>
        
                    :

                       this.state.page==="personProfile" ? 
                        
                        //Page profil
                        <section>
                            < ProfileTimeLine addFriend={this.addFriend} removeFriend={this.removeFriend} login={this.state.friendLogin}
                            id={this.state.friendId} nom={this.state.friendNom}
                            prenom={this.state.friendPrenom} isFriend={this.state.isFriend}
                            findUser={this.findUser} getMessages={this.getMessages} page={this.state.page} />
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
