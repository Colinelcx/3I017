import React, { Component } from 'react';
import NavigationPanel from './NavigationPanel';
import Profile from './Profile';
import TimeLine from './TimeLine';
import FooterPanel from './FooterPanel';
import ProfileTimeLine from './ProfileTimeLine';
import axios from 'axios';




class MainPage extends Component {

	constructor(props) {
		super(props);
        // this.state = {isConnected: true, page:"mur", id:"", login:"", key:"", nom:"", prenom:"", mail:"", visiting:"", friend:""}; // not correct start values
        this.state = {isConnected:true, page:"mur", id:0, login:"chf", key:"", nom:"felten", prenom:"charel", mail:"",
                      friendId:0, friendLogin:"colcx", isFriend:1, friendNom:"Coline", friendPrenom:"Lacoux",
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

	}
    
     getConnected (args) {
        //console.log("test");
        //alert(args.username);
        const url = new URLSearchParams();
        url.append("username", args.username); // not 100% sure how to get the username, possibly improvement needed
        url.append("password", args.password);
        axios.post("http://localhost:8080/Twistter/auth/login"+url).then(response => this.getConnectedResponse(response));
    }

    getConnectedResponse (response) {
        var responseObject = JSON.parse(response);
        if (("message" in responseObject) && ("code" in responseObject)) {
            // we know its an error message
            alert(response.data)
        } else {
            // user logged in correctly, we update the state
            this.setState({isConnected: true, page:"mur", id:responseObject["id"], login:responseObject["login"],
                           key:responseObject["key"], nom:responseObject["nom"], prenom:responseObject["prenom"],
                           mail:responseObject["mail"]});
        }
    }


    createAccount () {
        const url = new URLSearchParams();
        url.append("username", this.refs.username);
        url.append("nom", this.refs.nom);
        url.append("prenom", this.refs.prenom);
        url.append("password", this.refs.password);
        url.append("mail", this.refs.mail);
        axios.post("http://localhost:8080/Twistter/user/create"+url).then(response => this.createAccountResponse(response));
    }


    createAccountResponse (response) {
        var responseObject = JSON.parse(response);
        if (("message" in responseObject) && ("code" in responseObject)) {
            // we know its an error message
            alert(response.data)
        } else {
            // user account creation succesful --> user needs to login
            this.goToLogin() 
        }
    }


		
	setLogout() {
        const url = new URLSearchParams();
        url.append("key", this.refs.key);
        axios.post("http://localhost:8080/Twistter/auth/logout"+url)
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

    goToFriendProfile(){
        this.setState({page:"friendProfile"});
    }




    addFriend() {
        const url = new URLSearchParams();
        url.append("key", this.state.key);
        url.append("id_friend", this.state.friendId);
        axios.post("http://localhost:8080/Twistter/friends/add"+url).then(response => this.addFriendResponse(response));
    }

    removeFriend() {
        const url = new URLSearchParams();
        url.append("key", this.state.key);
        url.append("id_friend", this.state.friendId);
        axios.post("http://localhost:8080/Twistter/friends/remove"+url).then(response => this.removeFriendResponse(response));
    }

    addFriendResponse (response) {
        var responseObject = JSON.parse(response);
        if (("message" in responseObject) && ("code" in responseObject)) {
            // we know its an error message
            alert(response.data)
        } else {
            this.setState({isFriend:1}); // added as friend, we indicate the friendship, but this is not important, as any time we visit this friends profile, we will take the values from the databases, not this field
        }
    }


    removeFriendResponse (response) {
        var responseObject = JSON.parse(response);
        if (("message" in responseObject) && ("code" in responseObject)) {
            // we know its an error message
            alert(response.data)
        } else {
            // user account creation succesful --> user needs to login
            this.setState({isFriend:0}); // added as friend, we indicate the friendship, but this is not important, as any time we visit this friends profile, we will take the values from the databases, not this field 
        }
    }





	render() {
		return (
            <div className="main">
                <div className="height-full bg-light" id="MainPage">

                    {/*Panneau de navigation (Page de connexion, d'inscription ou header selon le cas)*/}
                    < NavigationPanel isConnected={this.state.isConnected} page={this.state.page} setLogout={this.setLogout} 
                    getConnected={this.getConnected} goToLogin ={this.goToLogin} goToSignIn={this.goToSignIn}
            goToTimeLine={this.goToTimeLine} goToProfile={this.goToProfile} createAccount={this.createAccount}/>

        <div className="container-fluid content-principal">

                    { this.state.page==="connexion" ? 

                    <div></div>

                    :

                      this.state.page==="mur" ?
                    
                        //Page Timeline
                        <section>
                            < TimeLine page={this.state.page} messageStat={this.state.messageStat} userStat={this.state.userStat}/>
                        </section>
            
                    : 

                        this.state.page==="profile" ? 
                        
                        //Page profil
                        <section>
                            < ProfileTimeLine login={this.state.login} id={this.state.id} nom={this.state.nom} prenom={this.state.prenom} isFriend={2} />
                        </section>
        
                    :

                       this.state.page==="friendProfile" ? 
                        
                        //Page profil
                        <section>
                            < ProfileTimeLine login={this.state.friendLogin} id={this.state.friendId} nom={this.state.friendNom} prenom={this.state.friendPrenom} isFriend={this.state.isFriend} />
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
