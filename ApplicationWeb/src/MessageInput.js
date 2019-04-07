import React, { Component } from 'react';

class MessageInput extends Component {

	constructor(props) {
        super(props);
        this.state = {text:""};
        this.handleText = this.handleText.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleText(event) {
        this.setState({text: event.target.value});
    }

    handleSubmit(event) {
        alert(this.state.text);

    }

    render() {
        return (
            <div class="card bg-light border-secondary mt-3 mb-3 shadow-sm mr-3" >
                <div class="card-body">
                    <form onSubmit={this.handleSubmit}>
                        <div class="row">
                            <textarea class="form-control col-10 ml-3" id="messageText" rows="2" placeholder="enter your message here" onChange={this.handleText}></textarea>
                            <button type="submit" class="btn btn-primary ml-3" >Publier</button>
                        </div>
                    </form>
                </div> 
            </div>
       )
    }
}


export default MessageInput;
