import React, { Component } from 'react';

class NavigationPanel extends Component {
	constructor(props) {
		super(props);
		
	}
	
	
	render() {
		return (
			<nav ClassName="NavigationPanel">
			{ this.props.isConnected === true ? <Logout setLogout=this.props.setLogout/> : < Login  getConnected=this.props.getConnected /> }
			</nav>
		)
	}
}

export default NavigationPanel;
