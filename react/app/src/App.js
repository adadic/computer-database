import React from 'react';
import 'fontsource-roboto';
import './App.scss';

import Header from "./Component/Header";
import {Register} from "./Component/Register";
import ComputerDashboard from "./Component/ComputerDashboard";
import {BrowserRouter, Switch, Route} from "react-router-dom";
import Login from "./Component/Login";
import CompanyDashboard from "./Component/CompanyDashboard";
import Computer from "./Component/Computer";
import Company from "./Component/Company";
import About from "./Component/About";
import Logout from "./Component/Logout";

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
                    <Route path={["/dashboard", "/"]}>
                        <ComputerDashboard />
                    </Route>

                </Switch>
            </div>
        </BrowserRouter>
    );
}
export default App;