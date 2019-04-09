import React, { Component } from 'react';

class Message extends Component {

	constructor(props) {
        super(props);
    }

    render() {
        return ( 
            <div className="card bg-light mb-3 shadow-sm mr-3" >
                <div className="card-header">
                         <div className="row">
                            <div className="card-text font-weight-bold ml-3">Charel Felten</div>
                            <div className="card-text ml-3">@charel</div>
                            <div className="card-text ml-3">29 mars 2019</div>
                        </div>
                </div>
                <div className="card-body">
                    <p className="card-text">Ceci est le texte du message.</p>
                </div> 
            </div>
       )
    }
}

export default Message;
