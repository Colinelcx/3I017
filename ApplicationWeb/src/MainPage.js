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
		this.state = {isConnected: true, page:"mur", id:"", login:"", key:"", nom:"", prenom:"", mail:""}; // not correct start values
		this.getConnected = this.getConnected.bind(this);
        this.setLogout = this.setLogout.bind(this);
        this.goToLogin = this.goToLogin.bind(this);
        this.goToSignIn = this.goToSignIn.bind(this);
        this.goToTimeLine = this.goToTimeLine.bind(this);
        this.goToProfile = this.goToProfile.bind(this);

        this.getConnectedResponse = this.getConnectedResponse.bind(this); // pas sur s'il faut faire ca et aussi les mettre dans le naviation panel
        this.createAccount = this.createAccount.bind(this);
        this.createAccountResponse = this.createAccountResponse.bind(this);

	}
    
     getConnected (args) {
        console.log("test");
        alert(args.username);
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
                            < TimeLine />
                        </section>
            
                    : 

                        this.state.page==="profile" ? 
                        
                        //Page profil
                        <section>
                            < ProfileTimeLine />
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
