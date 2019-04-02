import React, { Component } from 'react';
import axios from 'axios';


class Test extends Component {
// necessite que ServeurWeb sur tomcat est actif: http://localhost:8080/ServeurTest/Hello
constructor(props) {
        super(props);

    }

	handleClick () {
	axios.get("http://localhost:8080/ServeurTest/Hello").then(response => { alert(response.data )})
	}
	
	render() {
        return (  <div>
                        test
                        <button type="submit" className="btn btn-primary col-md-4" onClick={(event) => this.handleClick()}>Test</button>
                    </div>
                   );}}
                   
                   
 export default Test;
