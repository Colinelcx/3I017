import React, { Component } from 'react';

class Statistiques extends Component {

	constructor(props) {
        super(props);
    }

    render() {
        return (
                <div class="card bg-light shadow-sm card-stats border-primary mt-3 ml-3" >

                    <div class="card-header">
                        <h3 class="card-title text-primary">Statistiques</h3>
                    </div>
                    
                    <ul class="list-group">
                        <li class="list-group-item">Cras justo odio</li>
                        <li class="list-group-item">Dapibus ac facilisis in</li>
                        <li class="list-group-item">Vestibulum at eros</li>
                    </ul>
                </div>
        )
    }
}

export default Statistiques;
