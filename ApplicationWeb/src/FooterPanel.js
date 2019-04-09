import React, { Component } from 'react';

class FooterPanel extends Component {

	constructor(props) {
        super(props);
    }

    render() {
        return (
        <footer className="footer sticky fixed-bottom  mt-auto py-3 bg-primary">
            <div className="container-fluid">
                <div className="row">
                    <div className="col-md-4 text-muted" align="center">
                        Authors : Lacoux Coline Felten Charel
                    </div>
                </div>
            </div>
        </footer>
        )
    }
}

export default FooterPanel;
