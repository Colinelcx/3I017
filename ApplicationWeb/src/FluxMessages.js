import React, { Component } from 'react';
import Message from './Message';
import MessageInput from './MessageInput';

class FluxMessages extends Component {

	constructor(props) {
        super(props);
        //console.log(props);
        //console.log(props.getMessages());

        this.data = this.props.getMessages();
        
    }

    render() {
        return (
            

                <div className="overflow-auto flux">

                    {this.props.page==="mur" ?
                    <MessageInput/> : null}

                    {/*this.data.messages.items.map((login, id, nom, prenom, date, text) =>
                    <Message login={login} id={id} nom={nom} prenom={prenom} date ={date} text={text} />)*/
                    <Message goToPersonProfile={this.props.goToPersonProfile} login={"colcx"} id={1234} nom={"La"} prenom={"Col"} date ={"date"} text={"sampletext"} />}
                </div>

                           
       )
    }
}

export default FluxMessages;
