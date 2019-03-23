import React, { Component } from 'react';

class Test extends Component {

	constructor(props) {
        super(props);
    }

    render() {
        return (
            <body class="d-flex flex-column h-100">
<main role="main" class="flex-shrink-0">
  <div class="container">
    <h1 class="mt-5">Sticky footer</h1>
    <p class="lead">Pin a footer to the bottom of the viewport in desktop browsers with this custom HTML and CSS.</p>
    <p>Use <a href="/docs/4.3/examples/sticky-footer-navbar/">the sticky footer with a fixed navbar</a> if need be, too.</p>
  </div>
</main>

<footer class="footer mt-auto py-3">
  <div class="container">
    <span class="text-muted">Place sticky footer content here.</span>
  </div>
</footer>
</body>)
    }
}

export default Test;
