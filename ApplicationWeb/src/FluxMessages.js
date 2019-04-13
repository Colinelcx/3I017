import React, { Component } from 'react';
import Message from './Message';
import MessageInput from './MessageInput';

class FluxMessages extends Component {

	constructor(props) {
        super(props);
        //console.log(props);
        //console.log(props.getMessages());

        //this.data = this.props.getMessages();
        
    }


    messageArray () {
        if (this.props.page === "mur") {
            var ma = this.props.getMessages("user", this.props.login);
        } else {
            var ma = this.props.getMessages("timeline", this.props.login);
        }
        return ma;
    }

    render() {
        return (
            

                <div className="overflow-auto flux">

                    {this.props.page==="mur" ?
                    <MessageInput/> : null}

                    {/*messageArray().map((key,value) =>
                        obj = JSON.parse(key))
                        
                        
                        
                        this.data.messages.items.map((login, id, nom, prenom, date, text) =>
                    <Message login={login} id={id} nom={nom} prenom={prenom} date ={date} text={text} />)*/}
                    <Message findUser={this.props.findUser} login={"colcx"} id={1234} nom={"La"} prenom={"Col"} date ={"date"} text={"sampletext"} />}
                </div>

                           
       )
    }
}

export default FluxMessages;
