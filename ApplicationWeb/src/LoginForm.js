import React, { Component } from 'react';

class LoginForm extends Component {

	constructor(props) {
        super(props);
        this.state = {username:"", password:""};
        this.handleUsername = this.handleUsername.bind(this);
        this.handlePassword = this.handlePassword.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }


    handleUsername(event) {
        this.setState({username: event.target.value});
    }

    handlePassword(event) {
        this.setState({password: event.target.value});
    }

    handleSubmit(event) {
        console.log(this.state);
        this.props.getConnected(this.state) // we send the getConnected function all the necessary information to possibly login the user
    }

   



    render() {
        return ( 
            <div className="shadow p-3 bg-white rounded ">
                <div className="text-center h2">
                    Connexion
                </div>
                <form onSubmit={this.handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="username">Pseudo</label>
                        <div className="input-group">
                            <div className="input-group-prepend">
                            <span className="input-group-text" id="inputGroupPrepend2">@</span>
                            </div>
                            <input type="text" className="form-control" id="validationDefaultUsername" placeholder="Entrez votre pseudo" onChange={this.handleUsername}/>
                        </div>
                    </div>
                    <div className="form-group">
                        <label htmlFor="username">Mot de passe</label>
                        <input type="password" className="form-control" id="password" placeholder="Entrez votre mot de passe" onChange={this.handlePassword}/>
                    </div>
                    <button type="submit" className="btn btn-primary">Connexion</button>
                </form>
                <div className="text-right">
                    <a href="#" onClick={() => this.props.goToSignIn()}> Pas encore inscrit ?</a>

                    

                </div>
            </div> 
       )
    }
}

export default LoginForm;
