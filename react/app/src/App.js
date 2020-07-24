import React from 'react';
import 'fontsource-roboto';
import './App.scss';
import Header from "./Component/Header/Header";
import {Register} from "./Register";
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";

function App() {
  return (
    <div className="App">
      <Header/>
        <Router>

        </Router>
      Route
      <Register/>
    </div>
  );
}

export default App;
