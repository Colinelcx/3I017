import React, { Component } from 'react';
import Message from './Message';

class FluxMessages extends Component {

	constructor(props) {
        super(props);
    }

    render() {
        return ( 
            <div className="overflow-auto flux">
                <Message/>
                <Message/>
                <Message/>
                <Message/>
                <Message/>
                <Message/>
                <Message/>
                <Message/>
             </div>
               
       )
    }
}

export default FluxMessages;
