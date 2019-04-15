import React, { Component } from 'react';
import FluxMessages from './FluxMessages';
import Profile from './Profile';

class ProfileTimeLine extends Component {

	constructor(props) {
        super(props);
    }
    
    

    render() {
        return (<div className="row justify-content-md-center">
                    <div className="col-3 ">
                        < Profile addFriend={this.props.addFriend} removeFriend={this.props.removeFriend}
                        login={this.props.login} id={this.props.id} nom={this.props.nom} prenom={this.props.prenom} mail={this.props.mail} isFriend={this.props.isFriend} />
                    </div>
                    <div className="col-9 messsages-profile">
                        <FluxMessages page={this.props.page} getMessages={this.props.getMessages} findUser={this.props.findUser} messages={this.props.messages}
                        login={this.props.login} addMessage={this.props.addMessage}/>
                    </div>
                </div>
        )}
}

export default ProfileTimeLine;
