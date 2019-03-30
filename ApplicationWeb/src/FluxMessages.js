import React, { Component } from 'react';
import Message from './Message';
import MessageInput from './MessageInput';

class FluxMessages extends Component {

	constructor(props) {
        super(props);
    }

    render() {
        return ( 
            <div className="overflow-auto flux">
                <MessageInput/>
                <Message/>
                <Message/>
                <Message/>
                <Message/>
             </div>
               
       )
    }
}

export default FluxMessages;
