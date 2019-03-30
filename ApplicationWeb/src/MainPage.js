import React, { Component } from 'react';
import NavigationPanel from './NavigationPanel';
import Profile from './Profile';
import TimeLine from './TimeLine';
import FooterPanel from './FooterPanel';
import ProfileTimeLine from './ProfileTimeLine';



class MainPage extends Component {

	constructor(props) {
		super(props);
		this.state = {isConnected: true, page:"profile"};
		this.getConnected = this.getConnected.bind(this);
        this.setLogout = this.setLogout.bind(this);
        this.goToLogin = this.goToLogin.bind(this);
        this.goToSignIn = this.goToSignIn.bind(this);
        this.goToTimeLine = this.goToTimeLine.bind(this);
        this.goToProfile = this.goToProfile.bind(this);

	}
	
	getConnected() {
		this.setState({isConnected: true, page:"mur"});
	}
	
	setLogout() {
		this.setState({isConnected: false, page:"login"});
	}
    
    goToLogin() {
        this.setState({isConnected:false, page:"login"});
    }

    goToSignIn(){
        this.setState({isConnected:false, page:"signin"});
    }    

    goToTimeLine(){
        this.setState({isConnected:true, page:"mur"});
    }

    goToProfile(){
        this.setState({isConnected:true, page:"profile"});
    }


	render() {
		return (
            <div className="main">
                <div className="height-full bg-light" id="MainPage">

                    {/*Panneau de navigation (Page de connexion, d'inscription ou header selon le cas)*/}
                    < NavigationPanel isConnected={this.state.isConnected} page={this.state.page} setLogout={this.setLogout} 
                    getConnected={this.getConnected} goToLogin ={this.goToLogin} gotToSignIn={this.goToSignIn}
            goToTimeLine={this.goToTimeLine} goToProfile={this.goToProfile}/>

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
