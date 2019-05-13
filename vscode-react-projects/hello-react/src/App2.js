import React, { Component } from 'react';
import './App2.css';

class App2 extends Component {
    render() {
        return (
            <div className="App2">
                <h2>Hello React</h2>
                <hr />
                <dl>
                    <dt>index: 0</dt><dd>count: 1</dd>
                    <dt>index: 1</dt><dd>count: 2</dd>
                    <dt>index: 2</dt><dd>count: 3</dd>
                    <hr />
                    <h2>ES6 Syntax</h2>
                    {
                        [1, 2, 3, 4].map((e, i) =>
                            <React.Fragment>
                                <dt>index: {i}</dt>
                                <dd>count: {e}</dd>
                            </React.Fragment>
                        )
                    }
                </dl>
            </div>
        );
    }
}

export default App2;
