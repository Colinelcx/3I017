import React, { Component } from 'react';

class LoginForm extends Component {

	constructor(props) {
        super(props);
    }

    render() {
        return ( 
            <div className="shadow p-3 bg-white rounded ">
                <div className="text-center h2">
                    Connexion
                </div>
                <form>
                    <div className="form-group">
                        <label for="username">Pseudo</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroupPrepend2">@</span>
                            </div>
                            <input type="text" className="form-control" id="validationDefaultUsername" placeholder="Username"></input> 
                        </div>
                    </div>
                    <div className="form-group">
                        <label for="username">Mot de passe</label>
                        <input type="password" class="form-control" id="password" placeholder="Entrez votre mot de passe"/>
                    </div>
                    <button type="submit" class="btn btn-primary">Connexion</button>
                </form>
                <div className="text-right">
                    <a href=""> Mot de passe oublié </a>
                </div>
                <div className="text-right">
                    <a href=""> Pas encore inscrit ?</a>
                </div>
            </div> 
       )
    }
}

export default LoginForm;
