import React, { Component } from 'react';
import FluxMessages from './FluxMessages';
import Statistiques from './Statistiques';

class TimeLine extends Component {

	constructor(props) {
        super(props);
    }

    render() {
        return (<div className="row justify-content-md-center">
                    <div className="col-3">
                        < Statistiques messageStat={this.props.messageStat} userStat={this.props.userStat} />
                    </div>
                    <div className="col-7">
                        <FluxMessages page={this.props.page} getMessages={this.props.getMessages} findUser={this.props.findUser}
                        addMessage={this.props.addMessage} login={this.props.login} />
                    </div>
                </div>
        )}
}

export default TimeLine;
