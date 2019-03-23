import React, { Component } from 'react';
import LoginForm from './LoginForm';
import Logout from './Logout';

class NavigationPanel extends Component {

	constructor(props) {
        super(props);
    }

    render() {
        return (
        <nav >
            {this.props.isConnected==false ?
                //Page de connexion
                    <div>
                        <div class="media logo-accueil">
                            <img src={require('./medias/twistter.png')} alt="Bootstrap" class="img-responsive logo-accueil"/>
                        </div>
                        <div class="col-md-4 col-sm-7 form-center">
                            < LoginForm getConnected={this.isConnected}/>
                        </div>
                    </div>

                :  
                    
                //Header 
                <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
                    <a class="navbar-brand" href="#">Logo</a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
                        <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="#">Accueil <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Photo</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Profil</a>
                            <div class="dropdown-menu" aria-labelledby="dropdown01">
                                <a class="dropdown-item" href="#">Action</a>
                                <a class="dropdown-item" href="#">Another action</a>
                                <a class="dropdown-item" href="#">Something else here</a>
                            </div>
                        </li>
                        </ul>
                        <form class="form-inline my-2 my-lg-0">
                        <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search"/>
                        <button class="btn btn-secondary my-2 my-sm-0" type="submit">Rechercher</button>
                        </form>
                    </div>
                </nav>
            }
        </nav>
        )
    }
}

export default NavigationPanel;
