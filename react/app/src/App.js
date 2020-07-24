import React from 'react';
import 'fontsource-roboto';
import './App.scss';

import Header from "./Component/Header";

import {Register} from "./Component/Register";
import Dashboard from "./Component/Dashboard";
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
} from "react-router-dom";
import Login from "./Component/Login";


function App() {
    return (
        <div className="App">
            <Header/>
            <Router>
                <Switch>
                    <Route path="/register">
                        <Register />
                    </Route>
                    <Route path="/dashboard">
                        <Dashboard />
                    </Route>
                    <Route path="/login">
                        <Login/>
                    </Route>

                </Switch>
            </Router>


        </div>
    );
}
export default App;