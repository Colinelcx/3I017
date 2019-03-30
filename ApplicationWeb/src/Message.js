import React, { Component } from 'react';

class Message extends Component {

	constructor(props) {
        super(props);
    }

    render() {
        return ( 
            <div class="card bg-light mb-3 shadow-sm mr-3" >
                <div class="card-header">
                         <div class="row">
                            <div class="card-text font-weight-bold ml-3">Charel Felten</div>
                            <div class="card-text ml-3">@charel</div>
                            <div class="card-text ml-3">29 mars 2019</div>
                        </div>
                </div>
                <div class="card-body">
                    <p class="card-text">Ceci est le texte du message.</p>
                </div> 
            </div>
       )
    }
}

export default Message;
