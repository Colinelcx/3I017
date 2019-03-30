import React, { Component } from 'react';

class Message extends Component {

	constructor(props) {
        super(props);
    }

    render() {
        return ( 
            <div class="card bg-light border-secondary mt-3 mb-3 shadow-sm mr-3" >
                <div class="card-body">
                    <form >
                        <div class="row">
                            <textarea class="form-control col-10 ml-3" id="exampleFormControlTextarea1" rows="2" placeholder="enter your message here"></textarea>
                            <button type="submit" class="btn btn-primary ml-3">Publier</button>
                        </div>
                    </form>
                </div> 
            </div>
       )
    }
}

export default Message;
