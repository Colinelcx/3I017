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
                        < Profile />
                    </div>
                    <div className="col-7">
                        <FluxMessages />
                    </div>
                </div>
        )}
}

export default ProfileTimeLine;
