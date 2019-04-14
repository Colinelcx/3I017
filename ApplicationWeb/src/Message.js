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
                            <div className="card-text font-weight-bold ml-3">{this.props.prenom} {this.props.nom}</div>
                             <a className="card-text ml-3" href="#" onClick={() => this.props.findUser(this.props.login)}>@{this.props.login}</a>
                            <div className="card-text ml-3">{this.props.date}</div>
                        </div>
                </div>
                <div className="card-body">
                    <p className="card-text">{this.props.text}</p>
                </div> 
            </div>
       )
    }

}

export default Message;

//<div className="card-text ml-3">@{this.props.login}</div>