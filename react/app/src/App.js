import React from 'react';
import 'fontsource-roboto';
import './App.scss';

import Header from "./Component/Page/Header";
import {Register} from "./Component/User/Register";
import ComputerDashboard from "./Component/Computer/ComputerDashboard";
import {BrowserRouter, Switch, Route} from "react-router-dom";
import Login from "./Component/User/Login";
import CompanyDashboard from "./Component/Company/CompanyDashboard";
import Computer from "./Component/Computer/Computer";
import Company from "./Component/Company/Company";
import About from "./Component/Page/About";
import Logout from "./Component/User/Logout";
import Home from "./Component/Page/Home";
import { Provider } from 'react-redux'

function App() {
    return (
        <BrowserRouter>
                <div className="App">
                    <Header/>
                    <Switch>
                        <Route path="/register">
                            <Register />
                        </Route>
                        <Route path={"/about"}>
                            <About/>
                        </Route>
                        <Route path={"/logout"}>
                            <Logout/>
                        </Route>
                        <Route path="/login">
                            <Login/>
                        </Route>
                        <Route path={"/computers/:id"}>
                            <Computer/>
                        </Route>
                        <Route path={"/companies/:id"}>
                            <Company/>
                        </Route>
                        <Route path={"/companies"}>
                            <CompanyDashboard/>
                        </Route>
                        <Route path={"/computers"}>
                            <ComputerDashboard />
                        </Route>
                        <Route path={"/home"}>
                            <Home />
                        </Route>

                    </Switch>
                </div>
        </BrowserRouter>
    );
}

export default App;