import React, { Component } from 'react';
import NavigationPanel from './NavigationPanel';
import Profil from './Profil';
import TimeLine from './TimeLine';
import FooterPanel from './FooterPanel';



class MainPage extends Component {

	constructor(props) {
		super(props);
		this.state = {isConnected: false, page:"signin"};
		this.getConnected = this.getConnected.bind(this);
        this.setlogout = this.setLogout.bind(this);
	}
	
	getConnected() {
        alert(test);
		this.setState({isConnected: true, page:"mur"});
	}
	
	setLogout() {
		this.setState({isConnected: false, page:"login"});
	}
	
	render() {
		return (
            <div className="main">
                <div className="height-full bg-light" id="MainPage">

                    {/*Panneau de navigation (Page de connexion, d'inscription ou header selon le cas)*/}
                    < NavigationPanel isConnected={this.state.isConnected} page={this.state.page} setLogout={this.setLogout} getConnected={this.isConnected}/>

                    <div className="container-fluid content-principal">

                    { this.state.page=="connexion" ? 

                    <div></div>

                    :

                      this.state.page=="mur" ?
                    
                        //Page Timeline
                        <section>
                            < Profil />
                        </section>
            
                    : 

                        this.page=="profil" ? 
                        
                        //Page profil
                        <section>
                            Profil
                            < Profil />
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
