import React, { Component } from 'react';
import LoginForm from './LoginForm';
import Logout from './Logout';
import SignInForm from './SignInForm';

class NavigationPanel extends Component {

	constructor(props) {
        super(props);
    }

    render() {
        return (
        <nav >
            {this.props.isConnected==false ?

                this.props.page==="signin" ?
                    //Page d'inscription
                        <div className="height-full">
                            <div class="media">
                                <img src={require('./medias/twistter.png')} alt="Bootstrap" class="img-responsive logo-accueil"/>
                            </div>
                            <div class="col-md-6 col-sm-7 form-signin">
                                < SignInForm getConnected={this.props.getConnected}/>
                            </div>
                        </div>
                    :

                    //Page de connexion
                        <div>
                            <div class="media ">
                                <img src={require('./medias/twistter.png')} alt="Bootstrap" class="img-responsive logo-accueil"/>
                            </div>
                            <div class="col-md-4 col-sm-7 form-login">
                                < LoginForm getConnected={this.props.getConnected}/>
                            </div>
                        </div>

                :  
                    
                //Header 
                <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top shadow">
                    <a class="nav-link ">
                        <img src={require('./medias/owl.png')} width="30" height="30" alt="" />
                    </a>

                    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item ">
                                <a class="nav-link" href="#" onClick={() => this.props.goToTimeLine()}>Accueil <span class="sr-only"></span></a>
                            </li>
                            <a class="navbar-brand profil rounded-circle" href="#">
                                <img src={require('./medias/default_profile.png')} width="30" height="30" alt=""/>
                            </a>
                            <li class="nav-item">
                                <a class="nav-link" href="#" onClick={() => this.props.goToProfile()}>Voir mon profil</a>
                            </li>
                            <li class="nav-item"  >   
                                <Logout setLogout={this.props.setLogout}/>
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
