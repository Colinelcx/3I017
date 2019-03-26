import React, { Component } from 'react';
import FluxMessages from './FluxMessages';

class Profil extends Component {

	constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="row">
                <div class="card shadow card-profile" >
                        <img src={require('./medias/default_profile.png')} alt="Bootstrap" class="logo-profile"/>
                        <div class="card-body">
                            <h3 class="card-title">Username</h3>
                            <div class = "card-text">
                                S'abonner
                                 Nombre d'abonnés
                                 Amis
                            </div>
                        </div>
                    </div>
                <div className="col-md-9 flux">
                    <FluxMessages />
                </div>
            </div>
        )
    }
}

export default Profil;
