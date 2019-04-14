import React, { Component } from 'react';
import Message from './Message';
import MessageInput from './MessageInput';

class FluxMessages extends Component {

	constructor(props) {
        super(props);
        //console.log(props);
        //console.log(props.getMessages());

        this.messageArray = this.messageArray.bind(this);
        
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
                    <MessageInput addMessage={this.props.addMessage} /> : null}

                    {/*this.messageArray()*/
                    /*{"123":["Document{username:'test', id:234, nom:'testn', prenom:'testpn', date:'123', text='123'}"],"111":["Document{username:'test', id:234, nom:'testn', prenom:'testpn', date:'123', text='123'}"]}.map((key,value) =>
                    <Message login={JSON.parse(value)["username"]} id={JSON.parse(value)["id_user"]} nom={JSON.parse(value)["nom"]}
                    prenom={JSON.parse(value)["prenom"]} date ={JSON.parse(value)["date"]} text={JSON.parse(value)["text"]} />)*/}
                    
                </div>

                           
       )
    }
}

export default FluxMessages;

//<Message findUser={this.props.findUser} login={"colcx"} id={1234} nom={"La"} prenom={"Col"} date ={"date"} text={"sampletext"} />}

//{"123":["Document{username:'test', id:234, nom:'testn', prenom:'testpn', date:'123', text:'123'}"],"111":["Document{username:'test', id:234, nom:'testn', prenom:'testpn', date:'123', text='123'}"]}
