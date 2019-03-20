import React, { Component } from 'react';
import Login from './Login';
import Logout from './Logout';

class NavigationPanel extends Component {

	constructor(props) {
        super(props);
    }

    render() {
        return (
        <nav>
            { this.props.isConnected===true ? 
                < Logout setLogout={this.props.setLogout}/>
                :
                < Login isConnected={this.props.isConnected}/> 
            }
        </nav>
        )
    }
}

export default NavigationPanel;
