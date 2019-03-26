import React, { Component } from 'react';

class SignInForm extends Component {

	constructor(props) {
        super(props);
    }

    render() {
        return ( 
            <div className="shadow p-3 bg-white rounded ">
                <div className="text-center h2">
                    Inscription
                </div>
                <form>
                    <div className="form-row ">
                        <div className="col-md-6">
                            <label for="username">Pseudo</label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroupPrepend2">@</span>
                                </div>
                                <input type="text" class="form-control" id="username" placeholder="Username" aria-describedby="inputGroupPrepend2"/>
                            </div>
                        </div>
                        <div className="col-md-6" >
                            <label for="username-unique"> </label>
                            <input type="text" class="form-control-plaintext text-muted" readonly id="username-unique" placeholder="Le pseudo doit être unique"/>
                        </div>
                    </div>
                    <div className="form-row">
                        <div className="col">
                            <label for="nom">Nom :</label>
                            <input type="text" class="form-control" id="nom" placeholder="ex : Dupont"/>
                        </div>
                        <div className="col">
                            <label for="prenom">Prénom :</label>
                            <input type="text" class="form-control" id="prenom" placeholder="ex : Alain"/>
                        </div>
                    </div>
                    <div className="form-group">
                        <label for="mail">Adresse mail :</label>
                        <input type="mail" class="form-control" id="text" placeholder="email@example.com"/>
                        <small id="emailHelp" class="form-text text-muted">Une adresse doit être associée à un seul compte</small>
                    </div>
                    <div className="form-group">
                        <label for="password">Mot de passe :</label>
                        <input type="password" class="form-control" id="password" placeholder="Choisissez un mot de passe"/>
                    </div>
                    <div className="form-group">
                        <label for="paswordCheck">Vérifiez votre mot de passe :</label>
                        <input type="password" class="form-control" id="password" placeholder="Ressaisissez votre mot de passe"/>
                        <small id="emailHelp" class="form-text text-muted">Attention : Le mot de passe doit être identique</small>
                    </div>
                    <div className="form-row">
                        <div className="col">
                            <button type="submit" class="btn btn-primary col-md-4" onClick={() => alert("test")}>Inscription</button>
                        </div>
                        <div className="col">
                            <button type="submit" class="btn btn-primary col-md-4">Annuler</button>
                        </div>
                    </div>
                </form>
                <div className="text-right">
                    <a href="" > Déjà inscrit ? Connection</a>
                </div>
            </div> 
       )
    }
}

export default SignInForm;