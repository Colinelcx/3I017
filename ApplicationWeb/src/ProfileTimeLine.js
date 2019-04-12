import React, { Component } from 'react';
import FluxMessages from './FluxMessages';
import Profile from './Profile';

class ProfileTimeLine extends Component {

	constructor(props) {
        super(props);
    }

    render() {
        return (<div className="row justify-content-md-center">
                    <div className="col-3">
                        < Profile login={this.props.login} id={this.props.id} nom={this.props.nom} prenom={this.props.prenom} isFriend={this.props.isFriend} />
                    </div>
                    <div className="col-7">
                        <FluxMessages page={this.props.page} getMessages={this.props.getMessages} goToFriendProfile={this.props.goToFriendProfile}/>
                    </div>
                </div>
        )}
}

export default ProfileTimeLine;
