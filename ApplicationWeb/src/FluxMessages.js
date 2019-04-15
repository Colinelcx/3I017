import React, { Component } from 'react';
import Message from './Message';
import MessageInput from './MessageInput';

class FluxMessages extends Component {

	constructor(props) {
        super(props);

        //console.log(props);
        //console.log(props.getMessages());

        // this.messageArray = this.messageArray.bind(this);
        
    }


    // messageArray () {
    //     // alert(this.props.login);
    //     if (this.props.page === "mur") {
    //         var ma = this.props.getMessages("timeline", this.props.login);
    //     } else {
    //         var ma = this.props.getMessages("mur", this.props.login);
    //     }
    //     return JSON.parse(ma);
    // }
	
	componentDidMount(){
		this.props.getMessages(this.props.page, this.props.login);
	}

    render() {
        
		
		var a = this.props.messages;
		console.log("what fluxmsgs receives", a);
		
		//Object.keys(a).map(idm => console.log(JSON.parse(a[idm][0])));
        
        return (
              <div className="overflow-auto flux">
              
                  {Object.keys(a).map(idm =>
                    <Message  key={idm}
                    findUser={this.props.findUser}
                    login={JSON.parse(a[idm][0])["login"]}
                    id={JSON.parse(a[idm][0])["id"]}
                    nom={JSON.parse(a[idm][0])["nom"]}
                    prenom={JSON.parse(a[idm][0])["prenom"]}
                    //date={JSON.parse(a[idm][0])["date"]}
                    text={JSON.parse(a[idm][0])["content"]} />)

                    }
                    
                </div>

                           
       )
    }
}

export default FluxMessages;

//<Message findUser={this.props.findUser} login={"colcx"} id={1234} nom={"La"} prenom={"Col"} date ={"date"} text={"sampletext"} />}

//what we have {"123":["Document{username:'test', id:234, nom:'testn', prenom:'testpn', date:'123', text:'123'}"],"111":["Document{username:'test', id:234, nom:'testn', prenom:'testpn', date:'123', text='123'}"]}
//what i need  '{"123":{"username":"test", "id":234, "nom":"testn", "prenom":"testpn", "date":"123", "text":"123"}, "456":{"id_message":"23444", "username":"secondusername", "id":888, "nom":"secondn", "prenom":"seconpn", "date":"987", "text":"second text"}}'


/*<Message findUser={this.props.findUser} login={item["username"]} id={item["id"]} nom={item["nom"]}
                    prenom={item["prenom"]} date={item["date"]} text={item["text"]} />)}

                    {/*this.messageArray()*/
                    /*{"123":["Document{username:'test', id:234, nom:'testn', prenom:'testpn', date:'123', text='123'}"],"111":["Document{username:'test', id:234, nom:'testn', prenom:'testpn', date:'123', text='123'}"]}.map((key,value) =>
                    <Message login={JSON.parse(value)["username"]} id={JSON.parse(value)["id_user"]} nom={JSON.parse(value)["nom"]}
                    prenom={JSON.parse(value)["prenom"]} date ={JSON.parse(value)["date"]} text={JSON.parse(value)["text"]} />)*/
