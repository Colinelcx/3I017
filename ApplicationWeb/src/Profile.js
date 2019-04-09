import React, { Component } from 'react';
import FluxMessages from './FluxMessages';

class Profile extends Component {

	constructor(props) {
        super(props);
    }

    render() {
        return (
             <div className="card bg-light shadow-sm border-primary mt-3 ml-3" >

                    <img className="card-img-top" src={require('./medias/owl.png')} alt="Card image cap"></img>

                    <div className="card-header">
                        <h3 className="card-title text-primary">Nom Prenom</h3>
                        <h4 className="card-title text-secondary">@Username</h4>
                    </div>
                    
                    <ul className="list-group">
                        <li className="list-group-item">Cras justo odio</li>
                        <li className="list-group-item">Dapibus ac facilisis in</li>
                        <li className="list-group-item">Vestibulum at eros</li>
                    </ul>
                
            </div>
        )
    }
}

export default Profile;
