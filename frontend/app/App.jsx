import React from 'react';

export default class Application extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            counter: 0
        }
    }

    render () {
        return <div>
            <h1>Hello React!</h1>
            <b>{this.state.counter}</b>
            <div>
                <button onClick={() => this.setState({counter: this.state.counter + 1})}>+</button>
                <button onClick={() => this.setState({counter: this.state.counter - 1})}>-</button>
            </div>
        </div>
    }
}