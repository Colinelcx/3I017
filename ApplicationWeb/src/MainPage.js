import React, { Component } from 'react';
import NavigationPanel from './NavigationPanel';
import ConnexionForm from './ConnexionForm';
import Profil from './Profil';
import TimeLine from './TimeLine';
import FooterPanel from './FooterPanel';



class MainPage extends Component {

	constructor(props) {
		super(props);
		this.state = {isConnected: false, page:"connexion"};
		this.getConnected = this.getConnected.bind(this);
		this.setlogout = this.setLogout.bind(this);
	}
	
	getConnected() {
		this.setState({isConnected: true, page:"mur"})
	}
	
	setLogout() {
		this.setState({isConnected: false, page:"connexion"})
	}
	
	render() {
		return (
			<div className="MainPage"> MainPage
            < NavigationPanel isConnected={this.state.isConnected} setLogout={this.setLogout} getConnected={this.isConnected}/>
            < ConnexionForm />
            < Profil />
            < TimeLine />
            < FooterPanel />
			</div>
		)
	}
}

export default MainPage;
