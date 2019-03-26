import React, { Component } from 'react';

class Logout extends Component {

	constructor(props) {
        super(props);
    }

    render() {
        return <button type="submit" class="btn btn-secondary my-2 my-sm-0" onClick={() => this.props.setLogout()}>Déconnexion</button>
    }
}

export default Logout;
