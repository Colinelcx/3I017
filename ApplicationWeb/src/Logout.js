import React, { Component } from 'react';

class Logout extends Component {

	constructor(props) {
        super(props);
    }

    render() {
        return <button type="submit" class="navbar-toggler btn btn-dark">Déconnexion</button>
    }
}

export default Logout;
