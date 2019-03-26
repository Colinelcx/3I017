import React, { Component } from 'react';
import FluxMessages from './FluxMessages';
import Statistiques from './Statistiques';

class TimeLine extends Component {

	constructor(props) {
        super(props);
    }

    render() {
        return (<div className="row">
                    < Statistiques />
                    <div className="col-md-9 flux">
                        <FluxMessages />
                    </div>
                </div>
        )}
}

export default TimeLine;
