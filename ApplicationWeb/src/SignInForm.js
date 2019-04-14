import React, { Component } from 'react';

class SignInForm extends Component {

	constructor(props) {
        super(props);
        this.state = {username:"", password:"", nom:"", prenom:"", mail:""};
        this.handleUsername = this.handleUsername.bind(this);
        this.handlePassword = this.handlePassword.bind(this);
        this.handleNom = this.handleNom.bind(this);
        this.handlePrenom = this.handlePrenom.bind(this);
        this.handleMail = this.handleMail.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleUsername(event) {
        this.setState({username: event.target.value});
    }

    handlePassword(event) {
        this.setState({password: event.target.value});
    }

    handleNom(event) {
        this.setState({nom: event.target.value});
    }

    handlePrenom(event) {
        this.setState({prenom: event.target.value});
    }

    handleMail(event) {
        this.setState({mail: event.target.value});
    }

    handleSubmit(event) {
        console.log(JSON.stringify(this.state));
        this.props.createAccount(this.state) 
        //onClick={() => this.props.createAccount()}
    }

    render() {
        return ( 
            <div className="shadow p-3 bg-white rounded ">
                <div className="text-center h2">
                    Inscription
                </div>
                <form onSubmit={this.handleSubmit}>
                    <div className="form-row ">
                        <div className="col-md-6">
                            <label htmlFor="username">Pseudo</label>
                            <div className="input-group">
                                <div className="input-group-prepend">
                                <span className="input-group-text" id="inputGroupPrepend2">@</span>
                                </div>
                                <input type="text" className="form-control" id="username" onChange={this.handleUsername} placeholder="Username" aria-describedby="inputGroupPrepend2"/>
                            </div>
                        </div>
                    </div>
                    <div className="form-row">
                        <div className="col">
                            <label htmlFor="nom">Nom :</label>
                            <input type="text" className="form-control" id="nom" onChange={this.handleNom} placeholder="ex : Dupont"/>
                        </div>
                        <div className="col">
                            <label htmlFor="prenom">Prénom :</label>
                            <input type="text" className="form-control" id="prenom" onChange={this.handlePrenom} placeholder="ex : Alain"/>
                        </div>
                    </div>
                    <div className="form-group">
                        <label htmlFor="mail">Adresse mail :</label>
                        <input type="mail" className="form-control" id="text" onChange={this.handleMail} placeholder="email@example.com"/>
                        <small id="emailHelp" className="form-text text-muted">Une adresse doit être associée à un seul compte</small>
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Mot de passe :</label>
                        <input type="password" className="form-control" id="password" onChange={this.handlePassword} placeholder="Choisissez un mot de passe"/>
                    </div>
                    <div className="form-row">
                        <div className="col">
                            <button type="submit" className="btn btn-primary" >Connexion</button>
                        </div>
                        <div className="col">
                            <button type="reset" className="btn btn-primary">Annuler</button>
                        </div>
                    </div>
                </form>
                <div className="text-right">
                    <a href="#" onClick={() => this.props.goToLogin()}>Déjà inscrit ?</a>
                </div>
            </div> 
       )
    }
}

export default SignInForm;
