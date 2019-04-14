import React, { Component } from 'react';

class Statistiques extends Component {

	constructor(props) {
        super(props);
    }

    render() {
        return (
                <div className="card bg-white shadow card-stats mt-3 ml-3" >

                     <img className="card-img-top bg-light " src={require('./medias/twistter.png')} alt="twister"></img>

                    <div className="card-header border-secondary">
                        <h3 className="card-title text-secondary">Statistiques : </h3>
                    </div>
                    
                    <ul className="list-group">
                        <li className="list-group-item">Users: {this.props.messageStat} </li>
                        <li className="list-group-item">Messages: {this.props.userStat} </li>
                    </ul>
                </div>
        )
    }
}

export default Statistiques;
