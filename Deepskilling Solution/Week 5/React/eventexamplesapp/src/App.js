import React, { Component } from "react";
import CurrencyConvertor from "./CurrencyConvertor";

class App extends Component {
  constructor() {
    super();
    this.state = {
      count: 0,
    };
  }

  increment = () => {
    this.setState({ count: this.state.count + 1 });
  };

  decrement = () => {
    this.setState({ count: this.state.count - 1 });
  };

  sayHello = () => {
    alert("Hello! Have a nice day.");
  };

  handleIncrement = () => {
    this.increment();
    this.sayHello();
  };

  welcome = (msg) => {
    alert(msg);
  };

  syntheticEvent = () => {
    alert("I was clicked");
  };

  render() {
    return (
      <div style={{ margin: "30px" }}>
        <h2>Counter : {this.state.count}</h2>

        <button onClick={this.handleIncrement}>
          Increment
        </button>

        <button onClick={this.decrement}>
          Decrement
        </button>

        <br /><br />

        <button onClick={() => this.welcome("Welcome")}>
          Say Welcome
        </button>

        <br /><br />

        <button onClick={this.syntheticEvent}>
          OnPress
        </button>

        <hr />

        <CurrencyConvertor />
      </div>
    );
  }
}

export default App;