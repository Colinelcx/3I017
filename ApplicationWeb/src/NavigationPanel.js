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
                            <div className="media">
                                <img src={require('./medias/twistter.png')} alt="Bootstrap" className="img-responsive logo-accueil"/>
                            </div>
                            <div className="col-md-6 col-sm-7 form-signin">
                                < SignInForm goToLogin={this.props.goToLogin}/>
                            </div>
                        </div>
                    :

                    //Page de connexion
                        <div>
                            <div className="media ">
                                <img src={require('./medias/twistter.png')} alt="Bootstrap" className="img-responsive logo-accueil"/>
                            </div>
                            <div className="col-md-4 col-sm-7 form-login">
                                < LoginForm getConnected={this.props.getConnected} goToSignIn={this.props.goToSignIn}/>
                            </div>
                        </div>

                :  
                    
                //Header 
                

                <nav className="navbar navbar-expand-md navbar-dark bg-dark fixed-top shadow">

                    <div className="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
                        <ul className="navbar-nav mr-auto">
                            <li className="nav-item active">
                                <button className="btn btn-secondary" type="submit" onClick={() => this.props.goToTimeLine()} >Accueil</button>
                            </li>
                            <li className="nav-item">
                                 <button className="btn btn-secondary ml-3" type="submit" onClick={() => this.props.goToProfile()} >Profil</button>
                            </li>
                            <li className="nav-item">
                            
                            </li>
                        </ul>
                    </div>

                    <div className="mx-auto order-0 col-8 ">
                        <li className="nav-item active form-inline justify-content-md-center">
                            <input className="form-control col-lg-7 " type="text" placeholder="Search" aria-label="Search"/>
                            <button className="btn btn-primary ml-2" type="submit" >Recherche</button>
                        </li>
                        
                    </div>

                    <div className="navbar-collapse collapse w-100 order-3 dual-collapse2">
                        <ul className="navbar-nav ml-auto">
                            <li className="nav-item">
                                <button className="btn btn-secondary ml-3" type="submit" onClick={() => this.props.setLogout()} >Deconnexion</button>
                            </li>
                        </ul>
                    </div>

                </nav>
            }
        </nav>
        )
    }
}

export default NavigationPanel;

