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
                        < Profile addFriend={this.props.addFriend} removeFriend={this.props.removeFriend} login={this.props.login} id={this.props.id} nom={this.props.nom} prenom={this.props.prenom} isFriend={this.props.isFriend} />
                    </div>
                    <div className="col-7">
                        <FluxMessages page={this.props.page} getMessages={this.props.getMessages} findUser={this.props.findUser} getMessages={this.props.getMessages} />
                    </div>
                </div>
        )}
}

export default ProfileTimeLine;
