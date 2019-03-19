import React, { Component } from 'react';

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
			<div ClassName="MainPage"> MainPage
			< NavigationPanel isConnected=this.isConnected getConnected=this.getConnected setLogout=this.setLogout />
			{ this.state.page === "connexion" ? <Form />
			  this.state.page === "profil" ? <Profil/>
			  							   : <Mur/>
			< FooterPanel />
			
			</div>
		)
	}
}

export default MainPage;
