import React, { Component } from 'react';
import FluxMessages from './FluxMessages';
import Statistiques from './Statistiques';

class TimeLine extends Component {

	constructor(props) {
        super(props);
    }

    render() {
        return (<div className="row">
                    <div className="col-sm-3">
                        < Statistiques />
                    </div>
                    <div className="col-sm">
                        <FluxMessages />
                    </div>
                </div>
        )}
}

export default TimeLine;
