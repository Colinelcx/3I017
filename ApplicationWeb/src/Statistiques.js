import React, { Component } from 'react';

class Statistiques extends Component {

	constructor(props) {
        super(props);
    }

    render() {
        return (
                <div className="card bg-light shadow-sm card-stats border-primary mt-3 ml-3" >

                     <img className="card-img-top" src={require('./medias/twistter.png')} alt="Card image cap"></img>

                    <div className="card-header">
                        <h3 className="card-title text-primary">Statistiques</h3>
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

export default Statistiques;
