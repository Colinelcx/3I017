import React, { Component } from 'react';

class Message extends Component {

	constructor(props) {
        super(props);
    }

    render() {
        return ( 
            <div class="card bg-primary mb-3 shadow-sm" >
                <div class="card-header">Message</div>
                <div class="card-body">
                    <p class="card-text">Texte du message.</p>
                </div> 
            </div>
       )
    }
}

export default Message;
