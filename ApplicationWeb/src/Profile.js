import React, { Component } from 'react';
import FluxMessages from './FluxMessages';

class Profile extends Component {

	constructor(props) {
        super(props);
    }

    render() {
        return (
             <div class="card bg-light shadow-sm border-primary mt-3 ml-3" >

                    <img class="card-img-top" src={require('./medias/owl.png')} alt="Card image cap"></img>

                    <div class="card-header">
                        <h3 class="card-title text-primary">Nom Prenom</h3>
                        <h4 class="card-title text-secondary">@Username</h4>
                    </div>
                    
                    <ul class="list-group">
                        <li class="list-group-item">Cras justo odio</li>
                        <li class="list-group-item">Dapibus ac facilisis in</li>
                        <li class="list-group-item">Vestibulum at eros</li>
                    </ul>
                
            </div>
        )
    }
}

export default Profile;

/*
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

            
*/