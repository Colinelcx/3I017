import React, { Component } from 'react';

class Statistiques extends Component {

	constructor(props) {
        super(props);
    }

    render() {
        return (
                <div class="card shadow card-stats" >
                    <div class="card-body">
                        <h3 class="card-title">Statistiques</h3>
                        <div class = "card-text">
                            Tops #
                            Utilisateurs avec le + d'abonnés
                        </div>
                    </div>
                </div>
        )
    }
}

export default Statistiques;
