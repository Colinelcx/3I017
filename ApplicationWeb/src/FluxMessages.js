import React, { Component } from 'react';
import Message from './Message';
import MessageInput from './MessageInput';

class FluxMessages extends Component {

	constructor(props) {
        super(props);
    }

    render() {
        return (
            this.props.page==="mur" ?

                <div className="overflow-auto flux">
                    <MessageInput/>
                    <Message/>
                    <Message/>
                    <Message/>
                    <Message/>
                </div>

             :

                <div className="overflow-auto flux">
                    <Message/>
                    <Message/>
                    <Message/>
                    <Message/>
                </div>


               
       )
    }
}

export default FluxMessages;
