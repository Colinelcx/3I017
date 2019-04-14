import React, { Component } from 'react';
// import FluxMessages from './FluxMessages';

class Profile extends Component {

	constructor(props) {
        super(props);
        console.log(props);
    }

    render() {
        return (
             <div className="card bg-light berder-secondary shadow mt-3 ml-3" >

                    <img className="card-img-top logo-profile" src={require('./medias/owl.png')} alt="owl"></img>

                    <div className="card-header bg-white ">
                        <h3 className="card-title text-secondary">{this.props.prenom} {this.props.nom}</h3>
                        <h4 className="card-title text-primary">@{this.props.login}</h4>
                        <h5 className="card-title text-secondary">mail :{this.props.mail}</h5>
                        {this.props.isFriend===0 ?
                            <button className="btn btn-primary mt-2 mb-1" type="submit" onClick={() => this.props.addFriend()} >Ajouter ami</button>
                        :
                        this.props.isFriend===1 ?
                            <button className="btn btn-secondary mt-2 mb-1" type="submit" onClick={() => this.props.removeFriend()} >Supprimer ami</button>
                        : null // ceci est dans le cas ou on est sur son propre profile
                        }
                        

                    </div>

                    
                    
                   
                
            </div>
        )
    }
}

export default Profile;

/*
<ul className="list-group">
                        <li className="list-group-item">Friends: {this.props.getStats(this.props.login)["friends"]} </li>
                        <li className="list-group-item">Messages: {this.props.getStats(this.props.login)["messages"]} </li>
                        <li className="list-group-item">Member since: {this.props.getStats(this.props.login)["date"]} </li>
                    </ul>*/